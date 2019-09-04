package org.topiello.scanner.bytes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BlockScannerTests {
  @Mock
  Block block;

  @Test
  void whenBlockScannerIsConstructed_shouldGetReadBufferFromBlock_thenAcquireReferenceOnBlock() {
    new BlockScanner(block);

    var inOrder = Mockito.inOrder(block);
    inOrder.verify(block).getReadBuffer();
    inOrder.verify(block).acquire();
    inOrder.verifyNoMoreInteractions();
  }
}
