package com.demo.phy.phybasedemo.utils;

import com.demo.phy.phybasedemo.data.bean.PopTypeBean;
import com.demo.phy.phybasedemo.data.bean.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by phy on 2019/12/12
 */
public class Test {

    private static Test test = new Test();

    private Test(){
    }

    public static Test getTest(){
        return test;
    }

    public static PopTypeBean user = new PopTypeBean("",1);



    public static void main(String[] args){
//        test1();
//        test2();
    }

    public static void test1(){
        System.out.print("输入数字");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        System.out.print(IntToHex(i));
    }
    public static String IntToHex(Integer integer){
        return Integer.toHexString(integer);
    }

    public static void test2(){
        List<Map<Integer,String>> list = new ArrayList<>();
        Map map1 = new HashMap<Integer, String>();
        map1.put(1,"7");
        map1.put(2,"2");
        Map map2 = new HashMap<Integer, String>();
        map2.put(1,"4");
        map2.put(2,"g");
        Map map3 = new HashMap<Integer, String>();
        map3.put(1,"11");
        map3.put(2,"1");

        list.add(map1);
        list.add(map2);
        list.add(map3);

        Collections.sort(list, new Comparator<Map<Integer, String>>() {
            @Override
            public int compare(Map<Integer, String> integerStringMap, Map<Integer, String> t1) {
                Integer i = Integer.valueOf(integerStringMap.get(1));
                Integer j = Integer.valueOf(t1.get(1));
                return i.compareTo(j);
            }
        });

        System.out.print(list.toString());
    }


}
