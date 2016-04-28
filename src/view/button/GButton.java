/*----------------------------------
 * Bouton styl� homemade rempla�ant le classique JButton
 * A utiliser en faisant new GButton(String label, Dimension dimensions)
 */

package view.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.FontLoader;

@SuppressWarnings("serial")
public class GButton extends JPanel {
	
	private JLabel label;
	private Dimension dimensions;
	private GButtonGraphics buttonImage;
	
	public GButton(String text, int textSize, Dimension dim) {
		super();
		
		initLabel(text, textSize);
		
		this.dimensions = dim;
		buttonImage = new GButtonGraphics(this);
		setDimensions(dim);		
		
		this.setLayout(null);
		this.add(label);
		this.add(buttonImage);
		this.setVisible(true);
	}

	public void addMouseListener(MouseListener e) {
		buttonImage.addMouseListener(e);
	}
	
	public void initLabel(String text, int textSize) {
		label = new JLabel();
		label.setFont(FontLoader.getFont(textSize));
		label.setText(text);
		label.setForeground(Color.BLUE);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void setDimensions(Dimension dim) {
		this.dimensions = dim;
		this.setSize(dimensions);
		buttonImage.updateImageSize();
		buttonImage.setBounds(0,0,(int)dimensions.getWidth(), (int)dimensions.getHeight());
		label.setBounds(0, 3, (int)dimensions.getWidth(), (int)dimensions.getHeight()-3);
	}
	
	public Dimension getDimensions() {
		return(this.dimensions);
	}
	
	public void addTo(JPanel panel) {
		panel.add(this);
		buttonImage.updateImageSize();
	}
}
