package Pokedex;

public class PokemonDamage extends Attack {
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
        defender.takeDamage(power);
    }

    @Override
    public String toString() {
        return super.toString() + " | Power: " + power;
    }
}
