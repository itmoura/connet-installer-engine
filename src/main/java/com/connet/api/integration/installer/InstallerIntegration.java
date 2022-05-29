package com.connet.api.integration.installer;

import com.connet.api.model.dto.InstallerDTO;
import org.hibernate.cfg.beanvalidation.IntegrationException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@FeignClient(name = "installer", url = "${api.installer.host}${api.installer.v1.basePath}")
public interface InstallerIntegration {

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    InstallerDTO getInstaller(@PathVariable("id") Long id) throws IntegrationException;
}
