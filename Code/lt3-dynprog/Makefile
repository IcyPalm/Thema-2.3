build-test:
	javac \
		-d bin \
		-classpath bin:/usr/share/java/junit4.jar \
		-sourcepath src \
		src/dynProg/DynamischProgrammerenTest.java

test: build-test
	java \
		-classpath bin:/usr/share/java/junit4.jar \
		junit.textui.TestRunner \
		dynProg.DynamischProgrammerenTest
