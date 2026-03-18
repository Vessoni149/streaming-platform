package com.streaming.subscription_service.dto;

import com.streaming.subscription_service.model.PlanType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubToUpdateDto {


    @NotNull(message = "Plan type is required")
    private PlanType planType;

    @NotNull(message = "Auto renew must be specified")
    private Boolean autoRenew;
}
