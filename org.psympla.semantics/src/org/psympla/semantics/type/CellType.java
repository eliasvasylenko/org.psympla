package org.psympla.semantics.type;

import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Sequence;

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
