import java.util.*;

public class FourInALine {
    static Scanner kb = new Scanner(System.in);
    public static void main(String[] args) {
        String kbInput;
        int input;
        int player = 1;
        WinCondition winCon = new WinCondition();
        PlayTable table = new PlayTable();
        PrintTable prTable = new PrintTable();

        do {
            System.out.print("Enter height of the table (4-10):");
            int tableHeight = kb.nextInt();
            table.setHeight(tableHeight);
            if (table.checkTableHeight())
                continue;

            System.out.print("Enter width of the table (5-10):");
            int tableWidth = kb.nextInt();
            table.setWidth(tableWidth);
            if (table.checkTableWidth())
                continue;

            System.out.println();

            int[][] playTable = new int[tableHeight][tableWidth];
            int moves = tableWidth * tableHeight;
            int[] inputCount = new int[tableWidth];

            String[][] printTable = new String[tableHeight][tableWidth];
            for (int i = 0; i < tableHeight; i++) {
                for (int j = 0; j < tableWidth; j++)
                    printTable[i][j] = " ";
            }

            while (true) {
                kbInput = promptPlayerInput(tableWidth, player);

                if (kbInput.equals("X") || kbInput.equals("x")) {
                    if (playAgain()) {
                        break;
                    } else
                        return;
                }
                input = Integer.parseInt(kbInput);

                if (table.isInputValid(input))
                    continue;

                if (table.isColumnFull(inputCount, input))
                    continue;

                playTable[inputCount[input]][input] = player;
                if (player == 1) {
                    printTable[inputCount[input]][input] = "O";
                } else if (player == 2) {
                    printTable[inputCount[input]][input] = "X";
                }
                inputCount[input]++;
                prTable.print(tableHeight, tableWidth, printTable);

                if (winCon.WinCondition(tableHeight, tableWidth, playTable, player)) {
                    System.out.println("Player " + player + " won.");
                    if (playAgain()) {
                        break;
                    } else
                        return;
                }

                if (player == 2)
                    player = 1;
                else
                    player++;

                --moves;
                if (moves == 0) {
                    System.out.println("Draw.");
                    System.out.println("Play again? 'Y' or 'N':");
                    if (playAgain()) {
                        break;
                    } else
                        return;
                }
            }
        } while (true);
    }

    public static boolean playAgain() {
        while (true) {
            System.out.println("\nPlay again? 'Y' or 'N':");
            String goAgain = kb.next();
            if (goAgain.equals("Y") || goAgain.equals("y"))
                return true;
            else if (goAgain.equals("N") || goAgain.equals("n")) {
                System.out.println("Bye Bye!");
                return false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    public static String promptPlayerInput(int tableWidth, int player) {
        System.out.print("Player " + player + " type a column (0-" +
                (tableWidth - 1) + ") or type 'x' to quit current game:");
        String kbInput = kb.next();
        return kbInput;
    }
}
