import java.util.*;

/*
 * SD2x Homework #6
 */

/**
 * This is an implementation of Breadth First Search (BFS) on a graph.
 */
public class BreadthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		graph = graphToSearch;
	}
	
	/**
     * finds minimum number of edges to destination node
	 * Return -1 if element not found in graph
     * return 0 if element is the node
	 */
	public int bfs1(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
            System.out.println("Graph does not contain inputted start node");
            return -1;
		}
		if (start.getElement().equals(elementToFind)) {
			System.out.println("the start element is the destination element");
			return 0;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		Map<Node, Node> prev = new HashMap<>();
		toExplore.add(start);



		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
            if (current.element.equals(elementToFind)) {
			    break;
            } else {
                for (Node neighbor : graph.getNodeNeighbors(current)) {
                    if (!marked.contains(neighbor)) {
                        marked.add(neighbor);
                        toExplore.add(neighbor);
                        prev.put(neighbor, current);
                    }
                }
            }
		}

        List<Node> directions = new LinkedList<>();
        for(Node node = graph.getNode(elementToFind); node != null; node = prev.get(node)) {
            directions.add(node);
        }

        if (directions.size() == 1) {
            return -1;
        } else {
            return directions.size() - 1;
        }
	}
}
