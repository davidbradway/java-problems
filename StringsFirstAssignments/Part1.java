
/**
 * Write a description of Part1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        //Finds the index position of the start codon “ATG”.
        String start_codon = "ATG";
        int start_codon_index = dna.indexOf(start_codon);
        //If there is no “ATG”, return the empty string.
        if (start_codon_index == -1) {
            return "";
        }
        // Finds the index position of the first stop codon “TAA”
        // appearing after the “ATG” that was found.
        String stop_codon = "TAA";
        int stop_codon_index = dna.indexOf(stop_codon, start_codon_index + start_codon.length());
        // If there is no such “TAA”, return the empty string.
        if (stop_codon_index == -1) {
            return "";
        }
        // If the length of the substring between the “ATG” and “TAA”
        // is a multiple of 3, then return the substring that starts with
        // that “ATG” and ends with that “TAA”.
        if ((stop_codon_index - start_codon_index) % 3 == 0) {
            String result = dna.substring(start_codon_index, stop_codon_index + stop_codon.length());
            return result;
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
    
        // For each DNA string you should:
        // Print the DNA string.
        System.out.println("dna1 = " + dna1);
        // See if there is a gene by calling findSimpleGene
        // with this string as the parameter.
        String gene1 = findSimpleGene(dna1);
        // If a gene exists following our algorithm above, then print the gene,
        // otherwise print the empty string.
        System.out.println("gene1 = " + gene1);

        System.out.println("dna2 = " + dna2);
        String gene2 = findSimpleGene(dna2);
        System.out.println("gene2 = " + gene2);

        System.out.println("dna3 = " + dna3);
        String gene3 = findSimpleGene(dna3);
        System.out.println("gene3 = " + gene3);

        System.out.println("dna4 = " + dna4);
        String gene4 = findSimpleGene(dna4);
        System.out.println("gene4 = " + gene4);

        System.out.println("dna5 = " + dna5);
        String gene5 = findSimpleGene(dna5);
        System.out.println("gene5 = " + gene5);

    }
}
