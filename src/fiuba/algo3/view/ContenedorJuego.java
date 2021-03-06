package fiuba.algo3.view;

import fiuba.algo3.controller.ClickEnMapaHandler;
import fiuba.algo3.model.arena.Arena;
import fiuba.algo3.model.arena.Bonus;
import fiuba.algo3.model.arena.TerrenoAplicable;
import fiuba.algo3.model.espacio.Punto;
import fiuba.algo3.model.juego.Juego;
import fiuba.algo3.model.unidades.Algoformer;
import fiuba.algo3.view.layouts.PanelAbajo;
import fiuba.algo3.view.layouts.PanelLateral;
import fiuba.algo3.view.utilities.ConvertidorPuntoAPixels;
import fiuba.algo3.view.utilities.PuntoPixels;
import fiuba.algo3.view.vistas.VistaArena;
import fiuba.algo3.view.vistas.VistaMapaAlgoformers;
import fiuba.algo3.view.vistas.VistaMapaBonuses;
import fiuba.algo3.view.vistas.VistaPosicionChispa;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class ContenedorJuego extends BorderPane {

    private Juego juego;
    private PanelLateral panelLateral;
    private PanelAbajo panelAbajo;
    private ScrollPane contenedorCentral;
    private Canvas canvasAlgoformers;
    private Canvas canvasBonuses;
    private Canvas canvasPosicionChispa;
    private VistaMapaAlgoformers vistaAlgoformers;
    private VistaMapaBonuses vistaBonuses;
    private VistaArena vistaArena;
    private VistaPosicionChispa vistaPosicionChispa;
    private Background fondoPaneles = new Background(new BackgroundFill(Color.web("#000f3d"), null, null));
    private String estiloNegro = "-fx-base: #474747;";
    private Text msjError;
    private Algoformer algoformerSeleccionado;
    private TerrenoAplicable terrenoSeleccionado;
    private Bonus bonusSeleccionado;
    private Algoformer algoformerAccionado;
    private ConvertidorPuntoAPixels conversor;
    private int canvasSize = 2040;


    public ContenedorJuego(Juego juego) {
        this.juego = juego;
        crearContenedorCentral();
        crearPanelLateral();
        crearPanelAbajo();
        this.conversor = new ConvertidorPuntoAPixels();
        this.panearCamara();
        this.actualizarStatsLateral(juego.getJugadorEnTurno().getUltimoAlgoformerUtilizado().getUbicacion());
    }


    private void crearPanelLateral() {
        this.panelLateral = new PanelLateral(this, fondoPaneles);
        this.setLeft(panelLateral);
    }


    private void crearPanelAbajo() {
        this.panelAbajo = new PanelAbajo(this, fondoPaneles);
        this.setBottom(panelAbajo);
    }


    private void crearContenedorCentral() {
        this.contenedorCentral = new ScrollPane();
        StackPane contenedorCanvases = new StackPane();
        contenedorCanvases.setId("contenedorStackPane");
        contenedorCanvases.setOnMouseClicked(new ClickEnMapaHandler(this));

        this.canvasBonuses = new Canvas(canvasSize, canvasSize);
        this.canvasAlgoformers = new Canvas(canvasSize, canvasSize);
        this.canvasPosicionChispa = new Canvas(canvasSize,canvasSize);

        this.vistaArena = new VistaArena(102, 51);
        this.vistaArena.dibujarArena();
        this.vistaBonuses = new VistaMapaBonuses(canvasBonuses);
        this.vistaBonuses.mostrar();

        this.vistaAlgoformers = new VistaMapaAlgoformers(canvasAlgoformers);
        this.vistaAlgoformers.mostrar();
        
        this.vistaPosicionChispa = new VistaPosicionChispa(canvasPosicionChispa,juego.getPosicionChispa());

        contenedorCanvases.getChildren().addAll(vistaArena, canvasBonuses, canvasAlgoformers, canvasPosicionChispa);
        contenedorCentral.setContent(contenedorCanvases);
        contenedorCentral.setStyle(estiloNegro);
        contenedorCentral.setPannable(true);
        contenedorCentral.setVvalue(0.5);   // Para que el scroll vertical arranque a la mitad
        this.setCenter(contenedorCentral);
    }

    public void seleccionarCasillero(double x, double y) {

        PuntoPixels parPixel = new PuntoPixels(x, y);
        Punto ubicacion = conversor.reconvertir(parPixel);

        algoformerSeleccionado = Arena.getInstance().obtenerAlgoformerEn(ubicacion);
        terrenoSeleccionado = Arena.getInstance().devolverTerrenoEn(ubicacion);
        bonusSeleccionado = Arena.getInstance().devolverBonusEn(ubicacion);
        panelLateral.actualizarStats(algoformerSeleccionado, bonusSeleccionado, terrenoSeleccionado);
        if (algoformerSeleccionado == null)
        	mostrarAcciones(false);
       else
    	   mostrarAcciones(true);
    }

    private void setPantalla(PuntoPixels parPixel) {
		double valorEnX = parPixel.getX() / (double)canvasSize;
		double valorEnY = parPixel.getY() / (double)canvasSize;
		contenedorCentral.setHvalue(valorEnX);
		contenedorCentral.setVvalue(valorEnY);
	}


	public void actualizarStatsLateral(Punto punto){
    	PuntoPixels puntoEnPixeles = conversor.convertir(punto);
    	seleccionarCasillero(puntoEnPixeles.getX(),puntoEnPixeles.getY());
    }


    public void panearCamara() {
        Punto ubicacion = juego.getJugadorEnTurno().getUltimoAlgoformerUtilizado().getUbicacion();
        PuntoPixels ubicacionPixeles = conversor.convertir(ubicacion);
        
        setPantalla(ubicacionPixeles);
    }
        
    public void panearCamara(Punto punto){
    	PuntoPixels ubicacionPixeles = conversor.convertir(punto);
        
        setPantalla(ubicacionPixeles);
    }


    public Text getMsjError() {
        return this.msjError;
    }


    public Juego getJuego() {
        return this.juego;
    }


    public PanelLateral getPanelLateral() {
        return this.panelLateral;
    }

	public PanelAbajo getPanelAbajo() {
		return this.panelAbajo;
	}

    public VistaMapaAlgoformers getVistaMapaAlgoformers() {
        return this.vistaAlgoformers;
    }


    public VistaMapaBonuses getVistaMapaBonuses() {
        return this.vistaBonuses;
    }
    
	public VistaPosicionChispa getVistaPosicionChispa() {
		return this.vistaPosicionChispa;
	}   


    public Algoformer getAlgoformerAccionado() {
        return algoformerAccionado;
    }


    public void setMsjError(Text msjError) {
        this.msjError = msjError;
    }


    public void setAccionado(Algoformer algoformerOpcional) {
        if (algoformerOpcional != null)
            this.algoformerSeleccionado = algoformerOpcional;

        this.algoformerAccionado = this.algoformerSeleccionado;
    }

    
    private void mostrarAcciones(boolean disable) {
        HBox contenedorAcciones = (HBox) this.getPanelAbajo().lookup("#contenedorAcciones");
        contenedorAcciones.setVisible(disable);
    }
 
}
