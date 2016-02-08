/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagereader;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicola
 */
public class Checker extends Thread {

    protected String oldStringNav;
    protected String oldStringBus;

    public Checker(String name) throws IOException {
        super(name);
        oldStringNav = new PageReader("http://www.actv.it/opendata/navigazione", "nav").parse();
        oldStringBus = new PageReader("http://www.actv.it/opendata/automobilistico", "bus").parse();
    }

    @Override
    public void run() {
        try {
            int i=0;
            while (true) {
                PageReader navpr = new PageReader("http://www.actv.it/opendata/navigazione", "nav");
                PageReader buspr = new PageReader("http://www.actv.it/opendata/automobilistico", "bus");

                if (!oldStringNav.equals(navpr.parse())) {
                    System.out.println(oldStringNav);
                    navpr.download(navpr.parse()); //cerco l'ultimo zip disponibile e lo scarico
                    navpr.updateFiles(); //chiamo il metodo che estrare lo zip aggiornato nelle cartelle
                    //Dobbiamo far partire il csv reader e da li poi aggiornare il database
                    System.out.println("call update db nav "+navpr.parse());
                    //salvo il nome dell'ultimo archivo in modo da poterlo confrontare se cambia 
                    oldStringNav=navpr.parse();
                }else{              
                    System.out.println("nav not changed");
                }

                if (!oldStringBus.equals(buspr.parse())) {
                    System.out.println(oldStringBus);
                    buspr.download(buspr.parse());//cerco l'ultimo zip disponibile e lo scarico
                    buspr.updateFiles();//come sopra
                    //Dobbiamo far partire il csv reader e da li poi aggiornare il database
                    System.out.println("call update db bus "+buspr.parse());
                    oldStringBus=buspr.parse();
                }else{
                    System.out.println("bus not changed");
                }
                navpr = null;
                buspr = null;
                sleep(60000 * 24);//un giorno di attesa prima di cercare aggiornamenti e scaricarli!
            }
        } catch (InterruptedException | IOException ex ) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("exited");
    }

    public static void main(String[] args) {
        try {
            System.out.println("checker");
            Checker test = new Checker("Checker");
            test.start();
        } catch (IOException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
