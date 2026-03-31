package com.backend.aaz.shared.models.audit;

import java.time.LocalDateTime;
import java.util.UUID;

import com.backend.aaz.shared.models.audit.enums.AuditAction;
import com.backend.aaz.shared.models.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "audit_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id", nullable = false)
    private User actor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditAction action;

    @Column(nullable = false, name = "target_type")
    private String targetType;

    @Column(nullable = false, name = "target_id")
    private UUID targetId;

    @Column(columnDefinition = "TEXT")
    private String metadata;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}