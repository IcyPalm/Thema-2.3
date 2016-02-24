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

Het binaire algoritme is orde _O(log n)_. Het algoritme deelt de lijst telkens
in twee delen op en schrijft per stap de _helft_ van de lijst af. Een
verdubbeling van de lengte van de lijst is dus slechts _één_ extra stap voor een
binair algoritme. De lengte van de lijst die onderzocht kan worden in _m_
stappen is _2<sup>m</sup>_. Dus, het aantal stappen dat nodig is om een lijst
met _n_ elementen te doorlopen is _log<sub>2</sub>(n)_.


## Opdracht 10

**Wat is de verzameling trainingsitems T?**

T bevat alle namen van items: {Mercedes SLK 2001, Porsche 911 1982, Renault Laguna 1995, Saab Viggen 1975}

**Wat is de verzameling features F?**

F bevat: AC en ABS

**Hoe ziet het alfabet er in dit geval uit (“the set of categories”)?**

Zo: {Hoog, Midden, Laag}

**Hoe ziet de sequence of symbols eruit voor T? (“taking each item and looking at its category.”)**

| Item | Categorie |
|------|-----------|
| Mercedes SLK 2001   | Hoog   |
| Porsche 911 1982    | Hoog   |
| Renault Laguna 1995 | Midden |
| Saab Viggen 1975    | Laag   |

**Wat is het aantal elementen in T?**

> |T| = 4

**Wat is het aantal symbolen “Hoog” in de sequence?**

> |{item ∈ T | category(item) = Hoog}| = 2

**Met de vorige twee kun je de frequentie uitrekenen van categorieën.**

|Categorie|Frequentie f<sub>i</sub>|
|----|----|
|Hoog|f<sub>i</sub> = 2/4 = ½|
|Midden|f<sub>i</sub> = ¼|
|Laag|f<sub>i</sub> = ¼|

**Als je de frequenties hebt kun je met equation 4.1 de informatie van
trainingsset _T_ berekenen. Je hebt nu de _I<sub>T</sub>_ uit equation 4.4.**

> I<sub>T</sub> = ∑ Informatiewaarde

|Categorie|_f_<sub>_categorie_</sub>|Informatiewaarde|
|----|----|----|
|Hoog|_f_<sub>_Hoog_ = ½|I<sub>T</sub> = _f_<sub>_i_</sub>  log<sub>2</sub>(_f_<sub>_i_</sub>) = ½ log<sub>2</sub>(½) = ½ × -1 = -½|
|Midden|_f_<sub>_Midden_ = 1/4|I<sub>T</sub> = _f_<sub>_i_</sub>  log<sub>2</sub>(_f_<sub>_i_</sub>) = ¼ log<sub>2</sub>(¼) = ¼ × -2 = -½|
|Laag|_f_<sub>_Laag_ = 1/4|I<sub>T</sub> = _f_<sub>_i_</sub>  log<sub>2</sub>(_f_<sub>_i_</sub>)= ¼ log<sub>2</sub>(¼) = ¼ × -2 = -½|
<!-- ![equation](http://www.sciweavers.org/tex2img.php?eq=2%2F4%3D1%2F2	&bc=White&fc=Black&im=jpg&fs=12&ff=arev&edit=) -->

> I<sub>T</sub> = -½ - ½ - ½ = -1½

**Selecteer nu een feature, bijvoorbeeld airco. Wat is de _M_ uit equation 4.4
(“the number of possible values of feature s”). Splits de trainingsset _T_ in
meerdere verzamelingen, afhankelijk van de waarde van de feature. Hoe zien
_T<sub>airco,ja</sub>_ en _T<sub>airco,nee</sub>_ eruit?**

_M = 2_ ("Ja" of "Nee")

> T<sub>airco,ja</sub> = {Mercedes SLK 2001, Porsche 911 1982}  
> T<sub>airco,nee</sub> = {Renault Laguna 1995, Saab Viggen 1975}

**Bereken nu de informatie van de kleinere set _T<sub>airco,ja</sub>_ op dezelfde
manier als je dat net had gedaan voor de hele trainingsset _T_.**

_T<sub>airco,ja</sub>_:

| Categorie | Frequentie | Informatiewaarde |
|-----------|------------|------------------|
| Hoog      | 1          | _1 × log<sub>2</sub>(1) = 0_ |
| Midden    | 0          | _0 × log<sub>2</sub>(0) = 0_ |
| Laag      | 0          | _0 × log<sub>2</sub>(0) = 0_ |

> I<sub>T<sub>airco,ja</sub></sub> = ∑ Informatiewaarde = 0

**Idem _T<sub>airco,nee</sub>_.**

| Categorie | Frequentie | Informatiewaarde |
|-----------|------------|------------------|
| Hoog      | 0          | _0 × log<sub>2</sub>(0) = 0_  |
| Midden    | ½          | _½ × log<sub>2</sub>(½) = -½_ |
| Laag      | ½          | _½ × log<sub>2</sub>(½) = -½_ |

> I<sub>T<sub>airco,nee</sub></sub> = -1

**Wat is het gewogen gemiddelde van _T<sub>airco,ja</sub> en
_T<sub>airco,nee</sub>_?**

> (|T<sub>airco,ja</sub>| ÷ |T|) × I<sub>T<sub>airco,ja</sub></sub> +
> (|T<sub>airco,nee</sub>| ÷ |T|) × I<sub>T<sub>airco,nee</sub></sub>  
> = (2 / 4) × 0 + (2 / 4) × -1 = ½ × -1 = -½

**Bereken equation 4.4.**

> I<sub>airco,T</sub> = I<sub>T</sub> - -½ = -1½ + ½ = -1

**Voer de berekening nu ook uit voor de feature ABS. Kijk welke feature de
meeste informatie bevat.**

> _M = 2_ ("Ja" of "Nee")

> T<sub>abs,ja</sub> = {Mercedes SLK 2001, Renault Laguna 1995}  
> T<sub>abs,nee</sub> = {Porsche 911 1982, Saab Viggen 1975}

> _T<sub>abs,ja</sub>_:

| Categorie | Frequentie | Informatiewaarde |
|-----------|------------|------------------|
| Hoog      | ½          | ½ × log<sub>2</sub>(½) = -½ |
| Midden    | ½          | ½ × log<sub>2</sub>(½) = -½ |
| Laag      | 0          | 0 × log<sub>2</sub>(0) = 0  |

> _T<sub>abs,nee</sub>_:

| Categorie | Frequentie | Informatiewaarde |
|-----------|------------|------------------|
| Hoog      | ½          | ½ × log<sub>2</sub>(½) = -½ |
| Midden    | 0          | 0 × log<sub>2</sub>(0) = 0  |
| Laag      | ½          | ½ × log<sub>2</sub>(½) = -½ |

> I<sub>T<sub>abs,ja</sub></sub> = -1  
> I<sub>T<sub>abs,nee</sub></sub> = -1

> (|T<sub>abs,ja</sub>| ÷ |T|) × I<sub>T<sub>abs,ja</sub></sub> +
> (|T<sub>abs,nee</sub>| ÷ |T|) × I<sub>T<sub>abs,nee</sub></sub>  
> = (2 / 4) × -1 + (2 / 4) × -1 = ½ × -1 + ½ × -1 = -1

> I<sub>abs,T</sub> = I<sub>T</sub> - -1 = -1½ + 1 = -½

Recap:

> I<sub>airco,T</sub> = -1  
> I<sub>abs,T</sub> = -½

**Vervolg daarna met stap 4 van algoritme 4.2.**

           Airco
          /     \
     [Ja]/       \[Nee]
        /         \
      Hoog        ABS
                 /   \
            [Ja]/     \[Nee]
               /       \
            Midden    Laag

Hier een wat ondoorzichtige beschrijving van hoe het algoritme doorlopen wordt:

 * T={Mercedes SLK 2001, Porsche 911 1982, Renault Laguna 1995, Saab Viggen 1975}
 * Categorien: {Hoog, Hoog, Midden, Laag}
 * Node: Airco
 * Mogelijke waarden: {Ja, Nee}

 * Recursie met Airco=Ja
   * T={Mercedes SLK 2001, Porsche 911 1982}
   * Categorieën: {Hoog, Hoog}
   * Node: Hoog.

 * Recursie met Airco=Nee:
   * T={Renault Laguna 1995, Saab Viggen 1975}
   * Categorieën: {Midden, Laag}
   * Midden: ABS
   * Mogelijke waarden: {Ja, Nee}
   * Recursie met Airco=Nee ∧ ABS=Ja:
      * T={Renault Laguna 1995}
     * Categorieën: {Midden}
     * Node: Midden
   * Recursie met Airco=Nee ∧ ABS=Nee:
     * T={Saab Viggen 1975}
     * Categorieën: {Laag}
     * Node: Laag

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
