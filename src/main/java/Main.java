import java.util.InputMismatchException;
import java.util.Scanner;

import config.Config;
import io.github.crisstanza.tictactoe.TicTacToeGame;
import io.github.crisstanza.tictactoe.TicTacToeServer;

public class Main {
	public static final int BOARD_SIZE = 3;
	public static final char SPACE = ' ';
	private static final Scanner scanner = new Scanner(System.in);
	private static final Config config = Config.getInstance();

	public static void main(String[] args) throws Exception {
		//
		clear();

		final TicTacToeServer server = new TicTacToeServer();
		final TicTacToeGame game = server.getGame();
		//
		printHeader();
		//
		if (game.getStatus() == TicTacToeGame.STATUS_NOT_STARTED) {
			System.out.println("Este jogo ainda n\u00E3o iniciou.");
		} else if (game.getStatus() == TicTacToeGame.STATUS_GAME_OVER) {
			// System.out.println()
			System.out.println("Fim do jogo.");
			printBoard(game);
		} else {
			if (game.getTurn().equals(config.getNome())) {

				System.out.println("Sua vez de jogar.");
				printBoard(game);

				int row = getMove("linha");
				int col = getMove("coluna");

				updateBoard(game, row, col);
				game.setBoard("ooooooooo");
				while (true) {
					System.out.println("Tentando enviar jogada para o servidor");
					if (server.setGame(game).trim().equals("0")) {
						break;
					}
					System.out.println("Erro ao enviar jogada. Aguarde nova tentativa");
					Thread.sleep(1000);
				}
				clear();
				printHeader();
				System.out.println(" Esperando a jogada do advers\u00E1rio.");
				printBoard(game);
			} else {
				System.out.println(" Esperando a jogada do advers\u00E1rio.");
				printBoard(game);
			}
		}

		close(scanner);
		//
	}

	private static final void printHeader() {
		System.out.println("  ======================");
		System.out.println("  =    JOGO DA VELHA   =");
		System.out.println("  ======================");
		System.out.println();
	}

	public static void printBoard(TicTacToeGame game) {
		System.out.println();
		final String pad = "   ";
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print(pad);
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print("" + SPACE + game.getBoard().charAt(BOARD_SIZE * i + j) + SPACE);
				if (j < BOARD_SIZE - 1) {
					System.out.print("\u00A6");
				} else {
					System.out.println();
				}
			}
			if (i != BOARD_SIZE - 1) {
				System.out.print(pad);
				for (int j = 0; j < BOARD_SIZE; j++) {
					System.out.print("---");
					if (j < BOARD_SIZE - 1) {
						System.out.print("+");
					} else {
						System.out.println();
					}
				}
			} else {
				System.out.println();
			}
		}
	}

	public static final void clear() {
		for (int i = 0; i < 64; i++) {
			System.out.println();
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

	private static final int getMove(String msg) {
		while (true) {
			Integer value;
			System.out.print("Entre com o número da " + msg + ": ");
			value = nextInt(scanner);
			if (value != null && value >= 1 && value <= BOARD_SIZE) {
				return value;
			} else {
				scanner.next();
				System.out.println("Valor inv\u00E1lido. Insira um n\u00FAmero entre 1 e " + BOARD_SIZE);
			}
		}
	}

	private static final void updateBoard(TicTacToeGame game, int row, int col) {
		String newBoard = game.getBoard().substring(0, (row - 1) * BOARD_SIZE + col - 1);
		newBoard += game.getPiece();
		newBoard += game.getBoard().substring((row - 1) * BOARD_SIZE + col);
		game.setBoard(newBoard);
	}
}
