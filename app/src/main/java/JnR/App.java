package JnR;

import java.util.Random;

public class App implements Game{

    private int board[];
    private int currentPlayer;
    private int player1;
    private int player2;
    private int currentDice;
    private int player1Index;
    private int player2Index;
    private int specialRnd;

    App(){
        this.currentPlayer = 1;
        this.player1 = 1;
        this.player2 = 2;
        // 0 = leeres Feld
        // 1 = Player 1
        // 2 = Player 2
        // 3 = Rot 1
        // 4 = Rot 2
        // 5 = Grün
        // 6 = Ereignis
        // 7 = Ziel
        this.board = new int[] {0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0 ,0 ,5 ,0 ,6, 3, 0, 0, 4, 7};
        this.player1Index = 0;
        this.player2Index = 0;
    }

    public int getBoardIndex(int index) {
        return this.board[index];
    }

    public void setBoardIndex(int index, int value){
        this.board[index] = value;
    }

    public int[] getBoard(){
        return this.board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public int getCurrentDice() {
        return currentDice;
    }

    public void setCurrentDice(int currentDice) {
        this.currentDice = currentDice;
    }

    public void setCurrentPlayer(int player){
        this.currentPlayer = player;
    }

    public int getPlayer1(){
        return player1;
    }

    public int getPlayer2(){
        return player2;
    }

    public int getPlayer1Index(){
        return this.player1Index;
    }

    public int getPlayer2Index(){
        return this.player2Index;
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public void setPlayer1Index(int player1Index) {
        this.player1Index = player1Index;
    }

    public void setPlayer2Index(int player2Index) {
        this.player2Index = player2Index;
    }

    public int getSpecialRnd(){
        return specialRnd;
    }


    @Override
    public void dice() {
    Random rnd = new Random();
    setCurrentDice((rnd.nextInt(6)+1));
    walk();

    if(currentPlayer == 1){
        setCurrentPlayer(2);
    } else if (currentPlayer == 2){
        setCurrentPlayer(1);
    }
    }

    @Override
    public void ladder() {
        // Index verändert sich sofort
    }

    @Override
    public void snake() {

    }

    @Override
    public void walk() {            // Vorherige Werte wieder übernehmen
        if (getCurrentPlayer() == 1){
            setBoardIndex(getPlayer1Index(), 0);
            setPlayer1Index(getPlayer1Index()+getCurrentDice());
            this.setBoardIndex(player1Index, 1);
        }
        else if( getCurrentPlayer() == 2){
            setBoardIndex(getPlayer2Index(), 0);
            setPlayer2Index(getPlayer2Index()+getCurrentDice());
            this.setBoardIndex(player2Index, 2);
        }

    }

    @Override
    public void checkWinner() {

    }


    @Override
    public void gameOver() {

    }

    @Override
    public int specialField() {
        Random specialRnd = new Random();
        int specialRndInt = specialRnd.nextInt(6)+1;
        if (getSpecialRnd() == 1){
            // Spieler ein Feld vor
        if(currentPlayer == 1){
            setPlayer1Index(getPlayer1Index()+1);
        } else if(currentPlayer == 2){
            setPlayer2Index(getPlayer2Index()+1);
        }

        } else if (getSpecialRnd() == 2){
            // Gegner drei Felder zurück
            if(currentPlayer == 1){
                setPlayer2Index(getPlayer2Index()-3);
            } else if (currentPlayer == 2){
                setPlayer1Index(getPlayer1Index()-3);
            }

        } else if (getSpecialRnd() == 3){
            // Spieler zwei Felder vor
            if (currentPlayer == 1){
                setPlayer1Index(getPlayer1Index()+2);
            } else if (currentPlayer == 2){
                setPlayer2Index(getPlayer2Index()+2);
            }

        } else if (getSpecialRnd() == 4){
            // Gegner zwei Felder zurück
            if(currentPlayer == 1){
                setPlayer2Index(getPlayer2Index()-2);
            } else if(currentPlayer == 2){
                setPlayer1Index(getPlayer1Index()-2);
            }

        } else if (getSpecialRnd() == 5){
            // Spieler auf Ziel-2
            if (currentPlayer == 1){
                setPlayer1Index(19);
            } else if (currentPlayer == 2){
                setPlayer2Index(19);
            }

        } else if (getSpecialRnd() == 6){
            // Gegner auf Feld 1
            if (currentPlayer == 1){
                setPlayer2Index(1);
            } else if (currentPlayer == 2){
                setPlayer1Index(1);
            }

        }
        return specialRndInt;
    }
}
