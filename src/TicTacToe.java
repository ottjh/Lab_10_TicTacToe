import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TicTacToe
{
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String player = "X";
        boolean done = false;
        boolean gameOver = false;
        boolean validMove = false;
        int rowPlay;
        int colPlay;
        int moveCount = 0;

        do {
            //Need to reset things for a new game at the start
            clearBoard();
            moveCount = 0;

            do
            {
                validMove = false;
                display();
                System.out.print("\nPlayer " + player + ", it is your move.");
                //Now move validity check loop
                do
                {
                    rowPlay = (SafeInput.getRangedInt(in, "Please input the row of your move",  1, 3) - 1);
                    colPlay = (SafeInput.getRangedInt(in, "Please input the column of your move",  1, 3) - 1);
                    validMove = isValidMove(rowPlay, colPlay);
                    if (!validMove)
                        System.out.println("Invalid move");
                } while (!validMove);
                //Now assign the valid moves and increment the move count.
                board[rowPlay][colPlay] = player;
                moveCount++;

                //Now we need to test if the game is over after 5 turns have gone by.
                if (moveCount >= 5)
                {
                    gameOver = isWin(player);
                    if (gameOver)
                    {
                        System.out.println("Player " + player + " won the game");
                    }
                }

                //Now to test if the game is a tie
                if (moveCount >= 7)
                {
                    gameOver = isTie();
                    if (gameOver)
                    {
                        System.out.println("It's a tie. Game over.");
                    }
                }

                //This should alternate which player is acting.
                if (player == "X")
                {
                    player = "O";
                }
                else
                {
                    player = "X";
                }
            } while (!gameOver);

            //Here we are resetting variables for a replay and
            gameOver = false;
            player = "X";

            done = SafeInput.getYNConfirm(in, "Do you want to quit? [Y/N]");
        } while (!done);
    }

    //This is the clear board loop that assigns an empty space value to all available spots
    //on the board. This resets everything after a completion.
    private static void clearBoard()
    {
        for(int row = 0; row < ROWS; row++)
        {
            for(int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    //This is hopefully a method to print out the current state ov the board.
    //I am not sure the current setup works, but I will test it later.
    //It does have a divider on the trailing number. I might be able to get rid of it with an if
    //Maybe: if (col == 0 || col == 1)
    //print " | "
    //but I would need to get it all working first to even consider testing that.
    private static void display()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                System.out.print(board[row][col]);
                if (col == 0 || col == 1)
                    System.out.print(" | ");
            }
            if (row == 0 || row == 1)
                System.out.println("\n=========");
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if (board[row][col].equals(" "))
        {
            retVal = true;
        }
        return retVal;
    }

    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player)
    {
        for(int col = 0; col < COLS; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for(int row = 0; row < ROWS; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        if ((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || ((board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))))
        {
            return true;
        }
        return false;
    }

    private static boolean isTie()
    {
        if ()
    }
}