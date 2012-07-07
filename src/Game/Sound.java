package Game;
import java.io.*;
import java.applet.*;

/**
 * Simple Sound-Klasse. Gegebenenfalls wegen AudioClip ein wenig ueberholt?
 * Jeder Sound ist Freeware, gefunden auf folgenden Plattformen:
 * 
 * http://www.talkingwav.com/various_wav_sounds.html
 * http://www.villagegeek.com/HTML/wavfiles1.htm
 * 
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
