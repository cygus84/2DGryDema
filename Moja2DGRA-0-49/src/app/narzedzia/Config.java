package app.narzedzia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import app.panele.GamePanel;

public class Config {
	
	public GamePanel gp;
	
	public Config(GamePanel gp) {
		this.gp = gp;
	}
	
	public void saveConfig() {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			
			// FULL SCREEN
			if(gp.fullScrenOn == true) {
				bw.write("On");
			}
			
			if(gp.fullScrenOn == false){
				bw.write("Off");
			}
			
			bw.newLine();
			
			// MUSIC VOLUME 
			bw.write(String.valueOf(gp.music.volumeScale));
			bw.newLine();
			
			// SE VOLUME
			bw.write(String.valueOf(gp.se.volumeScale));
			bw.newLine();
			
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadConfig() {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			
			String s =  br.readLine();
			
			// FULL SCREEN
			if(s.equals("On")) {
				gp.fullScrenOn = true;
			}
			if(s.equals("Off")) {
				gp.fullScrenOn = false;
			}
			// music volume
			s = br.readLine();
			gp.music.volumeScale = Integer.parseInt(s);
			
			// se volume
			s = br.readLine();
			gp.se.volumeScale = Integer.parseInt(s);
			
			br.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
