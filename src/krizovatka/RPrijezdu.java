/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krizovatka;

import casovani.Casovac;
import casovani.Posluchac;
import kolekce.KolekceException;

/**
 *
 * @author dzhohar
 */
public class RPrijezdu {

    private long intervalPrijezdu;
    private final FrontaAut prijezd;

    private long cas;

    public RPrijezdu(long cetnost, FrontaAut prijezd) throws KolekceException {
        intervalPrijezdu = vypocetIntervaluPrijezdu(cetnost);
        this.prijezd = prijezd;
        Casovac.instance().pridej(new Posluchac() {
            @Override
            public void aktualizuj() {
                cas += Casovac.PERIODA;
                if (cas >= intervalPrijezdu) {
                    try {
                                prijezd.vloz(new Auto());
                    } catch (Exception e) {
                    }
            
                    cas = 0;
                }
            }
        });
    }

    public FrontaAut getPrijezd() {
        return prijezd;
    }

    public void setCetnost(long cetnost) {
        intervalPrijezdu = vypocetIntervaluPrijezdu(cetnost);
    }

    public long getCetnost() {
        return (3600 * 1000) / intervalPrijezdu;
    }

    private static long vypocetIntervaluPrijezdu(long cetnost) {
        return (3600 * 1000 / cetnost);
    }

}
