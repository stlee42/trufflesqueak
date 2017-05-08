package de.hpi.swa.trufflesqueak.nodes.primitives.impl;

import com.oracle.truffle.api.dsl.Specialization;

import de.hpi.swa.trufflesqueak.model.BaseSqueakObject;
import de.hpi.swa.trufflesqueak.model.CompiledMethodObject;
import de.hpi.swa.trufflesqueak.model.ImmediateCharacter;
import de.hpi.swa.trufflesqueak.nodes.primitives.PrimitiveBinaryOperation;

public abstract class PrimEquivalent extends PrimitiveBinaryOperation {
    public PrimEquivalent(CompiledMethodObject cm) {
        super(cm);
    }

    @Specialization
    boolean equivalent(long a, long b) {
        return a == b;
    }

    @Specialization
    boolean equivalent(boolean a, boolean b) {
        return a == b;
    }

    @Specialization
    boolean equivalent(ImmediateCharacter a, ImmediateCharacter b) {
        return a.getValue() == b.getValue();
    }

    @Specialization
    boolean equivalent(BaseSqueakObject a, BaseSqueakObject b) {
        return a == b;
    }
}
