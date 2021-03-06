package fiuba.algo3.controller.MovimientoHandlers;

import fiuba.algo3.model.espacio.Direccion;
import fiuba.algo3.model.espacio.DireccionArriba;
import fiuba.algo3.view.ContenedorJuego;

public class MoverAlgoformerArribaHandler extends MovimientoHandler {

    public MoverAlgoformerArribaHandler(ContenedorJuego contenedor){
        super(contenedor);
    }

    @Override
    Direccion obtenerDireccion(){
        return new DireccionArriba();
    }

}
