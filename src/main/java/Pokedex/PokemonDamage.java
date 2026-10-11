package Pokedex;

import java.util.Random;

public class PokemonDamage extends Attack {
    private static final Random RANDOM = new Random();
    private static final double DAMAGE_SCALE = 0.4;

    private final int power;

    public PokemonDamage(String name, int power, int accuracy, Types type) {
        super(name, accuracy, type);
        if (power <= 0) {
            throw new PokemonException("Power must be over 0, got: " + power);
        }
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    @Override
    public void execute(Pokemon attacker, Pokemon defender) {
        double randomFactor = 0.85 + RANDOM.nextDouble() * 0.15;
        int damage = (int) Math.round(power * DAMAGE_SCALE * randomFactor);
        defender.takeDamage(Math.max(1, damage));
    }

    @Override
    public String toString() {
        return super.toString() + " | Power: " + power;
    }
}
