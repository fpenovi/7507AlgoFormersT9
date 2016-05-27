package fiuba.algo3.model.arena;

import fiuba.algo3.model.arena.Punto;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class PuntoTest {

    @Test
    public void crearDosPuntosIgualesAlCompararlosSonElMismoPunto() {
        Punto punto1 = new Punto(3, 2, 0);
        Punto punto2 = new Punto(3, 2, 0);

        Assert.assertEquals(punto1, punto2);
    }

    @Test
    public void crearDosPuntosDistintosAlCompararlosSonDistintos() {
        Punto punto1 = new Punto(1, 2, 1);
        Punto punto2 = new Punto(1, 2, 0);

        Assert.assertNotEquals(punto1, punto2);
    }

    @Test
    public void insertarPuntosEnUnHasheableSeHasheanIgualLosPuntosIguales() {

        Set<Punto> setDePuntos = new HashSet<>();
        Punto p1 = new Punto(1, 2, 0);
        Punto p2 = new Punto(1, 2, 0);
        setDePuntos.add(p1);

        Assert.assertTrue(setDePuntos.contains(p2));
    }

}