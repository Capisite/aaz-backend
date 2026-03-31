package com.backend.aaz.shared.models.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.backend.aaz.shared.models.totemdevice.TotemDevice;
import com.backend.aaz.shared.models.transaction.enums.PaymentMethod;
import com.backend.aaz.shared.models.transaction.enums.TransactionStatus;
import com.backend.aaz.shared.models.user.User;
import com.backend.aaz.shared.models.lineitem.LineItem;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "totem_id", nullable = false)
    private TotemDevice totem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "payment_method", updatable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false, precision = 10, scale = 2, name = "total_value", updatable = false)
    @DecimalMin(value = "0.01", message = "O valor total deve ser maior que zero")
    private BigDecimal totalValue;

    @Column(nullable = false, name = "completed_at", updatable = false)
    private LocalDateTime completedAt;

    @Column(nullable = false, updatable = false, name = "received_at")
    private LocalDateTime receivedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voided_by_id")
    private User voidedBy;

    @Column(name = "voided_at")
    private LocalDateTime voidedAt;

    @Column(length = 500, name = "void_reason")
    private String voidReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linked_to_id")
    private Transaction linkedTo;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<LineItem> items;

}
