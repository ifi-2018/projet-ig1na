package com.ifi.tp.repositories;

import com.ifi.tp.fights.bo.Fight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FightRepository extends CrudRepository<Fight, Integer> {
}
