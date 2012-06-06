public class Carl extends Thread {

	public Player player;
	
	public Carl(Player crtPlayer) {
		super();
		player = crtPlayer;
	}

	public void run() {

		player.bCnt -= 1;
		Bomb bomb = new Bomb(player);
		Bomb.placeBomb(bomb, player);
		try {
			for (int i = 0; i < 30; i++){
				sleep(100);
				isChain(bomb);
			}
		} catch (InterruptedException e) {
			interrupt();
		}
		Bomb.radCheck(bomb, player.rad);
		Bomb.detonate(bomb, player);


		Field.f = new Field();
		Field.f.newPaint();
		

		try {
			sleep(1000);
		} catch (InterruptedException e) {

		}

		Bomb.endDetonation(bomb);
		Field.expPos[bomb.x][bomb.y] = false;

		Field.f = new Field();
		Field.f.newPaint();
		

		try {
			sleep(50);
		} catch (InterruptedException e) {

		}

		player.bCnt += 1;
	}
	
	public void isChain(Bomb bomb){
		if (Field.expPos[bomb.x][bomb.y])
			this.interrupt();
	}

}
