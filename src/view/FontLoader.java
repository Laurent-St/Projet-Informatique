/*---------------------------
 * CETTE CLASSE GERE L'IMPORTATION DE LA POLICE POUR LE JEU.
 * 	-> Appeler FontLoader.loadFontGame() au tout début
 * 	-> Appeler FontLoader.getFont() pour utiliser la police par la suite
 */

package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
	
	private static String fontURL = "view/ARCADE_N.TTF";
	private static String fontName = "Arcade Normal";
	private static int defaultSize = 16;
	private static int defaultStyle = Font.PLAIN;

	public static void loadGameFont() {
		InputStream is = FontLoader.class.getClassLoader().getResourceAsStream(fontURL);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Font getFont() {
		Font f = new Font(fontName, defaultStyle, defaultSize);
		return(f);
	}
	
	public static Font getFont(int sz) {
		Font f = new Font(fontName, defaultStyle, sz);
		return(f);
	}

}
