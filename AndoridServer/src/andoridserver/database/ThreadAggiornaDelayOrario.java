/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;


public class ThreadAggiornaDelayOrario {
    
    public static void main(String args[]) {
        Thread t = Thread.currentThread();
        t.setPriority(10);
        DBAsk aggiorna= new DBAsk();
        
        try{
            t.sleep();
            aggiorna.aggiornaData();
        }catch (InterruptedException e) {
            System.out.println("Thread interrotto");
        }
    }
}