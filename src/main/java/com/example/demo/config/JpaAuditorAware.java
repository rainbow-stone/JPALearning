package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/** 审计信息注入 */
@Component
@Slf4j
public class JpaAuditorAware implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    try {
      Long userId = 1L; // Token解析User信息
      return Optional.ofNullable(userId);
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
