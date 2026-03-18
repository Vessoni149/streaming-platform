package com.streaming.subscription_service.dto;

import com.streaming.subscription_service.model.PlanType;
import com.streaming.subscription_service.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Plan type is required")
    private PlanType planType;

    @NotNull(message = "Auto renew must be specified")
    private Boolean autoRenew;
}
