package JnR;

import java.util.Random;

public class App implements Game {

    private int board[];
    private int currentPlayer;
    private final int player1;
    private final int player2;
    private int dice;
    private int player1Index;
    private int player2Index;
    private int moveCounter;
    private int stayCounter;
    private boolean stay1;
    private boolean stay2;

    App() {
        this.currentPlayer = 1;
        this.player1 = 1;
        this.player2 = 2;
        // 0 = leeres Feld
        // 1 = Player 1
        // 2 = Player 2
        // 3 = Rot 1
        // 4 = Rot 2
        // 5 = Grün
        // 6 = Aussetzen
        // 7 = Nochmal Würfeln
        // 8 = Ziel
        this.board = new int[]{0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 5, 0, 7, 3, 0, 0, 4, 8};
        this.player1Index = 0;
        this.player2Index = 0;
        this.moveCounter = 0;
        this.stayCounter = 2;
        this.stay1 = false;
        this.stay2 = false;
        this.dice = 0;
    }

    public int getMoveCounter() {
        return this.moveCounter;
    }

    public void setMoveCounter(int count) {
        this.moveCounter = count;
    }

    public int getDice(){
        return this.dice;
    }

    public void setDice(int dice){
        this.dice = dice;
    }

    public int getBoardIndex(int index) {
        return this.board[index];
    }

    public void setBoardIndex(int index, int value) {
        this.board[index] = value;
    }

    public int[] getBoard() {
        return this.board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public void setCurrentPlayer(int player) {
        this.currentPlayer = player;
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public int getPlayer1Index() {
        return this.player1Index;
    }

    public int getPlayer2Index() {
        return this.player2Index;
    }

    public int getCurrentPlayer() {
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
        if (currentPlayer == 1) {
            if (checkWinner()) {
                setCurrentPlayer(1);
            } else {
                // Dice Again
                if (diceAgain()) {
                    setCurrentPlayer(1);
                } else {
                    setCurrentPlayer(2);
                }
                stay();
                if(stay1){
                    walk(0);
                } else {
                    walk(rnd.nextInt(6)+1);
                }
                stay1 = false;

            }


        } else if (currentPlayer == 2) {
            if (checkWinner()) {
                setCurrentPlayer(2);
            } else {
                // Dice Again
                if (diceAgain()) {
                    setCurrentPlayer(2);
                } else {
                    setCurrentPlayer(1);
                }
                stay();
                if(stay2){
                    walk(0);
                } else {
                    walk(rnd.nextInt(6)+1);
                }
                stay2 = false;

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
    public void walk(int dice) {
        setDice(dice);
        if (getCurrentPlayer() == 1) {
            if (getPlayer1Index() + dice > 21) {
                // mach nichts
            } else {
                if (moveCounter > 1 && getPlayer1Index() == getPlayer2Index()) {
                    setBoardIndex(getPlayer1Index(), 2);
                } else
                    setBoardIndex(getPlayer1Index(), 0);
                setPlayer1Index(getPlayer1Index() + dice);
                this.setBoardIndex(player1Index, 1);
            }
        } else if (getCurrentPlayer() == 2) {
            if (getPlayer2Index() + dice > 21) {
                // mach nichts
            } else {
                if (moveCounter > 1 && getPlayer1Index() == getPlayer2Index()) {
                    setBoardIndex(getPlayer2Index(), 1);
                } else
                    setBoardIndex(getPlayer2Index(), 0);
                setPlayer2Index(getPlayer2Index() + dice);
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
    public String specialField() {
/*
        // 1 = 1 Feld vor
        // 2 = 2 Felder vor
        // 3 = 3 Felder vor
        // 4 = 4 Felder vor
        // 5 = zurück auf start
        // 6 = Player auf Ziel -2
        // 7 = Player auf Feld 2
        if (getPlayer1Index() == 7 || getPlayer1Index() == 16 || getPlayer2Index() == 7 || getPlayer2Index() == 16) {
            //System.out.println("Random 1: " + specialRandomInt1);
            if (currentPlayer == 1) {
                // Case 1
                if (specialRandomInt1 == 1) {
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer1Index() + 1, 1);
                    setPlayer1Index(getPlayer1Index() + 1);
                    return "Spieler 1 ein Feld vor";
                }
                // Case 2
                else if (specialRandomInt1 == 2) {
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer1Index() + 2, 1);
                    setPlayer1Index(getPlayer1Index() + 2);
                    return "Spieler 1 zwei Felder vor";
                }
                // Case 3
                else if (specialRandomInt1 == 3) {
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer1Index() + 3, 1);
                    setPlayer1Index(getPlayer1Index() + 3);
                    return "Spieler 1 drei Felder vor";
                }
                // Case 4
                else if (specialRandomInt1 == 4) {
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(getPlayer1Index() + 4, 1);
                    setPlayer1Index(getPlayer1Index() + 4);
                    return "Spieler 1 vier Felder vor";
                }
                // Case 5
                else if (specialRandomInt1 == 5) {
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(1, 1);
                    setPlayer1Index(1);
                    return "Spieler 1 zurueck auf Anfang";
                }
                // Case 6
                else if (specialRandomInt1 == 6) {
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(19, 1);
                    setPlayer1Index(19);
                    return "Spieler 1 rueckt vor bis Feld 20";
                }
                // Case 7
                else if (specialRandomInt1 == 7) {
                    setBoardIndex(getPlayer1Index(), 6);
                    setBoardIndex(2, 1);
                    setPlayer1Index(1);
                    return "Spieler 1 rueckt zurueck auf Feld 2";
                }
            }
        }
        else if (currentPlayer == 2) {
            //System.out.println("Random 2: " + specialRandomInt2);
            if (getPlayer2Index() == 7 || getPlayer2Index() == 16) {
                // Case 1
                if (specialRandomInt2 == 1) {
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer2Index() + 1, 2);
                    setPlayer2Index(getPlayer2Index() + 1);
                    return "Spieler 2 ein Feld vor";
                }
                // Case 2
                else if (specialRandomInt2 == 2) {
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer2Index() + 2, 2);
                    setPlayer2Index(getPlayer2Index() + 2);
                    return "Spieler 2 zwei Felder vor";
                }
                // Case 3
                else if (specialRandomInt2 == 3) {
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer2Index() + 3, 2);
                    setPlayer2Index(getPlayer2Index() + 3);
                    return "Spieler 2 drei Felder vor";
                }
                // Case 4
                else if (specialRandomInt2 == 4) {
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(getPlayer2Index() + 4, 2);
                    setPlayer2Index(getPlayer2Index() + 4);
                    return "Spieler 2 vier Felder vor";
                }
                // Case 5
                else if (specialRandomInt2 == 5) {
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(1, 2);
                    setPlayer2Index(1);
                    return "Spieler 2 zurueck auf Anfang";
                }
                // Case 6
                else if (specialRandomInt2 == 6) {
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(19, 2);
                    setPlayer2Index(19);
                    return "Spieler 2 rueckt vor bis Feld 20";
                }
                // Case 7
                else if (specialRandomInt2 == 7) {
                    setBoardIndex(getPlayer2Index(), 6);
                    setBoardIndex(1, 2);
                    setPlayer2Index(1);
                    return "Spieler 2 rueckt zurueck auf Feld 2";
                }
            }
        }
        */
        return "";
    }

    public void stay() {
        if (currentPlayer == 1) {
            if (getPlayer1Index() == 7) {
                stay1 = true;
            }
        }

        if (currentPlayer == 2) {
            if (getPlayer2Index() == 7) {
                stay2 = true;
            }
        }
    }

    public boolean diceAgain() {
        if (currentPlayer == 1) {
            if (getPlayer1Index() == 16) {
                return true;
            }
        }
        if (currentPlayer == 2) {
            if (getPlayer2Index() == 16) {
                return true;
            }
        }
        return false;
    }
}
