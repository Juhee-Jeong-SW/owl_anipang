import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	AudioInputStream ais;
	Clip clip;
	
	public Sound() {
		
	}
	
	public void getSound(String file) {
		try {			
			ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	



}
