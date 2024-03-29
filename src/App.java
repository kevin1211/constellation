public class App {
    public static void main(String[] args) throws Exception {
        World w = new World();
        Node a, b, c, d;
        a = new Node(w, "A", 0, 0);
        b = new Node(w, "B", 350, 0);
        c = new Node(w, "C", 700, 0);
        d = new Node(w, "D", 1050, 0);
        w.nodes.add(a);
        w.nodes.add(b);
        w.nodes.add(c);
        w.nodes.add(d);
        w.schedule(new PowerOn(0, a));
        w.schedule(new PowerOn(0, b));
        w.schedule(new PowerOn(0, c));
        w.schedule(new PowerOn(0, d));
        w.run();
        boolean ok = true;
        for (Node n : w.nodes) {
            for (Node n2 : w.nodes) {
                if (n != n2 && !n.knows(n2)) {
                    ok = false;
                    System.out.println("fail: " + n2.name + " not discovered by " + n.name);
                }
            }
        }
        if (!ok)
            System.exit(1);
    }
}
