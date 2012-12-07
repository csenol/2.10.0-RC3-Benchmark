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

## Results
    java -javaagent:lib/allocation.jar -jar target/bench2.10.jar  1 1
    Sieve of Sundaram
    ___________________________

	Cold results: 
		Average: 	5283.500000ms
		Median: 	6045ms
		Mode: 		4522ms
 		Allocation: 	29353347
 
	Warm results: 
		Average: 	4338.000000ms
		Median: 	4505ms
		Mode: 		4171ms
 		Allocation: 	29368200    
 
    Sieve of Eratosthenes
    _______________________________

	Cold results: 
		Average: 	1476.500000ms
		Median: 	1537ms
		Mode: 		1416ms
 		Allocation: 	1061630
 
	Warm results: 
		Average: 	1261.000000ms
		Median: 	1264ms
		Mode: 		1258ms
 		Allocation: 	1061789


    java -javaagent:lib/allocation.jar -jar target/bench2.9.2.jar  1 1	 

    Sieve of Sundaram
    ___________________________

	Cold results: 
		Average: 	6280.500000ms
		Median: 	6510ms
		Mode: 		6051ms
 		Allocation: 	39809032
 
	Warm results: 
		Average: 	5591.500000ms
		Median: 	5615ms
		Mode: 		5568ms
 		Allocation: 	40779511


    Sieve of Eratosthenes
    _______________________________

	Cold results: 
		Average: 	4537.000000ms
		Median: 	4608ms
		Mode: 		4466ms
 		Allocation: 	28596804
 
	Warm results: 
		Average: 	4332.500000ms
		Median: 	4336ms
		Mode: 		4329ms
 		Allocation: 	28596971
