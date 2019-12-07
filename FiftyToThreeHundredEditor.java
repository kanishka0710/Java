package Final;

import java.beans.PropertyEditorSupport;

public class FiftyToThreeHundredEditor extends PropertyEditorSupport {
	private int propertyValue=0;

    public FiftyToThreeHundredEditor () {
      
    }

    public String[] getTags() {

       return null;

    }

   

    public void setAsText(String value) {              

       @SuppressWarnings("deprecation")
       int val = new Integer(value).intValue();

       if(val < 50 || val > 300) {

           throw new IllegalArgumentException(value);
       }

       propertyValue = val;

    }

   

    @SuppressWarnings("deprecation")
	public String getAsText() {

       return new Integer(propertyValue).toString();

    }

}
