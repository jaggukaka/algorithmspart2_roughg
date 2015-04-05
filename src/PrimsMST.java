/**
 * Created by jreddypyla on 4/5/15.
 */
public class PrimsMST {

    private PrimsMST() {

    }

    private static final class PrimsMSTHolder {
        private static final PrimsMST PRIM_MST = new PrimsMST();

    }

    public static PrimsMST getInstance() {
        return PrimsMSTHolder.PRIM_MST;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int V = in.readInt();
        int E = in.readInt();

        EdgeWeightedGraph G = new EdgeWeightedGraph(V);

        while (!in.isEmpty()) {
            int from = in.readInt() - 1 ;
            int to = in.readInt() - 1 ;
            double weight = in.readDouble();

            G.addEdge(new Edge(from, to, weight));


        }

        System.out.println("The cost of the Prim's MST : " +  PrimsMST.getInstance().primsMSTCost(G, E));

    }


    public double primsMSTCost(EdgeWeightedGraph G, int numberOfEdges) {

        PrimMST mst = new PrimMST(G);
        return mst.weight();
    }
}
