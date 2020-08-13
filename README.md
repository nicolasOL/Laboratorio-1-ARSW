
# Laboratorio-1-ARSW
# Integrantes:
## Nicolas Ortega Limas
## German Ospina Quintero


1. In agreement with the lectures, complete the classes CountThread, so that they define the life cycle of a thread that prints the numbers between A and B on the screen.
2. Complete the main method of the CountMainThreads class so that: 	
2.1 Create 3 threads of type CountThread, assigning the first interval [0..99], the second [99..199], and the third [200..299]. 
2.2 Start the three threads with start(). Run and check the output on the screen. 
2.3 Change the beginning with start() to run(). How does the output change? Why?
	- Al usar el metodo start() se crea un nuevo hilo y la salida dependera de la 	eficiencia de cada hilo.
	- Mientras, que al usar el metodo run() solo se ejecutara un hilo, es decir, de 	manera secuencial.

