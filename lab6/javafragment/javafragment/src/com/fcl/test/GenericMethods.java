package com.fcl.test;

import java.util.Arrays;

/**
 * @author fang.cai.liang@aliyun.com
 * @date 2016年8月28日 下午9:14:15
 */

public class GenericMethods {

	public static void main(String[] args) {
		GenericMethods gm = new GenericMethods();
		
		GenericMethods.getType("");
		GenericMethods.getType(1.1);
		GenericMethods.getType(2);
		GenericMethods.getType(gm);
		
		System.out.println(GenericMethods.getType2(gm, 1));
		
		GenericMethods.getType(GenericMethods.returnType(""));
		
		
		int[] array = {1, 2, 3};
		Arrays.asList();
	}
	
//	public static void getType(Object obj){
//		System.out.println(obj.getClass().getName());
//	}
	
	public static <T> void getType(T obj){
		System.out.println(obj.getClass().getName());
	}
	
	public static <T, S> String getType2(T obj, S s){
		return obj.getClass().getName() + s.getClass().getName();
	}
	
	public static <T> T returnType(T t){
		return t;
	}

}
