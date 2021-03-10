package com.excilys.cdb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTuto {
	public static void main(String[] args) {
		/* Chargement du driver JDBC pour MySQL */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			/* Gérer les éventuelles erreurs ici. */
		}

		String url = "jdbc:mysql://localhost:3306/computer-database-db";
		String utilisateur = "admincdb";
		String motDePasse = "qwerty1234";
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		try {
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

			/* Ici, nous placerons nos requêtes vers la BDD */
			/* ... */

		} catch (SQLException e) {
			/* Gérer les éventuelles erreurs ici */
		} finally {
			if (connexion != null) {
				try {
					System.out.println("test");
					// interface servant a executer des requetes
					statement = connexion.createStatement();
					resultat = statement.executeQuery("SELECT id, name FROM company;");

					/* Récupération des données du résultat de la requête de lecture */
					// resultat a l indice 1 car le curseur est place avant le 1er resultat
					// si on lit le 1er resultat, on obtient SQLException
					while (resultat.next()) {
						int idCompany = resultat.getInt("id");
						String nameCompany = resultat.getString("name");
						/* Traiter ici les valeurs récupérées. */
						System.out.println("id :" + idCompany + ", name :" + nameCompany);

					}
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
				} finally {
					if (resultat != null) {
						try {
							/* On commence par fermer le ResultSet */
							resultat.close();
						} catch (SQLException ignore) {
						}
					}
					if (statement != null) {
						try {
							/* Puis on ferme le Statement */
							statement.close();
						} catch (SQLException ignore) {
						}
					}
					if (connexion != null) {
						try {
							/* Et enfin on ferme la connexion */
							connexion.close();
						} catch (SQLException ignore) {
						}
					}
				}
			}
		}
	}
}