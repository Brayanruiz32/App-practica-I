package com.principal.daodto.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.principal.daodto.persistence.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements IUserDAO {
    // esepecificacion del entitymanager
    @PersistenceContext
    EntityManager em;

    @Override
    // se especifica si el transactional debe de ser solo de lectura, de lo
    // contrario en vacio.
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
    // las sentencias sql creadas mediante el entitymanager se trabaja con las clases
        return this.em.createQuery("SELECT u FROM UserEntity u").getResultList();
    }

    @Override
    // se especifica si el transaccional debe de ser solo de lectura
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
    //mediante el uso del optional se hace una consulta a la base de datos, anidada dentro mediante el entitymanager un find id
        return Optional.of(this.em.find(UserEntity.class, id));
    }

    @Override
    // el transaccional ejecuta el metodo hacia la BD
    @Transactional
    public void saveUser(UserEntity userEntity) {
        this.em.persist(userEntity);
        this.em.flush();
    }

    @Override
    // se especifica el transaccional para la ejecucion del metodo
    @Transactional
    public void updateUser(UserEntity userEntity) {
        this.em.merge(userEntity);
    }

    @Override
    // se especifica el transaccional para la ejecucion del metodo
    @Transactional
    public void deleteUser(UserEntity userEntity) {
        this.em.remove(userEntity);
    }

}
