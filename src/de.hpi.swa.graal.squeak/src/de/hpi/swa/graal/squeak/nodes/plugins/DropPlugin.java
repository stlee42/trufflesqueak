package de.hpi.swa.graal.squeak.nodes.plugins;

import java.util.List;

import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.ImportStatic;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.dsl.Specialization;

import de.hpi.swa.graal.squeak.model.CompiledMethodObject;
import de.hpi.swa.graal.squeak.nodes.primitives.AbstractPrimitiveFactoryHolder;
import de.hpi.swa.graal.squeak.nodes.primitives.AbstractPrimitiveNode;
import de.hpi.swa.graal.squeak.nodes.primitives.SqueakPrimitive;

public final class DropPlugin extends AbstractPrimitiveFactoryHolder {
    protected static String[] fileList = new String[0];

    public static void updateFileList(final String[] newList) {
        fileList = newList;
    }

    public static int getFileListSize() {
        return fileList.length;
    }

    @Override
    public List<? extends NodeFactory<? extends AbstractPrimitiveNode>> getFactories() {
        return DropPluginFactory.getFactories();
    }

    @ImportStatic(DropPlugin.class)
    @GenerateNodeFactory
    @SqueakPrimitive(name = "primitiveDropRequestFileHandle")
    protected abstract static class PrimDropRequestFileHandleNode extends AbstractPrimitiveNode {
        protected PrimDropRequestFileHandleNode(final CompiledMethodObject method, final int numArguments) {
            super(method, numArguments);
        }

        @Specialization(guards = "dropIndex <= getFileListSize()")
        protected final Object doRequest(@SuppressWarnings("unused") final Object receiver, final long dropIndex) {
            return FilePlugin.createFileHandleOrPrimFail(code.image.env.getTruffleFile(fileList[(int) dropIndex - 1]), false);
        }
    }

    @ImportStatic(DropPlugin.class)
    @GenerateNodeFactory
    @SqueakPrimitive(name = "primitiveDropRequestFileName")
    protected abstract static class PrimDropRequestFileNameNode extends AbstractPrimitiveNode {
        protected PrimDropRequestFileNameNode(final CompiledMethodObject method, final int numArguments) {
            super(method, numArguments);
        }

        @Specialization(guards = "dropIndex <= getFileListSize()")
        protected final Object doRequest(@SuppressWarnings("unused") final Object receiver, final long dropIndex) {
            return code.image.wrap(fileList[(int) dropIndex - 1]);
        }
    }
}