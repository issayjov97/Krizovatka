/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author dzhohar
 */
public class SerializaceSeznam implements Serializable {

    private final File file;

    public SerializaceSeznam() {
        file = new File("serializace.bin");
    }

    public void writeSz(Seznam seznam) throws FileNotFoundException, IOException {
        if (seznam.getPocet() == 0) {
            System.out.println("Seznam je prazdny!!!");
            return;
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(seznam);
    }

    public Seznam readSz() throws FileNotFoundException, IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Seznam seznam = (Seznam) ois.readObject();
        return seznam;
    }

}
