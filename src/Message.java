public class Message extends Event {
    int origin, reply;
    long t1;
    Node origin_sim;

    @Override
    public String toString() {
        return "o=" + origin + ", r=" + reply + ", t1=" + t1;
    }

    protected Message(long t) {
        super(t, null);
    }
}
