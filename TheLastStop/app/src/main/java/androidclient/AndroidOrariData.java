package androidclient;

import java.util.*;

/**
 * Created by nichi on 10/02/2016.
 */
public class AndroidOrariData implements AndroidDataInterface {

    final String objectName="Orari";

    List<String> data;

    public AndroidOrariData(){
        // this.objectName=name;
        this.data=new ArrayList<>();
    }


    @Override
    public String getNameObject() {
        return "orari"; //To change body of generated methods, choose Tools | Templates.
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
