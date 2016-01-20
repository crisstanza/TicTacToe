import java.util.InputMismatchException;
import java.util.Scanner;

import config.Config;
import io.github.crisstanza.tictactoe.TicTacToeGame;
import io.github.crisstanza.tictactoe.TicTacToeServer;

public class Main {
	public static final int BOARD_SIZE = 3;
	public static final char SPACE = '.';
	private static final Scanner scanner = new Scanner(System.in);
	private static final Config config = Config.getInstance();

	public static void main(String[] args) throws Exception {
		//
		clear();

		final TicTacToeServer server = new TicTacToeServer();
		final TicTacToeGame game = server.getGame();
		//
		if (game.getStatus() == TicTacToeGame.STATUS_NOT_STARTED) {
			System.out.println("Este jogo ainda n\u00E3o iniciou.");
		} else if (game.getStatus() == TicTacToeGame.STATUS_GAME_OVER) {
			// System.out.println()
			printBoard(game);
			System.out.println("Fim do jogo.");
		} else {
			printBoard(game);
			if (game.getTurn().equals(config.getNome())) {
				System.out.println("Sua vez de jogar.");
				System.out.print("Entre com a linha: ");
				int row = scanner.nextInt();
				System.out.print("Entre com a coluna: ");
				int col = scanner.nextInt();
				String newBoard = game.getBoard().substring(0, (row - 1) * BOARD_SIZE + (col - 1));
				newBoard += game.getPiece();
				newBoard += game.getBoard().substring((row - 1) * BOARD_SIZE + (col - 1) + 1);
				System.out.println(newBoard.replace(' ', '.'));
				System.out.println(newBoard.length());
				game.setBoard(newBoard);
				if (server.setGame(game).trim().equals("0")) {
					clear();
					printBoard(game);
					System.out.println("Esperando a jogada do advers\u00E1rio.");
				} else {
					System.out.println("Jogada n\u00E3o pode ser realizada");
				}
			} else {
				System.out.println("Esperando a jogada do advers\u00E1rio.");
			}
		}

		close(scanner);
		//
	}

	public static void printBoard(TicTacToeGame game) {

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print("" + SPACE + game.getBoard().charAt(BOARD_SIZE * i + j) + SPACE);
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

	public static final void clear() {
		for (int i = 0; i < 64; i++) {
			System.out.println(".");
		}
	}

	public static final Integer nextInt(final Scanner scanner) {
		try {
			return scanner.nextInt();
		} catch (final InputMismatchException ime) {
			return null;
		}
	}

	public static final void close(final AutoCloseable... closeMe) {
		try {
			for (AutoCloseable closeable : closeMe) {
				if (closeable != null) {
					closeable.close();
				}
			}
		} catch (final Exception exc) {
			// nothing to do!!!
		}
	}
}
