package com.bol.kalaha.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The type Create game reply.
 */
@Data
@Builder
@AllArgsConstructor
public class CreateGameReply {

  /**
   * The Game id.
   */
  private Long gameId;
  /**
   * The Uri.
   */
  private String uri;
}
