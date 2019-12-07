package Final;

import java.beans.PropertyEditorSupport;

public class MessageEditor extends PropertyEditorSupport {
	private String msg;
	
	public MessageEditor() {
		msg = "";
	}
	
	public String[] getTags() {
		return null;
	}
	
	public void setAsText(String s) {
		msg = s;
	}
	
	public String getAsText() {
		return msg;
	}
}
