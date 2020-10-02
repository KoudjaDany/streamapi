package com.ddf.training.streamapi.problem2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        List<String> list = List.of("Good", "Old", "Stream", "API");
        List<IndexedValue<String>> indexedValueList = getIndexedForm(list);
        indexedValueList.forEach(System.out::println);
    }

    private static List<IndexedValue<String>> getIndexedForm(List<String> list) {
        return IntStream.range(0, list.size())
                .mapToObj(index -> new IndexedValue<>(index, list.get(index)))
                .collect(Collectors.toList());
    }
}
