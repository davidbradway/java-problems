
/**
 * Write a description of Part2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        // Test if the first letter in the dna string is uppercase
        int makeUpper;
        if (dna.substring(1,2).equals(dna.substring(1,2).toUpperCase())) {
            // String is uppercase
            makeUpper = 1;
        }
        else {
            // String is lowercase
            makeUpper = 0;
            // make the codons lowercase so they are found
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }

        //Finds the index position of the start codon “ATG”.
        int start_codon_index = dna.indexOf(startCodon);
        //If there is no “ATG”, return the empty string.
        if (start_codon_index == -1) {
            return "";
        }
        // Finds the index position of the first stop codon “TAA”
        // appearing after the “ATG” that was found.
        int stop_codon_index = dna.indexOf(stopCodon, start_codon_index + startCodon.length());
        // If there is no such “TAA”, return the empty string.
        if (stop_codon_index == -1) {
            return "";
        }
        // If the length of the substring between the “ATG” and “TAA”
        // is a multiple of 3, then return the substring that starts with
        // that “ATG” and ends with that “TAA”.
        if ((stop_codon_index - start_codon_index) % 3 == 0) {
            String result = dna.substring(start_codon_index, stop_codon_index + startCodon.length());
            if (makeUpper == 1) {
                return result.toUpperCase();
            }
            else {
                return result.toLowerCase();
            }
        }
        return "";
    }

    public void testSimpleGene() {
        // Create five DNA strings.
        // The strings should have specific test cases, such as:
        // DNA with no “ATG”,
        String dna1 = "GGGGGGGATAA";
        // DNA with no “TAA”,
        String dna2 = "GGGGGGGATGAA";
        // DNA with no “ATG” or “TAA”,
        String dna3 = "GGGGGGGTGAA";
        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene),
        String dna4 = "ATGGGGGGGTAA";
        // and DNA with ATG, TAA and the substring between them is not a multiple of 3.
        String dna5 = "ATGAGGGGGGTAA";

        String dna11 = "gggggggataa";
        // DNA with no “TAA”,
        String dna12 = "gggggggatgaa";
        // DNA with no “ATG” or “TAA”,
        String dna13 = "gggggggtgaa";
        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene),
        String dna14 = "atgggggggtaa";
        // and DNA with ATG, TAA and the substring between them is not a multiple of 3.
        String dna15 = "atgaggggggtaa";

        // Practice Quiz
        String dna16 = "AAATGCCCTAACTAGATTAAGAAACC";


        // For each DNA string you should:
        // Print the DNA string.
        System.out.println("dna1 = " + dna1);
        // See if there is a gene by calling findSimpleGene
        // with this string as the parameter.
        String gene1 = findSimpleGene(dna1, "ATG", "TAA");
        // If a gene exists following our algorithm above, then print the gene,
        // otherwise print the empty string.
        System.out.println("gene1 = " + gene1);

        System.out.println("dna2 = " + dna2);
        String gene2 = findSimpleGene(dna2, "ATG", "TAA");
        System.out.println("gene2 = " + gene2);

        System.out.println("dna3 = " + dna3);
        String gene3 = findSimpleGene(dna3, "ATG", "TAA");
        System.out.println("gene3 = " + gene3);

        System.out.println("dna4 = " + dna4);
        String gene4 = findSimpleGene(dna4, "ATG", "TAA");
        System.out.println("gene4 = " + gene4);

        System.out.println("dna5 = " + dna5);
        String gene5 = findSimpleGene(dna5, "ATG", "TAA");
        System.out.println("gene5 = " + gene5);

        // For each DNA string you should:
        // Print the DNA string.
        System.out.println("dna11 = " + dna11);
        // See if there is a gene by calling findSimpleGene
        // with this string as the parameter.
        String gene11 = findSimpleGene(dna11, "ATG", "TAA");
        // If a gene exists following our algorithm above, then print the gene,
        // otherwise print the empty string.
        System.out.println("gene11 = " + gene11);

        System.out.println("dna12 = " + dna12);
        String gene12 = findSimpleGene(dna12, "ATG", "TAA");
        System.out.println("gene12 = " + gene12);

        System.out.println("dna13 = " + dna13);
        String gene13 = findSimpleGene(dna13, "ATG", "TAA");
        System.out.println("gene13 = " + gene13);

        System.out.println("dna14 = " + dna14);
        String gene14 = findSimpleGene(dna14, "ATG", "TAA");
        System.out.println("gene14 = " + gene14);

        System.out.println("dna15 = " + dna15);
        String gene15 = findSimpleGene(dna15, "ATG", "TAA");
        System.out.println("gene15 = " + gene15);

        System.out.println("dna16 = " + dna16);
        String gene16 = findSimpleGene(dna16, "ATG", "TAA");
        System.out.println("gene16 = " + gene16);
    }
}
