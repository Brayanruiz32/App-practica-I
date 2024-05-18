package com.principal.daodto.persistence.dao;

import java.util.List;
import java.util.Optional;

import com.principal.daodto.persistence.entity.UserEntity;

public interface IUserDAO {
    
    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    void saveUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);

}
