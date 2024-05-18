package com.principal.daodto.service;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.daodto.persistence.dao.IUserDAO;
import com.principal.daodto.persistence.entity.UserEntity;
import com.principal.daodto.presentation.dto.UserDTO;

@Service
public class UserServiceImpl implements IUserService {

    // la inyeccion de dependecias debe ser privada, no olvidar los modificadores de
    // acceso
    @Autowired
    // inyectamos la clase DAO, haz de cuenta que es un repositorio
    private IUserDAO userDAO;
    // hacemos uso del modelMapper
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<UserDTO> findAll() {
        // recorremos el listado del resultado del DAO y lo convertimos a un DTO
        // mediante el modelMapper.map
        return this.userDAO.findAll().stream().map(entity -> modelMapper.map(entity, UserDTO.class)).toList();
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<UserEntity> userFound = userDAO.findById(id);
        if (userFound.isPresent()) {
            return modelMapper.map(userFound.get(), UserDTO.class);
        } else {
            return new UserDTO();
        }
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        try {
            UserEntity userEntity = modelMapper.map(user, UserEntity.class);
            this.userDAO.saveUser(userEntity);
            return user;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }
    }

    @Override
    public UserDTO updateUser(UserDTO user, Long id) {
        Optional<UserEntity> userEntity = this.userDAO.findById(id);
        if (userEntity.isPresent()) {
            UserEntity currentUser = userEntity.get();

            currentUser.setName(user.getName());
            currentUser.setLastName(user.getLastName());
            currentUser.setEmail(user.getEmail());
            currentUser.setAge(user.getAge());

            userDAO.updateUser(currentUser);
            return modelMapper.map(currentUser, UserDTO.class);
        } else {
            throw new IllegalArgumentException("No existe el usuario");
        }
    }

    @Override
    public String deleteUser(Long id) {

        try {
            UserEntity userEntity = userDAO.findById(id).orElseThrow();
            userDAO.deleteUser(userEntity);
            return "Usuario con ID "+id+" ha sido eliminado con exito. ";
        } catch (Exception e) {
            return "El usuario con ID "+id+" no existe.";
        }

    }

}
