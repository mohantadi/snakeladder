package com.example.snakeladder3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class snakeladder3 extends Application {
    public static final int tilesize=40,height=10,width=10;
    public static final int buttonline=height*tilesize+50,displayline=height*tilesize+10;
   public  static boolean gamestarted=true,playeroneturn=false,playertwoturn = false;
    public Dice dice=new Dice();
    private  Player playerone, playertwo;
    private Pane createcontent(){
        Pane root=new Pane();
        root.setPrefSize(width*tilesize,height*tilesize+100);
       for(int i=0;i<height;i++)
       {
           for(int j=0;j<width;j++)
           {
               Tile tile=new Tile(tilesize);
               tile.setTranslateX(j*tilesize);
               tile.setTranslateY(i*tilesize);
               root.getChildren().add(tile);
           }
       }
        Image img=new Image("C:\\Users\\MOHAN\\IdeaProjects\\snakeladder3\\src\\main\\snakeladd.jpg");
        ImageView board=new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tilesize);
        board.setFitWidth(width*tilesize);
        root.getChildren().add(board);
        //buttons>>>>>>>>..
        Button playeronebutton=new Button("playerone");
        Button playertwobutton=new Button("playertwo");
        Button startbutton=new Button("start");
        playeronebutton.setTranslateY(buttonline);
        playeronebutton.setTranslateX(20);
        playeronebutton.setDisable(true);
        playertwobutton.setTranslateY(buttonline);
        playertwobutton.setTranslateX(300);
        playertwobutton.setDisable(true);
        startbutton.setTranslateY(buttonline);
        startbutton.setTranslateX(160);
        root.getChildren().addAll(playeronebutton,playertwobutton,startbutton);
        //labels>>>>>>>>>>>>>>>
        Label playeronelabel=new Label("");
        Label playertwolabel=new Label("");
        Label dicelabel=new Label("dicevalue::");
        playeronelabel.setTranslateY(displayline);
        playeronelabel.setTranslateX(20);
        playertwolabel.setTranslateY(displayline);
        playertwolabel.setTranslateX(300);
        dicelabel.setTranslateY(displayline);
        dicelabel.setTranslateX(160);
        root.getChildren().addAll(playeronelabel,playertwolabel,dicelabel);
        Player playerone = new Player(tilesize, Color.BLACK,"Mohan");
       Player playertwo= new Player(tilesize-5,Color.WHITE,"Sai");
        root.getChildren().addAll(playerone.getCoin(),playertwo.getCoin());

        startbutton.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent actionEvent){

                gamestarted=true;
                dicelabel.setText("Gamestarted");
                startbutton.setDisable(true);
                playeroneturn=true;
                playeronelabel.setText("Yourturn>>"+playerone.getName());
                playeronebutton.setDisable(false);
                playerone.startingposition();
                playertwoturn=false;
                playertwolabel.setText("");
                playertwobutton.setDisable(true);
                playertwo.startingposition();
            }
        });
        playeronebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(playeroneturn)
                {
                    int dicevalue=dice.getrolleddicevalue();
                    dicelabel.setText("Dicevaue::"+dicevalue);
                    playerone.moveplayer(dicevalue);
                    //winningcondition
                    if(playerone.iswinner()) {
                        dicelabel.setText("winner is"+playerone.getName());
                        playeroneturn=false;
                        playeronebutton.setDisable(true);
                        playeronelabel.setText("");
                        playertwoturn=true;
                        playertwobutton.setDisable(true);
                        playertwolabel.setText("");
                        startbutton.setDisable(false);
                        startbutton.setText("Restart");
                        gamestarted=false;

                    }
                    else {
                        playeroneturn=false;
                        playeronebutton.setDisable(true);
                        playeronelabel.setText("");
                        playertwoturn=true;
                        playertwobutton.setDisable(false);
                        playertwolabel.setText("yourturn>>"+playertwo.getName());
                    }


                }

            }
        });
        playertwobutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestarted) {
                    if (playertwoturn) {
                        int dicevalue = dice.getrolleddicevalue();
                        dicelabel.setText("Dicevaue::" + dicevalue);
                        playertwo.moveplayer(dicevalue);
                        if(playertwo.iswinner())
                        {
                            dicelabel.setText("winner is"+playertwo.getName());
                            playeroneturn=false;
                            playeronebutton.setDisable(true);
                            playeronelabel.setText("");
                            playertwoturn=true;
                            playertwobutton.setDisable(true);
                            playertwolabel.setText("");
                            startbutton.setDisable(false);
                            startbutton.setText("Restart");

                        }
                        else {
                            playeroneturn = true;
                            playeronebutton.setDisable(false);
                            playeronelabel.setText("yourturn>>"+playerone.getName());
                            playertwoturn = false;
                            playertwobutton.setDisable(true);
                            playertwolabel.setText("");
                        }


                    }
                }

            }
        });

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createcontent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}