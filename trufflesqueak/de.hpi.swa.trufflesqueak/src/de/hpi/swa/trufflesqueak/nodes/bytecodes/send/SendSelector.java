package de.hpi.swa.trufflesqueak.nodes.bytecodes.send;

import de.hpi.swa.trufflesqueak.model.BaseSqueakObject;
import de.hpi.swa.trufflesqueak.model.CompiledCodeObject;

public class SendSelector extends AbstractSend {
    public SendSelector(CompiledCodeObject code, int index, BaseSqueakObject sel, int argcount) {
        super(code, index, sel, argcount);
    }
}
