import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import java.util.ArrayList;
public abstract class Song {
	private int time;//how long the user wants the piece to take
	private int speed;//the speed or bpm of the piece
	private int numOfMeasures;//the number of measures in the piece found by (time/bpm)/4
	private int feel;// the feel of the music
	private Pattern intro; // it goes intro -> chorus -> verse 1 -> chorus -> verse 2 -> bridge -> chorus -> outro
	private Pattern chorus;
	private Pattern verse1;
	private Pattern verse2;
	private Pattern bridge;
	private Pattern Outro;
	private ArrayList<String> chords;// the chords included in the word submitted
	public Song(int howLong, int mostCommonColour, int averageColour, ArrayList<String> theChords) {
		if(theChords != null) {
			chords = theChords;
		}else {
			chords = null;
		}
		speed = averageColour;
		feel = mostCommonColour;
		time = howLong;
		numOfMeasures = (time/speed)/4;
	}
	public abstract String generateDrumline();
	public abstract String generateDrumlineSwitchup();
	public abstract String generateChorusMelody();
	public abstract String generateVerse1Melody();
	public abstract String generateVerse2Melody();
	public abstract String generateBridgeMelody();
	
}
