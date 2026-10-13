package Pokedex;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private final String name;
    private final Types type;
    private final int maxHp;
    private int currentHp;
    private final int speed;
    private final List<Attack> attacks;

    public static final int DEFAULT_SPEED = 50;
    public static final int MAX_SPEED = 999;

    public Pokemon(String name, Types type, int maxHp, int currentHp, int speed) {
        if (speed < 1 || speed > MAX_SPEED) {
            throw new PokemonException("Speed must be between 1 and " + MAX_SPEED + ", got: " + speed);
        }
        if (name == null || name.isBlank()) {
            throw new PokemonException("Name can't be empty");
        }
        if (type == null) {
            throw new PokemonException("Type must be given");
        }
        if (maxHp <= 0) {
            throw new PokemonException("Max HP must be over 0");
        }
        if (currentHp < 0) {
            throw new PokemonException("Current HP can't be negative");
        }

        this.name = name.trim();
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = Math.min(currentHp, maxHp);
        this.speed = speed;
        this.attacks = new ArrayList<>();
    }

    public int getSpeed() {
        return speed;
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

    public void addAttack(Attack attack) {
        if (attack == null) {
            throw new PokemonException("Attack can't be null");
        }
        if (attacks.size() >= 4) {
            throw new PokemonException("A Pokemon can have 4 attacks at most");
        }
        attacks.add(attack);
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new PokemonException("Damage can't be negative");
        }
        currentHp = Math.max(0, currentHp - amount);
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new PokemonException("Healing can't be negative");
        }
        currentHp = Math.min(maxHp, currentHp + amount);
    }

    public boolean isFainted() {
        return currentHp <= 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (").append(type.getLabel()).append(") | HP: ")
                .append(currentHp).append("/").append(maxHp)
                .append(" | Speed: ").append(speed).append(" | Attacks: ");

        for (int i = 0; i < attacks.size(); i++) {
            sb.append(attacks.get(i).getName());
            if (i < attacks.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}