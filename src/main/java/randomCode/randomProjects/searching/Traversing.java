package randomCode.randomProjects.searching;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class Traversing {
	public static void main(String[] args) {
		List<Node<Integer>> bfsTree = Node.setupBFSTree();
		List<Node<Integer>> dfsTree = Node.setupDFSTree();
		
		List<List<Node<Integer>>> trees = Lists.newArrayList(bfsTree, dfsTree);
		
		for (List<Node<Integer>> tree : trees) {
			Node<Integer> root = tree.get(0);
			
			Deque<Node<Integer>> queue = new ArrayDeque<>();
			Deque<Node<Integer>> stack = new ArrayDeque<>();
			
			Set<Node<Integer>> bfsVistorsLog = new LinkedHashSet<>();
			Set<Node<Integer>> dfsVistorsLog = new LinkedHashSet<>();
			
			queue.addFirst(root);
			stack.addFirst(root);
			
			traverse(queue, bfsVistorsLog, TraverseType.BFS);
			traverse(stack, dfsVistorsLog, TraverseType.DFS);
			
			System.out.println(String.format("BFS: %s", bfsVistorsLog.stream().map(n -> n.getData()).collect(Collectors.toList())));
			System.out.println(String.format("DFS: %s", dfsVistorsLog.stream().map(n -> n.getData()).collect(Collectors.toList())));
			
		}
		
	}
	
	private static void traverse(Deque<Node<Integer>> deque, Set<Node<Integer>> vistorsLog, TraverseType traverseType) {
		while (!deque.isEmpty()) {
			
			Node<Integer> currentNode = deque.removeFirst();
			vistorsLog.add(currentNode);
			
			if (currentNode.hasChildren()) {
				List<Node<Integer>> children = currentNode.getChildren();
				
				if (traverseType == TraverseType.DFS) {Collections.reverse(children);}
				
				for (Node<Integer> child : children) {
					if (traverseType == TraverseType.BFS) {
						deque.addLast(child); //queue
					} else if (traverseType == TraverseType.DFS) {
						deque.addFirst(child); //stack
					}
				}
			}
		}
	}
}
