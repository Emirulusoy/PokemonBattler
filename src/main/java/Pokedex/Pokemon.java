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
        setName(name);
        setType(type);
        setMaxHp(maxHp);
        setCurrentHp(currentHp);
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
        if (name == null || name.isBlank()) {
            throw new PokemonException("Namn får inte vara tomt");
        }
        this.name = name.trim();
    }

    public void setType(Types type) {
        if (type == null) {
            throw new PokemonException("Typ måste anges");
        }
        this.type = type;
    }

    public void setMaxHp(int maxHp) {
        if (maxHp <= 0) {
            throw new PokemonException("Max-HP måste vara > 0");
        }
        this.maxHp = maxHp;
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp < 0) {
            throw new PokemonException("Nuvarande HP kan inte vara negativt");
        }
        this.currentHp = currentHp;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (").append(type).append(") | HP: ")
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
