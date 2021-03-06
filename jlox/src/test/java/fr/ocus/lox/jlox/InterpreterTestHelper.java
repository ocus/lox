package fr.ocus.lox.jlox;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
class InterpreterTestHelper {
    private final ByteArrayOutputStream outBaos = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errBaos = new ByteArrayOutputStream();
    private final PrintStream out = new PrintStream(outBaos);
    private final PrintStream err = new PrintStream(errBaos);
    private final byte[] source;

    InterpreterTestHelper(Path sourceFile) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(sourceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        source = bytes;
    }

    void run() {
        Interpreter interpreter = new Interpreter(out, err);
        JLox.run(err, interpreter, new String(source, Charset.defaultCharset()));
    }

    String[] getOutput() {
        return toLines(outBaos);
    }

    String[] getError() {
        return toLines(errBaos);
    }

    private String[] toLines(ByteArrayOutputStream stream) {
        return new String(stream.toByteArray(), Charset.defaultCharset()).split("\n");
    }
}
