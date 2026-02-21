import java.util.*;
public class TicTacToe {
    public static void main(String args[])  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player 1 --> Select Move ('X' or 'O')");
        String player1 = sc.next();
        if(player1.length() != 1 || (player1.charAt(0) != 'X' && player1.charAt(0) != 'O')) {
            System.out.println("Invalid option");
        } 
        else {
            System.out.println("Player 1 --> " + player1);
            char player2 = player1.charAt(0) == 'X' ? 'O' : 'X';
            System.out.println("Player 2 --> " + player2 );
            
            Board b = new Board();
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    b.arr[i][j] = '_';
                }
            }
            b.printBoard();

            int count = 9;
            boolean invalid = false;
            
            int i = 0;
            while(count > 0) {
                int pos = 00;
                if(i == 0) {
                    System.out.print("Player 1 (" + player1 + ") Enter position --> ");
                    pos = sc.nextInt();
                    invalid = b.move(pos, player1.charAt(0), invalid);
                }
                else {
                    System.out.print("Player 2 (" + player2 + ") Enter position --> ");
                    pos = sc.nextInt();
                    invalid = b.move(pos, player2, invalid);
                }
                if(!invalid) {
                    i = Math.abs(i - 1);
                    count--;
                }
                else {
                    invalid = false;
                    continue;
                }


                b.printBoard();
                boolean win1 = b.check(player1.charAt(0));
                boolean win2 = b.check(player2);

                if(win1) {
                    System.out.println("PLAYER 1 (" + player1 + ") WINS !");
                    break;
                }
                if(win2) {
                    System.out.println("PLAYER 2 (" + player2 + ") WINS !");
                    break;
                }

            }
        }
        sc.close();
    }

}

class Board {
    char arr[][] = new char[3][3];
    
    public boolean move(int pos, char sign, boolean invalid) {
        int j = pos % 10;
        int i = pos / 10;
        if(i < 0 || j < 0 || i >= 3 || j >= 3 || arr[i][j] != '_') {
            System.out.println("Invalid move !!!");
            return invalid = true;
        }
        else {
            arr[i][j] = sign;
        }
        return invalid;
    }
    public void printBoard() {
        System.out.println("-------------------");
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(arr[i][j] == '_') System.out.print(i + "" + j);
                else System.out.print(arr[i][j]+ " ");
                System.out.print("  |  ");
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }
    public boolean check(char sign) {
        for(int i=0; i<3; i++) {
            int count = 0;
            for(int j=0; j<3; j++) {
                if(arr[i][j] == sign) {
                    count += 1;
                }
                else count = 0;
            }
            if(count == 3) return true;
        }

        for(int i=0; i<3; i++) {
            int count = 0;
            for(int j=0; j<3; j++) {
                if(arr[j][i] == sign) {
                    count += 1;
                }
                else count = 0;
            }
            if(count == 3) return true;
        }
        int count = 0;
        for(int i=0; i<3; i++) {
            if(arr[i][i] == sign) {
                count += 1;
            }
            else count = 0;
        }
        if(count == 3) return true;
        count = 0;
        int j = 0;
        for(int i=2; i>=0; i--) {
            if(arr[i][j++] == sign) {
                count += 1;
            }
            else count = 0;
        }
        if(count == 3) return true;

        return false;

    }
}