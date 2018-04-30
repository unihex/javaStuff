package randomCode.randomProjects.trees;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTree {
	
	private BinaryNode root;
	
	
	public BinarySearchTree(int rootData) {
		this.root = new BinaryNode(rootData);
	}
	
	public void insert(int newNodeData) {
		BinaryNode newNode = new BinaryNode(newNodeData);
		
		insert(root, newNode);
	}
	
	public void insert(BinaryNode currentNode, BinaryNode newNode) {
		
		if (currentNode.data > newNode.data) {
			
			if (currentNode.leftChild == null) {
				newNode.parent = currentNode;
				currentNode.leftChild = newNode;
				
			} else {
				insert(currentNode.leftChild, newNode);
				
			}
			
		} else if (currentNode.data < newNode.data) {
			
			if (currentNode.rightChild == null) {
				newNode.parent = currentNode;
				currentNode.rightChild = newNode;
				
			} else {
				insert(currentNode.rightChild, newNode);
			}			
		}
	}
	
	public void invert() {
		invert(root);
	}
	
	public void invert(BinaryNode currentNode) {
		BinaryNode oldLeft = currentNode.leftChild;
		BinaryNode oldRight = currentNode.rightChild;
		
		currentNode.leftChild = oldRight;
		currentNode.rightChild = oldLeft;
		
		if (oldLeft != null) {
			invert(oldLeft);
		}
		
		if (oldRight != null) {
			invert(oldRight);
		}
	}
	
	public void printDFS() {
		Deque<BinaryNode> stack = new ArrayDeque<>();
		stack.addFirst(root);
		
		traverse(stack, TraverseType.DFS);	
	}
	
	public void printBFS() {
		Deque<BinaryNode> queue = new ArrayDeque<>();
		queue.addFirst(root);
		
		traverse(queue, TraverseType.BFS);	
	}
	
	public void traverse(Deque<BinaryNode> deque, TraverseType traverseType) {
		while (!deque.isEmpty()) {
			
			BinaryNode currentNode = deque.removeFirst();
			System.out.println(currentNode);
			
			if (traverseType == TraverseType.BFS) {
				
				if (currentNode.leftChild != null) {
					deque.addLast(currentNode.leftChild);
				}
					
				if (currentNode.rightChild != null) {
					deque.addLast(currentNode.rightChild);
				}
				
			} else if (traverseType == TraverseType.DFS) {
				
				if (currentNode.rightChild != null) {
					deque.addFirst(currentNode.rightChild);
				}
					
				if (currentNode.leftChild != null) {
					deque.addFirst(currentNode.leftChild);
				}			
			}
		}
		
		System.out.println();
	}
	
	private class BinaryNode {
		int data;
		
		BinaryNode parent;
		
		BinaryNode leftChild;
		BinaryNode rightChild;
		
		BinaryNode(int data) {
			this.data = data;
		}
		
		public String toString() {
			if (parent == null) {
				return String.format("I am %d. I am the root of the tree!", data);
				
			} else {
				return String.format("I am %d. My parent is %d", data, parent.data);
				
			}
			
		}		
	}
}
