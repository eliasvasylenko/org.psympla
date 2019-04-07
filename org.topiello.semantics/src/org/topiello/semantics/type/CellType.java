package org.topiello.semantics.type;

import org.topiello.symbol.Cell;
import org.topiello.symbol.LexicalItem;
import org.topiello.symbol.Sequence;

public class CellType<T extends LexicalItem, U extends Sequence> implements SymbolType<Cell<T, U>> {
  private final SymbolType<U> carType;
  private final SymbolType<U> cdrType;

  public CellType(SymbolType<U> carType, SymbolType<U> cdrType) {
    this.carType = carType;
    this.cdrType = cdrType;
  }

  public SymbolType<U> carType() {
    return carType;
  }

  public SymbolType<U> cdrType() {
    return cdrType;
  }
}
