package Pokedex;

import java.util.Random;

public class PokemonDamage extends Attack {
    private static final Random RANDOM = new Random();
    private static final double DAMAGE_SCALE = 0.4;
    private static final double MIN_RANDOM_FACTOR = 0.85;

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

    public static int calculateDamage(int power, double randomFactor) {
        int damage = (int) Math.round(power * DAMAGE_SCALE * randomFactor);
        return Math.max(1, damage);
    }

    @Override
    public void execute(Pokemon attacker, Pokemon defender) {
        double randomFactor = MIN_RANDOM_FACTOR + RANDOM.nextDouble() * (1.0 - MIN_RANDOM_FACTOR);
        defender.takeDamage(calculateDamage(power, randomFactor));
    }

    @Override
    public String toString() {
        return super.toString() + " | Power: " + power;
    }
}
