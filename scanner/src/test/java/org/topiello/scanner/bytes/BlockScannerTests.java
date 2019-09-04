package org.topiello.scanner.bytes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BlockScannerTests {
  @Mock
  Block block;

  @Mock
  ByteBuffer buffer;

  @Mock
  ByteBuffer nextBuffer;

  @Test
  void whenConstructed_shouldGetReadBufferFromBlock_andAcquireReferenceOnBlock() {
    var scanner = new BlockScanner(block);

    assertEquals(block, scanner.block());

    var inOrder = Mockito.inOrder(block);
    inOrder.verify(block).getReadBuffer();
    inOrder.verify(block).acquire();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenConstructed_shouldBeOpen() {
    var scanner = new BlockScanner(block);

    assertTrue(scanner.isOpen());
  }

  @Test
  void whenBranched_shouldCreateNewBlockScanner_andAcquireReferenceOnBlock() {
    var scanner = new BlockScanner(block);
    var branch = scanner.branch();

    assertNotEquals(scanner, branch);

    var inOrder = Mockito.inOrder(block);
    inOrder.verify(block).getReadBuffer();
    inOrder.verify(block, times(2)).acquire();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenBranched_shouldDuplicateBufferFromOriginal_givenBufferIsAllocated() {
    when(block.getReadBuffer()).thenReturn(buffer);
    when(buffer.duplicate()).thenReturn(nextBuffer);

    var scanner = new BlockScanner(block);
    var branch = (BlockScanner) scanner.branch();

    assertNotEquals(scanner, branch);

    assertEquals(buffer, scanner.buffer());
    assertEquals(nextBuffer, branch.buffer());
  }

  @Test
  void whenClosed_shouldReleaseBlockAndNullBlockAndBuffer_givenNotClosed() {
    when(block.getReadBuffer()).thenReturn(buffer);

    var scanner = new BlockScanner(block);
    scanner.close();

    assertNull(scanner.buffer());
    assertNull(scanner.block());
    assertFalse(scanner.isOpen());

    var inOrder = Mockito.inOrder(block);
    inOrder.verify(block).getReadBuffer();
    inOrder.verify(block).acquire();
    inOrder.verify(block).release();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenClosed_shouldDoNothing_givenAlreadyClosed() {
    when(block.getReadBuffer()).thenReturn(buffer);

    var scanner = new BlockScanner(block);
    scanner.close();
    scanner.close();

    assertFalse(scanner.isOpen());

    var inOrder = Mockito.inOrder(block);
    inOrder.verify(block).getReadBuffer();
    inOrder.verify(block).acquire();
    inOrder.verify(block).release();
    inOrder.verifyNoMoreInteractions();
  }
}
