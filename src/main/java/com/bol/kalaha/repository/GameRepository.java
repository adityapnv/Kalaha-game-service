package com.bol.kalaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bol.kalaha.model.Game;

/**
 * Repository to interact with Game Entity
 */
public interface GameRepository extends JpaRepository<Game, Long> {

}
