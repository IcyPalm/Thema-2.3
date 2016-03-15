build-sum-threads:
	mkdir -p bin
	javac \
		-d bin \
		-sourcepath src \
		src/lt4/SumThreads.java

sum-threads: build-sum-threads
	java -classpath bin lt4.SumThreads

build-thread-cooperation:
	mkdir -p bin
	javac \
		-d bin \
		-sourcepath src \
		src/lt4/ThreadCooperation.java

thread-cooperation: build-thread-cooperation
	java -classpath bin lt4.ThreadCooperation
