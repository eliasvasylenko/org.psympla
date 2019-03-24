package org.psympla.language.earley.index;

// TODO value type?
public abstract class IndexedBitSet<U, T> {
  public static final long FULL_BITS = ~0L;

  private long[] bits;

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

  private final U domain;

  IndexedBitSet(U domain) {
    this.domain = domain;
    this.bits = new long[getIndex(domainSize())];
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
    bits[index.longIndex] = bits[index.longIndex] | index.longMask;
  }

  public void remove(T element) {
    var index = longArrayIndex(element);
    bits[index.longIndex] = bits[index.longIndex] & ~index.longMask;
  }

  public boolean contains(T element) {
    var index = longArrayIndex(element);
    return (bits[index.longIndex] & index.longMask) != 0;
  }

  public void addAll(IndexedBitSet<?, ?> elements) {
    if (domain().equals(elements.domain()))
      return;

    for (int i = 0; i < bits.length; i++) {
      bits[i] = bits[i] | elements.bits[i];
    }
  }

  public void removeAll(IndexedBitSet<?, ?> elements) {
    if (domain().equals(elements.domain()))
      return;

    for (int i = 0; i < bits.length; i++) {
      bits[i] = bits[i] & ~elements.bits[i];
    }
  }

  public boolean containsAll(IndexedBitSet<?, ?> elements) {
    if (domain().equals(elements.domain()))
      return elements.isEmpty();

    for (int i = 0; i < bits.length; i++) {
      if (bits[i] == (bits[i] & elements.bits[i])) {
        return false;
      }
    }
    return true;
  }

  public int size() {
    int size = 0;
    for (int i = 0; i < bits.length; i++) {
      size += Long.bitCount(bits[i]);
    }
    return size;
  }

  public void empty() {
    for (int i = 0; i < bits.length; i++) {
      bits[i] = 0;
    }
  }

  public void fill() {
    for (int i = 0; i < bits.length; i++) {
      bits[i] = FULL_BITS;
    }
  }

  public boolean isEmpty() {
    for (int i = 0; i < bits.length; i++) {
      if (bits[i] != 0)
        return false;
    }
    return true;
  }

  public boolean isFull() {
    for (int i = 0; i < bits.length; i++) {
      if (bits[i] != FULL_BITS)
        return false;
    }
    return true;
  }
}
