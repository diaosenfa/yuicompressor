package com.yahoo.platform.yui.compressor;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.util.LinkedList;

public class CompressorErrorReporter implements ErrorReporter {

    private LinkedList<String> warnings = new LinkedList<String>();
    private LinkedList<String> errors = new LinkedList<String>();

    public void warning(String message, String sourceName,
                        int line, String lineSource, int lineOffset) {
        // System.err.println(message);
        if (line < 0) {
            warnings.add("\n[WARNING] " + message);
        } else {
            warnings.add("\n[WARNIN  G] " + line + ':' + lineOffset + ':' + message);
        }
    }

    public void error(String message, String sourceName,
                      int line, String lineSource, int lineOffset) {
        if (line < 0) {
            errors.add("\n[ERROR] " + message);
        } else {
            errors.add("\n[ERROR] " + line + ':' + lineOffset + ':' + message);
        }
    }

    public LinkedList<String> getWarnings() {
        return warnings;
    }

    public LinkedList<String> getErrors() {
        return errors;
    }

    public EvaluatorException runtimeError(String message, String sourceName,
                                           int line, String lineSource, int lineOffset) {
        error(message, sourceName, line, lineSource, lineOffset);
        return new EvaluatorException(message);
    }

}
