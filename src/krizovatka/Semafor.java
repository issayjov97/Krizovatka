/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krizovatka;

import casovani.Casovac;
import java.util.function.Consumer;
import kolekce.IMapa;
import kolekce.KolekceException;

/**
 *
 * @author dzhohar
 */
public class Semafor {
    
    private final IMapa<IKrizovatka.SmerPrujezdu, ROdjezdu> smeryOdjezdu;
    private boolean running;
    private long x1;
    private long x2;
    private Consumer<IKrizovatka.SmerPrujezdu> hlaseniSemaforu;
    private IKrizovatka.SmerPrujezdu soucasnySmer;
    private long cas;

    Semafor(long i, long i0, IMapa<IKrizovatka.SmerPrujezdu, ROdjezdu> odjezdy) throws KolekceException{
        this.smeryOdjezdu = odjezdy;
        x1 = i;
        x2 = i0;
        running = true;
        soucasnySmer = IKrizovatka.SmerPrujezdu.SEVER_JIH;
        
        Casovac.instance().pridej(() -> {
            cas += Casovac.PERIODA;
            
            switch (soucasnySmer) {
                case SEVER_JIH:
                    ovladejSemafor(soucasnySmer, IKrizovatka.SmerPrujezdu.VYCHOD_ZAPAD, x1);
                    break;
                case VYCHOD_ZAPAD:
                    ovladejSemafor(soucasnySmer, IKrizovatka.SmerPrujezdu.SEVER_JIH, x2);
                    break;
            }
        });
    }


    
    private void ovladejSemafor(IKrizovatka.SmerPrujezdu soucasny, IKrizovatka.SmerPrujezdu novy, long dobaZelena) {
        if ( running && cas >= dobaZelena ) {
            smeryOdjezdu.dej(soucasny).stop();
            smeryOdjezdu.dej(novy).start();
            
            if (hlaseniSemaforu != null) {
                hlaseniSemaforu.accept(novy);
            }
            
            soucasnySmer = novy;
            cas = 0;
        }
    }

    public long getDobaSviteni(IKrizovatka.SmerPrujezdu prujezd) {
        switch (prujezd) {
            case SEVER_JIH:
                return x1;
            case VYCHOD_ZAPAD:
                return x2;
                    
        }
        return -1;
    }

    public void setDobaSviteni(IKrizovatka.SmerPrujezdu prujezd, long dobaSviteni) {
        switch (prujezd) {
            case SEVER_JIH:
                this.x1 = dobaSviteni;
                break;
            case VYCHOD_ZAPAD:
                this.x2 = dobaSviteni;
                break;
        }
    }
    
    

    void start() {
        running = true;
    }

    public long getX1() {
        return x1;
    }

    public void setX1(long x1) {
        this.x1 = x1;
    }

    public long getX2() {
        return x2;
    }

    public void setX2(long x2) {
        this.x2 = x2;
    }

    void setHlaseniSemaforu(Consumer<IKrizovatka.SmerPrujezdu> hlaseni) {
        this.hlaseniSemaforu = hlaseni; 
    }

    void stop() {
        running = false; 
    }

    void pause() {
        running = false;
    }

    
}
