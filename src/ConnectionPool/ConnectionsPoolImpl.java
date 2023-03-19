package ConnectionPool;

import java.util.HashMap;

import ConnectionPool.*;

public class ConnectionsPoolImpl implements ConnectionsPool {
	
	static class Node{
		Connection connection;
		Node prev;
		Node next;
		
		Node(Connection connection){
			this.connection = connection;
		}
	}
	
	static class ListNodes{
		Node head;
		Node tail;
		
		void addHeadNode(Node node){
			if (head == null) {
				head = tail = node;				
			} else {
				head.next = node;
				node.prev = head;
				head = node;
			}
		}
		
		void removeTail() {
			tail = tail.next;
			if (tail != null) {
				tail.prev = null;
			}
		}
		
		void removeNode(Node node) {
			if (node == tail) {
				removeTail();
			} else {
				Node nodePrev = node.prev;
				Node nodeNext = node.next;
				nodePrev.next = nodeNext;
				nodeNext.prev = nodePrev;
			}
		}
	}
	
	ListNodes listNodes = new ListNodes();
	HashMap<Integer, Node> mapNodes = new HashMap<Integer, Node>();
	int connectionsLimit;
	
	public ConnectionsPoolImpl(int connectionsLimit) {
		this.connectionsLimit = connectionsLimit;
	}
	

	@Override
	public boolean addConnection(Connection connection) {
		boolean res = false;
		if (!mapNodes.containsKey(connection.getId())) {
			Node node = new Node(connection);
			mapNodes.put(connection.getId(), node);
			listNodes.addHeadNode(node);
			if (mapNodes.size() > connectionsLimit) {
				mapNodes.remove(listNodes.tail.connection.getId());
				listNodes.removeTail();
				
			}
			res = true;
		}
		return res;
	}

	@Override
	public Connection getConnection(int id) {
		Node node = mapNodes.get(id);
		if (node != null && node != listNodes.head) {
		listNodes.removeNode(node);
		listNodes.addHeadNode(node);
		}
		return node == null ? null : node.connection;
	}
	
	
}
