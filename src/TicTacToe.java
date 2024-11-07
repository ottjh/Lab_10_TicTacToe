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
                System.out.print("\n\nPlayer " + player + ", it is your move.");
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
                        System.out.println("\nPlayer " + player + " won the game.\n");
                        display();
                    }
                }

                //Now to test if the game is a tie
                if (moveCount >= 7 && !gameOver) // !gameOver to ensure tie message doesn't display after winning with 7 or more moves.
                {
                    gameOver = isTie();
                    if (gameOver)
                    {
                        System.out.println("\nIt's a tie. Game over.\n");
                        display();

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

            done = SafeInput.getYNConfirm(in, "\nDo you want to quit? [Y/N]");
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
        if (isColTie() && isRowTie() && isDiagonalTie())
        {
            return true;
        }
        return false;
    }

    private static boolean isColTie()
    {
        int tieColCount = 0;
        for(int col = 0; col < COLS; col++)
        {
            if((board[0][col].equals("X") || board[1][col].equals("X") || board[2][col].equals("X")) && (board[0][col].equals("O") || board[1][col].equals("O") || board[2][col].equals("O")))
            {
                tieColCount++;
                if (tieColCount ==3)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isRowTie()
    {
        int tieRowCount = 0;
        for(int row = 0; row < COLS; row++)
        {
            if((board[0][row].equals("X") || board[1][row].equals("X") || board[2][row].equals("X")) && (board[0][row].equals("O") || board[1][row].equals("O") || board[2][row].equals("O")))
            {
                tieRowCount++;
                if (tieRowCount ==3)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isDiagonalTie()
    {
        if ((board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) && (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")) && ((board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) && ((board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O")))))
        {
            //I have a feeling I just committed a crime against programming, but I am not sure how to get around it.
            //But the conditions basically say if both players have moves in both the diagonals, it's impossible to win with them.
            return true;
        }
        return false;
    }
}