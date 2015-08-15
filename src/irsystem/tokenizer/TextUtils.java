/**
 * 
 */
package irsystem.tokenizer;
import java.text.Normalizer;


public class TextUtils {

    /**
     * Normalizes a String by removing all accents to original 127 US-ASCII
     * characters. This method handles German umlauts and "sharp-s" correctly
     * 
     * @param s
     *            The String to normalize
     * @return The normalized String
     * if your text is in unicode, you should use this instead:
	 * string = string.replaceAll("\\p{M}", "");
     */ 
    public String normalize(String s) {
        if (s == null)
            return null;

        String n = null;
        n = Normalizer.normalize(s, Normalizer.Form.NFD);
        n = n.replaceAll("[^\\p{ASCII}]", "");
        n = n.trim();
        n = n.toLowerCase();

        return n;
    } 
}
