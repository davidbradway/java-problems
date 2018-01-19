
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
       /*find how many times stringa appears in stringb,
        * where each occurrence of stringa must not overlap with 
        * another occurrence of it.*/

       // where we are searching from
       int index = 0;
       
       // counter for number of times seen
       int occurrences = 0;
       while (true) {
           index = stringb.indexOf(stringa, index);
           if (index == -1) {
               break;
           }
           occurrences++;
           index += stringa.length();
       }   
       return occurrences;
    }
    
    public void testHowMany(){
    /* Think carefully about what types of examples would be good to test 
     to make sure your method works correctly. */
     
     String stringa = "foo";
     String stringb = "foofoofofoo";
     System.out.println(howMany(stringa, stringb));
     System.out.println("above should be 3");

     System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
     System.out.println("above should be 3");
     System.out.println(howMany("AA", "ATAAAA"));
     System.out.println("above should be 2");
     System.out.println(howMany("GA", "ATAAAA"));
     System.out.println("above should be 0");
    }
}
