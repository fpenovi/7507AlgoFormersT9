package fiuba.algo3.model.unidades;

import fiuba.algo3.model.arena.Chispa;
import fiuba.algo3.model.espacio.Direccion;
import fiuba.algo3.model.espacio.Punto;


public abstract class Algoformer {

	String nombre;
	int vida;
	int vidaMax;
	Estado estado;
	Chispa chispa;
	Punto ubicacion;


	Algoformer(String nombre, int vida, Estado estado) {
		this.nombre = nombre;
		this.vida = vida;
		this.vidaMax = vida;
		this.estado = estado;
	}

	
	String getNombre() {
		return this.nombre;
	}


	public int getVida() {
		return this.vida;
	}
	
	public int getVidaMax(){
		return this.vidaMax;
	}


	void setChispa() {
		if (!ubicacion.contieneChispa()){
			throw new ImposibleCapturarChispaException();
		}
		chispa = ubicacion.obtenerChispa();
	}


	public Punto getUbicacion() {
		return this.ubicacion;
	}


	public void setUbicacion(Punto ubicacion) {
		this.ubicacion = ubicacion;
	}


	public boolean estaVivo() {
		return (this.vida > 0);
	}


	public boolean tieneChispa() {
		return (this.chispa != null);
	}


	public void capturarChispa() {
		this.estado.capturarChispa(this);
	}

	
	Estado getEstado() {
		return this.estado;
	}


	void setEstado(Estado nuevo) {
		this.estado = nuevo;
	}


	public void moverseHacia(Direccion direccion) {
		ubicacion = estado.moverse(direccion);
	}


	public void reiniciarMovimiento() {
		this.estado.reiniciarMovimiento(this.ubicacion);
	}


	public void transformarse() {
		this.estado.transformar(this);
	}
	
	public void recibirDanio(int danio){
		this.vida = vida - danio;
	}


	public abstract void combinarse();
	public abstract void atacar(Algoformer atacado);
	abstract void recibirAtaque(Autobot atacante, int danio);
	abstract void recibirAtaque(Decepticon atacante, int danio);

}
