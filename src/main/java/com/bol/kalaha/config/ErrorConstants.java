package com.bol.kalaha.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.bol.kalaha.dto.ErrorAttributes;

/**
 * The type Error constants.
 */
@Component
@Data
@ConfigurationProperties("constants.error")
public class ErrorConstants {

  private ErrorAttributes invalidGameIdException;
  private ErrorAttributes emptyPitException;
  private ErrorAttributes invalidPitIndexException;
  private ErrorAttributes wrongGameStatusException;
  private ErrorAttributes gameOverException;
}
