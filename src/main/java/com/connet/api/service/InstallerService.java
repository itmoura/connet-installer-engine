package com.connet.api.service;

import com.connet.api.exception.InstallerException;
import com.connet.api.integration.installer.InstallerIntegration;
import com.connet.api.model.dto.InstallerDTO;
import com.connet.api.model.entity.Installer;
import com.connet.api.repository.InstallerRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Log4j2
public class InstallerService {

    private final InstallerRepository installerRepository;
    private final InstallerIntegration installerIntegration;

    public InstallerService(@Autowired InstallerRepository installerRepository, @Autowired InstallerIntegration installerIntegration) {
        this.installerRepository = installerRepository;
        this.installerIntegration = installerIntegration;
    }

    public String createInstaller(Long id) {
        Optional<Installer> byId = installerRepository.findById(id);
        if (byId.isPresent())
            throw new InstallerException("Installer already exists");
        InstallerDTO installerDTO = installerIntegration.getInstaller(id);
        Installer installer = Installer.convert(installerDTO);
        String password = new String(generatePassword(8));
        installer.setPassword(passwordEncoder(password));
        installerRepository.save(installer);

        return password;
    }

    public InstallerDTO login(Long id, String password) {
        InstallerDTO installerDTO = InstallerDTO.convert(installerRepository.findById(id).orElseThrow(() -> new InstallerException("Invalid Id")));
        if(passwordDecoder(installerDTO.getPassword()).equals(password)) {
            return installerDTO;
        }
        throw new InstallerException("Invalid Password");
    }

    private static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

    private static String passwordEncoder(String password) {
        return new Base64().encodeToString(password.getBytes());
    }

    private static String passwordDecoder(String passwordCripto) {
        return new String(new Base64().decode(passwordCripto));
    }

    public UUID update(UUID id, InstallerDTO installerDTO) {
        Installer c = installerRepository.findByInternId(id).orElseThrow(() -> new InstallerException("Invalid Id"));
        installerDTO.setInternId(c.getInternId());
        installerDTO.setId(c.getId());
        return installerRepository.save(Installer.convert(installerDTO)).getInternId();
    }

    public InstallerDTO getInstaller(UUID id) {
        return InstallerDTO.convert(installerRepository.findByInternId(id).orElseThrow(() -> new InstallerException("Invalid Id")));
    }

    public InstallerDTO getInstallerExtern(Long id) {
        return installerIntegration.getInstaller(id);
    }

    public InstallerDTO getInstallerIntern(Long id) {
        return InstallerDTO.convert(installerRepository.findById(id).orElseThrow(() -> new InstallerException("Invalid Id")));
    }
}
