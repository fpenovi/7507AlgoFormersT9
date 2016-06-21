package fiuba.algo3.controller;

import fiuba.algo3.model.juego.Juego;
import fiuba.algo3.view.layouts.PanelLateral;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class BotonTerminarTurnoHandler implements EventHandler<ActionEvent> {

    private Label infoJugador;
    private Label infoEquipo;
    private Label infoTurno;
    private Juego modelJuego;
    private Text msjError;

    public BotonTerminarTurnoHandler(PanelLateral panelLateral, Juego juego,Text msjError) {
        this.infoJugador = (Label) panelLateral.lookup("#infoJugador");
        this.infoEquipo = (Label) panelLateral.lookup("#infoEquipo");
        this.infoTurno = (Label) panelLateral.lookup("#infoTurno");
        this.msjError = msjError;
        this.modelJuego = juego;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
    	modelJuego.finalizarTurno();
    	String nombreJug = modelJuego.getJugadorEnTurno().getNombre();
        String equipoJug = modelJuego.getJugadorEnTurno().getEquipo();
        String numeroTurno = Integer.toString(modelJuego.getTurno());

        infoJugador.setText("Jugador: " + nombreJug);
        infoTurno.setText("Turno: " + numeroTurno);
        infoEquipo.setText("Equipo: " + equipoJug);
        msjError.setText("");

    }


}