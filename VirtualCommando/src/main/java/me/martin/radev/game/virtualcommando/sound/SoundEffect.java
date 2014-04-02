/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;

/**
 *
 * @author Marto
 */
public class SoundEffect implements Sound {

    private Clip clip;
    
    /**
     *
     * @param f
     */
    public SoundEffect(File f) {
        try {
            loadSoundEffect(f);
        } catch (LineUnavailableException ex) {
            clip = null;
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.LineUnavailableException.getTitle(),
                    ExceptionHelper.LineUnavailableException.getMessage());
        } catch (UnsupportedAudioFileException ex1) {
                Global.getExceptionHandler().notificate(
                    ExceptionHelper.AudioFileNotSupportedException.getTitle(),
                    ExceptionHelper.AudioFileNotSupportedException.getMessage());
            } catch (IOException ex1) {
                Global.getExceptionHandler().notificate(
                    ExceptionHelper.IOException.getTitle(),
                    ExceptionHelper.IOException.getMessage());
            }
    }
    
    private void loadSoundEffect(File f) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.
                getAudioInputStream( f );
        clip.open(ais);
    }
    
    /**
     *
     */
    @Override
    public void play() {
        clip.start();
    }
    
}
