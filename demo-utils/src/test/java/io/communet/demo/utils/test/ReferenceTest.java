package io.communet.demo.utils.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * <p>function: String 不可变，
 * <p>User: LeeJohn
 * <p>Date: 2018/09/27
 * <p>Version: 1.0
 */
public class ReferenceTest {

    //不可变的String
    public static String appendStr(String s) {
        s += "bbb";
        return s;
    }

    //可变的StringBuilder
    public static StringBuilder appendSb(StringBuilder sb) {
        return sb.append("bbb");
    }

    @Test
    public void testString2(){
        String s = new String("aaa");
        String ns = ReferenceTest.appendStr(s);
        System.out.println("String aaa>>>" + s.toString());
        //StringBuilder做参数
        StringBuilder sb = new StringBuilder("aaa");
        StringBuilder nsb = ReferenceTest.appendSb(sb);
        System.out.println("StringBuilder aaa >>>" + sb.toString());
        //String aaa>>>aaa
        //StringBuilder aaa >>>aaabbb

        // nsb 指向 sb, nsb改变导致 sb也会改变
    }


    @Test
    public void testString(){
        String s1 = "abc";
        String s2 = s1 ;
        s2 = "12341";
        System.out.println("s1 : " + s1 );
        System.out.println("s2 : " + s2 );
//        s1 : abc
//        s2 : 12341
        //s1并未改变
        // s2指向s1，s2改变并不会引起s1改变，因为String是不可变的
    }

    @Test
    public void testUser(){
        User user1 = new User("Lee",18,"man");
        User user2 = user1;

        user2.setName("John");

        System.out.println("user1 : " + user1);
        System.out.println("user2 : " + user2);

        //user1 : ReferenceTest.User(name=John, age=18, sex=man)
        //user2 : ReferenceTest.User(name=John, age=18, sex=man)
        // user1,user2都已改变
        // user2 指向user1，user2改变导致user1改变
    }


    @Data
    @AllArgsConstructor
    class User {
        private String name ;
        private int age;
        private String sex;
        public User(){

        }
    }
}
