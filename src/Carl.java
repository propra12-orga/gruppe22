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
		Field.f.newPaint();
		Field.bombCnt += 1;
	}

}
