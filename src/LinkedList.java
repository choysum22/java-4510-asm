/*
 * Class : L i n k e d L i s t
 * 
 * @Name : CHOY Ming San
 * @StdID: 200216545
 * @Class: IT114105/1C
 * @2021-04-07
 */
public class LinkedList {
    public ListNode head;
    public ListNode tail;
    public ListNode refNode;        // reference node
    public Comparator comparator;

    public LinkedList(Comparator comparator) {
        head = tail = refNode = null;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return (head==null);
    }

    public void addToHead(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            head = new ListNode(item, head);
        }
    }

    public void addToTail(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            tail.next = new ListNode(item);
            tail =  tail.next;
        }
    }

    // add reference list to node
    public void addToTail(Object item, ListNode listNode) {
        if (isEmpty()) {
            head = tail = new ListNode(item,null,listNode);
        } else {
            tail.next = new ListNode(item,null,listNode);
            tail =  tail.next;
        }
    }

    public Object removeFromHead() throws EmptyListException {
        Object item = null;
        if (isEmpty()) {
            throw new EmptyListException();
        } 
        item = head.data;
        if (head == tail)      // there's only one single node
            head = tail = null;
        else
            head = head.next;
        return item;
    }

    public Object removeFromTail() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        } 
        Object item = tail.data;
        if (head == tail) {   // there is only one node
            head = tail = null;
            return item;
        }
        ListNode current = head;
        while (current.next != tail)
            current = current.next;
        tail = current;
        tail.next = null;
        return item;
    }

    public String toString() {
        String s = "[ ";
        ListNode current = head;
        while (current != null) {
            s += current.data + " ";
            current = current.next;
        }
        return s + "]";
    }

    // print the cross map
    public void toPrint() {
        String sCurrent, sCurrRef;
        ListNode current = head;
        System.out.println("\nCROSS REFERENCE:");
        while (current != null) {
            ListNode currRef = current.refList;
            sCurrent = (String) current.data;
            sCurrRef = "";
            while(currRef != null) {
                sCurrRef += currRef.data + " ";
                currRef = currRef.next;
            }
            current = current.next;
            System.out.printf("%-25s", sCurrent);
            System.out.println(" : [ "+sCurrRef + "]");
        }

    }

    // get the head node of a linked list
    public ListNode getHeadNode(LinkedList list) { return list.head; }

    // get the no. of item in the list
    public int getCount() {
        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public void insertInOrder (Object item) {
        if (isEmpty()) {
            head = tail = new ListNode (item);
        } else {
            if (comparator.isGreaterThanOrEqualTo(head.data, item)) {
                addToHead(item);
            } else if (comparator.isLessThanOrEqualTo(tail.data, item)) {
                addToTail(item);
            } else {
                // insert in the middle
                ListNode current = head;
                while (current.next != null) {
                    if (comparator.isGreaterThanOrEqualTo(current.next.data, item)) {
                        ListNode newNode = new ListNode(item);
                        newNode.next = current.next;
                        current.next = newNode;
                        return;
                    }
                    current = current.next;
                }
            }
        }
    }

    public void removeItem (Object item) throws ItemNotFoundException {
        if (isEmpty()) {
            throw new ItemNotFoundException();
        } 
        if (comparator.isEqualTo(head.data, item)) 
            removeFromHead();
        else if (comparator.isEqualTo(tail.data, item)) 
            removeFromTail();
        else {
            // remove a node in the middle
            ListNode current = head;
            while (current.next != null) {
                if (comparator.isEqualTo(current.next.data, item)) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
            throw new ItemNotFoundException();
        }
    }

    // check if the identifier is already in the list
    public boolean isDuplicated(String identifier) {
        ListNode current = head;
        while (current != null) {
            if (identifier.equals(current.data))
                return true;
            current = current.next;
        }
        return false;
    }

}
