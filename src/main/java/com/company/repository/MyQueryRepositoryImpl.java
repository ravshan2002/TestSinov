package com.company.repository;

import com.company.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;


public interface MyQueryRepositoryImpl extends ProfileRepository{
    @PersistenceContext
    public EntityManager entityManager =null;

    public default List  executeQuery(String query) {
        List resultList = entityManager.createNativeQuery(query).getResultList();
        return resultList;
    }


}
