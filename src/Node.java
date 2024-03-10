import java.util.HashSet;
import java.util.Set;

public class Node {
    public final String name;
    int x, y;
    int id;

    private boolean poweredOn = false;
    private Set<Integer> neighbors;
    private World w;

    public void onMessage(Message m) {
        if (poweredOn) {
            neighbors.add(m.origin);
            if (m.reply == 0) {
                Message r = w.createMessage();
                r.origin = id;
                r.reply = m.origin;
                w.send(r, this);
            }
        }
        else {
            System.out.println("ignoré car éteint");
        }
    }

    public void onPowerOn() {
        poweredOn = true;
        id = w.nextId();
        System.out.println(name + " prend l'id " + id);
        Message m = w.createMessage();
        m.origin = id;
        m.reply = 0;
        m.t1 = w.getTime();
        w.send(m, this);
    }

    public boolean knows(Node n) {
        return neighbors.contains(n.id);
    }

    public Node(World world, String n, int x, int y) {
        name = n;
        this.x = x;
        this.y = y;
        w = world;
        neighbors = new HashSet<>();
    }
}
