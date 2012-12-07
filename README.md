2.10.0-RC3-Benchmark
====================

I saw this benchmark on [this page](http://markehammons.wordpress.com/2012/12/02/2-10-performance/).
In comments someone asked reason of this improvement. I was sure this is due to new Implicit and Value Classes. Value Classes provide inlining instead of object creation at run-time. This means there should be less object allocation in scala 2.10. I used google's allocation instrumenter  
to inspect object allocations. You can find it [here] (http://code.google.com/p/java-allocation-instrumenter/)

## Usage
    sbt assembly

This will create a jar file file in target with name bench2.10.jar. You can change scala version and sbt version by manipulating  build.sbt

    java -javaagent:lib/allocation.jar -jar target/bench2.10.jar  1 1

This will instrument code and create a report. First Parameter for number of iterations on Sundaram second is for Eratosthenes