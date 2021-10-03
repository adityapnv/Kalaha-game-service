package com.bol.kalaha.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bol.kalaha.util.KalahCommonUtil;
import com.bol.kalaha.dto.GameStatus;
import com.bol.kalaha.exception.InvalidPitIndexException;
import com.bol.kalaha.model.Game;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.repository.PitRepository;
import com.bol.kalaha.service.impl.KalahaRuleServiceImpl;

@SpringBootTest
public class KalahRuleServiceTest {

  @Mock
  private PitRepository pitRepo;

  private KalahaRuleServiceImpl kalahaRuleService;

  @BeforeEach
  private void setUp() {
    kalahaRuleService = new KalahaRuleServiceImpl(pitRepo);
  }

  @Test
  public void testGetPlayerTurn() {
    GameStatus player1Status = kalahaRuleService.getPlayerTurn(2);
    assertTrue(GameStatus.PLAYER1_TURN == player1Status);

    GameStatus player2Status = kalahaRuleService.getPlayerTurn(8);
    assertTrue(GameStatus.PLAYER2_TURN == player2Status);

  }

  @Test
  public void testGetPlayerTurnException() {
    assertThrows(InvalidPitIndexException.class, () -> kalahaRuleService.getPlayerTurn(7));

  }

  @Test
  public void testGetOppositePit() {
    assertTrue(kalahaRuleService.getOppositePit(2) == 12);
  }

  @Test
  public void testGetOppositePitException() {
    assertThrows(InvalidPitIndexException.class, () -> kalahaRuleService.getOppositePit(7));
  }

  @Test
  public void testNextTurnOfPlayer() {
    assertTrue(kalahaRuleService.getNextTurnOfPlayer(GameStatus.PLAYER1_TURN, 7) == GameStatus.PLAYER1_TURN);
    assertTrue(kalahaRuleService.getNextTurnOfPlayer(GameStatus.PLAYER2_TURN, 14) == GameStatus.PLAYER2_TURN);
    assertTrue(kalahaRuleService.getNextTurnOfPlayer(GameStatus.PLAYER1_TURN, 12) == GameStatus.PLAYER2_TURN);
  }

  @Test
  public void testIfLastPitWasEmptyAndUpdate() {
    Map<Integer, Pit> map = kalahaRuleService.checkAndUpdateIfLastPitIsEmpty(getMapOfPits(), 3,
        GameStatus.PLAYER1_TURN);
    System.out.println(map.get(7).getNumberOfStones());
    assertTrue(map.get(7).getNumberOfStones() == 8);
    assertTrue(map.get(3).getNumberOfStones() == 0);
    assertTrue(map.get(11).getNumberOfStones() == 0);
  }

  @Test
  public void testMoveStonesInBoard() {

    Map<Integer, Pit> map = kalahaRuleService.moveTheStonesInBoard(getPitList(), 14, 10, 4, 0, 6);
    assertTrue(map.get(4).getNumberOfStones() == 0);
    assertTrue(map.get(10).getNumberOfStones() == 7);

  }

  @Test
  public void testIfGameIsEnded() {

    List<Integer> player1Pits = new ArrayList<Integer>();
    Collections.addAll(player1Pits, KalahCommonUtil.PLAYER_1_PITS);
    when(pitRepo.getSumOfStonesInPlayerPit(eq(1L), eq(player1Pits))).thenReturn(0);

    Pit pit = new Pit();
    pit.setNumberOfStones(4);
    pit.setPitIndex(14);

    when(pitRepo.getPitByGameIdAndPitIndex(any(), any())).thenReturn(Optional.of(pit));

    Game game = kalahaRuleService.checkGameEnded(new Game());
    assertTrue(game.getGameStatus() == GameStatus.FINISHED);

  }

  private List<Pit> getPitList() {
    List<Pit> pits = new ArrayList<>();
    IntStream.range(KalahCommonUtil.PIT_START_INDEX, KalahCommonUtil.PIT_END_INDEX + 1).sequential()
        .forEach(index -> {

          Pit pit = new Pit();

          if (index == KalahCommonUtil.PLAYER1_HOUSE) {
            pit.setNumberOfStones(1);
          } else if (index == KalahCommonUtil.PLAYER2_HOUSE) {
            pit.setNumberOfStones(1);
          } else {
            pit.setNumberOfStones(KalahCommonUtil.INITIAL_STONE_ON_PIT);
          }
          if (index == 3) {
            pit.setNumberOfStones(1);
          }
          if (index == 2) {
            pit.setNumberOfStones(1);
          }
          pit.setPitIndex(index);
          pits.add(pit);
        });

    return pits;
  }

  private Map<Integer, Pit> getMapOfPits() {

    return getPitList().stream().collect(Collectors.toMap(Pit::getPitIndex, pit -> pit));
  }
}
