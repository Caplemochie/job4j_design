package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index + 1) {
                i.add(value);
                return;
            }
            i.next();
        }
        i.add(value);

    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        Iterator<T> listIterator = list.iterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        int index = 0;
        for (T val : list) {
            if (filter.test(val)) {
                list.set(index, value);
            }
            index++;
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {

        for (ListIterator<T> elemIt = elements.listIterator(); elemIt.hasNext();) {
            T elem = elemIt.next();
            if (list.contains(elem)) {
                list.remove(elem);
            }
        }


    }
}