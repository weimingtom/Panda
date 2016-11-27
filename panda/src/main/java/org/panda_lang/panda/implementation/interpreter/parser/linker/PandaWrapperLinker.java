package org.panda_lang.panda.implementation.interpreter.parser.linker;

import org.panda_lang.framework.interpreter.parser.linker.WrapperLinker;
import org.panda_lang.framework.structure.Wrapper;

import java.util.Stack;

public class PandaWrapperLinker implements WrapperLinker {

    private final Stack<Wrapper> wrapperStack;

    public PandaWrapperLinker() {
        this.wrapperStack = new Stack<>();
    }

    @Override
    public void pushWrapper(Wrapper wrapper) {
        wrapperStack.push(wrapper);
    }

    @Override
    public Wrapper popWrapper() {
        return wrapperStack.pop();
    }

    @Override
    public Wrapper getCurrentWrapper() {
        return wrapperStack.peek();
    }

    @Override
    public int getNextID() {
        return wrapperStack.size();
    }

}
