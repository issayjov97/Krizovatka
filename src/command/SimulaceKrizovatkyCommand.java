package command;

import casovani.Casovac;
import kolekce.KolekceException;
import krizovatka.IKrizovatka;
import krizovatka.KrizovatkaSimulator;

/**
 *
 * @author
 */
public class SimulaceKrizovatkyCommand {
    
    private static int i = 0;
    
    public static void main(String[] args)
            throws KolekceException, InterruptedException {
        IKrizovatka krizovatka = new KrizovatkaSimulator();
   
                Casovac.instance().start();
      
        krizovatka.setHlaseniSemaforu(
                s -> System.err.printf("T= %04d: Zmena smeru na %s\n", i, s));
        
        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> System.out.printf("T= %04d: Prijezd ze severu %s\n", i, s));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> System.out.printf("T= %04d: Odjezd ze severu %s\n", i, s));
        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> System.out.printf("T= %04d: Prijezd ze jihu %s\n", i, s));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> System.out.printf("T= %04d: Odjezd ze jihu %s\n", i, s));
        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s -> System.out.printf("T= %04d: Prijezd ze zapadu %s\n", i, s));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s -> System.out.printf("T= %04d: Odjezd ze zapadu %s\n", i, s));
        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s -> System.out.printf("T= %04d: Prijezd ze vychodu %s\n", i, s));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s -> System.out.printf("T= %04d: Odjezd ze vychodu %s\n", i, s));        
        while (true) {
            Thread.sleep(1000);
            i++;
            
        }
    }
}
