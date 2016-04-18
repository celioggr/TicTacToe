package TicTacToe;

public class TicTacToeMatrix {
    int playerwithX;
    private char [] symbols = {'o', ' ', 'x'};
    public int[][] tab;
    public final int size = 3;
    private String field;

    TicTacToeMatrix(){
        this.tab = new int [size][size] ;
        this.field = division() ;
    }

    public void print()
    {
        if(playerwithX==1)
        {
            symbols[0]='x';
            symbols[2]='o';
        }
        for ( int i = 0 ; i < size ; i ++)
        {
            for ( int j = 0 ; j < size ; j ++)
            {
                System.out.printf ( " %c %c" , symbols[tab [i][j] + 1] , j==(size-1) ? ' ' : '|' ) ;
            }
            if ( i != ( size-1) )
                System.out.println (field) ;
        }
        System.out.println ( "\r\n" ) ;
    }

    public String division ( ) {

        String s = new String( "\r\n" ) ;
        for ( int i = 0 ; i < ( size - 1 ) ; i ++)
            s += "---+" ;
        s += "---" ;
        return s ;
    }

    public void play(int [] where) {
                this.tab[where[0]][where[1]] = -1;
    }

    public boolean isEmpty(int[] where) {
        return (tab[where[0]][where[1]] == 0);
    }
}