/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Controlador;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineErrorEvent;
import javax.speech.EngineEvent;
import javax.speech.EngineException;
import javax.speech.synthesis.SpeakableEvent;
import javax.speech.synthesis.SpeakableListener;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerEvent;
import javax.speech.synthesis.SynthesizerListener;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

/**
 *
 * @author cesar
 */
public class GestorLectura {

    private Synthesizer synth;
    private Control control;
    private ServidorHilo usuarioActual;

    public GestorLectura(Control control) throws IllegalArgumentException, EngineException {
        this.control = control;

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
        usuarioActual = control.getHiloHablando();

        synth.waitEngineState(Synthesizer.QUEUE_EMPTY);

        try {
            usuarioActual.getConexionCliente().getSalida().writeInt(3);
        } catch (IOException ex) {

        } catch (NullPointerException ex) {
            control.getVista().mostrarMensaje("Revisese los hilos");
        }

        // CRÉDITOS A: CMOP
    }

    public void cerrar() throws InterruptedException, EngineException {

        synth.deallocate();
    }

    public Synthesizer getSynth() {
        return synth;
    }
}
