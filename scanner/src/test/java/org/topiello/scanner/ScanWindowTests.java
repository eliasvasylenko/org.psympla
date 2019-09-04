package org.topiello.scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doCallRealMethod;

import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ScanWindowTests {
  @Mock
  ScanWindow<Object, Object> scanWindow;

  @Mock
  Scanner<Object, Object> scanner;

  @TestFactory
  Stream<DynamicTest> whenWeAskForWindowSize_shouldReturnDifferenceBetweenInputPositionAndRetainedPosition() {
    long inputPosition = 10;
    return LongStream
        .range(1, inputPosition)
        .mapToObj(
            retainedPosition -> dynamicTest(
                "Scan between " + retainedPosition + " and " + inputPosition,
                () -> {
                  // The following line is only necessary due to a bug in mockito
                  @SuppressWarnings("unchecked")
                  ScanWindow<Object, Object> scanWindow = Mockito.mock(ScanWindow.class);

                  when(scanWindow.windowSize()).thenCallRealMethod();

                  when(scanWindow.scanner()).thenReturn(scanner);
                  when(scanner.inputPosition()).thenReturn(inputPosition);
                  when(scanWindow.retainedPosition()).thenReturn(retainedPosition);

                  assertEquals(inputPosition - retainedPosition, scanWindow.windowSize());
                }));
  }

  @TestFactory
  Stream<DynamicTest> whenWeTakeToOffset_shouldTakeToOffsetPlusRetainedPosition() {
    long retainedPosition = 10;
    return LongStream
        .range(0, 10)
        .mapToObj(
            offset -> dynamicTest(
                "Take to offset " + offset + " at position " + retainedPosition,
                () -> {
                  // The following line is only necessary due to a bug in mockito
                  @SuppressWarnings("unchecked")
                  ScanWindow<Object, Object> scanWindow = Mockito.mock(ScanWindow.class);

                  Object take = new Object();

                  when(scanWindow.takeToOffset(anyLong())).thenCallRealMethod();
                  when(scanWindow.retainedPosition()).thenReturn(retainedPosition);
                  when(scanWindow.takeTo(anyLong())).thenReturn(take);

                  assertEquals(take, scanWindow.takeToOffset(offset));

                  Mockito.verify(scanWindow).takeTo(retainedPosition + offset);
                }));
  }

  @TestFactory
  Stream<DynamicTest> whenWeTake_shouldTakeToInputPosition() {
    return LongStream
        .range(0, 10)
        .mapToObj(inputPosition -> dynamicTest("Take to input position " + inputPosition, () -> {
          // The following line is only necessary due to a bug in mockito
          @SuppressWarnings("unchecked")
          ScanWindow<Object, Object> scanWindow = Mockito.mock(ScanWindow.class);

          Object take = new Object();

          when(scanWindow.take()).thenCallRealMethod();
          when(scanWindow.scanner()).thenReturn(scanner);
          when(scanner.inputPosition()).thenReturn(inputPosition);
          when(scanWindow.takeTo(anyLong())).thenReturn(take);

          assertEquals(take, scanWindow.take());

          Mockito.verify(scanWindow).takeTo(inputPosition);
        }));
  }

  @TestFactory
  Stream<DynamicTest> whenWeDiscardToOffset_shouldDiscardToOffsetPlusRetainedPosition() {
    long retainedPosition = 10;
    return LongStream
        .range(0, 10)
        .mapToObj(
            offset -> dynamicTest(
                "Discard to offset " + offset + " at position " + retainedPosition,
                () -> {
                  // The following line is only necessary due to a bug in mockito
                  @SuppressWarnings("unchecked")
                  ScanWindow<Object, Object> scanWindow = Mockito.mock(ScanWindow.class);

                  doCallRealMethod().when(scanWindow).discardToOffset(anyLong());
                  when(scanWindow.retainedPosition()).thenReturn(retainedPosition);

                  scanWindow.discardToOffset(offset);

                  Mockito.verify(scanWindow).discardTo(retainedPosition + offset);
                }));
  }

  @TestFactory
  Stream<DynamicTest> whenWeDiscard_shouldDiscardToInputPosition() {
    return LongStream
        .range(0, 10)
        .mapToObj(inputPosition -> dynamicTest("Discard to input position " + inputPosition, () -> {
          // The following line is only necessary due to a bug in mockito
          @SuppressWarnings("unchecked")
          ScanWindow<Object, Object> scanWindow = Mockito.mock(ScanWindow.class);

          doCallRealMethod().when(scanWindow).discard();
          when(scanWindow.scanner()).thenReturn(scanner);
          when(scanner.inputPosition()).thenReturn(inputPosition);

          scanWindow.discard();

          Mockito.verify(scanWindow).discardTo(inputPosition);
        }));
  }
}
