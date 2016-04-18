package TicTacToe;

public class TTTChilds {
//estados que resultam das posições em que ainda é possivel jogar
    int[][] tab;
    int utility;

    public TTTChilds (int[][] m) {
        int n = m.length;
        tab = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tab[i][j] = m[i][j];
    }
}
