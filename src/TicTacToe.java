/*
Programming final proyect developed by Mónica Treviño Fernández and Álvaro Vos Graciá.
This is a simple command prompt based TicTacToe with some advanced settings.
*/

import java.util.*;
import java.lang.Thread;

public class TicTacToe {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Para hacer el menú
        menu();
    }

    public static int menu() {
        Scanner menu = new Scanner(System.in);
        int option;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Choose an option:" + '\n' + "1 = Instructions" + '\n' + "2 = Settings" + '\n'
                    + "3 = Play" + '\n' + "4 = Exit");
            
            if(menu.hasNextInt()) option = menu.nextInt(); else option = 0;
             
            if (option != 1 && option != 2 && option != 3 && option != 4) {
                menu.nextLine();
                System.out.println("Try again, invalid input");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        } while(option != 1 && option !=2 && option !=3 && option !=4);
        menu.close();
        return option;
    }
}


