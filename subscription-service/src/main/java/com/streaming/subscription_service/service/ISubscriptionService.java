package com.streaming.subscription_service.service;

import com.streaming.subscription_service.dto.SubRequestDto;
import com.streaming.subscription_service.dto.SubResponseDto;
import com.streaming.subscription_service.dto.SubToUpdateDto;

import java.util.List;

public interface ISubscriptionService {

    public List<SubResponseDto> getAll();
    public SubResponseDto getById(Long id);
    public SubResponseDto createSub(SubRequestDto sub);
    public SubResponseDto updateSub (Long id, SubToUpdateDto sub);
    public void deleteSub (Long id);


}
