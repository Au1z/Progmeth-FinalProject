package utils;

import javafx.scene.media.AudioClip;

public class Sound {
    public static void changeBackgroundSound(AudioClip stopSound, AudioClip startSound) {
        Thread sound = new Thread(() -> {
            if (startSound != null) {
                startSound.play();
            } else {
                System.err.println("AudioClip (bgSound) is null.");
            }
        });

        sound.start();

        if (stopSound != null) {
            stopSound.stop();
        }
    }
}
