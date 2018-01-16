import edu.duke.*;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void findWebLinks() {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        
        for (String word : ur.words()) {
            // process each word in turn
            // For each word, check to see if “youtube.com” is in it. 
            String word_lower = word.toLowerCase();
            
            int yt_index = word_lower.indexOf("youtube.com");
            if (yt_index != -1) {
                // If it is, find the double quote to the left and right 
                // of the occurrence of “youtube.com” to identify 
                // the beginning and end of the URL.
                
                // Note, the double quotation mark is a special character in Java.
                // To look for a double quote, look for (\”), since the backslash (\)
                // character indicates we want the literal quotation marks (“) and not 
                // the Java character. The string you search for would be written “\””
                // for one double quotation mark.
                int dbl_qt_l_index = word.lastIndexOf("\"",yt_index);
                if (dbl_qt_l_index == -1) {
                    return;
                }

                int dbl_qt_r_index = word.indexOf("\"", yt_index + 11);
                if (dbl_qt_r_index == -1) {
                    return;
                }
                
                System.out.println("found URL = " + word.substring(dbl_qt_l_index + 1, dbl_qt_r_index));
        }
            
        }
    }
}
