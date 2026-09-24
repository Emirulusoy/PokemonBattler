package Pokedex;


import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Pokemon> pokedex = PokemonSeeder.seedData();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n===| Pokédex |===");
            System.out.println("1. Show all Pokémon");
            System.out.println("2. Add new Pokémon");
            System.out.println("3. Edit Pokémon");
            System.out.println("4. Delete Pokémon");
            System.out.println("5. Save to file");
            System.out.println("6. Upload from file");
            System.out.println("7. Reset to seeded data");
            System.out.println("8. Exit");

            int choice = PokemonService.readIntInRange(scan, "Choose: ", 1, 8);

            switch (choice) {
                case 1 -> {
                    for (Pokemon p : pokedex) {
                        System.out.println(p.getName() + " | " + p.getType() + " | HP: " + p.getCurrentHp() + "/" + p.getMaxHp());
                    }
                }
                case 2 -> PokemonService.addPokemon(pokedex, scan);
                case 3 -> PokemonService.editPokemon(pokedex, scan);
                case 4 -> PokemonService.removePokemon(pokedex, scan);
                case 5 -> PokemonService.savePokedex(pokedex, "pokedex.txt");
                case 6 -> {
                    List<Pokemon> loaded = PokemonService.loadPokedex("pokedex.txt");
                    if (!loaded.isEmpty()) {
                        pokedex.clear();
                        pokedex.addAll(loaded);
                    }
                }
                case 7 -> {
                    pokedex.clear();
                    pokedex.addAll(PokemonSeeder.seedData());
                    System.out.println("Reseted to seeded data.");
                }
                case 8 -> {
                    PokemonService.savePokedex(pokedex, "pokedex.txt");
                    System.out.println("| Saving and exiting |");
                    return;
                }
            }
        }
    }
}

