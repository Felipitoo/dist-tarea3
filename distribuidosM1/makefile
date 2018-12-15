all: JSONRead.class Enfermero.class Cliente.class Medico.class Paramedico.class Servidor.class Distribuidos.class

Enfermero.class: Enfermero.java
	javac Enfermero.java

JSONRead.class: JSONRead.java
		javac -classpath json-simple-1.1.1.jar JSONRead.java


Cliente.class: Cliente.java
	javac Cliente.java

Medico.class: Medico.java
	javac Medico.java

Paramedico.class: Paramedico.java
	javac Paramedico.java

Servidor.class: Servidor.java
	javac -classpath . Servidor.java

Distribuidos.class: Distribuidos.java
	javac Distribuidos.java

Distribuidos:
	java Distribuidos

JSON:
	java JSONRead

clean :
	rm -f *.class
