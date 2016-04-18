package TicTacToe;

import java.util.Calendar;

public class TicTacToe {

    public TicTacToeMatrix t = new TicTacToeMatrix();

    public void startGame(int algorithm, int whoPlaysFirst, int playerWithX) {
        t.playerwithX = playerWithX;
        switch (algorithm) {
            case 1:
                play(whoPlaysFirst, false);
                break;
            case 2:
                play(whoPlaysFirst, true);
                break;
            default:
                System.out.println("Not a valid option!!");
                System.exit(0);
        }
    }

    public void play(int who, boolean wantsAlpha) {

        Minimax s = new Minimax(t.size, -1);
        Runtime runtime = Runtime.getRuntime();
        Calendar cal = Calendar.getInstance();

        boolean first_move = true;
        int mb = 1024 * 1024;
        long start = cal.getTimeInMillis();

        do {
            if (first_move) {
                if (who == 1) {
                    System.out.println("Here's the table...Good luck you're gonna need it!");
                    t.print();
                    t.play(GameEngine.selectMove(t));
                    first_move = false;
                    t.print();
                } else {
                    t.tab = GameEngine.computer1stMove(t.tab);
                    first_move = false;
                    System.out.println("Computer's first move:");
                    t.print();
                    t.play(GameEngine.selectMove(t));
                    t.print();
                }
            } else {
                t.play(GameEngine.selectMove(t));
                t.print();
            }

            if (!s.isTerminal(t.tab)) {
                if (wantsAlpha)
                    t.tab = s.minmax_choice(t.tab, true);
                else
                    t.tab = s.minmax_choice(t.tab, false);

                System.out.println("Computer's move:");
                t.print();
                System.out.println("Memory used: " + (runtime.totalMemory() - runtime.freeMemory()) / mb + "mb");
                System.out.println("Execution time: " + ( Calendar.getInstance().getTimeInMillis() -start ) + " ms");
                System.out.println("Visited Nodes: " + Minimax.nNodes);
                Minimax.nNodes = 0;
            }
        } while (!s.isTerminal(t.tab));

        if (s.win(t.tab, 1)) {
            System.out.println("Computer won!!");
        } else if (s.win(t.tab, -1))
            System.out.println("You're the winner!");
        else
            System.out.println("It's a draw!");
    }
}


