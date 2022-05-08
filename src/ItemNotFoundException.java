/*
 * Class : I t e m N o t F o u n d E x c e p t i o n
 *
 * @Name : CHOY Ming San
 * @StdID: 200216545
 * @Class: IT114105/1C
 * @2021-04-07
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("Item is not found!");
    }
}
