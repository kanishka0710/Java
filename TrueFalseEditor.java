package Final;

import java.beans.PropertyEditorSupport;

public class TrueFalseEditor extends PropertyEditorSupport {
	private String trueFalseString;

	private String[] tags = { "yes", "no"};

	public TrueFalseEditor() {
	    trueFalseString="no";
	}

	public String[] getTags() {

		return (String[]) tags.clone();
	}

	public void setAsText(String value) {
		trueFalseString = value;
	}

	public String getAsText() {
		return trueFalseString;
	}
}
