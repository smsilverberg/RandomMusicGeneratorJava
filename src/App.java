import java.lang.Thread;
import java.util.Random;
import javax.sound.sampled.*;

/**
 * TODO: GUI
 * chords?
 * random chance to add notes from chromatic scale not already included if a different scale is chosen, bases on bound variable
 * get AI to fill out more scales for me
 * param to randomly change the the whole bar's octave after some bars instead of just randomly changing a few notes
**/

public class App {


    // scales - add scales here to be used in generation
    static String[] scaleBlues1 = { "C", "C#", "D", "F", "G", "A#" };
    static String[] scaleChromatic = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };

    public static void main(String[] args) throws InterruptedException {
        //*****DISCLAIMER*****// for really high/low octaves and yer ears and speakers! stick with 3 to 6 for now, 4s a good start
        Random rand = new Random();

        //details. change em around or not

        int measureLength = 4; //notes per line
        int barLength = 2; //lines per bar
        int octave = 3; //octave: choose 0 to 8 (higher number, higher pitch)
        int duration = 300; //note duration in msec (some really low values will crash, others will chirp))
        int pause = 300; //between lines, not notes
        int bars = 4; //# of bars
        boolean changeOctave = true; // true will randomly change octaves
        int bound = 35; // lower number means more likely to change octave
        // uncomment which scale to use, only 2 so far (blues & chromatic)
        // String[] scale = scaleBlues1;
        String[] scale = scaleChromatic;

        //end details

        int[] nums = new int[measureLength]; // array to hold the random numbers for musical tones
        // String[] notes = new String[measureLength]; //array to hold notes that were played
        
        int start = 0;
        int measure = 1;

        boolean closeLine = true; //true will have a small delay between each note, not sure what false does
        while (start < bars) {
            
            for (int i = 0; i < measureLength; i++) nums[i] = rand.nextInt(scale.length);
            for (int i = 0; i < barLength; i++) {
                println("\nmeasure " + String.valueOf(measure)); //printing measure #
                measure++;
                for (int ii = 0; ii < measureLength; ii++) {
                    int octaveGen = octave; //octave that was chosen and randomly changed
                    if (changeOctave) {
                        int chance = rand.nextInt(bound);
                        if (chance == bound - 1) {playNote(scale[nums[ii]], octave + 1, duration, closeLine); octaveGen += 1;}
                        else if (chance == 0) {playNote(scale[nums[ii]], octave - 1, duration, closeLine); octaveGen -= 1;}
                        else playNote(scale[nums[ii]], octave, duration, closeLine);
                    } else playNote(scale[nums[ii]], octave, duration, closeLine);
                    // notes[i] = scale[nums[ii]];
                    println(scale[nums[ii]] + "\t" + String.valueOf(octaveGen)); //printing notes
                }
                Thread.sleep(pause);
                
            }
            start++;
        }
        println("note\toctave");
        // for (String note : notes) {println(note);} 
    }

    // utility methods
    public static void print(Object o) {System.out.print(o);}
    public static void println(Object o) {System.out.println(o);}

    // note, octave 0 1 2 3 4 5 6 7 8
    static double[][] notes = { { 16.35, 32.7, 65.41, 130.81, 261.63, 523.25, 1046.5, 2093, 4186 }, // C - 0
            /* C# - 1 */{ 17.32, 34.65, 69.3, 138.59, 277.18, 554.37, 1108.73, 2217.46, 4434.92 }, // C# - 1
            /* D - 2 */{ 18.35, 36.71, 73.42, 146.83, 293.66, 587.33, 1174.66, 2349.32, 4698.63 }, // D - 2
            /* D# - 3 */{ 19.45, 38.89, 77.78, 155.56, 311.13, 622.25, 1244.51, 2489, 4978 }, // D# - 3
            /* E - 4 */{ 20.6, 41.2, 82.41, 164.81, 329.63, 659.25, 1318.51, 2637, 5274 }, // E - 4
            /* F - 5 */{ 21.83, 43.65, 87.31, 174.61, 349.23, 698.46, 1396.91, 2793.83, 5587.65 }, // F - 5
            /* F# - 6 */{ 23.12, 46.25, 92.5, 185, 369.99, 739.99, 1479.98, 2959.96, 5919.91 }, // F# - 6
            /* G - 7 */{ 24.5, 49, 98, 196, 392, 783.99, 1567.98, 3135.96, 6271.93 }, // G - 7
            /* G# - 8 */{ 25.96, 51.91, 103.83, 207.65, 415.3, 830.61, 1661.22, 3322.44, 6644.88 }, // G# - 8
            /* A - 9 */{ 27.5, 55, 110, 220, 440, 880, 1760, 3520, 7040 }, // A - 9
            /* A# -10 */{ 29.14, 58.27, 116.54, 233.08, 466.16, 932.33, 1864.66, 3729.31, 7458.62 }, // A# - 10
            /* B -11 */{ 30.87, 61.74, 123.47, 246.94, 493.88, 987.77, 1975.53, 3951, 7902.13 } }; // B - 11

    

    /**
     * Plays a note for a specified duration
     * @param note    the note
     * @param octave    the octave
     * @param duration  duration in msec
     * @param closeLine If true, there will be a small delay between each call of
     *                  playNote(). Otherwise no delay
     */
    public static void playNote(String note, int octave, int duration, boolean closeLine) {
        switch (note) {
            // choosing the note
            case "C":
                // choosing the octave
                switch (octave) {
                    case 0:
                        tone(notes[0][0], duration);
                        break;
                    case 1:
                        tone(notes[0][1], duration);
                        break;
                    case 2:
                        tone(notes[0][2], duration);
                        break;
                    case 3:
                        tone(notes[0][3], duration);
                        break;
                    case 4:
                        tone(notes[0][4], duration);
                        break;
                    case 5:
                        tone(notes[0][5], duration);
                        break;
                    case 6:
                        tone(notes[0][6], duration);
                        break;
                    case 7:
                        tone(notes[0][7], duration);
                        break;
                    case 8:
                        tone(notes[0][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "C#":
                switch (octave) {
                    case 0:
                        tone(notes[1][0], duration);
                        break;
                    case 1:
                        tone(notes[1][1], duration);
                        break;
                    case 2:
                        tone(notes[1][2], duration);
                        break;
                    case 3:
                        tone(notes[1][3], duration);
                        break;
                    case 4:
                        tone(notes[1][4], duration);
                        break;
                    case 5:
                        tone(notes[1][5], duration);
                        break;
                    case 6:
                        tone(notes[1][6], duration);
                        break;
                    case 7:
                        tone(notes[1][7], duration);
                        break;
                    case 8:
                        tone(notes[1][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "D":
                switch (octave) {
                    case 0:
                        tone(notes[2][0], duration);
                        break;
                    case 1:
                        tone(notes[2][1], duration);
                        break;
                    case 2:
                        tone(notes[2][2], duration);
                        break;
                    case 3:
                        tone(notes[2][3], duration);
                        break;
                    case 4:
                        tone(notes[2][4], duration);
                        break;
                    case 5:
                        tone(notes[2][5], duration);
                        break;
                    case 6:
                        tone(notes[2][6], duration);
                        break;
                    case 7:
                        tone(notes[2][7], duration);
                        break;
                    case 8:
                        tone(notes[2][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "D#":
                switch (octave) {
                    case 0:
                        tone(notes[3][0], duration);
                        break;
                    case 1:
                        tone(notes[3][1], duration);
                        break;
                    case 2:
                        tone(notes[3][2], duration);
                        break;
                    case 3:
                        tone(notes[3][3], duration);
                        break;
                    case 4:
                        tone(notes[3][4], duration);
                        break;
                    case 5:
                        tone(notes[3][5], duration);
                        break;
                    case 6:
                        tone(notes[3][6], duration);
                        break;
                    case 7:
                        tone(notes[3][7], duration);
                        break;
                    case 8:
                        tone(notes[3][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "E":
                switch (octave) {
                    case 0:
                        tone(notes[4][0], duration);
                        break;
                    case 1:
                        tone(notes[4][1], duration);
                        break;
                    case 2:
                        tone(notes[4][2], duration);
                        break;
                    case 3:
                        tone(notes[4][3], duration);
                        break;
                    case 4:
                        tone(notes[4][4], duration);
                        break;
                    case 5:
                        tone(notes[4][5], duration);
                        break;
                    case 6:
                        tone(notes[4][6], duration);
                        break;
                    case 7:
                        tone(notes[4][7], duration);
                        break;
                    case 8:
                        tone(notes[4][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "F":
                switch (octave) {
                    case 0:
                        tone(notes[5][0], duration);
                        break;
                    case 1:
                        tone(notes[5][1], duration);
                        break;
                    case 2:
                        tone(notes[5][2], duration);
                        break;
                    case 3:
                        tone(notes[5][3], duration);
                        break;
                    case 4:
                        tone(notes[5][4], duration);
                        break;
                    case 5:
                        tone(notes[5][5], duration);
                        break;
                    case 6:
                        tone(notes[5][6], duration);
                        break;
                    case 7:
                        tone(notes[5][7], duration);
                        break;
                    case 8:
                        tone(notes[5][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "F#":
                switch (octave) {
                    case 0:
                        tone(notes[6][0], duration);
                        break;
                    case 1:
                        tone(notes[6][1], duration);
                        break;
                    case 2:
                        tone(notes[6][2], duration);
                        break;
                    case 3:
                        tone(notes[6][3], duration);
                        break;
                    case 4:
                        tone(notes[6][4], duration);
                        break;
                    case 5:
                        tone(notes[6][5], duration);
                        break;
                    case 6:
                        tone(notes[6][6], duration);
                        break;
                    case 7:
                        tone(notes[6][7], duration);
                        break;
                    case 8:
                        tone(notes[6][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "G":
                switch (octave) {
                    case 0:
                        tone(notes[7][0], duration);
                        break;
                    case 1:
                        tone(notes[7][1], duration);
                        break;
                    case 2:
                        tone(notes[7][2], duration);
                        break;
                    case 3:
                        tone(notes[7][3], duration);
                        break;
                    case 4:
                        tone(notes[7][4], duration);
                        break;
                    case 5:
                        tone(notes[7][5], duration);
                        break;
                    case 6:
                        tone(notes[7][6], duration);
                        break;
                    case 7:
                        tone(notes[7][7], duration);
                        break;
                    case 8:
                        tone(notes[7][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "G#":
                switch (octave) {
                    case 0:
                        tone(notes[8][0], duration);
                        break;
                    case 1:
                        tone(notes[8][1], duration);
                        break;
                    case 2:
                        tone(notes[8][2], duration);
                        break;
                    case 3:
                        tone(notes[8][3], duration);
                        break;
                    case 4:
                        tone(notes[8][4], duration);
                        break;
                    case 5:
                        tone(notes[8][5], duration);
                        break;
                    case 6:
                        tone(notes[8][6], duration);
                        break;
                    case 7:
                        tone(notes[8][7], duration);
                        break;
                    case 8:
                        tone(notes[8][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "A":
                switch (octave) {
                    case 0:
                        tone(notes[9][0], duration);
                        break;
                    case 1:
                        tone(notes[9][1], duration);
                        break;
                    case 2:
                        tone(notes[9][2], duration);
                        break;
                    case 3:
                        tone(notes[9][3], duration);
                        break;
                    case 4:
                        tone(notes[9][4], duration);
                        break;
                    case 5:
                        tone(notes[9][5], duration);
                        break;
                    case 6:
                        tone(notes[9][6], duration);
                        break;
                    case 7:
                        tone(notes[9][7], duration);
                        break;
                    case 8:
                        tone(notes[9][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "A#":
                switch (octave) {
                    case 0:
                        tone(notes[10][0], duration);
                        break;
                    case 1:
                        tone(notes[10][1], duration);
                        break;
                    case 2:
                        tone(notes[10][2], duration);
                        break;
                    case 3:
                        tone(notes[10][3], duration);
                        break;
                    case 4:
                        tone(notes[10][4], duration);
                        break;
                    case 5:
                        tone(notes[10][5], duration);
                        break;
                    case 6:
                        tone(notes[10][6], duration);
                        break;
                    case 7:
                        tone(notes[10][7], duration);
                        break;
                    case 8:
                        tone(notes[10][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            case "B":
                switch (octave) {
                    case 0:
                        tone(notes[11][0], duration);
                        break;
                    case 1:
                        tone(notes[11][1], duration);
                        break;
                    case 2:
                        tone(notes[11][2], duration);
                        break;
                    case 3:
                        tone(notes[11][3], duration);
                        break;
                    case 4:
                        tone(notes[11][4], duration);
                        break;
                    case 5:
                        tone(notes[11][5], duration);
                        break;
                    case 6:
                        tone(notes[11][6], duration);
                        break;
                    case 7:
                        tone(notes[11][7], duration);
                        break;
                    case 8:
                        tone(notes[11][8], duration);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    /**
     * Plays a tone at the selected frequency (Hz) for a specified amount of
     * miliseconds
     * <br>
     * <br>
     * Mostly written by ChatGpt
     * 
     * @param frequency the frequency in Hz
     * @param duration  how long to play the note in msec
     */
    public static void tone(double frequency, int duration) {
        try {
            // Get the default audio output device
            SourceDataLine line = AudioSystem.getSourceDataLine(new AudioFormat(44100, 16, 1, true, true));
            // Open the audio line
            line.open(new AudioFormat(44100, 16, 1, true, true));
            line.start();
            // Generate the tone
            byte[] buffer = new byte[44100 * 2];
            for (int i = 0; i < 44100; i++) {
                double angle = 2.0 * Math.PI * i / (44100.0 / frequency);
                buffer[i * 2] = (byte) (Math.sin(angle) * 127.0);
                buffer[i * 2 + 1] = (byte) (Math.sin(angle) * 127.0);
            }
            // Play the tone for the specified duration
            line.write(buffer, 0, duration * 44100 / 1000);
            line.drain();
            // line.close();
        } catch (LineUnavailableException e) {e.printStackTrace();}
    }
}