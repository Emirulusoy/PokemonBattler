package Pokedex;

public enum Types {
    FIRE("Eld"),
    WATER("Vatten"),
    ELECTRIC("Elektrisk"),
    NORMAL("Normal"),
    GRASS("Gräs"),
    GROUND("Mark"),
    ROCK("Sten"),
    STEEL("Stål"),
    ICE("Is"),
    FIGHTING("Kamp"),
    DARK("Mörker");

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
