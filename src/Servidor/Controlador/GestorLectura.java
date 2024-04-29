/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controlador;

import java.util.Locale;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

/**
 *
 * @author cesar
 */
public class GestorLectura {

    private Synthesizer synth;

    public GestorLectura() throws IllegalArgumentException, EngineException {
        SynthesizerModeDesc required = new SynthesizerModeDesc();
        required.setLocale(Locale.ROOT);

        Voice voice = new Voice(null, Voice.GENDER_FEMALE, Voice.GENDER_FEMALE, null);

        required.addVoice(voice);

        synth = Central.createSynthesizer(null);
        synth.allocate();
    }

    public void leer(String say) throws EngineException, AudioException, InterruptedException {

        
        synth.resume();

        synth.speakPlainText(say, null);

        
        
        // CRÉDITOS A: CMOP
    }
    
    public void cerrar() throws InterruptedException, EngineException{
        synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
        synth.deallocate();
    }
}
