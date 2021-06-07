package com.falcozerk.chargeweave.cloud;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

public class CwCloudApp {
    static String APP_NAME = "ChargeWeave";
    static String APP_PREFIX = "Cw";
    static String ACCOUNT_ID = "505741164853";
    static String REGION = "us-east-1";
    static String BACKEND_DOCKER_URL = "docker.io/falcozerk/chargeweave-backend:latest";
    static String VPC_NAME = APP_PREFIX + "Vpc";
    static String CLUSTER_NAME = APP_PREFIX + "Cluster";
    static String SERVICE_NAME = APP_PREFIX + "Service";
    static String SCALING_NAME = APP_PREFIX + "CpuBasedScaling";

    static String ENV_NAME = "env";
    static String STAGING_NAME = "staging";
    static String PROD_NAME = "production";

    public static void main(final String[] args) {
        App app = new App();

        new CwCloudStack(app, "CwCloudStack", StackProps.builder()
                .env(Environment.builder()
                        .account(ACCOUNT_ID)
                        .region(REGION)
                        .build())
                .build());

        app.synth();
    }
}
