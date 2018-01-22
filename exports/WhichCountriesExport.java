/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public String countryInfo(CSVParser parser, String country) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Country" column
            String country_col = record.get("Country");
             
            //Check if it contains country
            if (country_col.contains(country)) {
                return country_col + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND"; // if there is no information about the country
    }

    public String listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                //If so, write down the "Country" from that row
                System.out.println(record.get("Country"));
            }
        }
        return "NOT FOUND"; // if there is no information about the country
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int number = 0;
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportItem)) {
                number++;
            }
        }
        return number;
    }

    // amount is a String in the format of a dollar sign, 
    // followed by an integer number with a comma separator
    // every three digits from the right. 
    // An example of such a string might be “$400,000,000”.
    // This method prints the names of countries and 
    // their Value amount for all countries whose 
    // Value (dollars) string is longer than the amount string. 
    // You do not need to parse either string value as an integer,
    // just compare the lengths of the strings. 
    public void bigExporters(CSVParser parser, String amount) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String value = record.get("Value (dollars)");
            //Check if it contains exportOfInterest
            if (value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        // For example, using the file exports_small.csv 
        // and the country Germany, 
        // the program returns the string: Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000
        System.out.println(countryInfo(parser, "Germany"));

        // For example, using the file exports_small.csv, 
        // this method called with the items “gold” and “diamonds” 
        // would print the countries
        // Namibia and South Africa
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        // For example, using the file exports_small.csv,
        // this method called with the item “gold” would return 3.
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));

        // For example, if  is called with the file exports_small.csv
        // and amount with the string $999,999,999
        // then this method would print eight countries and their export values shown here:
        // Germany $1,547,000,000,000
        // Macedonia $3,421,000,000
        // Malawi $1,332,000,000
        // Malaysia $231,300,000,000
        // Namibia $4,597,000,000
        // Peru $36,430,000,000
        // South Africa $97,900,000,000
        // United States $1,610,000,000,000
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
        
        System.out.println("what is third country below");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "fish", "nuts");
        
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "sugar"));
        
        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        
        System.out.println("what is second country below");
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        


    }
}
