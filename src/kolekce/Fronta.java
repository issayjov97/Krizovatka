package kolekce;

import java.util.Iterator;

/**
 *
 * @author @param <E>
 */
public class Fronta<E> implements IFronta<E> {

   private Seznam<E> seznam;

    public Fronta() {
        seznam = new Seznam<>();
    }

    @Override
    public int getPocet() {
        return seznam.getPocet();
    }

    @Override
    public boolean jePrazdny() {
        return seznam.jePrazdny();
    }

    @Override
    public void vloz(E data) throws KolekceException {

        seznam.pridej(data);

    }

    @Override
    public E odeber() throws KolekceException {
        return seznam.odeberPrvni();
    }

    @Override
    public void zrus() {
        seznam.zrus();
    }

    @Override
    public Iterator<E> iterator() {
        return seznam.iterator();
    }

}
