package com.streaming.subscription_service.dto;

import com.streaming.subscription_service.model.PlanType;
import com.streaming.subscription_service.model.Status;
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
public class SubResponseDto {

    private Long id;
    private Long userId;
    private PlanType planType;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime nextBill;
    private BigDecimal price;
    private Boolean autoRenew;
}
