/*
Programming final proyect developed by Mónica Treviño Fernández and Álvaro Vos Graciá.
This is a simple command prompt based TicTacToe with some advanced settings.
*/

import java.util.*;
import java.lang.Thread;

public class TicTacToe {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String settings[] =  {"Player X", "X", "Player O", "O"};

        while(true){

            int stage = menu();
        
            switch(stage){
            
                case 1: instructions(); break;

                case 2: settings = set_settings(settings); break;

                case 4: System.exit(-1);

                default: break;

            }
        }
    }

    // Terminal Cleaning
    public static void cls() {
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        for(int i=0; i < 50;i++) System.out.println();
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
            
            System.out.println("┌─────────────────────────────────────────────────────────────┐");
            System.out.println("|                       INSTRUCTIONS                          |");
            System.out.println("└─────────────────────────────────────────────────────────────┘\n\n");

            System.out.println("The game is played on a grid that's 3x3 squares.\n");

            print_matrix(inst_board);

            System.out.println("\nPlayers take turns putting their marks in empty squares."+ '\n'+
                               "The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner." +'\n'+
                               "When all 9 squares are full, the game is over."+'\n');

            System.out.println("To play you just have to say in which square you want to place your mark when your turn comes"); 
            
            System.out.print("\nType 1 to go back to the menu: ");

            input.nextLine();

            if(input.hasNextInt()) {
                
                end_check = input.nextInt();
                if(end_check != 1) { System.out.println("Invalid input, try again."); sleep(1250);}

            } else { System.out.println("Invalid input, try again."); sleep(1250);}

        } while(end_check != 1);

    }

    public static String[] set_settings(String rn_stg[]){

        String stg[] = new String[4];
        String player_selec = new String();
        int selec = 5;

        do{

            cls();

            System.out.println("┌─────────────────────────────────────────────────────────────┐");
            System.out.println("|                         SETTINGS                            |");
            System.out.println("└─────────────────────────────────────────────────────────────┘\n\n");

            System.out.println("Actual settings: \n");
            System.out.println("       - Player 1 tag: " + rn_stg[0]);
            System.out.println("       - Player 1 chip: " + rn_stg[1]);
            System.out.println("       - Player 2 tag: " + rn_stg[2]);
            System.out.println("       - Player 2 chip: " + rn_stg[3] + "\n\n");
            
            System.out.print("Choose what player settings you want to change, Player 1, Player 2 or both (enter 0 to keep default): ");

            input.nextLine();
            player_selec = input.nextLine().toLowerCase();

            switch(player_selec){

                case "player 1", "player1", "p1", "1", "1 player", "first", "player one", "one", "first player" -> 
                {selec = 1; break;}
                case "player 2", "player2", "p2", "2", "2 player", "second", "player two", "two", "second player" -> 
                {selec = 2; break;}
                case "both", "player 1 and 2", "player one and two", "one and two", "1 and 2", "both players" -> 
                {selec = 3; break;}
                case "0" -> {return rn_stg;}
                default -> {System.out.print("Invalid input, please try again."); sleep(1250); break;}
            
            }

        } while(selec != 1 && selec != 2 && selec != 3);

        switch(selec){

            case 1 -> {

                System.out.println("\nPlayer 1: ");
                stg[0] = get_tag();
                stg[1] = get_chip();
                stg[2] = rn_stg[2];
                stg[3] = rn_stg[3];
                break;
            }

            case 2 -> {

                System.out.println("\nPlayer 2: ");
                stg[0] = rn_stg[0];
                stg[1] = rn_stg[1];
                stg[2] = get_tag();
                stg[3] = get_chip();
                break;
            }

            case 3 -> {

                System.out.println("\nPlayer 1: ");
                stg[0] = get_tag();
                stg[1] = get_chip();

                System.out.println("\nPlayer 2: ");
                stg[2] = get_tag();
                stg[3] = get_chip();
                break;
            }

        }

        return stg;
    }

    public static String get_tag(){

        String tag = new String();

        System.out.println("How you want to be named as?");

        tag = input.nextLine();

        return tag;
    }

    public static String get_chip(){
        
        String chip = new String();
        int stg = 0;

        do{

            System.out.print("Select a chip of the following ones: X,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,Y,Z,+,*,-,@,1: ");

            chip = input.nextLine().toUpperCase();

            switch(chip){

                case "X","0","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
                "U","V","W","Y","Z","+","*","-","@","1" -> 
                {stg = 1; break;}
                default -> {stg = 0; System.out.println("That is not a valid chip, please try again."); break;}
            }

        } while(stg == 0);

        return chip;
    }

}
