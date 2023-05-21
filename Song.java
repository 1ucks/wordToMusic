import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import java.util.ArrayList;
import org.jfugue.pattern.Pattern;

public abstract class Song {
	private int speed;// the speed or bpm of the piece
	private int feel;// the feel of the music
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
	public abstract Pattern generateDrumline(int length);

<<<<<<< HEAD
	// the melody
	public abstract String generateMelody(ArrayList<String> keys);


	// the bassline
	public abstract String generateBass(ArrayList<String> keys);




	
	//main method to play the song
=======
	

	// the melodies
	public abstract String generateMelody(ArrayList<String> keys);

	
	// the basslines
	public abstract String generateBassLine(ArrayList<String> keys);

		//main method to play the song
>>>>>>> b81d140e66f109ca1b12d8b6efe8e5cc159d4886

	public abstract void playSong(ArrayList<String> keys);



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
