package Pokedex;

import java.util.HashMap;
import java.util.Map;

public class TypeChart {
    private static final Map<Types, Map<Types, Double>> chart = new HashMap<>();

    static {
        add(Types.FIRE, Types.GRASS, 2.0);
        add(Types.WATER, Types.FIRE, 2.0);
        add(Types.GRASS, Types.WATER, 2.0);
        add(Types.ELECTRIC, Types.WATER, 2.0);
        add(Types.FIRE, Types.WATER, 0.5);
        add(Types.WATER, Types.GRASS, 0.5);
        add(Types.GRASS, Types.FIRE, 0.5);
    }

    private static void add(Types attacker, Types defender, double multiplier) {
        if (!chart.containsKey(attacker)) {
            chart.put(attacker, new HashMap<>());
        }
        chart.get(attacker).put(defender, multiplier);
    }

    public static double getMultiplier(Types attacker, Types defender) {
        Map<Types, Double> row = chart.get(attacker);
        if (row == null || !row.containsKey(defender)) {
            return 1.0;
        }
        return row.get(defender);
    }
}
