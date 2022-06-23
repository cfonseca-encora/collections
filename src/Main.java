import collections.list.linkedlist.LinkedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("Custom collections :P");
        LinkedList l = new LinkedList();
        l.add("asdadsasd");
        l.add("asdasdas");
        l.remove(1);
        log.info(l.getAt(0));
        log.info(String.valueOf(l.size()));
    }
}
