package org.panda_lang.panda.implementation.interpreter.parser.util;

import org.panda_lang.framework.interpreter.SourceFile;
import org.panda_lang.framework.interpreter.lexer.TokenReader;
import org.panda_lang.framework.interpreter.lexer.TokenizedSource;
import org.panda_lang.framework.interpreter.parser.Parser;
import org.panda_lang.framework.interpreter.parser.ParserInfo;
import org.panda_lang.framework.interpreter.parser.ParserPipeline;
import org.panda_lang.framework.interpreter.parser.util.Components;
import org.panda_lang.framework.structure.Script;
import org.panda_lang.framework.structure.Statement;
import org.panda_lang.framework.structure.Wrapper;
import org.panda_lang.framework.util.FileUtils;
import org.panda_lang.panda.Panda;
import org.panda_lang.panda.PandaComposition;
import org.panda_lang.panda.implementation.interpreter.PandaInterpreter;
import org.panda_lang.panda.implementation.interpreter.lexer.PandaLexer;
import org.panda_lang.panda.implementation.interpreter.lexer.PandaTokenReader;
import org.panda_lang.panda.implementation.interpreter.parser.OverallParser;
import org.panda_lang.panda.implementation.interpreter.parser.PandaParserException;
import org.panda_lang.panda.implementation.interpreter.parser.PandaParserInfo;
import org.panda_lang.panda.implementation.interpreter.parser.ParserComposition;
import org.panda_lang.panda.implementation.interpreter.parser.linker.PandaWrapperLinker;
import org.panda_lang.panda.implementation.structure.PandaScript;

import java.io.File;

public class SourceFileParser implements Parser {

    private final PandaInterpreter interpreter;

    public SourceFileParser(PandaInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public Script parse(SourceFile sourceFile) {
        File file = sourceFile.getFile();
        String scriptName = FileUtils.getFileName(file);

        PandaScript pandaScript = new PandaScript(scriptName);

        Panda panda = interpreter.getPanda();
        PandaComposition pandaComposition = panda.getPandaComposition();
        ParserComposition parserComposition = pandaComposition.getParserComposition();
        ParserPipeline pipeline = parserComposition.getPipeline();

        PandaLexer lexer = new PandaLexer(interpreter.getPanda(), sourceFile.getContent());
        TokenizedSource tokenizedSource = lexer.convert();
        TokenReader tokenReader = new PandaTokenReader(tokenizedSource);

        ParserInfo parserInfo = new PandaParserInfo();
        parserInfo.setComponent(Components.INTERPRETER, interpreter);
        parserInfo.setComponent(Components.PARSER_PIPELINE, pipeline);
        parserInfo.setComponent(Components.READER, tokenReader);
        parserInfo.setComponent(Components.LINKER, new PandaWrapperLinker());

        OverallParser headOverallParser = new OverallParser(parserInfo, tokenReader);

        for (Statement statement : headOverallParser) {
            if (!(statement instanceof Wrapper)) {
                tokenReader.synchronize();
                throw new PandaParserException("Illegal statement in the main scope at " + tokenReader.previous().getLine());
            }

            Wrapper wrapper = (Wrapper) statement;
            pandaScript.addWrapper(wrapper);
        }

        return pandaScript;
    }

    public PandaInterpreter getInterpreter() {
        return interpreter;
    }

}
