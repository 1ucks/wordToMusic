import java.util.ArrayList;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import java.util.*;
import org.jfugue.pattern.Pattern;

public class BluesSong extends Song{
	private ArrayList<String> Drumline = new ArrayList<String>(Arrays.asList("A5", "C5", "E5", "G5"));
	public BluesSong(int howLong, int mostCommonColour, int averageColour, ArrayList<String> theChords) {
		super(howLong,  mostCommonColour,  averageColour, theChords);
	}
	//the drumlines
	public  Pattern generateDrumline() {
		
	}
	public  Pattern generateDrumlineSwitchup() {
		
	}
	
	//the melodies
	public String generateIntroMelody(ArrayList<String> keys) {
		
	}
	public String generateChorusMelody(ArrayList<String> keys) {
		
	}
	public String generateVerse1Melody(ArrayList<String> keys) {
		
	}
	public String generateBridgeMelody(ArrayList<String> keys) {
		
	}
	public String generateOutroMelody(ArrayList<String> keys) {
		
	}
	
	//the basslines
	public String generateIntroBassLine(ArrayList<String> keys) {
		
	}
	public String generateChorusBassLine(ArrayList<String> keys) {
		
	}
	public String generateVerse1BassLine(ArrayList<String> keys) {
		
	}
	public String generateBridgeBassLine(ArrayList<String> keys) {
		
	}
	public String generateOutroBassLine(ArrayList<String> keys) {
		
	}
	
	//the background melodies
	public String generateIntroBackground(ArrayList<String> keys) {
		
	}
	public String generateChorusBackground(ArrayList<String> keys) {
		
	}
	public String generateVerse1Background(ArrayList<String> keys) {
		
	}
	public String generateBridgeBackground(ArrayList<String> keys) {
		
	}
	public String generateOutroBackground(ArrayList<String> keys) {
		
	}
}
