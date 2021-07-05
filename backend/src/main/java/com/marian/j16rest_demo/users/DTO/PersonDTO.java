package com.marian.j16rest_demo.users.DTO;

import com.marian.j16rest_demo.users.entity.RolesEntity;
import lombok.Builder;

import java.util.List;

public record PersonDTO(String name, String address, List<RoleDTO> roles) {
    @Builder
    public PersonDTO{};
}
