import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import java.util.*;
import org.jfugue.pattern.Pattern;

public class RockSong extends Song{
	//the lengths of each note
	private double[] noteLengths = new double[] {4, 3, 2, 1.5, 1, 0.75, 0.5, 0.375,  0.25, 0.125};
	//the name of each note at the index 
	private String[] noteNames = new String[] {"w","h.", "h", "q.", "q", "i.", "i","s.", "s", "t"};
	//the special drumline instruments that sound cool
	private ArrayList<String> drumlineArr = new ArrayList<String>(Arrays.asList("R", "A5", "C5", "D5", "F5", "G5"));
	//constructor
	public RockSong(int howLong, int mostCommonColour, int averageColour, ArrayList<String> theChords) {
		super(howLong,  mostCommonColour,  averageColour, theChords);
	}
	//the drumlines
	public  Pattern generateDrumline() {
		//the result pattern
		Pattern drumline = new Pattern();
		//the rhythm with the kit
		Rhythm drumkit = new Rhythm();
		//the actual special instrument pattern
		String theDrumline = " V9 I ";
		//what measure and beat it is on
		int measureCount = 0;
		double beatCount = 0;
		//while to count the measure
		while(measureCount <= 6) {
			//while to count the beat
			while(beatCount <=4) {
				//picks a random beat that fits in the measure
				int theRandom = (int)(Math.random() * noteLengths.length);
				//if it dont fit it adds it skips and tries again
				if (beatCount + noteLengths[theRandom] > 4) {
					continue;
				}
				//picks a random instrument for the chosen beat
				int random2 = (int)(Math.random()*drumlineArr.size());
				//adds it to the big string pattern
				theDrumline += "" + drumlineArr.get(random2) + noteNames[theRandom]+ " ";
			}
			//marks the end of the measure
			theDrumline += " | ";
			//resets the beat counter
			beatCount = 0;
			//increments measure counter
			measureCount +=1;
		}
		drumline.add(theDrumline);
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
