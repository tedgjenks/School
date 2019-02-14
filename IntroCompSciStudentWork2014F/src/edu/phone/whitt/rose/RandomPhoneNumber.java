    package edu.phone.whitt.rose;
    
    import edu.jenks.dist.phone.*;
    
    public class RandomPhoneNumber extends AbstractRandomPhoneNumber
    {
        public static void main(String[] args) {
            System.out.println("Begin.");
            RandomPhoneNumber instance = new RandomPhoneNumber();
            for(int testIter = 1; testIter <= 100; testIter++) {
                String testNum = instance.generateRandomPhoneNumber();
                assert testNum.length() == 12 : "Test iter: " + testIter + "; test number: " + testNum + "Length is " + testNum.length();
                assert testNum.charAt(3) == '-' && testNum.charAt(7) == '-' : "Test iter: " + testIter + "; test number: " + testNum + " ; Hyphens misplaced";
                String areaCode = testNum.substring(0,3);
                assert validateAreaCode(areaCode) : "Test iter: " + testIter + "; test number: " + testNum + " ; Bad area code";
                String exchange = testNum.substring(4,7);
                assert validateExchange(exchange) : "Test iter: " + testIter + "; test number: " + testNum + " ; Bad exchange";
            }
            System.out.println("End without error.");
        }
        
        private static boolean validateExchange(String exchange) {
            int digits = Integer.parseInt(exchange);
            return digits < 743;
        }
        
        public static boolean validateAreaCode(String areaCode) {
           for(int index = 0; index < 3; index++) {
                String num = areaCode.substring(index, index + 1);
                int digit = Integer.parseInt(num);
                if (digit > 7)
                    return false;
            }
            return true;
        }
        
        private int random(int min, int max) {
            int range = max - min + 1;
            return (int)(Math.random() * range) + min;
        }
        
       private String generateSubscriber() {
           String sub = "";
           for(int i = 4; i > 0; i--)
               sub += random(0, 9);
           return sub;
        }
        
    private String generateExchange() {
       int ex = random(0, 742);
       String exch = String.valueOf(ex);
       if (exch.length() == 2) {
           exch = "0" + exch;
       } else if (exch.length() == 1){
           exch = "0" + "0" + exch;
       }
       return exch;
    }
    
    private String generateAreaCode() {
        String ac = "";
        for(int i = 3; i > 0; i--)
            ac += random(0, 7);
        return ac;
    }
    
    public String generateRandomPhoneNumber(){
        String result = "";
        result += generateAreaCode();
        result += '-';
        result += generateExchange();
        result += '-';
        result += generateSubscriber();
        return result;
    }
}
