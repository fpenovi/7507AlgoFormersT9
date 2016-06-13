package fiuba.algo3.model.arena;

import fiuba.algo3.model.unidades.Algoformer;

abstract class Bonus {
	Efecto efecto;
	
	
	public void aplicarseSobre(Algoformer algoformer) {		
		if (algoformer.contieneEfecto(efecto)){
			algoformer.removerEfecto(efecto);
		}

		algoformer.agregarEfecto(efecto);
	}

	public void setEfecto(Efecto nuevo) {
		this.efecto = nuevo;
	}
}