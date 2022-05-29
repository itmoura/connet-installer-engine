package com.connet.api.repository;

import com.connet.api.model.entity.Installer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstallerRepository extends JpaRepository<Installer, UUID> {
    Optional<Installer> findById(Long id);

    Optional<Installer> findByInternId(UUID id);
}
