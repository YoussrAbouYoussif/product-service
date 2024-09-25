package com.tech.task.product_service.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequest {

    @NotNull(message = "Name should not be empty.")
    private String name;

    private String description;
}
