package game;
import rooms.Room;
import items.Item;
import people.Person;
import rooms.*;


import java.util.Scanner;

import board.Board;


public class GameRunner {

    public static void main (String[] args)
    {
        Room[][][] map = new Room[3][3][3];
        for(int k = 0; k<map.length; k++)
        {
        	Room[][] floor = map[k];
        	for (int j = 0; j<floor.length; j++)
            {
            	Room[] row = floor[j];
                for (int i = 0; i<row.length;i++)
                {
                    boolean[] doors = {true,true,true,true}; //N,E,S,W
                    Person[] people = {};
                    Item[] items = {};
                    if(i == 1 && j ==2)
                    {
                    	row[i] = new Bossroom(doors, people, items, i, j,k);  // (if fight with boss is won, you move up a floor)
                    }
                    else if(i == 1)
                    {
                    	row[i] = new Hallway(doors, people, items, i, j,k);
                    }
                    else
                    {
                    	row[i] = new Room(doors, people, items, i, j,k);
                    }
                    
                }

            }
        }
        

        Board Abu = new Board(map);


        boolean gameOn = true;
        Person player1 = Utilities.createPerson();
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Abu Industries, " + player1.getName());
        map[0][0][0].addNpc(player1);
        System.out.println();
        Abu.printMap();
        player1.print();
        while(gameOn)
        {
            int move = player1.chooseMove();
            Abu = Utilities.movePlayer(Abu, player1,move);  //updates game map  
            Abu.printMap();
            //gameOn = false;
        }
		in.close();
    }

}