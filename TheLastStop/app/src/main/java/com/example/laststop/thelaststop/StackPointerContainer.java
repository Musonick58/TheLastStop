package com.example.laststop.thelaststop;

/**
 * Created by nichi on 19/02/2016.
 */
public class StackPointerContainer {

    private Main mainPointer;

    private linee lineePointer;

    private orari orariPointer;

    private static StackPointerContainer ref=null;

    private StackPointerContainer(){}

    public static  StackPointerContainer getInstance(){
        if(ref==null)
            ref = new StackPointerContainer();
        return ref;
    }

    public void addMain(Main o){ this.mainPointer=o; }

    public void addLinee(linee o){ this.lineePointer=o; }

    public void addOrari(orari o){ this.orariPointer=o; }

    public Main getMainPointer(){ return mainPointer; }

    public linee getLineePointer(){ return lineePointer; }

    public orari getOrariPointer(){ return orariPointer; }

}
