import java.util.*;

/*
 * SD2x Homework #6
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {

		// use breadth first search to find min distance
		if (graph == null) {
            System.out.println("null graph");
            return -1;
        }
        if (src == null) {
            System.out.println("null source node");
            return -1;
        }
        if (dest == null) {
            System.out.println("null destination node");
            return -1;
        }
        if (!graph.containsElement(dest)) {
            System.out.println("Does not contain destination element");
            return -1;
        }
        if (!graph.containsElement(src)) {
            System.out.println("Does not contain source element");
		    return -1;
        }



        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);

		Node start = graph.getNode(src);

		return bfs.bfs1(start, dest);
	}
	
    /**
     * Set of the values of all nodes within the specified distance (in terms of number of edges)
     * of the node labeled src, i.e. for which the minimum number of edges from src to that node
     * is less than or equal to the specified distance.
     */
	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

        if (graph == null) {
            System.out.println("graph is null");
            return null;
        }
        if (src == null) {
            System.out.println("source is null");
            return null;
        }
        if (distance < 1) {
            System.out.println("distance is < 1");
            return null;
        }

        Node start = graph.getNode(src);

        if (!graph.containsNode(start)) {
            System.out.println("graph does not contain the inputted source node");
            return null;
        }

        Set<Node> allNodes = graph.getAllNodes();
        Set<String> valuesWithinDistance = new HashSet<>();

        for (Node node : allNodes) {
            int distFromSrc = minDistance(graph, src, node.getElement());
            if (distFromSrc != -1 && distFromSrc != 0) {
                if (distFromSrc <= distance) {
                    valuesWithinDistance.add(node.getElement());
                }
            }

        }
        return valuesWithinDistance;
    }

    /**
     * A Hamiltonian Path is a valid path through the graph in which every node
     * in the graph is visited exactly once, except for the start and end nodes,
     * which are the same, so that it is a cycle.
     */
	public static boolean isHamiltonianPath(Graph g, List<String> values) {

        Map<String, Integer> visited = new HashMap<>();
        if (g == null || values == null || values.isEmpty()) {
            return false;
        }

        for (int i = 0; i < values.size() - 1; i++) {
            String current = values.get(i);
            Node currentNode = g.getNode(current);
            Set<Edge> currentEdges = g.getNodeEdges(currentNode);
            for (Edge edge : currentEdges) {
                if (edge.getSource().equals(currentNode)) {
                    if (edge.getDestination().equals(g.getNode(values.get(i + 1)))) {
                        if (visited.containsKey(current)) {
                            int cur = visited.get(current);
                            visited.put(current, ++cur);
                        } else {
                            visited.put(current, 1);
                        }
                    }
                }
            }
        }

        if (visited.size() < g.numNodes) {
            System.out.println("Did not visit all nodes");
            return false;
        }


        Set<Map.Entry<String, Integer>> vals = visited.entrySet();
        for (Map.Entry<String, Integer> val : vals) {
            if (val.getValue() > 1) {
                System.out.println("visited node " + val.getValue() + " more than once.");
                return false;
            }
        }

        return true;
	}

    public static void main(String[] args) {
        // testing getting the minimum distance between two nodes
        UndirectedGraph undirectedGraph = GraphBuilder.buildUndirectedGraph("videoExample.txt");
        int dst1 = minDistance(undirectedGraph, "A", "D");
        System.out.println(dst1);

        // testing getting the minimum distance of all nodes within a given distance of a node
        Set<String> nodeswithin = nodesWithinDistance(undirectedGraph, "A", 1);
        System.out.println(nodeswithin);

        // testing given a graph and a path through that graph if the path is Hamiltonian
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("E");
        list.add("F");
        System.out.println(isHamiltonianPath(undirectedGraph, list));
    }
}
