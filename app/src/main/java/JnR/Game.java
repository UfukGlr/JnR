package JnR;

public interface Game {

        void dice();
        /*
        Erstellt eine Random Zahl zwischen 1 und 6, ruft die walk funktion auf
        und wechselt den Spieler
         */

        void ladder();
        /*
        Bewegt den Spieler auf ein vorher festgelegtes Feld weiter nach vorne
         */

        void snake();
        /*
        Bewegt den Spieler auf ein vorher festgelegtes Feld zurück nach hinten
         */

        void walk();
        /*
        setzt den Index des Spielfeldes auf dem der Player drauf ist auf 0 (leeres Feld)
        und bewegt den Spieler um die Felder die gewürfelt wurden
         */

        boolean checkWinner();
         /*
         Gibt ein True zurück, sobald einer der Player auf dem Zielfeld steht.
          */

        void stay();

        boolean diceAgain();

        void gameOver();
        String specialField();
}
