package config;

import java.io.FileInputStream;
import java.util.Properties;

public final class Config {

	private static final Properties config = new Properties();
	private static final Config instance = new Config();

	private Config() {
		try {
			config.load(new FileInputStream("config.properties"));
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	}

	public static Config getInstance() {
		return instance;
	}

	public String getNome() {
		return config.getProperty("nome");
	}

}
