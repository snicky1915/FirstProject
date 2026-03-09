package com.example.FirstProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonBodyVO {

    @NotBlank(message = "ten không được rỗng")
    @Size(max = 100, message = "ten tối đa 100 ký tự")
    private String ten;

    @NotNull(message = "tuoi không được null")
    @Min(value = 0, message = "tuoi phải >= 0")
    private Integer tuoi;
}
