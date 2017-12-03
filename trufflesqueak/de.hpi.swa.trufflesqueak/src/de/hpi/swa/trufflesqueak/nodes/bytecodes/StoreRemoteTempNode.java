package de.hpi.swa.trufflesqueak.nodes.bytecodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.StandardTags;

import de.hpi.swa.trufflesqueak.model.CompiledCodeObject;
import de.hpi.swa.trufflesqueak.nodes.context.ObjectAtPutNode;

public class StoreRemoteTempNode extends RemoteTempBytecodeNode {
    @Child ObjectAtPutNode writeTempNode;

    public StoreRemoteTempNode(CompiledCodeObject code, int index, int indexInArray, int indexOfArray) {
        super(code, index, indexOfArray);
        writeTempNode = ObjectAtPutNode.create(indexInArray);
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return writeTempNode.executeWrite(getTempArray(frame), top(frame));
    }

    @Override
    protected boolean isTaggedWith(Class<?> tag) {
        if (tag == StandardTags.StatementTag.class) {
            return getSourceSection().isAvailable();
        }
        return false;
    }
}
