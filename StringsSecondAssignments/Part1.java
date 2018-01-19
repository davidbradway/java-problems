
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
}
