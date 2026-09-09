package PokeTest;

public class Main {
    public static void main(String[] args) {
        int hp = 150;
        String name = "pikachu";
        String[] mons = {"garchomp","pikachu","tyranitar","charizard"};

        try {
            MethodHelper.validateHp(hp);
            MethodHelper.validateName(name);
            System.out.println("Allt ok!");
        } catch (MethodHelper.InvalidPokemonException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        try {
            String found = MethodHelper.find(name, mons);
            System.out.println("Hittade: " + found);
        } catch (MethodHelper.PokemonNotFoundException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }
}
