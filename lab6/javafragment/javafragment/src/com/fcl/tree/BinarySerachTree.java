package com.fcl.tree;
/**
 * @author fang.cai.liang@aliyun.com
 * @date 2016年8月24日 上午11:19:48
 */

import java.util.LinkedHashMap;

/**
 * 二叉搜索树：
 * 	1. 有序，左<中<右子树
 * 	2. 每个节点最多拥有 2 个子节点
 * @param <K>
 * @param <V>
 */
public class BinarySerachTree<K extends Comparable<K>, V> {
	
	private class Node{
		private K key; // 存储的key
		private V value; // 存储的值
		private Node leftNode;  // 左节点
		private Node rightNode; // 右节点
		
		public Node(K key, V value, Node leftNode, Node rightNode) {
			super();
			this.key = key;
			this.value = value;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
		
		@Override
		public String toString(){
			return "{\"key\":" + this.key + ", \"value\":" + "\"" + this.value + "\"" + ", \"leftNode\":" + this.leftNode + ", \"rightNode\":" + this.rightNode + "}";
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
	 * 插入新的节点，如果指定的key已经存在，则更新原来的值
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		Node newNode = new Node(key, value, null, null);
		if(null == root){
			root = newNode;
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
	
	public Node getMinNode(Node node){
		if(null != node){
			while(null != node.leftNode){
				node = node.leftNode;
			}
		}
		return node;
	}
	
	/**
	 * 获取二叉搜索树中的最小节点
	 * @return
	 */
	public Node getMinNode(){
		return getMinNode(root);
	} 
	
	/**
	 * 需要考虑以下3中情况
	 * 1. 将要删除的节点没有子节点 ----> 直接删除
	 * 2. 将要删除的节点下有一个子节点 -----> 将要被删除的节点的子节点挂靠到将要被删除的节点的父节点上即可
	 * 3. 将要删除的节点下有两个子节点 ----> 在将要被删除的节点的右子树中找到一个最小节点然后，用找到的最小节点与需要删除的节点替换。最后再将最小节点进行删除
	 * @param key
	 * @return
	 */
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
						if(current.key.compareTo(parent.key) > 0){
							parent.rightNode = null;
						}else{
							parent.leftNode = null;
						}
						return true;
					}else{ // 将要删除的节点下有一个子节点, 将要被删除的节点的子节点挂靠到将要被删除的节点的父节点上即可
						Node childNode = (null == current.leftNode) ? current.rightNode : current.leftNode;
						if(current.key.compareTo(parent.key) > 0){
							parent.rightNode = childNode;
						}else{
							parent.leftNode = childNode;
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 处理被删除节点有两个子节点的情况
	 * @param parent 将要被删除的节点
	 */
	private void dleTwoChildrenNode(Node parent){
		Node parentRight = parent.rightNode;
		Node tmp = parentRight.leftNode;
		if(null == tmp){
			parent.value = parentRight.value;
			parent.key = parentRight.key;
			parent.rightNode = parentRight.rightNode;
		}else{
			Node tmpParent = parentRight;
			while(null != tmp.leftNode){
				tmpParent = tmp;
				tmp = tmp.leftNode;
			}
			parent.value = tmp.value;
			parent.key = tmp.key;
			tmpParent.leftNode = tmp.rightNode;
		}
	}
}
