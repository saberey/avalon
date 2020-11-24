package com.avalon.ms.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saberey
 * @version 1.0
 * @description
 * @date 2020/11/12 11:52
 */
public class ListTest {

    Logger logger = LoggerFactory.getLogger(ListTest.class);

    static class ListContentTest{
        String name;
        int id;

        public ListContentTest(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String toString() {
            return "ListContentTest{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public void test(){
        List<String> list = new ArrayList(){{
            add(new ListContentTest("test1", 1));
            add(new ListContentTest("test2", 2));
            add(new ListContentTest("test3", 3));
            add(new ListContentTest("test4", 4));
            add(new ListContentTest("test5", 5));
        }};
        logger.info("content:{}" , list);
    }

    public static void main(String[] args) {
        new ListTest().test();
    }
}
