package com.maitaryane.um.security;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SchedulerTokenOperations {
	private TokenStorageService storageService;
	@Scheduled(fixedRate = 600 * 1000)
    public void deleteExpiredToken()
    {
		storageService.deleteByDateBefore(LocalDateTime.now().minusSeconds(300));
    }
}
