package com.gudigudigudi.sb;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Jdk8 {

    public static void main(String[] args) {

//        stream_forEach();
//        stream_distinct();
//        stream_filter();
//        stream_sorted();
//    stream_map();
//        stream_flatMap();
        stream_reduce();
    }

    // 遍历输出
    private static void stream_forEach() {
        System.out.println("Stream method: forEach");
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.forEach(str -> System.out.println(str));
    }

    // 过滤
    private static void stream_filter() {
        System.out.println("Stream method: filter");
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.filter(str -> str.length() == 3)
                .forEach(str -> System.out.println(str));
    }

    // 去重
    private static void stream_distinct() {
        System.out.println("Stream method: distinct");
        Stream<String> stream = Stream.of("I", "love", "you", "too", "too");
        stream.distinct()
                .forEach(str -> System.out.println(str));
    }

    // 排序
    private static void stream_sorted() {
        System.out.println("Stream method: sorted");
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.sorted((str1, str2) -> str1.length() - str2.length())
                .forEach(str -> System.out.println(str));
    }

    // 映射
    private static void stream_map() {
        System.out.println("Stream method: map");
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.map(str -> str.toUpperCase())
                .forEach(str -> System.out.println(str));
    }

    // 摊平映射
    private static void stream_flatMap() {
        System.out.println("Stream method: flatMap");
        Stream<List<Integer>> stream=Stream.of(Arrays.asList(1,2),Arrays.asList(3,4,5));
        stream.flatMap(list->list.stream())
                .forEach(item->System.out.println(item));
    }

    // 从一组元素中生成一个值:sum,max,min,count
    private static void stream_reduce(){
        System.out.println("Stream method: reduce");
        Stream<String> stream = Stream.of("I", "love", "you", "too");
//        Optional<String> longest=stream.reduce((s1,s2)->s1.length()>=s2.length()?s1:s2);
        Optional<String> longest=stream.max((s1,s2)->s1.length()-s2.length());
        System.out.println(longest.get());
    }
}
