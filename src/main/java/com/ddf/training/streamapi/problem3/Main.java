package com.ddf.training.streamapi.problem3;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> javaVersions = List.of("JDK 1.0", "J2SE 5.0", "Java SE 8", "Java SE 11", "Java SE 14");
        List<Integer> releaseDates = List.of(1996, 1998, 2014, 2018, 2020);
        List<String> javaVersionReleaseDates = zipLists(javaVersions, releaseDates);
        javaVersionReleaseDates.forEach(System.out::println);

        zipTwoLists(javaVersions, releaseDates);
        zip(List.of("Maths", "Chemistry", "Computer Sciences", "English Language"), List.of(18, 14, 18, 16), (subject, mark) -> "I got " + mark + " in " + subject)
                .forEach(System.out::println);
    }

    private static <T1, T2> void zipTwoLists(List<T1> javaVersions, List<T2> releaseDates) {
        zip(javaVersions, releaseDates, (version, year) -> version + " was released in " + year)
                .forEach(System.out::println);
    }

    private static List<String> zipLists(List<String> javaVersions, List<Integer> releaseDates) {
        return IntStream.range(0, javaVersions.size())
                .mapToObj(index -> getReleaseDateStatement(javaVersions.get(index), releaseDates.get(index)))
                .collect(Collectors.toList());
    }

    private static String getReleaseDateStatement(String javaVersion, Integer releaseYear) {
        return javaVersion + " was released in " + releaseYear;
    }

    public static <T1, T2, R> Stream<R> zip(List<T1> list1, List<T2> list2, BiFunction<? super T1, ? super T2, ? extends R> mapper) {
        int size = list1.size();
        if (list1.size() > list2.size()) {
            throw new IllegalArgumentException("List sizes are different");
        }
        return IntStream.range(0, size)
                .mapToObj(index -> mapper.apply(list1.get(index), list2.get(index)));
    }
}
