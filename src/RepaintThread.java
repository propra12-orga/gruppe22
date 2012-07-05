/**
 * 
 * 
 * @author Jan Reckfort
 * 
 */

public class RepaintThread extends Thread {

	public RepaintThread() {
		super();
	}

	public void run() {
		try {

			while (true) {
				sleep(30);
//				Field.f = new Field();
				Field.f.newPaint();
			}
		} catch (InterruptedException e) {

		}

	}

}
