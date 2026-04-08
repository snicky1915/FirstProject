package com.example.FirstProject.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonQueryRequest {

    // Chuoi query search/tra cuu duoc gui tu client len.
    @NotBlank(message = "query khong duoc de trong")
    private String query;
}
