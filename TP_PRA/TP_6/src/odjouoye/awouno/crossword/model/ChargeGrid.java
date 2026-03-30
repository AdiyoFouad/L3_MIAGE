package odjouoye.awouno.crossword.model;

import java.sql.*;
import java.util.*;

public class ChargeGrid {
	private Connection connexion;

	public ChargeGrid() {
		try {
			connexion = connecterBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection connecterBD() throws SQLException {
		

		Connection connect;
//		String url = "jdbc:mysql://localhost:3306/crossword";
//		connect = DriverManager.getConnection(url, "root", "");
		
		String url = "jdbc:mysql://mysql.istic.univ-rennes1.fr:3306/base_fodjouoye";
		connect = DriverManager.getConnection(url, "user_fodjouoye", "Adiyo2002");
		return connect;
	}

	/*
	 * Retourne la liste des grilles disponibles dans la BD
	 */
	public Map<Integer, String> availableGrids() {
		Map<Integer, String> grilles = new HashMap<>();
		String sql = "SELECT numero_grille, CONCAT(nom_grille, ' (', hauteur, 'x', largeur, ')') AS description FROM GRID ORDER BY numero_grille";
		try (Statement stmt = connexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				grilles.put(rs.getInt("numero_grille"), rs.getString("description")); // numgrille, desc
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grilles;
	}

	/**
	 * Crée Crosssword depuis la base de données
	 */
	public Crossword extractGrid(int numGrille) {
		try {
			String sqlGrille = "SELECT * FROM GRID WHERE numero_grille = ?";
			int height = 0, width = 0, controleLen = 0;
			try (PreparedStatement pstmt = connexion.prepareStatement(sqlGrille)) {
				pstmt.setInt(1, numGrille);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						height = rs.getInt("hauteur");
						width = rs.getInt("largeur");
						controleLen = rs.getString("controle").length(); // Pour vérif
					}
				}
			}
			if (height == 0 || width == 0)
				return null;

			Crossword cw = new Crossword(height, width);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					cw.setBlackSquare(i, j, true);
				}
			}

			// Remplit chaque case (suppose table CROSSWORD avec row, col, black(0/1),
			// solution, horizontal, vertical)
			String sqlCases = """
					SELECT *
					FROM CROSSWORD WHERE numero_grille = ?
					ORDER BY ligne, colonne
					""";
			try (PreparedStatement pstmt = connexion.prepareStatement(sqlCases)) {
				pstmt.setInt(1, numGrille);
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						int row = rs.getInt("ligne") - 1;
						int col = rs.getInt("colonne") - 1;
						cw.setBlackSquare(row, col, false);

						boolean horizontal = rs.getBoolean("horizontal");
						String definition = rs.getString("definition");
						cw.setDefinition(row, col, horizontal, definition);

						String solution = rs.getString("solution").toUpperCase();
						
						int len = solution.length();
						for (int i = 0; i < len; i++) {
							if (horizontal) {
								cw.setBlackSquare(row, col + i, false);
								cw.setSolution(row, col + i, solution.charAt(i));
							} else {
								cw.setBlackSquare(row + i, col, false);
								cw.setSolution(row + i, col, solution.charAt(i));
							}
						}

					}
				}
			}

			cw.generateClues();
			return cw;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getControle(int numGrille) {
		String sqlGrille = "SELECT controle FROM GRID WHERE numero_grille = ?";
		try {
			try (PreparedStatement pstmt = connexion.prepareStatement(sqlGrille)) {
				pstmt.setInt(1, numGrille);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return rs.getString("controle");
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return "";
	}

//    
	public static void main(String[] args) {
		System.out.println("=== LISTE DES GRILLES DISPONIBLES ===\n");

		ChargeGrid db = new ChargeGrid();
		Map<Integer, String> grilles = db.availableGrids();

		if (grilles.isEmpty()) {
			return;
		}

		// Affiche chaque grille
		for (Map.Entry<Integer, String> entry : grilles.entrySet()) {
			int num = entry.getKey();
			String desc = entry.getValue();
			System.out.printf("Grille #%d : %s%n", num, desc);

			// Charge et affiche aperçu
			Crossword cw = db.extractGrid(num);
			if (cw != null) {
				System.out.println("  Dimensions : " + cw.getHeight() + "x" + cw.getWidth());
				System.out.println("  Aperçu solution :");
				afficherApercu(cw);
			}
			System.out.println();
		}

		System.out.println("Total : " + grilles.size() + " grille(s) chargée(s) avec succès.");
	}

	public static void afficherApercu(Crossword cw) {
		int h = cw.getHeight();
		int w = cw.getWidth();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (cw.isBlackSquare(i, j)) {
					System.out.print(". ");
				} else {
					System.out.print(cw.getSolution(i, j) + " ");
				}
			}
			System.out.println();
		}
		if (cw.getHeight() > 5 || cw.getWidth() > 5) {
			System.out.println("  ... (grille plus grande)");
		}
	}
}
