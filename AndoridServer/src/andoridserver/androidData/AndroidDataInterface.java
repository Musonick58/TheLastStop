/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import java.util.*;

/**
 *
 * @author Nicola
 */
public interface AndroidDataInterface {
      
    String getNameObject();
    
    void setNameObject(String name);
    
    List<String> getDataAsList();
    
    void addData(String dato);
    
    void setData(String s, int index);
   
    
    
  
}
