package fr.ocus.lox.jlox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 */
public class JLox {
    private static boolean hadError;
    private static boolean hadRuntimeError;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(System.err, new Interpreter(System.out, System.err), new String(bytes, Charset.defaultCharset()));

        if (hadError) System.exit(65);
        if (hadRuntimeError) System.exit(70);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        Interpreter interpreter = new Interpreter(System.out, System.err);
        while (true) {
            System.out.println("> ");
            run(System.err, interpreter, reader.readLine());
        }
    }

    public static void run(PrintStream errorStream, Interpreter interpreter, String source) {
        hadError = false;
        Scanner scanner = new Scanner(errorStream, source);
        List<Token> tokens = scanner.scanTokens();

        Parser parser = new Parser(errorStream, tokens);
        List<Stmt> statements = parser.parse();

        // Stop if there was a syntax error.
        if (hadError) return;

        interpreter.interpret(statements);
//        System.out.println(new AstPrinter().print(expression));
    }

    static void error(PrintStream errorStream, int line, String message) {
        report(errorStream, line, "", message);
    }

    private static void report(PrintStream errorStream, int line, String where, String message) {
        errorStream.println("[Line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    static void error(PrintStream errorStream, Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(errorStream, token.line, " at end", message);
        } else {
            report(errorStream, token.line, " at '" + token.lexeme + "'", message);
        }
    }

    static void runtimeError(PrintStream errorStream, RuntimeError error) {
        errorStream.println(error.getMessage() + "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }
}
