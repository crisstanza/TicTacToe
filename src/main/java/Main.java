import config.Config;
import io.github.crisstanza.tictactoe.TicTacToeGame;
import io.github.crisstanza.tictactoe.TicTacToeServer;

public class Main {
	public static final int BOARD_SIZE = 3;

	private static final Config config = Config.getInstance();

	public static void main(String[] args) throws Exception {
		//
		TicTacToeServer server = new TicTacToeServer();
		TicTacToeGame game = server.getGame();
		//
		if (game.getStatus() == TicTacToeGame.STATUS_NOT_STARTED) {
			System.out.println("Este jogo ainda n\u00E3o iniciou.");
		} else if (game.getStatus() == TicTacToeGame.STATUS_GAME_OVER) {
			// System.out.println()
			printBoard(game);
			System.out.println("Fim do jogo.");
		} else {
			if (game.getTurn().equals(config.getNome())) {
				System.out.println("Sua vez de jogar.");
			} else {
				System.out.println("Esperando a jogada do advers\u00E1rio.");
			}
		}
		//
	}

	public static void printBoard(TicTacToeGame game) {

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(" " + game.getBoard().charAt(i) + " ");
				if (j != BOARD_SIZE - 1) {
					System.out.print("|");
				} else {
					System.out.println("");
				}
			}
			if (i != BOARD_SIZE - 1) {
				System.out.println("-----------");
			}
		}

	}

}
