package fiuba.algo3.controller;

import fiuba.algo3.model.juego.Juego;
import fiuba.algo3.view.ContenedorJuego;
import fiuba.algo3.view.VentanaDefault;
import fiuba.algo3.view.utilities.ReproductorMusica;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class BotonComenzarBatallaHandler implements EventHandler<ActionEvent> {


    VentanaDefault ventanaDefault;
    ContenedorJuego proximaVista;
    Juego juego;
    Button botonComenzarBatalla;

    public BotonComenzarBatallaHandler(VentanaDefault ventanaDefault, Juego juego, Button botonComenzarBatalla) {
        this.ventanaDefault = ventanaDefault;
        this.botonComenzarBatalla = botonComenzarBatalla;
        this.juego = juego;
    }


    @Override
    public void handle(ActionEvent actionEvent) {

        juego.comenzarPartida();
        juego.setTrampas();

        this.proximaVista = new ContenedorJuego(juego);

        botonComenzarBatalla.setDisable(true);
        ReproductorMusica.fadeOut(Duration.seconds(2));

        Timeline accionDeFadeOut = ReproductorMusica.getTimeline();

        accionDeFadeOut.setOnFinished( (ae) -> {
            this.ventanaDefault.setCenter(this.proximaVista);
        } );
    }
}