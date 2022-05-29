package com.connet.api.model.entity;

import com.connet.api.model.dto.InstallerDTO;
import io.swagger.models.auth.In;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Installer implements Serializable {

    private static final long serialVersionUID = -1704664130824975429L;

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "intern_id")
    private UUID internId;

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price_per_km")
    private Integer pricePerKm;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lng;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "qtd", columnDefinition = "int default 0")
    private Integer qtd;

    public static Installer convert(InstallerDTO clientDTO) {
        Installer client = new Installer();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }
}
