package com.bol.kalaha.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The type Make a move reply.
 */
@Data
@Builder
@AllArgsConstructor
public class MakeAMoveReply {

  /**
   * The Id.
   */
  private Long id;
  /**
   * The Uri.
   */
  private String uri;
  /**
   * The Status.
   */
  private Map<Integer, Integer> status;

}
