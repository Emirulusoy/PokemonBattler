package PokeTest;

public class MethodHelper {
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
            if(p.equalsIgnoreCase(findName)){
                return p;
            }
        }throw new PokemonNotFoundException("Pokemon hittades inte: " + findName);
    }
}
