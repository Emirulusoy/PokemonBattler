package Pokedex;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Pokemon> pokedex = PokemonSeeder.seedData();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Pokédex ---");
            System.out.println("1. Visa alla Pokémons");
            System.out.println("2. Lägg till ny Pokémon");
            System.out.println("3. Redigera Pokémon");
            System.out.println("4. Ta bort Pokémon");
            System.out.println("5. Spara till fil");
            System.out.println("6. Ladda från fil");
            System.out.println("7. Återställ till seedad data");
            System.out.println("8. Avsluta");

            int choice = PokemonService.readIntInRange(scan, "Val: ", 1, 8);

            switch (choice) {
                case 1 -> {
                    for (Pokemon p : pokedex) {
                        System.out.println(p.getName() + " | " + p.getType() + " | HP: " + p.getCurrentHp() + "/" + p.getMaxHp());
                    }
                }
                case 2 -> System.out.println("Du valde: Lägg till");
                case 3 -> System.out.println("Du valde: Redigera");
                case 4 -> System.out.println("Du valde: Ta bort");
                case 5 -> System.out.println("Du valde: Spara");
                case 6 -> System.out.println("Du valde: Ladda");
                case 7 -> System.out.println("Du valde: Återställ");
                case 8 -> {
                    System.out.println("Sparar och avslutar...");
                    return;
                }
            }
        }
    }
}