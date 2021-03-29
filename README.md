# job4j_design
[![codecov](https://codecov.io/gh/Kwistercat/job4j_design/branch/master/graph/badge.svg?token=8UO987SSWI)](https://codecov.io/gh/Kwistercat/job4j_design)
[![Build Status](https://travis-ci.com/Kwistercat/job4j_design.svg?branch=master)](https://travis-ci.com/Kwistercat/job4j_design)


# Репозиторий по курсу job4j.ru.

## Блок 1. Структуры данных и алгоритмы.

+ ##### 1. Итератор.

   + [1.1. Итератор двумерного массива int[][]](./src/main/java/ru/job4j/iterator/MatrixIt.java)
   + [1.2. Итератор четных чисел](./src/main/java/ru/job4j/iterator/EvenNumbersIterator.java)
   + [1.3. Преобразование итератора итераторов](./src/main/java/ru/job4j/iterator/FlatMap.java)
   
+ ##### 2. Обобщение (generic).
   
   + [2.1. Создать параметризованный контейнер](./src/main/java/ru/job4j/generics/SimpleArray.java)
   + [2.2. Реализовать Store (User, Role) на базе контейнера из задания 2.1.](./src/main/java/ru/job4j/generics/service/MemStore.java)

+ ##### 3. List interface.

    + [3.1. Создать динамический список на базе массива](./src/main/java/ru/job4j/collection/SimpleArray.java)
    + [3.2. Создать контейнер на базе связного списка](./src/main/java/ru/job4j/collection/list/SimpleLinkedList.java)
    + [3.3. Используя контейнер на базе связанного списка создать контейнер Stack](./src/main/java/ru/job4j/collection/SimpleStack.java)
    + [3.4. Создать очередь на двух стеках](./src/main/java/ru/job4j/collection/SimpleQueue.java)
    + [3.5. Перевернуть связанный список](./src/main/java/ru/job4j/collection/ForwardLinked.java)
    + [3.6. Использование ListIterator](./src/main/java/ru/job4j/iterator/ListUtils.java)
   
+ ##### 3. Set interface.
    
    + [4.1. Реализовать коллекцию Set на базе массива](./src/main/java/ru/job4j/collection/set/SimpleSet.java)