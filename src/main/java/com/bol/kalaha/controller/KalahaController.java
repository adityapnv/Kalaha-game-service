package com.bol.kalaha.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bol.kalaha.dto.CreateGameReply;
import com.bol.kalaha.dto.MakeAMoveReply;
import com.bol.kalaha.service.GameService;

/**
 * Kalaha controller
 *
 */
@RestController
public class KalahaController {
	
	private GameService gameService;
	
	public KalahaController(GameService gameService) {
		this.gameService = gameService;
	}
	
	/**
	 * Create game entity.
	 * @param request - player request
	 * @return the response entity with gameID.
	 */
	  @PostMapping("/kalaha-games")
	  public ResponseEntity<CreateGameReply> createGame(HttpServletRequest request) {
	    return new ResponseEntity<>(gameService.createGame(request.getRequestURL().toString()),
	        HttpStatus.CREATED);
	  }
	  
	  /**
	   * Play game response entity.
	   *
	   * @param gameId   the game id
	   * @param pitIndex the pit index
	   * @param request  the request
	   * @return the response entity
	   */
	  @PutMapping("/kalaha-games/{gameId}/pits/{pitIndex}")
	  public ResponseEntity<MakeAMoveReply> playGame(@PathVariable Long gameId, @PathVariable Integer pitIndex,
	      HttpServletRequest request) {

	    return new ResponseEntity<>(gameService
	        .makeAMoveByPlayer(gameId, pitIndex, request.getRequestURL().toString().replace(request.getRequestURI(), "")),
	        HttpStatus.OK);

	  }

}
