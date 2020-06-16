/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekce;


import java.util.Iterator;


/**
 *
 * @author dzhohar
 * @param <K>
 * @param <E>
 */
public class Mapa<K, E> implements IMapa<K, E> {

    Seznam<Entry> seznam = new Seznam<>();

    @Override
    public void vloz(K klic, E data) throws KolekceException {
        seznam.pridej(new Entry(klic, data));
    }

    @Override
    public E dej(K klic) {
        if (!seznam.jePrazdny()) {
            for (Entry e : seznam) {
                if (e.getKey() == klic) {
                    return e.getData();
                }
            }

        }
        return null;
    }

    @Override
    public E odeber(K klic) throws KolekceException {
        if (seznam.jePrazdny()) {
            throw new KolekceException();
        }
        seznam.nastavPrvni();

        while (seznam.jeDalsi()) {

            if (seznam.zpristupni().getKey() == klic) {
                return seznam.odeber().getData();
            }
            seznam.prejdiNaDalsi();
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<Entry> it = seznam.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public E next() {
                return it.next().getData();
            }
        };
    }

  
    private class Entry {

        private final K key;
        private final E data;

        public Entry(K key, E data) {
            this.key = key;
            this.data = data;
        }

        public K getKey() {
            return key;
        }

        public E getData() {
            return data;
        }

    }
}
