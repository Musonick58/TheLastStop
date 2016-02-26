package com.example.laststop.thelaststop;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by John on 25/02/16.
 */

public class Funzioni {

    //Dati 2 orari calcola la differenza in minuti(INT) tra il primo e il secondo
    public static int diffOrari(String arrivo, String ritardo){
        Log.d("metodo", "Sono entrato su diffOrari di Funzioni.java");
        String[] aSplit = arrivo.replace(".", ":").split(":"); // [0]->ore, [1]-> minuti, [2]->secondi
        String[] rSplit = ritardo.replace(".", ":").split(":"); // [0]->ore, [1]-> minuti, [2]->secondi
        int aMin = Integer.parseInt(aSplit[1]);
        int aOre = Integer.parseInt(aSplit[0]);
        int rMin = Integer.parseInt(rSplit[1]);
        int rOre = Integer.parseInt(rSplit[0]);

        if(rOre < aOre){
            int oreMin = (rOre +24 - aOre) * 60; //ore di ritardo
            return (oreMin + (rMin - aMin));
        }
        else{
            int oreMin = (rOre - aOre) * 60; //ore di ritardo
            return (oreMin + (rMin - aMin));
        }
    }

    //Dato un orario e un ritardo calcola l'orario effettivo(STRING)
    public static String sommaMinuti(String orario, int minuti){
        Log.d("metodo", "Sono entrato su sommaMinuti di Funzioni.java");
        String[] oSplit = orario.replace(".", ":").split(":"); // [0]->ore, [1]-> minuti, [2]->secondi
        int oMin = Integer.parseInt(oSplit[1]);
        int oOre = Integer.parseInt(oSplit[0]);
        int oEff = oOre;
        int mEff = oMin + minuti;
        int oRit = (mEff / 60);
        if(mEff >= 60){
            oEff = oEff + oRit;
            mEff = mEff % 60;
            if(oEff >= 24)
                oEff = oEff % 24;
        }
        if (oEff == 0)
            return (oEff + "0." + mEff + "." + "00");
        else
            return (oEff + "." + mEff + "." + "00");
    }

    //Dato un Arraylist<String> di orari(arrivi) e uno di ritardi(ritardi) calcola un ArrayList<String> di orari modificati
    public static ArrayList<String> calcolaRitardi(ArrayList<String > arrivi, ArrayList<String> ritardi){
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < arrivi.size(); i++){
            output.add(i, "" + sommaMinuti(arrivi.get(i), (diffOrari(arrivi.get(i), ritardi.get(i)))));
        }
        return output;
    }


}