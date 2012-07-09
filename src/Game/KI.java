package Game;
/**
 * Klasse fuer alle KI-Methoden
 * @author Pierre Schwarz
 *
 */


public class KI {
	
	/**
	 * Gibt an, wo der Computergegner sich in Gefahr befinden wuerde, von einer
	 * Bombe getroffen zu werden.
	 */
	static boolean[][] danger = initDangerArray();
	
	static int cnt = 0;
	static double shuffle;
	static boolean l, r, o, u = false;
	
	/**
	 * Gibt an, ob der Computergegner sich auf einem PowerUp befindet, der direkt
	 * neben einer Kiste liegt. Dieser soll dann keine Bombe legen, um eine Exception
	 * zu vermeiden.
	 */
	static boolean noBomb = false;
	
	/**
	 * Gibt an, ob der Computergegner sich grade auf der Flucht befindet. <br>
	 * In manchen Faellen ist es noetig, dass der Computergegner sich in einem
	 * Threaddurchlauf mehrmals bewegt. Damit dieser aber waehrrenddessen nicht
	 * wieder in die KI.checkEnv-Methode geht ist der Zugriff auf eben diese von diesem
	 * Boolean abhaengig.
	 */
	static boolean esc = false;
	
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
		Init.Player2.ctrl = "Bombe";
		Movement.getMovement(Init.Player2);
		wait750ms();
		
		if (Init.Player2.rad <= 1)
			while(!hasMoved){
				if (Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2) && Field.fieldNumbers[Init.Player2.x][Init.Player2.y - 2] != 2){
					moveUp();
					hasMoved = true;
				}
				else if (Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2) && Field.fieldNumbers[Init.Player2.x][Init.Player2.y + 2] != 2){
					moveDown();
					hasMoved = true;
				}		
				else if (Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2) && Field.fieldNumbers[Init.Player2.x - 2][Init.Player2.y] != 2){
					moveLeft();
					hasMoved = true;
				}
				else if (Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2) && (danger[Init.Player2.x][Init.Player2.y - 1] || danger[Init.Player2.x][Init.Player2.y + 1])){
					moveLeft();
					hasMoved = true;
				}
				else if (Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2) && (danger[Init.Player2.x][Init.Player2.y - 1] || danger[Init.Player2.x][Init.Player2.y + 1])){
					moveRight();
					hasMoved = true;
				}
				else if (Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2) && (danger[Init.Player2.x - 1][Init.Player2.y] || danger[Init.Player2.x + 1][Init.Player2.y])){
					moveDown();
					hasMoved = true;
				}
				else if (Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2) && (danger[Init.Player2.x - 1][Init.Player2.y] || danger[Init.Player2.x + 1][Init.Player2.y])){
					moveUp();
					hasMoved = true;
				}
				else {
					moveRight();
					hasMoved = true;
				}
			}
			
		else {
			while(danger[Init.Player2.x][Init.Player2.y]){
				esc = true;
				if (Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2) && Field.fieldNumbers[Init.Player2.x][Init.Player2.y - 2] != 2){
					moveUp();
					hasMoved = true;
				}
				else if (Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2) && Field.fieldNumbers[Init.Player2.x][Init.Player2.y + 2] != 2){
					moveDown();
					hasMoved = true;
				}		
				else if (Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2) && Field.fieldNumbers[Init.Player2.x - 2][Init.Player2.y] != 2){
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
		esc = false;
		synchronized(Paul.kiThread){
			Paul.kiThread.notify();
		}
		hasMoved = false;
	}
	
	/**
	 * Computergegner bewegt sich nach Oben.
	 */
	
	public static void moveUp(){
		Init.Player2.ctrl = "Oben";
		Movement.getMovement(Init.Player2);
		//System.out.println("x = " + Init.Player2.x + " | y = " + Init.Player2.y);
	}
	
	/**
	 * Computergegner bewegt sich nach Unten.
	 */
	
	public static void moveDown(){
		Init.Player2.ctrl = "Unten";
		Movement.getMovement(Init.Player2);
		//System.out.println("x = " + Init.Player2.x + " | y = " + Init.Player2.y);
	}
	
	/**
	 * Computergegner bewegt sich nach Links.
	 */
	
	public static void moveLeft(){
		Init.Player2.ctrl = "Links";
		Movement.getMovement(Init.Player2);
		//System.out.println("x = " + Init.Player2.x + " | y = " + Init.Player2.y);
	}
	
	/**
	 * Computergegner bewegt sich nach Rechts.
	 */
	
	public static void moveRight(){
		Init.Player2.ctrl = "Rechts";
		Movement.getMovement(Init.Player2);
		//System.out.println("x = " + Init.Player2.x + " | y = " + Init.Player2.y);
	}
	
	/**
	 * Waehlt eine Bewegungsrichtung nach dem Zufallsprinzip.
	 */
	
	public static void chooseDir(){
		if (danger[Init.Player2.x][Init.Player2.y]){
			if (!danger[Init.Player2.x + 1][Init.Player2.y] && Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2)){
				moveRight();
				hasMoved = true;
			}
			else if (!danger[Init.Player2.x - 1][Init.Player2.y] && Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2)){
				moveLeft();
				hasMoved = true;
			}
			else if (!danger[Init.Player2.x][Init.Player2.y + 1] && Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2)){
				moveDown();
				hasMoved = true;
			}
			else if (!danger[Init.Player2.x][Init.Player2.y - 1] && Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2)){
				moveUp();
				hasMoved = true;
			}
		}
			
		while(!hasMoved){
			shuffle = Math.random();
			if (shuffle < 0.25 && Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2) && !danger[Init.Player2.x][Init.Player2.y - 1]){
				moveUp();
				hasMoved = true;
			}
			if (shuffle >= 0.25 && shuffle < 0.5 && Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2) && !danger[Init.Player2.x][Init.Player2.y + 1]){
				moveDown();
				hasMoved = true;
			}		
			if (shuffle >= 0.5 && shuffle < 0.75 && Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2) && !danger[Init.Player2.x - 1][Init.Player2.y]){
				moveLeft();
				hasMoved = true;
			}
			if (shuffle >= 0.75 && Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2) && !danger[Init.Player2.x + 1][Init.Player2.y]){
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
		for (int i = 0; i <= Init.Player2.rad; i++){
			if (Init.Player2.x - i >= 0)
				if (Field.fieldNumbers[Init.Player2.x - i][Init.Player2.y] == 3 ){
					placeBomb();
					//Init.Player2.danger = true;
				}
			if(Init.Player2.x + i < 21)
				if(Field.fieldNumbers[Init.Player2.x + i][Init.Player2.y] == 3){
					placeBomb();
					//Init.Player2.danger = true;
				}
			if (Init.Player2.y - i >= 0)
				if (Field.fieldNumbers[Init.Player2.x][Init.Player2.y - i] == 3){
					placeBomb();
					//Init.Player2.danger = true;
				}			
			if (Init.Player2.y + i < 17)
				if (Field.fieldNumbers[Init.Player2.x][Init.Player2.y + i] == 3){
					placeBomb();
					//Init.Player2.danger = true;
				}	
		}
	}
		
	/**
	 * Ueberprueft, ob sich eine Box in Bombenreichweite befindet.
	 */
	
	public static void checkBoxes(){
		for (int i = 1; i <= Init.Player2.rad; i++){
			if (Init.Player2.x + i < 21)
				if (Field.fieldNumbers[Init.Player2.x + i][Init.Player2.y] == 2){
					
					esc = true;
					if(!noBomb) placeBomb();
					wait750ms();
					esc = false;
					synchronized(Paul.kiThread){
						Paul.kiThread.notify();
					}
			}
					
			else if (Init.Player2.x - i >= 0)
				if (Field.fieldNumbers[Init.Player2.x - i][Init.Player2.y] == 2){
					
					esc = true;
					if(!noBomb) placeBomb();
					wait750ms();
					esc = false;
					synchronized(Paul.kiThread){
						Paul.kiThread.notify();
					}
			}
				
			else if (Init.Player2.y + i < 17)
				if (Field.fieldNumbers[Init.Player2.x][Init.Player2.y + i] == 2){
					
					esc = true;
					if(!noBomb) placeBomb();
					wait750ms();
					esc = false;
					synchronized(Paul.kiThread){
						Paul.kiThread.notify();
					}
			}
				
			else if (Init.Player2.y - i >= 0)
				if (Field.fieldNumbers[Init.Player2.x][Init.Player2.y - i] == 2){
					
					esc = true;
					if(!noBomb) placeBomb();
					wait750ms();
					esc = false;
					synchronized(Paul.kiThread){
						Paul.kiThread.notify();
					}
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
				
				if (shuffle < 0.33 && Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveDown();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveLeft();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveRight();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}
			}
		if (s == "Unten")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveUp();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveLeft();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveRight();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}
			}
		if (s == "Links")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveDown();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveUp();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveRight();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}
			}
		if (s == "Rechts")
			while(!hasMoved){
				shuffle = Math.random();
				
				if (shuffle < 0.33 && Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveDown();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}
				if (shuffle >= 0.33 && shuffle < 0.66 && Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveLeft();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
					hasMoved = true;
				}		
				if (shuffle >= 0.66 && Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2)){
					while (cnt + 1 <= Init.Player2.checkRad){
						moveUp();
						wait750ms();
						cnt++;
					}
					Init.Player2.danger = false;
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

			for (int i = 0; i <= Init.Player2.checkRad && Init.Player2.y - i >= 0; i++)
				if (Field.bombPos[Init.Player2.x][Init.Player2.y - i] || Field.fieldNumbers[Init.Player2.x][Init.Player2.y - i] == 8){
					Init.Player2.danger = true;
					checkEsc("Oben");
				}

			for (int i = 0; i <= Init.Player2.checkRad && Init.Player2.y + i < 17; i++)
				if (Field.bombPos[Init.Player2.x][Init.Player2.y + i] || Field.fieldNumbers[Init.Player2.x][Init.Player2.y + i] == 8){
					Init.Player2.danger = true;
					checkEsc("Unten");
				}
		
			for (int i = 0; i <= Init.Player2.checkRad && Init.Player2.x + i < 21; i++)
				if (Field.bombPos[Init.Player2.x + i][Init.Player2.y] || Field.fieldNumbers[Init.Player2.x + i][Init.Player2.y] == 8){
					Init.Player2.danger = true;
					checkEsc("Rechts");
				}

			for (int i = 0; i <= Init.Player2.checkRad && Init.Player2.x - i >= 0; i++)
				if (Field.bombPos[Init.Player2.x - i][Init.Player2.y] || Field.fieldNumbers[Init.Player2.x - i][Init.Player2.y] == 8){
					Init.Player2.danger = true;
					checkEsc("Links");
				}
		}

	}*/
	
	/**
	 * Laesst den Computergegner 3 Sekunden warten.
	 */
	public static void wait3sec(){
		try{
			Thread.sleep(3000);
		}
		catch(InterruptedException e){
			
		}
	}
	
	/**
	 * Laesst den Cumputergegner 750ms warten.
	 */
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
		if (danger[Init.Player2.x + 1][Init.Player2.y] && danger[Init.Player2.x - 1][Init.Player2.y]
				&& danger[Init.Player2.x][Init.Player2.y + 1] && danger[Init.Player2.x][Init.Player2.y - 1]
				&& Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2)
				&& Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2)
				&& Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2)
				&& Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
			
					random4way();
		
		//Links, Unten, Oben
		else if (danger[Init.Player2.x - 1][Init.Player2.y]
				&& danger[Init.Player2.x][Init.Player2.y + 1] && danger[Init.Player2.x][Init.Player2.y - 1]
				&& Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2)
				&& Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2)
				&& Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
				
					random3way(1);
		
		//Rechts, Unten, Oben
		else if (danger[Init.Player2.x + 1][Init.Player2.y]
				&& danger[Init.Player2.x][Init.Player2.y + 1] && danger[Init.Player2.x][Init.Player2.y - 1]
				&& Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2)
				&& Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2)
				&& Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
				
					random3way(2);
		
		//Links, Rechts, Oben
		else if (danger[Init.Player2.x + 1][Init.Player2.y] 
				&& danger[Init.Player2.x - 1][Init.Player2.y] && danger[Init.Player2.x][Init.Player2.y - 1]
				&& Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2)
				&& Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2)
				&& Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
			
					random3way(3);
		
		//Links, Rechts, Unten
		else if (danger[Init.Player2.x + 1][Init.Player2.y] 
				&& danger[Init.Player2.x - 1][Init.Player2.y] && danger[Init.Player2.x][Init.Player2.y + 1]
				&& Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2)
				&& Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2)
				&& Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2))
			
					random3way(4);
	}
	
	public static void random4way(){
		shuffle = Math.random();
		if (shuffle < 0.25)
			while(danger[Init.Player2.x][Init.Player2.y]){
				esc = true;
				moveUp();
				wait750ms();
				if(!Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
					break;
			}

		if (shuffle >= 0.25 && shuffle < 0.5)
			while(danger[Init.Player2.x][Init.Player2.y]){
				esc = true;
				moveDown();
				wait750ms();
				if(!Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2))
					break;
			}
		
		if (shuffle >= 0.5 && shuffle < 0.75)
			while(danger[Init.Player2.x][Init.Player2.y]){
				
				esc = true;
				moveLeft();
				wait750ms();
				if(!Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2))
					break;
			}
		
		if (shuffle >= 0.75)
			while(danger[Init.Player2.x][Init.Player2.y]){
				
				esc = true;
				moveRight();
				wait750ms();
				if(!Movement.checkMove(Init.Player2.x + 1, Init.Player2.y, Init.Player2))
					break;
			}
		
		esc = false;
	}
	
	public static void random3way(int w){
		shuffle = Math.random();
		
		//Links, Unten, Oben
		if (w == 1){
			if (shuffle < 0.33)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveLeft();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
						break;
				}

			if (shuffle >= 0.33 && shuffle < 0.66)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveDown();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2))
						break;
				}
			
			if (shuffle >= 0.66 && shuffle <= 1)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveUp();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2))
						break;
				}
		}
		
		//Rechts, Unten, Oben
		else if (w == 2){
			if (shuffle < 0.33)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveRight();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
						break;
				}

			if (shuffle >= 0.33 && shuffle < 0.66)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveDown();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2))
						break;
				}
			
			if (shuffle >= 0.66 && shuffle <= 1)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveUp();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2))
						break;
				}
		}
		
		//Links, Rechts, Unten
		else if (w == 3){
			if (shuffle < 0.33)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveLeft();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
						break;
				}

			if (shuffle >= 0.33 && shuffle < 0.66)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveDown();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2))
						break;
				}
			
			if (shuffle >= 0.66 && shuffle <= 1)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveRight();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2))
						break;
				}
		}
		
		//Links, Rechts, Oben	
		else if (w == 4){
			if (shuffle < 0.33)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveLeft();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x, Init.Player2.y - 1, Init.Player2))
						break;
				}

			if (shuffle >= 0.33 && shuffle < 0.66)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveRight();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x, Init.Player2.y + 1, Init.Player2))
						break;
				}
			
			if (shuffle >= 0.66 && shuffle <= 1)
				while(danger[Init.Player2.x][Init.Player2.y]){
					
					esc = true;
					moveUp();
					wait750ms();
					if(!Movement.checkMove(Init.Player2.x - 1, Init.Player2.y, Init.Player2))
						break;
				}
		}
		esc = false;
	}
}
