package com.ifi.tp.trainers.bo;

import com.ifi.tp.pokemonTypes.bo.PokemonType;
import com.ifi.tp.pokemonTypes.bo.Stats;

public class Pokemon {
    private int pokemonNumber;
    private int level;
    private int hp;
    private Stats stats;
    private PokemonType pokemonType;

    public int getPokemonNumber() {
        return pokemonNumber;
    }

    public void setPokemonNumber(int pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        //return stats.getHp();
        return this.hp;
    }

    public void setHp(int hp) {
        //this.stats.setHp(hp);
        this.hp = hp;
    }

    public PokemonType getPokemonType() {
        return pokemonType;
    }

    public Stats getStats() {
        return this.stats;
    }

    public void setPokemonType(PokemonType pokemonType) {
        this.stats = pokemonType.getStats();
        this.stats.setStatsFromLevel(this.level);
        this.pokemonType = pokemonType;
    }

    public String toString() {
        return "pokemon: " + pokemonType.getName() + " hp: "+ getHp() + " attack: " + getStats().getAttack() + " defense: " + getStats().getDefense();
    }
}
