build:
	mkdir -p bin
	javac \
		-d bin \
		-classpath bin:/usr/share/java/junit4.jar \
		-sourcepath src \
		src/gui/MainFrame.java

run: build
	java \
		-classpath bin \
		gui.MainFrame
