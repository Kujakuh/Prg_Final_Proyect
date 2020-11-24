/*
Programming final proyect developed by Mónica Treviño Fernández and Álvaro Vos Graciá.
This is a simple command prompt based TicTacToe with some advanced settings.
*/

import java.util.*;
import java.lang.Thread;

public class TicTacToe {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        while(true){

            int stage = menu();
        
            switch(stage){
            
                case 1: instructions(); break;

                case 4: System.exit(-1);

                default: break;

            }
        }
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

    public static void print_matrix(char[][] Board){
        
        for(char[] row : Board){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // Menu display function, that will be returning the selected stage 
    public static int menu() {

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
            if(input.hasNextInt()) option = input.nextInt(); else option = 0; 
             
            // Check user input to only use the aviable options
            if (option != 1 && option != 2 && option != 3 && option != 4) {
                
                input.nextLine();
                System.out.println("Try again, invalid input");
                sleep(2000);
                
            }
            // Check user input to only use the aviable options
        } while(option != 1 && option !=2 && option !=3 && option !=4);
        
        return option;
    }


    public static void instructions() {

        int end_check = 0;

        char[][] inst_board = {
                {'┌', '─', '─', '─', '┬', '─', '─', '─', '┬', '─', '─', '─', '┐'},
                {'|', ' ', '1', ' ', '|', ' ', '2', ' ', '|', ' ', '3', ' ', '|'},
                {'├', '─', '─', '─', '┼', '─', '─', '─', '┼', '─', '─', '─', '┤'},
                {'│', ' ', '4', ' ', '|', ' ', '5', ' ', '|', ' ', '6', ' ', '|'},
                {'├', '─', '─', '─', '┼', '─', '─', '─', '┼', '─', '─', '─', '┤'},
                {'│', ' ', '7', ' ', '|', ' ', '8', ' ', '|', ' ', '9', ' ', '|'},
                {'└', '─', '─', '─', '┴', '─', '─', '─', '┴', '─', '─', '─', '┘'},};

        do{
        
            cls();

            System.out.println("The game is played on a grid that's 3x3 squares.");

            print_matrix(inst_board);

            System.out.println("Players take turns putting their marks in empty squares."+ '\n'+
                               "The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner." +'\n'+
                               "When all 9 squares are full, the game is over."+'\n');

            System.out.println("To play you just have to say in which square you want to place your mark when your turn comes"); 
            
            System.out.println("Press 1 to go back to the menu");

            input.nextLine();

            if(input.hasNextInt()) {
                
                end_check = input.nextInt();
                if(end_check != 1) { System.out.println("Invalid input, try again."); sleep(1250);}

            } else { System.out.println("Invalid input, try again."); sleep(1250);}

        } while(end_check != 1);

    }
}
