package org.topiello.scanner.bytes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.internal.verification.VerificationModeFactory.calls;

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
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

  /*
   * TODO many of these tests use byte buffers directly rather than mocking them.
   * Now that mockito-inline is on the classpath to deal with this it should be
   * tried again.
   */

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

    block.allocateBuffer(ByteBuffer.allocate(1));
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

    block.allocateBuffer(ByteBuffer.allocate(1));
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
    var buffer = ByteBuffer.allocate(1);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(buffer);

    var readOnlyBuffer = block.getByteBuffer();
    assertThrows(ReadOnlyBufferException.class, () -> readOnlyBuffer.put((byte) 1));

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

  @Test
  void whenWeGetReadBuffer_givenBufferIsAllocate_shouldDuplicateAndFlipAllocatedBuffer() {
    int capacity = 10;
    byte value = 123;

    var writeBuffer = ByteBuffer.allocate(capacity);
    writeBuffer.put(value);

    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(writeBuffer);

    var readBuffer = block.getReadBuffer();
    byte readValue = readBuffer.get();

    assertEquals(value, readValue);

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
    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(ByteBuffer.allocate(1));
    block.awaitAllocation();

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenAwaitDataAtPosition_givenBufferIsAtGivenPosition_shouldCallAwaitDataOnContext() {
    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(ByteBuffer.allocate(10).position(5));
    block.awaitData(5);

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verify(context).awaitData(block, 5);
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void whenAwaitDataAtPosition_givenBufferIsAfterGivenPosition_shouldDoNothing() {
    var block = new Block(context);
    block.acquire();
    block.allocateBuffer(ByteBuffer.allocate(10).position(5));
    block.awaitData(4);

    var inOrder = Mockito.inOrder(context);
    inOrder.verify(context).open(block);
    inOrder.verifyNoMoreInteractions();
  }
}
