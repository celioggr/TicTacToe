package TicTacToe;

public class TicTacToeStart {
    public static void main(String [] args){
        GameEngine.Welcome();
        int[] inputs = GameEngine.Menu();
        new TicTacToe().startGame(inputs[0], inputs[1],inputs[2]);
    }
}
