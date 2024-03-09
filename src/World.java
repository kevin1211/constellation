import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class World {
    List<Node> nodes;
    private PriorityQueue<Event> events;
    private long t = 0;

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
        while (!events.isEmpty()) {
            Event e = events.poll();
            t = e.t_sim;
            if (e instanceof PowerOn) {
                e.dest.onPowerOn();
            }
            else if (e instanceof Message) {
                e.dest.onMessage((Message)e);
            }
        }
    }

    long getTime() {
        return t;
    }

    public World() {
        nodes = new ArrayList<Node>();
        events = new PriorityQueue<>();
    }
}
