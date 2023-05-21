
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import java.util.*;
import org.jfugue.pattern.Pattern;

public class RockSong extends Song {
	// constructor
	public RockSong(int mostCommonColour, int averageColour, ArrayList<String> theChords) {
		super(mostCommonColour, averageColour, theChords);
	}

	// the array of melodic rock instruments
	private static String[] melodicInstruments = new String[] { "OVERDRIVEN_GUITAR", "GUITAR_FRET_NOISE",
			"STEEL_STRING_GUITAR", "DISTORTION_GUITAR", "HARMONICA", "ROCK_ORGAN", "TANGO_ACCORDIAN", "BARITONE_SAX",
			"SKAKUHACHI", "BLOWN_BOTTLE", "DULCIMER", "GUNSHOT", "SHANAI" };
	private static String[] bassInstruments = new String[] { "ELECTRIC_BASS_FINGER", "ELECTRIC_BASS_PICK", "VOICE_OOHS",
			"REED_ORGAN", "TUBA", "SLAP_BASS_1", "SLAP_BASS_2", "FRETLESS_BASS", "BARITONE_SAX", "BANJO",
			"HELICOPTER", "BASSOON" };
	// list of all pitches possible in assending order
	private static String[] pitches = new String[] { "Ab", "A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G" };
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
	public Pattern generateDrumline(int length) {
		//taking voice 9 
		String theDrumline = " V9 I |";

		//the base pattern
		Pattern drumline = new Pattern();
		//adding the voice to the pattern
		drumline.add(theDrumline);
		//String for base drum layer to our rhthym
		String base = "";
		//String for the metro layer
		String tick = "";

		//Making a base and a metro layer based on the specified length
		for(int i =0; i < length; i++){
			base+=".";
			tick+="`";
		}

		//adding the base and metro layers
        Rhythm rhythm = new Rhythm().addLayer(base)
		.addLayer(tick);
		


		//snare drum ~ 2-4
		
		int snareDistance = (int) (Math.random()*3)+3;

		//layers for snare and normal drums
		String snare = "";
		String normDrum = "";
		boolean doCymbal = false;


		for(int i = 1; i <= length; i ++){
			if(i != 0 && i%snareDistance == 0){
				//every random number adds a snare, and nothing else
				snare += "S";
				normDrum+= ".";
				doCymbal = false;
			}
			else{
				
				snare+= ".";
				//randomly deciding which drums to use/not use
				int drumRand = (int) (Math.random()*10)+1;
				System.out.println(drumRand);
				//O is more likely to appear after a o
				if(normDrum.length()>0 && normDrum.charAt(normDrum.length()-1) == 'o'){
					drumRand+=5;
				}
				//cymbal is only possible after a blank, and only if the random number is less than 5
				else if(normDrum.length()>0 && normDrum.charAt(normDrum.length()-1) == '.' && doCymbal){
					drumRand -=5;
				}
				//decisions
				if(drumRand < 0){
					normDrum+="+";
				}
				else if(drumRand < 3){
					normDrum+=".";
				}
				else if(drumRand < 7){
					normDrum+="o";
				}
				else{
					normDrum+="O";
				}
				doCymbal = true;

			}
		}
		//adding them to the rhythm
		rhythm.addLayer(snare);
		rhythm.addLayer(normDrum);
		drumline.add(rhythm);
		
		return drumline;
	}

	
	public String generateMelody(ArrayList<String> keys) {
		// intro will be 4 measures aka 2 drum switchups for intrest with a key switch
		// in measure 3
		int key1;
		int key2;
		if (keys == null) {
			key1 = (int) (Math.random() * 11);
			key2 = (int) (Math.random() * 11);
		} else {
			if (keys.size() == 1) {
				int index;
				for (index = 0; index < pitches.length; index += 1) {
					if (pitches[index] == keys.get(0)) {
						break;
					}
				}
				key1 = index;
				key2 = (int) (Math.random() * 11);
			} else {
				int index;
				for (index = 0; index < pitches.length; index += 1) {
					if (pitches[index] == keys.get(0)) {
						break;
					}
				}
				key1 = index;
				keys.add(pitches[key1]);
				keys.remove(0);
				for (index = 0; index < pitches.length; index += 1) {
					if (pitches[index] == keys.get(0)) {
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
		switch (getFeel()) {
		case (0):
			// angry chords: idk probably like 1/2step up or down to create discordanent
			// sounds
			for (int measureCount = 1; measureCount <= 50; measureCount += 1) {
				if (measureCount == 3) {
					key1 = key2;
				}
				double beatCount = 0;
				int octave = (int) (Math.random() * 4 + 5);
				while (beatCount < 4) {
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					int random2 = (int) (Math.random() * 9);
					if (random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					} else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					int nextNote = ((int) (Math.random() * 7)) * 2 - 7;
					if (key1 + nextNote >= pitches.length) {
						octave += 1;
						key1 = key1 + nextNote - pitches.length;
					} else if (key1 + nextNote < 0) {
						octave -= 1;
						key1 = pitches.length + (key1 + nextNote);
					} else {
						key1 += nextNote;
					}
					// increment beatCount
					beatCount += noteLengths[theRandom];
				}
				thePattern += " ";
			}
			for (int count = 0; count < 4; count += 1) {
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * 13)] + "] " + thePattern
						+ "\n";
			}
			break;
		case (1):
			// orange happy and slightly angry and slightly royal sounding idk
			// keeps track of the measure
			for (int measureCount = 1; measureCount <= 50; measureCount += 1) {
				// on measure 3 it switches the key
				if (measureCount == 3) {
					key1 = key2;
				}
				// keeps track of what beat its on
				double beatCount = 0;
				// dictates the octave
				int octave = (int) (Math.random() * 3 + 5);
				// while a measure is not complete
				while (beatCount < 4) {
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					// it adds to the pattern the note name, the octave, and time length
					int random2 = (int) (Math.random() * 9);
					if (random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					} else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					// this will dictate the next note via a random number that correlates to a
					// themeatically sensible next note
					int nextNote = ((int) (Math.random() * 5));
					switch (nextNote) {
					// the possible next notes
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
					// if the next note is more or less than possible it changes the octave
					if (key1 + nextNote >= pitches.length) {
						octave += 1;
						key1 = key1 + nextNote - pitches.length;
					} else if (key1 + nextNote < 0) {
						octave -= 1;
						key1 = pitches.length + (key1 + nextNote);
					} else {
						key1 += nextNote;
					}
					// increment beatCount
					beatCount += noteLengths[theRandom];
				}
				// it gets added to the pattern
				thePattern += " ";
			}
			// it does it 4 times with different instruments
			for (int count = 0; count < 4; count += 1) {
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * 13)] + "] " + thePattern
						+ "\n";
			}
			break;
		case (2):
			// yellow
			// keeps track of the measure
			for (int measureCount = 1; measureCount <= 50; measureCount += 1) {
				// on measure 3 it switches the key
				if (measureCount == 3) {
					key1 = key2;
				}
				// keeps track of what beat its on
				double beatCount = 0;
				// dictates the octave
				int octave = (int) (Math.random() * 2 + 5);
				// while a measure is not complete
				while (beatCount < 4) {
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					// it adds to the pattern the note name, the octave, and time length
					int random2 = (int) (Math.random() * 9);
					if (random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					} else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					// this will dictate the next note via a random number that correlates to a
					// themeatically sensible next note
					int nextNote = ((int) (Math.random() * 5));
					switch (nextNote) {
					// the possible next notes
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
					// if the next note is more or less than possible it changes the octave
					if (key1 + nextNote >= pitches.length) {
						octave += 1;
						key1 = key1 + nextNote - pitches.length;
					} else if (key1 + nextNote < 0) {
						octave -= 1;
						key1 = pitches.length + (key1 + nextNote);
					} else {
						key1 += nextNote;
					}
					// increment beatCount
					beatCount += noteLengths[theRandom];
				}
				// it gets added to the pattern
				thePattern += " ";
			}
			// it does it 4 times with different instruments
			for (int count = 0; count < 4; count += 1) {
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * 13)] + "] " + thePattern
						+ "\n";
			}
			break;
		case (3):
			// green
			// keeps track of the measure
			for (int measureCount = 1; measureCount <= 50; measureCount += 1) {
				// on measure 3 it switches the key
				if (measureCount == 3) {
					key1 = key2;
				}
				// keeps track of what beat its on
				double beatCount = 0;
				// dictates the octave
				int octave = (int) (Math.random() * 3 + 5);
				// while a measure is not complete
				while (beatCount < 4) {
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					// it adds to the pattern the note name, the octave, and time length
					int random2 = (int) (Math.random() * 9);
					if (random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					} else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					// this will dictate the next note via a random number that correlates to a
					// themeatically sensible next note
					int nextNote = ((int) (Math.random() * 5));
					switch (nextNote) {
					// the possible next notes
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
					// if the next note is more or less than possible it changes the octave
					if (key1 + nextNote >= pitches.length) {
						octave += 1;
						key1 = key1 + nextNote - pitches.length;
					} else if (key1 + nextNote < 0) {
						octave -= 1;
						key1 = pitches.length + (key1 + nextNote);
					} else {
						key1 += nextNote;
					}
					// increment beatCount
					beatCount += noteLengths[theRandom];
				}
				// it gets added to the pattern
				thePattern += " ";
			}
			// it does it 4 times with different instruments
			for (int count = 0; count < 4; count += 1) {
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * 13)] + "] " + thePattern
						+ "\n";
			}
			break;
		case (4):
			// sad
			// keeps track of the measure
			for (int measureCount = 1; measureCount <= 50; measureCount += 1) {
				// on measure 3 it switches the key
				if (measureCount == 3) {
					key1 = key2;
				}
				// keeps track of what beat its on
				double beatCount = 0;
				// dictates the octave
				int octave = (int) (Math.random() * 4 + 3);
				// while a measure is not complete
				while (beatCount < 4) {
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					// it adds to the pattern the note name, the octave, and time length
					int random2 = (int) (Math.random() * 9);
					if (random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					} else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					// this will dictate the next note via a random number that correlates to a
					// themeatically sensible next note
					int nextNote = ((int) (Math.random() * 5));
					switch (nextNote) {
					// the possible next notes
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
					// if the next note is more or less than possible it changes the octave
					if (key1 + nextNote >= pitches.length) {
						octave += 1;
						key1 = key1 + nextNote - pitches.length;
					} else if (key1 + nextNote < 0) {
						octave -= 1;
						key1 = pitches.length + (key1 + nextNote);
					} else {
						key1 += nextNote;
					}
					// increment beatCount
					beatCount += noteLengths[theRandom];
				}
				// it gets added to the pattern
				thePattern += " ";
			}
			// it does it 4 times with different instruments
			for (int count = 0; count < 4; count += 1) {
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * 13)] + "] " + thePattern
						+ "\n";
			}
			break;
		case (5):
			// purple
			// keeps track of the measure
			for (int measureCount = 1; measureCount <= 50; measureCount += 1) {
				// on measure 3 it switches the key
				if (measureCount == 3) {
					key1 = key2;
				}
				// keeps track of what beat its on
				double beatCount = 0;
				// dictates the octave
				int octave = (int) (Math.random() * 3 + 2);
				// while a measure is not complete
				while (beatCount < 4) {
					// picks a random beat that fits in the measure
					int theRandom = (int) (Math.random() * noteLengths.length);
					// if it does not fit it adds it skips and tries again
					if (beatCount + noteLengths[theRandom] > 4) {
						continue;
					}
					// it adds to the pattern the note name, the octave, and time length
					int random2 = (int) (Math.random() * 9);
					if (random2 != 0) {
						thePattern += pitches[key1] + "" + octave + noteNames[theRandom] + " ";
					} else {
						thePattern += "R" + octave + noteNames[theRandom] + " ";
					}
					// this will dictate the next note via a random number that correlates to a
					// themeatically sensible next note
					int nextNote = ((int) (Math.random() * 5));
					switch (nextNote) {
					// the possible next notes
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
					// if the next note is more or less than possible it changes the octave
					if (key1 + nextNote >= pitches.length) {
						octave += 1;
						key1 = key1 + nextNote - pitches.length;
					} else if (key1 + nextNote < 0) {
						octave -= 1;
						key1 = pitches.length + (key1 + nextNote);
					} else {
						key1 += nextNote;
					}
					// increment beatCount
					beatCount += noteLengths[theRandom];
				}
				// it gets added to the pattern
				thePattern += " ";
			}
			// it does it 4 times with different instruments
			for (int count = 0; count < 4; count += 1) {
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * 13)] + "] " + thePattern
						+ "\n";
			}
			break;
		default:
			theMelody = "";
		}
		return theMelody;
	}

	
	// the basslines
	public String generateIntroBassLine(ArrayList<String> keys) {
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
		String theChord = "";
		int random = (int)(Math.random()*3);
		switch(getFeel()) {
		case(0):
			switch(random) {
			case(0):
				theChord = "sus2";
				break;
			case(1):
				theChord = "dom7<5>9";
				break;
			case(2):
				theChord = "add9";
				break;
			}
			break;
		case(1):
			switch(random) {
			case(0):
				theChord = "maj9";
				break;
			case(1):
				theChord = "dim7";
				break;
			case(2):
				theChord = "maj13";
				break;
			}
			break;
		case(2):
			switch(random) {
			case(0):
				theChord = "maj";
				break;
			case(1):
				theChord = "maj7";
				break;
			case(2):
				theChord = "add9";
				break;
			}
			break;
		case(3):
			switch(random) {
			case(0):
				theChord = "dom13";
				break;
			case(1):
				theChord = "sus4";
				break;
			case(2):
				theChord = "aug";
				break;
			}
			break;
		case(4):
			switch(random) {
			case(0):
				theChord = "min";
				break;
			case(1):
				theChord = "min7";
				break;
			case(2):
				theChord = "min11";
				break;
			}
			break;
		case(5):
			switch(random) {
			case(0):
				theChord = "minmaj7";
				break;
			case(1):
				theChord = "maj7>5";
				break;
			case(2):
				theChord = "dom11";
				break;
			}
			break;
		}
		String thePattern = "";
		for(int measureCount = 1; measureCount<=50; measureCount +=1) {
			if (measureCount ==3) {
				key1 = key2;
			}
			double beatCount = 0; 
			int octave = (int)(Math.random()*3 +2); 										
			while(beatCount<4) { 															
				// picks a random beat that fits in the measure
				int theRandom = (int) (Math.random() * noteLengths.length);
				// if it does not fit it adds it skips and tries again
				if (beatCount + noteLengths[theRandom] > 4) {
					continue;
				}
				int random2 = (int)(Math.random()* 9);
				if(random2 != 0) {
					thePattern += pitches[key1] + "" + octave+ theChord  + noteNames[theRandom] + " ";
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
			thePattern += "  | ";
		}
		String theMelody = "";
	
		theMelody += "V" + (int)(4) + " I[" + bassInstruments[(int)(Math.random()*13)] + "] " +thePattern + "\n"; 
	
	return theMelody;
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
	//main method to generate and play the song
	public void playSong(ArrayList<String> keys){
		System.out.println("Generating song, this make some time");
		//generating
		Pattern drumP = generateDrumline();
		Pattern drumSwitch = generateDrumlineSwitchup();
		
		Pattern introP = new Pattern(generateIntroMelody(keys));
		Pattern introBack = new Pattern(generateIntroBackground(keys));
		Pattern introBass = new Pattern(generateIntroBassLine(keys));

		Pattern chorusP = new Pattern(generateChorusMelody(keys));
		Pattern chorusBack = new Pattern(generateChorusBackground(keys));
		Pattern chorusBass = new Pattern(generateChorusBassLine(keys));

		Pattern verse1P = new Pattern(generateVerse1Melody(keys));
		Pattern verse1Back = new Pattern(generateVerse1Background(keys)); 
		Pattern verse1Bass = new Pattern(generateVerse1BassLine(keys));

		Pattern bridgeP = new Pattern(generateBridgeMelody(keys));
		Pattern bridgeBack = new Pattern(generateBridgeBackground(keys));
		Pattern bridgeBass= new Pattern(generateBridgeBassLine(keys));

		Pattern outroP = new Pattern(generateOutroMelody(keys));
		Pattern outroBack = new Pattern(generateOutroBackground(keys));
		Pattern outroBass = new Pattern(generateOutroBassLine(keys));

		Player player = new Player();

		//playing the song
		//intro
		player.play(introP, introBack, introBass);
		//chorus
		player.play(drumP, drumSwitch, chorusP, chorusBack, chorusBass);
		//verse1
		player.play(verse1P, verse1Back, verse1Bass);
		//chorus
		player.play(drumP, drumSwitch, chorusP, chorusBack, chorusBass);
		//bridge
		player.play(bridgeP, bridgeBack, bridgeBass, drumP, drumSwitch);
		//outro
		player.play(outroP, outroBack, outroBass);


	}
}
