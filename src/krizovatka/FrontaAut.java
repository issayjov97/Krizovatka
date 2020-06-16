/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krizovatka;

import java.util.Iterator;
import java.util.function.Consumer;
import kolekce.Fronta;
import kolekce.IFronta;
import kolekce.KolekceException;

/**
 *
 * @author dzhohar
 */
public class FrontaAut implements IFronta<Auto> {

    private static Consumer<String> logging;

    private final String name;
    private final IFronta<Auto> fronta;
    private Consumer<Auto> hlaseniPrijezdu;
    private Consumer<Auto> hlaseniOdjezdu;

    public FrontaAut(String name) {
        this.name = name;
        fronta = new Fronta<>();
    }

    @Override
    public void vloz(Auto auto) {
        try {
            fronta.vloz(auto);
            if (logging != null) {
                logging.accept("Prijezd ze smeru " + name + " " + auto.toString());
            }
            if (hlaseniPrijezdu != null) {
                hlaseniPrijezdu.accept(auto);
            }
        } catch (KolekceException ex) {
        }
    }

    @Override
    public int getPocet() {
        return fronta.getPocet();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean jePrazdny() {
        return fronta.jePrazdny();
    }

    @Override
    public Auto odeber() {
        try {
            Auto auto = fronta.odeber();
            if (logging != null) {
                logging.accept("Odjezd ze smeru " + name + " " + auto.toString());
            }
            if (hlaseniOdjezdu != null) {
                hlaseniOdjezdu.accept(auto);
            }
            return auto;
        } catch (Exception e) {
        }
        return null;
    }

    public static Consumer<String> getLogging() {
        return logging;
    }

    public static void setLogging(Consumer<String> logging) {
        FrontaAut.logging = logging;
    }

    public Consumer<Auto> getHlaseniPrijezdu() {
        return hlaseniPrijezdu;
    }

    public void setHlaseniPrijezdu(Consumer<Auto> hlaseniPrijezdu) {
        this.hlaseniPrijezdu = hlaseniPrijezdu;
    }

    public Consumer<Auto> getHlaseniOdjezdu() {
        return hlaseniOdjezdu;
    }

    public void setHlaseniOdjezdu(Consumer<Auto> hlaseniOdjezdu) {
        this.hlaseniOdjezdu = hlaseniOdjezdu;
    }

    @Override
    public void zrus() {
        fronta.zrus();
    }

    @Override
    public Iterator<Auto> iterator() {
        return fronta.iterator();
    }

}
