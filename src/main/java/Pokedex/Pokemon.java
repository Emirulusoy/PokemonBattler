package Pokedex;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private final String name;
    private final Types type;
    private final int maxHp;
    private int currentHp;
    private final List<Attack> attacks;

    public Pokemon(String name, Types type, int maxHp, int currentHp) {
        if (name == null || name.isBlank()) {
            throw new PokemonException("Namn får inte vara tomt");
        }
        if (type == null) {
            throw new PokemonException("Typ måste anges");
        }
        if (maxHp <= 0) {
            throw new PokemonException("Max-HP måste vara > 0");
        }
        if (currentHp < 0) {
            throw new PokemonException("Nuvarande HP kan inte vara negativt");
        }

        this.name = name.trim();
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = Math.min(currentHp, maxHp);
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

    public void addAttack(Attack attack) {
        if (attack == null) {
            throw new PokemonException("Attack kan inte vara null");
        }
        if (attacks.size() >= 4) {
            throw new PokemonException("En Pokemon kan max ha 4 attacker");
        }
        attacks.add(attack);
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new PokemonException("Skada kan inte vara negativ");
        }
        currentHp = Math.max(0, currentHp - amount);
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new PokemonException("Healing kan inte vara negativ");
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
                .append(currentHp).append("/").append(maxHp).append(" | Attacks: ");

        for (int i = 0; i < attacks.size(); i++) {
            sb.append(attacks.get(i).getName());
            if (i < attacks.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}