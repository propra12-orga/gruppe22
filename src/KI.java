/**
 * Klasse fuer alle KI-Methoden
 * @author Pierre Schwarz
 *
 */


public class KI {
	
	/**
	 * @param kiPl <br>
	 * Der zweite Spieler wird als Computergegner verwendet.
	 */
	static Player kiPl = Init.Player2;
	
	static double shuffle;
	
	/**
	 * @param hasMoved <br>
	 * Zur Abfrage, ob sich der Computergegner in diesem Threaddurchgang schon bewegt hat.
	 */
	
	static boolean hasMoved = false;
	
	/**
	 * Legt eine Bombe.
	 */
	
	public static void placeBomb(){
		kiPl.ctrl = "Bombe";
		Movement.getMovement(kiPl);
	}
	
	/**
	 * Computergegner bewegt sich 2x nach Oben.
	 */
	
	public static void moveUp(){
		kiPl.ctrl = "Oben";
		Movement.getMovement(kiPl);
		
		try{
			Thread.sleep(750);
		}
		catch (InterruptedException e){
			
		}
		Movement.getMovement(kiPl);
	}
	
	/**
	 * Computergegner bewegt sich 2x nach Unten.
	 */
	
	public static void moveDown(){
		kiPl.ctrl = "Unten";
		Movement.getMovement(kiPl);
		
		try{
			Thread.sleep(750);
		}
		catch (InterruptedException e){
			
		}
		Movement.getMovement(kiPl);
	}
	
	/**
	 * Computergegner bewegt sich 2x nach Links.
	 */
	
	public static void moveLeft(){
		kiPl.ctrl = "Links";
		Movement.getMovement(kiPl);
		
		try{
			Thread.sleep(750);
		}
		catch (InterruptedException e){
			
		}
		Movement.getMovement(kiPl);
	}
	
	/**
	 * Computergegner bewegt sich 2x nach Rechts.
	 */
	
	public static void moveRight(){
		kiPl.ctrl = "Rechts";
		Movement.getMovement(kiPl);
		
		try{
			Thread.sleep(750);
		}
		catch (InterruptedException e){
			
		}
		Movement.getMovement(kiPl);
	}
	
	/**
	 * Basismethode fuer die KI. <br>
	 * Ueberpruefung der Umgebung. <br>
	 * 
	 * @param checkRad <br>
	 * Der Computergegner bekommt den Radius des ersten Spielers zur spaeteren Ueberpruefung
	 * seiner Sicherheit bzgl. der Bombendetonationen seines Feindes.
	 */
	
	public static void checkEnv(){
		kiPl.checkRad = Init.Player1.rad;
		checkEnemy();
		checkBoxes();
		isDanger();
		
		if (!kiPl.danger)
			chooseDir();
	}
	
	
	/**
	 * Waehlt eine Bewegungsrichtung nach dem Zufallsprinzip.
	 */
	
	public static void chooseDir(){
		while(!hasMoved){
			shuffle = Math.random();
				
			if (shuffle < 0.25 && Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl)){
				moveUp();
				hasMoved = true;
			}
			if (shuffle >= 0.25 && shuffle < 0.5 && Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)){
				moveDown();
				hasMoved = true;
			}		
			if (shuffle >= 0.5 && shuffle < 0.75 && Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)){
				moveLeft();
				hasMoved = true;
			}
			if (shuffle >= 0.75 && Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)){
				moveRight();
				hasMoved = true;
			}
		}
		hasMoved = false;
	}
		
	/**
	 * Ueberprueft, ob sich ein Gegner in Bombenreichweite befindet.
	 */

	public static void checkEnemy(){
		for (int i = 0; i <= kiPl.rad; i++){
			if (Field.fieldNumbers[kiPl.x - i][kiPl.y] == 3 && kiPl.x - i >= 0)
				placeBomb();
			if (Field.fieldNumbers[kiPl.x + i][kiPl.y] == 3 && kiPl.x + i < 21)
				placeBomb();
			if (Field.fieldNumbers[kiPl.x][kiPl.y - i] == 3 && kiPl.y - i >= 0)
				placeBomb();
			if (Field.fieldNumbers[kiPl.x][kiPl.y + i] == 3 && kiPl.y + i < 17)
				placeBomb();
		}
	}
		
	/**
	 * Ueberprueft, ob sich eine Box in Bombenreichweite befindet.
	 */
	
	public static void checkBoxes(){
		for (int i = 1; i <= kiPl.rad; i++)
			if(Field.fieldNumbers[kiPl.x + i][kiPl.y] == 2
			  || Field.fieldNumbers[kiPl.x - i][kiPl.y] == 2
			  || Field.fieldNumbers[kiPl.x][kiPl.y + i] == 2
			  || Field.fieldNumbers[kiPl.x][kiPl.y - i] == 2)
					placeBomb();
	}
		
	/**
	 * Fluchtmoeglichkeiten werden je nach Richtung, aus der die Detonation einer Bombe kommen
	 * wuerde, entsprechend nach dem Zufallsprinzip abgehandelt und der Computergegner bewegt.
	 * 
	 * @param s <br>
	 * Richtungsstring
	 */
	
	public static void checkEsc(String s){
		if (s == "Oben")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)){
					moveDown();
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)){
					moveLeft();
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)){
					moveRight();
					hasMoved = true;
				}
			}
		if (s == "Unten")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl)){
					moveUp();
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)){
					moveLeft();
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)){
					moveRight();
					hasMoved = true;
				}
			}
		if (s == "Links")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)){
					moveDown();
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl)){
					moveUp();
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)){
					moveRight();
					hasMoved = true;
				}
			}
		if (s == "Rechts")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)){
					moveDown();
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)){
					moveLeft();
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl)){
					moveUp();
					hasMoved = true;
				}
			}
		hasMoved = false;
	}
	
	/**
	 * Ueberprueft, ob sich der Computergegner in der Gefahr befindet, von einer Bombe getroffen zu
	 * werden.
	 */
	
	public static void isDanger(){
		

		for (int i = 0; i <= kiPl.checkRad && kiPl.y - i >= 0; i++)
			if (Field.bombPos[kiPl.x][kiPl.y - i] || Field.fieldNumbers[kiPl.x][kiPl.y - i] == 8){
				kiPl.danger = true;
				checkEsc("Oben");
			}

		for (int i = 0; i <= kiPl.checkRad && kiPl.y + i < 17; i++)
			if (Field.bombPos[kiPl.x][kiPl.y + i] || Field.fieldNumbers[kiPl.x][kiPl.y + i] == 8){
				kiPl.danger = true;
				checkEsc("Unten");
			}
		
		for (int i = 0; i <= kiPl.checkRad && kiPl.x + i < 21; i++)
			if (Field.bombPos[kiPl.x + i][kiPl.y] || Field.fieldNumbers[kiPl.x + i][kiPl.y] == 8){
				kiPl.danger = true;
				checkEsc("Rechts");
			}

		for (int i = 0; i <= kiPl.checkRad && kiPl.x - i >= 0; i++)
			if (Field.bombPos[kiPl.x - i][kiPl.y] || Field.fieldNumbers[kiPl.x - i][kiPl.y] == 8){
				kiPl.danger = true;
				checkEsc("Links");
			}

	}
}
