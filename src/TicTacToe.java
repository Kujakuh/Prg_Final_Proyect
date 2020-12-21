import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Thread;

public class TicTacToe {

    public static Scanner input = new Scanner(System.in);

    public static ArrayList<winner> scoreboard = new ArrayList<winner>();

    public static int draw_counter = 0;

    // Each of the elements of the scoreboard will have an accesible tag and win counter
    public static class winner {

        private String name = null;
        private int score = 0;
   
        public winner(String name, int score) {

            this.name = name;
            this.score = score;
        }

        public void add_one_win(){
            this.score++;
        }
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


    public static void main(String[] args) {

        // String array to save the actual settings, inicializated with the default ones,
        String settings[] =  {"Player X", "X", "Player O", "O"};

        String[] game_output = new String[2]; // Replay and winner tag

        while(true){ // Infinite loop

            int stage = menu(); // Input missmatches are managed in the menu method
        
            switch(stage){

                // Instructions stage
                case 1: instructions(); break;

                // Set_settings will be returning an String array with the new settings
                // wich will be given to the settings array.
                case 2: settings = set_settings(settings); break;

                // Game stage
                case 3: 

                    // Inicialize the replay value
                    game_output[0] = "1";

                    do{

                        // Game execution and return of the winner player (or draw) and the replay value
                        game_output = game(settings[0], settings[1], settings[2], settings[3]);
                        // Draw, CPU or back to menu exceptions for the scoreboard
                        if(!game_output[1].equals("draw") && !game_output[1].equals("CPU") && !game_output[1].equals("")) 
                            scoreboard = add_to_scoreboard(scoreboard, game_output[1]);
                        // Draw exception management   
                        else if(game_output[1].equals("draw")) draw_counter++;

                    }while(game_output[0].equals("1"));

                    break;

                // Scoreboard stage
                case 4: print_scoreboard(scoreboard, draw_counter); break;

                // Close app
                case 5: System.exit(-1);

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
                         
            System.out.println("                  _ __ ___   ___ _ __  _   _ ");
            System.out.println("                 | '_ ` _ \\ / _ \\ '_ \\| | | |");
            System.out.println("                 | | | | | |  __/ | | | |_| |");
            System.out.println("                 |_| |_| |_|\\___|_| |_|\\__,_|\n\n");

            System.out.println("       __^__        ╔════════════════════╗       __^__             ");
            System.out.println("      ( ___ )       ║  Choose an option  ║      ( ___ )       " +
                        '\n' + "       | / |        ╠════════════════════╣       | / |        " +
                        '\n' + "       | / |        ║  1 = Instructions  ║       | / |        " +
                        '\n' + "       | / |        ║  2 = Settings      ║       | / |        " + 
                        '\n' + "       | / |        ║  3 = Play          ║       | / |        " + 
                        '\n' + "       | / |        ║  4 = Scoreboard    ║       | / |        " + 
                        '\n' + "       |___|        ║  5 = Exit          ║       |___|        ");
            System.out.println("      (_____)       ╚════════════════════╝      (_____)     \n");
            System.out.print("                        You selected: ");
            
            // Avoid data types errors, forbidding the user to give us a non numeric input
            if(input.hasNextInt()) option = input.nextInt(); else option = 0; 
             
            // Check user input to only use the aviable options
            if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5) {
                
                input.nextLine();
                System.out.println("Try again, invalid input");
                sleep(2000);
                
            }
            // Check user input to only use the aviable options
        } while(option != 1 && option !=2 && option !=3 && option !=4 && option != 5);
        
        return option;
    }

    public static ArrayList<winner> add_to_scoreboard(ArrayList<winner> scoreboard, String tag){

        ArrayList<winner> a = scoreboard;
        boolean is_new_tag = false;

        // Check if the scoreboard is not empty
        if(a.size() != 0)
            // If so, check each of the elements
            for(int i = 0; i < a.size(); i++){
                // If it finds the winner tag in the scoreboard, then adds one win
                if(a.get(i).name.equals(tag)){a.get(i).add_one_win(); is_new_tag = false; break;}
                // Else it means, is a not registered winner tag
                else is_new_tag = true;
            }
        else is_new_tag = true; // If not, the tag given is from a new winner

        if(is_new_tag == true) a.add(new winner(tag, 1)); // Add him to the scoreboard if is a new winner

        return a;
    }

    public static void print_scoreboard(ArrayList<winner> scoreboard, int draws){

        int end_check = 1;

        do{

            cls();
            System.out.println("╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║                         SCOREBOARD                          ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n\n");

            // If the scoreboard is empty and there hasn´t been any draws,
            // to avoid exceptions, print a warning.
            if(scoreboard.size() == 0 && draws == 0) System.out.println("There´s no game data registered");

            // If not print the actual draws
            else System.out.println("There has been " + draws + " draws.");

            // Print each of the winners and his wins,
            // checking first if the scoreboard is empty or not
            if(scoreboard.size() != 0)
                for(int i = 0; i < scoreboard.size(); i++){
                    System.out.println("\n " + scoreboard.get(i).name + ": " + scoreboard.get(i).score + " wins.");
                }
            
            // Back to menu code block ----
            System.out.print('\n'+"\n\nType 0 to go back to the menu: ");

            input.nextLine();

            if(input.hasNextInt()) {
                
                end_check = input.nextInt();
                if(end_check != 0) { System.out.println("Invalid input, try again."); sleep(1250);}

            } else { System.out.println("Invalid input, try again."); sleep(1250);}
            // ----------------------------
        } while(end_check != 0);
    }

    public static void instructions() {

        int end_check = 1;

        char[][] inst_board = {
                    {' ',' ', ' ', ' ', ' ', '1', ' ', ' ', ' ', '2', ' ', ' ', ' ', '3', ' ', ' ', ' ', 'C'},
                    {' ',' ', ' ', '╔', '═', '═', '═', '╦', '═', '═', '═', '╦', '═', '═', '═', '╗', ' ', ' '},
                    {' ','1', ' ', '║', ' ', ' ', ' ', '║', ' ', ' ', ' ', '║', ' ', ' ', ' ', '║', ' ', ' '},
                    {' ',' ', ' ', '╠', '═', '═', '═', '╬', '═', '═', '═', '╬', '═', '═', '═', '╣', ' ', ' '},
                    {' ','2', ' ', '║', ' ', ' ', ' ', '║', ' ', ' ', ' ', '║', ' ', ' ', ' ', '║', ' ', ' '},
                    {' ',' ', ' ', '╠', '═', '═', '═', '╬', '═', '═', '═', '╬', '═', '═', '═', '╣', ' ', ' '},
                    {' ','3', ' ', '║', ' ', ' ', ' ', '║', ' ', ' ', ' ', '║', ' ', ' ', ' ', '║', ' ', ' '},
                    {' ',' ', ' ', '╚', '═', '═', '═', '╩', '═', '═', '═', '╩', '═', '═', '═', '╝', ' ', ' '},
                    {' ','R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},};
                    
        char[][] inst_board1 = {
                    {' ','╔', '═', '═', '═', '╦', '═', '═', '═', '╦', '═', '═', '═', '╗'},
                    {' ','║', ' ', 'X', ' ', '║', ' ', ' ', ' ', '║', ' ', 'O', ' ', '║'},
                    {' ','╠', '═', '═', '═', '╬', '═', '═', '═', '╬', '═', '═', '═', '╣'},
                    {' ','║', ' ', ' ', ' ', '║', ' ', 'X', ' ', '║', ' ', 'O', ' ', '║'},
                    {' ','╠', '═', '═', '═', '╬', '═', '═', '═', '╬', '═', '═', '═', '╣'},
                    {' ','║', ' ', 'O', ' ', '║', ' ', ' ', ' ', '║', ' ', 'X', ' ', '║'},
                    {' ','╚', '═', '═', '═', '╩', '═', '═', '═', '╩', '═', '═', '═', '╝'},};

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

            System.out.println("To play you just have to say in which square you want to place your mark (row, column) when your turn comes"+'\n'); 
            System.out.println("This is an example of X's winning the game"+'\n');
            print_matrix(inst_board1);

            System.out.println("\n\nYou can also change your name and chip in settings");
            
            // Back to menu code block ----
            System.out.print('\n'+"Type 0 to go back to the menu: ");

            input.nextLine();

            if(input.hasNextInt()) {
                
                end_check = input.nextInt();
                if(end_check != 0) { System.out.println("Invalid input, try again."); sleep(1250);}

            } else { System.out.println("Invalid input, try again."); sleep(1250);}
            // -----------------------------
            
        } while(end_check != 0);
    }


    public static String[] set_settings(String rn_stg[]){

        // Setting array to save them and return it
        String stg[] = new String[4];
        // Default settings
        String def[] = {"Player X", "X", "Player O", "O"};
        // Get player selection on the options
        String player_selec = new String();
        // Switch var
        int selec = 0;
        
        input.nextLine();

        do{

            cls();

            System.out.println("╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║                         SETTINGS                            ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n\n");

            System.out.println("Actual settings: \n");
            System.out.println("       - Player 1 tag: " + rn_stg[0]);  // Print right now settings
            System.out.println("       - Player 1 chip: " + rn_stg[1]);
            System.out.println("       - Player 2 tag: " + rn_stg[2]);
            System.out.println("       - Player 2 chip: " + rn_stg[3] + "\n\n");
            
            System.out.println("Choose what player settings you want to change,");
            System.out.print("Player 1, Player 2 or both (Type '0' to keep the actual settings, 'D' to establish the default ones): ");

            player_selec = input.nextLine().toLowerCase();

            switch(player_selec){

                case "player 1", "player1", "p1", "1", "1 player", "first", "player one", "one", "first player" -> 
                {selec = 1; break;}
                case "player 2", "player2", "p2", "2", "2 player", "second", "player two", "two", "second player" -> 
                {selec = 2; break;}
                case "both", "player 1 and 2", "player one and two", "one and two", "1 and 2", "both players" -> 
                {selec = 3; break;}
                // Return the actual setting without changing anything
                case "0" -> {return rn_stg;}
                // Return the default settings
                case "d", "D" -> {return def;}
                // Missmatch input exception
                default -> {System.out.print("Invalid input, please try again."); sleep(1250); break;}
            
            }

        } while(selec != 1 && selec != 2 && selec != 3);

        String used_tag = "In use tag, plese type another tag.\n";
        String used_chip = "In use chip, plese type another chip.";

        switch(selec){

            case 1 -> {

                System.out.println("\nPlayer 1: ");
                // Ask player one tag and chip and save it the first 2 positions of the array
                // It checks if wether tag or chip are being used by the other player in the right now settings
                do{stg[0] = get_tag(); if(stg[0].equals(rn_stg[2])) System.out.println(used_tag);} while (stg[0].equals(rn_stg[2]));
                do{stg[1] = get_chip(); if(stg[1].equals(rn_stg[3])) System.out.println(used_chip);} while (stg[1].equals(rn_stg[3]));
                // Keep the player 2 settings
                stg[2] = rn_stg[2];
                stg[3] = rn_stg[3];
                break;
            }

            case 2 -> {

                System.out.println("\nPlayer 2: ");
                // Keep the player 1 settings
                stg[0] = rn_stg[0];
                stg[1] = rn_stg[1];
                // Ask player one tag and chip and save it the last 2 positions of the array
                // It checks if wether tag or chip are being used by the other player in the right now settings
                do{stg[2] = get_tag(); if(stg[2].equals(rn_stg[0])) System.out.println(used_tag);} while (stg[2].equals(rn_stg[0]));
                do{stg[3] = get_chip(); if(stg[3].equals(rn_stg[1])) System.out.println(used_chip);} while (stg[3].equals(rn_stg[1]));
                break;
            }

            case 3 -> {

                System.out.println("\nPlayer 1: ");
                // Ask player one tag and chip and save it the first 2 positions of the array
                // It doesn´t check for used tags and chips because it changes both 
                stg[0] = get_tag();
                stg[1] = get_chip();

                System.out.println("\nPlayer 2: ");
                // Ask player one tag and chip and save it the last 2 positions of the array
                // It checks if wether tag or chip are being used by the other player in the right now settings
                do{stg[2] = get_tag(); if(stg[2].equals(stg[0])) System.out.println(used_tag);} while (stg[2].equals(stg[0]));
                do{stg[3] = get_chip(); if(stg[3].equals(stg[1])) System.out.println(used_chip);} while (stg[3].equals(stg[1]));
                break;
            }

        }

        return stg;
    }

    public static String[] game(String player1_tag, String player1_chip, String player2_tag, String player2_chip){

        // Game output [replay check, winner tag ("draw" in draws and "" when back to menu)]
        String[] out = new String[2];
        String[] game_board = new String[9];
        // String var to check the winner, draw or to keep playing
        String win_check = new String();

        // Inicialice the full board to " "
        game_board = populate_null_1d_array(game_board);

        cls();

        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║                            GAME                             ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝\n\n");

        System.out.println("Select which gamemode do you want to play (Type 0 to go back): \n\n (A) Play 1 vs 1 \n (B) Play vs IA\n");

        char select_option;

        // Conditional loop to avoid missmatch input exceptions
        // Taking into account the lower and upper case
        do{
            select_option = input.next().charAt(0);
		    if ((select_option!='A')&&(select_option!='a')&&(select_option!='B')&&(select_option!='b')&&(select_option!='0')){

                System.out.print("\nInvalid input. \nPlease try again: ");}}

        while ((select_option!='A')&&(select_option!='a')&&(select_option!='B')&&(select_option!='b')&&(select_option!='0'));

        switch(select_option){

            case '0' -> {

                // Replay vaule to 0, to go back to the menu, and null winner tag
                out[0] = "0"; out[1] = "";
                return out;
            }

            case 'b', 'B' -> {

                System.out.print("As what player you want to play? 1 or 2: ");
                char selection;

                // Conditional loop to avoid missmatch input exceptions
                do{

                    selection = input.next().charAt(0);
                    if (selection != '1'&& selection!='2') 
                        System.out.print("\nInvalid input.\nTry again please: ");

                } while(selection != '1'&& selection!='2');

                // Replace the non selected player settings to the CPU ones
                if(selection=='1') {player2_chip = "0"; player2_tag = "CPU";}
                else {player1_chip = "0"; player1_tag = "CPU";} 
            }

            default -> {break;}

        }      

        int status = 0; // End game loop var

        // --------------------------------------------- GAME LOOP -------------------------------------------------------------
        do{
            cls();

            System.out.println("╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║                            GAME                             ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n");

            print_game_board(game_board);
            // Ask player one to place his chip in the board and chek if he has won
            // Save the board with the new chip (The one returned in the game board)
            game_board = place_chip(game_board, player1_tag, player1_chip, player2_chip);
            // Save in win_check wether player 1 or 2 chip, "draw" or null, depending on the actual game board
            win_check = check_winner(game_board, player1_chip, player2_chip);

            // If there has been wether a draw or a winner close the game loop
            if (win_check.equals(player1_chip) || win_check.equals(player2_chip) || win_check.equals("draw"))
                status=1;
    
            // If the game loop hasn´t finished yet then    
            print_game_board(game_board);
            if (status == 0){
                // Ask player one to place his chip in the board and chek if he has won
                // Save the board with the new chip (The one returned in the game board)
                game_board = place_chip(game_board, player2_tag, player2_chip, player1_chip);
                // Save in win_check wether player 1 or 2 chip, "draw" or null, depending on the actual game board
                win_check = check_winner(game_board, player1_chip, player2_chip);

                // If there has been wether a draw or a winner close the game loop
                if (win_check.equals(player1_chip) || win_check.equals(player2_chip) || win_check.equals("draw"))
                    status=1;

                print_game_board(game_board);    
            }

        } while (status==0);
        // -----------------------------------------------------------------------------------------------------------------------

        // If 0 (go back to the menu) hasn´t been selected, position 1 of game output will be the winner tag or draw
        if (win_check.equals(player1_chip)) {System.out.println(player1_tag + " wins."); out[1] = player1_tag;}
        else if (win_check.equals(player2_chip)) {System.out.println(player2_tag + " wins."); out[1] = player2_tag;}
        else if (win_check.equals("draw")) {System.out.println("It's a draw."); out[1] = "draw";}

        sleep(3000);
        System.out.println("\n\n");
        
        System.out.println("Do you want to keep playing? (You will be using the same settings)");
        System.out.println("(If you want to change some settings, please go back to the menu)\n");
        System.out.print("Type 1 to keep playing, 0 to go back to the menu: ");

        // Conditional loop to avoid missmatch input exceptions
        do{
            select_option = input.next().charAt(0);
		    if ((select_option!='1') && (select_option!='0')){

                System.out.print("\nInvalid input. \nPlease try again: ");}}

        while ((select_option!='1')&&(select_option!='0'));        

        // User selection will be asserted to the replay value of game output 
        out[0] = Character.toString(select_option);

        return out;
    }

    public static String get_tag(){

        String tag = new String();

        do {
            System.out.println("How you want to be named as?");

            tag = input.nextLine();

            if(tag.toUpperCase().equals("CPU")) 
                System.out.println("CPU is a reserved name, please type another name.\n");

        } while(tag.toUpperCase().equals("CPU"));    

        return tag;
    }

    public static String get_chip(){
        
        String chip = new String();
        int stg = 0;

        do{

            System.out.print("\nSelect a chip of the following ones: X,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,Y,Z,+,*,-,@,1: ");

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

        for (int i = 1; i < 9; i++){
    
            String line = null; // Save the charaters in a winner line, colunm and diag
            String a = new String(); 
    
            switch(i) { // Get the 3 values of a winner line, check if theres a winner in that line
                        // If theres a winner, it returns the winner chip, if not, check the next winner line
    
                case 1 ->   {line = board[0] + board[1] + board[2];
                            a = check(line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip)) return a;
                            else break;}

                case 2 ->   {line = board[3] + board[4] + board[5];
                            a = check(line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip)) return a; 
                            else break;}

                case 3 ->   {line = board[6] + board[7] + board[8];
                            a = check(line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip)) return a;
                            else break;}

                case 4 ->   {line = board[0] + board[3] + board[6];
                            a = check(line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip)) return a;
                            else break;}

                case 5 ->   {line = board[1] + board[4] + board[7];
                            a = check(line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip)) return a;
                            else break;}

                case 6 ->   {line = board[2] + board[5] + board[8];
                            a = check(line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip)) return a;
                            else break;}

                case 7 ->   {line = board[0] + board[4] + board[8];
                            a = check(line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip)) return a;
                            else break;}

                case 8 ->   {line = board[6] + board[4] + board[2];
                            a = check(line, p1_chip, p2_chip);
                            if(a.equals(p1_chip) || a.equals(p2_chip)) return a;
                            else break;}
            }
        }

        // Check if theres a draw, if so, return "draw" else return " "
        for (int j=0; j < board.length; j++){
        
            if (board[j].equals(" ")) return " ";
            else if (j == board.length - 1) return "draw";
        }
        
        // If nothing happens return " "
        return " ";
    }

    public static String check(String s_check, String p1_chip, String p2_chip){

        // Check if the line given is equal to player 1 or 2 
        // chips winning
        String a = p1_chip + p1_chip + p1_chip;
        String b = p2_chip + p2_chip + p2_chip;

        // If so, return his chip
        if (s_check.equals(a))
            return p1_chip;
    
        else if (s_check.equals(b))
            return p2_chip;

        // Else return " "
        return " ";
    } 

    public static String[] place_chip(String rn_board[], String tag, String chip, String enemy_chip){

        // Change the string array to char array to make it is to work with it
        char board[] = string_to_char(rn_board);
        // Get user keyboard input
        String user_input = new String();
        // Int array to save the coords given by the user
        int[] coords = new int[2];
        // End loop var
        int stage = 0;
        // Var to save the coords given traduced to a number from 1 to 9
        int pos;

        // If you are playing against the ia
        if (tag.equals("CPU")) {
            
            // Analice board and generate the best move for the ia
            pos = cpu_ai(one_to_two_dim(board), chip, enemy_chip);

            // Just to avoid errors, if that position is taken, generate a random position
            if (board[pos] != ' ') {
                do{
                    pos = random_bounded_nums(0, 9);
                } while(board[pos] != ' ');
            }

            // Place the ia chip
            board[pos] = chip.charAt(0);

            // Transform the number generated to coords and print the move done by the ia
            int rows = 1 + pos / 3;
            int columns = 1 + pos % 3;

            System.out.println("The CPU placed his chip in the following position: (" + rows + ", " + columns + ")\n");
            sleep(1750);

            return char_to_string(board);
        } 

        // If you playing 1 vs 1
        else {

            System.out.print("\nIt´s your turn "+ tag +". Type where you wanna place your token.\nCoordinates must be given in the following format (row,column): ");
            input.nextLine();
        
            do {

                // Ask the user to place the chip
                try {

                    // Get user input
                    user_input = input.next();
                    // Delete the commas and save the coords in a temporal array
                    String[] temp = user_input.split(",");

                    // Transform the coords to integer and make allow us to use it as indexes of the array
                    coords[0] = Integer.parseInt(temp[0]) - 1;
                    coords[1] = Integer.parseInt(temp[1]) - 1;
                    
                    // If the coords are from 1 to 3 then traduce it to numbers from 1 to 9 tu use them, if not return -1 to
                    // get to an invalid input in the following switch
                    if((coords[0] == 0 || coords[0] == 1 || coords[0] == 2) && (coords[1] == 0 || coords[1] == 1 || coords[1] == 2))
                        pos = coords[0] * 3 + coords[1];
                    else
                        pos = - 1;

                    // If pos is from 0 to 8, (1 to 9) then place the chip
                    switch(pos){

                        case 0, 1, 2, 3, 4, 5, 6, 7, 8 -> 
                        {
                            if(board[pos] == ' ') {board[pos] = chip.charAt(0); stage = 1;}
                            else {System.out.print("\nThat position is already taken, please, select another position: "); input.nextLine();}
                        }

                        // If not, send a warning and repeat the process
                        default -> { System.out.print("\nInvalid input, try again: "); input.nextLine();}
                    }

                }
                
                // If thers is a miss match input error due to letter in the input 
                // or a non valid transformation, generating a number out of the bounds 
                // of the array, send a warning and try again

                catch (java.lang.NumberFormatException | java.lang.ArrayIndexOutOfBoundsException e) { 
                    
                    System.out.print("\nInvalid input, try again: ");
                    input.nextLine();
                }

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
    //----------------------------- AI -----------------------------------------------------------------------------------------
    
    // https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/

    static class Move 
    { 
	int row, col; 
    }; 

    public static int cpu_ai(char rn_board[][], String cpu_chip, String enemy_chip){

        // Get the best move in the actual game board and return it as a number from 1 to 9
        Move cpu = cpu_move(rn_board, enemy_chip.charAt(0), '0');
        return cpu.col * 3 + cpu.row;
    }

    public static Boolean moves_left(char board[]) 
    { 
        // Check if its posible to make more moves
        for (int i = 0; i < board.length; i++) 
           if (board[i] == ' ') 
                return true; 
        return false; 
    } 

    public static char[] string_to_char(String a[]){

        // Return the string array given as a char array
        char b[] = new char[a.length];
        for(int i = 0; i < a.length; i++){
            b[i] = a[i].charAt(0);
        }

        return b;
    }

    public static String[] char_to_string(char a[]){

        // Return the char array given as a String array
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
        // Check if any of the players has won or theres a draw
        // if so, return +10 in maximizer win, and -10 in minimizer win
        String a[] = char_to_string(two_to_one_dim(b));
        String l = check_winner(a, Character.toString(cpu), Character.toString(player));

        if(l.charAt(0) == cpu) return +10;
        else if(l.charAt(0) == player) return -10;
        else return 0;

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
                    int moveVal = minimax(board, 0, true, player, cpu); 
  
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