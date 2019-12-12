package Final;

import java.beans.PropertyEditorSupport;

public class FiftyToThreeHundredEditor extends PropertyEditorSupport {
	
	private int propertyValue;

    public FiftyToThreeHundredEditor () {
    	
    }

    public String[] getTags() {

       return null;

    }

   

    public void setAsText(String value) {              

       int val = new Integer(value).intValue();

       if(val < 50 || val > 300) {
           System.out.println(val);
           throw new IllegalArgumentException(value);
       }
       propertyValue = val;

    }

   

    public String getAsText() {

       return new Integer(propertyValue).toString();

    }

}
