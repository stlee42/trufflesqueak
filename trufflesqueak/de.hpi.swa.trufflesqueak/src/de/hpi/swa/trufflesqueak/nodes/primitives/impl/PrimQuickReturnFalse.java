package de.hpi.swa.trufflesqueak.nodes.primitives.impl;

import com.oracle.truffle.api.frame.VirtualFrame;

import de.hpi.swa.trufflesqueak.model.CompiledMethodObject;
import de.hpi.swa.trufflesqueak.nodes.primitives.PrimitiveNode;

public class PrimQuickReturnFalse extends PrimitiveNode {
    public PrimQuickReturnFalse(CompiledMethodObject code) {
        super(code);
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return code.image.sqFalse;
    }
}