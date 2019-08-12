package org.topiello.derivationtree;

public class Extent {
  private final int left;
  private final int right;

  public Extent(int left, int right) {
    this.left = left;
    this.right = right;
  }

  public int left() {
    return left;
  }

  public int right() {
    return right;
  }
}
