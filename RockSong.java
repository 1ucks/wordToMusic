import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import java.util.*;
import org.jfugue.pattern.Pattern;

public class RockSong extends Song {
	// constructor
	public RockSong( int mostCommonColour, int averageColour, ArrayList<String> theChords) {
		super(mostCommonColour, averageColour, theChords);
	}

	// the array of melodic rock instruments
	private static String[] melodicInstruments = new String[] { "OVERDRIVEN_GUITAR", "GUITAR_FRET_NOISE", "STEEL_STRING_GUITAR",
			"DISTORTION_GUITAR", "HARMONICA", "ROCK_ORGAN", "TANGO_ACCORDIAN", "BARITONE_SAX", "SKAKUHACHI",
			"BLOWN_BOTTLE", "DULCIMER", "GUNSHOT", "SHANAI" };
	//list of all pitches possible in assending order
	private static String[] pitches = new String[] {"Ab", "A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G"};
	// the lengths of each note
	private static double[] noteLengths = new double[] { 4, 3, 2, 1.5, 1, 0.75, 0.5, 0.375, 0.25, 0.125 };
	// the name of each note at the index
	private static String[] noteNames = new String[] { "w", "h.", "h", "q.", "q", "i.", "i", "s.", "s", "t" };
	// list of Rhythm pieces for drumkit
	private static String[] rhythmParts = new String[] { "O", "S", "*", "o", "s", "^", "`", "+", "X", "x" };
	// the drumkit for the switchup with twice the instruments
	private static String[] rhythmParts2 = new String[] { "O", "S", "*", "X", "^", "o", "s", "`", "+", "x", "O", "S",
			"*", "X", "^" };
	// the special drumline instruments that sound cool
	private static ArrayList<String> drumlineArr = new ArrayList<String>(
			Arrays.asList("R", "A5", "C5", "D5", "F5", "G5"));

	// the drumlines
	public Pattern generateDrumline() {
		// the result pattern
		Pattern drumline = new Pattern();
		// the rhythm with the kit
		Rhythm drumkit = new Rhythm("................................................");
		// the actual special instrument pattern
		String theDrumline = " V9 I ";
		// what measure and beat it is on
		int measureCount = 0;
		double beatCount = 0;
		// while to count the measure
		while (measureCount < 6) {
			// while to count the beat
			while (beatCount < 4) {
				// picks a random beat that fits in the measure
				int theRandom = (int) (Math.random() * noteLengths.length);
				// if it dont fit it adds it skips and tries again
				if (beatCount + noteLengths[theRandom] > 4) {
					continue;

				}
				// picks a random instrument for the chosen beat
				int random2 = (int) (Math.random() * drumlineArr.size());
				// adds it to the big string pattern
				theDrumline += "" + drumlineArr.get(random2) + noteNames[theRandom] + " ";
				// increment beatCounter
				beatCount += noteLengths[theRandom];
			}
			// marks the end of the measure
			theDrumline += " | ";
			// resets the beat counter
			beatCount = 0;
			// increments measure counter
			measureCount += 1;
		}
		// adds the beats to the total drumline
		drumline.add(theDrumline);
		// for loop for the instruments in the drumkit
		for (int outCountRhythm = 0; outCountRhythm < rhythmParts.length; outCountRhythm += 1) {
			// string for the drumkit music
			String rhythmAdder = "";
			// goes through each beat in the drumkit
			for (int inCountRhythm = 0; inCountRhythm < 48; inCountRhythm += 1) {
				// if its not on bass drum, snare, or crash it will play random notes
				if (outCountRhythm > 2) {
					// it generates a random number 0-3
					int tF = (int) (Math.random() * 4);
					// if the number is one
					if (tF == 1) {
						// it adds the instrument/note to the music
						rhythmAdder += rhythmParts[outCountRhythm];
					} else { // otherwise it adds a rest
						rhythmAdder += ".";
					}
					// if it is snare, bass or crash it plays on beats 2 and 4 like a rock song has
					// emphasis on
				} else {
					// if its on beat 2 or 4
					if ((inCountRhythm - 2) % 4 == 0) {
						// it add the instrument/note to the string
						rhythmAdder += rhythmParts[outCountRhythm];
					} else { // otherwise it rests
						rhythmAdder += ".";
					}
				}
			}
			// it adds the string as a layer to the rhythm
			drumkit.addLayer(rhythmAdder);
		}
		// it adds the rhythm to the pattern
		drumline.add(drumkit);
		// it returns the pattern
		return drumline;
	}

	public Pattern generateDrumlineSwitchup() {
		// the result pattern
		Pattern drumline = new Pattern();
		// the rhythm with the kit
		Rhythm drumkit = new Rhythm("................................................");
		// the actual special instrument pattern
		String theDrumline = " V9 I ";
		// what measure and beat it is on
		int measureCount = 0;
		double beatCount = 0;
		// while to count the measure
		while (measureCount < 2) {
			// while to count the beat
			while (beatCount < 4) {
				// picks a random beat that fits in the measure
				int theRandom = (int) (Math.random() * noteLengths.length);
				// if it dont fit it adds it skips and tries again
				if (beatCount + noteLengths[theRandom] > 4) {
					continue;

				}
				// picks a random instrument for the chosen beat
				int random2 = (int) (Math.random() * drumlineArr.size());
				// adds it to the big string pattern
				theDrumline += "" + drumlineArr.get(random2) + noteNames[theRandom] + " ";
				// increment beatCounter
				beatCount += noteLengths[theRandom];
			}
			// marks the end of the measure
			theDrumline += " | ";
			// resets the beat counter
			beatCount = 0;
			// increments measure counter
			measureCount += 1;
		}
		// adds the beats to the total drumline
		drumline.add(theDrumline);
		// for loop for the instruments in the drumkit
		for (int outCountRhythm = 0; outCountRhythm < rhythmParts2.length; outCountRhythm += 1) {
			// string for the drumkit music
			String rhythmAdder = "";
			// goes through each beat in the drumkit
			for (int inCountRhythm = 0; inCountRhythm < 16; inCountRhythm += 1) {
				// if its not on bass drum, snare, or crash it will play random notes
				if (outCountRhythm > 5) {
					// it generates a random number 0-3
					int tF = (int) (Math.random() * 1.5);
					// if the number is one
					if (tF == 0) {
						// it adds the instrument/note to the music
						rhythmAdder += rhythmParts2[outCountRhythm];
					} else { // otherwise it adds a rest
						rhythmAdder += ".";
					}
					// if it is snare, bass or crash it plays on beats 2 and 4 like a rock song has
					// emphasis on
				} else {
					// if its on beat 2 or 4
					if ((inCountRhythm - 2) % 4 == 0) {
						// it add the instrument/note to the string
						rhythmAdder += rhythmParts2[outCountRhythm];
					} else { // otherwise it rests
						rhythmAdder += ".";
					}
				}
			}
			// it adds the string as a layer to the rhythm
			drumkit.addLayer(rhythmAdder);
		}
		// it adds the rhythm to the pattern
		drumline.add(drumkit);
		// it returns the pattern
		return drumline;
	}

	// the melodies
	public String generateIntroMelody(ArrayList<String> keys) {
		//intro will be 4 measures aka 2 drum switchups for intrest with a key switch in measure 3
		int key1;
		int key2;
		if(keys == null) {
			key1 = (int)(Math.random()*11);
			key2 = (int)(Math.random()*11);
		}else {
			if(keys.size() == 1) {
				int index;
				for(index =0; index<pitches.length; index +=1) {
					if(pitches[index] == keys.get(0)) {
						break;
					}
				}
				key1 = index;
				key2 = (int)(Math.random()*11); 
			}else {
				int index;
				for(index =0; index<pitches.length; index +=1) {
					if(pitches[index] == keys.get(0)) {
						break;
					}
				}
				key1 = index;
				keys.add(pitches[key1]);
				keys.remove(0);
				for(index =0; index<pitches.length; index +=1) {
					if(pitches[index] == keys.get(0)) {
						break;
					}
				}
				key2 = index;
				keys.add(pitches[key2]);
				keys.remove(0);
			}
		}
		String theMelody = "";
		String thePattern = "";
		switch(getFeel()){
		case(0):
			//angry chords: idk probably like 1/2step up or down to create discordanent sounds
				for(int measureCount = 1; measureCount<=4; measureCount +=1) {
					if (measureCount ==3) {
						key1 = key2;
					}
					double beatCount = 0; 
					int octave = (int)(Math.random()*4 + 5); 										
					while(beatCount<4) { 															
						// picks a random beat that fits in the measure
						int theRandom = (int) (Math.random() * noteLengths.length);
						// if it does not fit it adds it skips and tries again
						if (beatCount + noteLengths[theRandom] > 4) {
							continue;
						}
						int random2 = (int)(Math.random()* 9);
						if(random2 != 0) {
							thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
						}else {
							thePattern += "R" + octave + noteNames[theRandom] + " ";
						}
						int nextNote = ((int)(Math.random()*7))*2 -7;
						if(key1 + nextNote >= pitches.length) {								
							octave +=1;
							key1 = key1 + nextNote - pitches.length;
						}else if(key1 + nextNote < 0) {						
							octave -=1;
							key1 = pitches.length + (key1 +nextNote);
						}else {																
							key1 +=nextNote;
						}
						//increment beatCount
						beatCount += noteLengths[theRandom];
					}
					thePattern += " ";
				}
			for(int count = 0; count < 4; count +=1) {
				theMelody += "V" + count + " I[" + melodicInstruments[(int)(Math.random()*13)] + "] " +thePattern + "\n"; 
			}
			break;
		case(1):
			//orange happy and slightly angry and slightly royal sounding idk
			//keeps track of the measure
			for(int measureCount = 1; measureCount<=4; measureCount +=1) {
				//on measure 3 it switches the key
				if (measureCount ==3) {
					key1 = key2;
				}
				//keeps track of what beat its on
				double beatCount = 0;
				//dictates the octave
				int octave = (int)(Math.random()*3 + 5); 
				//while a measure is not complete
				while(beatCount<4) { 															
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					//it adds to the pattern the note name, the octave, and time length
					int random2 = (int)(Math.random()* 9);
					if(random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					}else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					//this will dictate the next note via a random number that correlates to a themeatically sensible next note
					int nextNote = ((int)(Math.random()*5));
					switch(nextNote) {
					//the possible next notes
					case (0):
						nextNote = -8;
						break;
					case (1):
						nextNote = -6;
						break;
					case (2):
						nextNote = 0;
						break;
					case (3):
						nextNote = 6;
						break;
					case (4):
						nextNote = 8;
						break;
					}
					//if the next note is more or less than possible it changes the octave
					if(key1 + nextNote >= pitches.length) {								
						octave +=1;
						key1 = key1 + nextNote - pitches.length;
					}else if(key1 + nextNote < 0) {						
						octave -=1;
						key1 = pitches.length + (key1 +nextNote);
					}else {																
						key1 +=nextNote;
					}
					//increment beatCount
					beatCount += noteLengths[theRandom];
				}
				//it gets added to the pattern
				thePattern += " ";
			}
		//it does it 4 times with different instruments
		for(int count = 0; count < 4; count +=1) {
			theMelody += "V" + count + " I[" + melodicInstruments[(int)(Math.random()*13)] + "] " +thePattern + "\n"; 
		}
			break;
		case(2):
			//yellow
			//keeps track of the measure
			for(int measureCount = 1; measureCount<=4; measureCount +=1) {
				//on measure 3 it switches the key
				if (measureCount ==3) {
					key1 = key2;
				}
				//keeps track of what beat its on
				double beatCount = 0;
				//dictates the octave
				int octave = (int)(Math.random()*2 + 5); 
				//while a measure is not complete
				while(beatCount<4) { 															
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					//it adds to the pattern the note name, the octave, and time length
					int random2 = (int)(Math.random()* 9);
					if(random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					}else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					//this will dictate the next note via a random number that correlates to a themeatically sensible next note
					int nextNote = ((int)(Math.random()*5));
					switch(nextNote) {
					//the possible next notes
					case (0):
						nextNote = -3;
						break;
					case (1):
						nextNote = -6;
						break;
					case (2):
						nextNote = 0;
						break;
					case (3):
						nextNote = 6;
						break;
					case (4):
						nextNote = 3;
						break;
					}
					//if the next note is more or less than possible it changes the octave
					if(key1 + nextNote >= pitches.length) {								
						octave +=1;
						key1 = key1 + nextNote - pitches.length;
					}else if(key1 + nextNote < 0) {						
						octave -=1;
						key1 = pitches.length + (key1 +nextNote);
					}else {																
						key1 +=nextNote;
					}
					//increment beatCount
					beatCount += noteLengths[theRandom];
				}
				//it gets added to the pattern
				thePattern += " ";
			}
		//it does it 4 times with different instruments
		for(int count = 0; count < 4; count +=1) {
			theMelody += "V" + count + " I[" + melodicInstruments[(int)(Math.random()*13)] + "] " +thePattern + "\n"; 
		}
			break;
		case(3):
			//green
			//keeps track of the measure
			for(int measureCount = 1; measureCount<=4; measureCount +=1) {
				//on measure 3 it switches the key
				if (measureCount ==3) {
					key1 = key2;
				}
				//keeps track of what beat its on
				double beatCount = 0;
				//dictates the octave
				int octave = (int)(Math.random()*3 + 5); 
				//while a measure is not complete
				while(beatCount<4) { 															
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					//it adds to the pattern the note name, the octave, and time length
					int random2 = (int)(Math.random()* 9);
					if(random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					}else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					//this will dictate the next note via a random number that correlates to a themeatically sensible next note
					int nextNote = ((int)(Math.random()*5));
					switch(nextNote) {
					//the possible next notes
					case (0):
						nextNote = -3;
						break;
					case (1):
						nextNote = -1;
						break;
					case (2):
						nextNote = 0;
						break;
					case (3):
						nextNote = 1;
						break;
					case (4):
						nextNote = 3;
						break;
					}
					//if the next note is more or less than possible it changes the octave
					if(key1 + nextNote >= pitches.length) {								
						octave +=1;
						key1 = key1 + nextNote - pitches.length;
					}else if(key1 + nextNote < 0) {						
						octave -=1;
						key1 = pitches.length + (key1 +nextNote);
					}else {																
						key1 +=nextNote;
					}
					//increment beatCount
					beatCount += noteLengths[theRandom];
				}
				//it gets added to the pattern
				thePattern += " ";
			}
		//it does it 4 times with different instruments
		for(int count = 0; count < 4; count +=1) {
			theMelody += "V" + count + " I[" + melodicInstruments[(int)(Math.random()*13)] + "] " +thePattern + "\n"; 
		}
			break;
		case(4):
			//sad
			//keeps track of the measure
			for(int measureCount = 1; measureCount<=4; measureCount +=1) {
				//on measure 3 it switches the key
				if (measureCount ==3) {
					key1 = key2;
				}
				//keeps track of what beat its on
				double beatCount = 0;
				//dictates the octave
				int octave = (int)(Math.random()*4 + 3); 
				//while a measure is not complete
				while(beatCount<4) { 															
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					//it adds to the pattern the note name, the octave, and time length
					int random2 = (int)(Math.random()* 9);
					if(random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					}else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					//this will dictate the next note via a random number that correlates to a themeatically sensible next note
					int nextNote = ((int)(Math.random()*5));
					switch(nextNote) {
					//the possible next notes
					case (0):
						nextNote = -14;
						break;
					case (1):
						nextNote = -6;
						break;
					case (2):
						nextNote = 0;
						break;
					case (3):
						nextNote = 6;
						break;
					case (4):
						nextNote = 14;
						break;
					}
					//if the next note is more or less than possible it changes the octave
					if(key1 + nextNote >= pitches.length) {								
						octave +=1;
						key1 = key1 + nextNote - pitches.length;
					}else if(key1 + nextNote < 0) {						
						octave -=1;
						key1 = pitches.length + (key1 +nextNote);
					}else {																
						key1 +=nextNote;
					}
					//increment beatCount
					beatCount += noteLengths[theRandom];
				}
				//it gets added to the pattern
				thePattern += " ";
			}
		//it does it 4 times with different instruments
		for(int count = 0; count < 4; count +=1) {
			theMelody += "V" + count + " I[" + melodicInstruments[(int)(Math.random()*13)] + "] " +thePattern + "\n"; 
		}
			break;
		case(5):
			//purple
			//keeps track of the measure
			for(int measureCount = 1; measureCount<=4; measureCount +=1) {
				//on measure 3 it switches the key
				if (measureCount ==3) {
					key1 = key2;
				}
				//keeps track of what beat its on
				double beatCount = 0;
				//dictates the octave
				int octave = (int)(Math.random()*3 + 2); 
				//while a measure is not complete
				while(beatCount<4) { 															
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					//it adds to the pattern the note name, the octave, and time length
					int random2 = (int)(Math.random()* 9);
					if(random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					}else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					//this will dictate the next note via a random number that correlates to a themeatically sensible next note
					int nextNote = ((int)(Math.random()*5));
					switch(nextNote) {
					//the possible next notes
					case (0):
						nextNote = -4;
						break;
					case (1):
						nextNote = -8;
						break;
					case (2):
						nextNote = 0;
						break;
					case (3):
						nextNote = 8;
						break;
					case (4):
						nextNote = 4;
						break;
					}
					//if the next note is more or less than possible it changes the octave
					if(key1 + nextNote >= pitches.length) {								
						octave +=1;
						key1 = key1 + nextNote - pitches.length;
					}else if(key1 + nextNote < 0) {						
						octave -=1;
						key1 = pitches.length + (key1 +nextNote);
					}else {																
						key1 +=nextNote;
					}
					//increment beatCount
					beatCount += noteLengths[theRandom];
				}
				//it gets added to the pattern
				thePattern += " ";
			}
		//it does it 4 times with different instruments
		for(int count = 0; count < 4; count +=1) {
			theMelody += "V" + count + " I[" + melodicInstruments[(int)(Math.random()*13)] + "] " +thePattern + "\n"; 
		}
			break;
		default:
			theMelody = "";
		}
		return theMelody;
	}
	public String generateChorusMelody(ArrayList<String> keys) {

	}

	public String generateVerse1Melody(ArrayList<String> keys) {

	}

	public String generateBridgeMelody(ArrayList<String> keys) {

	}

	public String generateOutroMelody(ArrayList<String> keys) {

	}

	// the basslines
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

	// the background melodies
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
