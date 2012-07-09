package Game;
import javax.swing.JPanel;

import Menues.GameOverPic;

/**
 * Bombenklasse fuer das Objekt Bombe und zugeh�rige Methoden.
 * 
 * @author Pierre Schwarz
 * 
 */

public class Bomb extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Abfrage bzgl. der einzelnen Richtungsradien einer Bombe. <br>
	 * (s. Bomb.radcheck-Methode)
	 */
	public boolean ob, ub, lb, rb; 
	
	/**
	 * Gibt Detonation der Bombe an. <br>
	 * Wenn dieser Boolean auf true steht, wird die Detonation gezeichnet.
	 */
	public boolean det;
	
	/**
	 * Gibt an, ob beim jeweiligen Client der Thread der jeweils aktiven Bombe schon gestartet wurde.
	 */
	public boolean isSet;
	
	/**
	 * Gibt an, ob die jeweilige Bombe des Bombenarrays aktiv ist oder noch nicht
	 * gelegt wurde. Insb. wichtig fuer das Speichern eines Spielstandes oder das Uebermitteln
	 * der Daten an einen anderen Client.
	 */
	public boolean active;
	
	/**
	 * Gibt an, ob ein Spiel beendet ist.
	 */
	public static boolean gameOver = false;
	
	/**
	 * Parameter fuer ein Bombenobjekt.
	 */
	public int x, y, o, u, l, r, num;
	
	/**
	 * Bombenarray, das alle 6 Bomben-Objekte eines Bomberdroit-Spieles beinhaltet.
	 */
	public static Bomb[] bombs = Init.bombs();
	
	public boolean pl1;

	/**
	 * Bombenobjekt wird initialisiert und bekommt sowohl Koordinaten, als auch
	 * Radien fuer die einzelnen Richtungen, eine Nummer und Detonationsabfrage.
	 * 
	 * @param Player player
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
		this.active = false;
		this.isSet = false;
		
		pl1 = false;
		
		if (!Init.isInit){
			if(player == Init.Player1){
				if(!Carl.bomb0.isAlive()){
					this.num =0;
				}else if(!Carl.bomb1.isAlive()){
					this.num =1;
				}else if(!Carl.bomb2.isAlive()){
					this.num =2;
				}
			}
			if (player == Init.Player2){
				if(!Carl.bomb3.isAlive()){
					this.num =3;
				}else if(!Carl.bomb4.isAlive()){
					this.num =4;
				}else if(!Carl.bomb5.isAlive()){
					this.num =5;
				}
			}
		}
			
	}

	/**
	 * Bombe wird platziert, bekommt eine Nummer, wird aktiviert und gezeichnet.
	 * 
	 * @param Bomb bomb
	 * @param Player player
	 * @return
	 */

	public static void placeBomb(Bomb bomb, Player player) {
		Field.bombPos[bomb.x][bomb.y] = true;
		//Netzwerk senden
		bomb.active = true;
		bombToArray(bomb);
	}

	/**
	 * Detonation der Bombe wird aktiviert und ueberprueft, ob sich weitere
	 * Bomben im Detonationsradius befinden. Sollte dies gegeben sein, wird die
	 * entsprechende Detonation der jeweiligen Bombe ebenfalls aktiviert und das
	 * Sleep vom 1. Try-Blocks des zugehoerigen Threads interrupted.
	 * 
	 * @param Bomb bomb
	 * @param Player player
	 * @return
	 */
	public static void detonate(Bomb bomb, Player player) {

		bomb.det = true;
		bombToArray(bomb);
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
		bomb.active = false;
		isGameOver(Field.fieldNumbers[bomb.x][bomb.y]);
		Field.fieldNumbers[bomb.x][bomb.y] = 8;
		Field.bombPos[bomb.x][bomb.y] = false;

	}

	/**
	 * Detonation wird beendet. Abfrage bzgl. des Ausganges und der PowerUps, ob ein entsprechendes Objekt sich
	 * unter einer Kiste befunden hat.
	 * 
	 * @param Bomb bomb
	 * @param Player pl
	 * @return
	 */

	public static void endDetonation(Bomb bomb, Player pl) {

		bomb.det = false;
		bombToArray(bomb);
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
	 * @param int coord
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
	 * @param Bomb bomb
	 * @param Player pl
	 */

	public static void bombToArray(Bomb bomb) {
		bombs[bomb.num] = bomb;
	}

	/**
	 * Abfrage, ob sich ein Spieler innerhalb eines Detonationsradius einer
	 * Bombe befand. Falls ja, ist das Spiel vorbei.
	 * 
	 * @param int coord
	 */

	public static void isGameOver(int coord) {
		if (coord == 3) {
			if (Init.KI){
				gameOver = true;
				Paul.kiThread.interrupt();
			}
			
			System.out.println("Spieler 2 siegt");
			GameOverPic.pic = 1;
			new GameOverPic();
			// Interface.closeGameOpenMenu();
			// Menue.MainMenu();
		} else if (coord == 4) {
			if (Init.KI){
				gameOver = true;
				Paul.kiThread.interrupt();
			}
			
			System.out.println("Spieler 1 siegt");
			GameOverPic.picOn = true;
			GameOverPic.pic = 1;
			new GameOverPic();
			// Interface.closeGameOpenMenu();
			// Menue.MainMenu();
		}
	}

	/**
	 * Die Methode bekommt ein Objekt der Klasse Bomb und eines der Klasse Player uebergeben. 
	 * Es folg eine ueberpruefung in jede Richtung. Die
	 * Explosion der Bombe wird solange weiter gefuehrt, bis sie den maximalen Radius
	 * erreicht hat oder diese auf die erste Kiste/feste Mauer in der jeweiligen Richtung
	 * stoesst. Hierzu werden Boolean-Werte eingesetzt, welche solange auf true
	 * bleiben, bis einer diese Faelle auftritt. Die laenge der Explosion wird fuer
	 * jede Richtung abgespeichert, so dass diese spaeter abgefragt werden kann.
	 * 
	 * @param Bomb bomb
	 * @param Player pl
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

		bombToArray(bomb);

	}
}
