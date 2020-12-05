/*
Programming final proyect developed by Mónica Treviño Fernández and Álvaro Vos Graciá.
This is a simple command prompt based TicTacToe with some advanced settings.
*/

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
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

                case 3: game(settings[0], settings[1], settings[2], settings[3]); break;

                case 4: System.exit(-1);

                default: break;

            }
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

        int end_check = 1;

        char[][] inst_board = {
                    {'╔', '═', '═', '═', '╦', '═', '═', '═', '╦', '═', '═', '═', '╗'},
                    {'║', ' ', '1', ' ', '║', ' ', '2', ' ', '║', ' ', '3', ' ', '║'},
                    {'╠', '═', '═', '═', '╬', '═', '═', '═', '╬', '═', '═', '═', '╣'}, //cambiar esto
                    {'║', ' ', '4', ' ', '║', ' ', '5', ' ', '║', ' ', '6', ' ', '║'},
                    {'╠', '═', '═', '═', '╬', '═', '═', '═', '╬', '═', '═', '═', '╣'},
                    {'║', ' ', '7', ' ', '║', ' ', '8', ' ', '║', ' ', '9', ' ', '║'},
                    {'╚', '═', '═', '═', '╩', '═', '═', '═', '╩', '═', '═', '═', '╝'},};
                    
        char[][] inst_board1 = {
                    {'╔', '═', '═', '═', '╦', '═', '═', '═', '╦', '═', '═', '═', '╗'},
                    {'║', ' ', 'X', ' ', '║', ' ', ' ', ' ', '║', ' ', ' ', ' ', '║'},
                    {'╠', '═', '═', '═', '╬', '═', '═', '═', '╬', '═', '═', '═', '╣'},
                    {'║', ' ', 'O', ' ', '║', ' ', 'X', ' ', '║', ' ', ' ', ' ', '║'},
                    {'╠', '═', '═', '═', '╬', '═', '═', '═', '╬', '═', '═', '═', '╣'},
                    {'║', ' ', 'O', ' ', '║', ' ', 'O', ' ', '║', ' ', 'X', ' ', '║'},
                    {'╚', '═', '═', '═', '╩', '═', '═', '═', '╩', '═', '═', '═', '╝'},};

       do{
        
            cls();
            System.out.println("╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║                       INSTRUCTIONS                          ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n\n");
            System.out.println("The game is played on a grid that's 3x3 squares."+ '\n');

            print_matrix(inst_board);

            System.out.println('\n'+"Players take turns putting their marks in empty squares."+ '\n'+
                               "The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner." +'\n'+
                               "When all 9 squares are full, the game is over."+'\n');

            System.out.println("To play you just have to say in which square you want to place your mark when your turn comes"+'\n'); 
            System.out.println("This is an example of X's winning the game"+'\n');
            print_matrix(inst_board1);
            
            System.out.print('\n'+"Type 0 to go back to the menu: ");

            input.nextLine();

            if(input.hasNextInt()) {
                
                end_check = input.nextInt();
                if(end_check != 0) { System.out.println("Invalid input, try again."); sleep(1250);}

            } else { System.out.println("Invalid input, try again."); sleep(1250);}

        } while(end_check != 0);
    }


    public static String[] set_settings(String rn_stg[]){

        String stg[] = new String[4];
        String player_selec = new String();
        int selec = 5;

        do{

            cls();

            System.out.println("╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║                         SETTINGS                            ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n\n");

            System.out.println("Actual settings: \n");
            System.out.println("       - Player 1 tag: " + rn_stg[0]);
            System.out.println("       - Player 1 chip: " + rn_stg[1]);
            System.out.println("       - Player 2 tag: " + rn_stg[2]);
            System.out.println("       - Player 2 chip: " + rn_stg[3] + "\n\n");
            
            System.out.print("Choose what player settings you want to change, Player 1, Player 2 or both (enter 0 to keep the actual settings): ");

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

    public static int game(String player1_tag, String player1_chip, String player2_tag, String player2_chip){

        int replay = 1;
        String[] game_board = {"0","0",player1_chip," ","0"," ",player1_chip,player1_chip," "};
    
        cls();
        //game_board = populate_null_1d_array(game_board);

//------------------- Testing -----------------------------------------
        print_game_board(game_board);

        game_board = place_chip(game_board, "CPU", "0", player1_chip);

        print_game_board(game_board);
        sleep(2000);
//---------------------------------------------------------------------
        return replay;
    }

    public static String get_tag(){

        String tag = new String();

        System.out.println("How you want to be named as?");

        do {
            tag = input.nextLine();

            if(tag.toUpperCase().equals("CPU")) 
                System.out.println("CPU is a reserved name, please type another name.");

        } while(tag.toUpperCase().equals("CPU"));    

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
                "U","V","W","Y","Z","+","*","-","@","1" -> {stg = 1; break;}
                default -> {stg = 0; System.out.println("That is not a valid chip, please try again."); break;}
            }

        } while(stg == 0);

        return chip;
    }

    public static String check_winner(String board[], String p1_chip, String p2_chip) {

        for (int i=0; i< board.length-1; i++){
    
            String line = null;
    
            switch(i) {
    
                case 1 -> {line = board[0] + board[1] + board[2]; break;}
                case 2 -> {line = board[3] + board[4] + board[5]; break;}
                case 3 -> {line = board[6] + board[7] + board[8]; break;}
                case 4 -> {line = board[0] + board[3] + board[6]; break;}
                case 5 -> {line = board[1] + board[4] + board[7]; break;}
                case 6 -> {line = board[2] + board[5] + board[8]; break;}
                case 7 -> {line = board[0] + board[4] + board[8]; break;}
                case 8 -> {line = board[6] + board[4] + board[2]; break;}
    
            }
    
            if (line.equals(p1_chip + p1_chip + p1_chip))
                return p1_chip;

            else if (line.equals(p2_chip + p2_chip + p2_chip))
                return p2_chip;
            
            for (int j=0; j< board.length-1; j++){
    
                if (board[j].equals(" ")) break;
                else if (j == board.length -1) return "draw";
            }
            
        }

        return null;
    }

    public static String[] place_chip(String rn_board[], String tag, String chip, String enemy_chip){

        String board[] = rn_board;
        int stage = 0;
        int user_input;
        
        if (tag.equals("CPU")) {
            
            user_input = cpu_ai(board, chip, enemy_chip);

            if (!board[user_input].equals(" ")) {
                do{
                    user_input = random_bounded_nums(0, 9);
                } while(!board[user_input].equals(" "));
            }

            board[user_input] = chip;
            user_input++;

            System.out.println("The CPU placed his chip in the following position: " + user_input);
        } 

        else {

            System.out.print("\nIt´s your turn "+ tag +". Type where you wanna place your token (1-9): ");

            do {
            
                if (input.hasNextInt()) {
                
                    user_input = input.nextInt();
                
                    switch(user_input){

                        case 1, 2, 3, 4, 5, 6, 7, 8, 9 -> 
                        {
                            if(board[user_input-1].equals(" ")) {board[user_input-1] = chip; stage = 1;}
                            else System.out.println("That position is already taken, please, select another position.");
                        }

                        default -> System.out.println("Invalid input, try again.");
                    }

                } else { System.out.println("Invalid input, try again.");}

            } while(stage == 0);

        }

        return board;
    }

    public static void print_game_board(String board[]){

        System.out.println('\n');
        System.out.println("        ╔═══╦═══╦═══╗");
        System.out.println("        ║ " + board[0] + " ║ "+ board[1] + " ║ "+ board[2] + " ║");
        System.out.println("        ╠═══╬═══╬═══╣");
        System.out.println("        ║ " + board[3] + " ║ "+ board[4] + " ║ "+ board[5] + " ║");
        System.out.println("        ╠═══╬═══╬═══╣");
        System.out.println("        ║ " + board[6] + " ║ "+ board[7] + " ║ "+ board[8] + " ║");
        System.out.println("        ╚═══╩═══╩═══╝");
        System.out.println('\n');
    }

    public static String[] populate_null_1d_array(String list[]){

        String temp[] = new String[list.length];
        for(int i = 0; i < temp.length; i++) temp[i] = " ";
        return temp;
    }

    public static void print_matrix(char[][] Board){
        
        for(char[] row : Board){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static int random_bounded_nums(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a + 1, b);
    }


    public static boolean check_char(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    // Terminal Cleaning
    public static void cls() {
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        for(int i=0 ; i < 80 ; i++) System.out.println();
    }

    // Simplified sleep method 
    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {e.printStackTrace();}
    }

    //----------------------------- AI -----------------------------------------------------------------------------------------
    
    // https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/

    public static int cpu_ai(String rn_board[], String cpu_chip, String enemy_chip){

        int pos[] = cpu_move(one_to_two_dim(rn_board), enemy_chip, "0");

        return pos[0]*3 + pos[1];
    }

    public static Boolean moves_left(String board[]) 
    { 
        return true;
        //for (int i = 0; i < board.length; i++) 
         //   if (board[i].equals(" ")) 
        //        return true; 
        //return false; 
    } 

    public static String[][] one_to_two_dim(String a[]){

        String array[][] = new String[3][3];

        for(int i=0; i<3;i++)
            for(int j=0;j<3;j++)
                array[i][j] = a[(j*3) + i];
        
        return array;
    }

    public static String[] two_to_one_dim(String a[][]){

        String array[] = new String[9];

        int l = 0;
        for(int i=0; i<3;i++)
            for(int j=0;j<3;j++)
                array[l] = a[i][j];
                l++;
        
        return array;
    }

    public static int evaluate(String b[][], String player, String cpu) 
    { 
        // Checking for Rows for player or cpu victory. 
        for (int row = 0; row < 3; row++) 
        { 
            if (b[row][0].equals(b[row][1]) && 
               b[row][1].equals(b[row][2])) 
            { 
                if (b[row][0].equals(player)) 
                    return +10; 
                else if (b[row][0].equals(cpu)) 
                    return -10; 
            } 
        } 
  
        // Checking for Columns for player or cpu victory. 
        for (int col = 0; col < 3; col++) 
        { 
            if (b[0][col].equals(b[1][col]) && 
                b[1][col].equals(b[2][col])) 
            { 
                if (b[0][col].equals(player)) 
                    return +10; 
  
                else if (b[0][col].equals(cpu)) 
                    return -10; 
            } 
        } 
  
        // Checking for Diagonals for player or cpu victory. 
        if (b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2])) 
        { 
            if (b[0][0].equals(player)) 
                return +10; 
            else if (b[0][0].equals(cpu)) 
                return -10; 
        } 
  
        if (b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0])) 
        { 
            if (b[0][2].equals(player)) 
                return +10; 
            else if (b[0][2].equals(cpu)) 
                return -10; 
        } 
  
        // Else if none of them have won then return 0 
        return 0; 
    } 

    static int minimax(String board[][], int depth, Boolean isMax, String player, String cpu) { 
        
        int score = evaluate(board, player, cpu); 
  
        // If Maximizer has won the game  
        // return his/her evaluated score 
        if (score == 10) 
        return score; 
  
        // If Minimizer has won the game  
        // return his/her evaluated score 
        if (score == -10) 
        return score; 
        
        // If there are no more moves and  
        // no winner then it is a tie 
        if (moves_left(two_to_one_dim(board)) == false) 
        return 0; 
  
        // If this maximizer's move 
        if (isMax) 
        { 
            int best = -1000; 
  
            // Traverse all cells 
            for (int i = 0; i < 3; i++) 
            { 
                for (int j = 0; j < 3; j++) 
                { 
                    // Check if cell is empty 
                    if (board[i][j].equals(" ")){ 

                        // Make the move 
                        board[i][j] = cpu; 
  
                        // Call minimax recursively and choose 
                        // the maximum value 
                        best = Math.max(best, minimax(board, depth + 1, !isMax, player, cpu)); 
  
                        // Undo the move 
                        board[i][j] = " "; 
                    } 
                } 
            } 

            return best; 
        } 
        else 
        { 
            int best = 1000; 
  
            // Traverse all cells 
            for (int i = 0; i < 3; i++){ 

                for (int j = 0; j < 3; j++){ 

                    // Check if cell is empty 
                    if (board[i][j].equals(" ")){ 
                        // Make the move 
                        board[i][j] = player; 
  
                        // Call minimax recursively and choose 
                        // the maximum value 
                        best = Math.max(best, minimax(board, depth + 1, !isMax, player, cpu)); 
  
                        // Undo the move 
                        board[i][j] = " "; 
                    } 
                } 
            }

            return best; 
        }

    } 

    static int[] cpu_move(String board[][], String player, String cpu){

        int bestVal = -1000; 
        int bestMove[] = {-1,-1};
  
        // Traverse all cells, evaluate minimax function  
        // for all empty cells. And return the cell  
        // with optimal value. 
        for (int i = 0; i < 3; i++){ 

            for (int j = 0; j < 3; j++){ 

                // Check if cell is empty 
                if (!board[i][j].equals(player) && !board[i][j].equals(cpu)){ 
                    // Make the move 
                    board[i][j] = cpu; 
  
                    // Compute evaluation function for this 
                    // move. 
                    int moveVal = minimax(board, 0, false, player, cpu); 
  
                    // Undo the move 
                    board[i][j] = " "; 
  
                    // If the value of the current move is 
                    // more than the best value, then update 
                    // best
                    if (moveVal > bestVal) 
                    { 
                        bestMove[0] = i; 
                        bestMove[1] = j; 
                        bestVal = moveVal; 
                    } 
                } 
            } 
        } 

        return bestMove; 
    } 
}