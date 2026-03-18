package com.streaming.subscription_service.mapper;

import com.streaming.subscription_service.dto.SubRequestDto;
import com.streaming.subscription_service.dto.SubResponseDto;
import com.streaming.subscription_service.model.Subscription;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubResponseDto toDto(Subscription sub);
    Subscription toEntity(SubRequestDto subReqDto);

    List<SubResponseDto> toDtoList(List<Subscription> subList);

}
