import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        // start searching for a stop codon at the end of the current start codon
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);

        // keep searching while you are finding more stop codons
        while (stopIndex != -1) {
            // stopIndex was found, is it a valid gene?
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }
            // not valid, update and search again
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }

        // no stop valid codon found, return length
        return dna.length();
    }

    public void testFindStopCodon() {
        // test several examples and prints out the results
        // to check if findStopCodon is working correctly.
        // Think about what types of examples you should check.
        // For example, you may want to check some strings of DNA
        // that have genes and some that do not.
        // What other examples should you check?
        String dna = "ATGAAATAA";
        int startIndex = 0;
        String stopCodon = "TAA";
        System.out.println("dna = " + dna);
        System.out.println("stopCodon = " + stopCodon);
        int index = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("index = " + index);
       
        dna = "ATGAAATAA";
        startIndex = 0;
        stopCodon = "TGA";
        System.out.println("dna = " + dna);
        System.out.println("stopCodon = " + stopCodon);
        index = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("index = " + index);

        dna = "ATGAATGA";
        startIndex = 0;
        stopCodon = "TGA";
        System.out.println("dna = " + dna);
        System.out.println("stopCodon = " + stopCodon);
        index = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("index = " + index);
        
        dna = "ATGAATGAATAA";
        startIndex = 0;
        stopCodon = "TAA";
        System.out.println("dna = " + dna);
        System.out.println("stopCodon = " + stopCodon);
        index = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("index = " + index);
    }
    
    public String findGene(String dna) {
        // Find the index of the first occurrence of the start codon “ATG”.
        int startIndex = dna.indexOf("ATG");

        while(startIndex != -1) {
            // Find the index of the first occurrence of the stop codon “TAA”
            // after the first occurrence of “ATG” that is a multiple of three 
            // away from the “ATG”. Hint: call findStopCodon.        
            int indexTAA = findStopCodon(dna, startIndex, "TAA");
            
            // Find the index of the first occurrence of the stop codon “TAG”
            // after the first occurrence of “ATG” that is a multiple of three
            // away from the “ATG”. 
            int indexTAG = findStopCodon(dna, startIndex, "TAG");
            
            // Find the index of the first occurrence of the stop codon “TGA” 
            // after the first occurrence of “ATG” that is a multiple of three
            // away from the “ATG”.
            int indexTGA = findStopCodon(dna, startIndex, "TGA");
                
            int minIndex = Math.min(indexTAA, Math.min(indexTAG, indexTGA));
            // If there is no valid stop codon and therefore no gene, return the empty string.
            if (minIndex != dna.length()) {
                // Return the gene formed from the “ATG” and the closest stop codon
                // that is a multiple of three away.
                return dna.substring(startIndex, minIndex + 3);
            }
            
            // find another start Index if there is more than one
            startIndex = dna.indexOf("ATG", startIndex + 1);            
        }
                
        // If there is no valid gene, return the empty string.        
        return "";
    }

    public void testFindGene() {
        System.out.println("1. DNA with no “ATG”, to check if findStopCodon is working correctly.");
        String dna = "AATTAAATAA";
        System.out.println("dna = " + dna);
        String gene = findGene(dna);
        System.out.println("gene = " + gene);
       
        System.out.println("2. DNA with “ATG” and one valid stop codon, ");
        dna = "ATGAAATAA";
        System.out.println("dna = " + dna);
        gene = findGene(dna);
        System.out.println("gene = " + gene);

        System.out.println("3. DNA with “ATG” and multiple valid stop codons,");
        dna = "ATGAAATAATAGTGA";
        System.out.println("dna = " + dna);
        gene = findGene(dna);
        System.out.println("gene = " + gene);
        
        System.out.println("4. DNA with “ATG” and no valid stop codons.");
        dna = "ATGAATAATAGTGA";
        System.out.println("dna = " + dna);
        gene = findGene(dna);
        System.out.println("gene = " + gene);
        
        System.out.println("5. DNA with a second “ATG”: should return ATGTTTTAA.");
        dna = "ATGAATAATAGTGATGTTTTAA";
        System.out.println("dna = " + dna);
        gene = findGene(dna);
        System.out.println("gene = " + gene);
    }
    
    public void printAllGenes(String dna) {
        // In this method you should repeatedly find genes
        // Hint: remember you learned a while(true) loop and break.
        System.out.println("dna = " + dna);
            
        while (true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            // and print each one until there are no more genes
            System.out.println("gene = " + gene);
            // locate the found gene in the dna sequence
            int indexGene = dna.indexOf(gene);
            // overwrite dna with only the part that remains
            dna = dna.substring(indexGene + gene.length());
        }
    }

    public StorageResource getAllGenes(String dna) {
        // In this method you should repeatedly find genes
        // Hint: remember you learned a while(true) loop and break.
        StorageResource sr = new StorageResource();
        
        while (true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            // and add each one until there are no more genes
            sr.add(gene);
            // locate the found gene in the dna sequence
            int indexGene = dna.indexOf(gene);
            // overwrite dna with only the part that remains
            dna = dna.substring(indexGene + gene.length());
        }
        return sr;
    }

    public void testPrintAllGenes() {
        System.out.println("1. two genes");
        String dna = "ATGA1ATAAATGA2ATAAAATTAAATAA";
        printAllGenes(dna);

        System.out.println("2. two genes");
        dna = "ATGA1ATAATAGTGAATGA2ATAA";
        printAllGenes(dna);

        System.out.println("3. three genes");
        dna = "ATGAATAATAGTGATGT1TTAAATGT2TTAAATGT3TTAA";
        printAllGenes(dna);
    }

    public void testGetAllGenes() {
        System.out.println("1. two genes");
        String dna = "ATGA1ATAAATGA2ATAAAATTAAATAA";
        StorageResource sr = getAllGenes(dna);
        for (String s : sr.data()) {
            System.out.println(s);
        }

        System.out.println("2. two genes");
        dna = "ATGA1ATAATAGTGAATGA2ATAA";
        sr = getAllGenes(dna);
        for (String s : sr.data()) {
            System.out.println(s);
        }

        System.out.println("3. three genes");
        dna = "ATGAATAATAGTGATGT1TTAAATGT2TTAAATGT3TTAA";
        sr = getAllGenes(dna);
        for (String s : sr.data()) {
            System.out.println(s);
        }
    }
    
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
    
    public double cgRatio(String dna) {
        int cs = howMany("C", dna);
        int gs = howMany("G", dna);
        
        return ((double) cs + gs) / dna.length();
    }
    
    public void testCgRatio() {
        double cgr = cgRatio("ATGCCATAG");
        System.out.println(cgr);
        System.out.println("above should be 4/9 or .4444444");
    }
    
    public int countCTG(String dna) {
        int ctgs = howMany("CTG", dna);
        return ctgs;
    }
    
    public void testCountCTG() {
        String dna = "CTGCTGCTG";
        System.out.println(countCTG(dna));
        System.out.println("above should be 3");
    }
    
    public void processGenes(StorageResource sr){
        int count = 0;
        System.out.println("Strings in sr that are longer than 60 characters:");
        for (String s : sr.data()){
            if (s.length() > 60) {
                 System.out.println(s);
                 count++;
            }
        }
        System.out.println("total: " + count);
        
        count = 0;
        System.out.println("CG Ration greater than .35:");
        for (String s : sr.data()){    
            if (cgRatio(s) > 0.35) {
                 System.out.println(s);
                 count++;
            }
        }
        System.out.println("total: " + count);

        int longest = 0;
        for (String s : sr.data()){    
            if (s.length() > longest) {
                longest = s.length();
            }
        }
        System.out.println("longest gene is length: " + longest);
    }
    
    public void testProcessGenes() {
        // Think of five DNA strings to use as test cases.
        // These should include:
        System.out.println("one DNA string that has some genes longer than 9 characters,");
        String dna = "ATGA1AA1ATAAATGA2ATAAAATTAAATAA";
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        
        System.out.println("one DNA string that has no genes longer than 9 characters,");
        dna = "ATGA1ATAAATGA2ATAAAATTAAATAA";
        sr = getAllGenes(dna);
        processGenes(sr);
        
        System.out.println("one DNA string that has some genes whose C-G-ratio is higher than 0.35,");
        dna = "ATGCCCGGGCCCGGGA1ATAA";
        sr = getAllGenes(dna);
        processGenes(sr);
        
        System.out.println("and one DNA string that has some genes whose C-G-ratio is lower than 0.35.");
        dna = "ATGA1ATAA";
        sr = getAllGenes(dna);
        processGenes(sr);

        System.out.println("and one DNA string with no genes");
        dna = "ATGAATAA";
        sr = getAllGenes(dna);
        processGenes(sr);

        System.out.println("with real DNA.");
        FileResource fr = new FileResource("brca1line.fa");
        dna = fr.asString().toUpperCase();
        sr = getAllGenes(dna);
        System.out.println("number of genes " + sr.size());
        processGenes(sr);
    }
}
