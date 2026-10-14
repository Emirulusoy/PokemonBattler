package Pokedex;

public enum Types {
    FIRE("Fire"),
    WATER("Water"),
    ELECTRIC("Electric"),
    NORMAL("Normal"),
    GRASS("Grass"),
    GROUND("Ground"),
    ROCK("Rock"),
    STEEL("Steel"),
    ICE("Ice"),
    FIGHTING("Fighting"),
    DARK("Dark");

    private final String label;

    Types(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean isSpecial() {
        return this != NORMAL;
    }
}
