package com.fcl.test;

import java.util.Arrays;

import com.fcl.tree.BinarySerachTree;

/**
 * @author fang.cai.liang@aliyun.com
 * @date 2016年8月30日 下午9:15:10
 */

public class Tree {

	public static void main(String[] args) {

		BinarySerachTree<Integer, String> bst = new BinarySerachTree<Integer, String>();
		
		bst.put(100, "v100");
		bst.put(50, "v50");
		bst.put(150, "v150");
		bst.put(20, "v20");
		bst.put(85, "v85");
		bst.put(10, "v10");
		bst.put(15, "a15");
		bst.put(75, "v75");
		bst.put(95, "v95");
		bst.put(65, "v65");
		bst.put(76, "v76");
		bst.put(60, "v60");
		bst.put(66, "v66");
		bst.put(61, "v61");
		
//		Object[] keys;
//		keys = bst.inorderTraversal().keySet().toArray();
//		System.out.println(Arrays.toString(keys));
//		System.out.println(bst.delete(50));
		System.out.println(bst.get(100));
		
		//System.out.println(Arrays.toString(bst.inorderTraversal().keySet().toArray()));
		
	}
	


}
