import io.github.crisstanza.tictactoe.TicTacToeGame;
import io.github.crisstanza.tictactoe.TicTacToeServer;
import config.Config;

public class Main {

	private static final Config config = Config.getInstance();

	public static void main(String[] args) throws Exception {
		//
		TicTacToeServer server = new TicTacToeServer();
		TicTacToeGame game = server.getGame();
		//
		if (game.getStatus() == TicTacToeGame.STATUS_GAME_OVER) {
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

}
