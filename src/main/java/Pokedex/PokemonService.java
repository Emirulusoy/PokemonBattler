package Pokedex;

import java.util.Scanner;

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
}
