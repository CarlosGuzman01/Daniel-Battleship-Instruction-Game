import java.util.Scanner;
import java.util.InputMismatchException;

public class BattleshipGame {
    public static void main(String[] args) {

        char[][] board = createBoard();
        placeShips(board);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to BattleShip! " + "Players take turns guessing the location of the ships.");
        System.out.println("Enter your guess as row and column numbers" + "from 1 to 5 with space between.");

        System.out.println("Enter name for Player 1: ");
        String player1Name = scanner.nextLine();

        System.out.println("Enter name for Player 2: ");
        String player2Name = scanner.nextLine();

        boolean player1Turn = true;
        int player1Score = 0;
        int player2Score = 0;
        int shipsToSink = 6;

        while(true){
            displayBoard(board);
            System.out.println((player1Turn ? player1Name : player2Name) + ", enter your guess (row and column): ");

            int row = -1, col = -1;
            boolean validInput = false;

            while(!validInput){
                try{
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;
                    validInput = true;
                } catch (InputMismatchException e){
                    System.out.println("Invalid input." + " Please enter numberic coordinates within the range of 1 to 5.");
                    scanner.next(); //clear invalid input
                }
            }

            if(row < 0 || row >= 5 || col < 0 || col >= 5){
                System.out.println("Invalid guess." + "Please enter coordinates within the board game range (1 to 5).");

                continue;
            }


            if(board[row][col] == 'H' || board[row][col] == 'M'){
                System.out.println("This spot has been already guessed. Try another oen.");

                continue;
            }

            if(guess(board, row, col)){
                if(player1Turn){
                    player1Score++;
                    System.out.println(player1Name + " hits!");
                }else{
                    player2Score++;
                    System.out.println(player2Name + " hits!");
                }
            }else{
                System.out.println((player1Turn ? player1Name : player2Name) + " misses.");
            }

            if(player1Score >= shipsToSink){
                System.out.println("Good job, " + player1Name + "... Captain " + player1Name + " Sparrow! You won!");

                System.out.println(player2Name + ", you lost the battle, but not the war! " + "Try again next time!");

                break;

            }else if(player2Score >= shipsToSink){
                System.out.println("Good job, " + player2Name + "... Captain" + player2Name + " Sparrow! You won!");

                System.out.println(player1Name + ", you lost the battle, but not the war!" + "Try again next time!");

                break;
            }

            player1Turn = !player1Turn;
        
        }

        scanner.close();
        
    }

    private static char[][] createBoard(){
        char[][] board = new char[5][5];

        for(int i = 0; i < 5; i++){
            for(int j  = 0; j < 5; j++){
                board[i][j] = '-';
            }
        }

        return board;
    }

    private static void placeShips(char[][] board){

        for(int i = 0; i < 11; i++){
            int shipRow;
            int shipCol;

            do{
                shipRow = (int) (Math.random() * 5);
                shipCol = (int) (Math.random() * 5);
            
            }while(board[shipRow][shipCol] == 'S');
            board[shipRow][shipCol] = 'S';
        }

    }

    private static void displayBoard(char[][] board){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(board[i][j] == 'S'){
                    System.out.print("- ");
                }else{
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

    private static boolean guess(char[][] board, int row, int col){
        
        if(board[row][col] == 'S'){
            board[row][col] = 'H';
            return true;
        } else{
            board[row][col] = 'M';
            return false;
        }
    }

    
}