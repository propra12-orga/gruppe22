public class Carl extends Thread {

	public Carl(String str) {
		super();
	}

	public void run() {

		Field.bombCnt -= 1;

		Bomb.placeBomb();
		try {
			sleep(3000);
		} catch (InterruptedException e) {

		}

		Field.isBomb = false;
		Field.isExp = true;
		Bomb.detonate();

		Field.f.newPaint();

		try {
			sleep(1000);
		} catch (InterruptedException e) {

		}

		Bomb.endDetonation();
		Field.isExp = false;
		Field.f.newPaint();

		try {
			sleep(50);
		} catch (InterruptedException e) {

		}

		Field.bombCnt += 1;
	}

}
