package com.backend.aaz.shared.models.totemdevice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.backend.aaz.shared.models.totemdevice.enums.TotemDeviceStatus;
import com.backend.aaz.shared.models.transaction.Transaction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "totem_devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotemDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50, name = "display_name")
    private String displayName;

    @Column(nullable = false, name = "device_token")
    private String deviceToken;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TotemDeviceStatus status = TotemDeviceStatus.OFFLINE;

    @Column(length = 200, name = "error_description")
    private String errorDescription;

    @Column(name = "last_seen_at")
    private LocalDateTime lastSeenAt;

    @Column(nullable = false, updatable = false, name = "enrolled_at")
    private LocalDateTime enrolledAt;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "totem")
    private List<Transaction> transactions;

    @OneToOne(mappedBy = "totem", cascade = CascadeType.ALL)
    private TotemConfiguration configuration;

    @OneToMany(mappedBy = "totem")
    private List<PairingSession> pairingSessions;

}
