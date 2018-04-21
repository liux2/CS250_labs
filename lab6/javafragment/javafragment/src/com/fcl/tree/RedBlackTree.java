package com.fcl.tree;

import java.util.LinkedHashMap;

import javax.management.RuntimeErrorException;

/**
 * @author fang.cai.liang@aliyun.com
 * @date 2016年9月16日 上午11:54:07
 */

public class RedBlackTree<K extends Comparable<K>, V> {
	
	private static final byte RED = 1;
	private static final byte BLACK = 0; 
	
	private class Node{
		private byte color;
		private K key; // 存储的key
		private V value; // 存储的值
		private Node leftNode;  // 左节点
		private Node rightNode; // 右节点
		private Node parentNode; // 父节点
		
		public Node(K key, V value, Node leftNode, Node rightNode, byte color, Node parentNode) {
			super();
			this.key = key;
			this.value = value;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			this.color = color;
			this.parentNode = parentNode;
		}
		
		@Override
		public String toString(){
			return "{"
					+ "\"key\":" + this.key + ", "
					+ "\"value\":" + "\"" + this.value + "\"" + ", "
					+ "\"color\":" + ((this.color == RED) ? "\"红\"" : "\"黑\"") + ", "
					+ "\"leftNode\":" + this.leftNode + ","
					+ "\"rightNode\":" + this.rightNode + "}";
		}
	}
	
	private Node root; // 根节点
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * 根据 key 来获取指定的节点
	 * @param key
	 * @return
	 */
	public Node get(K key) {
		if(null != key){
			return find(key, root); // 从根节点开始找
		}
		return null;
	}
	
	private Node find(K key, Node root){
		if(null != root){
			int result = key.compareTo(root.key);
			if(result == 0){
				return root;
			}
			if(result > 0){
				return find(key, root.rightNode);
			}
			if(result < 0){
				return find(key, root.leftNode);
			}
		}
		return null;
	}
	
	/**
	 * 通过中序遍历（按 左，中，右的顺序遍历，），能得到从小到大的 节点顺序
	 * @return
	 */
	public LinkedHashMap<K, V> inorderTraversal(){
		LinkedHashMap<K, V> nodeMap = new LinkedHashMap<K, V>();
		if(null != root){
			inorder(root, nodeMap);
		}
		return nodeMap;
	}
	
	private void inorder(Node root, LinkedHashMap<K, V> nodeMap){
		if(null != root.leftNode){
			inorder(root.leftNode, nodeMap);
		}
		nodeMap.put(root.key, root.value);
		if(null != root.rightNode){
			inorder(root.rightNode, nodeMap);
		}
	}
	
	/**
	 * 对节点 h 左旋转
	 * 			|							|
	 * 			|							|
	 * 			h							m
	 * 		   / \     	  	h 左旋转后	   / \
	 * 		  a   m						  h	  t
	 * 			 / \					 / \
	 * 			k   t					a   k
	 */
	private void leftRotation(Node h){
		Node m = h.rightNode;
		// 1. 将 k 节点设置为 h 的右节点
		h.rightNode = m.leftNode;
		if(null != m.leftNode){
			m.leftNode.parentNode = h;
		}
		// 2. 将 h 的父节点 赋给 m 的父节点，之后分 3 种情况讨论
		m.parentNode = h.parentNode;
		if(null == m.parentNode){ // I: 说明 h 原来是根节点，现在将 m 设置为新的根节点
			root = m;
		}else{
			if(h.key.compareTo(h.parentNode.key) < 0){ // II: 说明 h 原来是它父节点的左孩子，现在将 m 设置为新的左孩子
				h.parentNode.leftNode = m;
			}else {
				h.parentNode.rightNode = m; // III: 说明 h 原来是它父节点的右孩子，现在将 m 设置为新的右孩子
			}
		}
		// 3. 将 h 挂靠在 m 的左孩子上
		m.leftNode = h;
		h.parentNode = m;
	}
	
	/**
	 * 对节点 m 右旋转
	 * 			|							|
	 * 			|							|
	 * 			m							h
	 * 		   / \     	  	m 右旋转后	   / \
	 * 		  h   t						  a	  m
	 * 		 / \					         / \
	 * 		a   k					        k   t
	 */
	private void rightRotation(Node m){
		Node h = m.leftNode;
		// 1. 将 k 设置为 m 的左节点
		m.leftNode = h.rightNode;
		if(null != h.rightNode){
			h.rightNode.parentNode = m;
		}
		// 2. 将 m 的父节点 赋给 h 的父节点，之后分 3 种情况讨论
		h.parentNode = m.parentNode;
		if(null == m.parentNode){ // I: 说明 m 原来是根节点，现在将 h 设置为新的根节点
			root = h;
		}else{
			if(m.key.compareTo(m.parentNode.key) < 0){ // II: 说明 m 原来是它父节点的左孩子，现在将 h 设置为新的左孩子
				m.parentNode.leftNode = h;
			}else {
				m.parentNode.rightNode = h; // III: 说明 m 原来是它父节点的右孩子，现在将 h 设置为新的右孩子
			}
		}
		// 3. 将 m 挂靠在 h 的右孩子上
		h.rightNode = m;
		m.parentNode = h;
	}
	
	/**
	 * 插入新的节点，如果指定的key已经存在，则更新原来的值
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		Node newNode = new Node(key, value, null, null, RED, null);
		if(null == root){
			root = newNode;
			root.color = BLACK;
		}else{
			upsert(null, root, newNode);
		}
	}
	
	private void upsert(Node parent, Node current, Node newNode){
		if(null == current){
			if(newNode.key.compareTo(parent.key) > 0){
				parent.rightNode = newNode;
			}else{
				parent.leftNode = newNode;
			}
			newNode.parentNode = parent;
			upsertFix(newNode); // 插入新节点后 对红黑树进行修复
		}else{
			int result = newNode.key.compareTo(current.key);
			if(result == 0){
				current.value = newNode.value;
			}
			parent = current;
			if(result > 0){
				upsert(parent, parent.rightNode, newNode);
			}
			if(result < 0){
				upsert(parent, parent.leftNode, newNode);
			}
		}
	}
	
	private void upsertFix(Node newNode){
		Node parent = newNode.parentNode;
		if(RED == parent.color){ // 父节点如果是黑节点 则不需要处理
			Node grandfather = parent.parentNode;
			if(parent == grandfather.leftNode){ // 1. 父节点原来是 左节点
				Node uncle = grandfather.rightNode;
				if((null != uncle) && (RED == uncle.color)){ // case 3: 叔叔节点是红色
					uncleRedFix(newNode);
				}else{ // 叔叔节点为 NULL 或者 是黑色节点
					if(newNode.key.compareTo(parent.key) < 0){ // case 1: 叔叔节点是黑色，插入到左子树中
						leftNodeFix(grandfather, parent);
					}else{ // case 2: 叔叔节点是黑色，插入到右子树中
						leftRotation(parent);
						leftNodeFix(grandfather, newNode); //我们将 parent 节点作为“新插入的节点”，这样 真正新插入的节点 newNode 就是父节点
					}
				}
			}else{ // 1. 父节点原来是 右节点
				Node uncle = grandfather.leftNode;
				if((null != uncle) && (RED == uncle.color)){ // case 3: 叔叔节点是红色
					uncleRedFix(newNode);
				}else{ // 叔叔节点为 NULL 或者 是黑色节点
					if(newNode.key.compareTo(parent.key) > 0){ // case 1: 叔叔节点是黑色，插入到右子树中
						rightNodeFix(grandfather, parent);
					}else{ // case 2: 叔叔节点是黑色，插入到左子树中
						rightRotation(parent);
						rightNodeFix(grandfather, newNode); //我们将 parent 节点作为“新插入的节点”，这样 真正新插入的节点 newNode 就是父节点
					}
				}
			}
		}
	}
	
	/**
	 * 处理 父节点原来是 左节点 的 case 1 的情况: 叔叔节点是黑色，插入到左子树中
	 * @param grandfather
	 * @param parent
	 */
	private void leftNodeFix(Node grandfather, Node parent){
		parent.color = BLACK;
		grandfather.color = RED;
		rightRotation(grandfather);
	}
	
	/**
	 * 处理 父节点原来是 右节点 的 case 1 的情况: 叔叔节点是黑色，插入到右子树中
	 * @param grandfather
	 * @param parent
	 */
	private void rightNodeFix(Node grandfather, Node parent){
		parent.color = BLACK;
		grandfather.color = RED;
		leftRotation(grandfather);
	}
	
	/**
	 * 处理 case 3: 叔叔节点是红色
	 * @param newNode
	 */
	private void uncleRedFix(Node newNode){
		Node parent = newNode.parentNode;
		if((null != parent) && (RED == parent.color)){
			Node grandfather = parent.parentNode;
			Node uncle = grandfather.leftNode;
			if(parent == grandfather.leftNode){ 
				uncle = grandfather.rightNode;
			}
			parent.color = BLACK;
			uncle.color = BLACK;
			if(root != grandfather){
				grandfather.color = RED;
				upsertFix(grandfather);
			}
		}
	}
	
	/**
	 * 删除 叶子节点 后的修复过程
	 * @param deletedNode 被删除的节点
	 * @param deletedNodeParent 被删除节点的父节点
	 */
	private void deleteLeafFix(Node deletedNode){
		while((deletedNode != root) && (BLACK == deletedNode.color)){
			Node parent = deletedNode.parentNode;
			Node brother = getBrother(deletedNode);
			if(deletedNode.key.compareTo(parent.key) > 0){ // 删除的是右叶子节点
				if(RED == brother.color){ // case5: 如果该兄弟节点是红色的，那么根据红黑树的特性可以得出它的一定有两个黑色的子节点
					brother.color = BLACK;
					brother.rightNode.color = RED;
					rightRotation(parent);
					break;
				}else{
					if((null == brother.leftNode) && (null == brother.rightNode)){ // case4: 兄弟节点是黑色的，且没有子节点
						brother.color = RED; // 将兄弟节点设为红色，将父节点设为当前节点递归， 直到根节点，或遇到红色节点，
						deletedNode = parent;
					}else{
						if((null != brother.leftNode) && (RED == brother.leftNode.color)){// case1: 兄弟节点是黑色的，且有一个左节点（可以断定 左节点是红色的）
							//case3: 兄弟节点是黑色的，且有两个节点（可以断定 左右节点都是红色的） 这个和情况 1 是一样的
							brother.color = parent.color;
							parent.color = BLACK;
							brother.leftNode.color = BLACK;
							rightRotation(parent);
							break;
						}else{// case2: 兄弟节点是黑色的，且有一个右节点（可以断定 右节点是红色的）
							brother.rightNode.color = BLACK;
							brother.color = RED;
							leftRotation(brother);
						}
					}
				}
			}else{// 删除的是左叶子节点
				if(RED == brother.color){ // case5 : 如果该兄弟节点是红色的，那么根据红黑树的特性可以得出它的一定有两个黑色的子节点
					brother.color = BLACK;
					brother.leftNode.color = RED;
					leftRotation(parent);
					break;
				}else{
					if((null == brother.leftNode) && (null == brother.rightNode)){ // case4: 兄弟节点是黑色的，且没有子节点
						brother.color = RED; // 将兄弟节点设为红色，将父节点设为当前节点递归， 直到根节点，或遇到红色节点，
						deletedNode = parent;
					}else{
						if((null != brother.rightNode) && (RED == brother.rightNode.color)){ // case1 : 兄弟节点是黑色的，且有一个右节点（可以断定 右节点是红色的）
							// case3 : 兄弟节点是黑色的，且有两个节点（可以断定 左右节点都是红色的） 这个和情况 1 是一样的
							brother.color = parent.color;
							parent.color = BLACK;
							brother.rightNode.color = BLACK;
							leftRotation(parent);
							break;
						}else{ // case2: 兄弟节点是黑色的，且有一个左节点（可以断定 左节点是红色的）
							brother.leftNode.color = BLACK;
							brother.color = RED;
							rightRotation(brother);
						}
					}
				}
			}
		}
		
		deletedNode.color = BLACK;
	}
	
	private Node getBrother(Node node){
		if(null == node){
			return null;
		}
		Node parent = node.parentNode;
		if(null == parent){
			return null;
		}
		if(node.key.compareTo(parent.key) > 0){
			return parent.leftNode;
		}else{
			return parent.rightNode;
		}
	}
	
	public boolean delete(K key){
		if(null != key){
			if(null != root){
				return deleteNode(key, root, null);
			}
		}
		return false;
	}
	
	private boolean deleteNode(K key, Node current, Node parent){
		if(null != current){
			if(key.compareTo(current.key) > 0){
				return deleteNode(key, current.rightNode, current);
			}
			if(key.compareTo(current.key) < 0){
				return deleteNode(key, current.leftNode, current);
			}
			if(key.compareTo(current.key) == 0){
				if((null != current.leftNode) && (null != current.rightNode)){ //将要删除的节点下有两个子节点
					dleTwoChildrenNode(current);
					return true;
				}else{
					if((null == current.leftNode) && (null == current.rightNode)){ //将要删除的节点没有子节点
						deleteLeafFix(current);
						if(current.key.compareTo(parent.key) > 0){
							parent.rightNode = null;
						}else{
							parent.leftNode = null;
						}
						return true;
					}else{ // 将要删除的节点下有一个子节点, 
						dleOneChildNode(current);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private void dleOneChildNode(Node delNode){
		Node replaceNode = (null == delNode.leftNode) ? delNode.rightNode : delNode.leftNode;
		deltetLeafNode(delNode, replaceNode);
	}
	
	/**
	 * 处理被删除节点有两个子节点的情况
	 * @param target 将要被删除的节点
	 */
	private void dleTwoChildrenNode(Node target){
		Node replaceNode = successor(target);
		if((null == replaceNode.rightNode) && (null == replaceNode.leftNode)){
			deltetLeafNode(target, replaceNode);
		}else{
			target.key = replaceNode.key;
			target.value = replaceNode.value;
			dleOneChildNode(replaceNode);
		}
	}
	
	private void deltetLeafNode(Node target, Node replaceNode){
		target.key = replaceNode.key;
		target.value = replaceNode.value;
		deleteLeafFix(replaceNode);
		if(replaceNode == replaceNode.parentNode.rightNode){
			replaceNode.parentNode.rightNode = null;
		}else{
			replaceNode.parentNode.leftNode = null;
		}
	}
	
	//找后继结点。即，查找"红黑树中数据值大于该结点"的"最小结点"
	private Node successor(Node node) {
        if (node == null){
        	return null;
        }
        if (null != node.rightNode) { // 获取 后继节点
        	Node p = node.rightNode;
            while (null != p.leftNode){
            	 p = p.leftNode;
            }
            return p;
        } else {
        	Node p = node.parentNode;
        	Node ch = node;
            while (p != null && ch == p.rightNode) {
                ch = p;
                p = p.parentNode;
            }
            return p;
        }
    }
}
