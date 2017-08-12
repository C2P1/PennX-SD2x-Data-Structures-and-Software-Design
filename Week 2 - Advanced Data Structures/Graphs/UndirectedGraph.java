/*
 * Graph using an Adjacency Set
 */
public class UndirectedGraph extends Graph {

    public UndirectedGraph() {
        super();
    }

    @Override
    public boolean addEdge(Node node1, Node node2, int weight) {

        // only adds if node not already in graph
        addNode(node1);
        addNode(node2);

        boolean addEdgeSuccess =
                (addEdgeFromTo(node1, node2, weight)
                && addEdgeFromTo(node2, node1, weight));

        if (addEdgeSuccess) {
            incrementNumEdges();
        }

        return addEdgeSuccess;
    }


    @Override
    public boolean removeEdge(Node node1, Node node2, int weight) {
        return false;
    }
}
