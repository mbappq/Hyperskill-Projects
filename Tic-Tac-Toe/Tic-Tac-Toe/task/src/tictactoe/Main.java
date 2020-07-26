package tictactoe;

import java.util.Scanner;

public class Main {
    char[] layout;
    char currentToken;

    Main() {
        layout = new char[9];
        for (int i = 0; i < layout.length; i++) {
            layout[i] = ' ';
        }
        currentToken = 'X';
    }

    public static void main(String[] args) {
        // write your code here
        Main game = new Main();
        Scanner scanner = new Scanner(System.in);
        printBoard(game);

        int column, row;
        while (true) {
            System.out.print("Enter the coordinates: ");
            try {
                column = scanner.nextInt();
                row = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (column < 1 || column > 3 || row < 1 || row > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            int cell = 9 - (row * 3) + (column - 1);
            if (game.layout[cell] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            game.layout[cell] = game.currentToken;
            printBoard(game);
            if (game.checkWin(game.currentToken)) {
                System.out.println(game.currentToken + " wins");
                break;
            } else if (game.isFull()) {
                System.out.println("Draw");
                break;
            }
            switch (game.currentToken) {
                case 'X': game.currentToken = 'O';
                    break;
                case 'O': game.currentToken = 'X';
            }

            /*if (game.checkImpossible()) {
                System.out.println("Impossible");
            } else if (game.checkWin('O')) {
                System.out.println("O wins");
            } else if (game.checkWin('X')) {
                System.out.println("X wins");
            } else if (game.isFull()) {
                System.out.println("Draw");
            } else {
                System.out.println("Game not finished");
            }*/

        }
    }

    private static void printBoard(Main game) {
        System.out.println("---------");
        System.out.println("| " + game.layout[0] + " " + game.layout[1] + " " + game.layout[2] + " |");
        System.out.println("| " + game.layout[3] + " " + game.layout[4] + " " + game.layout[5] + " |");
        System.out.println("| " + game.layout[6] + " " + game.layout[7] + " " + game.layout[8] + " |");
        System.out.println("---------");
    }

    private boolean checkImpossible() {
        int countO = 0;
        int countX = 0;
        for (char cell : layout) {
            if (cell == 'O') countO++;
            else if (cell == 'X') countX++;
        }
        return (checkWin('O') && checkWin('X')) || Math.abs(countO - countX) >= 2;
    }

    private boolean checkWin(char token) {
        return checkRows(token) || checkColumns(token)
                || (layout[4] == token && (layout[0] == layout[4] && layout[4] == layout[8]
                || layout[2] == layout[4] && layout[4] == layout[6]));
    }

    private boolean checkRows(char token) {
        for (int i = 0; i < 3; i++) {
            int index = i * 3;
            if (layout[index] == token && layout[index] == layout[index + 1]
                    && layout[index + 1] == layout[index + 2]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char token) {
        for (int i = 0; i < 3; i++) {
            if (layout[i] == token && layout[i] == layout[i + 3]
                    && layout[i + 3] == layout[i + 6]) {
                return true;
            }
        }
        return false;
    }

    private boolean isFull() {
        for (char cell : layout) {
            if (cell == ' ') return false;
        }
        return true;
    }
}
