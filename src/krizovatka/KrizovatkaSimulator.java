package krizovatka;

import java.util.function.Consumer;
import kolekce.IFronta;
import kolekce.IMapa;
import kolekce.KolekceException;
import kolekce.Mapa;

/**
 *
 * @author
 */
public class KrizovatkaSimulator implements IKrizovatka {

    private final IMapa<Smer, FrontaAut> fronty;
    private final IMapa<Smer, RPrijezdu> prijezdy;
    private final IMapa<SmerPrujezdu, ROdjezdu> odjezdy;
    private final Semafor semafor;
    private long dobaPrujezdu = 900;

    public KrizovatkaSimulator() throws KolekceException {
        fronty = new Mapa<>();
        FrontaAut vychodF = new FrontaAut("Vychod");
        FrontaAut severF = new FrontaAut("Sever");
        FrontaAut jihF = new FrontaAut("Jih");
        FrontaAut zapadF = new FrontaAut("Zapad");
        fronty.vloz(Smer.JIH, jihF);
        fronty.vloz(Smer.SEVER, severF);
        fronty.vloz(Smer.ZAPAD, zapadF);
        fronty.vloz(Smer.VYCHOD, vychodF);
        prijezdy = new Mapa<>();
        prijezdy.vloz(Smer.JIH, new RPrijezdu(1400, jihF));
        prijezdy.vloz(Smer.SEVER, new RPrijezdu(1700, severF));
        prijezdy.vloz(Smer.ZAPAD, new RPrijezdu(2300, zapadF));
        prijezdy.vloz(Smer.VYCHOD, new RPrijezdu(2000, vychodF));
        odjezdy = new Mapa<>();
        ROdjezdu sevJih = new ROdjezdu(dobaPrujezdu, SmerPrujezdu.SEVER_JIH ,jihF,severF);
        ROdjezdu vychZap = new ROdjezdu(dobaPrujezdu, SmerPrujezdu.VYCHOD_ZAPAD, zapadF,vychodF);
        odjezdy.vloz(SmerPrujezdu.SEVER_JIH, sevJih);
        odjezdy.vloz(SmerPrujezdu.VYCHOD_ZAPAD, vychZap);
        semafor = new Semafor(5000, 7500, odjezdy);
      

    }

    @Override
    public void setSemaforDobaZelena(SmerPrujezdu prujezd, long x) {
         semafor.setDobaSviteni(prujezd , x);
    
    }

    @Override
    public long getSemaforDobaZelena(SmerPrujezdu prujezd) {
            return semafor.getDobaSviteni(prujezd);
    }

    @Override
    public void setCetnostPrijezdu(Smer prijezd, long cetnost) {
        prijezdy.dej(prijezd).setCetnost(cetnost);
    }

    @Override
    public long getCetnostPrijezdu(Smer prijezd) {
        return prijezdy.dej(prijezd).getCetnost();
    }

    @Override
    public void setDobaPrujezdu(long s) {
        this.dobaPrujezdu = s;
    }

    @Override
    public long getDobaPrujezdu() {
        return dobaPrujezdu;

    }

    @Override
    public int getPocetCekajicichZeSmeru(Smer smer) {
        return fronty.dej(smer).getPocet();
    }

    @Override
    public IFronta<Auto> getFrontaSmeru(Smer smer) {
        return fronty.dej(smer);

    }

    @Override
    public void setHlaseniPrijezduZeSmeru(Smer smer, Consumer<Auto> hlaseni) {
        fronty.dej(smer).setHlaseniPrijezdu(hlaseni);
    }

    @Override

    public void setHlaseniOdjezduZeSmeru(Smer smer, Consumer<Auto> hlaseni) {
        fronty.dej(smer).setHlaseniOdjezdu(hlaseni);

    }

    @Override
    public void setHlaseniSemaforu(Consumer<SmerPrujezdu> hlaseni) {
        semafor.setHlaseniSemaforu(hlaseni);
    }

    @Override
    public void start() {
        semafor.start();
    }

    @Override
    public void stop() {
        semafor.stop();
    }

    @Override
    public void pause() {
        semafor.pause();
    }

}
