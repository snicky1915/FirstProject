package com.example.FirstProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiflowRequestVO<T> {

    @NotNull(message = "header không được null")
    @Valid
    private DiflowRequestHeaderVO header;

    @NotNull(message = "body không được null")
    @Valid
    private T body;
}
