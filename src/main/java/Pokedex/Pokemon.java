package Pokedex;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name;
    private Types type;
    private int maxHp;
    private int currentHp;
    private List<Attack> attacks;

    public Pokemon(String name, Types type, int maxHp, int currentHp) {
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.attacks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Types getType() {
        return type;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }
}
