
    import java.util.Scanner;

    public class XOGame {
        private static final int BOARD_SIZE = 3;
        private static final char EMPTY = '-';
        private static final char PLAYER_X = 'X';
        private static final char PLAYER_O = 'O';
        private final char[][] board;
        private char currentPlayer;

        public XOGame() {
            board = new char[BOARD_SIZE][BOARD_SIZE];
            currentPlayer = PLAYER_X;
            initializeBoard();
        }

        private void initializeBoard() {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    board[i][j] = EMPTY;
                }
            }
        }

        public void playGame() {
            Scanner scanner = new Scanner(System.in);
            boolean gameRunning = true;
            while (gameRunning) {
                displayBoard();
                int row, col;
                do {
                    System.out.print("Player " + currentPlayer + ", enter row (0-2): ");
                    row = scanner.nextInt();
                    System.out.print("Player " + currentPlayer + ", enter column (0-2): ");
                    col = scanner.nextInt();
                } while (!isValidMove(row, col));
                board[row][col] = currentPlayer;
                if (isGameOver()) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameRunning = false;
                } else if (isBoardFull()) {
                    displayBoard();
                    System.out.println("It's a draw!");
                    gameRunning = false;
                } else {
                    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                }
            }
            scanner.close();
        }

        private boolean isValidMove(int row, int col) {
            if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
                System.out.println("Invalid move! Row and column must be between 0 and 2.");
                return false;
            }
            if (board[row][col] != EMPTY) {
                System.out.println("Invalid move! That cell is already occupied.");
                return false;
            }
            return true;
        }

        private boolean isGameOver() {
            return checkRows() || checkCols() || checkDiagonals();
        }

        private boolean checkRows() {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkCols() {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkDiagonals() {
            return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                    (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
        }

        private boolean isBoardFull() {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j] == EMPTY) {
                        return false;
                    }
                }
            }
            return true;
        }

        private void displayBoard() {
            System.out.println("-------------");
            for (int i = 0; i < BOARD_SIZE; i++) {
                System.out.print("| ");
                for (int j = 0; j < BOARD_SIZE; j++) {
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println("\n-------------");
            }
        }

        public static void main(String[] args) {
            XOGame game = new XOGame();
            game.playGame();
        }
    }

