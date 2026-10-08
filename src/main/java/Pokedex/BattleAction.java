package Pokedex;

public interface BattleAction {
    String getName();

    void execute(Pokemon self, Pokemon enemy);
}