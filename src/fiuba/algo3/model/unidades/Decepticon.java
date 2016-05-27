package fiuba.algo3.model.unidades;

public abstract class Decepticon extends Algoformer {


    Decepticon(String nombre, int vida, Estado estado) {
        super(nombre, vida, estado);
    }


    @Override
    public void atacar(Algoformer atacado) {
        this.estado.atacar(atacado, this);
    }


    @Override
    void recibirAtaque(Algoformer atacante,int danio) {
        this.recibirAtaque(atacante,danio);
    }


    @Override
    void recibirAtaque(Decepticon otro,int danio) {
        System.out.println("No pasa nada, friendly-fire Decepticon-Decepticon");
    }


    @Override
    void recibirAtaque(Autobot otro, int danio) {
    	vida= vida-danio;
        System.out.println("Autobot ataca a Decepticon");
    }

}