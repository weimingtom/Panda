package org.panda_lang.panda.implementation.interpreter.lexer;

import org.panda_lang.framework.interpreter.lexer.TokenRepresentation;
import org.panda_lang.framework.interpreter.token.Token;

public class PandaTokenRepresentation implements TokenRepresentation {

    private final Token token;
    private final int line;

    public PandaTokenRepresentation(Token token, int line) {
        this.token = token;
        this.line = line;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token.getTokenValue() + " [ at line " + line + " ]";
    }

}
