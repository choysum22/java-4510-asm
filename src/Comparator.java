/*
 * Interface : C o m p a r a t o r
 *
 * @Name : CHOY Ming San
 * @StdID: 200216545
 * @Class: IT114105/1C
 * @2021-04-07
 */
public interface Comparator {
    public abstract boolean isEqualTo (Object item1, Object item2);
    //public abstract boolean isLessThan (Object item1, Object item2);
    public abstract boolean isLessThanOrEqualTo (Object item1, Object item2);
    public abstract boolean isGreaterThan (Object item1, Object item2);
    public abstract boolean isGreaterThanOrEqualTo (Object item1, Object item2);
}

