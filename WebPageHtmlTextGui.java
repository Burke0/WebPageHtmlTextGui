import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
/*
 * GUI that lets a user input a domain and click go. 
 * The Html text from that web address is added to a default list model and displayed.
 */

public class WebPageHtmlTextGui extends JPanel {

	JList<String> 				list;
	DefaultListModel<String> 	model;
	
	JTextField 	urlTextField;
	JLabel 		urlLabel;
	JButton 	goButton;
	JScrollPane pane;

	public WebPageHtmlTextGui() {
		model= 			new DefaultListModel<String>();
		
		UrlHandler urlHandler= 	new UrlHandler();
	    list=			new JList<String>(model);
	    pane=			new JScrollPane(list);
	    urlLabel=		new JLabel("Enter URL");
	    urlTextField=	new JTextField();
	    urlTextField.setText("https://www.google.com/");
	    goButton=		new JButton("GO!");
	    goButton.addActionListener(urlHandler);
	    setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    pane.setPreferredSize(new Dimension(1000,600));
	    add(pane);
	    add(urlLabel);
	    add(urlTextField);
	    add(goButton);
	 
	   
	}


	private class UrlHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			URL             url;
			BufferedReader  webPageReader;
			String          str;

			model.removeAllElements();

			try
			    {

			    url = new URL(urlTextField.getText());

			    webPageReader = new BufferedReader(
			                           new InputStreamReader(url.openStream()));

			    str = webPageReader.readLine();  // prime read
			    while (str != null)
			        {
			        model.addElement(str);
			        str = webPageReader.readLine(); // follow-up read
			        }
			    }

			catch (MalformedURLException  mue)
			    {
			    System.out.println("Malformed URL!");
			    JOptionPane.showMessageDialog(null, "Malformed Url: check spelling and try again");
			    //System.exit(1);
			    }
			catch (IOException  ioe)
			    {
				JOptionPane.showMessageDialog(null,"IOException creating streams!");
			    ioe.printStackTrace();
			    //System.exit(1);
			    }
			
		}
		
		

	}


	public static void main(String[] args) {
	JFrame frame=new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new WebPageHtmlTextGui());
    frame.setSize(1000, 700);
    frame.setVisible(true);
    
   
  
	
}}