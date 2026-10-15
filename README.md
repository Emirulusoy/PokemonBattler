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

- **Turn order:** the Pokémon with the highest speed attacks first. If the speed is the same, a coin flip decides.
- **Your turn:** you choose an attack from a menu each round.
- **CPU turn:** the computer chooses one of the wild Pokémon's attacks at random.
- **Accuracy:** an attack hits if a random number 0-99 is lower than its accuracy.
- **Type effectiveness:** the attack's type is compared with the defending Pokémon's type. The log prints "It's super effective!" or "It's not very effective." when the multiplier is not 1x.
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

- `BattleAction` (interface) - something that can be used in a battle. The battle engine only uses this, so new kinds of actions can be added without changing `BattleEngine`. It has default methods for `rollHit` and `getEffectiveness`.
- `Attack` (abstract class) implements `BattleAction` - name, accuracy and type that all attacks have, plus hit rolling and effectiveness lookup.
- `PokemonDamage` extends `Attack` - an attack with power that does damage.
- `TypeChart` - keeps the type effectiveness table in one place, so no other class needs to know about it. `Attack` asks it for the multiplier.
- `CpuStrategy` (interface) - how the computer picks an attack. `RandomStrategy` (used in the game) and `AggressiveStrategy` implement it, and `CpuPlayer` uses whichever strategy it is given.
- `Pokemon` keeps its fields private and validates the values. `PokemonException` is my own exception.

## VG

Attempted:

- Type effectiveness table (VG 6.1).

Not done:

- JSON as persistence format (Jackson). Persistence uses text files.
- Critical hits.
- Persistent statistics beyond total wins/losses (win/loss ratio per Pokémon, most used attack, most used Pokémon).

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