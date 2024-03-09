public class Node {
    int x, y;
    Node[] neighbors;

    private boolean poweredOn = false;
    private static int nextId = 0;
    private int id;
    private World w;

    public void onMessage(Message m) {
        if (poweredOn) {
            System.out.println(id + " a reçu " + m);
        }
        else {
            System.out.println("ignoré car éteint");
        }
    }

    public void onPowerOn() {
        poweredOn = true;
        id = nextId++;
        System.out.println(this + " prend l'id " + id);
        Message m = new Message(w.getTime());
        m.origin = id;
        m.reply = 0;
        m.t1 = w.getTime();
        w.send(m, this);
    }

    public Node(World world, int x, int y) {
        this.x = x;
        this.y = y;
        w = world;
    }
}
