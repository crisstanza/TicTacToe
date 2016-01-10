package io.github.crisstanza.tictactoe;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public final class TicTacToeServer {

	public static final String URL = "http://www.acasadojava.com.br/tictactoe/service";

	public TicTacToeGame getGame() throws Exception {
		return (TicTacToeGame) transform(get(operation(URL, "GetGame")), new TicTacToeGame());
	}

	private String operation(String url, String operation) {
		return url + "?op=" + operation;
	}

	private String get(String url) throws Exception {
		InputStream urlOpenStream = null;
		Scanner scanner = null;
		try {
			urlOpenStream = new URL(url).openStream();
			scanner = new Scanner(urlOpenStream, "UTF-8");
			String response = scanner.useDelimiter("\\A").next();
			return response;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (urlOpenStream != null) {
				urlOpenStream.close();
			}
		}

	}

	private Object transform(String response, Object game) throws Exception {
		String[] lines = response.split("\n");
		Field[] fields = game.getClass().getDeclaredFields();
		Arrays.sort(fields, new Comparator<Field>() {
			public int compare(Field o1, Field o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		int line = 0;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			if (!Modifier.isFinal(field.getModifiers())) {
				field.set(game, value(lines[line++], field));
			}
			field.setAccessible(false);
		}
		return game;
	}

	private Object value(String line, Field field) {
		Class<?> type = field.getType();
		if ("int".equals(type.getName()) || Integer.class.getName().equals(type.getName())) {
			return Integer.parseInt(line, 10);
		} else {
			return line;
		}
	}

}
