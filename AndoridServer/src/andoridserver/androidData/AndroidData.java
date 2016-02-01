/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nichi
 */
public class AndroidData implements Serializable {

    private String lineNumber;
    private List<String> partenze;
    private List<String> arrivi;
    private int ritardoMedio;

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void popolaPartenze(List<String> p) {
        partenze=p;
    }

    public void popolaArrivi(List<String> a) {
        arrivi=a;
    }
    
    public String request(){
        return "";
    }

    public void setRitardoMedio(int ritardoMedio) {
        this.ritardoMedio = ritardoMedio;
    }

}
