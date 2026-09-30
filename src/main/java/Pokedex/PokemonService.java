package Pokedex;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class PokemonService {

    public static void validateHp(int hp) {
        if (hp <= 0) {
            throw new PokemonException("HP has to be over 0, got: " + hp);
        }
    }

    public static void validateName(String name) {
        if (name.isBlank()) {
            throw new PokemonException("No name");
        }
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            boolean isLetter = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
            boolean isSpace = c == ' ';
            if (!isLetter && !isSpace) {
                throw new PokemonException("Name can only contain letters");
            }
        }
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

    private static String readValidAttackName(Scanner scan) {
        while (true) {
            System.out.println("Name: ");
            String attackName = scan.nextLine();
            try {
                validateName(attackName);
                return attackName;
            } catch (PokemonException e) {
                System.out.println("Invalid attack name: " + e.getMessage() + ". Try again.");
            }
        }
    }

    private static boolean readYesNo(Scanner scan, String prompt) {
        while (true) {
            System.out.println(prompt);
            String answer = scan.nextLine().trim();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Please answer y or n.");
        }
    }

    //CASE 2 ADD POKÉMON
    public static void addPokemon(List<Pokemon> pokedex, Scanner scan) {
        System.out.println("| Adding Pokemon |");
        System.out.println("Name: ");
        String name = scan.nextLine();
        try {
            PokemonService.validateName(name);
        } catch (PokemonException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        System.out.println("Type: NORMAL, FIRE, WATER, GRASS, ELECTRIC, DARK, ICE, FIGHTING, GROUND, ROCK, STEEL ");
        Types type = null;
        while (type == null) {
            String typeInput = scan.nextLine();
            try {
                type = Types.valueOf(typeInput.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type, try again.");
            }
        }
        int maxHp = PokemonService.readIntInRange(scan, "Max hp: ", 1, 999);
        PokemonService.validateHp(maxHp);

        Pokemon newPokemon = new Pokemon(name, type, maxHp, maxHp);

        int numAttacks = PokemonService.readIntInRange(scan, "Amount of attacks 1-4: ", 1, 4);
        for (int i = 1; i <= numAttacks; i++) {
            System.out.println("Attack " + i + ":");
            String attackName = readValidAttackName(scan);

            int baseDamage = PokemonService.readIntInRange(scan, "Damage: ", 1, 300);
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
        System.out.println("Added Pokemon\n Dont forget to save to file!");
    }


    //CASE 3 EDIT POKÉMON
    public static void editPokemon(List<Pokemon> pokedex, Scanner scan) {
        if (pokedex.isEmpty()) {
            System.out.println("No Pokemon to edit. ");
            return;
        }

        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i + 1) + ". " + pokedex.get(i).getName());
        }
        int index = PokemonService.readIntInRange(scan, "Which Pokemon do you want to edit? ", 1, pokedex.size());
        Pokemon pokemon = pokedex.get(index - 1);

        System.out.println("Editing: " + pokemon.getName());

        String finalName = pokemon.getName();
        System.out.println("New name (press Enter to keep previous name \"" + pokemon.getName() + "\"): ");
        String newName = scan.nextLine();
        if (!newName.isBlank()) {
            try {
                PokemonService.validateName(newName);
                finalName = newName;
            } catch (PokemonException e) {
                System.out.println("Invalid name (" + e.getMessage() + "), keeping \"" + pokemon.getName() + "\"");
            }
        }

        int finalMaxHp = PokemonService.readIntInRange(scan, "New max HP (current HP: " + pokemon.getMaxHp() + "): ", 1, 999);

        Types finalType = pokemon.getType();
        System.out.println("New type (current type: " + pokemon.getType() + "), Press enter to keep: ");
        String newTypeInput = scan.nextLine();
        if (!newTypeInput.isBlank()) {
            try {
                finalType = Types.valueOf(newTypeInput.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type, keeping " + pokemon.getType());
            }
        }

        Pokemon updated = new Pokemon(finalName, finalType, finalMaxHp, finalMaxHp);
        for (Attack a : pokemon.getAttacks()) {
            updated.addAttack(a);
        }

        boolean wantsToEditAttacks = readYesNo(scan, "Do you wish to change attacks? (y/n): ");
        if (wantsToEditAttacks) {
            int attackChoice = PokemonService.readIntInRange(scan, "1. Add attack.  2. Remove attack.  3. No change. ", 1, 3);

            if (attackChoice == 1) {
                if (updated.getAttacks().size() >= 4) {
                    boolean wantsToReplace = readYesNo(scan, "Already 4 attacks. Do you wish to replace one? (y/n): ");
                    if (wantsToReplace) {
                        for (int i = 0; i < updated.getAttacks().size(); i++) {
                            System.out.println((i + 1) + ". " + updated.getAttacks().get(i).getName());
                        }
                        int removeIndex = PokemonService.readIntInRange(scan, "Which attack will you remove? ", 1, updated.getAttacks().size());
                        updated.getAttacks().remove(removeIndex - 1);
                    } else {
                        System.out.println("Cancelling");
                    }
                }

                if (updated.getAttacks().size() < 4) {
                    String attackName = readValidAttackName(scan);
                    int baseDamage = PokemonService.readIntInRange(scan, "Damage: ", 1, 300);
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
                    updated.addAttack(new Attack(attackName, baseDamage, accuracy, attackType));
                    System.out.println("Attack added.");
                }
            } else if (attackChoice == 2) {
                if (updated.getAttacks().isEmpty()) {
                    System.out.println("No attacker to remove.");
                } else if (updated.getAttacks().size() == 1) {
                    System.out.println("Can't remove the last attack - a Pokemon needs at least 1.");
                } else {
                    for (int i = 0; i < updated.getAttacks().size(); i++) {
                        System.out.println((i + 1) + ". " + updated.getAttacks().get(i).getName());
                    }
                    int attackIndex = PokemonService.readIntInRange(scan, "Which attack will you remove? ", 1, updated.getAttacks().size());
                    updated.getAttacks().remove(attackIndex - 1);
                    System.out.println("Attack deleted.");
                }
            }
        }

        pokedex.set(index - 1, updated);
        System.out.println("Updated " + updated.getName());
    }

    //CASE 4 DELETE POKÉMON
    public static void removePokemon(List<Pokemon> pokedex, Scanner scan) {
        if (pokedex.isEmpty()) {
            System.out.println("No Pokemon to delete");
            return;
        }

        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i + 1) + ". " + pokedex.get(i).getName());
        }

        int index = PokemonService.readIntInRange(scan, "Which Pokemon till you remove? ", 1, pokedex.size());
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
            System.out.println("Pokedex saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Couldn't save file: " + e.getMessage());
        }
    }

    //CASE 6
    public static List<Pokemon> loadPokedex(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("No save file found.");
            return new ArrayList<>();
        }
        List<Pokemon> pokedex = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }
                try {
                    String[] parts = line.split(";", -1);
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
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Skipping corrupted line " + lineNumber + " in save file: " + e.getMessage());
                }
            }
            System.out.println("Loaded " + pokedex.size() + " Pokemon.");
        } catch (IOException e) {
            System.out.println("Couldn't read the Pokemon. " + e.getMessage());
        }
        return pokedex;
    }
}