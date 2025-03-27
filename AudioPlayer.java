import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {
        private Clip clip;
        private boolean isPaused = false;
        private long clipPosition = 0;
        private List<String> playlist;
        private int currentTrackIndex = 0;

        public AudioPlayer(final List<String> filePaths) {
            this.playlist = new ArrayList<>(filePaths);
            loadTrack(currentTrackIndex);
        }

        private void loadTrack(int index) {
            if (index < 0 || index >= playlist.size()) return;
            stop();
            try {
                File audioFile = Path.of(playlist.get(index)).toFile();
                System.out.println(audioFile);
                try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public void play() {
            if (clip != null) {
                if (isPaused) clip.setMicrosecondPosition(clipPosition);
                clip.start();
                isPaused = false;
            }
        }

        public void pause() {
            if (clip != null && clip.isRunning()) {
                clipPosition = clip.getMicrosecondPosition();
                clip.stop();
                isPaused = true;
            }
        }

        public void stop() {
            if (clip != null) {
                clip.stop();
                clip.setMicrosecondPosition(0);
                isPaused = false;
            }
        }

        public void next() {
            if (currentTrackIndex < playlist.size() - 1) {
                currentTrackIndex++;
                loadTrack(currentTrackIndex);
                play();
            }
        }

        public void previous() {
            if (currentTrackIndex > 0) {
                currentTrackIndex--;
                loadTrack(currentTrackIndex);
                play();
            }
        }

    }
