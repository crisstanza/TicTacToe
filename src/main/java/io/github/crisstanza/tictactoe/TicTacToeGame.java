package io.github.crisstanza.tictactoe;

public final class TicTacToeGame {

	public static final int STATUS_NOT_STARTED = -1;
	public static final int STATUS_RUNNING = 0;
	public static final int STATUS_GAME_OVER = 1;

	private String board;
	private int id;
	private int status;
	private String turn;

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

}
