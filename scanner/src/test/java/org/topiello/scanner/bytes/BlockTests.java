package org.topiello.scanner.bytes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.internal.verification.VerificationModeFactory.calls;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.topiello.scanner.ScannerClosedException;
import org.topiello.scanner.ScannerFailedException;

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
  @Mock
  ByteBuffer duplicatedBuffer;
  @Mock
  ByteBuffer flipperBuffer;

  @Test
  void whenABlockIsConstructed_shouldCallOpenOnGivenContext() {
    var block = new Block(context);

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @TestFactory
  Stream<DynamicTest> whenReleaseCountReachesAcquireCount_givenThereAreNoSubsequentBlocks_shouldCallReleaseOnContext() {
    return IntStream.range(1, 10).mapToObj(i -> dynamicTest("Count of " + i, () -> {
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
  void whenReleaseCalledOnBlock_givenAcquireHasNotBeenCalled_shouldThrowException() {
    var block = new Block(context);

    assertThrows(ScannerClosedException.class, block::release);
  }

  @Test
  void whenInitialBlockIsReleased_givenThereAreNoSubsequentBlocks_shouldCallCloseOnContext() {
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
  void whenInitialBlockIsReleased_givenThereIsANextBlock_shouldNotCallCloseOnContext() {
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
  void whenWeGetTheNextBlock_givenTheInitialBlockIsAllocated_shouldReleaseInitialBlock() {
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
  void whenWeGetTheNextBlock_givenTheInitialBlockIsNotAllocated_shouldThrow() {
    var block = new Block(context);
    block.acquire();
    assertThrows(ScannerFailedException.class, block::next);

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenWeGetTheBuffer_givenTheBlockIsNotAllocated_shouldReturnNull() {
    var block = new Block(context);
    block.acquire();
    assertEquals(null, block.getByteBuffer());

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenWeGetTheBuffer_givenTheBlockIsAllocated_shouldReturnReadOnlyBuffer() {
    Mockito.when(buffer.asReadOnlyBuffer()).thenReturn(readOnlyBuffer);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(buffer);

    assertEquals(this.readOnlyBuffer, block.getByteBuffer());

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenWeGetStartPositionOfInitialBlock_shouldReturnZero() {
    var block = new Block(context);

    assertEquals(0, block.startPosition());
  }

  @Test
  void whenWeGetStartPositionOfSecondBlock_givenTheInitialBlockIsAllocated_shouldGetBufferCapacity() {
    int capacity = 123;

    Mockito.when(buffer.capacity()).thenReturn(capacity);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(buffer);
    var nextBlock = block.next();

    assertEquals(capacity, nextBlock.startPosition());

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verify(context).release(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenWeGetReadBuffer_givenBufferIsAllocate_shouldDuplicateAndFlipAllocatedBuffer() {
    Mockito.when(buffer.asReadOnlyBuffer()).thenReturn(readOnlyBuffer);
    Mockito.when(readOnlyBuffer.duplicate()).thenReturn(duplicatedBuffer);
    Mockito.when(duplicatedBuffer.flip()).thenReturn(flipperBuffer);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(buffer);

    assertEquals(flipperBuffer, block.getReadBuffer());

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenWeGetReadBuffer_givenBufferIsNotAllocated_shouldReturnNull() {
    var block = new Block(context);
    block.acquire();

    var readBuffer = block.getReadBuffer();
    assertEquals(readBuffer, null);

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenAwaitAllocation_givenBufferIsNotAllocated_shouldCallAwaitAllocationOnContext() {
    var block = new Block(context);
    block.acquire();
    block.awaitAllocation();

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verify(context).awaitAllocation(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenAwaitAllocation_givenBufferIsAllocated_shouldDoNothing() {
    Mockito.when(buffer.asReadOnlyBuffer()).thenReturn(readOnlyBuffer);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(buffer);
    block.awaitAllocation();

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenAwaitDataAtPosition_givenBufferIsAtGivenPosition_shouldCallAwaitDataOnContext() {
    int position = 5;

    Mockito.when(buffer.asReadOnlyBuffer()).thenReturn(readOnlyBuffer);
    Mockito.when(readOnlyBuffer.position()).thenReturn(position);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(buffer);
    block.awaitData(position);

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verify(context).awaitData(block, position);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenAwaitDataAtPosition_givenBufferIsAfterGivenPosition_shouldDoNothing() {
    int position = 5;

    Mockito.when(buffer.asReadOnlyBuffer()).thenReturn(readOnlyBuffer);
    Mockito.when(readOnlyBuffer.position()).thenReturn(position);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(buffer);
    block.awaitData(position - 1);

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }
}
