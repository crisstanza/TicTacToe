package config;

import java.util.ResourceBundle;

public final class Config {

	private static final Config instance = new Config();

	private final ResourceBundle config = ResourceBundle.getBundle("config");

	private Config() {
	}

	public static Config getInstance() {
		return instance;
	}

	public String getNome() {
		return getString("nome");
	}

	private String getString(String name) {
		return config.getString(name);
	}

}
