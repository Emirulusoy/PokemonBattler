package Pokedex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleEngine {
    public enum Outcome { WIN, LOSS, NOT_STARTED }

    private final Scanner scan;
    private final CpuPlayer cpu;
    private final Random random = new Random();

    public BattleEngine(Scanner scan, CpuPlayer cpu) {
        this.scan = scan;
        this.cpu = cpu;
    }

    public Outcome startBattle(List<Pokemon> pokedex) {
        if (pokedex.isEmpty()) {
            System.out.println("You don't own any Pokemon to battle with.");
            return Outcome.NOT_STARTED;
        }

        for (int i = 0; i < pokedex.size(); i++) {
            Pokemon p = pokedex.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " | HP: " + p.getCurrentHp() + "/" + p.getMaxHp());
        }
        int index = PokemonService.readIntInRange(scan, "Choose your Pokemon: ", 1, pokedex.size());
        Pokemon player = pokedex.get(index - 1);

        if (player.getAttacks().isEmpty()) {
            System.out.println(player.getName() + " has no attacks and can't battle.");
            return Outcome.NOT_STARTED;
        }
        if (player.isFainted()) {
            System.out.println(player.getName() + " has 0 HP and can't battle.");
            return Outcome.NOT_STARTED;
        }

        Pokemon wild = pickWildPokemon();
        System.out.println("\nA wild " + wild.getName() + " appeared!");
        System.out.println("Go, " + player.getName() + "!");

        boolean playerWon = runBattle(player, wild);
        player.heal(player.getMaxHp());

        if (playerWon) {
            System.out.println("\nYou won! " + player.getName() + " defeated the wild " + wild.getName() + ".");
            return Outcome.WIN;
        }
        System.out.println("\nYou lost... " + player.getName() + " was defeated by the wild " + wild.getName() + ".");
        return Outcome.LOSS;
    }

    private Pokemon pickWildPokemon() {
        List<Pokemon> wildPool = PokemonSeeder.seedData();
        return wildPool.get(random.nextInt(wildPool.size()));
    }

    private boolean runBattle(Pokemon player, Pokemon wild) {
        while (true) {
            BattleAction playerAction = choosePlayerAction(player);
            performTurn(player, wild, playerAction);
            if (wild.isFainted()) {
                return true;
            }

            List<BattleAction> options = new ArrayList<>(wild.getAttacks());
            BattleAction cpuAction = cpu.takeTurn(wild, player, options);
            performTurn(wild, player, cpuAction);
            if (player.isFainted()) {
                return false;
            }
        }
    }

    private BattleAction choosePlayerAction(Pokemon player) {
        System.out.println("\nYour turn! Choose an attack:");
        List<Attack> attacks = player.getAttacks();
        for (int i = 0; i < attacks.size(); i++) {
            System.out.println((i + 1) + ". " + attacks.get(i));
        }
        int choice = PokemonService.readIntInRange(scan, "Attack: ", 1, attacks.size());
        return attacks.get(choice - 1);
    }

    private void performTurn(Pokemon user, Pokemon target, BattleAction action) {
        System.out.println("\n" + user.getName() + " uses " + action.getName() + "!");
        if (!action.rollHit()) {
            System.out.println("It missed!");
            return;
        }
        int hpBefore = target.getCurrentHp();
        action.execute(user, target);
        int damage = hpBefore - target.getCurrentHp();
        System.out.println(target.getName() + " took " + damage + " damage. ("
                + target.getCurrentHp() + "/" + target.getMaxHp() + " HP)");
        if (target.isFainted()) {
            System.out.println(target.getName() + " fainted!");
        }
    }
}
