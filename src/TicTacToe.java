
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
        String[] game_board = new String[9];
        String win_check = new String();

        game_board = populate_null_1d_array(game_board);

        cls();

        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║                            GAME                             ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝\n\n");

        System.out.println("Select which option do you want: \n\n (A) Play 1 vs 1 \n (B) Play vs IA\n");

        char select_option;

        do{
            select_option = input.next().charAt(0);
		    if ((select_option!='A')&&(select_option!='a')&&(select_option!='B')&&(select_option!='b')){

                System.out.println("\nThat is not a valid answer, \nTry again: ");}}

        while ((select_option!='A')&&(select_option!='a')&&(select_option!='B')&&(select_option!='b'));

        // -------- Check if 1 vs Ai was selected and choose as what player you wanna play if so        

        int status =0;

        do{
            cls();

            System.out.println("╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║                            GAME                             ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n");

            print_game_board(game_board);
            game_board=place_chip(game_board,player1_tag,player1_chip,player2_chip);
            win_check = check_winner(game_board, player1_chip, player2_chip);

            if (win_check.equals(player1_chip) || win_check.equals(player2_chip) || win_check.equals("draw"))
                status=1;

            print_game_board(game_board);
            if (status == 0){
                game_board=place_chip(game_board,player2_tag,player2_chip,player1_chip);
                win_check = check_winner(game_board, player1_chip, player2_chip);

                if (win_check.equals(player1_chip) || win_check.equals(player2_chip) || win_check.equals("draw"))
                    status=1;
            }

        } while (status==0);

        if (win_check.equals(player1_chip)) System.out.println(player1_tag + " wins.");
        else if (win_check.equals(player2_chip)) System.out.println(player2_tag + " wins.");
        else if (win_check.equals("draw")) System.out.println("It's a draw.");

        sleep(5000); //testing if print works

        // ---- Ask if you wanna keep playing (using the same settings)

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
   /* public static String winner(String board[], String p1_chip, String p2_chip, String p1_tag, String p2_tag){
        for (int i=1; i < 10; i++){
        switch(i){
            case 1 ->{
                if(board[0].equals(p1_chip)&&board[1].equals(p1_chip)&&board[2].equals(p1_chip)){
                    System.out.println(p1_tag + " wins"); 
                }
                if (board[0].equals(p2_chip)&&board[1].equals(p2_chip)&&board[2].equals(p2_chip)){
                    System.out.println(p2_tag + " wins"); 
                }
                break;
            }
            case 2 ->{
                if(board[3].equals(p1_chip)&&board[4].equals(p1_chip)&&board[5].equals(p1_chip)){
                    System.out.println(p1_tag + " wins"); 
                }
                if (board[3].equals(p2_chip)&&board[4].equals(p2_chip)&&board[5].equals(p2_chip)){
                    System.out.println(p2_tag + " wins"); 
                }
                break;
            }
            case 3 ->{
                if(board[6].equals(p1_chip)&&board[7].equals(p1_chip)&&board[8].equals(p1_chip)){
                    System.out.println(p1_tag + " wins"); 
                }
                if (board[6].equals(p2_chip)&&board[7].equals(p2_chip)&&board[8].equals(p2_chip)){
                    System.out.println(p2_tag + " wins"); 
                }
                break;
            }
            case 4 ->{
                if(board[0].equals(p1_chip)&&board[3].equals(p1_chip)&&board[6].equals(p1_chip)){
                    System.out.println(p1_tag + " wins"); 
                }
                if (board[0].equals(p2_chip)&&board[3].equals(p2_chip)&&board[6].equals(p2_chip)){
                    System.out.println(p2_tag + " wins"); 
                }
                break;
            }
            case 5 ->{
                if(board[1].equals(p1_chip)&&board[4].equals(p1_chip)&&board[7].equals(p1_chip)){
                    System.out.println(p1_tag + " wins"); 
                }
                if (board[1].equals(p2_chip)&&board[4].equals(p2_chip)&&board[7].equals(p2_chip)){
                    System.out.println(p2_tag + " wins"); 
                }
                break;
            }
            case 6 ->{
                if(board[2].equals(p1_chip)&&board[5].equals(p1_chip)&&board[8].equals(p1_chip)){
                    System.out.println(p1_tag + " wins"); 
                }
                if (board[2].equals(p2_chip)&&board[5].equals(p2_chip)&&board[8].equals(p2_chip)){
                    System.out.println(p2_tag + " wins"); 
                }
                break;
            }
            case 7 ->{
                if(board[0].equals(p1_chip)&&board[4].equals(p1_chip)&&board[8].equals(p1_chip)){
                    System.out.println(p1_tag + " wins"); 
                }
                if (board[0].equals(p2_chip)&&board[4].equals(p2_chip)&&board[8].equals(p2_chip)){
                    System.out.println(p2_tag + " wins"); 
                }
                break;
            }
        }}
        return " ";
    } */

    public static String check_winner(String board[], String p1_chip, String p2_chip) {

        for (int i=1; i < 10; i++){
    
            String line = null;
            String a = new String();
    
            switch(i) {
    
                case 1 ->   {line = board[0] + board[1] + board[2];
                            a = check(board, line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip) || a.equals("draw")){ 
                                return check(board, line, p1_chip, p2_chip);}
                            else break;}
                case 2 ->   {line = board[3] + board[4] + board[5];
                            a = check(board, line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip) || a.equals("draw")){ 
                                return check(board, line, p1_chip, p2_chip);}
                            else break;}
                case 3 ->   {line = board[6] + board[7] + board[8];
                            a = check(board, line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip) || a.equals("draw")){ 
                                return check(board, line, p1_chip, p2_chip);}
                            else break;}
                case 4 ->   {line = board[0] + board[3] + board[6];
                            a = check(board, line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip) || a.equals("draw")){ 
                                return check(board, line, p1_chip, p2_chip);}
                            else break;}
                case 5 ->   {line = board[1] + board[4] + board[7];
                            a = check(board, line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip) || a.equals("draw")){ 
                                return check(board, line, p1_chip, p2_chip);}
                            else break;}
                case 6 ->   {line = board[2] + board[5] + board[8];
                            a = check(board, line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip) || a.equals("draw")){ 
                                return check(board, line, p1_chip, p2_chip);}
                            else break;}
                case 7 ->   {line = board[0] + board[4] + board[8];
                            a = check(board, line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip) || a.equals("draw")){ 
                                return check(board, line, p1_chip, p2_chip);}
                            else break;}
                case 8 ->   {line = board[6] + board[4] + board[2];
                            a = check(board, line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip) || a.equals("draw")){ 
                                return check(board, line, p1_chip, p2_chip);}
                            else break;}
                default ->  {return " ";}
    
            }
        }
        return " ";
    }

    public static String check(String[] rn_board,String s_check, String p1_chip, String p2_chip){

        String a = p1_chip+p1_chip+p1_chip;
        String b = p2_chip+p2_chip+p2_chip;

        if (s_check.equals(a))
            return p1_chip;
    
        else if (s_check.equals(b))
            return p2_chip;
                
        for (int j=0; j< rn_board.length; j++){
        
            if (rn_board[j].equals(" ")) break;
            else if (j == rn_board.length - 1) return "draw";
            }

        return "";
        
    } 

    public static String[] place_chip(String rn_board[], String tag, String chip, String enemy_chip){

        char board[] = string_to_char(rn_board);
        int stage = 0;
        int user_input;
        
        if (tag.equals("CPU")) {
            
            user_input = cpu_ai(one_to_two_dim(board), chip, enemy_chip);

            if (board[user_input] != ' ') {
                do{
                    user_input = random_bounded_nums(0, 9);
                } while(board[user_input] != ' ');
            }

            board[user_input] = chip.charAt(0);
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
                            if(board[user_input-1] == ' ') {board[user_input-1] = chip.charAt(0); stage = 1;}
                            else System.out.println("That position is already taken, please, select another position.");
                        }

                        default -> System.out.println("Invalid input, try again.");
                    }

                } else { System.out.println("Invalid input, try again.");}

            } while(stage == 0);

        }

        return char_to_string(board);
    }

    public static void print_game_board(String board[]){

        System.out.println('\n');
        System.out.println("                         ╔═══╦═══╦═══╗");
        System.out.println("                         ║ " + board[0] + " ║ "+ board[1] + " ║ "+ board[2] + " ║");
        System.out.println("                         ╠═══╬═══╬═══╣");
        System.out.println("                         ║ " + board[3] + " ║ "+ board[4] + " ║ "+ board[5] + " ║");
        System.out.println("                         ╠═══╬═══╬═══╣");
        System.out.println("                         ║ " + board[6] + " ║ "+ board[7] + " ║ "+ board[8] + " ║");
        System.out.println("                         ╚═══╩═══╩═══╝");
        System.out.println();
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

    static class Move 
    { 
	int row, col; 
    }; 

    public static int cpu_ai(char rn_board[][], String cpu_chip, String enemy_chip){

        Move cpu = cpu_move(rn_board, enemy_chip.charAt(0), '0');
        int pos[] = {cpu.row, cpu.col};
        return pos[1]*3 + pos[0];
    }

    public static Boolean moves_left(char board[]) 
    { 
        for (int i = 0; i < board.length; i++) 
           if (board[i] == ' ') 
                return true; 
        return false; 
    } 

    public static char[] string_to_char(String a[]){

        char b[] = new char[a.length];

        for(int i = 0; i < a.length; i++){

            b[i] = a[i].charAt(0);

        }

        return b;
    }

    public static String[] char_to_string(char a[]){

        String b[] = new String[a.length];

        for(int i = 0; i < a.length; i++){

            b[i] = Character.toString(a[i]);

        }

        return b;
    }

    public static char[][] one_to_two_dim(char a[]){

        char array[][] = new char[3][3];

        for(int i=0; i<3;i++)
            for(int j=0;j<3;j++)
                array[i][j] = a[(j*3) + i];
        
        return array;
    }

    public static char[] two_to_one_dim(char a[][]){

        char array[] = new char[9];

        for(int i=0; i<3;i++)
            for(int j=0;j<3;j++)
                array[(j*3) + i] = a[i][j];
        
        return array;
    }

    public static int evaluate(char b[][], char player, char cpu) 
    { 
        
    // Checking for Rows for X or O victory. 
    for (int row = 0; row < 3; row++) 
    { 
        if (b[row][0] == b[row][1] && 
            b[row][1] == b[row][2]) 
        { 
            if (b[row][0] == cpu) 
                return +10; 
            else if (b[row][0] == player) 
                return -10; 
        } 
    } 
  
    // Checking for Columns for X or O victory. 
    for (int col = 0; col < 3; col++) 
    { 
        if (b[0][col] == b[1][col] && 
            b[1][col] == b[2][col]) 
        { 
            if (b[0][col] == cpu) 
                return +10; 
  
            else if (b[0][col] == player) 
                return -10; 
        } 
    } 
  
    // Checking for Diagonals for X or O victory. 
    if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) 
    { 
        if (b[0][0] == cpu) 
            return +10; 
        else if (b[0][0] == player) 
            return -10; 
    } 
  
    if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) 
    { 
        if (b[0][2] == cpu) 
            return +10; 
        else if (b[0][2] == player) 
            return -10; 
    } 
  
    // Else if none of them have won then return 0 
    return 0; 

    } 

    public static int minimax(char board[][], int depth, Boolean isMax, char player, char cpu) { 
        
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
                    if (board[i][j] == ' ') 
                    { 
                        // Make the move 
                        board[i][j] = cpu; 
      
                        // Call minimax recursively and choose 
                        // the maximum value 
                        best = Math.max(best, minimax(board,  
                                        depth + 1, !isMax, player, cpu)); 
      
                        // Undo the move 
                        board[i][j] = ' '; 
                    } 
                } 
            } 
            return best; 
        } 
      
        // If this minimizer's move 
        else
        { 
            int best = 1000; 
      
            // Traverse all cells 
            for (int i = 0; i < 3; i++) 
            { 
                for (int j = 0; j < 3; j++) 
                { 
                    // Check if cell is empty 
                    if (board[i][j] == ' ') 
                    { 
                        // Make the move 
                        board[i][j] = player; 
      
                        // Call minimax recursively and choose 
                        // the minimum value 
                        best = Math.min(best, minimax(board,  
                                        depth + 1, !isMax, player, cpu)); 
      
                        // Undo the move 
                        board[i][j] = ' '; 
                    } 
                } 
            } 
            return best; 
        } 

    } 

    public static Move cpu_move(char board[][], char player, char cpu){

        int bestVal = -1000; 
        Move bestMove = new Move(); 
        bestMove.row = -1; 
        bestMove.col = -1; 
  
        // Traverse all cells, evaluate minimax function  
        // for all empty cells. And return the cell  
        // with optimal value. 
        for (int i = 0; i < 3; i++) 
        { 
            for (int j = 0; j < 3; j++) 
            { 
                // Check if cell is empty 
                if (board[i][j] == ' ') 
                { 
                    // Make the move 
                    board[i][j] = player; 
  
                    // compute evaluation function for this 
                    // move. 
                    int moveVal = minimax(board, 0, false, player, cpu); 
  
                    // Undo the move 
                    board[i][j] = ' '; 
  
                    // If the value of the current move is 
                    // more than the best value, then update 
                    // best/ 
                    if (moveVal > bestVal) 
                    { 
                        bestMove.row = i; 
                        bestMove.col = j; 
                        bestVal = moveVal; 
                    } 
                } 
            } 
        } 
        return bestMove;
    } 
}