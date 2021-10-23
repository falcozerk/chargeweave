package com.falcozerk.chargeweave.beans.app;

import com.falcozerk.chargeweave.beans.common.CwController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app")
public class AppController extends CwController {
    static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    AppService appService;

    @GetMapping("/importData")
    public String importData() {
        return appService.importData();
    }

    @GetMapping("/clearData")
    public String clearData() {
        return appService.clearData();
    }

}
