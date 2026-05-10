import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 *
 * Each logical scenario is tested against BOTH the loop-based and
 * stream-based implementations so they are verified to be equivalent.
 */
class ArrayUtilsTest {

    // =========================================================================
    // Shared test data
    // =========================================================================

    static final int[] SINGLE        = {42};
    static final int[] POSITIVES     = {3, 1, 4, 1, 5, 9, 2, 6};
    static final int[] WITH_NEGATIVES = {-10, -3, 0, 7, 2};
    static final int[] ALL_SAME      = {5, 5, 5, 5};
    static final int[] ALL_NEGATIVE  = {-1, -2, -3, -4};
    static final int[] TWO_ELEMENTS  = {10, 20};
    static final int[] LARGE_VALUES  = {Integer.MAX_VALUE, Integer.MAX_VALUE};

    // =========================================================================
    // min — loop
    // =========================================================================

    @Test void minLoop_single()       { assertEquals(42,  ArrayUtils.minLoop(SINGLE)); }
    @Test void minLoop_positives()    { assertEquals(1,   ArrayUtils.minLoop(POSITIVES)); }
    @Test void minLoop_withNegatives(){ assertEquals(-10, ArrayUtils.minLoop(WITH_NEGATIVES)); }
    @Test void minLoop_allSame()      { assertEquals(5,   ArrayUtils.minLoop(ALL_SAME)); }
    @Test void minLoop_allNegative()  { assertEquals(-4,  ArrayUtils.minLoop(ALL_NEGATIVE)); }
    @Test void minLoop_twoElements()  { assertEquals(10,  ArrayUtils.minLoop(TWO_ELEMENTS)); }

    // =========================================================================
    // min — stream
    // =========================================================================

    @Test void minStream_single()       { assertEquals(42,  ArrayUtils.minStream(SINGLE)); }
    @Test void minStream_positives()    { assertEquals(1,   ArrayUtils.minStream(POSITIVES)); }
    @Test void minStream_withNegatives(){ assertEquals(-10, ArrayUtils.minStream(WITH_NEGATIVES)); }
    @Test void minStream_allSame()      { assertEquals(5,   ArrayUtils.minStream(ALL_SAME)); }
    @Test void minStream_allNegative()  { assertEquals(-4,  ArrayUtils.minStream(ALL_NEGATIVE)); }
    @Test void minStream_twoElements()  { assertEquals(10,  ArrayUtils.minStream(TWO_ELEMENTS)); }

    // =========================================================================
    // max — loop
    // =========================================================================

    @Test void maxLoop_single()       { assertEquals(42,  ArrayUtils.maxLoop(SINGLE)); }
    @Test void maxLoop_positives()    { assertEquals(9,   ArrayUtils.maxLoop(POSITIVES)); }
    @Test void maxLoop_withNegatives(){ assertEquals(7,   ArrayUtils.maxLoop(WITH_NEGATIVES)); }
    @Test void maxLoop_allSame()      { assertEquals(5,   ArrayUtils.maxLoop(ALL_SAME)); }
    @Test void maxLoop_allNegative()  { assertEquals(-1,  ArrayUtils.maxLoop(ALL_NEGATIVE)); }
    @Test void maxLoop_twoElements()  { assertEquals(20,  ArrayUtils.maxLoop(TWO_ELEMENTS)); }

    // =========================================================================
    // max — stream
    // =========================================================================

    @Test void maxStream_single()       { assertEquals(42,  ArrayUtils.maxStream(SINGLE)); }
    @Test void maxStream_positives()    { assertEquals(9,   ArrayUtils.maxStream(POSITIVES)); }
    @Test void maxStream_withNegatives(){ assertEquals(7,   ArrayUtils.maxStream(WITH_NEGATIVES)); }
    @Test void maxStream_allSame()      { assertEquals(5,   ArrayUtils.maxStream(ALL_SAME)); }
    @Test void maxStream_allNegative()  { assertEquals(-1,  ArrayUtils.maxStream(ALL_NEGATIVE)); }
    @Test void maxStream_twoElements()  { assertEquals(20,  ArrayUtils.maxStream(TWO_ELEMENTS)); }

    // =========================================================================
    // sum — loop
    // =========================================================================

    @Test void sumLoop_single()       { assertEquals(42L,  ArrayUtils.sumLoop(SINGLE)); }
    @Test void sumLoop_positives()    { assertEquals(31L,  ArrayUtils.sumLoop(POSITIVES)); }   // 3+1+4+1+5+9+2+6
    @Test void sumLoop_withNegatives(){ assertEquals(-4L,  ArrayUtils.sumLoop(WITH_NEGATIVES)); } // -10-3+0+7+2
    @Test void sumLoop_allSame()      { assertEquals(20L,  ArrayUtils.sumLoop(ALL_SAME)); }
    @Test void sumLoop_allNegative()  { assertEquals(-10L, ArrayUtils.sumLoop(ALL_NEGATIVE)); }
    @Test void sumLoop_twoElements()  { assertEquals(30L,  ArrayUtils.sumLoop(TWO_ELEMENTS)); }
    @Test void sumLoop_noIntOverflow() {
        // long return type must handle values that would overflow int
        long expected = (long) Integer.MAX_VALUE * 2;
        assertEquals(expected, ArrayUtils.sumLoop(LARGE_VALUES));
    }

    // =========================================================================
    // sum — stream
    // =========================================================================

    @Test void sumStream_single()       { assertEquals(42L,  ArrayUtils.sumStream(SINGLE)); }
    @Test void sumStream_positives()    { assertEquals(31L,  ArrayUtils.sumStream(POSITIVES)); }
    @Test void sumStream_withNegatives(){ assertEquals(-4L,  ArrayUtils.sumStream(WITH_NEGATIVES)); }
    @Test void sumStream_allSame()      { assertEquals(20L,  ArrayUtils.sumStream(ALL_SAME)); }
    @Test void sumStream_allNegative()  { assertEquals(-10L, ArrayUtils.sumStream(ALL_NEGATIVE)); }
    @Test void sumStream_twoElements()  { assertEquals(30L,  ArrayUtils.sumStream(TWO_ELEMENTS)); }
    @Test void sumStream_noIntOverflow() {
        long expected = (long) Integer.MAX_VALUE * 2;
        assertEquals(expected, ArrayUtils.sumStream(LARGE_VALUES));
    }

    // =========================================================================
    // average — loop
    // =========================================================================

    @Test void averageLoop_single()       { assertEquals(42.0,    ArrayUtils.averageLoop(SINGLE),        1e-9); }
    @Test void averageLoop_positives()    { assertEquals(31.0/8,  ArrayUtils.averageLoop(POSITIVES),     1e-9); }
    @Test void averageLoop_withNegatives(){ assertEquals(-4.0/5,  ArrayUtils.averageLoop(WITH_NEGATIVES),1e-9); }
    @Test void averageLoop_allSame()      { assertEquals(5.0,     ArrayUtils.averageLoop(ALL_SAME),      1e-9); }
    @Test void averageLoop_allNegative()  { assertEquals(-10.0/4, ArrayUtils.averageLoop(ALL_NEGATIVE),  1e-9); }
    @Test void averageLoop_twoElements()  { assertEquals(15.0,    ArrayUtils.averageLoop(TWO_ELEMENTS),  1e-9); }

    // =========================================================================
    // average — stream
    // =========================================================================

    @Test void averageStream_single()       { assertEquals(42.0,    ArrayUtils.averageStream(SINGLE),        1e-9); }
    @Test void averageStream_positives()    { assertEquals(31.0/8,  ArrayUtils.averageStream(POSITIVES),     1e-9); }
    @Test void averageStream_withNegatives(){ assertEquals(-4.0/5,  ArrayUtils.averageStream(WITH_NEGATIVES),1e-9); }
    @Test void averageStream_allSame()      { assertEquals(5.0,     ArrayUtils.averageStream(ALL_SAME),      1e-9); }
    @Test void averageStream_allNegative()  { assertEquals(-10.0/4, ArrayUtils.averageStream(ALL_NEGATIVE),  1e-9); }
    @Test void averageStream_twoElements()  { assertEquals(15.0,    ArrayUtils.averageStream(TWO_ELEMENTS),  1e-9); }

    // =========================================================================
    // Loop vs Stream equivalence — confirms both approaches agree
    // =========================================================================

    @Test void loopAndStreamAgree_positives() {
        assertEquals(ArrayUtils.minLoop(POSITIVES),     ArrayUtils.minStream(POSITIVES));
        assertEquals(ArrayUtils.maxLoop(POSITIVES),     ArrayUtils.maxStream(POSITIVES));
        assertEquals(ArrayUtils.sumLoop(POSITIVES),     ArrayUtils.sumStream(POSITIVES));
        assertEquals(ArrayUtils.averageLoop(POSITIVES), ArrayUtils.averageStream(POSITIVES), 1e-9);
    }

    @Test void loopAndStreamAgree_withNegatives() {
        assertEquals(ArrayUtils.minLoop(WITH_NEGATIVES),     ArrayUtils.minStream(WITH_NEGATIVES));
        assertEquals(ArrayUtils.maxLoop(WITH_NEGATIVES),     ArrayUtils.maxStream(WITH_NEGATIVES));
        assertEquals(ArrayUtils.sumLoop(WITH_NEGATIVES),     ArrayUtils.sumStream(WITH_NEGATIVES));
        assertEquals(ArrayUtils.averageLoop(WITH_NEGATIVES), ArrayUtils.averageStream(WITH_NEGATIVES), 1e-9);
    }

    // =========================================================================
    // Edge-case / guard-rail tests
    // =========================================================================

    @Test void allMethods_throwOnNullArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.minLoop(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.maxLoop(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.sumLoop(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.averageLoop(null));

        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.minStream(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.maxStream(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.sumStream(null));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.averageStream(null));
    }

    @Test void allMethods_throwOnEmptyArray() {
        int[] empty = {};
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.minLoop(empty));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.maxLoop(empty));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.sumLoop(empty));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.averageLoop(empty));

        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.minStream(empty));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.maxStream(empty));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.sumStream(empty));
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.averageStream(empty));
    }
}
