package org.panda_lang.framework.structure;

/**
 * ExecutableCell is a mutable container for {@link Executable}
 */
public interface StatementCell {

    void setStatement(Statement statement);

    boolean isExecutable();

    Statement getStatement();

}
