import java.io.*;
import java.applet.*;

/**
 * Simple Sound-Klasse. Gegenebenfalls wegen AudioClip ein wenig ueberholt?
 * @author Pierre Schwarz
 *
 */

public class Sound {
	
	/**
	 * Quelle des Sounds.
	 */
	File source;
	
	/**
	 * Wird abgespielt.
	 */
	AudioClip sound;
	
	/**
	 * Konstruktor <br>
	 * Erhaelt den jeweiligen String zur Sounddatei, welche dann abgespielt werden soll.
	 * @param String s
	 */
	
	public Sound(String s){
		source = new File(s);
		
		try{
			sound = Applet.newAudioClip(source.toURL());
			sound.play();
		}
		catch(Exception e){
			System.out.println("Error: ");
			e.printStackTrace();
		}
	}
}
