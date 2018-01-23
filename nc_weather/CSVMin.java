/**
 * Find the temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with coldestSoFar as nothing
        CSVRecord coldestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        //The coldestSoFar is the answer
        return coldestSoFar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }
    
    public void testColdestHourInFile () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String coldestFileName = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            //System.out.println(f.getName());
            
            FileResource fr = new FileResource(f);
            
            // Note that after determining the filename,
            // you could call the method coldestHourInFile to determine
            // the coldest temperature on that day.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            
            if (coldestFileName == null) {
                coldestFileName = f.getName();
            }
            
            // compare result from this day to coldest day yet
            if (Double.parseDouble(currentRow.get("TemperatureF")) < Double.parseDouble(coldestSoFar.get("TemperatureF"))) {
                //If so update coldestSoFar and coldestFileNames accordingly
                coldestSoFar = currentRow;
                coldestFileName = f.getName();
            }
        }
        
        // This method should return a string that is the name of the file 
        // from selected files that has the coldest temperature. 
        return coldestFileName;
    }
    
    public void testFileWithColdestTemperature() {
        // When fileWithColdestTemperature runs
        // and selects the files for January 1–3 in 2014,
        // the method should print out
        // Coldest day was in file weather-2014-01-03.csv
        // Coldest temperature on that day was 21.9
        // All the Temperatures on the coldest day were: blah...
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in the file " + coldestFileName);
        FileResource fr = new FileResource("data/2013/" + coldestFileName);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + Double.parseDouble(coldest.get("TemperatureF")));
        System.out.println("All the Temperatures on the coldest day were: ");
        /* Also note that the header for the time is either TimeEST or TimeEDT,
         * depending on the time of year. You will instead use the DateUTC field 
         * at the right end of the data file to get both the date and time of a 
         * temperature reading.*/
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord currentRow : parser) {
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }
    }
    
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        //start with coldestSoFar as nothing
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getLowerOfTwo(currentRow, lowestSoFar);
        }
        //returns the CSVRecord that has the lowest humidity.
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get lowest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            lowestSoFar = getLowerOfTwo(currentRow, lowestSoFar);
        }
        //The lowestSoFar is the answer
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int count = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum += currentTemp;
            count++;
        }
        if (count == 0) {
            return 0;
        }
        else {
            return sum / count;
        }
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int count = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            String currentHum_str = currentRow.get("Humidity");
            // Note that sometimes there is not a number in the
            // Humidity column but instead there is the string “N/A”.
            // This only happens very rarely.
            // You should check to make sure the value you get is not “N/A”
            // before converting it to a number.
            if (currentHum_str.contains("N/A")) {
                continue;
            }
            double currentHum = Double.parseDouble(currentHum_str);

            //Check if currentHum is higher than value
            if (currentHum > value) {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                sum += currentTemp;
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        else {
            return sum / count;
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (average == 0) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average Temp when high Humidity is " + average);
        }
        /* selects the file for January 20, 2014, the method should print out
         * No temperatures with that humidity */
        /* If you run the method checking for humidity greater than or equal to 80
         * and select the file March 20, 2014, a wetter day, the method should print out
         * Average Temp when high Humidity is 41.78666666666667 */
    }    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        
        System.out.println("Average temperature in file is " +
                   average);
    }
    
    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest humidity was " + csv.get("Humidity") +
                   " at " + csv.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        // print the lowest humidity AND the time the lowest humidity occurred.
        // Be sure to test this method on two files so you can
        // check if it is working correctly.
        // If you run this program and select the files for
        // January 19, 2014 and January 20, 2014, you should get
        // Lowest Humidity was 24 at 2014-01-20 19:51:00
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") +
                   " at " + csv.get("DateUTC"));
    }

    public CSVRecord getLowerOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If coldestSoFar is nothing
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        //Otherwise
        else {
            String currentHum_str = currentRow.get("Humidity");
            String lowestHum_str = lowestSoFar.get("Humidity");
            // Note that sometimes there is not a number in the
            // Humidity column but instead there is the string “N/A”.
            // This only happens very rarely.
            // You should check to make sure the value you get is not “N/A”
            // before converting it to a number.
            if (lowestHum_str.contains("N/A")) {
                return currentRow;
            }
            if (currentHum_str.contains("N/A")) {
                return lowestSoFar;
            }

            double currentHum = Double.parseDouble(currentHum_str);
            double lowestHum = Double.parseDouble(lowestHum_str);

            //Check if currentRow’s is lower
            if (currentHum < lowestHum) {
                //If so update largestSoFar to currentRow
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord getColdestOfTwo (CSVRecord currentRow, CSVRecord coldestSoFar) {
        //If coldestSoFar is nothing
        if (coldestSoFar == null) {
            coldestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            
            // NOTE: Sometimes there was not a valid reading 
            // at a specific hour, so the temperature field says -9999. 
            if (currentTemp < -9998) {
                //If so ignore these bogus temperature values when 
                // calculating the lowest temperature.
                return coldestSoFar;
            }

            //Check if currentRow’s temperature < coldestSoFar’s
            if (currentTemp < coldestTemp) {
                //If so update largestSoFar to currentRow
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }

    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
}
