package gr.aueb.cf.projects;

import java.util.Scanner;

public class Project04XOGame {

    public static Scanner in = new Scanner(System.in);
    public static char[][] grid = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};

    public static void main(String[] args) {
        boolean game = true;

        while (game) {
            printBoard();
            game = play();
        }
    }

    public static boolean play() {
        int row;
        int col;
        int player = 2;

        while (true) {
           if (player == 2) {
               player = 1;
           } else player = 2;

           System.out.println("Its Player's " + player + " turn!");

           System.out.println("Please choose a row: ");
           row = getChoice();
           System.out.println("Please choose a column: ");
           col = getChoice();

           while (grid[row][col] != ' ') {
               System.out.println("Please choose an empty block: ");
               System.out.println("Please choose a row: ");
               row = getChoice();
               System.out.println("Please choose a column: ");
               col = getChoice();
           }

           grid[row][col] = (player == 1) ? 'O' : 'X';
           printBoard();


           if (checkForWinner()) {
               System.out.println("Player " + player + " won!");
               return playAgain();
           } else if (boardIsFull()) {                          // check for draw
               System.out.println("Its a Draw!");
               return playAgain();
           }
        }
    }

    public static void  printBoard(){
        System.out.println("  +---+---+---+");
        System.out.println(2 +  " | " + grid[2][0] +  " | " + grid[2][1] + " | " + grid[2][2] + " |");
        System.out.println("  +---+---+---+");
        System.out.println(1 +  " | " + grid[1][0] +  " | " + grid[1][1] + " | " + grid[1][2] + " |");
        System.out.println("  +---+---+---+");
        System.out.println(0 +  " | " + grid[0][0] +  " | " + grid[0][1] + " | " + grid[0][2] + " |");
        System.out.println("  +---+---+---+");
        System.out.println("    0   1   2  ");
    }

    public static int getChoice() {
        int choice;

        while (true) {
            if (in.hasNextInt()) {
                choice = in.nextInt();
                if ((choice >= 0) && (choice <= 2)) {
                    return choice;
                } else System.out.println("Please enter a number between 0 and 2: ");
            } else {
                System.out.println("Please enter an integer: ");
                in.nextLine();
            }
        }
    }

    public static boolean checkForWinner() {
         return (grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2] && grid[0][2] != ' ') ||
                (grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2] && grid[1][2] != ' ') ||
                (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2] && grid[2][2] != ' ') ||

                (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2] && grid[0][2] != ' ') ||
                (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] != ' ') ||

                (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0] && grid[2][0] != ' ') ||
                (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1] && grid[2][1] != ' ') ||
                (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2] && grid[2][2] != ' ');
    }

    public static boolean boardIsFull() {
        int emptyBlocks = 0;

        for (char[] chars : grid) {
            for (char ch : chars) {
                if (ch == ' ') emptyBlocks++;
            }
        }
        return emptyBlocks == 0;
    }

    public static boolean playAgain() {
        System.out.println("Play again? y/n");
        while (true) {
            String choice = in.nextLine();
            if (choice.matches("[yY]")) {

                for (int i = 0; i < grid.length; i++) {          //reset board
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i][j] = ' ';
                    }
                }
                return true;
            } else {
                if (choice.matches("[nN]")) {
                    System.out.println("Thank you for playing! Bye");
                    return false;
                }
            }
        }

    }
}