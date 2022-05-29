package com.connet.api.resource.v1;

import com.connet.api.model.dto.InstallerDTO;
import com.connet.api.model.entity.Installer;
import com.connet.api.service.InstallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController("InstallerResource")
@RequestMapping("/api/installer/v1/installers")
public class InstallerResource {
    @Autowired
    private InstallerService installerService;

    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createInstaller(@RequestParam Long id) {;
        return ResponseEntity.ok(installerService.createInstaller(id));
    }

    @PostMapping(value = "/login", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InstallerDTO> getInstaller(@RequestParam Long id, @RequestParam String password) {
        return ResponseEntity.ok(installerService.login(id, password));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InstallerDTO> getInstaller(@PathVariable UUID id) {
        return ResponseEntity.ok(installerService.getInstaller(id));
    }

    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Installer> updateInstaller(@PathVariable("id") UUID id, @RequestBody InstallerDTO installerDTO) {
        return ResponseEntity.ok(installerService.update(id, installerDTO));
    }
}
