package JnR;

import processing.core.PApplet;
import processing.core.PImage;

public class Draw extends PApplet {
    public static void main(String[] args) {
        PApplet.runSketch(new String[]{"S&L"}, new Draw());
    }

    App app = new App();
    int[][] fieldCoordinates = {
            {200, 75}, {300, 75}, {400, 75}, {500, 75}, {600, 75}, {700, 75}, {800, 75}, {900, 75},
            {900, 175}, {900, 275}, {900, 375},
            {800, 375}, {700, 375}, {600, 375}, {500, 375}, {400, 375}, {300, 375},
            {300, 225}, {400, 225}, {500, 225}, {600, 225}, {725, 225}
    };


    public void settings() {
        size(1000, 500);
    }

    public void setup() {
        rectMode(CENTER);
        ellipseMode(CENTER);
        noStroke();

        PImage img;
        //img = loadImage("Space.jpg");
        //background(img);
        background(80, 90, 110);
    }

    public void mousePressed() {
        if (mousePressed) {
            println("x: " + mouseX + "\ny: " + mouseY);
            if (mouseX > 75 && mouseX < 175) {
                if (mouseY > 280 && mouseY < 310) {
                    fill(0);
                    rect(125, 400, 70, 70, 20);
                    app.dice();
                    app.setMoveCounter(app.getMoveCounter() + 1);
                }
            }
        }
    }

    public void gameOver() {
        if (app.checkWinner()) {
            textSize(50);
            if (app.getCurrentPlayer() == 1) {
                fill(255, 210, 150);
                rect(725, 225, 90, 90, 30);

                fill(255, 165, 0);
                text("Player " + app.getPlayer1() + " wins!", 275, 460);
            } else if (app.getCurrentPlayer() == 2) {
                fill(150, 150, 255);
                rect(725, 225, 90, 90, 30);
                fill(0, 0, 255);
                text("Player " + app.getPlayer2() + " wins!", 275, 460);
            }
        }
    }

    public void updateField() {
        for (int i = 0; i < app.getBoard().length; i++) {
            if (app.getBoardIndex(i) == 1) {
                fill(255, 165, 0);
                ellipse(fieldCoordinates[i][0], fieldCoordinates[i][1], 40, 40);
            } else if (app.getBoardIndex(i) == 2) {
                fill(0, 0, 255);
                ellipse(fieldCoordinates[i][0], fieldCoordinates[i][1], 40, 40);
            } else if (app.getBoardIndex(i) == 0) {
                fill(255);
                ellipse(fieldCoordinates[i][0], fieldCoordinates[i][1], 40, 40);
            }
            if (app.getMoveCounter() >= 1) {
                fill(255, 210, 150);
                ellipse(50, 50, 40, 40);
            }
            if (app.getMoveCounter() >= 2) {
                fill(150, 150, 255);
                ellipse(50, 100, 40, 40);
            }
        }
    }

    public void checkSameField() {
        if (app.getMoveCounter() >= 1) {
            if (app.getPlayer1Index() == app.getPlayer2Index()) {
                fill(255, 165, 0);
                ellipse(fieldCoordinates[app.getPlayer1Index()][0] + 4, fieldCoordinates[app.getPlayer1Index()][1] + 4, 40, 40);
                fill(0, 0, 255);
                ellipse(fieldCoordinates[app.getPlayer2Index()][0] - 4, fieldCoordinates[app.getPlayer2Index()][1] - 4, 40, 40);
            }
        }
    }

    public String specialText() {
        textSize(15);
        if (app.getPlayer1Index() == 14 || app.getPlayer2Index() == 14) {
            fill(0, 200, 0);
            return "Ladder!";
        }
        if (app.getPlayer1Index() == 17 || app.getPlayer1Index() == 20 || app.getPlayer2Index() == 17 || app.getPlayer2Index() == 20) {
            fill(255, 0, 0);
            return "Snake!";
        }
        if (app.getPlayer1Index() == 16 || app.getPlayer2Index() == 16 || app.getPlayer1Index() == 7 || app.getPlayer2Index() == 7) {
            fill(100, 43, 225);
            return "Roll the dice again!";
        }
        return "";
    }

    public String moveText() {
        if (app.getDice() > 0) {
            fill(0);
            textSize(15);
            if (app.getCurrentPlayer() == 1) {
                return "Player 2 moves " + app.getDice() + " Fields";
            } else if (app.getCurrentPlayer() == 2) {
                return "Player 1 moves " + app.getDice() + " Fields";
            }
        }
        return "";
    }

    public void drawBoard(){

        // Player Houses
        fill(255, 210, 150);
        rect(50, 50, 50, 50, 5);
        fill(150, 150, 255);
        rect(50, 100, 50, 50, 5);

        fill(255);
        // First Row
        for (int i = 300; i <= 900; i = i + 100) {
            rect(i, 75, 50, 50, 10);
        }
        rect(550, 75, 700, 10);

        // First Column
        for (int j = 175; j <= 400; j = j + 100) {
            rect(900, j, 50, 50, 10);
        }
        rect(900, 225, 10, 300);

        // Second Row
        for (int k = 300; k <= 900; k = k + 100) {
            rect(k, 375, 50, 50, 10);
        }
        rect(600, 375, 600, 10);

        // Second Column
        rect(300, 225, 50, 50, 10);
        rect(300, 300, 10, 100);
        textSize(30);

        // Third Row
        for (int l = 400; l <= 600; l = l + 100) {
            rect(l, 225, 50, 50, 10);
        }
        rect(500, 225, 400, 10);

        // Last Field
        fill(255);
        rect(725, 225, 90, 90, 30);

        // Snakes
        fill(255, 0, 0);
        rect(300, 150, 2, 100);
        rect(600, 300, 2, 100);
        rect(600, 225, 50, 50, 10);
        rect(300, 225, 50, 50, 10);

        // Ladder
        fill(0, 255, 0);
        rect(500, 300, 2, 100);
        rect(500, 375, 50, 50, 10);

        // DiceAgain
        fill(100, 43, 225);
        text("2x", 282, 385);
        text("2x", 883, 85);

        if (!app.diceAgain()) {
            textSize(30);
            fill(100, 43, 225);
            text("2x", 282, 385);
            text("2x", 883, 85);
        }

        // Elements
        fill(210);
        rect(125, 185, 205, 35, 5);
        rect(125, 235, 205, 35, 5);
        text(specialText(), 50, 240);
        text(moveText(), 40, 190);
    }

    public void drawPlayer(){
        fill(255, 165, 0);      // Player 1 ROT
        ellipse(50, 50, 40, 40);
        fill(0, 0, 255);          // Player 2 BLAU
        ellipse(50, 100, 40, 40);
    }

    public void drawDice(){
        fill(150);
        if (app.getCurrentPlayer() == 1) {
            fill(255, 210, 150);
        }
        if (app.getCurrentPlayer() == 2) {
            fill(150, 150, 255);
        }
        rect(125, 295, 105, 45, 10);
        fill(220);
        if (mousePressed) {
            rect(125, 295, 100, 40, 10);
        }
        fill(0);
        textSize(12);
        text("Roll the Dice", 90, 300);
        textSize(55);
        fill(220);
        if (app.getMoveCounter() > 0) {
            if (app.getCurrentPlayer() == 1) {
                fill(150, 150, 255);
            }
            if (app.getCurrentPlayer() == 2) {
                fill(255, 210, 150);
            }
        }
        rect(125, 400, 70, 70, 20);
        fill(0);
        text(app.getDice(), 107, 420);
    }

    public void draw() {
        if (mousePressed) {
            keyPressed();
        }

        drawBoard();
        drawPlayer();
        drawDice();


        // Special Fields
        app.ladder();
        app.snake();
        app.diceAgain();


        gameOver();
        updateField();
        checkSameField();
    }
}
