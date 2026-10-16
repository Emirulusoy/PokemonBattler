# Pokédex

A Java console program for managing a collection of Pokémon,
with save/load to file, editing, deletion, seeded starter data and turn-based battles.

Hey! I'm glad you're using my Pokémon battler. I hope you'll enjoy battling and winning against the wild Pokémon!

## How to run

Open the project in IntelliJ and run `Main.java`, or from the terminal run:

```
mvn compile exec:java
```

## Menu

1. **Show all Pokémon** - shows all Pokémon in the program.
2. **Add new Pokémon** - choose name, type, HP, speed and 1-4 attacks.
3. **Edit Pokémon** - change name, type, HP, speed and attacks.
4. **Delete Pokémon** - deletes a Pokémon of your choosing.
5. **Save to file** - saves your Pokédex to `pokedex.txt`.
6. **Upload from file** - loads the Pokédex from `pokedex.txt`.
7. **Reset to seeded data** - restores the default Pokémon.
8. **Start battle** - see below.
9. **Battle results** - shows how many battles you have played, won and lost. Saved in `battlestats.txt`.
10. **Exit and save** - saves your Pokédex and battle results and exits.

## Battles

Choose one of your Pokémon and fight a random wild Pokémon from the seeded pool.

- **Turn order:** the Pokémon with the highest speed attacks first. If the speed is the same then one its random.
- **Your turn:** you choose an attack from a menu each round.
- **CPU turn:** the computer chooses one of the wild Pokémon's attacks at random.
- **Accuracy:** an attack hits if a random number 0-99 is lower than its accuracy.
- **Type effectiveness:** the attack's type is compared with the defending Pokémon's type. The log prints super effective when the move is is effective, or not very effective when the move isn't very effective.
- **End of battle:** the battle ends when one Pokémon has 0 HP. The result is shown and saved as wins/losses.
- Your Pokémon's HP is always restored after a battle.

## Damage formula

```
damage = max(1, round(power * 0.4 * effectiveness * randomFactor))
```

- `power` is the attack's base power.
- `effectiveness` comes from the type chart (see below).
- `randomFactor` is a random number between 0.85 and 1.00.

The formula is in `PokemonDamage.calculateDamage`.

## Type chart

Defined in `TypeChart`. Every attacker/defender pair that is not listed is 1x.

| Attacking | Defending | Effect |
|-----------|-----------|--------|
| Fire      | Grass     | 2x     |
| Water     | Fire      | 2x     |
| Grass     | Water     | 2x     |
| Electric  | Water     | 2x     |
| Fire      | Water     | 0.5x   |
| Water     | Grass     | 0.5x   |
| Grass     | Fire      | 0.5x   |
| Normal    | (all)     | 1x     |

## OOP

- `BattleAction` is an interface for anything that can be used in a battle.
  `BattleEngine` only knows about this interface, so I could add new kinds of
  actions later without touching the engine.
- `Attack` is an abstract class that implements `BattleAction`. It holds what
  every attack has in common: a name, accuracy and a type. It also rolls for
  hit or miss and asks `TypeChart` how effective it is.
- `PokemonDamage` extends `Attack` and adds power, so it can actually deal damage.
- `TypeChart` keeps all the type matchups in one place. Nothing else needs to
  know how they work, which makes it easy to add more later.
- `CpuStrategy` is an interface for how the computer picks its attack.
  `RandomStrategy` is the one used in the game, and `AggressiveStrategy` is
  another option. `CpuPlayer` works with whichever strategy it gets.
- `Pokemon` keeps its fields private and checks the values, and
  `PokemonException` is my own exception for invalid input.
## VG

Jag fokuserade på G-kraven och hann inte med de flesta VG-delarna.
Typeffektivitetstabellen    fick jag med, och den syns i stridsloggen.

Det här hann jag inte med:

- Att spara allt som JSON med Jackson. Jag använde vanliga textfiler i stället.
- Kritiska träffar.
- Mer detaljerad statistik, som vinst/förlust per Pokémon och mest använda
  attack eller Pokémon. Jag sparar bara totala vinster och förluster.
## Known limitations

- No critical hits or JSON (VG parts).
- Battle results are only the total number of wins and losses.
- The type chart only contains the relations listed above. All other combinations are 1x.
- `PokemonService` does a lot (input, validation and files) and should be split up.

## Example

```
===| Pokédex |===
1. Show all Pokémon
2. Add new Pokémon
3. Edit Pokémon
4. Delete Pokémon
5. Save to file
6. Upload from file
7. Reset to seeded data
8. Start battle
9. Battle results
10. Exit and save
Choose: 1
Donphan | GROUND | HP: 90/90
Politoed | WATER | HP: 90/90
Rapidash | FIRE | HP: 65/65
Machamp | FIGHTING | HP: 90/90
Garganacl | ROCK | HP: 100/100
Cetitan | ICE | HP: 170/170
```