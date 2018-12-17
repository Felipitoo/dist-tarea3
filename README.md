# dist-tarea3

Antes que nada, cabe mencionar que la contraseña por defecto fue cambiada a "felipito" sin comillas.

Para ejecutar, utilizar el makefile escribiendo "make", luego "make Distribuidos".
El timestamp esta implementado parcialmente en TimeStamp.java

Comparación de ámbos métodos:

En el primer caso, que corresponde a la consistencia secuencial, en donde se envían los cambios al coordinador actual y este propaga a las réplicas para mantener consistencia completa, 
se envía un menor número de mensajes y con una menor latencia respecto al segundo método, sin embargo, en este último cada máquina hace localmente el cambio y envía el append de información hacia
los logs de las otras máquinas, siendo este método más consistente que el primero. No obstante, el segundo método es más complejo de implementar, además al ser descentralizado no tiene un único punto de falla. Un algoritmo
que se pudo haber usado para implementar el segundo método es el algoritmo de Maekawa, pero preferimos utilizar el primer método debido a su menor complejidad de implementación.

Felipe Montero 201473611-8
Victor Zavala 201473560-K