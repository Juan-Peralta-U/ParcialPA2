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
 * Clase encargada de gestionar la lectura de texto mediante síntesis de voz.
 *
 * @author cesar
 */
public class GestorLectura {

    /**
     * Objeto Synthesizer utilizado para realizar la síntesis de voz.
     */
    private Synthesizer synth;
    
    /**
     * Objeto Control que maneja la lógica principal de la aplicación.
     */
    private Control control;
    
    /**
     * Objeto ServidorHilo que representa al usuario actual que está hablando.
     */
    private ServidorHilo usuarioActual;
    
     /**
     * Crea una nueva instancia de GestorLectura.
     *
     * @param control Objeto Control que maneja la lógica principal de la aplicación.
     * @throws IllegalArgumentException Si se proporcionan argumentos inválidos.
     * @throws EngineException Si ocurre un error relacionado con el motor de síntesis de voz.
     */
    public GestorLectura(Control control) throws IllegalArgumentException, EngineException {
        this.control = control;

        SynthesizerModeDesc required = new SynthesizerModeDesc();
        required.setLocale(Locale.ROOT);

        Voice voice = new Voice(null, Voice.GENDER_FEMALE, Voice.GENDER_FEMALE, null);

        required.addVoice(voice);

        synth = Central.createSynthesizer(null);
        synth.allocate();
    }

    /**
     * Lee el texto dado mediante síntesis de voz.
     *
     * @param say Texto que se leerá.
     * @throws EngineException Si ocurre un error relacionado con el motor de síntesis de voz.
     * @throws AudioException Si ocurre un error relacionado con el audio.
     * @throws InterruptedException Si el hilo es interrumpido mientras espera a que el motor termine de hablar.
     */
    public void leer(String say) throws EngineException, AudioException, InterruptedException {

        synth.resume();

        synth.speakPlainText(say, null);
        usuarioActual = control.getHiloHablando();
        
        // Se espera a que termine de hablar
        synth.waitEngineState(Synthesizer.QUEUE_EMPTY);

        // Se manda opcion al cliente requerido para que se borre el texto
        try {
            usuarioActual.getConexionCliente().getSalida().writeInt(3);
        } catch (IOException ex) {

        } catch (NullPointerException ex) {
            control.getVista().mostrarMensaje("Revisese los hilos");
        }

        // CRÉDITOS A: CMOP
    }

    /**
     * Cierra y libera los recursos utilizados por el sintetizador de voz.
     *
     * @throws InterruptedException Si el hilo es interrumpido mientras espera a que el motor se cierre.
     * @throws EngineException Si ocurre un error relacionado con el motor de síntesis de voz.
     */
    public void cerrar() throws InterruptedException, EngineException {

        synth.deallocate();
    }

    /**
     * Obtiene el objeto Synthesizer utilizado para realizar la síntesis de voz.
     *
     * @return El objeto Synthesizer.
     */
    public Synthesizer getSynth() {
        return synth;
    }
}
