To compile InstrumentationAgent:

    cd src/main/java/benchmark
    javac InstrumentationAgent.java
    jar cmf MANIFEST.MF InstrumentationAgent.jar InstrumentationAgent.class
    
ObjSize should be run with agent. Set VM options:

    "-javaagent:.\src\main\java\benchmark\InstrumentationAgent.jar"