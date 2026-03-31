package com.backend.aaz.shared.models.totemdevice;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "totem_configurations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotemConfiguration {

    @Id
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "totem_id")
    private TotemDevice totem;

    @Column(nullable = false, name = "manual_catalog_enabled")
    private Boolean manualCatalogEnabled = false;

    @Column(length = 200, name = "welcome_message")
    private String welcomeMessage;

    @Column(nullable = false, name = "config_version")
    private Integer configVersion = 1;

    @Column(name = "last_pushed_at")
    private LocalDateTime lastPushedAt;

    @Column(name = "acknowledged_at")
    private LocalDateTime acknowledgedAt;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

}