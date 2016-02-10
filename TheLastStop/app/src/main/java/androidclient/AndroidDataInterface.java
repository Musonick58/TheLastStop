package androidclient;

import java.util.List;
import java.io.Serializable;
/**
 * Created by nichi on 10/02/2016.
 */
public interface AndroidDataInterface extends Serializable{

        public String getNameObject();
        public List<String> getDataAsList();
        public void addData(String dato);
        public void setData(String s, int index);
}
