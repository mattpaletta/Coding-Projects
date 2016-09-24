import java.util.*;
import java.io.*;
import java.lang.*;

public class MorseCode {
    String[][] dic;
    
    
    
    public void MorseCode() {
        //dic = add2(" ","", dic);
    }
    
    public static void main(String[] args) {
        String s= "HELLOWORLD";
        
        MorseCode L = new MorseCode();
        
        L.dic = new String[0][2];
        
        File file = new File(args[0]);
        
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            System.exit(-1);
        }
        
        while (sc.hasNextLine()) {
            String ch = sc.next();
            //System.out.print(ch + " ");
            String morse = sc.next();
            //System.out.println(morse);
            L.dic = L.add2(ch, morse, L.dic);
        }
        
        String converted = L.convertToMorse(s.toUpperCase());
        System.out.println(converted);
        //L.play(converted);
        System.out.println(L.convertBack(converted));
    }
    
    
    public String convertToMorse(String str) {
        String converted = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < dic.length; j++) {
                String c = str.charAt(i) + "";
                if (c.equals(dic[j][0])) {
                    converted += dic[j][1] + " ";
                }
            }
        }
        return converted;
    }
    
    public String convertBack(String str) {
        String s = "";
        String converted = "";
        for (int i = 0; i < str.length(); i++) {
            String a = str.charAt(i) + "";
            if (a.equals(" ")) {
                converted += lookup(s);
                s = "";
            } else {
                s += a;
            }
        }
        return converted;
    }
    
    public String lookup(String in) {
        
        String out = "";
        
        for (int i = 0; i < dic.length; i++) {
            if (dic[i][1].equals(in)) {
                out = dic[i][0];
                break;
            }
        }
        
        return out;
    }
                    
    public String[] add(String in, String[] A) {
        String[] B = new String[A.length+1];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        B[A.length] = in;
        
        return B;
    }
    
    public String[][] add2(String in1, String in2, String[][] A) {
        String[][] B = new String[A.length+1][2];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        String[] C = {in1, in2};
        B[A.length] = C;
        
        return B;
    }
}