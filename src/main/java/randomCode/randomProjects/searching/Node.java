package randomCode.randomProjects.searching;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	private T data;
	
	private Node<T> parent;
	private List<Node<T>> children;
	
	public Node(T data) {
		this.data = data;
		children = new ArrayList<>();
	}
	
	public void addChild(Node<T> child) {
		child.setParent(this);
		this.children.add(child);
	}
	
	public Node<T> getParent() {
		return parent;
	}
	
	public List<Node<T>> getChildren() {
		return children;
	}
	
	public boolean hasChildren() {
		return 0 != children.size();
	}
	
	public void setParent(Node<T> node) {
		parent = node;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public static List<Node<Integer>> setupBFSTree() {
		List<Node<Integer>> bsfNodeList = new ArrayList<>();
		
		for (int i = 1; i <= 12; i++) {
			bsfNodeList.add(new Node<>(i));
		}
		
		bsfNodeList.get(0).addChild(bsfNodeList.get(1));
		bsfNodeList.get(0).addChild(bsfNodeList.get(2));
		bsfNodeList.get(0).addChild(bsfNodeList.get(3));

		bsfNodeList.get(1).addChild(bsfNodeList.get(4));
		bsfNodeList.get(1).addChild(bsfNodeList.get(5));
		
		bsfNodeList.get(3).addChild(bsfNodeList.get(6));
		bsfNodeList.get(3).addChild(bsfNodeList.get(7));
		
		bsfNodeList.get(4).addChild(bsfNodeList.get(8));
		bsfNodeList.get(4).addChild(bsfNodeList.get(9));
		
		bsfNodeList.get(6).addChild(bsfNodeList.get(10));
		bsfNodeList.get(6).addChild(bsfNodeList.get(11));
		

		return bsfNodeList;
	}
	
	public static List<Node<Integer>> setupDFSTree() {
		List<Node<Integer>> dsfNodeList = new ArrayList<>();
		
		for (int i = 1; i <= 12; i++) {
			dsfNodeList.add(new Node<>(i));
		}
		
		dsfNodeList.get(0).addChild(dsfNodeList.get(1));
		dsfNodeList.get(0).addChild(dsfNodeList.get(6));
		dsfNodeList.get(0).addChild(dsfNodeList.get(7));

		dsfNodeList.get(1).addChild(dsfNodeList.get(2));
		dsfNodeList.get(1).addChild(dsfNodeList.get(5));
		
		dsfNodeList.get(2).addChild(dsfNodeList.get(3));
		dsfNodeList.get(2).addChild(dsfNodeList.get(4));
		
		dsfNodeList.get(7).addChild(dsfNodeList.get(8));
		dsfNodeList.get(7).addChild(dsfNodeList.get(11));
		
		dsfNodeList.get(8).addChild(dsfNodeList.get(9));
		dsfNodeList.get(8).addChild(dsfNodeList.get(10));
		

		return dsfNodeList;
	}
}
