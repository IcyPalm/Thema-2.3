build:
	mkdir -p bin
	javac \
		-d bin \
		-sourcepath src \
		src/gui/MainFrame.java

run: build
	java \
		-classpath bin \
		gui.MainFrame
