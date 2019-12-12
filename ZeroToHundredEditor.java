package Final;

import java.beans.PropertyEditorSupport;



public class ZeroToHundredEditor extends PropertyEditorSupport {

    private int propertyValue=0;

    public ZeroToHundredEditor () {
      
    }

    public String[] getTags() {

       return null;

    }

   

    public void setAsText(String value) {              

       @SuppressWarnings("deprecation")
       int val = new Integer(value).intValue();

       if(val < 0 || val > 100) {

           throw new IllegalArgumentException(value);
       } else {
           propertyValue = val;
       }


    }

   

    public String getAsText() {

       return new Integer(propertyValue).toString();

    }

}
