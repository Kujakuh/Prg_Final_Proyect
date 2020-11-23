/*
Programming final proyect developed by Mónica Treviño Fernández and Álvaro Vos Graciá.
This is a simple command prompt based TicTacToe with some advanced settings.
*/

import java.util.*;
import java.lang.Thread;

public class TicTacToe {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        //Para hacer el menú
        menu();
        /*Scanner baia = new Scanner(System.in);
        boolean exit = false;
        int option;
        while (!exit){
            System.out.println("Choose an option:" +'\n'+
                                "1 = Instructions"+ '\n'+
                                "2 = Settings"+'\n'+
                                "3 = Play"+'\n'+
                                "4 = Exit");
            option = baia.nextInt();
         switch(option){
             case 1: 
             System.out.println("Instructions");
             break;
             case 2: 
             System.out.println("Settings");
             break;
             case 3:
             System.out.println("Play");
             break;
             case 4:
             exit = true;
             break;
             default:
             System.out.println("You can only choose a number from 1 to 4");
         }

        }*/

    }
    public static int menu(){
        Scanner menu = new Scanner(System.in);
        int option = 0;
        do{
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Choose an option:" +'\n'+
                                "1 = Instructions"+ '\n'+
                                "2 = Settings"+'\n'+
                                "3 = Play"+'\n'+
                                "4 = Exit");
            option = menu.nextInt();
            /*if(menu.hasNextInt()){
                option = menu.nextInt();
            }
            else {System.out.println("Try again, invalid input");}*/
            if (option != 1&&option !=2&&option !=3&&option !=4){
                System.out.println("Try again, invalid input");
                Thread.sleep( 2000);
            }
        }while(option != 1&&option !=2&&option !=3&&option !=4);   
return option;
    }
}


