package Pokedex;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class PokemonService {
    public static class InvalidPokemonException extends RuntimeException {
        public InvalidPokemonException(String message) {
            super(message);
        }
    }

    public static void validateHp(int hp) {
        if (hp <= 0) {
            throw new InvalidPokemonException("HP has to be over 0, got: " + hp);
        }
    }

    public static void validateName(String name) {
        if (name.isBlank()) {
            throw new InvalidPokemonException("No name");
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
        throw new PokemonNotFoundException("Pokémon doesn't exist: " + findName);
    }

    public static int readIntInRange(Scanner scan, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine();

            try {
                int value = Integer.parseInt(input.trim());
                if (value < min || value > max) {
                    System.out.println("Say a number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("That isn't a valid number. Try again.");
            }
        }
    }

    //CASE 2 ADD POKÉMON
    public static void addPokemon(List<Pokemon> pokedex, Scanner scan) {
        System.out.println("Add: ");
        System.out.println("Name: ");
        String name = scan.nextLine();
        try {
            PokemonService.validateName(name);
        } catch (PokemonService.InvalidPokemonException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        System.out.println("Type: NORMAL, FIRE, WATER, GRASS, ELECTRIC, DARK, ICE, FIGHTING, GROUND, ROCK, STEEL ");
        String typeInput = scan.nextLine();
        Types type;
        try {
            type = Types.valueOf(typeInput.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("You have to add a valid type.");
            return;
        }
        int maxHp = PokemonService.readIntInRange(scan, "Max hp: ", 1, 999);

        Pokemon newPokemon = new Pokemon(name, type, maxHp, maxHp);

        int numAttacks = PokemonService.readIntInRange(scan, "Amount of attacks 1-4: ", 1, 4);
        for (int i = 1; i <= numAttacks; i++) {
            System.out.println("Attack " + i + ":");
            System.out.println("Name: ");
            String attackName = scan.nextLine();

            int baseDamage = PokemonService.readIntInRange(scan, "Damage: ", 0, 300);
            int accuracy = PokemonService.readIntInRange(scan, "Accuracy 0-100: ", 0, 100);

            Types attackType = null;
            while (attackType == null) {
                System.out.println("Type");
                String attacktypeInput = scan.nextLine();
                try {
                    attackType = Types.valueOf(attacktypeInput.trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid type, try again.");

                }
            }

            newPokemon.getAttacks().add(new Attack(attackName, baseDamage, accuracy, attackType));
        }

        pokedex.add(newPokemon);
        System.out.println("Added Pokémon");
    }


    //CASE 3 EDIT POKÉMON
    public static void editPokemon(List<Pokemon> pokedex, Scanner scan) {
        if (pokedex.isEmpty()) {
            System.out.println("No Pokémon to edit. ");
            return;
        }

        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i + 1) + ". " + pokedex.get(i).getName());
        }
        int index = PokemonService.readIntInRange(scan, "Which Pokémon do you want to edit? ", 1, pokedex.size());
        Pokemon pokemon = pokedex.get(index - 1);

        System.out.println("Editing: " + pokemon.getName());
        System.out.println("New name (press Enter to keep previous name \"" + pokemon.getName() + "\"): ");
        String newName = scan.nextLine();
        if (!newName.isBlank()) {
            pokemon.setName(newName);
        }
        int newMaxHp = PokemonService.readIntInRange(scan, "New max HP (n: " + pokemon.getMaxHp() + "): ", 1, 999);
        pokemon.setMaxHp(newMaxHp);
        pokemon.setCurrentHp(newMaxHp);

        System.out.println("Ny typ (nuvarande: " + pokemon.getType() + "), Press enter to keep: ");
        String newTypeInput = scan.nextLine();
        if (!newTypeInput.isBlank()) {
            try {
                Types newType = Types.valueOf(newTypeInput.trim().toUpperCase());
                pokemon.setType(newType);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid typ, keeping " + pokemon.getType());
            }
        }

        System.out.println("Do you wish to change attacks? (y/n): ");
        String editAttacks = scan.nextLine();
        if (editAttacks.trim().equalsIgnoreCase("y")) {
            int attackChoice = PokemonService.readIntInRange(scan, "1. Add attack.  2. Remove attack.  3. No change. ", 1, 3);

            if (attackChoice == 1) {
                if (pokemon.getAttacks().size() >= 4) {
                    System.out.println("Already 4 attacks. Do you wish to replace one? (y/n): ");
                    String replace = scan.nextLine();
                    if (replace.trim().equalsIgnoreCase("y")) {
                        for (int i = 0; i < pokemon.getAttacks().size(); i++) {
                            System.out.println((i + 1) + ". " + pokemon.getAttacks().get(i).getName());
                        }
                        int removeIndex = PokemonService.readIntInRange(scan, "Which attack will you remove? ", 1, pokemon.getAttacks().size());
                        pokemon.getAttacks().remove(removeIndex - 1);
                    } else {
                        System.out.println("Cancelling");
                    }
                }

                if (pokemon.getAttacks().size() < 4) {
                    System.out.print("Name: ");
                    String attackName = scan.nextLine();
                    int baseDamage = PokemonService.readIntInRange(scan, "Damage: ", 0, 300);
                    int accuracy = PokemonService.readIntInRange(scan, "Accuracy 0-100: ", 0, 100);

                    Types attackType = null;
                    while (attackType == null) {
                        System.out.print("Type: ");
                        String attackTypeInput = scan.nextLine();
                        try {
                            attackType = Types.valueOf(attackTypeInput.trim().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid type, type again.");
                        }
                    }
                    pokemon.getAttacks().add(new Attack(attackName, baseDamage, accuracy, attackType));
                    System.out.println("Attack added.");
                }
            } else if (attackChoice == 2) {
                if (pokemon.getAttacks().isEmpty()) {
                    System.out.println("No attacker to remove.");
                } else {
                    for (int i = 0; i < pokemon.getAttacks().size(); i++) {
                        System.out.println((i + 1) + ". " + pokemon.getAttacks().get(i).getName());
                    }
                    int attackIndex = PokemonService.readIntInRange(scan, "Which attack will you remove? ", 1, pokemon.getAttacks().size());
                    pokemon.getAttacks().remove(attackIndex - 1);
                    System.out.println("Attack deleted.");
                }
            }
        }

        System.out.println("Updated " + pokemon.getName());
    }

    //CASE 4 DELETE POKÉMON
    public static void removePokemon(List<Pokemon> pokedex, Scanner scan) {
        if (pokedex.isEmpty()) {
            System.out.println("No Pokémon to delete");
            return;
        }

        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i + 1) + ". " + pokedex.get(i).getName());
        }

        int index = PokemonService.readIntInRange(scan, "Which Pokémon till you remove? ", 1, pokedex.size());
        Pokemon removed = pokedex.remove(index - 1);

        System.out.println(removed.getName() + " Deleted.");
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
            System.out.println("Pokédex saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Couldn't save file: " + e.getMessage());
        }



    }
    //CASE 6
    public static List<Pokemon> loadPokedex(String filename){
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("No save file found.");

            return new ArrayList<>();
        }
        List <Pokemon> pokedex = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(";");
                String name = parts[0];
                Types type = Types.valueOf(parts[1]);
                int maxHp = Integer.parseInt(parts[2]);
                int currentHp = Integer.parseInt(parts[3]);
                Pokemon p = new Pokemon(name, type, maxHp, currentHp);
                if (parts.length > 4 && !parts[4].isBlank()) {
                    String[] attackParts = parts[4].split("\\|");

                    for (String attackStr : attackParts) {
                        String[] a = attackStr.split(",");
                        String attackName = a[0];
                        int baseDamage = Integer.parseInt(a[1]);
                        int accuracy = Integer.parseInt(a[2]);
                        Types attackType = Types.valueOf(a[3]);

                        p.getAttacks().add(new Attack(attackName, baseDamage, accuracy, attackType));
                    }
                }

                pokedex.add(p);

            }
            System.out.println("Added pokémon");
        } catch (IOException e) {
            System.out.println("Couldn't read the Pokémon. " + e.getMessage());
        }
        return pokedex;
    }
}

