package fiuba.algo3.model.arena;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreadorDeTerrenoAleatorio {
	
	private static CreadorDeTerrenoAleatorio instancia = new CreadorDeTerrenoAleatorio();
	private Random random = new Random();
	private List<CreadorDeTerreno> terrenosAereos = Arrays.asList(
			new CreadorNube(),
			new CreadorNube(),
			new CreadorNube(),
			new CreadorNube(),
			new CreadorNube(),
			new CreadorNube(),
			new CreadorNube(),
			new CreadorNube(),
			new CreadorNebulosaDeAndromeda(),
			new CreadorTormentaPsionica());
		//Ajustar repeticion de Nube para controlar la 
		//cantidad de terrenos con efectos que parecen en el mapa
	private List<CreadorDeTerreno> terrenosTerrestres = Arrays.asList(
			new CreadorRocoso(),
			new CreadorRocoso(),
			new CreadorRocoso(),
			new CreadorRocoso(),
			new CreadorRocoso(),
			new CreadorRocoso(),
			new CreadorRocoso(),
			new CreadorRocoso(),
			new CreadorEspinas(),
			new CreadorPantano());
		//Ajustar repeticion de Rocoso para controlar la 
		//cantidad de terrenos con efectos que parecen en el mapa
	
	CreadorDeTerrenoAleatorio(){
	}
	
	static CreadorDeTerrenoAleatorio getInstance(){
		return instancia;
	}
	
	public TerrenoAplicable crearTerrenoAereoAleatorio(){
		int indice = random.nextInt(terrenosAereos.size());
		return terrenosAereos.get(indice).crearTerreno();
	}
	
	public TerrenoAplicable crearTerrenoTerrestreAleatorio(){
		int indice = random.nextInt(terrenosTerrestres.size());
		return terrenosTerrestres.get(indice).crearTerreno();
	}

}