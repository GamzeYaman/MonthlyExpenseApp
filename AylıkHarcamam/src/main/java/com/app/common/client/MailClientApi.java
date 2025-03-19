package com.app.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "mail-islemleri", url = "${mail.url}")
public interface MailClientApi {
    @GetMapping("/send")
    String sendBatchMail();
}
