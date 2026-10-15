# Pokédex

A Java console program for managing a collection of Pokémon,
with save/load to file, editing, deletion, seeded starter data and battles.

Hey! im glad you're using my pokemon battler. I hope you'll enjoy battling and winning against the pokemon!

How to run
Open the project in IntelliJ and run Main.java, or from the terminal run: mvn compile exec:java

1. Show all Pokémon

Shows all Pokémon that exist in the program.

2. Add new Pokémon

Adds new Pokémon to the program. You can choose the Name, Its typing, its hp, its speed and its moves.

3. Edit Pokémon

Edits Pokémon. can change their names, typing, speed, moves aswell as their hp.

4. Delete Pokémon

Deletes a Pokémon of your choosing.

5. Save to file

This saves the Pokémon you've added/deleted/edited to the pokédex.

6. Upload from file

This uploads the pokédex that exists in the file.

7. Reset to seeded data

This resets that data so that the default Pokémon show when you show all Pokémon

8. Start battle

Choose one of your Pokémon and fight a random wild Pokémon. Each round you choose an attack and the computer chooses one at random.
The Pokémon with the highest speed attacks first (if the speed is the same a coin flip decides).
An attack hits if a random number 0-99 is lower than its accuracy.
The battle ends when one Pokémon has 0 HP and the result is saved.

9. Battle results

Shows how many battles you have played, won and lost. Saved in battlestats.txt.

10. Exit and save

Saves your pokédex and your battle results and exits the program.

## Damage formula

```
damage = max(1, round(power * 0.4 * randomFactor))
```

randomFactor is a random number between 0.85 and 1.00. The formula is in `PokemonDamage.calculateDamage`.

## OOP

- `BattleAction` (interface) - something that can be used in a battle. The battle engine only uses this.
- `Attack` (abstract class) implements `BattleAction` - name, accuracy and type that all attacks have.
- `PokemonDamage` extends `Attack` - an attack with power that does damage.
- `CpuStrategy` (interface) - how the computer picks an attack. `RandomStrategy` (used in the game) and `AggressiveStrategy` implement it.
- `Pokemon` keeps its fields private and checks the values, `PokemonException` is my own exception.

## VG

Försökte inte riktigt fram på VG.

## Known limitations

- No type effectiveness, critical hits or JSON (VG parts).
- Battle results are only the total number of wins and losses.
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