package com.example.snakeladder3;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positions;
    ArrayList<Integer> snakeladderpos;
    public Board(){
        positions=new ArrayList<>();
        positionscordinates();
        snakeladderposition();
        
    }
    private void positionscordinates(){
        positions.add(new Pair<>(0,0));
        for(int i=0;i<snakeladder3.height;i++)
        {
            for(int j=0;j<snakeladder3.width;j++)
            {
                int xchord=0;
                if(i%2==0)
                {
                    xchord=j*snakeladder3.tilesize+snakeladder3.tilesize/2;
                }
                else {
                    xchord=snakeladder3.height*snakeladder3.tilesize-(snakeladder3.tilesize*j)-snakeladder3.tilesize/2;
                }
                int ychord=snakeladder3.height*snakeladder3.tilesize-(snakeladder3.tilesize*i)-snakeladder3.tilesize/2;
                positions.add(new Pair<>(xchord,ychord));
            }
        }
    }
    private void snakeladderposition() {
        snakeladderpos = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeladderpos.add(i);
        }
        snakeladderpos.set(4, 25);
        snakeladderpos.set(8, 29);
        snakeladderpos.set(13, 46);
        snakeladderpos.set(33, 49);
        snakeladderpos.set(50, 69);
        snakeladderpos.set(74, 92);
        snakeladderpos.set(67, 86);
        snakeladderpos.set(62, 81);
        snakeladderpos.set(42, 63);
        snakeladderpos.set(27, 5);
        snakeladderpos.set(40, 3);
        snakeladderpos.set(43, 18);
        snakeladderpos.set(54, 31);
        snakeladderpos.set(66, 45);
        snakeladderpos.set(76, 58);
        snakeladderpos.set(89,53);
        snakeladderpos.set(95,84);
        snakeladderpos.set(99,41);

    }


    public int getnewposition(int currentposition)
    {
        if(currentposition>=1&&currentposition<=100)
        {
            return snakeladderpos.get(currentposition);
        }
        return -1;
    }
    public int getxcordinate(int position)
    {
        if(position>=1 && position<=100)
        {
            return positions.get(position).getKey();
        }
        return -1;
    }
    public int getycordinate(int position)
    {
        if(position>=1 && position<=100)
        {
            return positions.get(position).getValue();
        }
        return -1;
    }
}
