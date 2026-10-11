package Pokedex;

import java.util.ArrayList;
import java.util.List;

public class StrategyDemo {
    public static void main(String[] args) {
        List<Pokemon> pokedex = PokemonSeeder.seedData();
        Pokemon self = pokedex.get(0);
        Pokemon enemy = pokedex.get(1);

        run("Random", new CpuPlayer(new RandomStrategy()), self, enemy);
        run("Aggressive", new CpuPlayer(new AggressiveStrategy()), self, enemy);
    }

    private static void run(String label, CpuPlayer cpu, Pokemon self, Pokemon enemy) {
        System.out.println("--- " + label + " ---");
        List<BattleAction> options = new ArrayList<>(self.getAttacks());
        for (int i = 0; i < 6; i++) {
            BattleAction action = cpu.takeTurn(self, enemy, options);
            System.out.println(self.getName() + " Choosing " + action.getName());
        }
    }
}