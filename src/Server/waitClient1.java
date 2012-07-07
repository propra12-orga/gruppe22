package Server;
import java.io.IOException;

public class waitClient1 implements Runnable
{

	/**
	 * <u>run:</u><br>
	 * Es wird eine Schleife gestartet die auf das <b>fieldNumbers</b> Feld wartet,
	 *  dass vom Klienten 1 gesendet wird. Wenn diese beendet ist wird der
	 *  <b>waitClient2</b> Thread gestartet und der Server sendet mit der Methode
	 *  <b>SendClient2</b> das aktuelle fieldNumbers Feld an den zweiten Klienten.
	 */
	public void run()
	{

		boolean check = true;
		try {
			System.out.println("waitClient1: gestartet.");
			while (check == true) {
				if (Server.streamReader1.available() == 1506)
				{
					for (int i = 0; i < 17; i++)
					{
						for (int j = 0; j < 21; j++) 
						{
							Server.fieldNumbers[j][i] = Server.streamReader1
								.readInt();
						}
					}
					for(int i=0;i<6;i++){
						Server.playerInfo[i]=Server.streamReader1.readInt();	
					}
					for(int i = 0;i<11;i++){
						Server.bombInfoInt[i]=Server.streamReader1.readInt();
					}
					for(int i=0;i<6;i++){
						Server.bombInfoBoolean[i]=Server.streamReader1.readBoolean();
					}
					check=false;
				}
			}
			System.out.println("waitClient1: beenden.");
			Thread t2 = new Thread(new waitClient2());
			t2.start();
			Server.SendClient2();
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("waitClient1: Konnte nichts empfangen.");
		}

	} // run schließen
} // innere Klasse schließen

