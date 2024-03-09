public class Message extends Event {
    int origin, reply;
    long t1;

    public Message(long t) {
        super(t, null);
    }
}
