package com.backend.aaz.shared.models.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.aaz.shared.models.audit.AuditEntry;
import com.backend.aaz.shared.models.user.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "actor")
    private List<AuditEntry> auditEntries;

    public User(String name, String encryptedPassword, UserRole role, String email, Boolean isActive) {
        this.name = name;
        this.password = encryptedPassword;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == UserRole.MANAGER) return List.of(new SimpleGrantedAuthority("MANAGER"), new SimpleGrantedAuthority("OPERATOR"), new SimpleGrantedAuthority("VIEWER"));
        else if (role == UserRole.OPERATOR) return List.of(new SimpleGrantedAuthority("OPERATOR"), new SimpleGrantedAuthority("VIEWER"));
        else if (role == UserRole.VIEWER) return List.of(new SimpleGrantedAuthority("VIEWER"));
        else return List.of(new SimpleGrantedAuthority("VIEWER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.isActive);
    }

}