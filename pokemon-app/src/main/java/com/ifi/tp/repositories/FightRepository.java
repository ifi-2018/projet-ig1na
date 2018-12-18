package com.ifi.tp.repositories;

import com.ifi.tp.fights.bo.Fight;
import org.springframework.data.repository.CrudRepository;

public interface FightRepository extends CrudRepository<Fight, Integer> {
}
