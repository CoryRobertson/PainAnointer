package com.github.coryrobertson.PainAnointer.effects;

import com.github.coryrobertson.PainAnointer.EffectTypes;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class SoundEffect extends Effect
{
    JFXPanel panel = new JFXPanel();
    MediaPlayer mp;
    final String[] soundEffects = {"src/main/resources/BruhSoundEffect.mp3","src/main/resources/InstagramThud.mp3"};

    public SoundEffect()
    {
        super();

        this.EffectName = "Sound Effect";
        this.effectTypes  = EffectTypes.SOUND_EFFECT;
        this.EffectDuration = 1000;

    }

    //TODO: add tarkov scav voice lines, add gunshot random noises from arma and tarkov, add minecraft creeper noises, knock on door from twitch sound effect
    @Override
    protected void EffectStep()
    {

    }

    @Override
    protected void init()
    {
        Media media = getRandomMedia();
        mp = new MediaPlayer(media);
        mp.play();

    }

    private Media getRandomMedia()
    {
        int randomNum = ThreadLocalRandom.current().nextInt(0,soundEffects.length);
        return new Media(new File(soundEffects[randomNum]).toURI().toString());
    }
}
