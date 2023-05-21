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
		
		
		
		//the main variables to determine the variations in the song
		
		//IMPORTANT GETAVERAGECOLOR METHOD MUST BE RAN FIRST BECAUSE IT HAS THE ERROR DETECTION
		String averageColor = ip.getAverageColor();
		
		String dominantColor = ip.getDominantColor();
		String leastColor = ip.getLeastColor();
		
		//prints
		System.out.println("Printing the image :");
		System.out.println("Average Color: " + averageColor);
		System.out.println("Most Common Color: " + dominantColor);
		System.out.println("Least Common Color: " + leastColor+"\n");
		ip.printImage();
		
		//chords
		ArrayList<String> chords = new ArrayList<String>();
		
		
		//The song
		Song song = null;
		
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
			song = new LowFi(colorNameToInt(leastColor), colorNameToInt(averageColor), chords);
			break;
		
		case 5:
			song = new TechnoSong(colorNameToInt(leastColor), colorNameToInt(averageColor), chords);
			break;
		}
		int keyA = 0;
		int keyB = 0;
		int keyC = 0;
		int keyD = 0;
		int keyE = 0;
		int keyF = 0;
		//finding the chords based on the letters in the word
		String[] possibleChords = {"A", "B", "C", "D", "E", "F"};
		
		for(int i = 0; i < word.length(); i++) {
			
			for(int j = 0; j < possibleChords.length; j ++) {
				
				if(word.toUpperCase().charAt(i) == possibleChords[j].charAt(0)) {
					chords.add(possibleChords[j]);
				}
			}
		}
		//plays the song
		System.out.print("Type play to play the song: ");
		in.next();
		System.out.println("\nPlaying . . .");
		song.playSong(chords);
		
		
		
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
