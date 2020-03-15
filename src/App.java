import java.util.*;


public class App {

    public static void main(String[] args) {
        Scanner text = new Scanner(System.in);
        try {
            testUserInput("abbcccaaeeeeb bfffffca ccab",9,"abbcccaaeeeeb bfffffca ccab");
            testUserInput("abbcccaaeeeeb bfffffca ccab",5,"abbcccaaeeeeb b*****ca ccab");
            testUserInput("abbcccaaeeeeb bfffffca ccab",4,"abbcccaa****b b*****ca ccab");
            testUserInput("abbcccaaeeeeb bfffffca ccab",3,"abb***aa****b b*****ca ccab");
            testUserInput("abbcccaaeeeeb bfffffca ccab",2,"a***********b b*****ca **ab");
            testUserInput("abbcccaaeeeeb bfffffca ccab",0,"abbcccaaeeeeb bfffffca ccab");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Enter text");
        String inputText = text.nextLine();

        Scanner repeatCount = new Scanner(System.in);
        System.out.println("Enter count");

        int inputCount = 0;
        try {
            inputCount = Integer.parseInt(repeatCount.nextLine());
        }catch (Exception e){
            System.out.println("Please enter a number");
            System.exit(0);
        }

        String result = run(inputText,inputCount);

        System.out.println("result : " + result);

    }

    private static String run(String text, int count) {
        char[] chars =  text.toCharArray();
        char lastChecked = 0;

        // Kural 2'den küçük ya da text'in uzunluğundan büyük ise kontrol etme.
        if(count > 2 || count < chars.length){
            for(int i=0;i< chars.length; i++){

                //Son eşleşen karakter ile sıradaki karakter aynı ise '*' yap.
                if(lastChecked != 0 && chars[i] == lastChecked ){
                    chars[i] = '*';
                }else{
                    int repeatCount = 1;
                    for(int j = 1; j < count; j++ ){
                        if(chars.length > i + j){
                            if(chars[i+j] == chars[i]) {
                                repeatCount++;
                            }
                        }
                    }
                    if(repeatCount == count){
                        //Eşleşen karakteri kaydet.
                        lastChecked = chars[i];
                        for(int a = 0;a<count;a++){
                            chars[a+i] = '*';
                        }
                        //Eşleşenleri atla diğerlerinden devam et.
                        if(count+i > chars.length){
                            i = chars.length - count -1;
                        }else{
                            i = count+i-1;
                        }
                    }else{
                        //Son eşleşen karakteri sıfırla.
                        lastChecked = 0;
                    }
                }
            }
        }

        return new String(chars);
    }

    public static void testUserInput(String text, int count, String expected) throws Exception {
        String result = run(text,count);
        if(!result.equals(expected)) throw new Exception("Test case error: " + text + "-" + count);
    }


}
