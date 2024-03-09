public class App {
    public static void main(String[] args) throws Exception {
        World w = new World();
        Node a, b, c, d;
        a = new Node(w);
        b = new Node(w);
        c = new Node(w);
        d = new Node(w);
        w.nodes.add(a);
        w.nodes.add(b);
        w.nodes.add(c);
        w.nodes.add(d);
        w.schedule(new PowerOn(1, a));
        w.schedule(new PowerOn(2, b));
        w.run();
    }
}
