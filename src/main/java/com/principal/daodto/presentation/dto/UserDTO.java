package com.principal.daodto.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//en mi caso utilizaré un record en lugar de una clase para crear mi DTO

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{

    Long id; 

    String name;

    String lastName; 

    String email;

    int age;



}
