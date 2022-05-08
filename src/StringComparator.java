/*
 * Interface : S t r i n g C o m p a r a t o r
 *
 * @Name : CHOY Ming San
 * @StdID: 200216545
 * @Class: IT114105/1C
 * @2021-04-07
 */
public class StringComparator implements Comparator {
    
    public boolean isEqualTo (Object item1, Object item2) {
        if (((String) item1).compareTo((String) item2) == 0)
            return true;
        else
            return false;
    }

    public boolean isLessThanOrEqualTo (Object item1, Object item2) {
        if (((String) item1).compareTo((String) item2) <= 0)
            return true;
        else
            return false;
    }
    
    public boolean isGreaterThan (Object item1, Object item2) {
        if (((String) item1).compareTo((String) item2) > 0)
            return true;
        else
            return false;
    }
    
    public boolean isGreaterThanOrEqualTo (Object item1, Object item2) {
        if (((String) item1).compareTo((String) item2) >= 0)
            return true;
        else
            return false;
    }

}

