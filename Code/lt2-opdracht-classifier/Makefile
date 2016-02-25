build:
	javac \
		-Xlint:deprecation \
		-d bin \
		-sourcepath src \
		src/ui/GUI.java
build-tree:
	javac \
		-Xlint:deprecation \
		-d bin \
		-sourcepath src \
		src/ui/TreeFrame.java

build-test:
	javac \
		-d bin \
		-classpath bin:/usr/share/java/junit4.jar \
		-sourcepath src \
		src/test/*.java

run: build
	java -classpath bin ui.GUI

tree: build-tree
	java -classpath bin ui.TreeFrame

test: build-test
	java \
		-classpath bin:/usr/share/java/junit4.jar \
		junit.textui.TestRunner \
		test.AllTests
