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


## Opdracht 4

Zie [GetalRij.java](../../Code/lt2-getalrij/src/getalrij/GetalRij.java#L41-L48).


## Opdracht 5

Algoritme B zou een stuk sneller moeten zijn. Algoritme A blijft namelijk
doodleuk getalletjes checken, ook als allang bekend is dat het gezochte getal
aanwezig is.

Gemiddeld zal Algoritme B 2× zo snel zijn als Algoritme A, wanneer het gezochte
getal _wel_ aanwezig is. Als het gezochte getal _niet_ in de lijst aanwezig is,
zal er geen verschil in performance zijn.

Hieronder de gemiddelde tijd om 1 item te vinden. Hiervoor is een lijst van
100,000 items gebruikt en 50 samples:

```
Lineair - zitErinA: 0.056ms
Lineair - zitErinB: 0.028ms
```

Dan is `zitErinB` dus precies 2× zo vlug! Dat is niet wat we verwachtten, want
er zou immers ook vaak _geen_ verschil in performance moeten zijn, wanneer het
gezochte getal _niet_ in de lijst zit. Een veelvoorkomende issue met benchmarks
is dat de volgorde uit kan maken, voor als bijvoorbeeld de VM nog bezig is met
opwarmen. We kunnen het effect hiervan globaal bekijken als we eerst algoritme B
timen en vervolgens algoritme A:

```
Lineair - zitErinB: 0.024ms
Lineair - zitErinA: 0.036ms
```

Juist, dat is wat we verwachten!

Om dit soort timingproblemen te voorkomen doen we het volgende:
[We genereren vijf GetalRijen en gebruiken daarvan alleen de laatste.](../../Code/lt2-getalrij/src/getalrij/SnelheidOefening.java#L45-L49)
Zo verknoeien we genoeg tijd om de JVM al wat op te warmen en wat
GetalRij-methoden te optimaliseren. Dan gebeurt dat allemaal niet meer _tijdens_
de test. Vervolgens zullen we ook de resultaten in nanoseconden weergeven omdat
't allemaal wat sneller gaat na het opwarmen.

```
Lineair - zitErinA: 351.5773ns
Lineair - zitErinB: 304.5789ns
```

### SIDETRACK!

Het wordt nóg interessanter wanneer we dezelfde methode meerdere malen
benchmarken:

```
Lineair - zitErinA - 1: 0.032ms
Lineair - zitErinA - 2: 0.008ms
Lineair - zitErinA - 3: 0.008ms
```

Wow! Hypothese: JIT-compilatie ziet dat `zitErinA` erg vaak uitgevoerd wordt, en
hyperoptimaliseert er de 💩 uit. Dit is leuk, maar voor ons niet bijzonder
belangrijk—de verschillende methoden worden immers allemaal even vaak gedraaid
in onze daadwerkelijke testsituatie. We gaan er voor het gemak vanuit dat het
effect van JIT-optimalisatie geen gigantisch effect heeft op de verschillen
tussen de methoden. (Of dat terecht is, …)

## Opdracht 6

In een vooraf gesorteerde lijst kunnen we nog één situatie versnellen: wanneer
het gezochte getal _niet_ in de lijst zit. Immers, als we door de lijst lopen
en we komen een getal tegen dat _groter_ is dan het gezochte getal, dan zijn
alle volgende getallen _ook_ groter, dus allemaal _niet_ het gezochte getal. We
kunnen dan onmiddellijk stoppen met zoeken.

In onze benchmarks komt dit echter niet uit: deze methode scoort slechter dan
Algoritme B, maar nog wel beter dan Algoritme A. Mogelijk is de extra
if-conditie `value > zoekWaarde` de schuldige.

```
Linear        - zitErinA: 381.1027ns
Linear        - zitErinB: 275.7311ns
Linear Sorted - zitErinC: 322.1291ns
```

## Opdracht 7

(…)


## Opdracht 8

(…)


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
