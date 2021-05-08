package JnR;

import java.util.Random;

public class App implements Game{

    private int board[];
    private int currentPlayer;
    private final int player1;
    private final int player2;
    private int currentDice;
    private int player1Index;
    private int player2Index;
    private int moveCounter;

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
        this.moveCounter = 0;
    }

    public int getMoveCounter(){
        return this.moveCounter;
    }

    public void setMoveCounter(int count){
        this.moveCounter = count;
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



    @Override
    public void dice() {
    Random rnd = new Random();
    setCurrentDice((rnd.nextInt(6)+1));
    walk();

    if(currentPlayer == 1){
        if (checkWinner()){
            setCurrentPlayer(1);
        } else {
            setCurrentPlayer(2);
        }
    } else if (currentPlayer == 2){
        if(checkWinner()){
            setCurrentPlayer(2);
        } else {
            setCurrentPlayer(1);
        }
    }
    }

    @Override
    public void ladder() {
        if (currentPlayer == 1) {
            if (getPlayer1Index() == 14) {
                setBoardIndex(14, 5);
                setBoardIndex(19, 1);
                setPlayer1Index(19);
            }
        } else if (currentPlayer == 2) {
            if (getPlayer2Index() == 14) {
                setBoardIndex(14, 5);
                setBoardIndex(19, 2);
                setPlayer2Index(19);
            }
        }
    }

    @Override
    public void snake() {
        if (currentPlayer == 1) {
            if (getPlayer1Index() == 17) {
                setBoardIndex(17, 3);
                setBoardIndex(1, 1);
                setPlayer1Index(1);
            } else if (getPlayer1Index() == 20) {
                setBoardIndex(20, 4);
                setBoardIndex(13, 1);
                setPlayer1Index(13);
            }
        }
            if (currentPlayer == 2) {
                if (getPlayer2Index() == 17) {
                    setBoardIndex(17, 3);
                    setBoardIndex(1, 2);
                    setPlayer2Index(1);
                } else if (getPlayer2Index() == 20) {
                    setBoardIndex(20, 4);
                    setBoardIndex(13, 2);
                    setPlayer2Index(13);
                }
            }
        }


    @Override
    public void walk() {
        if (getCurrentPlayer() == 1){
            if(getPlayer1Index()+getCurrentDice() > 21){
                // mach nichts
            } else {
                 if(moveCounter > 1 && getPlayer1Index() == getPlayer2Index()) {
                    setBoardIndex(getPlayer1Index(), 2);
                } else
                setBoardIndex(getPlayer1Index(), 0);
                setPlayer1Index(getPlayer1Index()+getCurrentDice());
                this.setBoardIndex(player1Index, 1);
            }
        }
        else if( getCurrentPlayer() == 2){
            if(getPlayer2Index()+getCurrentDice() > 21){
                // mach nichts
            } else {
                if(moveCounter > 1 && getPlayer1Index() == getPlayer2Index()) {
                    setBoardIndex(getPlayer2Index(), 1);
                } else
                setBoardIndex(getPlayer2Index(), 0);
                setPlayer2Index(getPlayer2Index()+getCurrentDice());
                this.setBoardIndex(player2Index, 2);
            }
        }
    }

    @Override
    public boolean checkWinner() {
        return getPlayer1Index() == 21 || getPlayer2Index() == 21;
    }


    @Override
    public void gameOver() {

    }

    @Override
    public int specialField() {
        Random specialRnd = new Random();
        int specialRandomInt = specialRnd.nextInt(7)+1;

        // 1 = 1 Feld vor
        // 2 = 2 Felder vor
        // 3 = 3 Felder vor
        // 4 = Gegner 1 Feld zurück
        // 5 = Gegner 2 Felder zurück
        // 6 = Player auf Ziel -2
        // 7 = Player auf Feld 2

        if(specialRandomInt == 1){
            if (getPlayer1Index() == 7 || getPlayer1Index() == 16){
                // Case 1
                if(specialRandomInt == 1){
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer1Index()+1, 1);
                    setPlayer1Index(getPlayer1Index()+1);
                }
                // Case 2
                else if (specialRandomInt == 2){
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer1Index()+2, 1);
                    setPlayer1Index(getPlayer1Index()+2);
                }
                // Case 3
                else if (specialRandomInt == 3){
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer1Index()+3, 1);
                    setPlayer1Index(getPlayer1Index()+3);
                }
                // Case 4
                else if (specialRandomInt == 4){
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer2Index()-1, 2);
                    setPlayer2Index(getPlayer2Index()-1);
                }
                // Case 5
                else if (specialRandomInt == 5){
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer2Index()-2, 2);
                    setPlayer2Index(getPlayer2Index()-2);
                }
                // Case 6
                else if (specialRandomInt == 6){
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(19, 1);
                    setPlayer1Index(19);
                }
                // Case 7
                else if (specialRandomInt == 7){
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(1, 1);
                    setPlayer1Index(1);
                }
            }
        }

        else if(specialRandomInt == 2){
            if (getPlayer2Index() == 7 || getPlayer2Index() == 16){
                // Case 1
                if(specialRandomInt == 1){
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer2Index()+1, 2);
                    setPlayer2Index(getPlayer2Index()+1);
                }
                // Case 2
                else if (specialRandomInt == 2){
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer2Index()+2, 2);
                    setPlayer2Index(getPlayer2Index()+2);
                }
                // Case 3
                else if (specialRandomInt == 3){
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer2Index()+3, 2);
                    setPlayer2Index(getPlayer2Index()+3);
                }
                // Case 4
                else if (specialRandomInt == 4){
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer1Index()-1, 1);
                    setPlayer1Index(getPlayer1Index()-1);
                }
                // Case 5
                else if (specialRandomInt == 5){
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer1Index()-2, 1);
                    setPlayer1Index(getPlayer1Index()-2);
                }
                // Case 6
                else if (specialRandomInt == 6){
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(19, 2);
                    setPlayer2Index(19);
                }
                // Case 7
                else if (specialRandomInt == 7){
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(1, 2);
                    setPlayer2Index(1);
                }
            }
        }

        return specialRandomInt;
    }
}
