package com.example.csvloadfile.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Client {
    private Long id;
    private String name;
    private Integer age;
    private Integer groupId;
    private String phone;
    private LocalDate date;
}
