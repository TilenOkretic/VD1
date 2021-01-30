
import java.util.Scanner;

public class Vaja {

    public static void main(String[] args) {

        String in = new Scanner(System.in).next();

        for(int i = 0; i < in.length(); i++){
            if(i % 2 == 0) System.out.print(in.charAt(i));
        }

    }

}
