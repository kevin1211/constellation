public class App {
    public static void main(String[] args) throws Exception {
        World w = new World();
        Node a, b, c, d;
        a = new Node(w, 0, 0);
        b = new Node(w, 350, 0);
        c = new Node(w, 700, 0);
        d = new Node(w, 1050, 0);
        w.nodes.add(a);
        w.nodes.add(b);
        w.nodes.add(c);
        w.nodes.add(d);
        w.schedule(new PowerOn(0, a));
        w.schedule(new PowerOn(0, b));
        w.schedule(new PowerOn(0, c));
        w.schedule(new PowerOn(0, d));
        w.run();
    }
}
