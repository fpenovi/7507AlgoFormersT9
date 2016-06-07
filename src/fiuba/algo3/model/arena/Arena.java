package fiuba.algo3.model.arena;

import fiuba.algo3.model.espacio.*;
import fiuba.algo3.model.unidades.Algoformer;

import java.util.HashMap;
import java.util.Map;

public class Arena {

	private static Arena instancia = new Arena();
    private Map<Punto, Casillero> arena;
    private final int ANCHO = 51;
    private final int ALTO = 51;


    private Arena() {
        this.arena = new HashMap<>();
    }


    public void inicializar() {

        for (int i = 1; i <= ANCHO; i++) {
            for (int j = 1; j <= ALTO; j++) {

                Punto puntoTierra = new PuntoTierra(i, j);
                Casillero casilleroTierra = new Casillero(new Rocoso());  // Hay que ver de pasarle el terreno adecuado (Aire o Tierra)
                arena.put(puntoTierra, casilleroTierra);

                Punto puntoAire = new PuntoAire(i, j);
                Casillero casilleroAire = new Casillero(new Nube());  // Hay que ver de pasarle el terreno adecuado (Aire o Tierra)
                arena.put(puntoAire, casilleroAire);
            }
        }
    }


    public void colocarChispa(Punto punto) {
        Casillero casilla_media = arena.get(punto);
        casilla_media.colocar(new Chispa());
    }

    
    public boolean contieneChispa(Punto punto){
    	Casillero buscado = arena.get(punto);
    	return buscado.contieneChispa();
    }
    
	public Chispa obtenerChispa(Punto punto) {
		Casillero buscado = arena.get(punto);
		return buscado.removerChispa();
	}


    public static Arena getInstance(){
    	return instancia;
    }


    public Boolean estaEnArena(Punto punto){
    	return arena.containsKey(punto);
    }


    public Boolean estaOcupado(Punto punto){
    	Casillero aRevisar = arena.get(punto);
    	return aRevisar.estaOcupado();
    }


    public void ubicarAlgoformer(Algoformer algoformer, Punto punto) {

        if (estaOcupado(punto))
            return;

        arena.get(punto).colocar(algoformer);
        algoformer.setUbicacion(punto);
    }
    
    public void setTerrenoEnPunto(Punto punto, Terreno terreno){
    	arena.get(punto).setTerreno(terreno);
    }


    public Algoformer removerAlgoformerEn(Punto punto) {
        return arena.get(punto).removerAlgoformer();
    }
}
