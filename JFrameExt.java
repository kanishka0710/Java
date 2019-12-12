package Final;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.management.IntrospectionException;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class JFrameExt extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel[] jlbPropNames = new JLabel[10];
	private JTextField[] jtfPropValues = new JTextField[10];
	private JComboBox[] jcboPropValues = new JComboBox[10];
	private PropertyEditor[] pe = new PropertyEditor[10];
	private JPanel jpPropValues = new JPanel();
	
	private JPanel targetBeanObject = null;
	private Class classObject = null;
	private PropertyDescriptor[] pd = null;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameExt frame = new JFrameExt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameExt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel jpLeft = new JPanel();
		jpLeft.setBackground(Color.ORANGE);
		contentPane.add(jpLeft);
		
		JPanel jpRight = new JPanel();
		contentPane.add(jpRight);
		jpRight.setLayout(new BorderLayout(0, 0));
		
		JPanel jpController = new JPanel();
		jpController.setBackground(Color.RED);
		jpRight.add(jpController, BorderLayout.NORTH);
		
		JPanel jpInspector = new JPanel();
		jpRight.add(jpInspector, BorderLayout.CENTER);
		jpInspector.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel jpPropNames = new JPanel();
		jpPropNames.setBackground(Color.YELLOW);
		jpInspector.add(jpPropNames);
		jpPropNames.setLayout(new GridLayout(10, 1, 0, 0));
		
		jpPropValues.setBackground(Color.BLACK);
		jpInspector.add(jpPropValues);
		jpPropValues.setLayout(new GridLayout(10, 1, 0, 0));
		String[] list = {"", "Final.Rect", "Final.Circ", "Final.Ticker"};
		comboBox = new JComboBox(list);
		comboBox.setEditable(true);

		jpController.add(comboBox);
		
		
		comboBox.addActionListener(new ActionListener() {

			@SuppressWarnings({ "deprecation" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String className = (String) comboBox.getSelectedItem();
				
				if(className.equals("")) {
					return;
				}
				
				try {
					targetBeanObject = (JPanel)
					Beans.instantiate(null, className);
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				if (!(targetBeanObject instanceof JPanel)) {
					return;
				} 
				
				contentPane.remove(0);
				contentPane.add((JPanel)targetBeanObject, 0);
				contentPane.validate();

				Class classObject;
				BeanInfo bi = null;
				try {
					classObject = Class.forName(className);
					bi = Introspector.getBeanInfo(classObject, JPanel.class);
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (java.beans.IntrospectionException ex) {
					ex.printStackTrace();
				}
				
				pd = bi.getPropertyDescriptors();
				
				jpPropValues.removeAll();
				jpPropValues.validate();
				String propName;
				for(int i = 0; i < pd.length; i++) {

					propName = pd[i].getName();
					jlbPropNames[i].setText(propName);
					Class customEditorClass = pd[i].getPropertyEditorClass();
		            PropertyEditor customEditor=null;
		            if(customEditorClass!=null) {
		            	try {                
		            		customEditor = (PropertyEditor)customEditorClass.newInstance();
		                    pe[i]=customEditor;

		                } catch (IllegalAccessException ex) {
		                    ex.printStackTrace();               
		                } catch (InstantiationException ex) {
		                    ex.printStackTrace();
		                } 
		            }		            
		            String[] tags = pe[i].getTags();

		            if(tags == null) {
		            	//text field
		            	jtfPropValues[i] = new JTextField();
		            	jtfPropValues[i].addActionListener(JFrameExt.this);
		            	jpPropValues.add(jtfPropValues[i]);
		            } else {
		            	//combo box
		            	jcboPropValues[i] = new JComboBox(tags);
		            	jcboPropValues[i].addActionListener(JFrameExt.this);
		            	jpPropValues.add(jcboPropValues[i]);
		            }
		            
		            Method mget = pd[i].getReadMethod();
					Object robj = null;
					try {
						robj = mget.invoke(targetBeanObject, null);
					} catch (IllegalAccessException ex) {
						ex.printStackTrace();
					} catch (IllegalArgumentException ex) {
						ex.printStackTrace();
					} catch(InvocationTargetException ex) {
						ex.printStackTrace();
					}
					String sobj = robj.toString();
					
					try {
						pe[i].setAsText(sobj);
					} catch (Exception ex) {
						ex.printStackTrace();
						return;
					}
					
					if(tags == null) {
						jtfPropValues[i].setText(sobj);
					} else {
						jcboPropValues[i].setSelectedItem(sobj);
					}
					jpPropValues.validate();
				}
				

				
			}
		});
		
		
		for(int i = 0; i < 10; i++) {
			jlbPropNames[i] = new JLabel("");
			jpPropNames.add(jlbPropNames[i]);
			
		}
		
		/**for(int i = 0; i < jtfPropValues.length; i++) {
			
			jtfPropValues[i] = new JTextField(10);
			jpPropValues.add(jtfPropValues[i]);
			jtfPropValues[i].addActionListener(this);	
		}**/
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int i;
		String propName = "", propValue = "";
		
		for(i= 0; i < jtfPropValues.length; i++) {
			if(e.getSource() == jtfPropValues[i]) {
				propValue = jtfPropValues[i].getText();
				propName = "jtf";
				break;
			} else if (e.getSource() == jcboPropValues[i]) {
			    propValue = (String)jcboPropValues[i].getSelectedItem();
			    propName = "jcbo";
				break;
			}
		}
		try {
			pe[i].setAsText(propValue);
		} catch (IllegalArgumentException ex) {
			if (propName.equals("jtf")) {
			    System.out.println(pe[i].getAsText());
				jtfPropValues[i].setText(pe[i].getAsText());
			} else {
				jcboPropValues[i].setSelectedItem(pe[i].getAsText());
			}
		}
		propName  = pd[i].getName();
		Class proptype = pd[i].getPropertyType();
		String propTypeName = proptype.getName();
		
		Object[] params = new Object[1];
		
		if(propTypeName.equals("int")){
			params[0] = Integer.parseInt(propValue);
		} else if(propTypeName.equals("double")){
			params[0] = Double.parseDouble(propValue);
		} else if(propTypeName.equals("boolean")){
			params[0] = new Boolean(propValue);
		} else if(propTypeName.equals("java.lang.String")){
			params[0] = propValue;
		}
		
		Method mset = pd[i].getWriteMethod();
		
		try {
			mset.invoke(targetBeanObject, params);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		} catch(InvocationTargetException ex) {
			ex.printStackTrace();
		}
		
		
	}

}
