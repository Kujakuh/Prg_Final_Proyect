/*
Programming final proyect developed by Mónica Treviño Fernández and Álvaro Vos Graciá.
This is a simple command prompt based TicTacToe with some advanced settings.
*/

import java.util.*;
import java.lang.Thread;

public class TicTacToe {

    public static void main(String[] args) {
        menu();
    }

    // Terminal Cleaning
    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Simplified sleep method 
    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {e.printStackTrace();}
    }

    // Menu display function, that will be returning the selected stage 
    public static int menu() {

        Scanner menu = new Scanner(System.in);
        int option;
        do {
            // Terminal clear
            cls();
            
            System.out.println(Data.ANSI_RED + "Choose an option:"+ Data.ANSI_RESET + 
                                                     '\n' + "1 = Instructions" +
                                                     '\n' + "2 = Settings" + 
                                                     '\n' + "3 = Play" + 
                                                     '\n' + "4 = Exit");
            
            // Avoid data types errors, forbidding the user to give us a non numeric input
            if(menu.hasNextInt()) option = menu.nextInt(); else option = 0; 
             
            // Check user input to only use the aviable options
            if (option != 1 && option != 2 && option != 3 && option != 4) {
                
                menu.nextLine();
                System.out.println("Try again, invalid input");
                sleep(2000);
                
            }
            // Check user input to only use the aviable options
        } while(option != 1 && option !=2 && option !=3 && option !=4);
        
        // Scanner end
        menu.close();
        return option;
    }
    public static int Instructions() {
        int option = 0;
        if ( option == 1){
            char[][] ExampleBoard = {
                {'┌', '─', '─', '─', '┬', '─', '─', '─', '┬', '─', '─', '─', '┐'},
                {'|', ' ', '1', ' ', '|', ' ', '2', ' ', '|', ' ', '3', ' ', '|'},
                {'├', '─', '─', '─', '┼', '─', '─', '─', '┼', '─', '─', '─', '┤'},
                {'│', ' ', '4', ' ', '|', ' ', '5', ' ', '|', ' ', '6', ' ', '|'},
                {'├', '─', '─', '─', '┼', '─', '─', '─', '┼', '─', '─', '─', '┤'},
                {'│', ' ', '7', ' ', '|', ' ', '8', ' ', '|', ' ', '9', ' ', '|'},
                {'└', '─', '─', '─', '┴', '─', '─', '─', '┴', '─', '─', '─', '┘'},};
            System.out.println("The game is played on a grid that's 3x3 squares.");
            System.out.println(ExampleBoard);
            System.out.println("Players take turns putting their marks in empty squares."+ '\n'+
             "The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner." +'\n'+
             "When all 9 squares are full, the game is over."+'\n');
             System.out.println("To play you just have to say in which square you want to place your mark when your turn comes"); 
        }
        return option;
    }
}


