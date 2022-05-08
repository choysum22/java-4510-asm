import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * class XRef - Cross Reference Map
 *
 * I understand the meaning of academic dishonesty, in particular plagiarism, copyright infringement
 * and collusion. I am aware of the consequences if found to be involved in these misconducts. I hereby
 * declare that the work submitted for the "ITP4510 Data Structures & Algorithms" is authentic record
 * of my own work.
 *
 * @Name : CHOY Ming San
 * @StdID: 200216545
 * @Class: IT114105/1C
 * @2021-04-07
 */

public class XRef {
    private static int lineCount = 1;           // line counter
    // temporary list to store split identifier
    private static LinkedList tempList = new LinkedList(new StringComparator());
    // reference list to store the reference line no
    private static LinkedList refList = new LinkedList(new StringComparator());
    // list to store identifier and its reference
    private static LinkedList linkedRefList = new LinkedList(new StringComparator());
    private static final String                 // Delimiter for splitting
            DELIMITER = "\"(?:\\\\\"|[^\"])*?\"|[\\s.,;:+*/|!=><@?#%&(){}\\-\\^\\[\\]\\&&]+";

    public static void main(String[] args) throws IOException {
        header();                               // header

        if (args.length == 0) {                 // terminate when no file path provided
            System.out.println("Usage: java XRef [File path]");
            System.out.println("\nXRef v1 terminated.");
            System.exit(1);
        } else if (args.length != 1) {          // terminate when more than one file path provided
            System.out.println("Usage: java XRef [File path]");
            System.out.println("\nXRef v1 terminated.");
            System.exit(1);
        }

        Scanner file = new Scanner(new File(args[0]));      // file scanner
        String line;                                        // store line from scanner
        System.out.println("SOURCE FILE: " + new File(args[0]).getName());
        String[] tokens;                                    // String array to store identifier
        // print file into String and record line no.
        while (file.hasNextLine()) {
            line = file.nextLine();
            tokens = tokenizer(line);                       // split line into separate String
            for (String token: tokens)
                // check if token is a valid identifier
                if (isIdentifier(token))
                    if (!tempList.isDuplicated(token))
                        // insert into temporary list for sorting in alphabetical order
                        tempList.insertInOrder(token);
            System.out.println(String.format("%04d", lineCount++) + " | " + line);
        }

        int count = tempList.getCount();                // take count of how many item in temporary list
        int countRef = 0;                               // initialize the count of the reference list
        // search for matches of the identifier
        for (int i = 0; i < count;i++) {
            lineCount = 1;
            Object item = tempList.removeFromHead();
            file = new Scanner(new File(args[0]));
            // traverse line for matching
            while (file.hasNextLine()) {
                line = file.nextLine();
                tokens = tokenizer(line);               // split line into separate String
                // matching through for-loop
                for (String t : tokens)
                    if (item.equals(t))
                        refList.addToTail(lineCount);   // add line no. to reference list
                countRef = refList.getCount();
                lineCount++;
            }
            // add the reference list and identifier into a
            // linked list to have a reference of the identifier
            linkedRefList.addToTail(item,refList.getHeadNode(refList));

            // clear reference list
            for(int j = 0;j< countRef;j++)
                refList.removeFromHead();
        }
        linkedRefList.toPrint();                        // print cross reference map
        footer();                                       // footer
    }

    // split the String line till only words are remaining
    public static String[] tokenizer(String javaStmt) {
        String[] tokens = javaStmt.split(DELIMITER);
        return tokens;
    }

    // check if the word is an identifier
    public static boolean isIdentifier(String srcCode){
        // prevent empty space
        if (!srcCode.equals("")) {
            char firstChar = srcCode.charAt(0);
            // an identifier can begin with a letter, a dollar
            // sign ($) or an underscore character ( _ ).
            if (firstChar == '$' || firstChar == '_'
                    || (firstChar <= 'z' && firstChar >= 'a')
                    || (firstChar <= 'Z' && firstChar >= 'A')) {
                // keywords are not identifiers
                return !srcCode.matches("abstract|continue|for|new|switch|" +
                        "assert|default|goto|package|synchronized|" +
                        "boolean|do|if|private|this|" +
                        "break|double|implements|protected|throw|" +
                        "byte|else|import|public|throws|" +
                        "case|enum|instanceof|return|transient|" +
                        "catch|extends|int|short|try|" +
                        "char|final|interface|static|void|" +
                        "class|finally|long|strictfp|volatile|" +
                        "const|float|native|super|while");
            }
            return false;
        }
        return false;
    }

    // header of the program
    public static void header() {
        System.out.println("X R e f \t v1");
        System.out.println("-------------------------------------------");
        System.out.println("Program created by CHOY Ming San. 07-4-2021\n");
    }

    // footer of the program
    public static void footer() {
        System.out.println("\nXRef v1 normally terminated.");
    }
}