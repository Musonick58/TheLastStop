/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nichi
 */
public class AndroidDataLinee implements AndroidDataInterface {
     final String objectName="Linee";
    
    List<String> data;
    
    public AndroidDataLinee(){
        this.data=new ArrayList<>();
    }
    
    
    @Override
    public String getNameObject() {
        return objectName; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getDataAsList() {
        return data; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addData(String dato) {
        data.add(dato); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setData(String s, int index) {
        data.add(index, s); //To change body of generated methods, choose Tools | Templates.
    }  
}
