package Pokedex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BattleStats {
    private int wins;
    private int losses;

    public BattleStats(int wins, int losses) {
        if (wins < 0 || losses < 0) {
            throw new PokemonException("Wins and losses can't be negative");
        }
        this.wins = wins;
        this.losses = losses;
    }

    public void recordWin() {
        wins++;
    }

    public void recordLoss() {
        losses++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTotal() {
        return wins + losses;
    }

    public void printSummary() {
        if (getTotal() == 0) {
            System.out.println("No battles played yet.");
            return;
        }
        System.out.println("Battles played: " + getTotal());
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
    }

    public void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(wins + ";" + losses);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Couldn't save battle results: " + e.getMessage());
        }
    }

    public static BattleStats load(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return new BattleStats(0, 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null || line.isBlank()) {
                return new BattleStats(0, 0);
            }
            String[] parts = line.trim().split(";");
            if (parts.length != 2) {
                throw new PokemonException("Expected two numbers, got: " + line);
            }
            int wins = Integer.parseInt(parts[0]);
            int losses = Integer.parseInt(parts[1]);
            return new BattleStats(wins, losses);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Couldn't read battle results (" + e.getMessage() + "), starting from 0.");
            return new BattleStats(0, 0);
        }
    }
}
