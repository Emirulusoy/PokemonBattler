package Pokedex;

import java.util.Random;

public abstract class Attack implements BattleAction {
    private static final Random RANDOM = new Random();

    private String name;
    private int accuracy;
    private Types type;

    public Attack(String name, int accuracy, Types type) {
        setName(name);
        setAccuracy(accuracy);
        setType(type);
    }

    public abstract void execute(Pokemon attacker, Pokemon defender);

    @Override
    public boolean rollHit() {
        return RANDOM.nextInt(100) < accuracy;
    }

    @Override
    public double getEffectiveness(Pokemon target) {
        return TypeChart.getMultiplier(type, target.getType());
    }

    public String getName() {
        return name;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Types getType() {
        return type;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new PokemonException("Attack name can't be empty");
        }
        this.name = name.trim();
    }

    public void setAccuracy(int accuracy) {
        if (accuracy < 0 || accuracy > 100) {
            throw new PokemonException("Accuracy must be between 0 and 100, got: " + accuracy);
        }
        this.accuracy = accuracy;
    }

    public void setType(Types type) {
        if (type == null) {
            throw new PokemonException("Attack type can't be null");
        }
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " (" + type.getLabel() + ") | Accuracy: " + accuracy;
    }
}
