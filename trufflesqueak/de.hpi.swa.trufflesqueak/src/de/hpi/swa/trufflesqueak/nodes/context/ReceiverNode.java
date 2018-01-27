package de.hpi.swa.trufflesqueak.nodes.context;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import de.hpi.swa.trufflesqueak.model.CompiledCodeObject;
import de.hpi.swa.trufflesqueak.nodes.SqueakNodeWithCode;
import de.hpi.swa.trufflesqueak.util.FrameAccess;

public abstract class ReceiverNode extends SqueakNodeWithCode {
    public static ReceiverNode create(CompiledCodeObject code) {
        return ReceiverNodeGen.create(code);
    }

    protected ReceiverNode(CompiledCodeObject code) {
        super(code);
    }

    @Specialization(guards = {"isVirtualized(frame, code)"})
    protected Object doReceiverVirtualized(VirtualFrame frame) {
        return FrameAccess.getReceiver(frame);
    }

    @Specialization(guards = {"!isVirtualized(frame, code)"})
    protected Object doReceiver(VirtualFrame frame) {
        return getContext(frame).getReceiver();
    }
}
