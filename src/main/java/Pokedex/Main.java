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
                case 2 -> {
                    System.out.println("Lägg till: ");
                    System.out.println("Namn: ");
                    String name = scan.nextLine();
                    try {
                        PokemonService.validateName(name);
                    } catch (PokemonService.InvalidPokemonException e) {
                        System.out.println("Fel: " + e.getMessage());
                    }
                    System.out.println("Typ: NORMAL, FIRE, WATER, GRASS, ELECTRIC, DARK, ICE, FIGHTING, GROUND, ROCK, STEEL ");
                    String typeInput = scan.nextLine();
                    Types type;
                    try {
                        type = Types.valueOf(typeInput.trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Du måste skriva in en type.");
                        break;
                    }
                    int maxHp = PokemonService.readIntInRange(scan, "Max hp: ", 1, 999);

                    Pokemon newPokemon = new Pokemon(name, type, maxHp, maxHp);

                    int numAttacks = PokemonService.readIntInRange(scan, "Antal attacker 1-4: ", 1, 4);
                    for (int i = 1; i <= numAttacks; i++) {
                        System.out.println("Attack " + i + ":");
                        System.out.println("Namn: ");
                        String attackName = scan.nextLine();

                        int baseDamage = PokemonService.readIntInRange(scan, "Damage", 0, 300);
                        int accuracy = PokemonService.readIntInRange(scan, "Accuracy 0-100: ", 0, 100);

                        Types attackType = null;
                        while (attackType == null) {
                            System.out.println("Type");
                            String attacktypeInput = scan.nextLine();
                            try {
                                attackType = Types.valueOf(attacktypeInput.trim().toUpperCase());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Ogiltig typ, försök igen.");
                            }
                        }

                        newPokemon.getAttacks().add(new Attack(attackName, baseDamage, accuracy, attackType));
                    }

                    pokedex.add(newPokemon);
                    System.out.println("Lade till pokémon");
                }
                case 3 -> {
                    if (pokedex.isEmpty()) {
                        System.out.println("Inga Pokémon att redigera. ");
                        break;
                    }

                    for (int i = 0; i < pokedex.size(); i++) {
                        System.out.println((i + 1) + ". " + pokedex.get(i).getName());
                    }
                    int index = PokemonService.readIntInRange(scan, "Vilken Pokémon vill du redigera? ", 1, pokedex.size());
                    Pokemon pokemon = pokedex.get(index - 1);

                    System.out.println("Editing: " + pokemon.getName());
                    System.out.println("Nytt namn (Enter för att behålla \"" + pokemon.getName() + "\"): ");
                    String newName = scan.nextLine();
                    if (!newName.isBlank()) {
                        pokemon.setName(newName);
                    }
                    int newMaxHp = PokemonService.readIntInRange(scan, "Nytt max HP (nuvarande: " + pokemon.getMaxHp() + "): ", 1, 999);
                    pokemon.setMaxHp(newMaxHp);
                    pokemon.setCurrentHp(newMaxHp);

                    System.out.println("Uppdaterade " + pokemon.getName());
                }
                case 4 -> System.out.println("Ta bort: ");
                case 5 -> System.out.println("Spara: ");
                case 6 -> System.out.println("Ladda: ");
                case 7 -> System.out.println("Återställ: ");
                case 8 -> {
                    System.out.println("Sparar och avslutar...");
                    return;
                }
            }
        }
    }
}

