package Pokedex;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File saveFile = new File("pokedex.txt");
        List<Pokemon> pokedex;
        if (saveFile.exists()) {
            pokedex = PokemonService.loadPokedex("pokedex.txt");
        } else {
            pokedex = PokemonSeeder.seedData();
        }
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n===| Pokedex |===");
            System.out.println("1. Show all Pokemon");
            System.out.println("2. Add new Pokemon");
            System.out.println("3. Edit Pokemon");
            System.out.println("4. Delete Pokemon");
            System.out.println("5. Save to file");
            System.out.println("6. Upload from file");
            System.out.println("7. Reset to seeded data");
            System.out.println("8. Exit and save");

            int choice = PokemonService.readIntInRange(scan, "Choose: ", 1, 8);

            switch (choice) {
                //CASE 1 SHOW ALL POKÉMON
                case 1 -> {
                    if (pokedex.isEmpty()) {
                        System.out.println("No Pokemon to show.");
                    } else {
                        for (Pokemon p : pokedex) {
                            System.out.println(p.getName() + " | " + p.getType() + " | HP: " + p.getCurrentHp() + "/" + p.getMaxHp());
                        }
                    }
                }
                //CASE 2 ADD POKÉMON
                case 2 -> PokemonService.addPokemon(pokedex, scan);
                //CASE 3 EDIT POKÉMON
                case 3 -> PokemonService.editPokemon(pokedex, scan);
                //CASE 4 DELETE POKÉMON
                case 4 -> PokemonService.removePokemon(pokedex, scan);
                //CASE 5 SAVE TO FILE
                case 5 -> PokemonService.savePokedex(pokedex, "pokedex.txt");
                //CASE 6 LOAD FROM FILE
                case 6 -> {
                    List<Pokemon> loaded = PokemonService.loadPokedex("pokedex.txt");
                    if (!loaded.isEmpty()) {
                        pokedex.clear();
                        pokedex.addAll(loaded);
                    }
                }
                //CASE 7 RESET TO SEEDED DATA
                case 7 -> {
                    pokedex.clear();
                    pokedex.addAll(PokemonSeeder.seedData());
                    PokemonService.savePokedex(pokedex, "pokedex.txt");
                    System.out.println("Reseted to seeded data.");
                }
                //CASE 8 EXIT AND SAVE
                case 8 -> {
                    PokemonService.savePokedex(pokedex, "pokedex.txt");
                    System.out.println("| Saving and exiting |");
                    return;
                }
            }
        }
    }
}