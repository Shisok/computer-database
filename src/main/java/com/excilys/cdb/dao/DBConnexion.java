package com.excilys.cdb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.exception.DAOConfigurationException;

public class DBConnexion {

	private static final String FICHIER_PROPERTIES = "com/excilys/cdb/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

	private String url;
	private String username;
	private String password;

	private DBConnexion(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DBConnexion getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
		} catch (NullPointerException e) {
			throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
		}

		DBConnexion instance = new DBConnexion(url, nomUtilisateur, motDePasse);
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}