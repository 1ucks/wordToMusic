import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import java.util.ArrayList;
import org.jfugue.pattern.Pattern;

public abstract class Song {
	private int speed;// the speed or bpm of the piece
	private int feel;// the feel of the music
	private Pattern intro; // it goes intro -> chorus -> verse 1 -> chorus -> verse 2 -> bridge -> chorus -> outro
	private Pattern chorus;
	private Pattern verse1;
	private Pattern verse2;
	private Pattern bridge;
	private Pattern Outro;
	private ArrayList<String> chords;// the chords included in the word submitted

	public Song(int leastCommonColor, int averageColour, ArrayList<String> theChords) {
		if (theChords != null) {
			setChords(theChords);
		} else {
			setChords(null);
		}
		setSpeed(averageColour);
		setFeel(leastCommonColor);
	}

	// the drumlines
	public abstract Pattern generateDrumline();

	public abstract Pattern generateDrumlineSwitchup();

	// the melodies
	public abstract String generateIntroMelody(ArrayList<String> keys);

	public abstract String generateChorusMelody(ArrayList<String> keys);

	public abstract String generateVerse1Melody(ArrayList<String> keys);

	public abstract String generateBridgeMelody(ArrayList<String> keys);

	public abstract String generateOutroMelody(ArrayList<String> keys);

	// the basslines
	public abstract String generateIntroBassLine(ArrayList<String> keys);

	public abstract String generateChorusBassLine(ArrayList<String> keys);

	public abstract String generateVerse1BassLine(ArrayList<String> keys);

	public abstract String generateBridgeBassLine(ArrayList<String> keys);

	public abstract String generateOutroBassLine(ArrayList<String> keys);

	// the background melodies
	public abstract String generateIntroBackground(ArrayList<String> keys);

	public abstract String generateChorusBackground(ArrayList<String> keys);

	public abstract String generateVerse1Background(ArrayList<String> keys);

	public abstract String generateBridgeBackground(ArrayList<String> keys);

	public abstract String generateOutroBackground(ArrayList<String> keys);
	
	//getters and setters
	public int getFeel() {
		return feel;
	}

	public void setFeel(int feel) {
		this.feel = feel;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<String> getChords() {
		return chords;
	}

	public void setChords(ArrayList<String> chords) {
		this.chords = chords;
	}
}
