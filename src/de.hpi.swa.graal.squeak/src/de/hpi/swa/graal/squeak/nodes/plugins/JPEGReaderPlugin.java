package de.hpi.swa.graal.squeak.nodes.plugins;

import java.util.List;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.ImportStatic;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.dsl.Specialization;

import de.hpi.swa.graal.squeak.exceptions.PrimitiveExceptions.PrimitiveFailed;
import de.hpi.swa.graal.squeak.model.AbstractSqueakObject;
import de.hpi.swa.graal.squeak.model.ArrayObject;
import de.hpi.swa.graal.squeak.model.CompiledMethodObject;
import de.hpi.swa.graal.squeak.model.NativeObject;
import de.hpi.swa.graal.squeak.model.PointersObject;
import de.hpi.swa.graal.squeak.nodes.SqueakGuards;
import de.hpi.swa.graal.squeak.nodes.primitives.AbstractPrimitiveFactoryHolder;
import de.hpi.swa.graal.squeak.nodes.primitives.AbstractPrimitiveNode;
import de.hpi.swa.graal.squeak.nodes.primitives.PrimitiveInterfaces.QuinaryPrimitive;
import de.hpi.swa.graal.squeak.nodes.primitives.PrimitiveInterfaces.SenaryPrimitive;
import de.hpi.swa.graal.squeak.nodes.primitives.PrimitiveInterfaces.TernaryPrimitive;
import de.hpi.swa.graal.squeak.nodes.primitives.PrimitiveInterfaces.UnaryPrimitive;
import de.hpi.swa.graal.squeak.nodes.primitives.SqueakPrimitive;

/* Automatically generated by
    VMPluginCodeGenerator * VMMaker.oscog-eem.2480 uuid: bb3ffda7-8241-4dea-b886-d656e474b6c1
   from
    JPEGReaderPlugin * VMMaker.oscog-eem.2480 uuid: bb3ffda7-8241-4dea-b886-d656e474b6c1
 */

public final class JPEGReaderPlugin extends AbstractPrimitiveFactoryHolder {

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
    private static int[] acTable;
    private static int acTableSize;
    private static int[][] cbBlocks = new int[128][];
    private static int[] cbComponent = new int[11];
    private static int[][] crBlocks = new int[128][];
    private static int[] crComponent = new int[11];
    private static int[] dcTable;
    private static int dcTableSize;
    private static int ditherMask;

    private static int[] jpegBits;
    private static int jpegBitsSize;
    private static final int[] jpegNaturalOrder = {
                    0, 1, 8, 16, 9, 2, 3, 10,
                    17, 24, 32, 25, 18, 11, 4, 5,
                    12, 19, 26, 33, 40, 48, 41, 34,
                    27, 20, 13, 6, 7, 14, 21, 28,
                    35, 42, 49, 56, 57, 50, 43, 36,
                    29, 22, 15, 23, 30, 37, 44, 51,
                    58, 59, 52, 45, 38, 31, 39, 46,
                    53, 60, 61, 54, 47, 55, 62, 63
    };
    private static int jsBitBuffer;
    private static int jsBitCount;
    private static byte[] jsCollection;
    private static int jsPosition;
    private static int jsReadLimit;
    private static final String moduleName = "JPEGReaderPlugin * VMMaker.oscog-eem.2480 (GraalSqueak)";
    private static int[] residuals;
    private static int[][] yBlocks = new int[128][];
    private static int[] yComponent = new int[11];

    private static boolean failed = false;

    @GenerateNodeFactory
    @SqueakPrimitive(names = "primitiveColorConvertGrayscaleMCU")
    protected abstract static class PrimColorConvertGrayscaleMCUNode extends AbstractPrimitiveNode implements QuinaryPrimitive {

        protected PrimColorConvertGrayscaleMCUNode(final CompiledMethodObject method) {
            super(method);
        }

        @Specialization(guards = {"bits.isIntType()", "residualArray.isIntType()", "residualArray.getIntLength() == 3"})
        @TruffleBoundary
        protected static final Object doColor(final Object receiver, final ArrayObject componentArray, final NativeObject bits, final NativeObject residualArray, final long mask) {
            return primitiveColorConvertGrayscaleMCU(receiver, componentArray, bits, residualArray, mask);
        }
    }

    @GenerateNodeFactory
    @SqueakPrimitive(names = "primitiveColorConvertMCU")
    protected abstract static class PrimColorConvertMCUNode extends AbstractPrimitiveNode implements QuinaryPrimitive {

        protected PrimColorConvertMCUNode(final CompiledMethodObject method) {
            super(method);
        }

        @Specialization(guards = {"componentArray.size() == 3", "bits.isIntType()", "residualArray.isIntType()", "residualArray.getIntLength() == 3"})
        @TruffleBoundary
        protected static final Object doColor(final Object receiver, final PointersObject componentArray, final NativeObject bits, final NativeObject residualArray, final long mask) {
            return primitiveColorConvertMCU(receiver, componentArray, bits, residualArray, mask);
        }
    }

    @ImportStatic(JPEGReaderPlugin.class)
    @GenerateNodeFactory
    @SqueakPrimitive(names = "primitiveDecodeMCU")
    protected abstract static class PrimDecodeMCUNode extends AbstractPrimitiveNode implements SenaryPrimitive {

        protected PrimDecodeMCUNode(final CompiledMethodObject method) {
            super(method);
        }

        @Specialization(guards = {"sampleBuffer.isIntType()", "sampleBuffer.getIntLength() == DCTSize2", "comp.size() >= MinComponentSize", "dcTableValue.isIntType()", "acTableValue.isIntType()",
                        "jpegStream.size() >= 5"})
        @TruffleBoundary
        protected static final Object doColor(final Object receiver, final NativeObject sampleBuffer, final PointersObject comp, final NativeObject dcTableValue, final NativeObject acTableValue,
                        final PointersObject jpegStream) {
            return primitiveDecodeMCU(receiver, sampleBuffer, comp, dcTableValue, acTableValue, jpegStream);
        }
    }

    @ImportStatic(JPEGReaderPlugin.class)
    @GenerateNodeFactory
    @SqueakPrimitive(names = "primitiveIdctInt")
    protected abstract static class PrimIdctIntNode extends AbstractPrimitiveNode implements TernaryPrimitive {

        protected PrimIdctIntNode(final CompiledMethodObject method) {
            super(method);
        }

        @Specialization(guards = {"anArray.isIntType()", "anArray.getIntLength() == DCTSize2", "qt.isIntType()", "qt.getIntLength() == DCTSize2"})
        @TruffleBoundary
        protected static final Object doColor(final Object receiver, final NativeObject anArray, final NativeObject qt) {
            return primitiveIdctInt(receiver, anArray, qt);
        }
    }

    @GenerateNodeFactory
    @SqueakPrimitive(names = "primGetModuleName")
    public abstract static class PrimGetModuleNameNode extends AbstractPrimitiveNode implements UnaryPrimitive {

        public PrimGetModuleNameNode(final CompiledMethodObject method) {
            super(method);
        }

        @Specialization
        protected final NativeObject doGet(@SuppressWarnings("unused") final AbstractSqueakObject rcvr) {
            return method.image.wrap(moduleName);
        }
    }

    /* JPEGReaderPlugin>>#cbColorComponentFrom: */
    private static boolean cbColorComponentFrom(final Object oop) {
        return colorComponentfrom(cbComponent, oop) && colorComponentBlocksfrom(cbBlocks, oop);
    }

    /* JPEGReaderPlugin>>#colorComponentBlocks:from: */
    private static boolean colorComponentBlocksfrom(final int[][] blocks, final Object oop) {
        final Object arrayOop;
        Object blockOop;
        final int max;

        if (!isPointers(oop)) {
            return false;
        }
        final PointersObject pointersOop = (PointersObject) oop;
        if (slotSizeOf(pointersOop) < MinComponentSize) {
            return false;
        }
        arrayOop = fetchPointerofObject(MCUBlockIndex, pointersOop);
        if (!isPointers(arrayOop)) {
            return false;
        }
        final PointersObject arrayPointersOop = (PointersObject) arrayOop;
        max = slotSizeOf(arrayPointersOop);
        if (max > MaxMCUBlocks) {
            return false;
        }
        for (int i = 0; i < max; i += 1) {
            blockOop = fetchPointerofObject(i, arrayPointersOop);
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
    private static boolean colorComponentfrom(final int[] aColorComponent, final Object oop) {
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
    private static boolean crColorComponentFrom(final Object oop) {
        return colorComponentfrom(crComponent, oop) && colorComponentBlocksfrom(crBlocks, oop);
    }

    /* JPEGReaderPlugin>>#decodeBlockInto:component: */
    private static void decodeBlockIntocomponent(final int[] anArray, final int[] aColorComponent) {
        int bits;
        int byteValue;
        int i;
        int index;
        int zeroCount;

        byteValue = jpegDecodeValueFromsize(dcTable, dcTableSize);
        if (byteValue < 0) {
            throw new PrimitiveFailed();
        }
        if (byteValue != 0) {
            bits = getBits(byteValue);
            byteValue = scaleAndSignExtendinFieldWidth(bits, byteValue);
        }
        byteValue = aColorComponent[PriorDCValueIndex] = aColorComponent[PriorDCValueIndex] + byteValue;
        anArray[0] = byteValue;
        for (i = 1; i < DCTSize2; i += 1) {
            anArray[i] = 0;
        }
        index = 1;
        while (index < DCTSize2) {
            byteValue = jpegDecodeValueFromsize(acTable, acTableSize);
            if (byteValue < 0) {
                throw new PrimitiveFailed();
            }
            zeroCount = (int) (Integer.toUnsignedLong(byteValue) >> 4);
            byteValue = byteValue & 15;
            if (byteValue != 0) {
                index += zeroCount;
                bits = getBits(byteValue);
                byteValue = scaleAndSignExtendinFieldWidth(bits, byteValue);
                if (index < 0 || index >= DCTSize2) {
                    throw new PrimitiveFailed();
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
    private static int fillBuffer() {
        byte byteValue;

        while (jsBitCount <= 16) {
            if (jsPosition >= jsReadLimit) {
                return jsBitCount;
            }
            byteValue = jsCollection[jsPosition];
            jsPosition += 1;
            if (byteValue == (byte) 0xFF) {
                /* peek for 00 */
                if (!(jsPosition < jsReadLimit && jsCollection[jsPosition] == 0)) {
                    jsPosition -= 1;
                    return jsBitCount;
                }
                jsPosition += 1;
            }
            jsBitBuffer = (int) (Integer.toUnsignedLong(jsBitBuffer) << 8) | Byte.toUnsignedInt(byteValue);
            jsBitCount += 8;
        }
        return jsBitCount;
    }

    /* JPEGReaderPlugin>>#getBits: */
    private static int getBits(final int requestedBits) {
        final int value;

        if (requestedBits > jsBitCount) {
            fillBuffer();
            if (requestedBits > jsBitCount) {
                return -1;
            }
        }
        jsBitCount -= requestedBits;
        value = (int) (Integer.toUnsignedLong(jsBitBuffer) >> jsBitCount);
        jsBitBuffer = jsBitBuffer & (1 << jsBitCount) - 1;
        return value;
    }

    /** JPEGReaderPlugin>>#idctBlockInt:qt: inlined. */

    /* Decode the next value in the receiver using the given huffman table. */

    /* JPEGReaderPlugin>>#jpegDecodeValueFrom:size: */
    private static int jpegDecodeValueFromsize(final int[] table, final int tableSize) {
        int bits;
        int bitsNeeded;
        int index;
        int tableIndex;
        int value;

        /* Initial bits needed */
        bitsNeeded = (int) (Integer.toUnsignedLong(table[0]) >> 24);
        if (bitsNeeded > MaxBits) {
            return -1;
        }

        /* First real table */
        tableIndex = 2;
        while (true) {

            /* Get bits */
            bits = getBits(bitsNeeded);
            if (bits < 0) {
                return -1;
            }
            index = tableIndex + bits - 1;
            if (index >= tableSize) {
                return -1;
            }

            /* Lookup entry in table */
            value = table[index];
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
    private static boolean loadJPEGStreamFrom(final PointersObject streamOop) {
        final Object oop;
        final int sz;

        oop = fetchPointerofObject(0, streamOop);
        if (!isBytes(oop)) {
            return false;
        }
        jsCollection = ((NativeObject) oop).getByteStorage();
        sz = jsCollection.length;
        jsPosition = fetchIntegerofObject(1, streamOop);
        jsReadLimit = fetchIntegerofObject(2, streamOop);
        jsBitBuffer = fetchIntegerofObject(3, streamOop);
        jsBitCount = fetchIntegerofObject(4, streamOop);
        if (failed()) {
            return false;
        }
        if (sz < jsReadLimit) {
            return false;
        }
        return 0 <= jsPosition && jsPosition < jsReadLimit;
    }

    /* JPEGReaderPlugin>>#nextSampleCb */
    private static int nextSampleCb() {
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
    private static int nextSampleY() {
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
    private static Object primitiveColorConvertGrayscaleMCU(final Object receiver, final ArrayObject componentArray, final NativeObject bits, final NativeObject residualArray, final long mask) {
        ditherMask = (int) mask;
        residuals = residualArray.getIntStorage();
        jpegBits = bits.getIntStorage();
        jpegBitsSize = jpegBits.length;
        if (!yColorComponentFrom(componentArray)) {
            throw new PrimitiveFailed();
        }
        /* begin colorConvertGrayscaleMCU */
        yComponent[CurrentXIndex] = 0;
        yComponent[CurrentYIndex] = 0;
        int y;
        for (int i = 0; i < jpegBitsSize; i += 1) {
            y = nextSampleY();
            y += residuals[GreenIndex];
            y = y < MaxSample ? y : MaxSample;
            residuals[GreenIndex] = y & ditherMask;
            y = y & MaxSample - ditherMask;
            y = y < 1 ? 1 : y;
            jpegBits[i] = 0xFF000000 + (int) ((long) y << 16) + (int) ((long) y << 8) + y;
        }
        return receiver;
    }

    /*
     * Requires: Array with: 3*JPEGColorComponent bits WordArray with: 3*Integer (residuals)
     * ditherMask
     */

    /* JPEGReaderPlugin>>#primitiveColorConvertMCU */
    private static Object primitiveColorConvertMCU(final Object receiver, final PointersObject componentArray, final NativeObject bits, final NativeObject residualArray, final long mask) {
        int blockIndex;
        int blue;
        int cb;
        int cr;
        int curX;
        int dx;
        int dy;
        int green;
        int red;
        int sample;
        int sampleIndex;
        int sx;
        int sy;
        int y;

        ditherMask = (int) mask;
        residuals = residualArray.getIntStorage();
        jpegBits = bits.getIntStorage();
        jpegBitsSize = jpegBits.length;
        if (!yColorComponentFrom(fetchPointerofObject(0, componentArray))) {
            throw new PrimitiveFailed();
        }
        if (!cbColorComponentFrom(fetchPointerofObject(1, componentArray))) {
            throw new PrimitiveFailed();
        }
        if (!crColorComponentFrom(fetchPointerofObject(2, componentArray))) {
            throw new PrimitiveFailed();
        }
        /* begin colorConvertMCU */
        yComponent[CurrentXIndex] = 0;
        yComponent[CurrentYIndex] = 0;
        cbComponent[CurrentXIndex] = 0;
        cbComponent[CurrentYIndex] = 0;
        crComponent[CurrentXIndex] = 0;
        crComponent[CurrentYIndex] = 0;
        for (int i = 0; i < jpegBitsSize; i += 1) {
            y = nextSampleY();
            cb = nextSampleCb();
            cb -= SampleOffset;
            /* begin nextSampleCr */
            dx = curX = crComponent[CurrentXIndex];
            dy = crComponent[CurrentYIndex];
            sx = crComponent[HScaleIndex];
            sy = crComponent[VScaleIndex];
            if (!(sx == 0 && sy == 0)) {
                dx = dx / sx;
                dy = dy / sy;
            }
            blockIndex = (int) ((Integer.toUnsignedLong(dy) >> 3) * crComponent[BlockWidthIndex] + (Integer.toUnsignedLong(dx) >> 3));
            sampleIndex = (int) ((Integer.toUnsignedLong(dy & 7) << 3) + (dx & 7));
            sample = crBlocks[blockIndex][sampleIndex];
            curX += 1;
            if (curX < crComponent[MCUWidthIndex] * 8) {
                crComponent[CurrentXIndex] = curX;
            } else {
                crComponent[CurrentXIndex] = 0;
                crComponent[CurrentYIndex] = crComponent[CurrentYIndex] + 1;
            }
            cr = sample;
            cr -= SampleOffset;
            red = y + FIXn1n40200 * cr / 65536 + residuals[RedIndex];
            red = red < MaxSample ? red : MaxSample;
            red = red < 0 ? 0 : red;
            residuals[RedIndex] = red & ditherMask;
            red = red & MaxSample - ditherMask;
            red = red < 1 ? 1 : red;
            green = y - FIXn0n34414 * cb / 65536 - FIXn0n71414 * cr / 65536 + residuals[GreenIndex];
            green = green < MaxSample ? green : MaxSample;
            green = green < 0 ? 0 : green;
            residuals[GreenIndex] = green & ditherMask;
            green = green & MaxSample - ditherMask;
            green = green < 1 ? 1 : green;
            blue = y + FIXn1n77200 * cb / 65536 + residuals[BlueIndex];
            blue = blue < MaxSample ? blue : MaxSample;
            blue = blue < 0 ? 0 : blue;
            residuals[BlueIndex] = blue & ditherMask;
            blue = blue & MaxSample - ditherMask;
            blue = blue < 1 ? 1 : blue;
            jpegBits[i] = (int) (0xFF000000 + (Integer.toUnsignedLong(red) << 16) + (Integer.toUnsignedLong(green) << 8) + blue);
        }
        return receiver;
    }

    /*
     * In: anArray WordArray of: DCTSize2 aColorComponent JPEGColorComponent dcTable WordArray
     * acTable WordArray stream JPEGStream
     */

    /* JPEGReaderPlugin>>#primitiveDecodeMCU */
    private static Object primitiveDecodeMCU(final Object receiver, final NativeObject sampleBuffer, final PointersObject comp, final NativeObject dcTableValue, final NativeObject acTableValue,
                    final PointersObject jpegStream) {
        if (!loadJPEGStreamFrom(jpegStream)) {
            throw new PrimitiveFailed();
        }
        acTable = acTableValue.getIntStorage();
        acTableSize = acTable.length;
        dcTable = dcTableValue.getIntStorage();
        dcTableSize = dcTable.length;
        if (!colorComponentfrom(yComponent, comp)) {
            throw new PrimitiveFailed();
        }
        decodeBlockIntocomponent(sampleBuffer.getIntStorage(), yComponent);
        if (failed()) {
            return null;
        }
        storeJPEGStreamOn(jpegStream);
        storeIntegerofObjectwithValue(PriorDCValueIndex, comp, yComponent[PriorDCValueIndex]);
        return receiver;
    }

    /*
     * In: anArray: IntegerArray new: DCTSize2 qt: IntegerArray new: DCTSize2.
     */

    /* JPEGReaderPlugin>>#primitiveIdctInt */
    private static Object primitiveIdctInt(final Object receiver, final NativeObject anArrayValue, final NativeObject qtValue) {
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
                t2 = z1 + z3 * (0 - FIXn1n847759065);
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
                z1 = z1 * (0 - FIXn0n899976223);
                z2 = z2 * (0 - FIXn2n562915447);
                z3 = z3 * (0 - FIXn1n961570560);
                z4 = z4 * (0 - FIXn0n390180644);
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
            t2 = z1 + z3 * (0 - FIXn1n847759065);
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
            z1 = z1 * (0 - FIXn0n899976223);
            z2 = z2 * (0 - FIXn2n562915447);
            z3 = z3 * (0 - FIXn1n961570560);
            z4 = z4 * (0 - FIXn0n390180644);
            z3 += z5;
            z4 += z5;
            t0 = t0 + z1 + z3;
            t1 = t1 + z2 + z4;
            t2 = t2 + z2 + z3;
            t3 = t3 + z1 + z4;
            v = (t10 + t3) / Pass2Div + SampleOffset;
            v = v < MaxSample ? v : MaxSample;
            v = v < 0 ? 0 : v;
            anArray[i] = v;
            v = (t10 - t3) / Pass2Div + SampleOffset;
            v = v < MaxSample ? v : MaxSample;
            v = v < 0 ? 0 : v;
            anArray[i + 7] = v;
            v = (t11 + t2) / Pass2Div + SampleOffset;
            v = v < MaxSample ? v : MaxSample;
            v = v < 0 ? 0 : v;
            anArray[i + 1] = v;
            v = (t11 - t2) / Pass2Div + SampleOffset;
            v = v < MaxSample ? v : MaxSample;
            v = v < 0 ? 0 : v;
            anArray[i + 6] = v;
            v = (t12 + t1) / Pass2Div + SampleOffset;
            v = v < MaxSample ? v : MaxSample;
            v = v < 0 ? 0 : v;
            anArray[i + 2] = v;
            v = (t12 - t1) / Pass2Div + SampleOffset;
            v = v < MaxSample ? v : MaxSample;
            v = v < 0 ? 0 : v;
            anArray[i + 5] = v;
            v = (t13 + t0) / Pass2Div + SampleOffset;
            v = v < MaxSample ? v : MaxSample;
            v = v < 0 ? 0 : v;
            anArray[i + 3] = v;
            v = (t13 - t0) / Pass2Div + SampleOffset;
            v = v < MaxSample ? v : MaxSample;
            v = v < 0 ? 0 : v;
            anArray[i + 4] = v;
        }
        return receiver;
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
    private static void storeJPEGStreamOn(final PointersObject streamOop) {
        storeIntegerofObjectwithValue(1, streamOop, jsPosition);
        storeIntegerofObjectwithValue(3, streamOop, jsBitBuffer);
        storeIntegerofObjectwithValue(4, streamOop, jsBitCount);
    }

    /* JPEGReaderPlugin>>#yColorComponentFrom: */
    private static boolean yColorComponentFrom(final Object oop) {
        return colorComponentfrom(yComponent, oop) && colorComponentBlocksfrom(yBlocks, oop);
    }

    /*
     * POLYFILLS
     */

    private static Object fetchPointerofObject(final int index, final PointersObject pointersOop) {
        return pointersOop.getPointer(index);
    }

    private static int fetchIntegerofObject(final int index, final PointersObject arrayOop) {
        final Object value = fetchPointerofObject(index, arrayOop);
        if (value instanceof Long) {
            return ((Long) value).intValue();
        } else {
            failed = true;
            return 0;
        }
    }

    private static void storeIntegerofObjectwithValue(final int i, final PointersObject streamOop, final long value) {
        streamOop.atput0(i, value);
    }

    private static boolean isBytes(final Object object) {
        return SqueakGuards.isNativeObject(object) && ((NativeObject) object).isByteType();
    }

    private static boolean isWords(final Object object) {
        return SqueakGuards.isNativeObject(object) && ((NativeObject) object).isIntType();
    }

    private static boolean failed() {
        return failed;
    }

    private static boolean isPointers(final Object pointerOop) {
        return SqueakGuards.isPointersObject(pointerOop);
    }

    private static int slotSizeOf(final PointersObject pointerOop) {
        return pointerOop.size();
    }

    @Override
    public List<? extends NodeFactory<? extends AbstractPrimitiveNode>> getFactories() {
        return JPEGReaderPluginFactory.getFactories();
    }
}
