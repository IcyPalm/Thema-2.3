build:
	javac -d bin -sourcepath src src/ui/Command.java

build-test:
	javac \
		-d bin \
		-classpath bin:/usr/share/java/junit4.jar \
		-sourcepath src \
		src/test/*.java

run: build
	java -classpath bin ui.Command

test: build-test
	for test in test.TestCalculator test.TestFormat test.TestRational ; do \
		java \
			-classpath bin:/usr/share/java/junit4.jar \
			junit.textui.TestRunner \
			"$$test" ; \
	done
