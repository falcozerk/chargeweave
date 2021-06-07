package com.falcozerk.chargeweave.cloud;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.applicationautoscaling.EnableScalingProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.CpuUtilizationScalingProps;
import software.amazon.awscdk.services.ecs.ScalableTaskCount;
import software.amazon.awscdk.services.ecs.patterns.*;
import software.amazon.awscdk.services.elasticloadbalancingv2.*;

public class CwCloudStack extends Stack {

    static int CPU = 1024;
    static int MEMORY_LIMIT = 2048;
    static int SERVICE_COUNT = 2;
    static int MIN_PROD_TASK_COUNT = 2;
    static int MAX_PROD_TASK_COUNT = 6;
    static int SPRING_BOOT_PORT = 8080;
    static int CPU_UTILIZATION = 50;

    public CwCloudStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CwCloudStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        Vpc vpc = new Vpc(this, CwCloudApp.VPC_NAME);
        Cluster cluster = Cluster.Builder.create(this, CwCloudApp.CLUSTER_NAME).vpc(vpc).build();
        buildSpringBootService(cluster);
    }

    public void buildSpringBootService(Cluster cluster) {
        ApplicationLoadBalancedFargateService fargateService =
                ApplicationLoadBalancedFargateService.Builder.create(this, CwCloudApp.SERVICE_NAME)
                        .cluster(cluster)
                        .cpu(CPU)
                        .memoryLimitMiB(MEMORY_LIMIT)
                        .desiredCount(SERVICE_COUNT)
                        .taskImageOptions(
                                ApplicationLoadBalancedTaskImageOptions.builder()
                                .image(ContainerImage.fromAsset("../backend"))
                                .containerPort(SPRING_BOOT_PORT)
                                .build())
                        .publicLoadBalancer(true)
                        .assignPublicIp(false)
                        .build();

        fargateService.getTargetGroup().configureHealthCheck(
                HealthCheck.builder()
                .healthyHttpCodes("200")
                .path("/actuator/health")
                .port("8080")
                .build());

        checkBuildProdTask(fargateService);
    }

    public void checkBuildProdTask(ApplicationLoadBalancedFargateService fargateService) {
        String environment = System.getenv(CwCloudApp.ENV_NAME);
        if(environment != null && !environment.isEmpty() && environment.equals(CwCloudApp.PROD_NAME)) {
            ScalableTaskCount scalableTask =
                    fargateService.getService().autoScaleTaskCount(EnableScalingProps.builder()
                    .minCapacity(MIN_PROD_TASK_COUNT)
                    .maxCapacity(MAX_PROD_TASK_COUNT)
                    .build()
            );

            scalableTask.scaleOnCpuUtilization( CwCloudApp.SCALING_NAME, CpuUtilizationScalingProps.builder()
                    .targetUtilizationPercent(CPU_UTILIZATION)
                    .build()
            );
        }
    }
}
