package com.streaming.subscription_service.service;

import com.streaming.subscription_service.clients.UserClient;
import com.streaming.subscription_service.dto.SubRequestDto;
import com.streaming.subscription_service.dto.SubResponseDto;
import com.streaming.subscription_service.dto.SubToUpdateDto;
import com.streaming.subscription_service.exceptions.ResourceNotFoundException;
import com.streaming.subscription_service.mapper.SubscriptionMapper;
import com.streaming.subscription_service.model.Status;
import com.streaming.subscription_service.model.Subscription;
import com.streaming.subscription_service.repository.ISubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService{

    @Autowired
    private ISubscriptionRepository subRepo;
    @Autowired
    private SubscriptionMapper subMapper;
    @Autowired
    private UserClient userClient;

    @Override
    public List<SubResponseDto> getAll() {
        List<Subscription> subsList = subRepo.findAll();
        return subMapper.toDtoList(subsList);
    }

    @Override
    public SubResponseDto getById(Long id) {
        Subscription sub = subRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id, "Subscription")
        );
        return subMapper.toDto(sub);
    }

    @Override
    public SubResponseDto createSub(SubRequestDto sub) {
        //Antes de crear la sub verificaremos que el usuario al que le va a pertenecer existe
        // llamando al user service con Feign
        Boolean exists = userClient.checkUserExists(sub.getUserId());
        if (!exists) {
            throw new ResourceNotFoundException(sub.getUserId(), "User");
        }
        //Si existe continua la ejecucion.
        Subscription subToSave = subMapper.toEntity(sub);
        subToSave.setStatus(Status.ACTIVE);

        LocalDateTime now = LocalDateTime.now();
        subToSave.setStartDate(now);
        subToSave.setEndDate(now.plusMonths(1).plusDays(5));
        subToSave.setNextBill(now.plusMonths(1));

        BigDecimal finalPrice = switch (sub.getPlanType()) {
            case BASIC -> BigDecimal.valueOf(5);
            case ESSENTIAL -> BigDecimal.valueOf(12.5);
            case PRO -> BigDecimal.valueOf(18.9);
            case EXPERT -> BigDecimal.valueOf(25);
        };
        subToSave.setPrice(finalPrice);
        subRepo.save(subToSave);
        return subMapper.toDto(subToSave);
    }

    @Override
    public SubResponseDto updateSub(Long id, SubToUpdateDto sub) {
        Subscription subToUpdate = subRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id, "Subscription")
        );
        if(sub.getAutoRenew() != null){
            subToUpdate.setAutoRenew(sub.getAutoRenew());
        }
        if(sub.getPlanType() != null){
            subToUpdate.setPlanType(sub.getPlanType());
        }
        Subscription subUpdated = subRepo.save(subToUpdate);

        return subMapper.toDto(subUpdated);
    }

    @Override
    public void deleteSub(Long id) {
        if(!subRepo.existsById(id)){
            throw  new ResourceNotFoundException(id, "Subscription");
        }
        subRepo.deleteById(id);
    }




}
