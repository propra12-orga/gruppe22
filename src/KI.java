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
	static boolean[][] danger = initDangerArray();
	
	static int cnt = 1;
	static double shuffle;
	static boolean l, r, o, u, esc, noBomb = false;
	
	/**
	 * @param hasMoved <br>
	 * Zur Abfrage, ob sich der Computergegner in diesem Threaddurchgang schon bewegt hat.
	 */
	
	static boolean hasMoved = false;
	
	/**
	 * Legt eine Bombe.
	 */
	
	public static void placeBomb(){
		esc = true;
		kiPl.ctrl = "Bombe";
		Movement.getMovement(kiPl);
		wait750ms();
		
		if (kiPl.rad <= 1)
			while(!hasMoved){
				if (Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl) && Field.fieldNumbers[kiPl.x][kiPl.y - 2] != 2){
					moveUp();
					hasMoved = true;
				}
				else if (Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl) && Field.fieldNumbers[kiPl.x][kiPl.y + 2] != 2){
					moveDown();
					hasMoved = true;
				}		
				else if (Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl) && Field.fieldNumbers[kiPl.x - 2][kiPl.y] != 2){
					moveLeft();
					hasMoved = true;
				}
				else {
					moveRight();
					hasMoved = true;
				}
			}
			
		else {
			while(danger[kiPl.x][kiPl.y]){
				esc = true;
				if (Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl) && Field.fieldNumbers[kiPl.x][kiPl.y - 2] != 2){
					moveUp();
					hasMoved = true;
				}
				else if (Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl) && Field.fieldNumbers[kiPl.x][kiPl.y + 2] != 2){
					moveDown();
					hasMoved = true;
				}		
				else if (Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl) && Field.fieldNumbers[kiPl.x - 2][kiPl.y] != 2){
					moveLeft();
					hasMoved = true;
				}
				else {
					moveRight();
					hasMoved = true;
				}
				wait750ms();
			}
		}
		hasMoved = false;
		esc = false;
	}
	
	/**
	 * Computergegner bewegt sich nach Oben.
	 */
	
	public static void moveUp(){
		kiPl.ctrl = "Oben";
		Movement.getMovement(kiPl);
		//System.out.println("x = " + kiPl.x + " | y = " + kiPl.y);
	}
	
	/**
	 * Computergegner bewegt sich nach Unten.
	 */
	
	public static void moveDown(){
		kiPl.ctrl = "Unten";
		Movement.getMovement(kiPl);
		//System.out.println("x = " + kiPl.x + " | y = " + kiPl.y);
	}
	
	/**
	 * Computergegner bewegt sich nach Links.
	 */
	
	public static void moveLeft(){
		kiPl.ctrl = "Links";
		Movement.getMovement(kiPl);
		//System.out.println("x = " + kiPl.x + " | y = " + kiPl.y);
	}
	
	/**
	 * Computergegner bewegt sich nach Rechts.
	 */
	
	public static void moveRight(){
		kiPl.ctrl = "Rechts";
		Movement.getMovement(kiPl);
		//System.out.println("x = " + kiPl.x + " | y = " + kiPl.y);
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
		
		if(kiPl.rad <= Init.Player1.rad)
			kiPl.checkRad = Init.Player1.rad;
		else kiPl.checkRad = kiPl.rad;
		
		//isDanger();
		setDet();
		checkEnemy();
		checkBoxes();
		//surrounded();
		
		if (!kiPl.danger)
			chooseDir();
	}
	
	
	/**
	 * Waehlt eine Bewegungsrichtung nach dem Zufallsprinzip.
	 */
	
	public static void chooseDir(){
		if (danger[kiPl.x + 1][kiPl.y] && danger[kiPl.x - 1][kiPl.y] && kiPl.y != 15 
			&& kiPl.y != 1 && kiPl.x != 1 && kiPl.x != 19) //Gefahr von Links und Rechts
				hasMoved = true;
		else if (danger[kiPl.x][kiPl.y + 1] && danger[kiPl.x][kiPl.y - 1] && kiPl.y != 15 
			&& kiPl.y != 1 && kiPl.x != 1 && kiPl.x != 19) // Gefahr von Oben und Unten
				hasMoved = true;
		else if (danger[kiPl.x + 1][kiPl.y] && !Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)
			&& kiPl.y != 15 && kiPl.y != 1 && kiPl.x != 1 && kiPl.x != 19) // Gefahr von Rechts, Links nicht begehbar
				hasMoved = true;
		else if (danger[kiPl.x - 1][kiPl.y] && !Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)
			&& kiPl.y != 15 && kiPl.y != 1 && kiPl.x != 1 && kiPl.x != 19) // Gefahr von Links, Rechts nicht begehbar
				hasMoved = true;
		else if (danger[kiPl.x][kiPl.y - 1] && !Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)
			&& kiPl.y != 15 && kiPl.y != 1 && kiPl.x != 1 && kiPl.x != 19) // Gefahr von Oben, Unten nicht begehbar
				hasMoved = true;
		else if (danger[kiPl.x][kiPl.y + 1] && !Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl)
			&& kiPl.y != 15 && kiPl.y != 1 && kiPl.x != 1 && kiPl.x != 19) // Gefahr von Unten, Oben nicht begehbar
				hasMoved = true;
		
	
		while(!hasMoved){
			shuffle = Math.random();
			if (shuffle < 0.25 && Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl) && !danger[kiPl.x][kiPl.y - 1]){
				moveUp();
				hasMoved = true;
			}
			if (shuffle >= 0.25 && shuffle < 0.5 && Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl) && !danger[kiPl.x][kiPl.y + 1]){
				moveDown();
				hasMoved = true;
			}		
			if (shuffle >= 0.5 && shuffle < 0.75 && Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl) && !danger[kiPl.x - 1][kiPl.y]){
				moveLeft();
				hasMoved = true;
			}
			if (shuffle >= 0.75 && Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl) && !danger[kiPl.x + 1][kiPl.y]){
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
			if (kiPl.x - i >= 0)
				if (Field.fieldNumbers[kiPl.x - i][kiPl.y] == 3 ){
					placeBomb();
					//kiPl.danger = true;
				}
			if(kiPl.x + i < 21)
				if(Field.fieldNumbers[kiPl.x + i][kiPl.y] == 3){
					placeBomb();
					//kiPl.danger = true;
				}
			if (kiPl.y - i >= 0)
				if (Field.fieldNumbers[kiPl.x][kiPl.y - i] == 3){
					placeBomb();
					//kiPl.danger = true;
				}			
			if (kiPl.y + i < 17)
				if (Field.fieldNumbers[kiPl.x][kiPl.y + i] == 3){
					placeBomb();
					//kiPl.danger = true;
				}	
		}
	}
		
	/**
	 * Ueberprueft, ob sich eine Box in Bombenreichweite befindet.
	 */
	
	public static void checkBoxes(){
		for (int i = 1; i <= kiPl.rad; i++){
			if (kiPl.x + i < 21)
				if (Field.fieldNumbers[kiPl.x + i][kiPl.y] == 2){
					
					esc = true;
					if(!noBomb) placeBomb();
					wait750ms();
					esc = false;
			}
					
			else if (kiPl.x - i >= 0)
				if (Field.fieldNumbers[kiPl.x - i][kiPl.y] == 2){
					
					esc = true;
					if(!noBomb) placeBomb();
					wait750ms();
					esc = false;
			}
				
			else if (kiPl.y + i < 17)
				if (Field.fieldNumbers[kiPl.x][kiPl.y + i] == 2){
					
					esc = true;
					if(!noBomb) placeBomb();
					wait750ms();
					esc = false;
			}
				
			else if (kiPl.y - i >= 0)
				if (Field.fieldNumbers[kiPl.x][kiPl.y - i] == 2){
					
					esc = true;
					if(!noBomb) placeBomb();
					wait750ms();
					esc = false;
			}
		}
		noBomb = false;
		
	}
		
	/**
	 * Fluchtmoeglichkeiten werden je nach Richtung, aus der die Detonation einer Bombe kommen
	 * wuerde, entsprechend nach dem Zufallsprinzip abgehandelt und der Computergegner bewegt.
	 * 
	 * @param s <br>
	 * Richtungsstring
	 */
	
	public static void checkEsc(String s){
		esc = true;
		if (s == "Oben")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveDown();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveLeft();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveRight();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}
			}
		if (s == "Unten")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveUp();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveLeft();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveRight();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}
			}
		if (s == "Links")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveDown();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveUp();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveRight();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}
			}
		if (s == "Rechts")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveDown();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveLeft();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl)){
					while (cnt + 1 <= kiPl.checkRad){
						moveUp();
						wait750ms();
						cnt++;
					}
					kiPl.danger = false;
					hasMoved = true;
				}
			}
		hasMoved = false;
		esc = false;
	}
	
	/**
	 * Ueberprueft, ob sich der Computergegner in der Gefahr befindet, von einer Bombe getroffen zu
	 * werden.
	 */
	
/*	public static void isDanger(){

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

	}*/
	
	public static void wait3sec(){
		try{
			Thread.sleep(3000);
		}
		catch(InterruptedException e){
			
		}
	}
	
	public static void wait750ms(){
		try{
			Thread.sleep(750);
		}
		catch(InterruptedException e){
			
		}
	}
	
	/**
	 * Initialisiert das 2-dimensionale Boolean-Array zur Abfrage
	 * bzgl. der Sicherheit fuer den Computergegner.
	 * @return boolean[][] danger <br>
	 * 
	 */
	
	public static boolean[][] initDangerArray(){
		boolean[][] danger = new boolean[21][17];
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				danger[i][j] = false;
		return danger;
	}
	
	/**
	 * Setzt im danger-Array entsprechend der Bombe und ihrer kommenden
	 * Detonation die jeweiligen Stellen auf true.
	 * @param bomb
	 */
	
	public static void setDanger (Bomb bomb){
		danger[bomb.x][bomb.y] = true;
			for (int l = 0; l <= bomb.l; l++)
				danger[bomb.x - l][bomb.y] = true;
			for (int r = 0; r <= bomb.r; r++)
				danger[bomb.x + r][bomb.y] = true;
			for (int o = 0; o <= bomb.o; o++)
				danger[bomb.x][bomb.y - o] = true;
			for (int u = 0; u <= bomb.u; u++)
				danger[bomb.x][bomb.y + u] = true;
			
		
		/*for(int j = 0; j < 17; j++){
			System.out.println();
			for (int i = 0; i < 21; i++)
				System.out.print("[" + danger[i][j] + "]");
		}
		System.out.println();*/
			
	}
	
	/**
	 * Untersucht das Spielfeld nach Detonationen und aendert entsprechende
	 * Stellen im danger-Array.
	 */
	
	public static void setDet(){
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				if(Field.fieldNumbers[i][j] == 8)
					danger[i][j] = true;
	}
	
	/**
	 * Setzt die Booleans im danger-Array nach der Detonation wieder auf false.
	 * @param bomb
	 */
	public static void clearDanger (Bomb bomb){
		danger[bomb.x][bomb.y] = false;
			for (int l = 0; l <= bomb.l; l++)
				danger[bomb.x - l][bomb.y] = false;
			for (int r = 0; r <= bomb.r; r++)
				danger[bomb.x + r][bomb.y] = false;
			for (int o = 0; o <= bomb.o; o++)
				danger[bomb.x][bomb.y - o] = false;
			for (int u = 0; u <= bomb.u; u++)
				danger[bomb.x][bomb.y + u] = false;
					
		
	}
	
	/**
	 * Kein Kommentar....
	 */
	
	public static void surrounded(){
		if (danger[kiPl.x + 1][kiPl.y] && danger[kiPl.x - 1][kiPl.y]
				&& danger[kiPl.x][kiPl.y + 1] && danger[kiPl.x][kiPl.y - 1]
				&& Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)
				&& Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)
				&& Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)
				&& Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
			
					random4way();
		
		//Links, Unten, Oben
		else if (danger[kiPl.x - 1][kiPl.y]
				&& danger[kiPl.x][kiPl.y + 1] && danger[kiPl.x][kiPl.y - 1]
				&& Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)
				&& Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)
				&& Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
				
					random3way(1);
		
		//Rechts, Unten, Oben
		else if (danger[kiPl.x + 1][kiPl.y]
				&& danger[kiPl.x][kiPl.y + 1] && danger[kiPl.x][kiPl.y - 1]
				&& Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)
				&& Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl)
				&& Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
				
					random3way(2);
		
		//Links, Rechts, Oben
		else if (danger[kiPl.x + 1][kiPl.y] 
				&& danger[kiPl.x - 1][kiPl.y] && danger[kiPl.x][kiPl.y - 1]
				&& Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)
				&& Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)
				&& Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
			
					random3way(3);
		
		//Links, Rechts, Unten
		else if (danger[kiPl.x + 1][kiPl.y] 
				&& danger[kiPl.x - 1][kiPl.y] && danger[kiPl.x][kiPl.y + 1]
				&& Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl)
				&& Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl)
				&& Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl))
			
					random3way(4);
	}
	
	public static void random4way(){
		shuffle = Math.random();
		if (shuffle < 0.25)
			while(danger[kiPl.x][kiPl.y]){
				esc = true;
				moveUp();
				wait750ms();
				if(!Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
					break;
			}

		if (shuffle >= 0.25 && shuffle < 0.5)
			while(danger[kiPl.x][kiPl.y]){
				esc = true;
				moveDown();
				wait750ms();
				if(!Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl))
					break;
			}
		
		if (shuffle >= 0.5 && shuffle < 0.75)
			while(danger[kiPl.x][kiPl.y]){
				
				esc = true;
				moveLeft();
				wait750ms();
				if(!Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl))
					break;
			}
		
		if (shuffle >= 0.75)
			while(danger[kiPl.x][kiPl.y]){
				
				esc = true;
				moveRight();
				wait750ms();
				if(!Movement.checkMove(kiPl.x + 1, kiPl.y, kiPl))
					break;
			}
		
		esc = false;
	}
	
	public static void random3way(int w){
		shuffle = Math.random();
		
		//Links, Unten, Oben
		if (w == 1){
			if (shuffle < 0.33)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveLeft();
					wait750ms();
					if(!Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
						break;
				}

			if (shuffle >= 0.33 && shuffle < 0.66)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveDown();
					wait750ms();
					if(!Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl))
						break;
				}
			
			if (shuffle >= 0.66 && shuffle <= 1)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveUp();
					wait750ms();
					if(!Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl))
						break;
				}
		}
		
		//Rechts, Unten, Oben
		else if (w == 2){
			if (shuffle < 0.33)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveRight();
					wait750ms();
					if(!Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
						break;
				}

			if (shuffle >= 0.33 && shuffle < 0.66)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveDown();
					wait750ms();
					if(!Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl))
						break;
				}
			
			if (shuffle >= 0.66 && shuffle <= 1)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveUp();
					wait750ms();
					if(!Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl))
						break;
				}
		}
		
		//Links, Rechts, Unten
		else if (w == 3){
			if (shuffle < 0.33)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveLeft();
					wait750ms();
					if(!Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
						break;
				}

			if (shuffle >= 0.33 && shuffle < 0.66)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveDown();
					wait750ms();
					if(!Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl))
						break;
				}
			
			if (shuffle >= 0.66 && shuffle <= 1)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveRight();
					wait750ms();
					if(!Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl))
						break;
				}
		}
		
		//Links, Rechts, Oben	
		else if (w == 4){
			if (shuffle < 0.33)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveLeft();
					wait750ms();
					if(!Movement.checkMove(kiPl.x, kiPl.y - 1, kiPl))
						break;
				}

			if (shuffle >= 0.33 && shuffle < 0.66)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveRight();
					wait750ms();
					if(!Movement.checkMove(kiPl.x, kiPl.y + 1, kiPl))
						break;
				}
			
			if (shuffle >= 0.66 && shuffle <= 1)
				while(danger[kiPl.x][kiPl.y]){
					
					esc = true;
					moveUp();
					wait750ms();
					if(!Movement.checkMove(kiPl.x - 1, kiPl.y, kiPl))
						break;
				}
		}
		esc = false;
	}
}
