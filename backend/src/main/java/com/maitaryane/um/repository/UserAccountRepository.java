package com.maitaryane.um.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maitaryane.um.entity.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	
    Optional<UserAccount> findByUsername(String username);
    
    @Query("SELECT u FROM UserAccount u WHERE CONCAT(u.firstname, u.lastname, u.username) LIKE %:keyword%")
    Page<UserAccount> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
