/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krizovatka;

import casovani.Casovac;
import casovani.Posluchac;
import kolekce.IMapa;
import kolekce.KolekceException;
import kolekce.Mapa;
import krizovatka.IKrizovatka.SmerPrujezdu;

/**
 *
 * @author dzhohar
 */
public class ROdjezdu {

    private long dobaPrujezduAuta;
    private final IMapa<SmerPrujezdu, FrontaAut> seznamFront;
    private long cas;
    private volatile boolean running;

    public ROdjezdu(long dobaPrujezduAuta, SmerPrujezdu smer, FrontaAut... fronty) throws KolekceException {
        this.dobaPrujezduAuta = dobaPrujezduAuta;
        this.seznamFront = new Mapa<>();
        for (FrontaAut frontaAut : fronty) {
            seznamFront.vloz(smer, frontaAut);
        }
        this.running = false;
        Casovac.instance().pridej(new Posluchac() {
            @Override
            public void aktualizuj() {
                cas += Casovac.PERIODA;
                if (running && cas >= dobaPrujezduAuta) {
                    seznamFront.stream().forEach(( f) -> {
                        if (!f.jePrazdny()) {
                            f.odeber();
                        }
                        
                    });
                    cas = 0;
                }
            }
        });

    }
       public synchronized void start(){
        cas = 0;
        running = true;
    }

    public synchronized void stop(){
        running = false;
    }

    public synchronized void setDobaPrujezdu(long dobaPrujezdu){
        this.dobaPrujezduAuta = dobaPrujezdu;
    }

    public synchronized long getDobaPrujezdu(){
        return this.dobaPrujezduAuta;
    }

}
