package com.example.FirstProject.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {
    String id;
    String name;
    String age;
    String gender;
    String phoneNumber;
    String address;
}
