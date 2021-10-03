package com.bol.kalaha.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.bol.kalaha.dto.CreateGameReply;
import com.bol.kalaha.dto.MakeAMoveReply;

@SpringBootTest
@ActiveProfiles("test")
public class GameServiceIntegrationTest {

  @Autowired
  private GameService gameService;

  @Test
  public void testCreateGame() {
    CreateGameReply reply = gameService.createGame("");

    assertTrue(reply.getGameId() != null);
  }

  @Test
  public void testPlayer1Move() {
    CreateGameReply replyGame = gameService.createGame("");

    MakeAMoveReply reply = gameService.makeAMoveByPlayer(replyGame.getGameId(), 2, "");

    assertTrue(reply.getStatus().containsKey(2));
    assertTrue(reply.getStatus().get(2) == 0);
    assertTrue(reply.getStatus().get(7) == 1);
    assertTrue(reply.getStatus().get(8) == 7);

  }

  @Test
  public void testPlayer2Move() {
    CreateGameReply replyGame = gameService.createGame("");

    MakeAMoveReply reply = gameService.makeAMoveByPlayer(replyGame.getGameId(), 10, "");

    assertTrue(reply.getStatus().containsKey(10));
    assertTrue(reply.getStatus().get(10) == 0);
    assertTrue(reply.getStatus().get(14) == 1);
    assertTrue(reply.getStatus().get(2) == 7);

  }

}
