package ru.spbu.apcyb.svp.tasks.task2;


/**
 * Doubly-linked list implementation of the List interface
 */
public class LinkedList<E> implements java.util.List<E> {

  private int size = 0;

  private Node<E> first;

  private Node<E> last;

  private static final String NOT_IMPLEMENTED_MSG = "method is not implemented yet";

  private static class Node<E> {

    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }

  /**
   * Links e as last element.
   */
  private void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null) {
      first = newNode;
    } else {
      l.next = newNode;
    }
    size++;
  }

  /**
   * Inserts element e before non-null Node succ.
   */
  private void linkBefore(E e, Node<E> succ) {
    final Node<E> pred = succ.prev;
    final Node<E> newNode = new Node<>(pred, e, succ);
    succ.prev = newNode;
    if (pred == null) {
      first = newNode;
    } else {
      pred.next = newNode;
    }
    size++;
  }

  /**
   * Unlinks non-null node x.
   */
  private E unlink(Node<E> x) {
    final E element = x.item;
    final Node<E> next = x.next;
    final Node<E> prev = x.prev;

    if (prev == null) {
      first = next;
    } else {
      prev.next = next;
    }

    if (next == null) {
      last = prev;
    } else {
      next.prev = prev;
    }

    size--;
    return element;
  }

  /**
   * Returns the (non-null) Node at the specified element index.
   */
  private Node<E> node(int index) {
    Node<E> x;
    if (index < (size >> 1)) {
      x = first;
      for (int i = 0; i < index; i++) {
        x = x.next;
      }
    } else {
      x = last;
      for (int i = size - 1; i > index; i--) {
        x = x.prev;
      }
    }
    return x;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  @Override
  public java.util.Iterator<E> iterator() {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public Object[] toArray() {
    Object[] result = new Object[this.size()];
    int i = 0;
    for (Node<E> x = first; x != null; x = x.next) {
      result[i] = x.item;
      i++;
    }
    return result;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public boolean add(E e) {
    linkLast(e);
    return true;
  }

  @Override
  public boolean remove(Object o) {
    if (o == null) {
      for (Node<E> x = first; x != null; x = x.next) {
        if (x.item == null) {
          unlink(x);
          return true;
        }
      }
    } else {
      for (Node<E> x = first; x != null; x = x.next) {
        if (o.equals(x.item)) {
          unlink(x);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(java.util.Collection<?> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public boolean addAll(java.util.Collection<? extends E> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public boolean addAll(int index, java.util.Collection<? extends E> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public boolean removeAll(java.util.Collection<?> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public boolean retainAll(java.util.Collection<?> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public E get(int index) {
    checkElementIndex(index);
    return node(index).item;
  }

  @Override
  public E set(int index, E element) {
    checkElementIndex(index);
    Node<E> x = node(index);
    E oldVal = x.item;
    x.item = element;
    return oldVal;
  }

  @Override
  public void add(int index, E element) {
    checkPositionIndex(index);

    if (index == size) {
      linkLast(element);
    } else {
      linkBefore(element, node(index));
    }
  }

  @Override
  public E remove(int index) {
    checkElementIndex(index);
    return unlink(node(index));
  }

  @Override
  public int indexOf(Object o) {
    int index = 0;
    if (o == null) {
      for (Node<E> x = first; x != null; x = x.next) {
        if (x.item == null) {
          return index;
        }
        index++;
      }
    } else {
      for (Node<E> x = first; x != null; x = x.next) {
        if (o.equals(x.item)) {
          return index;
        }
        index++;
      }
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {
    int index = size;
    if (o == null) {
      for (Node<E> x = last; x != null; x = x.prev) {
        index--;
        if (x.item == null) {
          return index;
        }
      }
    } else {
      for (Node<E> x = last; x != null; x = x.prev) {
        index--;
        if (o.equals(x.item)) {
          return index;
        }
      }
    }
    return -1;
  }

  @Override
  public java.util.ListIterator<E> listIterator() {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public java.util.ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  @Override
  public java.util.List<E> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED_MSG);
  }

  private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
  }

  /**
   * Tells if the argument is the index of a valid position for an iterator or an add operation.
   */
  private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;
  }

  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }

  private void checkElementIndex(int index) {
    if (!isElementIndex(index)) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
  }

  private void checkPositionIndex(int index) {
    if (!isPositionIndex(index)) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
  }
}
