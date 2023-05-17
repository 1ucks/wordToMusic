package wordToMusic;

import java.io.IOException;
import java.util.*;

import org.jfugue.player.Player;

public class musicMain {
	public static void main(String[] args) throws IOException {
		//Our Runner
		
		Scanner in = new Scanner(System.in);
		
		//used to play the music
		Player player = new Player();
		
		
		System.out.print("Enter a word or phrase that you want to make a song with: ");
		
		//main input from user
		String word = in.nextLine();
		System.out.println("\n");
		//class to deal with images
		ImageProcessor ip = new ImageProcessor(word);
		
		//searches for the word using the api
		ip.search();
		
		System.out.println("Link to image chosen: " + ip.getUrl());
		
		//the main variables to determine the variations in the song
		String averageColor = ip.getAverageColor();
		String dominantColor = ip.getDominantColor();
		String leastColor = ip.getLeastColor();
		
		//prints
		System.out.println("Average Color: " + averageColor);
		System.out.println("Most Common Color: " + dominantColor);
		System.out.println("Least Common Color: " + leastColor);
		ip.printImage();
		
		//chords
		ArrayList<String> chords = new ArrayList<String>();
		
		
		//The song
		Song song;
		
		//determining which type of song it is based on the most common color
		switch(colorNameToInt(dominantColor)) {
		//red, orange, and yellow are rock
		//blue is the blues
		//green and purple are techno
		
		case 0:
			song = new RockSong(colorNameToInt(leastColor), colorNameToInt(averageColor), chords);
			break;
		
		case 1:
			song = new RockSong(colorNameToInt(leastColor), colorNameToInt(averageColor), chords);
			break;
		
		case 2:
			song = new RockSong(colorNameToInt(leastColor), colorNameToInt(averageColor), chords);
			break;
		case 3:
			song = new TechnoSong(colorNameToInt(leastColor), colorNameToInt(averageColor), chords);
			break;
		
		case 4:
			song = new BluesSong(colorNameToInt(leastColor), colorNameToInt(averageColor), chords);
			break;
		
		case 5:
			song = new TechnoSong(colorNameToInt(leastColor), colorNameToInt(averageColor), chords);
			break;
		}
		
		
		
		
		
	}
	public static int colorNameToInt(String name) {
		switch(name) {
		case "red":
			return 0;
		case "orange":
			return 1;
		case "yellow":
			return 2;
		case "green":
			return 3;
		case "blue":
			return 4;
		case "purple":
			return 5;
		}
		return 0;
	}
}
