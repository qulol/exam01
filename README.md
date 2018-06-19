# exam01
Exam Part 1, module: Programmieren


Erster Teil der Abschlussarbeit des Moduls: Programmieren im Wintersemester 17/18.
Ziel der Aufgabe war ein Program, nur mit Hilfe der Java Basis Pakete java.lang und java.util, zu schreiben.
Mit ein paar Ausnahmen gehörten Unterpakete nicht dazu.

<h1>Connect 6</h1>
Hierbei handelt es sich um eine Abwandlung von TikTakToe. Man braucht 6 Spielsteine in einer Reihe um zu gewinnen und legt in jedem Zug 2 auf ein NxN Dimensionales Spielbrett. Im "Torus" Spielmodus sind die Gewinnlinien nicht durch die Ränder beschränkt und man zählt bei Überschreitung auf der gegenüberliegenden Seite weiter.

Folgende Kommandos sind möglich und jeweils über die Komandozeile einzugeben:

- place \<Zeile>;\<Spalte>;\<Zeile>;\<Spalte><br>
- rowprint \<Zeile><br>
- colprint \<Spalte><br>
- print<br>
- state \<Zeile>;\<Spalte><br>
- reset<br>
- quit<br>
  
Die Parameter sind mit einem Leerzeichen vom Kommando- und mit einem ; (Semikolon) von einander getrennt.
Die Parameter wie z.B <Zeile> muss mit einer Zahl (ohne < und >) im gültigen Bereich ersetzt werden.
  
Das Programm wird mit 3 Argumenten gestartet:

- standard | torus  , beschreibt den Spielmodus<br>
- 18 | 20           , beschreibt die Dimension des NxN Spielbrettes<br>
- 2|3|4             , beschreibt die Anzahl der Spieler<br>

Ein Beispiel für einen gültigen Programmstart wäre:

- java Main standard 18 3
