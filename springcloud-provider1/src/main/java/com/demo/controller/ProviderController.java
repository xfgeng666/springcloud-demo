package com.demo.controller;

import com.demo.common.I18N.I18NUtils;
import com.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author xfgeng
 * @date 2020-11-16 15:16
 */
@Slf4j
@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    private AppConfig appConfig;

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/sayHello")
    public String sayHello(String name) {
        log.info("provider-demo1: {} {} {}", appConfig.getAppId(), appConfig.getAppName(), name);
        return "provider-demo1：hello," + name + " [appId：" + appConfig.getAppId() + ",appName:" + appConfig.getAppName() + "]";
    }

    /**
     * 测试服务
     * @return
     */
    @GetMapping(path="/sayNo")
    public String sayNo(String name) {
        return "no，" + name;
    }


    @RequestMapping(value = "/echo/{str}", method = RequestMethod.POST)
    public String i18nEcho(@RequestBody RequestEntity requestEntity, @PathVariable String str) {
        String language = requestEntity.getHeaders().getFirst("Language");
        String contentLanguage = requestEntity.getHeaders().getContentLanguage().getLanguage();

        Object[] args = {str,language,contentLanguage};
        return I18NUtils.msg("tsf.gateway.create", args, language);
    }

}
