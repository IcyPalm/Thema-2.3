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

(…)


## Opdracht 3

(…)


## Opdracht 4

(…)


## Opdracht 5

(…)


## Opdracht 6

(…)


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
