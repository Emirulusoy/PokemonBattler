package Pokedex;

import java.util.ArrayList;
import java.util.List;

public class PokemonSeeder {
    public static List<Pokemon> seedData() {
        List<Pokemon> pokedex = new ArrayList<>();

        Pokemon p1 = new Pokemon("Donphan", Types.GROUND, 90, 90);
        p1.getAttacks().add(new Attack("Rapid Spin", 50, 100, Types.NORMAL));
        p1.getAttacks().add(new Attack("Earthquake", 100, 100, Types.GROUND));
        p1.getAttacks().add(new Attack("Rock Slide", 75, 90, Types.ROCK));
        p1.getAttacks().add(new Attack("Iron Head", 80, 100, Types.STEEL));
        pokedex.add(p1);

        Pokemon p2 = new Pokemon("Politoed", Types.WATER, 90, 90);
        p2.getAttacks().add(new Attack("Hyper Voice", 90, 100, Types.NORMAL));
        p2.getAttacks().add(new Attack("Water Bun", 40, 100, Types.WATER));
        p2.getAttacks().add(new Attack("Surf", 90, 100, Types.WATER));
        p2.getAttacks().add(new Attack("Ice Beam", 90, 100, Types.ICE));
        pokedex.add(p2);


        Pokemon p3 = new Pokemon("Rapidash", Types.FIRE, 65, 65);
        p3.getAttacks().add(new Attack("Flame Wheel", 60, 100, Types.FIRE));
        p3.getAttacks().add(new Attack("Flare Blitz", 120, 100, Types.FIRE));
        p3.getAttacks().add(new Attack("Hidden Power", 60, 100, Types.NORMAL));
        p3.getAttacks().add(new Attack("SolarBeam", 120, 100, Types.GRASS));
        pokedex.add(p3);


        Pokemon p4 = new Pokemon("Machamp", Types.FIGHTING, 90, 90);
        p4.getAttacks().add(new Attack("Rock Smash", 40, 100, Types.FIGHTING));
        p4.getAttacks().add(new Attack("Close Combat", 120, 100, Types.FIGHTING));
        p4.getAttacks().add(new Attack("Ice Punch", 40, 100, Types.ICE));
        p4.getAttacks().add(new Attack("Brutal Swing", 60, 100, Types.DARK));
        pokedex.add(p4);


        Pokemon p5 = new Pokemon("Garganacl", Types.ROCK, 100, 100);
        p5.getAttacks().add(new Attack("Salt Cure", 40, 100, Types.ROCK));
        p5.getAttacks().add(new Attack("Giga Impact", 150, 90, Types.NORMAL));
        p5.getAttacks().add(new Attack("Rock Slide", 75, 90, Types.ROCK));
        p5.getAttacks().add(new Attack("Explosion", 250, 100, Types.NORMAL));
        pokedex.add(p5);


        Pokemon p6 = new Pokemon("Cetitan", Types.ICE, 170, 170);
        p6.getAttacks().add(new Attack("Ice Spinner", 80, 100, Types.ICE));
        p6.getAttacks().add(new Attack("Bodypress", 80, 100, Types.FIGHTING));
        p6.getAttacks().add(new Attack("Blizzard", 110, 70, Types.ICE));
        p6.getAttacks().add(new Attack("Facade", 70, 100, Types.NORMAL));
        pokedex.add(p6);

        return pokedex;
    }
}
