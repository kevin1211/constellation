public abstract class Event implements Cloneable, Comparable<Event> {
    public long t_sim;
    public Node dest;

    @Override
    public int compareTo(Event o) {
        return (int)(t_sim - o.t_sim);
    }

    public Event cloneAt(long t) throws CloneNotSupportedException {
        Event e = (Event)clone();
        e.t_sim = t;
        return e;
    }

    public Event(long t, Node d) {
        t_sim = t;
        dest = d;
    }
}
