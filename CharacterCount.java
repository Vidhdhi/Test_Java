public class CharacterCount    
{    
    public static void main(String[] args) {    
        String string = "I am A good   Copy Paster ";    
        int count = 0;      
        for(int i = 0; i < string.length(); i++) {    
            if(((string.charAt(i) == ' ') && (string.charAt(i+1) != ' ')))
                       count++;    
        }    
        System.out.println("Totall Word is " + count);    
    }    
}