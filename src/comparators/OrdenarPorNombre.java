package comparators;

import java.util.Comparator;

import almacenamiento.Producto;

// clase que se va a instanciar cuando se necesite ordenar productos por nombre
public class OrdenarPorNombre  implements Comparator<Producto> {

    @Override
    public int compare(Producto o1, Producto o2) {
        return o1.getNombre().compareTo(o2.getNombre());
    }
}
