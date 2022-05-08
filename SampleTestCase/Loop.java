import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Loop {
    public static void main(String[] args) throws FileNotFoundException {
        int num = 19;
        int[] loop = new int[num];
        for (int i = 0; i < loop.length; i++)
            loop[i] = i + 1;
        for (int i : loop) {
            System.out.print((int)Math.pow(2,i) + " \n");
        }
    }
}
