public class Carl extends Thread {

	public Carl(String str) {
		super();
	}

	public void run() {

		Interface.Player1.bCnt -= 1;
		Bomb bomb = new Bomb();
		Bomb.placeBomb(bomb.x, bomb.y);
		try {
			sleep(3000);
		} catch (InterruptedException e) {

		}

		Field.expPos[bomb.x][bomb.y] = true;
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

		Interface.Player1.bCnt += 1;
	}

}
