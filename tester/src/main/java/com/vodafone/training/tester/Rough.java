package com.vodafone.training.tester;

import com.vodafone.training.tester.domainObjects.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Rough {

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        List<Integer> integers = List.of(1,2,3,4,5,6,7,8);

      Integer integer=  integers.stream().reduce(1,(subResult,element)->subResult+element);


      integers.stream().sorted(Comparator.comparing(Integer::floatValue));

        System.out.println(integer);
    }


}
