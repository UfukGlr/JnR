package JnR;

public interface Game {

    /*
    Erstellt eine Random Zahl zwischen 1 und 6, ruft die walk funktion auf
    und wechselt den Spieler
     */
    void dice();

    /*
    Bewegt den Spieler auf ein vorher festgelegtes Feld weiter nach vorne
     */
    void ladder();

    /*
    Bewegt den Spieler auf ein vorher festgelegtes Feld zurück nach hinten
     */
    void snake();

    /*
    setzt den Index des Spielfeldes auf dem der Player drauf ist auf 0 (leeres Feld)
    und bewegt den Spieler um die Felder die gewürfelt wurden
     */
    void walk(int dice);

    /*
    Gibt ein True zurück, sobald einer der Player auf dem Zielfeld steht.
     */
    boolean checkWinner();

    /*
    Befindet sich der Player auf einem diceAgain Feld, gibt die Methode ein true zurück,
    was den CurrentPlayer wieder auf den gleichen Spieler setzt und es ihm erlaubt
    nochmal zu Würfeln.
     */
    boolean diceAgain();

    /*
    Sobald checkWinner true ist, wird das Spiel beendet und der Gewinner wird in der
    Konsole ausgegeben.
     */
    void gameOver();

}
