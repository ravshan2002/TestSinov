package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {






    public ProfileDTO fromEntityToDTO(ProfileEntity entity){
        ProfileDTO dto = new ProfileDTO();
        dto.setRole(entity.getRole());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setSurname(entity.getSurname());

        return dto;
    }
}
