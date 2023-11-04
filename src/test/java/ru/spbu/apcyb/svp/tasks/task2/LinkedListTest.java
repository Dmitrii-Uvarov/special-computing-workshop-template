package ru.spbu.apcyb.svp.tasks.task2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkedListTest {

  @Test
  void addObjectTest() {
    List<Integer> list = new LinkedList<>();
    list.add(5);
    Assertions.assertArrayEquals(new Integer[]{5}, list.toArray());
  }

  @Test
  void addSeveralObjectsTest() {
    List<Integer> list = new LinkedList<>();
    list.add(0, 5);
    list.add(0, 0);
    list.add(1, 7);
    Assertions.assertArrayEquals(new Object[]{0, 7, 5}, list.toArray());
  }

  @Test
  void addExceptionTest() {
    List<Integer> list = new LinkedList<>();
    list.add(10);
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.add(2, 6));
  }


  @Test
  void sizeEmptyTest() {
    List<Integer> list = new LinkedList<>();
    Assertions.assertEquals(0, list.size());
  }

  @Test
  void multipleSizeTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    list.add(0, "temp");
    list.remove("string");
    Assertions.assertEquals(1, list.size());
  }

  @Test
  void emptyListTest() {
    List<Integer> list = new LinkedList<>();
    Assertions.assertTrue(list.isEmpty());
  }

  @Test
  void notEmptyListTest() {
    List<Integer> list = new LinkedList<>();
    list.add(5);
    Assertions.assertFalse(list.isEmpty());
  }

  @Test
  void removeObjectTest() {
    List<Integer> list = new LinkedList<>();
    list.add(10);
    list.add(5);
    list.add(1);
    list.remove(Integer.valueOf(5));
    Assertions.assertArrayEquals(new Object[]{10, 1}, list.toArray());
  }

  @Test
  void removeIndexTest() {
    List<Integer> list = new LinkedList<>();
    list.add(5);
    int element = list.remove(0);

    assertAll(
        () -> Assertions.assertTrue(list.isEmpty()),
        () -> Assertions.assertEquals(5, element)
    );
  }


  @Test
  void removeSeveralTest() {
    List<String> list = new LinkedList<>();
    list.add("zero");
    list.add("one");
    list.remove(0);
    Assertions.assertEquals("one", list.get(0));
  }

  @Test
  void removeFromEmptyListTest() {
    List<Object> list = new LinkedList<>();
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.remove(0));
  }

  @Test
  void removeFalseTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    Assertions.assertFalse(list.remove("temp"));
  }

  @Test
  void getTest() {
    List<Integer> list = new LinkedList<>();
    list.add(5);
    list.add(69);
    list.add(1984);
    Assertions.assertEquals(69, list.get(1));
  }

  @Test
  void getExceptionTest() {
    List<Integer> list = new LinkedList<>();
    list.add(5);
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.get(2));
  }

  @Test
  void setTest() {
    List<Integer> list = new LinkedList<>();
    list.add(5);
    list.add(0);
    list.add(7);

    int element = list.set(1,9);

    Assertions.assertAll(
        () -> Assertions.assertEquals(0,element),
        () -> Assertions.assertArrayEquals(new Object[]{5, 9, 7}, list.toArray())
    );
  }

  @Test
  void setExceptionTest() {
    LinkedList<String> list = new LinkedList<>();

    list.add("Python");

    assertThrows(IndexOutOfBoundsException.class, () -> list.set(1,"Java"));
  }

  @Test
  void indexOfTrueTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    list.add("notstring");
    Assertions.assertEquals(0, list.indexOf("string"));
  }

  @Test
  void indexOfFalseTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    Assertions.assertEquals(-1, list.indexOf(10));
  }

  @Test
  void indexOfFirstOccuranceTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    list.add("number");
    list.add("number");
    Assertions.assertEquals(1, list.indexOf("number"));
  }

  @Test
  void lastIndexOfTrueTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    list.add("string");
    list.add("num");
    list.add("string");
    Assertions.assertEquals(3, list.lastIndexOf("string"));
  }

  @Test
  void lastIndexOfFalseTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    list.add("string");
    list.add("string");
    Assertions.assertEquals(-1, list.lastIndexOf(10));
  }

  @Test
  void lastIndexOfLastOccuranceTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    list.add("number");
    list.add("number");
    Assertions.assertEquals(2, list.lastIndexOf("number"));
  }

  @Test
  void containsTrueTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    list.add("number");
    Assertions.assertTrue(list.contains("string"));
  }

  @Test
  void containsFalseTest() {
    List<String> list = new LinkedList<>();
    list.add("string");
    list.add("number");
    Assertions.assertFalse(list.contains("object"));
  }
  @Test
  void clearExceptionTest() {
    List<String> list = new LinkedList<>();
    assertThrows(UnsupportedOperationException.class, list::clear);
  }

  @Test
  void iteratorExceptionTest() {
    List<String> list = new LinkedList<>();
    assertThrows(UnsupportedOperationException.class, list::iterator);
  }

  @Test
  void listIteratorExceptionTest() {
    List<String> list = new LinkedList<>();
    assertThrows(UnsupportedOperationException.class, list::listIterator);
  }

  @Test
  void sublistExceptionTest() {
    List<String> list = new LinkedList<>();
    assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 2));
  }
}
