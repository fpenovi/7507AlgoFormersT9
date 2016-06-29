package fiuba.algo3.controller.acciones;

import fiuba.algo3.model.espacio.Punto;
import fiuba.algo3.model.juego.Jugador;
import fiuba.algo3.model.unidades.Algoformer;
import fiuba.algo3.model.unidades.CombinacionYaSeEncuentraCombinadaException;
import fiuba.algo3.model.unidades.NoHaySuficientesAlgoformersAdyacentesException;
import fiuba.algo3.view.ContenedorJuego;
import fiuba.algo3.view.utilities.ConvertidorPuntoAPixels;
import fiuba.algo3.view.utilities.PuntoPixels;
import fiuba.algo3.view.utilities.ReproductorFX;
import fiuba.algo3.view.vistas.vistasAlgoformers.VistaAlgoformer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

public class BotonAccionCombinarseHandler extends BotonAccionHandler implements EventHandler<ActionEvent> {
	
	public BotonAccionCombinarseHandler(ContenedorJuego contenedor){
		super(contenedor);
	}


	@Override
	public void handle(ActionEvent arg0) {
		Jugador enTurno = contenedorJuego.getJuego().getJugadorEnTurno();
		ConvertidorPuntoAPixels convertidor = new ConvertidorPuntoAPixels();

		Map<VistaAlgoformer,Punto> mapaDeVistasPuntos = new HashMap<>();

		try {
			for(Algoformer algoformer: enTurno.getAlgoformers()){
				mapaDeVistasPuntos.put(contenedorJuego.getVistaMapaAlgoformers().getVista(algoformer), algoformer.getUbicacion());
			}

			enTurno.combinarAlgoformers();
		}catch(NoHaySuficientesAlgoformersAdyacentesException | CombinacionYaSeEncuentraCombinadaException e){
			ReproductorFX.reproducirFX(ReproductorFX.ERROR1);
			contenedorJuego.getMsjError().setText(e.getMessage());
			return;
		}

		for (Map.Entry<VistaAlgoformer, Punto> par : mapaDeVistasPuntos.entrySet()) {
			VistaAlgoformer vistaActual = par.getKey();
			Punto ubicacion = par.getValue();
			if (ubicacion != null) {
				PuntoPixels punto = convertidor.convertir(ubicacion);
				vistaActual.limpiar(punto.getX(), punto.getY());
			}
		}

		Algoformer combinacion = enTurno.getAlgoformers().get(0);
		contenedorJuego.getVistaMapaAlgoformers().agregarEntradaCombinacion(combinacion, enTurno.getEquipo());
		VistaAlgoformer vistaCombinacion = contenedorJuego.getVistaMapaAlgoformers().getVista(combinacion);
		PuntoPixels pixeleado = convertidor.convertir(combinacion.getUbicacion());
		vistaCombinacion.dibujar(pixeleado.getX(),pixeleado.getY());
		contenedorJuego.seleccionarCasillero(pixeleado.getX(),pixeleado.getY());
		enTurno.setUltimoAlgoformerUtilizado(combinacion);
		deshabilitarAcciones();


	}

}
