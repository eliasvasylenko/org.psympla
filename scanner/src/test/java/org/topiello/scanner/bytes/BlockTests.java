package org.topiello.scanner.bytes;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.calls;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.nio.ByteBuffer;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Calls;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.topiello.scanner.bytes.concurrent.ScannerFailedException;

@ExtendWith(MockitoExtension.class)
public class BlockTests {
  @Mock
  BlockContext context;

  @Mock
  Runnable marker;

  @Mock
  ByteBuffer buffer;
  @Mock
  ByteBuffer readOnlyBuffer;

  @TestFactory
  Stream<DynamicTest> shouldCallReleaseOnContext_whenReleaseCountReachesAcquireCount_givenThereAreNoSubsequentBlocks() {
    return range(1, 10).mapToObj(i -> dynamicTest("Count of " + i, () -> {
      var block = new Block(context);

      for (int j = 0; j < i; j++) {
        marker.run();
        block.acquire();
      }

      for (int j = 0; j < i; j++) {
        marker.run();
        block.release();
      }

      marker.run();

      var inOrder = Mockito.inOrder(context, marker);
      inOrder.verify(context).open(block);
      inOrder.verify(marker, calls(i * 2)).run();
      inOrder.verify(context).release(block);
      inOrder.verify(marker, calls(1)).run();
      inOrder.verifyNoMoreInteractions();
    }));
  }

  @Test
  void shouldCallCloseOnContext_whenInitialBlockIsReleased_givenThereAreNoSubsequentBlocks() {
    marker.run();
    var block = new Block(context);
    block.acquire();
    marker.run();
    block.release();
    marker.run();

    var inOrder = Mockito.inOrder(context, marker);
    inOrder.verify(marker, calls(1)).run();
    inOrder.verify(context).open(block);
    inOrder.verify(marker, calls(1)).run();
    inOrder.verify(context).release(block);
    inOrder.verify(context).close();
    inOrder.verify(marker, calls(1)).run();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void shouldNotCallCloseOnContext_whenInitialBlockIsReleased_givenThereIsANextBlock() {
    var block = new Block(context);
    block.acquire();

    block.allocateBuffer(buffer);
    block.next();

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verify(context).release(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void shouldReleaseInitialBlock_whenWeGetTheNextBlock_givenTheInitialBlockIsAllocated() {
    var block = new Block(context);
    block.acquire();

    block.allocateBuffer(buffer);
    block.next();

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verify(context).release(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void shouldThrow_whenWeGetTheNextBlock_givenTheInitialBlockIsNotAllocated() {
    var block = new Block(context);
    block.acquire();
    assertThrows(ScannerFailedException.class, block::next);

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void shouldReturnNull_whenWeGetTheBuffer_givenTheBlockIsNotAllocated() {
    var block = new Block(context);
    block.acquire();
    assertEquals(null, block.getByteBuffer());

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void shouldReturnReadOnlyBuffer_whenWeGetTheBuffer_givenTheBlockIsAllocated() {
    when(buffer.asReadOnlyBuffer()).thenReturn(readOnlyBuffer);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(buffer);
    assertEquals(readOnlyBuffer, block.getByteBuffer());

    var inOrder = Mockito.inOrder(context, buffer);
    inOrder.verify(context).open(block);
    inOrder.verify(buffer).asReadOnlyBuffer();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void shouldReturnZero_whenWeGetStartPositionOfInitialBlock() {

    var block = new Block(context);

    assertEquals(0, block.startPosition());
  }

  @Test
  void shouldGetBufferCapacity_whenWeGetStartPositionOfSecondBlock_givenTheInitialBlockIsAllocated() {
    int capacity = 123;

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(ByteBuffer.allocate(capacity));
    var nextBlock = block.next();

    assertEquals(capacity, nextBlock.startPosition());

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verify(context).release(block);
    inOrder.verifyNoMoreInteractions();
  }

}
