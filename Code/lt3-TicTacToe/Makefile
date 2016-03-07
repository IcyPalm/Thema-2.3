build:
	javac \
		-Xlint:deprecation \
		-d bin \
		-sourcepath src \
		src/ttt/Tui.java

build-test:
	javac \
		-d bin \
		-classpath bin:/usr/share/java/junit4.jar \
		-sourcepath src \
		src/test/*.java

run: build
	java -classpath bin ttt.Tui

test: build-test
	java \
		-classpath bin:/usr/share/java/junit4.jar \
		junit.textui.TestRunner \
		test.AllTests
