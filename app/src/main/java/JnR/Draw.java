package JnR;

import processing.core.PApplet;

public class Draw extends PApplet {
    public static void main(String[] args) {
        PApplet.runSketch(new String[]{""}, new Draw());
    }

    int moveCounter = 0;

    App app = new App();
    int[][] fieldCoordinates = {
            {200, 75}, {300, 75}, {400, 75}, {500, 75}, {600, 75}, {700, 75}, {800,75}, {900, 75},
            {900, 175}, {900, 275}, {900, 375},
            {800, 375}, {700, 375}, {600, 375}, {500, 375}, {400, 375}, {300, 375},
            {300, 225}, {400, 225}, {500,225}, {600, 225}, {725, 225}
    };




    public void settings() {
        size(1000, 500);
    }

    public void setup() {
    }

    public void field(){
    }

    // Hilfsmethode - Kann später weg
    public void mousePressed(){
        if (mousePressed){
            if (mouseX > 50 && mouseX < 150){
                if(mouseY > 280 && mouseY < 310){
                    app.dice();
                    moveCounter++;
                }
            }
            println();
            println("mouse X: " + mouseX);
            println("mouse Y: " + mouseY);
            println();
        }
        println(app.getBoard());
        println(app.getCurrentPlayer());
    }



    public void updateField(){
        for (int i = 0; i < app.getBoard().length; i++){
            if (app.getBoardIndex(i) == 1){
                fill(255, 165, 0);
                ellipse(fieldCoordinates[i][0], fieldCoordinates[i][1], 40, 40);
            } else if (app.getBoardIndex(i) == 2){
                fill(0,0,255);
                ellipse(fieldCoordinates[i][0], fieldCoordinates[i][1], 40, 40);
            } else if (app.getBoardIndex(i) == 0){
                fill(255);
                ellipse(fieldCoordinates[i][0], fieldCoordinates[i][1], 40, 40);
            }
            if (moveCounter >= 1){
                fill(255,210,150);
                ellipse(50,50,40,40);
            }if (moveCounter >= 2){
                fill(150,150,255);
                ellipse(50,100,40,40);
            }
        }
    }

    public void checkSameField() {
        if(moveCounter >= 1) {
            if (app.getPlayer1Index() == app.getPlayer2Index()) {
                fill(255, 165, 0);
                ellipse(fieldCoordinates[app.getPlayer1Index()][0] + 4, fieldCoordinates[app.getPlayer1Index()][1] + 4, 40, 40);
                fill(0, 0, 255);
                ellipse(fieldCoordinates[app.getPlayer2Index()][0] - 4, fieldCoordinates[app.getPlayer2Index()][1] - 4, 40, 40);
            }
        }
    }



    public void draw() {
        // Warum geht das nicht amk
        if(mousePressed){
            keyPressed();
        }

        if (app.getCurrentPlayer() == 1){
            if (app.getPlayer1Index() == 7 || app.getPlayer1Index() == 16){
                //app.specialField();
                //fill(255);
                //text("Case: " + app.specialField(), 550, 440);
                println("specialfield");
                println(app.getCurrentPlayer());
            }
        } else if (app.getCurrentPlayer() == 2){
            if (app.getPlayer2Index() == 7 || app.getPlayer2Index() == 16){
                //app.specialField();
                //fill(255);
                //text("Case: " + app.specialField(), 550, 440);
                println("specialfield");
                println(app.getCurrentPlayer());
            }
        }





        noStroke();
        //PImage img;
        //img = loadImage("./lawn.jpg");
        background(120,150,120);
        rectMode(CENTER);
        ellipseMode(CENTER);


        // Fields
        // Player Houses
        fill(255,210,150);
        rect(50, 50, 50, 50);
        fill(150,150,255);
        rect(50, 100, 50, 50);

        fill(255);
        // First Row
        for (int i = 300; i <= 900; i=i+100) {
            rect(i, 75, 50, 50);
            }
        rect(550, 75, 700, 10);
        // First Column
        for (int j = 175; j <= 400; j=j+100){
            rect(900, j, 50, 50);
        }
        rect(900, 225, 10, 300);
        // Second Row
        for (int k = 300; k <= 900; k=k+100) {
            rect(k, 375, 50, 50);
        }
        rect(600, 375, 600, 10);
        // Second Column
            rect(300, 225, 50, 50);
        rect(300, 300, 10, 100);
        textSize(30);
        // Third Row
        for (int l = 400; l <= 600; l=l+100) {
            rect(l, 225, 50, 50);
        }
        rect(500, 225, 400, 10);
        // Last Field
        fill(255,215,0);
            ellipse(730, 225, 90, 90);

        fill(255);

        // Snakes
        fill(255,0,0);
        rect(300, 150, 2, 100);
        rect(600, 300, 2, 100);
        rect(600,225,50,50);
        rect(300, 225, 50, 50);

        // Ladder
        fill(100,255,100);
        rect(500, 300, 2, 100);
        rect(500, 375, 50, 50);

        // Special Field
        fill(200,15,200);
        text("?", 295, 385);
        text("?", 895, 85);


        // Player
        fill(255, 165, 0);      // Player 1 ROT
        ellipse(50,50,40,40);
        fill(0,0,255);          // Player 2 BLAU
        ellipse(50,100,40,40);

        // Dice
        if (app.getCurrentPlayer() == 1){
            fill(255, 165, 0);
        }
        else if (app.getCurrentPlayer() == 2){
            fill(0,0,255);
        }
        rect(100, 295, 100, 30);
        fill(0);
        textSize(12);
        text("Roll the Dice", 65, 300);
        textSize(55);
        text(app.getCurrentDice(), 75, 400);


        updateField();
        checkSameField();


    }



}
