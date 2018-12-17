# dist-tarea3

Antes que nada, cabe mencionar que la contrase�a por defecto fue cambiada a "felipito" sin comillas.

Para ejecutar, utilizar el makefile escribiendo "make", luego "make Distribuidos".
El timestamp esta implementado parcialmente en TimeStamp.java

Comparaci�n de �mbos m�todos:

En el primer caso, que corresponde a la consistencia secuencial, en donde se env�an los cambios al coordinador actual y este propaga a las r�plicas para mantener consistencia completa, 
se env�a un menor n�mero de mensajes y con una menor latencia respecto al segundo m�todo, sin embargo, en este �ltimo cada m�quina hace localmente el cambio y env�a el append de informaci�n hacia
los logs de las otras m�quinas, siendo este m�todo m�s consistente que el primero. No obstante, el segundo m�todo es m�s complejo de implementar, adem�s al ser descentralizado no tiene un �nico punto de falla. Un algoritmo
que se pudo haber usado para implementar el segundo m�todo es el algoritmo de Maekawa, pero preferimos utilizar el primer m�todo debido a su menor complejidad de implementaci�n.

Felipe Montero 201473611-8
Victor Zavala 201473560-K