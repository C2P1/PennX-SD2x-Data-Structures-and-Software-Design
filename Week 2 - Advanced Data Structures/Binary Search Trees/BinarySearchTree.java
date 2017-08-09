import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof BinarySearchTree.Node))
				return false;
			Node other = (Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	/*
	 * 	 Given a value that is stored in the BST, it returns the corresponding Node that holds it.
	 * 	 If the value does not exist in this BST, this method should return null.
	 */
	public Node findNode(E val) {
        if (val == null) {
            return null;
        }
        return findNode(root, val);
	}

	public Node findNode(Node n, E val) {
        if (n == null) {
            return null;
        }

        if (n.value == val) {
            return n;
        } else if (val.compareTo(n.value) < 0) {
            return findNode(n.leftChild, val);
        } else {
            return findNode(n.rightChild, val);
        }
    }

    /*
     * Given a value, this method should return the “depth” of its Node, which is the number of
     * ancestors between that node and the root, including the root but not the node itself.
     */
	protected int depth(E val) {
        if (val == null) {
            return -1;
        }
		Node target = findNode(val);
        if (target == null) {
            return -1;
        } else {
            Node curNode = root;
            int count = 0;
            while (!curNode.equals(target)) {
                count++;
                if (curNode.value.compareTo(val) > 0) { // if curNode value is smaller than val
                    curNode = curNode.leftChild;
                } else {
                    curNode = curNode.rightChild;
                }
            }
            return count;
        }
	}

	
	/*
	 * Given a value, this method should return the “height” of its Node which is
	 * the greatest number of nodes between that node and any descendant node that
	 * is a leaf, including the leaf but not the node itself.
	 */
	protected int height(E val) {

        if (val == null) {
            return -1;
        }
        Node target = findNode(val);

		return  height(target);
	}

    protected int height(Node n) {
        if (n == null) {
            return -1;
        }

        int leftHeight = height(n.leftChild);
        int rightHeight = height(n.rightChild);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }


	/*
	 * Given a Node, return true if the absolute value of the difference in
	 * heights of its left and right children is 0 or 1, and return false otherwise.
	 * If the Node is null or does not exist in this BST, this method should return false.
	 */
	protected boolean isBalanced(Node n) {
        if (n == null) {
            return false;
        }
        if (!contains(n.value)) {
            return false;
        }

        int leftHeight = height(n.leftChild);
        int rightHeight = height(n.rightChild);

        int diff = Math.abs(leftHeight - rightHeight);

		return (0 <= diff) && (diff <= 1);
	}
	
	/*
	 * returns true if isBalanced(Node) returns true for all Nodes in the tree.
	 */
	public boolean isBalanced() {

        ArrayList<Node> nodes = inorderRecursive(root, new ArrayList<>());

        for (Node node : nodes) {
            if (!isBalanced(node)) {
                return false;
            }
        }

		return true;

	}

    public ArrayList<Node> inorderRecursive(Node n, ArrayList<Node> array)
    {
        if (n == null){
            return null;
        }else{
            inorderRecursive(n.leftChild, array);
            array.add(n);
            inorderRecursive(n.rightChild, array);
        }
        return array;
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(8);
        tree.add(6);
        tree.add(16);
        tree.add(4);
        tree.add(2);
        tree.add(10);
        tree.add(20);
        tree.add(9);
        tree.add(12);

        //tree.inorderTraversal(tree.root);

        // System.out.println(tree.findNode(2).value);

        //System.out.println(tree.depth(99));

        //tree.inorderTraversal(tree.root);

        //System.out.println(tree.height(16));

        //System.out.println(tree.isBalanced(tree.findNode(6)));

        //System.out.println(tree.isBalanced());

    }
}
