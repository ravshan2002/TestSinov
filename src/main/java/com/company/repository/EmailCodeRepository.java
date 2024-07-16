package com.company.repository;

import com.company.entity.EmailCodeEntity;
import com.company.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailCodeRepository extends CrudRepository<EmailCodeEntity, Integer> {

    Optional<EmailCodeEntity> findByUserIdAndStatus(Integer userId, Status status);

    @Transactional
    @Modifying
    @Query(value = "update email_code set status=:status where user_id=:userId",nativeQuery = true)
    void updateEmailCodeStatus(Integer userId, Status status);

}
