package com.maitaryane.um.security;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


public interface TokenStorageRepository extends JpaRepository<TokenStorage, Long> {

	Optional<TokenStorage> findByAccessToken(String accessToken);
	Optional<TokenStorage> findByRefreshToken(String refreshToken);
	
    @Query("SELECT t FROM TokenStorage t WHERE t.accessToken LIKE :token or t.refreshToken LIKE :token")
    Optional<TokenStorage> findByToken( @Param("token") String token);
	
    @Transactional
    @Modifying
    @Query("DELETE FROM TokenStorage t WHERE t.date < :date")
    void deleteByDateBefore(@Param("date") LocalDateTime date);

}
