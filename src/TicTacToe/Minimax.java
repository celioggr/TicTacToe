package TicTacToe;

import java.util.ArrayList;

public class Minimax{

    public static ArrayList<TTTChilds> childs = new ArrayList<TTTChilds>(); //arvore de estados
    int lenght , maxDeep;
    public static int nNodes;

    public Minimax(int lenght, int maxDeep){
        this.lenght = lenght ;
        if ( maxDeep > 0 )
            this.maxDeep = maxDeep ;
        else
            this.maxDeep = Integer.MAX_VALUE;
    }

    public int [][] minmax_choice(int [][] tab , boolean wantsAlpha){
        childs.clear ();
        int v;

        if(wantsAlpha)
             v = Max_utility(tab, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        else
            v = Max_utility( tab , true , 1);

        for ( TTTChilds c : childs)
            if ( c.utility == v )
                return c.tab;
        return tab;
    }


    public int Max_utility ( int [][] tab , boolean add , int deep ){
        nNodes++;
        if ( deep++ > maxDeep || isTerminal(tab) )
            return utilityOf(tab) ;
        int v = Integer.MIN_VALUE ;
        for ( TTTChilds c : gen_childs( tab , 1 ) )
        {
            v = Math.max( v , Min_utility( c.tab , deep ) ) ;
            c.utility = v;
            if (add)
                childs.add(c) ;
        }
        return v;
    }


    public int Min_utility ( int [][] tab , int deep ){
        nNodes++;
        if ( deep++ > maxDeep || isTerminal( tab ) )
            return utilityOf(tab) ;

        int v = Integer.MAX_VALUE; // min utility
        for ( TTTChilds c : gen_childs( tab , -1 ) )
        {
            v = Math.min( v , Max_utility( c.tab , false , deep ) );
            c.utility = v;
        }
        return v;
    }

    //simplificações Alphabeta

    private int Max_utility(int[][] tab, int alfa , int beta , boolean add) {
      nNodes++;
        if ( isTerminal(tab) )
            return utilityOf(tab) ;
        int v = Integer.MIN_VALUE ;
        for ( TTTChilds  c : gen_childs(tab,1) )
        {
            v = Math.max(v, Min_utility(c.tab, alfa, beta));
            c.utility = v;
            if (add)
                childs.add(c) ;

            if (v >= beta)
                return v;

            alfa = Math.max(alfa, v);
        }
        return v;
    }

    private int Min_utility(int[][] tab, int alfa, int beta) {
           nNodes++;
            if ( isTerminal(tab) )
                return utilityOf(tab) ;

            int v = Integer.MAX_VALUE; // min utility
            for ( TTTChilds c : gen_childs( tab , -1 ) )
            {
                v = Math.min( v , Max_utility(c.tab ,alfa , beta , false) );
                c.utility = v;

                if( v <= alfa)
                    return v;
                beta = Math.min(beta,v);
            }
            return v;
        }

    public ArrayList <TTTChilds > gen_childs ( int [][] tab , int player )
    {
        ArrayList <TTTChilds> c = new ArrayList <TTTChilds>();
        for (int i=0 ; i < tab.length ; i++)
        {
            for (int j=0 ; j < tab.length ; j++)
            {
                if ( tab [i][j] == 0 )
                {
                    tab[i][j] = player ;
                    c.add (new TTTChilds ( tab ) ) ;
                    tab[i][j] = 0; //voltar a repor a matriz original
                }
            }
        }
        return c;
    }

    public boolean win(int [][] m, int x){
        for ( int i = 0 ; i < m.length ; i ++)
            if ( GameEngine.win_at_row( m , i , x ) || GameEngine.win_at_col( m , i , x ) )
                return true ;
        if ( GameEngine.win_at_Diag1( m , x ) || GameEngine.win_at_Diag2( m , x ) )
            return true;

        return false;
    }

    public boolean isTerminal (int [][] tab) { // se for uma matrix final
        return (  win( tab , 1 ) || win( tab , -1) || GameEngine.isFull( tab ) ) ;
    }

    public int utilityOf ( int [][] tab ) {
        if ( win( tab , 1 ) )
            return 1 ;
        else if ( win( tab , -1) )
            return -1;
        else
            return 0 ;
    }
}
