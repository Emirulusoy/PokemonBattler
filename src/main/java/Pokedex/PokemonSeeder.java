package Pokedex;

import java.util.ArrayList;
import java.util.List;

public class PokemonSeeder {
    public static List<Pokemon> seedData() {
        List<Pokemon> pokedex = new ArrayList<>();

        Pokemon p1 = new Pokemon("Donphan", Types.GROUND, 90, 90, 50);
        p1.addAttack(new PokemonDamage("Rapid Spin", 50, 100, Types.NORMAL));
        p1.addAttack(new PokemonDamage("Earthquake", 100, 100, Types.GROUND));
        p1.addAttack(new PokemonDamage("Rock Slide", 75, 90, Types.ROCK));
        p1.addAttack(new PokemonDamage("Iron Head", 80, 100, Types.STEEL));
        pokedex.add(p1);

        Pokemon p2 = new Pokemon("Politoed", Types.WATER, 90, 90, 70);
        p2.addAttack(new PokemonDamage("Hyper Voice", 90, 100, Types.NORMAL));
        p2.addAttack(new PokemonDamage("Water Bun", 40, 100, Types.WATER));
        p2.addAttack(new PokemonDamage("Surf", 90, 100, Types.WATER));
        p2.addAttack(new PokemonDamage("Ice Beam", 90, 100, Types.ICE));
        pokedex.add(p2);


        Pokemon p3 = new Pokemon("Rapidash", Types.FIRE, 65, 65, 105);
        p3.addAttack(new PokemonDamage("Flame Wheel", 60, 100, Types.FIRE));
        p3.addAttack(new PokemonDamage("Flare Blitz", 120, 100, Types.FIRE));
        p3.addAttack(new PokemonDamage("Hidden Power", 60, 100, Types.NORMAL));
        p3.addAttack(new PokemonDamage("SolarBeam", 120, 100, Types.GRASS));
        pokedex.add(p3);


        Pokemon p4 = new Pokemon("Machamp", Types.FIGHTING, 90, 90, 55);
        p4.addAttack(new PokemonDamage("Rock Smash", 40, 100, Types.FIGHTING));
        p4.addAttack(new PokemonDamage("Close Combat", 120, 100, Types.FIGHTING));
        p4.addAttack(new PokemonDamage("Ice Punch", 40, 100, Types.ICE));
        p4.addAttack(new PokemonDamage("Brutal Swing", 60, 100, Types.DARK));
        pokedex.add(p4);


        Pokemon p5 = new Pokemon("Garganacl", Types.ROCK, 100, 100, 35);
        p5.addAttack(new PokemonDamage("Salt Cure", 40, 100, Types.ROCK));
        p5.addAttack(new PokemonDamage("Giga Impact", 150, 90, Types.NORMAL));
        p5.addAttack(new PokemonDamage("Rock Slide", 75, 90, Types.ROCK));
        p5.addAttack(new PokemonDamage("Explosion", 250, 100, Types.NORMAL));
        pokedex.add(p5);


        Pokemon p6 = new Pokemon("Cetitan", Types.ICE, 170, 170, 45);
        p6.addAttack(new PokemonDamage("Ice Spinner", 80, 100, Types.ICE));
        p6.addAttack(new PokemonDamage("Bodypress", 80, 100, Types.FIGHTING));
        p6.addAttack(new PokemonDamage("Blizzard", 110, 70, Types.ICE));
        p6.addAttack(new PokemonDamage("Facade", 70, 100, Types.NORMAL));
        pokedex.add(p6);

        return pokedex;
    }
}
