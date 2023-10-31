/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    private static final int GRID_SIZE = 3;
    private static final int PLAYER_X = 0;
    private static final int PLAYER_O = 1;
    private static final int EMPTY = -1;
    private static final int TIE = -1;

    private final int[][] board = new int[GRID_SIZE][GRID_SIZE]; //initializing "board" as a 3x3 2D array.
    private int currentPlayer = PLAYER_X; //Integer to keep track of what players turn it is, uses 0 and 1 to toggle between players.
    private boolean gameOver = false; //Boolean checking whether the game has ended (due to win or draw).

    public GameBoard() {
        clearBoard();
    }

    // INTERFACE IMPLEMENTATION
    @Override
    public int getNextPlayer() //returns int value representing current player.
    {
        return currentPlayer;
    }

    @Override
    public boolean play(int col, int row) //attempts to place a marker on the board for the current player at the specified row and col.
    {
        if (!gameOver && board[row][col] == EMPTY) //Ensures that a move is only made if the game is not over and the chosen spot is empty.
        {
            board[row][col] = currentPlayer; //Updates the board array, assigning it the value of the current player (0 or 1).
            gameOver = checkGameOver(); //use helper method to check if the game is over after the last move and updates the gameOver variable.
            if (!gameOver) swapPlayer(); //If the game is not over, toggles the current player.
            return true;
        }
        return false;
    }

    @Override
    public boolean isGameOver()  //Simply returns boolean value of "gameOver".
    {
        return gameOver;
    }

    @Override
    public int getWinner()
    //Returns the current player as the winner if the game is over and the board is not full (indicating a win and not a draw).
    //If the game is a draw or is still ongoing, it returns -1.
    {
        if (isGameOver())
        {
            return currentPlayer;
        }

        return -1;
    }

    @Override
    public void newGame()  //clears the board and resets variables "currentPlayer" and "gameOver".
    {
        clearBoard(); // clears board
        currentPlayer = PLAYER_X; //resetting currentPlayer.
        gameOver = false;  //resetting gameOver.
    }

    // HELPER METHODS
    private boolean checkGameOver() //helper method that checks for win or draw conditions, returns boolean.
    {
        for (int i = 0; i < GRID_SIZE; i++)
        //checks rows and columns for 3 of same value on a line.
        {
            // Check rows
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2])
            {
                return true;
            }

            // Check columns
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i])
            {
                return true;
            }

            //checks diagonals for 3 of same value on a line.
            if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2])
            {
                return true;
            }
            if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0])
            {
                return true;
            }
        }

        //checks if the board is full.
        if (isBoardFull())
        {
            currentPlayer = TIE;
            return true;
        }

        return false;
    }

    private boolean isBoardFull() //helper method, checks if all cells are occupied.
    {
        for (int r = 0; r < GRID_SIZE; r++)
        {
            for(int c = 0; c < GRID_SIZE; c++)
            {
                if (board[r][c] == EMPTY) //if a specific cell is empty.
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void swapPlayer() {
        if (currentPlayer == PLAYER_X) {
            currentPlayer = PLAYER_O;
        } else {
            currentPlayer = PLAYER_X;
        }
    }

    private void clearBoard() {
        for (int r = 0; r < GRID_SIZE; r++)
        {
            for (int c = 0; c < GRID_SIZE; c++)
            {
                board[r][c] = EMPTY; //clearing board array.
            }
        }
    }

}
