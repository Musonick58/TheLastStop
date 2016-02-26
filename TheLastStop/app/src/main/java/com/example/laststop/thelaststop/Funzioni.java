package com.example.laststop.thelaststop;

/**
 * Created by John on 25/02/16.
 */
public class Funzioni {

    public static int diffOrari(String arrivo, String ritardo){
        String[] asplit = arrivo.split("."); // 0->ore, 1-> minuti, 2->secondi
        String[] rsplit = ritardo.split("."); // 0->ore, 1-> minuti, 2->secondi
        int minuti = 0; // minuti di ritardo calcolati
        int amin = Integer.parseInt(asplit[0]);
        int aore = Integer.parseInt(asplit[1]);
        int rmin = Integer.parseInt(rsplit[0]);
        int rore = Integer.parseInt(rsplit[1]);

        return minuti;
    }

}