import adtList.*;

public class SllTest {
    public static void main(String [ ] args) {
        LinkedList s = new LinkedList();
        System.out.println(s);
        s.addToTail(42);
        System.out.println(s);
        s.addToTail('n');
        System.out.println(s);
        s.addToTail(new String("hello"));
        System.out.println(s);
        s.addToHead(new String("apple"));
        System.out.println(s);
        while (!s.isEmpty()) {
            System.out.println("removed: " + s.removeFromHead());
            System.out.println(s);
        }
    }
}
