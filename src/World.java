import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class World {
    List<Node> nodes;
    private PriorityQueue<Event> events;
    private long t = 0;
    private int nextId = 0;

    public int nextId() {
        return nextId++;
    }

    void schedule(Event e) {
        if (e.t_sim < t)
            throw new RuntimeException("retour vers le passé ?");
        events.add(e);
    }

    void send(Message m, Node origin) {
        try {
            for (Node dest : nodes)
                if (dest != origin) {
                    int e = (int)Math.hypot(origin.x - dest.x, origin.y - dest.y);
                    int dt = e / 340;
                    Event o;
                    m.origin_sim = origin;
                    o = m.cloneAt(t + dt);
                    o.dest = dest;
                    events.add(o);
                }
        }
        catch (CloneNotSupportedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    void run() {
        System.err.println("@startuml");
        long prev_t = -1; //display t=0
        while (!events.isEmpty()) {
            Event e = events.poll();
            t = e.t_sim;
            if (t != prev_t)
                System.err.println("... t=" + e.t_sim + " ...");
            if (e instanceof PowerOn) {
                System.err.println("note over " + e.dest.name + ": powered on");
                e.dest.onPowerOn();
            }
            else if (e instanceof Message) {
                Message m = (Message)e;
                System.err.println(m.origin_sim.name + " -> " + e.dest.name + ": " + m);
                e.dest.onMessage(m);
            }
            prev_t = t;
        }
        System.err.println("@enduml");
    }

    long getTime() {
        return t;
    }

    public World() {
        nodes = new ArrayList<Node>();
        events = new PriorityQueue<>();
    }
}
