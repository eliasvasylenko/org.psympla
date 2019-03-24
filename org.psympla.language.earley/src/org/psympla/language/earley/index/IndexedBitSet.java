package org.psympla.language.earley.index;

import java.util.Arrays;
import java.util.Objects;

// TODO value type?
public abstract class IndexedBitSet<U, T> {
  public static final long FULL_BITS = ~0L;

  // TODO value type
  private static class LongArrayIndex {
    final int longIndex;
    final long longMask;

    public LongArrayIndex(int index) {
      this.longIndex = getIndex(index);
      this.longMask = getMask(index);
    }
  }

  private static long getMask(int index) {
    return 1l << (long) index % Long.SIZE;
  }

  private static int getIndex(int index) {
    return ((index - 1) / Long.SIZE) + 1;
  }

  private long[] bits;
  private final U domain;

  IndexedBitSet(U domain) {
    this.domain = domain;
    this.bits = null;
  }

  private long[] initializeBits() {
    if (bits == null) {
      bits = new long[domainSize()];
    }
    return bits;
  }

  public U domain() {
    return domain;
  }

  protected abstract int domainSize(U domain);

  public int domainSize() {
    return domainSize(domain());
  }

  protected abstract T get(U domain, int index);

  public T get(int index) {
    return get(domain(), index);
  }

  protected abstract int indexOf(U domain, T element);

  public int indexOf(T element) {
    return indexOf(domain(), element);
  }

  private LongArrayIndex longArrayIndex(T element) {
    return new LongArrayIndex(indexOf(element));
  }

  public void add(T element) {
    var index = longArrayIndex(element);
    initializeBits();

    bits[index.longIndex] = bits[index.longIndex] | index.longMask;
  }

  public void remove(T element) {
    if (bits == null) {
      return;
    }

    var index = longArrayIndex(element);
    bits[index.longIndex] = bits[index.longIndex] & ~index.longMask;
  }

  public boolean contains(T element) {
    if (bits == null) {
      return false;
    }

    var index = longArrayIndex(element);
    return (bits[index.longIndex] & index.longMask) != 0;
  }

  public void addAll(IndexedBitSet<?, ?> elements) {
    if (!isCompatible(elements)) {
      return;
    }
    initializeBits();

    for (int i = 0; i < bits.length; i++) {
      bits[i] = bits[i] | elements.bits[i];
    }
  }

  public void removeAll(IndexedBitSet<?, ?> elements) {
    if (!isCompatible(elements) || bits == null) {
      return;
    }

    for (int i = 0; i < bits.length; i++) {
      bits[i] = bits[i] & ~elements.bits[i];
    }
  }

  public boolean containsAll(IndexedBitSet<?, ?> elements) {
    if (!isCompatible(elements) || bits == null) {
      return elements.isEmpty();
    }

    for (int i = 0; i < bits.length; i++) {
      if (bits[i] == (bits[i] & elements.bits[i])) {
        return false;
      }
    }
    return true;
  }

  private boolean isCompatible(IndexedBitSet<?, ?> elements) {
    return domain.equals(elements.domain) && getClass().equals(elements.getClass());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || getClass().equals(obj.getClass())) {
      return false;
    }

    var that = (IndexedBitSet<?, ?>) obj;

    return Objects.equals(this.domain, that.domain) && Arrays.equals(this.bits, that.bits);
  }

  @Override
  public int hashCode() {
    return Objects.hash(domain, bits);
  }

  public int size() {
    if (bits == null) {
      return 0;
    }

    int size = 0;
    for (int i = 0; i < bits.length; i++) {
      size += Long.bitCount(bits[i]);
    }
    return size;
  }

  public void empty() {
    bits = null;
  }

  public void fill() {
    initializeBits();

    for (int i = 0; i < bits.length; i++) {
      bits[i] = FULL_BITS;
    }
  }

  public boolean isEmpty() {
    if (bits == null) {
      return true;
    }

    for (int i = 0; i < bits.length; i++) {
      if (bits[i] != 0) {
        return false;
      }
    }
    bits = null;
    return true;
  }

  public boolean isFull() {
    if (bits == null) {
      return false;
    }

    for (int i = 0; i < bits.length; i++) {
      if (bits[i] != FULL_BITS)
        return false;
    }
    return true;
  }
}
