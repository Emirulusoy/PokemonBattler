package Pokedex;

public abstract class Attack {
    private String name;
    private int accuracy;
    private Types type;

    public Attack(String name, int accuracy, Types type) {
        setName(name);
        setAccuracy(accuracy);
        setType(type);
    }

    public abstract void execute(Pokemon attacker, Pokemon defender);

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