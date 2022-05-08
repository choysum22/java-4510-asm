/*
 * Class : L i s t N o d e
 *
 * @Name : CHOY Ming San
 * @StdID: 200216545
 * @Class: IT114105/1C
 * @2021-04-07
 */
public class ListNode {
    public Object data;   
    public ListNode next;
    public ListNode refList;        // reference list
    public ListNode(Object data) {
        this(data, null);
    }

    public ListNode(Object data, ListNode next) {
        this(data,next,null);
    }

    public ListNode(Object data, ListNode next, ListNode refList) {
        this.data = data;
        this.next = next;
        this.refList = refList;
    }


}

