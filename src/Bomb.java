import javax.swing.JPanel;

/**
 * Bombenklasse fuer das Objekt Bombe und zugeh�rige Methoden.
 * 
 * @author Pierre Schwarz
 * 
 */

public class Bomb extends JPanel {

	boolean ob, ub, lb, rb, det;
	static boolean gameOver = false;
	int x, y, o, u, l, r, num;
	static Bomb[] bombs = Init.bombs();

	/**
	 * Bombenobjekt wird initialisiert und bekommt sowohl Koordinaten, als auch
	 * Radien fuer die einzelnen Richtungen, eine Nummer und Detonationsabfrage.
	 * 
	 * @param player
	 */
	public Bomb(Player player) {
		this.x = player.x;
		this.y = player.y;
		this.o = 0;
		this.u = 0;
		this.l = 0;
		this.r = 0;
		this.ob = true;
		this.ub = true;
		this.lb = true;
		this.rb = true;
		this.det = false;
	}

	/**
	 * Bombe wird platziert, bekommt eine Nummer und wird gezeichnet.
	 * 
	 * @param bomb
	 * @param player
	 * @return
	 */

	public static void placeBomb(Bomb bomb, Player player) {
		Field.bombPos[bomb.x][bomb.y] = true;
		//Netzwerk senden
		bomb.num = player.bP;
		bombToArray(bomb, player);

		player.bP += 1;

	}

	/**
	 * Detonation der Bombe wird aktiviert und ueberprueft, ob sich weitere
	 * Bomben im Detonationsradius befinden. Sollte dies gegeben sein, wird die
	 * entsprechende Detonation der jeweiligen Bombe ebenfalls aktiviert und das
	 * Sleep vom 1. Try-Blocks des zugehoerigen Threads interrupted.
	 * 
	 * @param bomb
	 * @param player
	 * @return
	 */
	public static void detonate(Bomb bomb, Player player) {

		bomb.det = true;
		bombToArray(bomb, player);
		for (int gor = 1; gor <= bomb.r; gor++) {
			isGameOver(Field.fieldNumbers[bomb.x + gor][bomb.y]);
			Field.fieldNumbers[bomb.x + gor][bomb.y] = 8;
			if (Field.bombPos[bomb.x + gor][bomb.y]) {
				Field.bombPos[bomb.x + gor][bomb.y] = false;
				for (int i = 0; i < bombs.length; i++)
					if (bombs[i].x == bomb.x + gor && bombs[i].y == bomb.y)
						bombs[i].det = true;
			}
		}
		for (int gol = 1; gol <= bomb.l; gol++) {
			isGameOver(Field.fieldNumbers[bomb.x - gol][bomb.y]);
			Field.fieldNumbers[bomb.x - gol][bomb.y] = 8;
			if (Field.bombPos[bomb.x - gol][bomb.y]) {
				Field.bombPos[bomb.x - gol][bomb.y] = false;
				for (int i = 0; i < bombs.length; i++)
					if (bombs[i].x == bomb.x - gol && bombs[i].y == bomb.y)
						bombs[i].det = true;
			}
		}
		for (int goo = 1; goo <= bomb.o; goo++) {
			isGameOver(Field.fieldNumbers[bomb.x][bomb.y - goo]);
			Field.fieldNumbers[bomb.x][bomb.y - goo] = 8;
			if (Field.bombPos[bomb.x][bomb.y - goo]) {
				Field.bombPos[bomb.x][bomb.y - goo] = false;
				for (int i = 0; i < bombs.length; i++)
					if (bombs[i].x == bomb.x && bombs[i].y == bomb.y - goo)
						bombs[i].det = true;
			}
		}
		for (int gou = 1; gou <= bomb.u; gou++) {
			isGameOver(Field.fieldNumbers[bomb.x][bomb.y + gou]);
			Field.fieldNumbers[bomb.x][bomb.y + gou] = 8;
			if (Field.bombPos[bomb.x][bomb.y + gou]) {
				Field.bombPos[bomb.x][bomb.y + gou] = false;
				for (int i = 0; i < bombs.length; i++)
					if (bombs[i].x == bomb.x && bombs[i].y == bomb.y + gou)
						bombs[i].det = true;
			}
		}
		isGameOver(Field.fieldNumbers[bomb.x][bomb.y]);
		Field.fieldNumbers[bomb.x][bomb.y] = 8;
		Field.bombPos[bomb.x][bomb.y] = false;

	}

	/**
	 * Detonation wird beendet. Abfrage bzgl. des Ausganges, ob dieser sich
	 * unter einer Kiste befunden hat.
	 * 
	 * @param bomb
	 * @param pl
	 * @return
	 */

	public static void endDetonation(Bomb bomb, Player pl) {

		bomb.det = false;
		bombToArray(bomb, pl);
		for (int gor = 1; gor <= bomb.r; gor++) {
			Field.fieldNumbers[bomb.x + gor][bomb.y] = 0;
			checkPowerUp(bomb.x + gor, bomb.y);
		}
		for (int gol = 1; gol <= bomb.l; gol++) {
			Field.fieldNumbers[bomb.x - gol][bomb.y] = 0;
			checkPowerUp(bomb.x - gol, bomb.y);
		}
		for (int goo = 1; goo <= bomb.o; goo++) {
			Field.fieldNumbers[bomb.x][bomb.y - goo] = 0;
			checkPowerUp(bomb.x, bomb.y - goo);
		}
		for (int gou = 1; gou <= bomb.u; gou++) {
			Field.fieldNumbers[bomb.x][bomb.y + gou] = 0;
			checkPowerUp(bomb.x, bomb.y + gou);
		}
		Field.fieldNumbers[bomb.x][bomb.y] = 0;

		if (Field.fieldNumbers[Init.ex][Init.ey] == 0) {
			Field.fieldNumbers[Init.ex][Init.ey] = 9;
		}

		pl.bP -= 1;
	}

	private static void checkPowerUp(int x, int y) {
		if (Init.powerUps[x][y] == 42) {
			Field.fieldNumbers[x][y] = 42;
		} else if (Init.powerUps[x][y] == 41) {
			Field.fieldNumbers[x][y] = 41;
		}

	}

	/**
	 * Abfrage, ob sich ein Feld zerstoeren laesst oder nicht.
	 * 
	 * @param coord
	 * @return
	 */
	public static boolean isDestructable(int coord) {
		if (coord == 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Bombe wird zum statischen Bombenarray dieser Klasse hinzugefuegt.
	 * 
	 * @param bomb
	 * @param pl
	 */

	public static void bombToArray(Bomb bomb, Player pl) {
		if (pl == Init.Player1)
			bombs[bomb.num] = bomb;

		if (Init.MP)
			if (pl == Init.Player2)
				bombs[bomb.num] = bomb;
		
		if (Init.KI)
			if (pl == KI.kiPl)
				bombs[bomb.num] = bomb;
	}

	/**
	 * Abfrage, ob sich ein Spieler innerhalb eines Detonationsradius einer
	 * Bombe befand. Falls ja, ist das Spiel vorbei.
	 * 
	 * @param coord
	 */

	public static void isGameOver(int coord) {
		if (coord == 3) {
			if (Init.KI){
				gameOver = true;
				Paul.kiThread.interrupt();
			}
			
			System.out.println("Spieler 2 siegt");
			Init.reset();
			GameOverPic.pic = 1;
			GameOverPic.DrawPic();
			// Interface.closeGameOpenMenu();
			// Menue.MainMenu();
		} else if (coord == 4) {
			if (Init.KI){
				gameOver = true;
				Paul.kiThread.interrupt();
			}
			
			System.out.println("Spieler 1 siegt");
			Init.reset();
			GameOverPic.pic = 1;
			GameOverPic.DrawPic();
			// Interface.closeGameOpenMenu();
			// Menue.MainMenu();
		}
	}

	/**
	 * Die Methode bekommt ein Objekt der Klasse Bomb �bergeben und die Gr��e
	 * des Bombenradiuses. Es folg eine �berpr�fung in jede Richtung. Die
	 * Explosion der Bombe wird solange weiter gef�hrt bis sie den maximal Wert
	 * erreicht hat oder diese auf die erste Kiste in der jeweiligen Richtung
	 * st��t. Hier zu werden Boolean-Werte eingesetzt welche solange auf True
	 * bleiben bis einer diese F�lle auftritt. Die l�nge der Explosion wir f�r
	 * jede Richtung abgespeichert so das diese sp�ter abgefragt werden kann.
	 * 
	 * @param bomb
	 * @param pl
	 */

	public static void radCheck(Bomb bomb, Player pl) {
		bomb.ob = true;
		bomb.ub = true;
		bomb.lb = true;
		bomb.rb = true;

		int x = bomb.x;
		int y = bomb.y;

		bomb.r = 0;
		bomb.l = 0;
		bomb.o = 0;
		bomb.u = 0;

		for (int i = 0; i <= pl.rad; i++) {
			/* nach rechts �berpr�fen */
			if (bomb.rb) {
				bomb.r = i;
				if (Field.fieldNumbers[x + i][y] == 2) {
					bomb.rb = false;
				}
				if (!isDestructable(Field.fieldNumbers[x + (i + 1)][y])) {
					bomb.rb = false;
				}
			}

			/* nach links �berpr�fen */
			if (bomb.lb) {
				bomb.l = i;
				if (Field.fieldNumbers[x - i][y] == 2) {
					bomb.lb = false;
				}
				if (!isDestructable(Field.fieldNumbers[x - (i + 1)][y])) {
					bomb.lb = false;
				}
			}
			/* nach unten �berpr�fen */
			if (bomb.ub) {
				bomb.u = i;
				if (Field.fieldNumbers[x][y + i] == 2) {
					bomb.ub = false;
				}
				if (!isDestructable(Field.fieldNumbers[x][y + (i + 1)])) {
					bomb.ub = false;
				}
			}
			/* nach oben �berpr�fen */
			if (bomb.ob) {
				bomb.o = i;
				if (Field.fieldNumbers[x][y - i] == 2) {
					bomb.ob = false;
				}
				if (!isDestructable(Field.fieldNumbers[x][y - (i + 1)])) {
					bomb.ob = false;
				}
			}
		}

		bombToArray(bomb, pl);

	}
}
