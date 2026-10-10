package Pokedex;

import java.util.List;

public class CpuPlayer {
    private final CpuStrategy strategy;

    public CpuPlayer(CpuStrategy strategy) {
        if (strategy == null) {
            throw new PokemonException("CpuPlayer behöver en strategi");
        }
        this.strategy = strategy;
    }

    public BattleAction takeTurn(Pokemon self, Pokemon enemy, List<BattleAction> options) {
        if (options == null || options.isEmpty()) {
            throw new PokemonException(self.getName() + " har inga val att välja bland");
        }
        return strategy.chooseAction(self, enemy, options);
    }
}