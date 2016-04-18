package TicTacToe;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GameEngine {
    public static Scanner in = new Scanner(System.in);
    public final int size = 3;

    public static void Welcome() {
        System.out.println("Hello, I'm your personal intelligent guide! You're probably thinking who made me...");
        System.out.println("I can tell you that humans from DCC are involved");
        System.out.println();
    }

    public static int[] Menu() {

        int inputs[] = new int[3];
        System.out.println("-----------Menu-----------");
        System.out.println();
        System.out.println("Choose the algorithm to play TicTacToe :");
        System.out.println("1- MinMax");
        System.out.println("2- Alphabeta");
        inputs[0] = in.nextInt();
        while(inputs[0]!=1 && inputs[0]!=2)
        {
            System.out.println("You have to choose a valid option. If you would like to play with MiniMax press 1, otherwise press 2");
            inputs[0]=in.nextInt();
        }
        System.out.println("Would you like to play first? If so press 1 otherwise press 2");
        inputs[1] = in.nextInt();
        while(inputs[1]!=1 && inputs[1]!=2)
        {
            System.out.println("You have to choose a valid option. If you would like to play first press 1, otherwise press 2");
            inputs[1]=in.nextInt();
        }
        System.out.println("Would you like to play with the X or with the O?");
        char c = in.next().charAt(0);
        while ((c!='X'&& c!='x' && c!='O' && c!='o'))
        {
            System.out.println("You have to choose a valid option. If you would like to play with the X or with the O?");
            c=in.next().charAt(0);
        }
        int x=0;
        if(c=='X'||c=='x') x=1;
        else x=-1;
        inputs[2]=x;
        return inputs;
    }

    public static int[] selectMove(TicTacToeMatrix t){
        System.out.println("Please select a position to play [1~9]");
        int move = in.nextInt();

        while (!validPosition(move) || !t.isEmpty(coord(move))){
            System.out.println("Position is already taken or invalid! Choose another one");
            move = in.nextInt();
        }
        return coord(move);
    }

    public static int[] coord(int where) {
        int[] pos = new int[2];
        switch (where) {
            case 1:
                pos[0] = 0;pos[1] = 0;break;
            case 2:
                pos[0] = 0;pos[1] = 1;break;
            case 3:
                pos[0] = 0;pos[1] = 2;break;
            case 4:
                pos[0] = 1;pos[1] = 0;break;
            case 5:
                pos[0] = 1;pos[1] = 1;break;
            case 6:
                pos[0] = 1;pos[1] = 2;break;
            case 7:
                pos[0] = 2;pos[1] = 0;break;
            case 8:
                pos[0] = 2;pos[1] = 1;break;
            case 9:
                pos[0] = 2;pos[1] = 2;break;
        }
        return pos;
    }

    public static boolean validPosition(int pos) {
        return (pos > 0 && pos < 10);
    }

    public static boolean isFull(int [][] tab){
        for(int i=0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++)
                if (tab[i][j] == 0)
                    return false;
        }
        return true;
    }

    public static int[][] computer1stMove(int[][] tab) {
        // gen a best random position
        // the ideal positions to play for the first time are the corners and the (1,1)
        int [] best_pos = {1,3,5,7,9};
        int [] play_where = coord(best_pos[ThreadLocalRandom.current().nextInt(0, 3 + 1)]);
        tab[play_where[0]][play_where[0]] = 1;
        return  tab;
    }

// check for wins

    public static boolean win_at_row ( int [][] tab , int l , int player )
    {
        for ( int i = 0 ; i < tab.length ; i ++)
            if ( tab [ l ] [ i ] != player )
                return false ;
        return true;
    }

    public static boolean win_at_col ( int [][] tab , int c , int player )
    {
        for ( int i = 0 ; i < tab.length ; i ++)
            if ( tab [ i ] [ c ] != player )
                return false ;
        return true;
    }

    public static boolean win_at_Diag1 ( int [][] tab , int player )
    {
        for ( int i = 0 ; i < tab.length ; i ++)
            if ( tab [ i ] [ i ] != player )
                return false;
        return true;
    }

    public static boolean win_at_Diag2 ( int [][] tab , int player )
    {
        for ( int i = 0 ; i < tab.length ; i ++)
            if ( tab [ ( tab.length -1)- i ] [ i ] != player )
                return false ;
        return true ;
    }

}
