package ru.spbu.apcyb.svp.tasks.task2;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkedQueueTest {


  @Test
  void addObjectTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    queue.add("string");
    Assertions.assertEquals("string", queue.peek());
  }

  @Test
  void sizeEmptyTest() {
    LinkedQueue<Object> queue = new LinkedQueue<>();
    Assertions.assertEquals(0, queue.size());
  }

  @Test
  void sizeSeveralTest() {
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.add(5);
    queue.remove();
    Assertions.assertEquals(0, queue.size());
  }

  @Test
  void pollTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    queue.add("string");
    queue.add("notstring");
    Object element = queue.poll();
    Assertions.assertAll(
        () -> Assertions.assertEquals("string", element),
        () -> Assertions.assertArrayEquals(new Object[]{"notstring"}, queue.toArray())
    );
  }

  @Test
  void pollNullTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    Assertions.assertNull(queue.poll());
  }

  @Test
  void elementTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    queue.add("A");
    queue.add("B");
    queue.add("C");
    Object element = queue.element();
    Assertions.assertAll(
        () -> Assertions.assertEquals("A", element),
        () -> Assertions.assertArrayEquals(new Object[]{"A","B","C"}, queue.toArray())
    );
  }

  @Test
  void elementExceptionTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    Assertions.assertThrows(NoSuchElementException.class, queue::element);
  }

  @Test
  void removeTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    queue.add("A");
    queue.add("B");
    Object element = queue.remove();
    Assertions.assertAll(
        () -> Assertions.assertEquals("A", element),
        () -> Assertions.assertArrayEquals(new Object[]{"B"}, queue.toArray())
    );
  }

  @Test
  void removeExceptionTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    Assertions.assertThrows(NoSuchElementException.class, queue::remove);
  }

  @Test
  void peekTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    queue.add("A");
    queue.add("B");
    Object element = queue.peek();
    Assertions.assertAll(
        () -> Assertions.assertEquals("A", element),
        () -> Assertions.assertArrayEquals(new Object[]{"A", "B"}, queue.toArray())
    );
  }

  @Test
  void peekNullTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    Assertions.assertNull(queue.peek());
  }

  @Test
  void isEmptyTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    assertTrue(queue.isEmpty());
  }

  @Test
  void isEmptyFalseTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    queue.add("A");
    assertFalse(queue.isEmpty());
  }

  @Test
  void clearTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    Assertions.assertThrows(UnsupportedOperationException.class, queue::clear);
  }

  @Test
  void offerTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    Assertions.assertThrows(UnsupportedOperationException.class, () -> queue.offer("A"));
  }

  @Test
  void iteratorTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    Assertions.assertThrows(UnsupportedOperationException.class, queue::iterator);
  }

  @Test
  void containsTest() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    Assertions.assertThrows(UnsupportedOperationException.class, () -> queue.contains("A"));
  }
}