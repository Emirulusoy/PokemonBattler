package Pokedex;

import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PokemonService {
    public static class InvalidPokemonException extends RuntimeException {
        public InvalidPokemonException(String message) {
            super(message);
        }
    }

    public static void validateHp(int hp) {
        if (hp <= 0) {
            throw new InvalidPokemonException("HP måste vara > 0, fick: " + hp);
        }
    }

    public static void validateName(String name) {
        if (name.isBlank()) {
            throw new InvalidPokemonException("Inget namn");
        }
    }

    public static class PokemonNotFoundException extends RuntimeException {
        public PokemonNotFoundException(String message) {
            super(message);
        }
    }

    public static String find(String findName, String[] pokemonList) {
        for (String p : pokemonList) {
            if (p.equalsIgnoreCase(findName)) {
                return p;
            }
        }
        throw new PokemonNotFoundException("Pokemon hittades inte: " + findName);
    }

    public static int readIntInRange(Scanner scan, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine();

            try {
                int value = Integer.parseInt(input.trim());
                if (value < min || value > max) {
                    System.out.println("Ange ett tal mellan " + min + " och " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Det där är inte ett giltigt tal. Försök igen.");
            }
        }
    }

    //CASE 2 ADD POKÉMON
    public static void addPokemon(List<Pokemon> pokedex, Scanner scan) {
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
            return;
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


    //CASE 3 EDIT POKÉMON
    public static void editPokemon(List<Pokemon> pokedex, Scanner scan) {
        if (pokedex.isEmpty()) {
            System.out.println("Inga Pokémon att redigera. ");
            return;
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

        System.out.println("Ny typ (nuvarande: " + pokemon.getType() + "), Enter för att behålla: ");
        String newTypeInput = scan.nextLine();
        if (!newTypeInput.isBlank()) {
            try {
                Types newType = Types.valueOf(newTypeInput.trim().toUpperCase());
                pokemon.setType(newType);
            } catch (IllegalArgumentException e) {
                System.out.println("Ogiltig typ, behåller " + pokemon.getType());
            }
        }

        System.out.println("Vill du ändra attacker? (ja/nej): ");
        String editAttacks = scan.nextLine();
        if (editAttacks.trim().equalsIgnoreCase("ja")) {
            int attackChoice = PokemonService.readIntInRange(scan, "1. Lägg till attack  2. Ta bort attack  3. Ingen ändring: ", 1, 3);

            if (attackChoice == 1) {
                if (pokemon.getAttacks().size() >= 4) {
                    System.out.println("Redan 4 attacker. Vill du ersätta en? (ja/nej): ");
                    String replace = scan.nextLine();
                    if (replace.trim().equalsIgnoreCase("ja")) {
                        for (int i = 0; i < pokemon.getAttacks().size(); i++) {
                            System.out.println((i + 1) + ". " + pokemon.getAttacks().get(i).getName());
                        }
                        int removeIndex = PokemonService.readIntInRange(scan, "Vilken attack vill du ta bort? ", 1, pokemon.getAttacks().size());
                        pokemon.getAttacks().remove(removeIndex - 1);
                    } else {
                        System.out.println("Avbryter.");
                    }
                }

                if (pokemon.getAttacks().size() < 4) {
                    System.out.print("Namn: ");
                    String attackName = scan.nextLine();
                    int baseDamage = PokemonService.readIntInRange(scan, "Damage: ", 0, 300);
                    int accuracy = PokemonService.readIntInRange(scan, "Accuracy 0-100: ", 0, 100);

                    Types attackType = null;
                    while (attackType == null) {
                        System.out.print("Typ: ");
                        String attackTypeInput = scan.nextLine();
                        try {
                            attackType = Types.valueOf(attackTypeInput.trim().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ogiltig typ, försök igen.");
                        }
                    }
                    pokemon.getAttacks().add(new Attack(attackName, baseDamage, accuracy, attackType));
                    System.out.println("Attack tillagd.");
                }
            } else if (attackChoice == 2) {
                if (pokemon.getAttacks().isEmpty()) {
                    System.out.println("Inga attacker att ta bort.");
                } else {
                    for (int i = 0; i < pokemon.getAttacks().size(); i++) {
                        System.out.println((i + 1) + ". " + pokemon.getAttacks().get(i).getName());
                    }
                    int attackIndex = PokemonService.readIntInRange(scan, "Vilken attack vill du ta bort? ", 1, pokemon.getAttacks().size());
                    pokemon.getAttacks().remove(attackIndex - 1);
                    System.out.println("Attack borttagen.");
                }
            }
        }

        System.out.println("Uppdaterade " + pokemon.getName());
    }

    //CASE 4 DELETE POKÉMON
    public static void removePokemon(List<Pokemon> pokedex, Scanner scan) {
        if (pokedex.isEmpty()) {
            System.out.println("Inga Pokémon att ta bort.");
            return;
        }

        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i + 1) + ". " + pokedex.get(i).getName());
        }

        int index = PokemonService.readIntInRange(scan, "Vilken Pokémon vill du ta bort? ", 1, pokedex.size());
        Pokemon removed = pokedex.remove(index - 1);

        System.out.println(removed.getName() + " togs bort.");
    }

    //CASE 5 SAVE POKÉMON
    public static void savePokedex(List<Pokemon> pokedex, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Pokemon p : pokedex) {
                StringBuilder line = new StringBuilder();
                line.append(p.getName()).append(";")
                        .append(p.getType()).append(";")
                        .append(p.getMaxHp()).append(";")
                        .append(p.getCurrentHp()).append(";");

                for (Attack a : p.getAttacks()) {
                    line.append(a.getName()).append(",")
                            .append(a.getBaseDamage()).append(",")
                            .append(a.getAccuracy()).append(",")
                            .append(a.getType()).append("|");
                }

                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("Pokédex sparad till " + filename);
        } catch (IOException e) {
            System.out.println("Kunde inte spara filen: " + e.getMessage());
        }
    }
}

