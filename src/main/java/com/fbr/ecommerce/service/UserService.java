package com.fbr.ecommerce.service;

import com.fbr.ecommerce.controller.dto.CreateUserDto;
import com.fbr.ecommerce.entities.BillingAddresEntity;
import com.fbr.ecommerce.entities.UserEntity;
import com.fbr.ecommerce.repository.BillingAddressRepository;
import com.fbr.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UserEntity createUser(CreateUserDto dto) {
        var billingAddress = new BillingAddresEntity();
        billingAddress.setAddres(dto.Address());
        billingAddress.setComplement(dto.complement());
        billingAddress.setNumber(dto.number());

        var user = new UserEntity();
        user.setFullName(dto.fullName());
        user.setBillingAddresEntity(billingAddress);
        return userRepository.save(user);
    }

    public Optional<UserEntity> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    public Boolean deleteById(UUID userId) {
        var user = userRepository.findById(userId);

        if (user.isPresent()) {
            userRepository.deleteById(userId);
        }
        return user.isPresent();
    }
}
