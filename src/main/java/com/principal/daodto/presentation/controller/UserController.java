package com.principal.daodto.presentation.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.daodto.presentation.dto.UserDTO;
import com.principal.daodto.service.IUserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//AQUI ESTABLEZCO LOS ENDPOINTS, USANDO COMO PARAMETRO SIEMPRE EL DTO
@RestController
@RequestMapping("/user")
public class UserController {

    //hacemos la inyeccion de dependencias de la interfaz UserService
    @Autowired 
    private IUserService userService;

    //endpoint para listar todos los usuarios
    @GetMapping("/find")
    public ResponseEntity<List<UserDTO>> findAll(){
        return new ResponseEntity<List<UserDTO>>(userService.findAll(), HttpStatus.OK);
    }
    //endpoint para encontrar un usuario
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return new ResponseEntity<UserDTO>(userService.findById(id), HttpStatus.OK);
    }

    //endpoint para crear un nuevo usuario
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<UserDTO>(userService.createUser(userDTO), HttpStatus.CREATED);
    }
    //endpoint para actualizar el usuario enviado
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user, @PathVariable Long id){
        return new ResponseEntity<UserDTO>(userService.updateUser(user, id) , HttpStatus.OK);
    }
    //endpoint para eliminar el usuario indicado 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<String>(userService.deleteUser(id), HttpStatus.NO_CONTENT);
    }
    

}
