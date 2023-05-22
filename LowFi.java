package wordToMusic;

import java.util.ArrayList;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import java.util.*;
import org.jfugue.pattern.Pattern;

public class LowFi extends Song{
	
	public LowFi(int leastCommonColor, int averageColour, ArrayList<String> theChords) {
		super(leastCommonColor,  averageColour, theChords);
		
	}
	private static String[] melodicInstruments = new String[] {"warm", "Helicopter", "Choir_Aahs","Applause","Rain", "Seashore", "Crystal","Breath_Noise", "Goblins",};
	private static String[] bassInstruments = new String[] {"Goblins","Atmosphere","Applause", "Voice_Oohs",};
	// list of all pitches possible in assending order
	private static String[] pitches = new String[] { "Ab", "A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G" };
	// the lengths of each note
	private static double[] noteLengths = new double[] { 4,};
	// the name of each note at the index
	private static String[] noteNames = new String[] { "w", "h.", "h", "q.", "q", "i.", "i", "s.", "s", "t" };
	//the drumlines
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

		//Making a base and a metro layer based on the specified length ticks every other
		for(int i =0; i < length; i++){
			base+=".";
			if(i%2 == 0){
				tick+=".";
			}
			else{
			tick+="`";}
		}

		//adding the base and metro layers
        Rhythm rhythm = new Rhythm().addLayer(base)
		.addLayer(tick);
		


		//snare drum ~ 4-6
		
		int snareDistance = (int) (Math.random()*3)+5;

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
				
				//O is more likely to appear after a o
				if(normDrum.length()>0 && normDrum.charAt(normDrum.length()-1) == 'o'){
					drumRand+=5;
				}
				//cymbal is only possible after a blank, and only if the random number is less than 2
				else if(normDrum.length()>0 && normDrum.charAt(normDrum.length()-1) == '.' && doCymbal){
					drumRand -=5;
				}
				//decisions
				if(drumRand < -2){
					normDrum+="+";
				}
				else if(drumRand < 5){
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
	
	
	//the melodies
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
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * melodicInstruments.length)] + "] " + thePattern
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
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * melodicInstruments.length)] + "] " + thePattern
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
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * melodicInstruments.length)] + "] " + thePattern
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
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * melodicInstruments.length)] + "] " + thePattern
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
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * melodicInstruments.length)] + "] " + thePattern
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
				theMelody += "V" + count + " I[" + melodicInstruments[(int) (Math.random() * melodicInstruments.length)] + "] " + thePattern
						+ "\n";
			}
			break;
		default:
			theMelody = "";
		}
		return theMelody;
	}
	

	
	//the bassline
	public String generateBassLine(ArrayList<String> keys) {
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
	
		theMelody += "V" + (int)(4) + " I[" + bassInstruments[(int)(Math.random()*bassInstruments.length)] + "] " +thePattern + "\n"; 
	
	return theMelody;
	}







	//main method to generate and play the song
	public void playSong(ArrayList<String> keys) {
		String sound = generateMelody(keys);

		Pattern p0 = new Pattern(sound.substring(0, sound.indexOf("V1"))); 						//player.play(p0);				
		Pattern p1 = new Pattern(sound.substring(sound.indexOf("V1"), sound.indexOf("V2")));
		Pattern p2 = new Pattern(sound.substring(sound.indexOf("V2"), sound.indexOf("V3")));
		Pattern p3 = new Pattern(sound.substring(sound.indexOf("V3")));
		
		Pattern drums = generateDrumline(450);

		String bass = generateBassLine(keys);

		Player player = new Player();

		player.play(drums, new Pattern(bass), p0, p1, p2, p3);
	}




	
	//the background melodies

}

