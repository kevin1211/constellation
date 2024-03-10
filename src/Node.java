public class Node {
    public final String name;
    int x, y;
    Node[] neighbors;
    int id;

    private boolean poweredOn = false;
    private World w;

    public void onMessage(Message m) {
        if (poweredOn) {
            System.out.println(name + " a reçu " + m);
        }
        else {
            System.out.println("ignoré car éteint");
        }
    }

    public void onPowerOn() {
        poweredOn = true;
        id = w.nextId();
        System.out.println(name + " prend l'id " + id);
        Message m = new Message(w.getTime());
        m.origin = id;
        m.reply = 0;
        m.t1 = w.getTime();
        w.send(m, this);
    }

    public Node(World world, String n, int x, int y) {
        name = n;
        this.x = x;
        this.y = y;
        w = world;
    }
}
