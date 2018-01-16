
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public Boolean twoOccurrences(String stringa, String stringb) {
       //This method returns true if stringa appears at least twice in stringb, otherwise it returns false. For example, the call twoOccurrences(“by”, “A story by Abby Long”) returns true as there are two occurrences of “by”, the call twoOccurrences(“a”, “banana”) returns true as there are three occurrences of “a” so “a” occurs at least twice, and the call twoOccurrences(“atg”, “ctgtatgta”) returns false as there is only one occurence of “atg”.
       int i1 = stringb.indexOf(stringa);
       if (i1 > -1) {
           int i2 = stringb.indexOf(stringa, i1 + stringa.length());
           if (i2 > -1) {
               return true;
           }
       }
       return false;
    }

    public String lastPart(String stringa, String stringb) {
        // This method finds the first occurrence of stringa in stringb,
        // and returns the part of stringb that follows stringa.
       int i1 = stringb.indexOf(stringa);
       if (i1 > -1) {
           return stringb.substring(i1 + stringa.length());
        }

        // If stringa does not occur in stringb, then return stringb.
        return stringb;
    }
    
    public void testing() {
        // the call twoOccurrences(“by”, “A story by Abby Long”) 
        // returns true as there are two occurrences of “by”,
        System.out.println("by," + "A story by Abby Long");
        Boolean t1 = twoOccurrences("by", "A story by Abby Long");
        System.out.println("t1 = " + t1);

        //the call twoOccurrences(“a”, “banana”) returns true 
        // as there are three occurrences of “a” so “a” occurs at least twice,
        System.out.println("a, " + "banana");
        Boolean t2 = twoOccurrences("a", "banana");
        System.out.println("t2 = " + t2);
        
        // and the call twoOccurrences(“atg”, “ctgtatgta”) 
        // returns false as there is only one occurence of “atg”.
        System.out.println("atg, " + "ctgtatgta");
        Boolean t3 = twoOccurrences("atg", "ctgtatgta");
        System.out.println("t3 = " + t3);
        
        // For example, the call lastPart(“an”, “banana”)
        // returns the string “ana”, 
        // the part of the string after the first “an”.
        System.out.println("an, " + "banana");
        String t4 = lastPart("an", "banana");
        System.out.println("t4 = " + t4);
        
        // The call lastPart(“zoo”, “forest”)
        // returns the string “forest”
        // since “zoo” does not appear in that word.
        System.out.println("zoo, " + "forest");
        String t5 = lastPart("zoo", "forest");
        System.out.println("t5 = " + t5);
    }
    

}
