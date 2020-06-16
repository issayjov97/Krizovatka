package kolekce;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 *
 * @author dzhohar
 * @param <E>
 */
public class Seznam<E> implements IKolekce<E>, Iterable<E>, Serializable {

    private Prvek prvni;
    private Prvek aktualni;

    @Override
    public E odeber() throws KolekceException {

        Prvek n = prvni;
        Prvek n1 = null;
        if (jePrazdny()) {
            throw new KolekceException();

        }

        for (int i = 0; i < getPocet() - 2; i++) {
            n = n.dalsi;

        }
        n1 = n.dalsi;
        n.dalsi = null;
        return n1.data;

    }

    @Override
    public int getPocet() {
        Prvek prvek = prvni;

        int length = 0;
        while (prvek != null) {
            prvek = prvek.dalsi;
            length++;
        }
        return length;
    }

    @Override
    public void nastavPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        aktualni = prvni;
    }

    @Override
    public boolean jeDalsi() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException();
        }
        return aktualni.dalsi != null;
    }

    @Override
    public void prejdiNaDalsi() throws KolekceException {
        if (jeDalsi()) {
            aktualni = aktualni.dalsi;
        } else {
            throw new KolekceException();
        }
    }

    @Override
    public boolean jePrazdny() {
        return getPocet() == 0;
    }

    @Override
    public void pridej(E data) throws KolekceException{
        Prvek prvek = new Prvek();
        prvek.data = data;
        if (prvni == null) {
            prvni = prvek;

        } else {
            Prvek nPrvek = prvni;
            while (nPrvek.dalsi != null) {
                nPrvek = nPrvek.dalsi;

            }
            nPrvek.dalsi = prvek;

        }

    }

    @Override
    public E odeberPrvni() throws KolekceException {
        if (jePrazdny()) {

            throw new KolekceException("Seznam je prazdny");
        }
        Prvek prvek = prvni;
        prvni = prvek.dalsi;
        return prvek.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Prvek n = prvni;

            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = n.data;
                n = n.dalsi;
                return data;
            }
        };
    }

    @Override
    public E zpristupni() throws KolekceException {
        if (aktualni == null) {
            throw new KolekceException();
        }
        return aktualni.getData();
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[getPocet()];
        int i = 0;
        for (E e : this) {
            array[i] = e;
            i++;
        }
        return array;
    }

    @Override
    public E[] toArray(E[] array) throws IllegalArgumentException {
        E[] pole = array;

        if (getPocet() > pole.length) {
          throw new IllegalArgumentException();

        }

        int i = 0;
        for (E e : this) {
            pole[i] = e;
            i++;
        }
        if (i < pole.length) {
            pole[i] = null;
        }
        return pole;
    }



    @Override
    public E[] toArray(Function<Integer, E[]> createFunction) {
        E[] array = createFunction.apply(getVelikost());
        int i = 0;
        for (E e : this) {
            array[i] = e;
            i++;
        }
        return array;
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        if (jePrazdny()) {

            throw new KolekceException();
        }
        Prvek prvek = prvni;
        Prvek n = null;
        for (int i = 0; i < getPocet() - 2; i++) {
            prvek = prvek.dalsi;

        }
        n = prvek.dalsi;
        prvek.dalsi = null;
        return n.data;
    }

    @Override
    public void zrus() {
        if (!jePrazdny()) {
            while (prvni != null) {
                prvni = prvni.dalsi;
            }

        }
    }

    private class Prvek implements Serializable {

        private E data;
        private Prvek dalsi;

        public E getData() {
            return data;
        }

        public Prvek getDalsi() {
            return dalsi;
        }

        public void setDalsi(Prvek dalsi) {
            this.dalsi = dalsi;
        }

    }
}
