package com.example.FirstProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiflowRequestHeaderVO {

    @NotBlank(message = "cid không được rỗng")
    @Size(max = 50, message = "cid tối đa 50 ký tự")
    private String cid;

    @NotBlank(message = "tid không được rỗng")
    @Size(max = 50, message = "tid tối đa 50 ký tự")
    private String tid;

    @Size(max = 100, message = "tgt tối đa 100 ký tự")
    private String tgt;
}
