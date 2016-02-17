build-cli:
	javac -d bin -sourcepath src src/ui/Command.java

build-gui:
	javac -d bin -sourcepath src src/ui/GUI.java

build-test:
	javac \
		-d bin \
		-classpath bin:/usr/share/java/junit4.jar \
		-sourcepath src \
		src/test/*.java

run: build-cli
	java -classpath bin ui.Command

gui: build-gui
	java -classpath bin ui.GUI

test: build-test
	for test in test.TestCalculator test.TestFormat test.TestRational ; do \
		java \
			-classpath bin:/usr/share/java/junit4.jar \
			junit.textui.TestRunner \
			"$$test" ; \
	done
