package de.hpi.swa.graal.squeak.model;

import com.oracle.truffle.api.interop.ForeignAccess;
import com.oracle.truffle.api.interop.TruffleObject;

import de.hpi.swa.graal.squeak.instrumentation.BaseSqueakObjectMessageResolutionForeign;

public final class FrameMarker implements TruffleObject {

    @Override
    public String toString() {
        return "FrameMarker@" + Integer.toHexString(System.identityHashCode(this));
    }

    public ForeignAccess getForeignAccess() {
        return BaseSqueakObjectMessageResolutionForeign.ACCESS;
    }
}