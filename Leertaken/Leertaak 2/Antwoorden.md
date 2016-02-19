# Week 2

## Opdracht 1

### Inorder

	current = root; // Begin bij de root
	while ( current is not NULL or stack is nonempty ) 
		if ( current is not NULL ){
			stack.push(current);
			current = current.leftchild
		} 
		else {
			current = stack.pop
			print current.content
			current = current.rightchild
	}

### Preorder
	current = root; // Begin bij de root
	while ( current is not NULL or stack is nonempty ){
		if ( current is not NULL ){
			print current.content
			push current
			current = current.leftchild
		} 
		else {
			current = stack.pop
			current = current.rightchild
		}
	}

### Postorder
	current = root; // Begin bij de root
	while ( current is not NULL or stack is nonempty ){
		if ( current is not NULL ){
			stack.push(current)
			current = current.leftchild
		} 
		else {
			current = stack.pop
			if ( current.secondPop ){
				print current.content
				current = stack.pop
			} 
			else{
				current.setSecondPop
				push current
				current = current.rightchild
			}
		}
	}

## Opdracht 2

Zie de Code in lt2-opdracht2


## Opdracht 3

Zie [GetalRij.java](../../Code/lt2-getalrij/src/getalrij/GetalRij.java#L31-L39).

```java
boolean exists = false;
for (int value : this.getallen) {
  if (value == zoekWaarde) {
    exists = true;
  }
}
return exists;
```

## Opdracht 4

Zie [GetalRij.java](../../Code/lt2-getalrij/src/getalrij/GetalRij.java#L41-L48).

```java
for (int value : this.getallen) {
  if (value == zoekWaarde) {
    return true;
  }
}
return false;
```

## Opdracht 5

Algoritme B zou een stuk sneller moeten zijn. Algoritme A blijft namelijk
doodleuk getalletjes checken, ook als allang bekend is dat het gezochte getal
aanwezig is.

Gemiddeld zal Algoritme B 2× zo snel zijn als Algoritme A, wanneer het gezochte
getal _wel_ aanwezig is. Als het gezochte getal _niet_ in de lijst aanwezig is,
zal er geen verschil in performance zijn.

We vergelijken de totale tijd om 200 willekeurige getallen in een lijst van
100,000 items op te zoeken:

```
Linear        - zitErinA: 13.04ms
Linear        - zitErinB: 11.65ms
```

`zitErinB` is dus vlugger dan `zitErinA`, maar lang niet 2× zo vlug! Dit komt
doordat niet alle getallen die we proberen ook in de lijst zitten. Er is dus een
zekere hoeveelheid iteraties waarbij `zitErinA` en `zitErinB` evenveel werk
doen.

## Opdracht 6

In een vooraf gesorteerde lijst kunnen we nog één situatie versnellen: wanneer
het gezochte getal _niet_ in de lijst zit. Immers, als we door de lijst lopen
en we komen een getal tegen dat _groter_ is dan het gezochte getal, dan zijn
alle volgende getallen _ook_ groter, dus allemaal _niet_ het gezochte getal. We
kunnen dan onmiddellijk stoppen met zoeken.

Zie [GetalRij.java](../../Code/lt2-getalrij/src/getalrij/GetalRij.java#L50-L59).

```java
for (int value : this.getallen) {
  if (value == zoekWaarde) {
    return true;
  } else if (value > zoekWaarde) {
     return false;
  }
}
return false;
```

In onze benchmarks komt dit echter niet uit: deze methode scoort slechter dan
Algoritme B, en soms zelfs slechter dan Algoritme A. Soms, want de test is niet
perfect, en de snelheid van de algoritmen is ook afhankelijk van welke getallen
geprobeerd worden. Als er veel kleine getallen geprobeerd worden die _niet_ in
de lijst zitten, zal Algoritme C voordelig uitvallen. Als er veel grote getallen
voorkomen of getallen die _wel_ in de lijst zitten, raakt Algoritme C in de
problemen.

Waarschijnlijk is de extra if-conditie `value > zoekWaarde` de schuldige. Die
verdubbelt het werk in de loop voor alle getallen beneden de `zoekWaarde`.

```
Linear        - zitErinA: 13.263ms
Linear        - zitErinB: 11.335ms
Linear Sorted - zitErinC: 12.524ms
```

```
Linear        - zitErinA: 13.740ms
Linear        - zitErinB: 11.433ms
Linear Sorted - zitErinC: 13.836ms
```

## Opdracht 7

De zoektijd van een binair zoekalgoritme zal een stuk minder zijn. Gemiddeld
hebben de lineaire varianten namelijk _n / 2_ stappen nodig, terwijl een binair
algoritme slechts _log<sub>2</sub>(n)_ stappen nodig heeft.

Bij 100,000 items verwachten we van een lineair algoritme gemiddeld 50,000
stappen. Bij een binair algoritme verwachten we 17 stappen, ongeveer 3000×
minder. Elke stap kost bij een binair algoritme wel iets meer tijd dan bij een
lineair algoritme, dus de tijdswinst zal minder dan 3000× zijn.

Zie [GetalRij.java](../../Code/lt2-getalrij/src/getalrij/GetalRij.java#L61-L76).

```
Linear        - zitErinA: 13.317ms
Linear        - zitErinB: 11.344ms
Linear Sorted - zitErinC: 13.537ms
Binary Sorted - zitErinD: 0.391ms
```

`zitErinD` is hier ongeveer _13.5 / 0.4 =_ 34× zo snel als de lineaire
varianten. Dat is veel, maar nog lang geen 3000×! We vermoeden dat dit verschil
veroorzaakt wordt doordat lineaire zoekfuncties van meer VM en hardware-
optimalisaties gebruik kunnen maken. Zo interessant is dat ook niet, want dit
algoritme is al best vlug! :)

Als de lijst verdubbeld wordt, zal een lineaire zoekfunctie gemiddeld 2× zoveel
stappen nodig hebben. Een binaire zoekfunctie heeft dan slechts **één** extra
stap nodig:

```
Linear        - zitErinA: 23.414ms
Linear        - zitErinB: 22.065ms
Linear Sorted - zitErinC: 21.863ms
Binary Sorted - zitErinD: 0.269ms
```

## Opdracht 8

Het lineaire algoritme is orde _O(n)_, want als er een extra item aan een lijst
toegevoegd wordt heeft het algoritme ook een extra stap nodig om de lijst te
doorlopen.


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
