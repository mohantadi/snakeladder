package com.example.snakeladder3;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    Circle coin;
    int currentposition;
    String name;
    Board gameboard=new Board();

    public Player(int tilesize, Color coincolor, String playername) {
        coin=new Circle(tilesize/2);
        coin.setFill(coincolor);
        currentposition=1;
        coin.setTranslateX(gameboard.getxcordinate(currentposition));
        coin.setTranslateY(gameboard.getycordinate(currentposition));
        moveplayer(0);
        name=playername;

    }


    public void moveplayer(int dicevalue)
    {
        if(currentposition+dicevalue<=100) {
            currentposition += dicevalue;
            TranslateTransition secondmove = null, firstmove = translateAnimation(dicevalue);
            translateAnimation(dicevalue);

            int newposition = gameboard.getnewposition(currentposition);
            if (currentposition != newposition && newposition != -1) {
//            int x=gameboard.getxcordinate(newposition);
//            int y=gameboard.getycordinate(newposition);
                currentposition = newposition;
                secondmove = translateAnimation(6);
            }
            if(secondmove==null)
            {
                firstmove.play();
            }
            else{
                SequentialTransition sequentialtransition=new SequentialTransition(firstmove,
                        new PauseTransition(Duration.millis(500)),secondmove);
                sequentialtransition.play();
            }
        }
    }
   private TranslateTransition translateAnimation(int dicevalue)
   {
       TranslateTransition animate=new TranslateTransition(Duration.millis(100*dicevalue),coin);
       animate.setToX(gameboard.getxcordinate(currentposition));
       animate.setToY(gameboard.getycordinate(currentposition));
       animate.setAutoReverse(false);
       return animate;

   }
   public void startingposition()
   {
       currentposition=0;
       moveplayer(1);
   }
   boolean iswinner()
   {
       if(currentposition==100)
       {
           return true;
       }
       return false;
   }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentposition() {
        return currentposition;
    }

    public String getName() {
        return name;
    }
}
