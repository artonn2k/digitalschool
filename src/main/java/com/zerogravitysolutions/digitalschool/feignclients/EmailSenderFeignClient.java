package com.zerogravitysolutions.digitalschool.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;


@Service
@FeignClient(value = "email-service", url = "${feign.address.email-service}")
public interface EmailSenderFeignClient {

    @PostMapping(path = "/v1/send",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> send(
            @RequestPart(name = "subject") String subject,
            @RequestPart(name = "to") String recipients,
            @RequestPart(name = "body") String body
    );
}
