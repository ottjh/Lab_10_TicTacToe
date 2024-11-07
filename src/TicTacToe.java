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
        boolean playing = true;
        boolean validMove = false;
        int rowPlay;
        int colPlay;

        do {
            clearBoard();
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
                board[rowPlay][colPlay] = player;

                //This should alternate which player is acting.
                if (player == "X")
                {
                    player = "O";
                }
                else
                {
                    player = "X";
                }
            } while (playing);

            //Here we are resetting variables for a replay and
            playing = true;
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
}