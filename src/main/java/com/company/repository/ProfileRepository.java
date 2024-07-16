package com.company.repository;

import com.company.config.Config;
import com.company.entity.ProfileEntity;
import com.company.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {


    Optional<ProfileEntity> findByEmailAndStatus(String email, Status status);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update profile set status=:status where user_id=:userId",nativeQuery = true)
    Optional<ProfileEntity> updateProfileStatus(Integer userId, Status status);


//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(value = ,nativeQuery = true)
//    List<ProfileEntity> sinov(@Param("query") String query);

}
