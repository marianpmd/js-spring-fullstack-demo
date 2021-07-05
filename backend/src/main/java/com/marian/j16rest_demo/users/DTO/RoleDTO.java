package com.marian.j16rest_demo.users.DTO;

import lombok.Builder;


public record RoleDTO(String name, Double salary) {
    @Builder
    public RoleDTO {
    }
}
