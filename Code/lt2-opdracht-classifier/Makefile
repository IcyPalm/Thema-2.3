build:
	javac -d bin -sourcepath src src/ui/GUI.java

build-test:
	javac \
		-d bin \
		-classpath bin:/usr/share/java/junit4.jar \
		-sourcepath src \
		src/test/*.java

run: build
	java -classpath bin ui.GUI

test: build-test
	java \
		-classpath bin:/usr/share/java/junit4.jar \
		junit.textui.TestRunner \
		test.AllTests
