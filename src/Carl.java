public class Carl extends Thread {

	public Player player;
	
	public Carl(Player crtPlayer) {
		super();
		player = crtPlayer;
	}

	public void run() {

		player.bCnt -= 1;
		Bomb bomb = new Bomb(player);
		Bomb.placeBomb(bomb.x, bomb.y);
		try {
			sleep(3000);
		} catch (InterruptedException e) {

		}

		Field.expPos[bomb.x][bomb.y] = true;
		Bomb.radCheck(bomb.x, bomb.y, player.rad);
		Bomb.detonate(bomb.x, bomb.y);

		Field.f.newPaint();

		try {
			sleep(1000);
		} catch (InterruptedException e) {

		}

		Bomb.endDetonation(bomb.x, bomb.y);
		Field.expPos[bomb.x][bomb.y] = false;
		Field.f.newPaint();

		try {
			sleep(50);
		} catch (InterruptedException e) {

		}

		player.bCnt += 1;
	}

}
