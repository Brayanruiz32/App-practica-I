package com.principal.daodto.service;

import java.util.List;

import com.principal.daodto.persistence.entity.UserEntity;
import com.principal.daodto.presentation.dto.UserDTO;

//implementacion de la interfaz, las cuales devuelven dto's aún 

public interface IUserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Long id);

    String deleteUser(Long id);

}
