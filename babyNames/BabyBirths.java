/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numBoysNames = 0;
        int numGirlsNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                numBoysNames++;
            }
            else {
                totalGirls += numBorn;
                numGirlsNames++;
            }
        }
        int numNames = numBoysNames + numGirlsNames;
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("number of boys names = " + numBoysNames);
        System.out.println("number of girls names = " + numGirlsNames);
        System.out.println("total number of names = " + numNames);
    }

    public int getRank(int year, String name, String gender){
        
        int practice = 0;
        // Open the file for the chosen year
        FileResource fr;
        if (practice == 1) {
            fr = new FileResource("data/yob" + year +"short.csv");
        }
        else {
            fr = new FileResource("data/yob" + year +".csv");
        }
        
        int rank = 0;
        int firstOfChosenGender = -1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // rank (or line numbers) will start at 1 not 0
            rank++;
            if (firstOfChosenGender == -1 && rec.get(1).equals(gender)) {
                firstOfChosenGender = rank;
            }

            // If the current line contains the right name and gender
            if (firstOfChosenGender != -1 && rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                // this is on the line we want, return its rank
                return rank - firstOfChosenGender + 1;
            }
        }
        // This method returns the rank of the name
        // in the file for the given gender,
        // where rank 1 is the name with the largest number of births.
        // If the name is not in the file, then -1 is returned.
        return -1;
    }

    public String getName(int year, int rank, String gender){
        // This method returns the name of the person in the file
        // at this rank, for the given gender, where rank 1
        // is the name with the largest number of births.
        
        int practice = 0;
        // Open the file for the chosen year
        FileResource fr;
        if (practice == 1) {
            fr = new FileResource("data/yob" + year +"short.csv");
        }
        else {
            fr = new FileResource("data/yob" + year +".csv");
        }
        
        int linenum = 0;
        int firstOfChosenGender = -1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // rank (or line numbers) will start at 1 not 0
            linenum++;
            if (firstOfChosenGender == -1 && rec.get(1).equals(gender)) {
                firstOfChosenGender = linenum;
            }

            // If the current line contains the right rank and gender
            if (firstOfChosenGender != -1 && rank == linenum - firstOfChosenGender + 1 && rec.get(1).equals(gender)) {
                // this is on the line we want, return the name
                return rec.get(0);
            }
        }
        // If the rank does not exist in the file, then “NO NAME” is returned.
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        // return what name would have been named if
        // they were born in a different year,
        // based on the same popularity.
        // That is, you should determine the rank of name
        // in the year they were born,
        // and then print the name born in newYear
        // that is at the same rank and same gender.
        
        // NOTE NEED TO HARDCODE THE SHORT FILE NAME FOR THIS TO WORK
        int rank = getRank(year, name, gender);
        System.out.println(rank);
        
        // NOTE NEED TO HARDCODE THE SHORT FILE NAME FOR THIS TO WORK
        String newName = getName(newYear, rank, gender);
        System.out.println(newName);
        
        String pronoun;
        if (gender.equals("M")) {
            pronoun = "he";
        }
        else {
            pronoun = "she";
        }
        System.out.println(name + " born in " + year + " would be " + newName + " if " +
                           pronoun + " was born in " + newYear + ".");        
    }
    
    public int yearOfHighestRank(String name, String gender){
        // This method selects a range of files to process and returns an integer,
        // the year with the highest rank for the name and gender.
        // If the name and gender are not in any of the selected files,
        // it should return -1.
        
        int bestRank = -1;
        int yearOfBestRank = -1;
        
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            
            String filename = f.getName();
            //System.out.println(filename);
            
            int practice = 0;
            // Open the file for the chosen year
            int year;
            if (practice == 1) {
                year = Integer.parseInt(filename.substring(filename.indexOf("yob") + 3, filename.indexOf("short.csv")));
            }
            else {
                year = Integer.parseInt(filename.substring(filename.indexOf("yob") + 3, filename.indexOf(".csv")));
            }
            //System.out.println(year);
            int currentRank = getRank(year, name, gender);
            //System.out.println(currentRank);
            if (bestRank == -1 || (currentRank != -1 && currentRank < bestRank)) {
                bestRank = currentRank;
                yearOfBestRank = year;
            }
        }
        return yearOfBestRank;
    }

    public double getAverageRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        
        double totalRank = 0;
        int numfiles = 0;
        for (File f : dr.selectedFiles()) {
            numfiles++;
            /* find the average rank of the name and gender
             * over the selected files.
             * It should return -1.0 if the name is not ranked
             * in any of the selected files.*/
            
            String filename = f.getName();
            //System.out.println(filename);

            int practice = 0;
            // Open the file for the chosen year
            int year = 0;
            if (practice == 1) {
                year = Integer.parseInt(filename.substring(filename.indexOf("yob") + 3, filename.indexOf("short.csv")));
            }
            else {
                year = Integer.parseInt(filename.substring(filename.indexOf("yob") + 3, filename.indexOf(".csv")));
            }

            //System.out.println(year);
            int currentRank = getRank(year, name, gender);
            //System.out.println(currentRank);
            if (currentRank == -1) {
                // unranked, return -1
                return -1;
            }
            else {
                totalRank += currentRank;
            }
        }
        if (numfiles == 0) {
            return 0;
        }
        else {
            return totalRank / numfiles;
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirths = 0;
        
        int practice = 0;
        // Open the file for the chosen year
        FileResource fr;
        if (practice == 1) {
            fr = new FileResource("data/yob" + year +"short.csv");
        }
        else {
            fr = new FileResource("data/yob" + year +".csv");
        }
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                return totalBirths;
            }
            if (rec.get(1).equals(gender)) {
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        // never found the right name and gender
        return -1;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        /* For example, if getTotalBirthsRankedHigher accesses 
         * the "yob2012short.csv" file with name set to “Ethan”,
         * gender set to “M”, and year set to 2012,
         * then this method should return 15,
         * since Jacob has 8 births and Mason has 7 births,
         * and those are the only two ranked higher than Ethan.*/
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
        System.out.println("above should print 15");
        
        System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
        
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }

    
    public void testGetAverageRank() {
        /* For example calling getAverageRank
         * with name Mason and gender ‘M’
         * and selecting the three test files above
         * results in returning 3.0, as he is
         * rank 2 in the year 2012,
         * rank 4 in 2013 and
         * rank 3 in 2014.*/
        System.out.println(getAverageRank ("Mason", "M"));
        System.out.println("above should be 3.0");
        
        /* As another example, calling getAverageRank
         * with name Jacob and gender ‘M’
         * and selecting the three test files above
         * results in returning 2.66.*/
        System.out.println(getAverageRank ("Jacob", "M"));
        System.out.println("above should be 2.66");
        
        
        System.out.println(getAverageRank ("Susan", "F"));
        System.out.println(getAverageRank ("Robert", "M"));

    }
    
    public void testYearOfHighestRank(){
        // For example, calling yearOfHighestRank with name Mason
        // and gender ‘M’ and selecting the three test files above
        // results in returning the year 2012.
        // That is because Mason was ranked the 2nd most popular
        // name in 2012, ranked 4th in 2013 and ranked 3rd in 2014.
        // His highest ranking was in 2012.
        System.out.println(yearOfHighestRank("Mason","M"));
        
        System.out.println(yearOfHighestRank("Genevieve","F"));
        
        System.out.println(yearOfHighestRank("Mich","M"));
    }

    public void testWhatIsNameInYear() {
        // NOTE NEED TO HARDCODE THE SHORT FILE NAME FOR THIS TO WORK
        whatIsNameInYear("Isabella", 2012, 2014, "F");
        
        whatIsNameInYear("Susan", 1972, 2014, "F");
        
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }

    public void testGetRank () {
       // For example, in the file "yob2012short.csv",
       // given the name Mason,
       // the year 2012 and the gender ‘M’, 
       // the number returned is 2,
       // NOTE NEED TO HARDCODE THE SHORT FILE NAME FOR THIS TO WORK
       System.out.println(getRank(2012,"Mason","M"));
       System.out.println("above should print 2");

       // as Mason is the boys name with the second highest number of births. 
       // Given the name Mason, 
       // the year 2012 and the gender ‘F’, 
       // the number returned is -1 as Mason does not appear with an F in that file.
       // NOTE NEED TO HARDCODE THE SHORT FILE NAME FOR THIS TO WORK
       System.out.println(getRank(2012,"Mason","F"));
       System.out.println("above should print -1");
       
       System.out.println(getRank(1960,"Emily","F"));
       System.out.println("above is the Rank of the girls name Emily in 1960");

       System.out.println(getRank(1971,"Frank","M"));
       System.out.println("above is the Rank of the boys name Frank in 1971");
    }
    
    public void testGetName() {
       // For example, in the file "yob2012short.csv",
       // given the rank 2
       // the year 2012 and the gender ‘M’, 
       // the name returned is Mason,
       // NOTE NEED TO HARDCODE THE SHORT FILE NAME FOR THIS TO WORK
       System.out.println(getName(2012,2,"M"));
       System.out.println("above should print Mason");
       // as Mason is the boys name with the second highest number of births. 

       System.out.println(getName(1980, 350, "F"));
       System.out.println("above is 1980 rank 350 female");
       
       System.out.println(getName(1982, 450, "M"));
       System.out.println("above is 1982 rank 450 male");
       
    }
    
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);

        fr = new FileResource("data/yob1900.csv");
        totalBirths(fr);
        
        fr = new FileResource("data/yob1905.csv");
        totalBirths(fr);
}
}
