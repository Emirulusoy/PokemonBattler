package Pokedex;

import java.util.List;
import java.util.Random;

public interface CpuStrategy {
    BattleAction chooseAction(Pokemon self, Pokemon enemy, List<BattleAction> options);
}

class RandomStrategy implements CpuStrategy {
    private final Random random = new Random();

    @Override
    public BattleAction chooseAction(Pokemon self, Pokemon enemy, List<BattleAction> options) {
        if (options.isEmpty()) {
            throw new PokemonException("Inga val att välja bland");
        }
        return options.get(random.nextInt(options.size()));
    }
}

class AggressiveStrategy implements CpuStrategy {

    @Override
    public BattleAction chooseAction(Pokemon self, Pokemon enemy, List<BattleAction> options) {
        if (options.isEmpty()) {
            throw new PokemonException("Inga val att välja bland");
        }

        PokemonDamage best = null;
        for (BattleAction action : options) {
            if (action instanceof PokemonDamage damage) {
                if (best == null || damage.getPower() > best.getPower()) {
                    best = damage;
                }
            }
        }

        if (best != null) {
            return best;
        }
        return options.get(0);
    }
}