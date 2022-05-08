import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class XRef {
    private static int lineCount = 1;
    private static LinkedList tempList = new LinkedList(new StringComparator());
    private static LinkedList refList = new LinkedList(new StringComparator());
    private static LinkedList linkedRefList = new LinkedList(new StringComparator());
    private static final String
            DELIMITER = "\"(?:\\\\\"|[^\"])*?\"|[\\s.,;:+*/|!=><@?#%&(){}\\-\\^\\[\\]\\&&]+";

    public static void main(String[] args) throws IOException {
        header();

        if (args.length == 0) {
            System.out.println("Usage: java XRef [File path]");
            System.out.println("\nXRef v1 terminated.");
            System.exit(1);
        } else if (args.length != 1) {
            System.out.println("Usage: java XRef [File path]");
            System.out.println("\nXRef v1 terminated.");
            System.exit(1);
        }

        Scanner file = new Scanner(new File(args[0]));
        String line;
        System.out.println("SOURCE FILE: " + new File(args[0]).getName());
        String[] tokens;
        while (file.hasNextLine()) {
            line = file.nextLine();
            tokens = tokenizer(line);
            for (String token: tokens)
                if (isIdentifier(token))
                    if (!tempList.isDuplicated(token))
                        tempList.insertInOrder(token);
            System.out.println(String.format("%04d", lineCount++) + " | " + line);
        }

        int count = tempList.getCount();
        int countRef = 0;
        for (int i = 0; i < count;i++) {
            lineCount = 1;
            Object item = tempList.removeFromHead();
            file = new Scanner(new File(args[0]));
            while (file.hasNextLine()) {
                line = file.nextLine();
                tokens = tokenizer(line);
                for (String t : tokens)
                    if (item.equals(t))
                        refList.addToTail(lineCount);
                countRef = refList.getCount();
                lineCount++;
            }
            linkedRefList.addToTail(item,refList.getHeadNode(refList));
            for(int j = 0;j< countRef;j++)
                refList.removeFromHead();
        }
        linkedRefList.toPrint();
        footer();
    }

    public static String[] tokenizer(String javaStmt) {
        String[] tokens = javaStmt.split(DELIMITER);
        return tokens;
    }

    public static boolean isIdentifier(String srcCode){
        if (!srcCode.equals("")) {
            char firstChar = srcCode.charAt(0);
            if (firstChar == '$' || firstChar == '_'
                    || (firstChar <= 'z' && firstChar >= 'a')
                    || (firstChar <= 'Z' && firstChar >= 'A')) {
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

    public static void header() {
        System.out.println("X R e f \t v1");
        System.out.println("-------------------------------------------");
        System.out.println("Program created by CHOY Ming San. 07-4-2021\n");
    }

    public static void footer() {
        System.out.println("\nXRef v1 normally terminated.");
    }
}