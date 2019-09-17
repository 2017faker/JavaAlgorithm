package initcode;
import java.util.*;
public class Solution {
    public static void rotateString(char[] str, int offset) {
        // write your code
        int k=str.length-offset;

        for(int i=k;i<str.length;i++){
            System.out.print(str[i]);
        }
        for(int i=0;i<k;i++) {
            System.out.print(str[i]);
        }
    }

    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
       // char[] str='abcdefg';
       // rotateString(str,3);
        String a=input.next();
        int b=input.nextInt();
        char[] str=a.toCharArray();
        for(int i=0;i<str.length;i++){
          //  System.out.print(str[i]);
        }
        System.out.println("");
        rotateString(str,b);

    }
}
