import javax.sound.sampled.*;
/**
 * Mostly written by chatgpt
 */
public class App1 {
    public static void main(String[] args) {
        try {
            // Specify the frequencies and duration of the tones
            double frequency1 = 130.81; // Frequency of the first tone in Hz
            double frequency2 = 174.61; // Frequency of the second tone in Hz
            double frequency3 = 196;
            int duration = 1000; // Duration in milliseconds (1 second in this example)

            // Create threads for each tone
            Thread thread1 = new Thread(() -> playTone(frequency1, duration));
            Thread thread2 = new Thread(() -> playTone(frequency2, duration));
            Thread thread3 = new Thread(() -> playTone(frequency3, duration));

            // Start both threads
            thread1.start();
            thread2.start();
            thread3.start();

            // Wait for both threads to finish before exiting
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to play a tone with a specified frequency and duration
    private static void playTone(double frequency, int duration) {
        try {
            SourceDataLine line = AudioSystem.getSourceDataLine(new AudioFormat(44100, 16, 1, true, true));
            line.open(new AudioFormat(44100, 16, 1, true, true));
            line.start();

            byte[] buffer = new byte[44100 * 2];
            for (int i = 0; i < 44100; i++) {
                double angle = 2.0 * Math.PI * i / (44100.0 / frequency);
                buffer[i * 2] = (byte) (Math.sin(angle) * 127.0);
                buffer[i * 2 + 1] = (byte) (Math.sin(angle) * 127.0);
            }

            line.write(buffer, 0, duration * 44100 / 1000);
            line.drain();
            // line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
