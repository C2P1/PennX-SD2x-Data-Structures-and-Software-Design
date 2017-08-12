import java.util.*;

/*
 * Graph using an Adjacency Set
 */
public abstract class Graph {

    private Map<Node, Set<Edge>> adjacencySets;
    private int numNodes;
    private int numEdges;

    public Graph() {
        adjacencySets = new HashMap<Node, Set<Edge>>();
        numNodes = 0;
        numEdges = 0;
    }

    public int getNumNodes() { return numNodes; }
    public int getNumEdges() { return numEdges; }

    public void incrementNumEdges() {
        numEdges++;
    }

    public boolean containsNode(Node node) {
        return adjacencySets.containsKey(node);
    }

    public boolean addNode(Node newNode) {

        if (newNode == null || containsNode(newNode)) {
            return false;
        }

        Set<Edge> newAdjacencySet = new HashSet<>();
        adjacencySets.put(newNode, newAdjacencySet);
        numNodes++;
        return true;
    }

    public Set<Node> getNodeNeighbors(Node node) {
        if (!containsNode(node)) {
            return null;
        }
        Set<Edge> nodeEdges = adjacencySets.get(node);
        Set<Node> nodeNeighbors = new HashSet<>();
        for (Edge e: nodeEdges) {
            Node neighbor = e.getDestination();
            nodeNeighbors.add(neighbor);

        }

        return nodeNeighbors;
    }

    // declared abstract as directed graph will have different impl to a undirected graph
    public abstract boolean addEdge(Node node1, Node node2, int weight);
    public abstract boolean removeEdge(Node node1, Node node2, int weight);

    protected boolean addEdgeFromTo(Node source, Node destination, int weight) {
        Edge newEdge = new Edge(source, destination, weight);
        Set<Edge> sourceEdges = adjacencySets.get(source);

        // if set doesnt already contain the new edge then add new edge to the set
        if (!sourceEdges.contains(newEdge)) {
            sourceEdges.add(newEdge);
            return true;
        }
        return false;
    }

    /**
     *  Node class
     */
    public class Node {

        private String element;

        public Node(String element) {
            this.element = element;
        }

        public String getElement() {
            return element;
        }

        @Override
        public String toString() {
            return getElement();
        }
    }


    /**
     * Edge class
     */
    public class Edge {
        private final Node source;
        private final Node destination;
        private final int weight;

        public Edge(Node source, Node destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public Node getSource() {
            return source;
        }

        public Node getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return source.toString() + " --" + weight + "--> " + destination.toString();
        }

        public boolean equals(Edge otherEdge) {
            Node otherSource = otherEdge.getSource();
            Node otherDest = otherEdge.getDestination();
            int otherWeight = otherEdge.getWeight();
            return (otherSource.equals(source) && otherDest.equals(destination) && otherWeight == weight);
        }

        @Override
        public int hashCode() {
            return source.hashCode() + destination.hashCode() + weight;
        }
    }

    /**
     * Breadth-First Search
     * systematic way of traversing a graph
     * BFS guaranteed will find minimum number of edges
     *
     * Start with a source node...
     * then visit all its neighbours...
     * then visit all their neighbours...
     * and so on, until destination is found.
     */
    public class BreadthFirstSearch {
        private Set<Node> marked;
        private Graph graph;

        public BreadthFirstSearch(Graph graphToSearch) {
            marked = new HashSet<>();
            graph = graphToSearch;
        }

        public boolean bfs(String elementToFind, Node start) {
            if (!graph.containsNode(start)) {
                return false;
            }
            if (start.getElement().equals(elementToFind)) {
                return true;
            }

            Queue<Node> toExplore = new LinkedList<Node>();
            marked.add(start);
            toExplore.add(start);
            while (!toExplore.isEmpty()) {
                Node current = toExplore.remove(); // get first node in queue
                for (Node neighbor : graph.getNodeNeighbors(current)) {
                    if (!marked.contains(neighbor)) {
                        if (neighbor.getElement().equals(elementToFind)) {
                            return true;
                        }
                        marked.add(neighbor);
                        toExplore.add(neighbor);
                    }
                }
            }
            return false;
        }
    }

    /**
     * Depth-First Search
     * recusive way of traversing graph.
     * (could use a stack in a non-recursive implementation)
     * DFS is more efficient (in memory and execution time)
     *
     * start with a source node...
     * then visit one of its neighbors...
     * and so on, until destination is found.
     */
    public class DepthFirstSearch {
        private Set<Node> marked;
        private Graph graph;

        public DepthFirstSearch(Set<Node> marked, Graph graph) {
            this.marked = marked;
            this.graph = graph;
        }

        public boolean dfs(String elementToFind, Node start) {
            if (!graph.containsNode(start)) {
                return false;
            }

            if (start.getElement().equals(elementToFind)) {
                return true;
            } else {
                marked.add(start);
                for (Node neighbor : graph.getNodeNeighbors(start)) {
                    if (dfs(elementToFind, neighbor)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
}
