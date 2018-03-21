package de.hpi.swa.trufflesqueak.nodes.primitives;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeFactory;

import de.hpi.swa.trufflesqueak.model.CompiledMethodObject;
import de.hpi.swa.trufflesqueak.nodes.SqueakNode;
import de.hpi.swa.trufflesqueak.nodes.context.ArgumentNode;
import de.hpi.swa.trufflesqueak.nodes.context.ReceiverAndArgumentsNode;
import de.hpi.swa.trufflesqueak.nodes.plugins.FilePlugin;
import de.hpi.swa.trufflesqueak.nodes.plugins.FloatArrayPlugin;
import de.hpi.swa.trufflesqueak.nodes.plugins.LargeIntegers;
import de.hpi.swa.trufflesqueak.nodes.plugins.MiscPrimitivePlugin;
import de.hpi.swa.trufflesqueak.nodes.plugins.TruffleSqueakPlugin;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.ArithmeticPrimitives;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.ArrayStreamPrimitives;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.BlockClosurePrimitives;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.ControlPrimitives;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.ControlPrimitives.PrimitiveFailedNode;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.IOPrimitives;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.MiscellaneousPrimitives;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.MiscellaneousPrimitives.SimulationPrimitiveNode;
import de.hpi.swa.trufflesqueak.nodes.primitives.impl.StoragePrimitives;

public abstract class PrimitiveNodeFactory {
    @CompilationFinal(dimensions = 1) private static final AbstractPrimitiveFactoryHolder[] indexPrimitives = new AbstractPrimitiveFactoryHolder[]{
                    new ArithmeticPrimitives(),
                    new ArrayStreamPrimitives(),
                    new BlockClosurePrimitives(),
                    new ControlPrimitives(),
                    new IOPrimitives(),
                    new MiscellaneousPrimitives(),
                    new StoragePrimitives()};
    @CompilationFinal(dimensions = 1) private static final AbstractPrimitiveFactoryHolder[] plugins = new AbstractPrimitiveFactoryHolder[]{
                    new FilePlugin(),
                    new FloatArrayPlugin(),
                    new LargeIntegers(),
                    new MiscPrimitivePlugin(),
                    new TruffleSqueakPlugin()};
    @CompilationFinal private static Map<Integer, NodeFactory<? extends AbstractPrimitiveNode>> primitiveTable;

    @TruffleBoundary
    public static AbstractPrimitiveNode forIndex(CompiledMethodObject method, int primitiveIndex) {
        if (264 <= primitiveIndex && primitiveIndex <= 520) {
            return ControlPrimitives.PrimQuickReturnReceiverVariableNode.create(method, primitiveIndex - 264);
        }
        NodeFactory<? extends AbstractPrimitiveNode> nodeFactory = getPrimitiveTable().get(primitiveIndex);
        if (nodeFactory != null) {
            return createInstance(method, nodeFactory, nodeFactory.getNodeClass().getAnnotation(SqueakPrimitive.class));
        }
        return null;
    }

    @TruffleBoundary
    public static AbstractPrimitiveNode forName(CompiledMethodObject method, String moduleName, String functionName) {
        if (moduleName.equals("BitBltPlugin") || moduleName.equals("B2DPlugin")) {
            return SimulationPrimitiveNode.create(method, moduleName, functionName, new SqueakNode[]{ReceiverAndArgumentsNode.create(method)});
        }
        for (AbstractPrimitiveFactoryHolder plugin : plugins) {
            if (!plugin.getClass().getSimpleName().equals(moduleName)) {
                continue;
            }
            try {
                List<? extends NodeFactory<? extends AbstractPrimitiveNode>> nodeFactories = plugin.getFactories();
                for (NodeFactory<? extends AbstractPrimitiveNode> nodeFactory : nodeFactories) {
                    Class<? extends AbstractPrimitiveNode> primitiveClass = nodeFactory.getNodeClass();
                    SqueakPrimitive primitive = primitiveClass.getAnnotation(SqueakPrimitive.class);
                    if (functionName.equals(primitive.name())) {
                        return createInstance(method, nodeFactory, primitive);
                    }
                }
            } catch (RuntimeException e) {
                break;
            }
        }
        return PrimitiveFailedNode.create(method);
    }

    private static AbstractPrimitiveNode createInstance(CompiledMethodObject method, NodeFactory<? extends AbstractPrimitiveNode> nodeFactory, SqueakPrimitive primitive) {
        if (primitive.variableArguments()) {
            return nodeFactory.createNode(method, new SqueakNode[]{ReceiverAndArgumentsNode.create(method)});
        }
        int numArgs = primitive.numArguments();
        SqueakNode[] arguments = new SqueakNode[numArgs];
        for (int i = 0; i < numArgs; i++) {
            arguments[i] = ArgumentNode.create(method, i);
        }
        return nodeFactory.createNode(method, arguments);
    }

    private static Map<Integer, NodeFactory<? extends AbstractPrimitiveNode>> getPrimitiveTable() {
        if (primitiveTable == null) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            primitiveTable = new HashMap<>();
            fillPrimitiveTable(indexPrimitives);
            fillPrimitiveTable(plugins);
        }
        return primitiveTable;
    }

    private static void fillPrimitiveTable(AbstractPrimitiveFactoryHolder[] primitiveFactories) {
        for (AbstractPrimitiveFactoryHolder primitiveFactory : primitiveFactories) {
            List<? extends NodeFactory<? extends AbstractPrimitiveNode>> nodeFactories = primitiveFactory.getFactories();
            for (NodeFactory<? extends AbstractPrimitiveNode> nodeFactory : nodeFactories) {
                Class<? extends AbstractPrimitiveNode> primitiveClass = nodeFactory.getNodeClass();
                SqueakPrimitive primitive = primitiveClass.getAnnotation(SqueakPrimitive.class);
                if (primitive != null) {
                    if (primitive.index() > 0) {
                        addEntryToPrimitiveTable(primitive.index(), nodeFactory);
                    }
                    for (int index : primitive.indices()) {
                        addEntryToPrimitiveTable(index, nodeFactory);
                    }
                }
            }
        }
    }

    private static void addEntryToPrimitiveTable(int index, NodeFactory<? extends AbstractPrimitiveNode> nodeFactory) {
        assert !primitiveTable.containsKey(index); // primitives are not allowed to override others
        primitiveTable.put(index, nodeFactory);
    }
}
