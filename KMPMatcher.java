
/**
 * 
 * Complexity:
 * -----------
 * O(n)
 * 
 */
public final class KMPMatcher {
    
    private KMPMatcher() { }
    
    /*
     * Assume ptrn is - abcabd, then lps would be:   [0 0 0 1 2 0]
     * Assume ptrn is - abaa, then lps would be: [0 0 1 1]
     * Assume ptrn is - YBYBO, then lps would be:   [0 0 1 2 0]
     * Assume ptrn is - YBCYBCO, then lps would be: [0 0 0 1 2 3 0]
     * 
     * LPS: longest proper prefix which is also suffix
     */
    public static int[] computeLPS(String ptrn) {
        int j = 0;
        int[] lps = new int[ptrn.length()];
        lps[0] = 0;
         
        for (int i = 1; i < ptrn.length(); i++) {      
            
            while (j > 0 && ptrn.charAt(i) != ptrn.charAt(j)) {
                j = lps[j - 1];  
            }
             
            if (ptrn.charAt(j) == ptrn.charAt(i)) { 
                j++;   
            } 
            
            lps[i] = j; 
        }
            
        return lps;
    }  


    public static void searchSubString(String text, String ptrn) {
        int j = 0;
        if (text.length() == 0) return;
        
 
        // initialize new array and preprocess the pattern
        int[] lps = computeLPS(ptrn);
 
        // i traverses the text, the j traverses the pattern, but j can go back and front.
        for (int i = 0; i < text.length(); i++) { 
            // abcabcabd
            // abcabd
            while (j > 0 && text.charAt(i) != ptrn.charAt(j)) {
                j = lps[j - 1];
            }
             
            if (text.charAt(i) == ptrn.charAt(j)) { 
                j++; 
            }
 
            // a match is found
            if (j == ptrn.length()) {
                System.out.println("found substring at index:" + (i - ptrn.length() + 1));
                j = 0;
            } 
        }
    }
    
    public static void main(String[] args) {
        searchSubString("ameya", "eya");
        searchSubString("ameyambyeamb", "amb");
    }
}
