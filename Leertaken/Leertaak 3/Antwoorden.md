# Week 3

## Opdracht 1
Recursie in de informatica is een functie die zichzelf aanroept. Twee voorwaarden hiervoor zijn: 

 - Een probleem dat kleiner wordt bij elke aanroep
 - Een basis uitkomst aka `Base Case` de laatste stap
 - (een startprobleem)

## Opdracht 2

Er is een probleem dat steeds kleiner wordt:
	
	else
		solve() // rest of solution with candidates left

en er is een base case:

	if solution complete
		show solution


## Opdracht 3

- **Wat zijn je kandidaten? **
	- De speelkaarten
- **Hoe bewaar je de kandidaten, in welke datastructuur?**
	- Een lijst
- **Hoe heb je het veld gemodelleerd (datastructuur)? **
	- Een 2D array met een solution Stack
- **Hoe herstel je het veld als de recursieve aanroep returnt?**
	- stack.pop


## Opdracht 4

(…)

## Opdracht 5

(…)

## Opdracht 6

Zie [TestTicTacToe.java](../../Code/lt3-TicTacToe/test/ttt/TestTicTacToe.java).

## Opdracht 7

Een Node is een spelsituatie, oftewel een bord en een huidige speler. Elke Node
heeft dus een aantal children, één voor elke move in die situatie mogelijk is.
Het aantal children van een Node is gelijk aan het aantal lege vakjes in die
situatie.

Stel een Node heeft _N_ children, dan heeft elke child _N - 1_ children. De
Depth van een enkele Node is dus _N_, gelijk aan het aantal vrije vakjes. Aan
de start van het spel is _N = 9_, en die _N_ gaat met elke zet 1 omlaag.

De waarderingsfunctie checkt of het bord in een winstaat is. Hierbij wordt ook
rekening gehouden met het aantal zetten dat gebruikt is om tot de winstaat te
komen. Een winstaat voor de Computer in 2 zetten tijd is beter dan een winstaat
voor de Computer in 4 zetten tijd. Voor de Speler geldt het omgekeerde: liever
dat de Speler na 4 zetten wint, dan na 2. We kunnen hier rekening mee houden
door bij een winstaat te compenseren voor het aantal zetten:

    if (speler wint)
      aantal zetten - 9
    else if (computer wint)
      9 - aantal zetten
    else
      0

Dit is bijna perfect, maar er is nog één situatie waarin deze waarderingsfunctie
iets verkeerd kan doen:

    X X a
    O O b
    . . .

Hier kan de Computer kiezen om op vakje `b` in plaats van `a` te staan, wat
waarschijnlijk op remise uitloopt (aangezien de Speler "waarschijnlijk" op
vakje `a` gaat staan). Dat komt doordat _aantal zetten - 9_ en _9 - aantal
zetten_ gelijk kunnen zijn aan _0_ (oftewel remise), waardoor sommige winstaten
als gelijkwaardig aan remise worden gescored. Om dit te vermijden gebruiken we
simpelweg _10_ in plaats van _9_:

    if (speler wint)
      aantal zetten - 10
    else if (computer wint)
      10 - aantal zetten
    else
      0

| State                     | Waardering  |
|---------------------------|-------------|
| Computer wint in 3 zetten | 10 - 3 = 7  |
| Computer wint in 7 zetten | 10 - 7 = 3  |
| Speler wint in 3 zetten   | 3 - 10 = -7 |
| Speler wint in 7 zetten   | 7 - 10 = -3 |
| Remise                    | 0           |

## Opdracht 8

Zie [TicTacToe.java](../../Code/lt3-TicTacToe/src/ttt/TicTacToe.java#L113-L175).

## Opdracht 9

(…)

## Opdracht 10

(…)

## Opdracht 11

(…)

## Opdracht 12

(…)

## Opdracht 13

(…)

## Opdracht 14

(…)

## Opdracht 15

(…)

## Opdracht 16

(…)

## Opdracht 17

(…)

## Opdracht 18

(…)

## Opdracht 19

(…)

## Opdracht 20

(…)

## Opdracht 21

Zie hiervoor ook de benchmark, vooral de enkel en dubbel karakter set. Hier is goed te zien dat de lengte van de dictionary enorm veel uitmaakt op de compressieratio. Daarnaast zal over het algemeen gelden dat hoe groter de tekst hoe efficienter de compressie zal zijn omdat er dan relatief meer karakters per dictionary entry zijn.


