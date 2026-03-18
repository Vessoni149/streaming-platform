package com.streaming.subscription_service.controller;

import com.streaming.subscription_service.dto.SubRequestDto;
import com.streaming.subscription_service.dto.SubResponseDto;
import com.streaming.subscription_service.dto.SubToUpdateDto;
import com.streaming.subscription_service.responses.ApiResponse;
import com.streaming.subscription_service.service.ISubscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    private ISubscriptionService subService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubResponseDto>>> getSubscriptions(){
        List<SubResponseDto> subscriptions = subService.getAll();
        return ResponseEntity.ok(ApiResponse.success(subscriptions, "Subscriptions retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubResponseDto>> getSubscription(@PathVariable Long id){
        SubResponseDto response = subService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(response,"Subscription retrieved successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SubResponseDto>> createUser(@Valid @RequestBody SubRequestDto subRequestDto) {
        SubResponseDto createdSub = subService.createSub(subRequestDto);
        return new ResponseEntity<>(
                ApiResponse.success(createdSub, "Subscription created successfully"),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSub(@PathVariable Long id) {
        subService.deleteSub(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Subscription deleted successfully"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<SubResponseDto>> updateSub(
            @PathVariable Long id,
            @Valid @RequestBody SubToUpdateDto request) {

        SubResponseDto updatedUser = subService.updateSub(id, request);
        return ResponseEntity.ok(ApiResponse.success(updatedUser, "Subscription updated successfully"));
    }

}
