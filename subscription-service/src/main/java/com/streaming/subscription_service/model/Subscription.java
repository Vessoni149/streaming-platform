package com.streaming.subscription_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private PlanType planType;
    private Boolean autoRenew;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal price;

    // --- FECHAS DE NEGOCIO (Sin anotaciones de auditoría) ---
    // Se llenan en tu Service o mediante @PrePersist
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime nextBill;

    // --- FECHAS DE AUDITORÍA (Manejo automático) ---
    @CreatedDate
    @Column(updatable = false)        // Para que este campo no sea considerado en el LastModifiedDate.
    private LocalDateTime createdAt; // Cuándo se insertó el registro

    @LastModifiedDate
    private LocalDateTime updatedAt;    //  cuando se modifico algun campo de la entidad. Excepto createdAt que tiene 'updatable = false'

}
