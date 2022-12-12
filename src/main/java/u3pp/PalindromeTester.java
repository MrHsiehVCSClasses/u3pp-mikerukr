package u3pp;

public class PalindromeTester {

    /**
     * Tests whether a string is a palindrome. Case insensitive. 
     * @param s  the string to test
     * @return true if the string is a palindrome
     */
    public static boolean isPalindrome(String s) {
        String rev= "";
        s = s.toLowerCase();
        boolean ans = false;
for(int i= s.length()-1;i >= 0; i--){
    rev = rev + s.charAt(i);
   /*  rev = rev + s.toLowerCase(i);*/
   System.out.println(rev);
}
if (s.equals(rev)){
    ans =true;
}
return ans;
    }
}
