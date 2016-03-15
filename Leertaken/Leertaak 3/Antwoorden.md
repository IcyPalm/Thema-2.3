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

_O(N!)_.

Een naïeve implementatie zou er ongeveer zo uit zien:

    can_sum = (array, sum) ->
      if array is empty
        return whether sum equals 0
      for each item in array
        if can_sum (array except `item`, sum)
           or can_sum (array except `item`, sum - item)
          return true
      return false

Bij een array met _N_ items worden tijdens de eerste iteratie _N_ items
gecheckt. Vervolgens wordt hetzelfde _N_ keer gedaan voor een array met _N - 1_
items (recursieve case). Uiteindelijk levert dit een aantal operaties op als:

> N * (N - 1) * (N - 2) * … * 1

> = O(N!)

## Opdracht 10

> list = { 3, 5, 7, 9, 11 }  
> n = 5  
> B = 17

| M | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 |
|---|---|---|---|---|---|---|---|---|---|----|----|----|----|----|----|----|----|
| 1 | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  |


## Opdracht 11

Elke rij stelt een getal in de set voor, en elke kolom stelt een "subsom" voor.
Voor deze opgave gaan we uit van 1-indexed lijsten.

Een kolom (subsom) in rij `i` bevat een ✅ indien:

  - dezelfde kolom in de vorige rij `i - 1` een ✅ bevat—hier wordt het "huidige"
    getal dus _niet_ meegenomen in de som—, of
  - De cel in de vorige rij, in de huidige kolom min het "huidige" getal
    (`list[i]`) een ✅ bevat—hier wordt het "huidige" getal dus bij de vorige
    som opgeteld.

In functievorm:

> q(i, j) = max(q(i - 1, j), q(i - 1, j - list[i]))

Hierbij zijn:

> q(0, j) = ❌ = 0

Dit is de "nulde" rij, waar geen enkel getal uit de set wordt meegenomen. Nul
getallen bij elkaar opgeteld komen natuurlijk nooit op een subsom > 0 uit.

> q(i, 0) = ✅ = 1

Dit is de "nulde" kolom, waar N getallen uit de set tot 0 moeten optellen. Dat
kan altijd, door 0 getallen bij elkaar op te tellen.

Totaalresultaat:

| M | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 |
|---|---|---|---|---|---|---|---|---|---|----|----|----|----|----|----|----|----|
| 1 | ❌ | ❌ | ✅ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  |
| 2 | ❌ | ❌ | ✅ | ❌ | ✅ | ❌ | ❌ | ✅ | ❌ | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  | ❌  |
| 3 | ❌ | ❌ | ✅ | ❌ | ✅ | ❌ | ✅ | ✅ | ❌ | ✅  | ❌  | ✅  | ❌  | ❌  | ✅  | ❌  | ❌  |
| 4 | ❌ | ❌ | ✅ | ❌ | ✅ | ❌ | ✅ | ✅ | ✅ | ✅  | ❌  | ✅  | ❌  | ✅  | ✅  | ✅  | ✅  |
| 5 | ❌ | ❌ | ✅ | ❌ | ✅ | ❌ | ✅ | ✅ | ✅ | ✅  | ✅  | ✅  | ❌  | ✅  | ✅  | ✅  | ✅  |


## Opdracht 12

Zie [BottomUpSolver.java](../../Code/lt3-dynprog/src/dynprog/solvers/BottomUpSolver.java).

> `// The result is in the bottom right of the matrix.`

## Opdracht 13

_O(n × B)_.

Voor een lijst met _n_ getallen en een targetsom _B_, wordt een matrix opgebouwd
van _n × B_ cellen, en elke cel wordt slechts éénmaal berekend.

## Opdracht 14

Zie [TopDownSolver.java](../../Code/lt3-dynprog/src/dynprog/solvers/TopDownSolver.java).

## Opdracht 15

Zie [UncompressTest.java](../../Code/lt3-filecomp/src/test/UncompressTest.java).

## Opdracht 16

Nope

## Opdracht 17

Nope

## Opdracht 18

Zie [HuffmanTree.java](../../Code/lt3-filecomp/src/huffman/HuffmanTree.java).


## Opdracht 19

Jup, werken

## Opdracht 20

Zie [Benchmark.java](../../Code/lt3-filecomp/src/benchmark/Benchmark.java).

## Opdracht 21

Zie hiervoor ook de benchmark, vooral de enkel en dubbel karakter set. Hier is goed te zien dat de lengte van de dictionary enorm veel uitmaakt op de compressieratio. Daarnaast zal over het algemeen gelden dat hoe groter de tekst hoe efficienter de compressie zal zijn omdat er dan relatief meer karakters per dictionary entry zijn.


