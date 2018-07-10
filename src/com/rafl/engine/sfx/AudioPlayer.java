package com.rafl.engine.sfx;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	private Clip clip;
	private FloatControl gainControl;

	public AudioPlayer() {}

	public AudioPlayer(String path) {
		loadTrack(path);
	}

	public void loadTrack(String path) {
		try {

			AudioInputStream ais = AudioSystem
					.getAudioInputStream(getClass().getResourceAsStream(path));

			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodedFormat = new AudioFormat
					(AudioFormat.Encoding.PCM_SIGNED,
							baseFormat.getSampleRate(),
							16,
							baseFormat.getChannels(),
							baseFormat.getChannels() * 2,
							baseFormat.getSampleRate(),
							false);

			AudioInputStream audioInput = AudioSystem
					.getAudioInputStream(decodedFormat, ais);

			clip = AudioSystem.getClip();
			clip.open(audioInput);

		}
		catch(UnsupportedAudioFileException | IOException |
				LineUnavailableException e) 
		{
			e.printStackTrace();
		}

		if(clip != null) gainControl = (FloatControl) 
				clip.getControl(FloatControl.Type.MASTER_GAIN);
	}

	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		while(!clip.isRunning()) clip.start();
	}

	public void playOnLoop() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		while(!clip.isRunning()) clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		if(clip != null && clip.isRunning()) clip.stop();
	}

	public void close() {
		if(clip == null) return;
		stop();
		clip.drain();
		clip.close();
	}

	public void setVolume(float value) {
		if(gainControl != null) gainControl.setValue(value);
	}

	public float getMaximumVolume() {
		return gainControl == null ? 0 : gainControl.getMaximum();
	}
	
	public float getMinimumVolume() {
		return gainControl == null ? 0 : gainControl.getMinimum();
	}

	public void turnVolumeUp(float amt) {
		if(gainControl != null) setVolume(gainControl.getValue()+amt);
	}

	public void turnVolumeDown(float amt) {
		if(gainControl != null) setVolume(gainControl.getValue()-amt);
	}

	public float getVolume() {
		return gainControl.getValue();
	}
}
