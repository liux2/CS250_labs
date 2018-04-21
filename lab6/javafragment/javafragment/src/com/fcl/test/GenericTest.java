package com.fcl.test;

import java.util.ArrayList;
import java.util.List;

//public class GenericTest {
//
//    public static void main(String[] args) {
//        List list = new ArrayList();
//        list.add("qqyumidi");
//        list.add("corn");
//        list.add(100);
//        list.add(new int[]{1,2});
//
//        for (int i = 0; i < list.size(); i++) {
//            String name = (String) list.get(i); // 我们没有在 List 加上类型限制所以可以存放任意类型，
//            System.out.println("name:" + name);
//        }
//    }
//}

public class GenericTest {

    public static void main(String[] args) {
    	List<String> strlist = new ArrayList<String>();
    	strlist.add("aa");
    	strlist.add("bb");
    	strlist.add("cc");
    	List<?> list = strlist;
    	// list.add("ddd"); // 在Java集合框架中，对于参数值是未知类型的容器类，只能读取其中元素，不能向其中添加元素， 因为，其类型是未知，所以编译器无法识别添加元素的类型和容器的类型是否兼容，唯一的例外是NULL

        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); // 我们没有在 List 加上类型限制所以可以存放任意类型，
            System.out.println("name:" + name);
        }
    }
}