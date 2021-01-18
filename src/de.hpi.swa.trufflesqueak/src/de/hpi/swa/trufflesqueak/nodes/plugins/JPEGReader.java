/*
 * Copyright (c) 2017-2021 Software Architecture Group, Hasso Plattner Institute
 *
 * Licensed under the MIT License.
 */
package de.hpi.swa.trufflesqueak.nodes.plugins;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

import de.hpi.swa.trufflesqueak.exceptions.PrimitiveExceptions.PrimitiveFailed;
import de.hpi.swa.trufflesqueak.model.ArrayObject;
import de.hpi.swa.trufflesqueak.model.NativeObject;
import de.hpi.swa.trufflesqueak.model.PointersObject;
import de.hpi.swa.trufflesqueak.nodes.SqueakGuards;

/* Automatically generated by
    VMPluginCodeGenerator * VMMaker.oscog-eem.2480 uuid: bb3ffda7-8241-4dea-b886-d656e474b6c1
   from
    JPEGReaderPlugin * VMMaker.oscog-eem.2480 uuid: bb3ffda7-8241-4dea-b886-d656e474b6c1
 */

public final class JPEGReader {

    /* Constants */
    private static final int BlockWidthIndex = 5;
    private static final int BlueIndex = 2;
    private static final int ConstBits = 13;
    private static final int CurrentXIndex = 0;
    private static final int CurrentYIndex = 1;
    private static final int DCTSize = 8;
    public static final int DCTSize2 = 64;
    private static final int FIXn0n298631336 = 2446;
    private static final int FIXn0n34414 = 22554;
    private static final int FIXn0n390180644 = 0xC7C;
    private static final int FIXn0n541196100 = 4433;
    private static final int FIXn0n71414 = 46802;
    private static final int FIXn0n765366865 = 6270;
    private static final int FIXn0n899976223 = 7373;
    private static final int FIXn1n175875602 = 9633;
    private static final int FIXn1n40200 = 91881;
    private static final int FIXn1n501321110 = 12299;
    private static final int FIXn1n77200 = 116130;
    private static final int FIXn1n847759065 = 15137;
    private static final int FIXn1n961570560 = 16069;
    private static final int FIXn2n053119869 = 16819;
    private static final int FIXn2n562915447 = 20995;
    private static final int FIXn3n072711026 = 25172;
    private static final int GreenIndex = 1;
    private static final int HScaleIndex = 2;
    private static final int MaxBits = 16;
    private static final int MaxMCUBlocks = 128;
    private static final int MaxSample = 255;
    private static final int MCUBlockIndex = 4;
    private static final int MCUWidthIndex = 8;
    public static final int MinComponentSize = 11;
    private static final int Pass1Bits = 2;
    private static final int Pass1Div = 0x800;
    private static final int Pass2Div = 0x40000;
    private static final int PriorDCValueIndex = 10;
    private static final int RedIndex = 0;
    private static final int SampleOffset = 127;
    private static final int VScaleIndex = 3;

    /* Variables */
    private int[] acTable;
    private int acTableSize;
    private final int[][] cbBlocks = new int[128][];
    private final int[] cbComponent = new int[11];
    private final int[][] crBlocks = new int[128][];
    private final int[] crComponent = new int[11];
    private int[] dcTable;
    private int dcTableSize;
    private int ditherMask;

    private int[] jpegBits;
    private int jpegBitsSize;
    @CompilationFinal(dimensions = 1) private final int[] jpegNaturalOrder = {
                    0, 1, 8, 16, 9, 2, 3, 10,
                    17, 24, 32, 25, 18, 11, 4, 5,
                    12, 19, 26, 33, 40, 48, 41, 34,
                    27, 20, 13, 6, 7, 14, 21, 28,
                    35, 42, 49, 56, 57, 50, 43, 36,
                    29, 22, 15, 23, 30, 37, 44, 51,
                    58, 59, 52, 45, 38, 31, 39, 46,
                    53, 60, 61, 54, 47, 55, 62, 63
    };
    private int jsBitBuffer;
    private int jsBitCount;
    private byte[] jsCollection;
    private int jsPosition;
    private int jsReadLimit;
    public static final String moduleName = "JPEGReaderPlugin * VMMaker.oscog-eem.2480 (TruffleSqueak)";
    private int[] residuals;
    private final int[][] yBlocks = new int[128][];
    private final int[] yComponent = new int[11];

    private boolean failed;

    /* JPEGReaderPlugin>>#cbColorComponentFrom: */
    private boolean cbColorComponentFrom(final Object oop) {
        return colorComponentfrom(cbComponent, oop) && colorComponentBlocksfrom(cbBlocks, oop);
    }

    /* JPEGReaderPlugin>>#colorComponentBlocks:from: */
    private static boolean colorComponentBlocksfrom(final int[][] blocks, final Object oop) {
        if (!isPointers(oop)) {
            return false;
        }
        final PointersObject pointersOop = (PointersObject) oop;
        if (slotSizeOf(pointersOop) < MinComponentSize) {
            return false;
        }
        final Object arrayOop = fetchPointerofObject(MCUBlockIndex, pointersOop);
        if (!isPointers(arrayOop)) {
            return false;
        }
        final PointersObject arrayPointersOop = (PointersObject) arrayOop;
        final int max = slotSizeOf(arrayPointersOop);
        if (max > MaxMCUBlocks) {
            return false;
        }
        for (int i = 0; i < max; i += 1) {
            final Object blockOop = fetchPointerofObject(i, arrayPointersOop);
            if (!isWords(blockOop)) {
                return false;
            }
            final NativeObject blockNativeOop = (NativeObject) blockOop;
            if (blockNativeOop.getIntLength() != DCTSize2) {
                return false;
            }
            blocks[i] = blockNativeOop.getIntStorage();
        }
        return true;
    }

    /* JPEGReaderPlugin>>#colorComponent:from: */
    private boolean colorComponentfrom(final int[] aColorComponent, final Object oop) {
        if (!isPointers(oop)) {
            return false;
        }
        final PointersObject pointersOop = (PointersObject) oop;
        if (slotSizeOf(pointersOop) < MinComponentSize) {
            return false;
        }

        aColorComponent[CurrentXIndex] = fetchIntegerofObject(CurrentXIndex, pointersOop);
        aColorComponent[CurrentYIndex] = fetchIntegerofObject(CurrentYIndex, pointersOop);
        aColorComponent[HScaleIndex] = fetchIntegerofObject(HScaleIndex, pointersOop);
        aColorComponent[VScaleIndex] = fetchIntegerofObject(VScaleIndex, pointersOop);
        aColorComponent[BlockWidthIndex] = fetchIntegerofObject(BlockWidthIndex, pointersOop);
        aColorComponent[MCUWidthIndex] = fetchIntegerofObject(MCUWidthIndex, pointersOop);
        aColorComponent[PriorDCValueIndex] = fetchIntegerofObject(PriorDCValueIndex, pointersOop);
        return true;
    }

    /** JPEGReaderPlugin>>#colorConvertGrayscaleMCU inlined. */

    /** JPEGReaderPlugin>>#colorConvertMCU inlined. */

    /* JPEGReaderPlugin>>#crColorComponentFrom: */
    private boolean crColorComponentFrom(final Object oop) {
        return colorComponentfrom(crComponent, oop) && colorComponentBlocksfrom(crBlocks, oop);
    }

    /* JPEGReaderPlugin>>#decodeBlockInto:component: */
    private void decodeBlockIntocomponent(final int[] anArray, final int[] aColorComponent) {
        int byteValue = jpegDecodeValueFromsize(dcTable, dcTableSize);
        if (byteValue < 0) {
            throw PrimitiveFailed.GENERIC_ERROR;
        }
        int bits;
        if (byteValue != 0) {
            bits = getBits(byteValue);
            byteValue = scaleAndSignExtendinFieldWidth(bits, byteValue);
        }
        byteValue = aColorComponent[PriorDCValueIndex] = aColorComponent[PriorDCValueIndex] + byteValue;
        anArray[0] = byteValue;
        for (int i = 1; i < DCTSize2; i += 1) {
            anArray[i] = 0;
        }
        int index = 1;
        while (index < DCTSize2) {
            byteValue = jpegDecodeValueFromsize(acTable, acTableSize);
            if (byteValue < 0) {
                throw PrimitiveFailed.GENERIC_ERROR;
            }
            final int zeroCount = (int) (Integer.toUnsignedLong(byteValue) >> 4);
            byteValue = byteValue & 15;
            if (byteValue != 0) {
                index += zeroCount;
                bits = getBits(byteValue);
                byteValue = scaleAndSignExtendinFieldWidth(bits, byteValue);
                if (index < 0 || index >= DCTSize2) {
                    throw PrimitiveFailed.GENERIC_ERROR;
                }
                anArray[jpegNaturalOrder[index]] = byteValue;
            } else {
                if (zeroCount == 15) {
                    index += zeroCount;
                } else {
                    return;
                }
            }
            index += 1;
        }
    }

    /* JPEGReaderPlugin>>#fillBuffer */
    private void fillBuffer() {
        byte byteValue;

        while (jsBitCount <= 16) {
            if (jsPosition >= jsReadLimit) {
                return;
            }
            byteValue = jsCollection[jsPosition];
            jsPosition += 1;
            if (byteValue == (byte) 0xFF) {
                /* peek for 00 */
                if (!(jsPosition < jsReadLimit && jsCollection[jsPosition] == 0)) {
                    jsPosition -= 1;
                    return;
                }
                jsPosition += 1;
            }
            jsBitBuffer = (int) (Integer.toUnsignedLong(jsBitBuffer) << 8) | Byte.toUnsignedInt(byteValue);
            jsBitCount += 8;
        }
    }

    /* JPEGReaderPlugin>>#getBits: */
    private int getBits(final int requestedBits) {
        if (requestedBits > jsBitCount) {
            fillBuffer();
            if (requestedBits > jsBitCount) {
                return -1;
            }
        }
        jsBitCount -= requestedBits;
        final int value = (int) (Integer.toUnsignedLong(jsBitBuffer) >> jsBitCount);
        jsBitBuffer = jsBitBuffer & (1 << jsBitCount) - 1;
        return value;
    }

    /** JPEGReaderPlugin>>#idctBlockInt:qt: inlined. */

    /* Decode the next value in the receiver using the given huffman table. */

    /* JPEGReaderPlugin>>#jpegDecodeValueFrom:size: */
    private int jpegDecodeValueFromsize(final int[] table, final int tableSize) {
        /* Initial bits needed */
        int bitsNeeded = (int) (Integer.toUnsignedLong(table[0]) >> 24);
        if (bitsNeeded > MaxBits) {
            return -1;
        }

        /* First real table */
        int tableIndex = 2;
        while (true) {

            /* Get bits */
            final int bits = getBits(bitsNeeded);
            if (bits < 0) {
                return -1;
            }
            final int index = tableIndex + bits - 1;
            if (index >= tableSize) {
                return -1;
            }

            /* Lookup entry in table */
            final int value = table[index];
            if ((value & 0x3F000000) == 0) {
                return value;
            }

            /* Table offset in low 16 bit */
            tableIndex = value & 0xFFFF;

            /* Additional bits in high 8 bit */
            bitsNeeded = (int) (Integer.toUnsignedLong(value) >> 24 & 0xFF);
            if (bitsNeeded > MaxBits) {
                return -1;
            }
        }
    }

    /* JPEGReaderPlugin>>#loadJPEGStreamFrom: */
    private boolean loadJPEGStreamFrom(final PointersObject streamOop) {
        final Object oop = fetchPointerofObject(0, streamOop);
        if (!isBytes(oop)) {
            return false;
        }
        jsCollection = ((NativeObject) oop).getByteStorage();
        jsPosition = fetchIntegerofObject(1, streamOop);
        jsReadLimit = fetchIntegerofObject(2, streamOop);
        jsBitBuffer = fetchIntegerofObject(3, streamOop);
        jsBitCount = fetchIntegerofObject(4, streamOop);
        if (failed()) {
            return false;
        }
        if (jsCollection.length < jsReadLimit) {
            return false;
        }
        return 0 <= jsPosition && jsPosition < jsReadLimit;
    }

    /* JPEGReaderPlugin>>#nextSampleCb */
    private int nextSampleCb() {
        final int blockIndex;
        int curX;
        int dx;
        int dy;
        final int sample;
        final int sampleIndex;
        final int sx;
        final int sy;

        dx = curX = cbComponent[CurrentXIndex];
        dy = cbComponent[CurrentYIndex];
        sx = cbComponent[HScaleIndex];
        sy = cbComponent[VScaleIndex];
        if (!(sx == 0 && sy == 0)) {
            dx = dx / sx;
            dy = dy / sy;
        }
        blockIndex = (int) ((Integer.toUnsignedLong(dy) >> 3) * cbComponent[BlockWidthIndex] + (Integer.toUnsignedLong(dx) >> 3));
        sampleIndex = (int) ((Integer.toUnsignedLong(dy & 7) << 3) + (dx & 7));
        sample = cbBlocks[blockIndex][sampleIndex];
        curX += 1;
        if (curX < cbComponent[MCUWidthIndex] * 8) {
            cbComponent[CurrentXIndex] = curX;
        } else {
            cbComponent[CurrentXIndex] = 0;
            cbComponent[CurrentYIndex] = cbComponent[CurrentYIndex] + 1;
        }
        return sample;
    }

    /** JPEGReaderPlugin>>#nextSampleCr inlined. */

    /** JPEGReaderPlugin>>#nextSampleFrom:blocks: has no sender. */

    /* JPEGReaderPlugin>>#nextSampleY */
    private int nextSampleY() {
        final int blockIndex;
        int curX;
        int dx;
        int dy;
        final int sample;
        final int sampleIndex;
        final int sx;
        final int sy;

        dx = curX = yComponent[CurrentXIndex];
        dy = yComponent[CurrentYIndex];
        sx = yComponent[HScaleIndex];
        sy = yComponent[VScaleIndex];
        if (!(sx == 0 && sy == 0)) {
            dx = dx / sx;
            dy = dy / sy;
        }
        blockIndex = (int) ((Integer.toUnsignedLong(dy) >> 3) * yComponent[BlockWidthIndex] + (Integer.toUnsignedLong(dx) >> 3));
        sampleIndex = (int) ((Integer.toUnsignedLong(dy & 7) << 3) + (dx & 7));
        sample = yBlocks[blockIndex][sampleIndex];
        curX += 1;
        if (curX < yComponent[MCUWidthIndex] * 8) {
            yComponent[CurrentXIndex] = curX;
        } else {
            yComponent[CurrentXIndex] = 0;
            yComponent[CurrentYIndex] = yComponent[CurrentYIndex] + 1;
        }
        return sample;
    }

    /*
     * Requires: JPEGColorComponent bits WordArray with: 3*Integer (residuals) ditherMask
     */

    /* JPEGReaderPlugin>>#primitiveColorConvertGrayscaleMCU */
    @TruffleBoundary(transferToInterpreterOnException = false)
    public void primitiveColorConvertGrayscaleMCU(final ArrayObject componentArray, final NativeObject bits, final NativeObject residualArray, final long mask) {
        ditherMask = (int) mask;
        residuals = residualArray.getIntStorage();
        jpegBits = bits.getIntStorage();
        jpegBitsSize = jpegBits.length;
        if (!yColorComponentFrom(componentArray)) {
            throw PrimitiveFailed.GENERIC_ERROR;
        }
        /* begin colorConvertGrayscaleMCU */
        yComponent[CurrentXIndex] = 0;
        yComponent[CurrentYIndex] = 0;
        int y;
        for (int i = 0; i < jpegBitsSize; i += 1) {
            y = nextSampleY();
            y += residuals[GreenIndex];
            y = Math.min(y, MaxSample);
            residuals[GreenIndex] = y & ditherMask;
            y = y & MaxSample - ditherMask;
            y = Math.max(y, 1);
            jpegBits[i] = 0xFF000000 + (int) ((long) y << 16) + (int) ((long) y << 8) + y;
        }
    }

    /*
     * Requires: Array with: 3*JPEGColorComponent bits WordArray with: 3*Integer (residuals)
     * ditherMask
     */

    /* JPEGReaderPlugin>>#primitiveColorConvertMCU */
    @TruffleBoundary(transferToInterpreterOnException = false)
    public void primitiveColorConvertMCU(final PointersObject componentArray, final NativeObject bits, final NativeObject residualArray, final long mask) {
        ditherMask = (int) mask;
        residuals = residualArray.getIntStorage();
        jpegBits = bits.getIntStorage();
        jpegBitsSize = jpegBits.length;
        if (!yColorComponentFrom(fetchPointerofObject(0, componentArray))) {
            throw PrimitiveFailed.GENERIC_ERROR;
        }
        if (!cbColorComponentFrom(fetchPointerofObject(1, componentArray))) {
            throw PrimitiveFailed.GENERIC_ERROR;
        }
        if (!crColorComponentFrom(fetchPointerofObject(2, componentArray))) {
            throw PrimitiveFailed.GENERIC_ERROR;
        }
        /* begin colorConvertMCU */
        yComponent[CurrentXIndex] = 0;
        yComponent[CurrentYIndex] = 0;
        cbComponent[CurrentXIndex] = 0;
        cbComponent[CurrentYIndex] = 0;
        crComponent[CurrentXIndex] = 0;
        crComponent[CurrentYIndex] = 0;
        for (int i = 0; i < jpegBitsSize; i += 1) {
            final int y = nextSampleY();
            int cb = nextSampleCb();
            cb -= SampleOffset;
            /* begin nextSampleCr */
            int dx = crComponent[CurrentXIndex];
            int curX = dx;
            int dy = crComponent[CurrentYIndex];
            final int sx = crComponent[HScaleIndex];
            final int sy = crComponent[VScaleIndex];
            if (!(sx == 0 && sy == 0)) {
                dx = dx / sx;
                dy = dy / sy;
            }
            final int blockIndex = (int) ((Integer.toUnsignedLong(dy) >> 3) * crComponent[BlockWidthIndex] + (Integer.toUnsignedLong(dx) >> 3));
            final int sampleIndex = (int) ((Integer.toUnsignedLong(dy & 7) << 3) + (dx & 7));
            final int sample = crBlocks[blockIndex][sampleIndex];
            curX += 1;
            if (curX < crComponent[MCUWidthIndex] * 8) {
                crComponent[CurrentXIndex] = curX;
            } else {
                crComponent[CurrentXIndex] = 0;
                crComponent[CurrentYIndex] = crComponent[CurrentYIndex] + 1;
            }
            int cr = sample;
            cr -= SampleOffset;
            int red = y + FIXn1n40200 * cr / 65536 + residuals[RedIndex];
            red = Math.min(red, MaxSample);
            red = Math.max(red, 0);
            residuals[RedIndex] = red & ditherMask;
            red = red & MaxSample - ditherMask;
            red = Math.max(red, 1);
            int green = y - FIXn0n34414 * cb / 65536 - FIXn0n71414 * cr / 65536 + residuals[GreenIndex];
            green = Math.min(green, MaxSample);
            green = Math.max(green, 0);
            residuals[GreenIndex] = green & ditherMask;
            green = green & MaxSample - ditherMask;
            green = Math.max(green, 1);
            int blue = y + FIXn1n77200 * cb / 65536 + residuals[BlueIndex];
            blue = Math.min(blue, MaxSample);
            blue = Math.max(blue, 0);
            residuals[BlueIndex] = blue & ditherMask;
            blue = blue & MaxSample - ditherMask;
            blue = Math.max(blue, 1);
            jpegBits[i] = (int) (0xFF000000 + (Integer.toUnsignedLong(red) << 16) + (Integer.toUnsignedLong(green) << 8) + blue);
        }
    }

    /*
     * In: anArray WordArray of: DCTSize2 aColorComponent JPEGColorComponent dcTable WordArray
     * acTable WordArray stream JPEGStream
     */

    /* JPEGReaderPlugin>>#primitiveDecodeMCU */
    @TruffleBoundary(transferToInterpreterOnException = false)
    public void primitiveDecodeMCU(final NativeObject sampleBuffer, final PointersObject comp, final NativeObject dcTableValue, final NativeObject acTableValue,
                    final PointersObject jpegStream) {
        if (!loadJPEGStreamFrom(jpegStream)) {
            throw PrimitiveFailed.GENERIC_ERROR;
        }
        acTable = acTableValue.getIntStorage();
        acTableSize = acTable.length;
        dcTable = dcTableValue.getIntStorage();
        dcTableSize = dcTable.length;
        if (!colorComponentfrom(yComponent, comp)) {
            throw PrimitiveFailed.GENERIC_ERROR;
        }
        decodeBlockIntocomponent(sampleBuffer.getIntStorage(), yComponent);
        if (failed()) {
            return;
        }
        storeJPEGStreamOn(jpegStream);
        storeIntegerofObjectwithValue(PriorDCValueIndex, comp, yComponent[PriorDCValueIndex]);
    }

    /*
     * In: anArray: IntegerArray new: DCTSize2 qt: IntegerArray new: DCTSize2.
     */

    /* JPEGReaderPlugin>>#primitiveIdctInt */
    @TruffleBoundary(transferToInterpreterOnException = false)
    public static void primitiveIdctInt(final NativeObject anArrayValue, final NativeObject qtValue) {
        int anACTerm;
        final int[] anArray;
        int dcval;
        int j;
        final int[] qt;
        int row;
        int t0;
        int t1;
        int t10;
        int t11;
        int t12;
        int t13;
        int t2;
        int t3;
        int v;
        final int[] ws = new int[64];
        int z1;
        int z2;
        int z3;
        int z4;
        int z5;

        qt = qtValue.getIntStorage();
        anArray = anArrayValue.getIntStorage();
        /* begin idctBlockInt:qt: */
        for (int i = 0; i < DCTSize; i += 1) {
            anACTerm = -1;
            for (row = 1; row < DCTSize; row += 1) {
                if (anACTerm == -1 && anArray[row * DCTSize + i] != 0) {
                    anACTerm = row;
                }
            }
            if (anACTerm == -1) {
                dcval = (int) ((long) (anArray[i] * qt[0]) << Pass1Bits);
                for (j = 0; j < DCTSize; j += 1) {
                    ws[j * DCTSize + i] = dcval;
                }
            } else {
                z2 = anArray[DCTSize * 2 + i] * qt[DCTSize * 2 + i];
                z3 = anArray[DCTSize * 6 + i] * qt[DCTSize * 6 + i];
                z1 = (z2 + z3) * FIXn0n541196100;
                t2 = z1 + z3 * -FIXn1n847759065;
                t3 = z1 + z2 * FIXn0n765366865;
                z2 = anArray[i] * qt[i];
                z3 = anArray[DCTSize * 4 + i] * qt[DCTSize * 4 + i];
                t0 = (int) ((long) (z2 + z3) << ConstBits);
                t1 = (int) ((long) (z2 - z3) << ConstBits);
                t10 = t0 + t3;
                t13 = t0 - t3;
                t11 = t1 + t2;
                t12 = t1 - t2;
                t0 = anArray[DCTSize * 7 + i] * qt[DCTSize * 7 + i];
                t1 = anArray[DCTSize * 5 + i] * qt[DCTSize * 5 + i];
                t2 = anArray[DCTSize * 3 + i] * qt[DCTSize * 3 + i];
                t3 = anArray[DCTSize + i] * qt[DCTSize + i];
                z1 = t0 + t3;
                z2 = t1 + t2;
                z3 = t0 + t2;
                z4 = t1 + t3;
                z5 = (z3 + z4) * FIXn1n175875602;
                t0 = t0 * FIXn0n298631336;
                t1 = t1 * FIXn2n053119869;
                t2 = t2 * FIXn3n072711026;
                t3 = t3 * FIXn1n501321110;
                z1 = z1 * -FIXn0n899976223;
                z2 = z2 * -FIXn2n562915447;
                z3 = z3 * -FIXn1n961570560;
                z4 = z4 * -FIXn0n390180644;
                z3 += z5;
                z4 += z5;
                t0 = t0 + z1 + z3;
                t1 = t1 + z2 + z4;
                t2 = t2 + z2 + z3;
                t3 = t3 + z1 + z4;
                ws[i] = (t10 + t3) / Pass1Div;
                ws[DCTSize * 7 + i] = (t10 - t3) / Pass1Div;
                ws[DCTSize + i] = (t11 + t2) / Pass1Div;
                ws[DCTSize * 6 + i] = (t11 - t2) / Pass1Div;
                ws[DCTSize * 2 + i] = (t12 + t1) / Pass1Div;
                ws[DCTSize * 5 + i] = (t12 - t1) / Pass1Div;
                ws[DCTSize * 3 + i] = (t13 + t0) / Pass1Div;
                ws[DCTSize * 4 + i] = (t13 - t0) / Pass1Div;
            }
        }
        for (int i = 0; i <= DCTSize2 - DCTSize; i += DCTSize) {
            z2 = ws[i + 2];
            z3 = ws[i + 6];
            z1 = (z2 + z3) * FIXn0n541196100;
            t2 = z1 + z3 * -FIXn1n847759065;
            t3 = z1 + z2 * FIXn0n765366865;
            t0 = (int) (Integer.toUnsignedLong(ws[i] + ws[i + 4]) << ConstBits);
            t1 = (int) (Integer.toUnsignedLong(ws[i] - ws[i + 4]) << ConstBits);
            t10 = t0 + t3;
            t13 = t0 - t3;
            t11 = t1 + t2;
            t12 = t1 - t2;
            t0 = ws[i + 7];
            t1 = ws[i + 5];
            t2 = ws[i + 3];
            t3 = ws[i + 1];
            z1 = t0 + t3;
            z2 = t1 + t2;
            z3 = t0 + t2;
            z4 = t1 + t3;
            z5 = (z3 + z4) * FIXn1n175875602;
            t0 = t0 * FIXn0n298631336;
            t1 = t1 * FIXn2n053119869;
            t2 = t2 * FIXn3n072711026;
            t3 = t3 * FIXn1n501321110;
            z1 = z1 * -FIXn0n899976223;
            z2 = z2 * -FIXn2n562915447;
            z3 = z3 * -FIXn1n961570560;
            z4 = z4 * -FIXn0n390180644;
            z3 += z5;
            z4 += z5;
            t0 = t0 + z1 + z3;
            t1 = t1 + z2 + z4;
            t2 = t2 + z2 + z3;
            t3 = t3 + z1 + z4;
            v = (t10 + t3) / Pass2Div + SampleOffset;
            v = Math.min(v, MaxSample);
            v = Math.max(v, 0);
            anArray[i] = v;
            v = (t10 - t3) / Pass2Div + SampleOffset;
            v = Math.min(v, MaxSample);
            v = Math.max(v, 0);
            anArray[i + 7] = v;
            v = (t11 + t2) / Pass2Div + SampleOffset;
            v = Math.min(v, MaxSample);
            v = Math.max(v, 0);
            anArray[i + 1] = v;
            v = (t11 - t2) / Pass2Div + SampleOffset;
            v = Math.min(v, MaxSample);
            v = Math.max(v, 0);
            anArray[i + 6] = v;
            v = (t12 + t1) / Pass2Div + SampleOffset;
            v = Math.min(v, MaxSample);
            v = Math.max(v, 0);
            anArray[i + 2] = v;
            v = (t12 - t1) / Pass2Div + SampleOffset;
            v = Math.min(v, MaxSample);
            v = Math.max(v, 0);
            anArray[i + 5] = v;
            v = (t13 + t0) / Pass2Div + SampleOffset;
            v = Math.min(v, MaxSample);
            v = Math.max(v, 0);
            anArray[i + 3] = v;
            v = (t13 - t0) / Pass2Div + SampleOffset;
            v = Math.min(v, MaxSample);
            v = Math.max(v, 0);
            anArray[i + 4] = v;
        }
    }

    /* JPEGReaderPlugin>>#scaleAndSignExtend:inFieldWidth: */
    private static int scaleAndSignExtendinFieldWidth(final int aNumber, final int w) {
        if (aNumber < 1 << w - 1) {
            return aNumber - (1 << w) + 1;
        } else {
            return aNumber;
        }
    }

    /* JPEGReaderPlugin>>#storeJPEGStreamOn: */
    private void storeJPEGStreamOn(final PointersObject streamOop) {
        storeIntegerofObjectwithValue(1, streamOop, jsPosition);
        storeIntegerofObjectwithValue(3, streamOop, jsBitBuffer);
        storeIntegerofObjectwithValue(4, streamOop, jsBitCount);
    }

    /* JPEGReaderPlugin>>#yColorComponentFrom: */
    private boolean yColorComponentFrom(final Object oop) {
        return colorComponentfrom(yComponent, oop) && colorComponentBlocksfrom(yBlocks, oop);
    }

    /*
     * POLYFILLS
     */

    private static Object fetchPointerofObject(final int index, final PointersObject pointersOop) {
        return pointersOop.instVarAt0Slow(index);
    }

    private int fetchIntegerofObject(final int index, final PointersObject arrayOop) {
        final Object value = fetchPointerofObject(index, arrayOop);
        if (value instanceof Long) {
            return ((Long) value).intValue();
        } else {
            failed = true;
            return 0;
        }
    }

    private static void storeIntegerofObjectwithValue(final int i, final PointersObject streamOop, final long value) {
        streamOop.instVarAtPut0Slow(i, value);
    }

    private static boolean isBytes(final Object object) {
        return SqueakGuards.isNativeObject(object) && ((NativeObject) object).isByteType();
    }

    private static boolean isWords(final Object object) {
        return SqueakGuards.isNativeObject(object) && ((NativeObject) object).isIntType();
    }

    private boolean failed() {
        return failed;
    }

    private static boolean isPointers(final Object pointerOop) {
        return SqueakGuards.isPointersObject(pointerOop);
    }

    private static int slotSizeOf(final PointersObject pointerOop) {
        return pointerOop.size();
    }
}
