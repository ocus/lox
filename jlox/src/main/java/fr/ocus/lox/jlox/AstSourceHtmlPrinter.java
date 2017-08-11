package fr.ocus.lox.jlox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matthieu Honel <matthieu.honel@vectaury.io>
 * @since 2017-08-11
 */
public class AstSourceHtmlPrinter implements Stmt.Visitor<String>, Expr.Visitor<String> {
    List<String> print(List<Stmt> stmts) {
        List<String> prints = new ArrayList<>(stmts.size());
        for (Stmt stmt : stmts) {
            prints.add(print(stmt));
        }
        return prints;
    }

    String print(Stmt stmt) {
        return stmt.accept(this);
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "name", expr.name.lexeme));
        builder.append(wrap("span", "equal keyword", " = "));
        builder.append(wrap("span", "value", expr.value.accept(this)));
        builder.append(semicolon());

        return wrap("div", "expr-assign", builder.toString());
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "left", expr.left.accept(this))).append(" ");
        builder.append(wrap("span", "operator", expr.operator.lexeme)).append(" ");
        builder.append(wrap("span", "right", expr.right.accept(this)));

        return wrap("span", "expr-binary", builder.toString());
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "callee", expr.callee.accept(this)));
        builder.append(parenLeft());
        for (int i = 0; i < expr.arguments.size(); i++) {
            Expr argExpr = expr.arguments.get(i);
            builder.append(wrap("span", "argument", argExpr.accept(this)));
            if (i < expr.arguments.size() - 1) {
                builder.append(comma());
                builder.append(" ");
            }
        }
        builder.append(parenRight());
        return wrap("span", "expr-call", builder.toString());
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(parenLeft());
        builder.append(wrap("span", "expression", expr.expression.accept(this)));
        builder.append(parenRight());

        return wrap("span", "expr-grouping", builder.toString());
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        StringBuilder builder = new StringBuilder();
        if (expr.value instanceof String) {
            builder.append(wrap("span", "string", "\"" + expr.value + "\""));
        } else {
            builder.append(wrap("span", "number", String.valueOf(expr.value)));
        }
        return wrap("span", "expr-literal", builder.toString());
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "left", expr.left.accept(this))).append(" ");
        builder.append(wrap("span", "operator keyword", expr.operator.lexeme)).append(" ");
        builder.append(wrap("span", "right", expr.right.accept(this)));

        return wrap("span", "expr-logical", builder.toString());
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "operator keyword", expr.operator.lexeme));
        builder.append(wrap("span", "right", expr.right.accept(this)));

        return wrap("span", "expr-unary", builder.toString());
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "name", expr.name.lexeme));

        return wrap("span", "expr-variable", builder.toString());
    }

    @Override
    public String visitBlockStmt(Stmt.Block stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "brace-left", "{"));
        builder.append(statementList(stmt.statements));
        builder.append(wrap("span", "brace-right", "}"));

        return wrap("div", "stmt-block", builder.toString());
    }

    @Override
    public String visitExpressionStmt(Stmt.Expression stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "expression", stmt.expression.accept(this)));

        return wrap("div", "stmt-expression", builder.toString());
    }

    @Override
    public String visitFunctionStmt(Stmt.Function stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "fun keyword", "fun"));
        builder.append(" ");
        builder.append(wrap("span", "function-name", stmt.name.lexeme));
        builder.append(parenLeft());
        for (int i = 0; i < stmt.parameters.size(); i++) {
            Token token = stmt.parameters.get(i);
            builder.append(wrap("span", "parameter", token.lexeme));
            if (i < stmt.parameters.size() - 1) {
                builder.append(comma());
                builder.append(" ");
            }
        }
        builder.append(parenRight());
        builder.append(" ");
        builder.append(wrap("span", "brace-left", "{"));
        builder.append(statementList(stmt.body));
        builder.append(wrap("span", "brace-right", "}"));

        return wrap("div", "stmt-function", builder.toString());
    }

    @Override
    public String visitIfStmt(Stmt.If stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "if keyword", "if"));
        builder.append(" ");
        builder.append(parenLeft());
        builder.append(wrap("span", "condition", stmt.condition.accept(this)));
        builder.append(parenRight());
        builder.append(wrap("div", "then-branch", stmt.thenBranch.accept(this)));
        if (stmt.elseBranch != null) {
            builder.append(wrap("span", "else keyword", "else"));
            builder.append(" ");
            builder.append(wrap("div", "else-branch", stmt.elseBranch.accept(this)));
        }

        return wrap("div", "stmt-if", builder.toString());
    }

    @Override
    public String visitPrintStmt(Stmt.Print stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "print keyword", "print"));
        builder.append(" ");
        builder.append(wrap("span", "expression", stmt.expression.accept(this)));
        builder.append(semicolon());

        return wrap("div", "stmt-print", builder.toString());
    }

    @Override
    public String visitVarStmt(Stmt.Var stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "var keyword", "var"));
        builder.append(" ");
        builder.append(wrap("span", "name", stmt.name.lexeme));
        if (stmt.initializer != null) {
            builder.append(" ");
            builder.append(wrap("span", "equal keyword", " = "));
            builder.append(" ");
            builder.append(wrap("span", "initializer", stmt.initializer.accept(this)));
        }
        builder.append(semicolon());

        return wrap("div", "stmt-var", builder.toString());
    }

    @Override
    public String visitReturnStmt(Stmt.Return stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "return keyword", "return"));
        builder.append(" ");
        builder.append(wrap("span", "value", stmt.value.accept(this)));
        builder.append(semicolon());

        return wrap("div", "stmt-return", builder.toString());
    }

    @Override
    public String visitWhileStmt(Stmt.While stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append(wrap("span", "while keyword", "while"));
        builder.append(" ");
        builder.append(parenLeft());
        builder.append(wrap("span", "condition", stmt.condition.accept(this)));
        builder.append(parenRight());
        builder.append(" ");
        builder.append(wrap("div", "body", stmt.body.accept(this)));

        return wrap("div", "stmt-while", builder.toString());
    }

    private String statementList(List<Stmt> stmts) {
        StringBuilder builder = new StringBuilder();
        for (Stmt stmt : stmts) {
            builder.append(stmt.accept(this));
        }
        return wrap("div", "statements", builder.toString());
    }

    private String parenLeft() {
        return wrap("span", "paren-left", "(");
    }

    private String parenRight() {
        return wrap("span", "paren-right", ")");
    }

    private String semicolon() {
        return wrap("span", "semicolon", ";");
    }

    private String comma() {
        return wrap("span", "comma", ",");
    }

    private static String wrap(String tag, String cssClass, String element) {
        return String.format("<%1$s class=\"%2$s\">%3$s</%1$s>", tag, cssClass, element);
    }

    private static String programToString(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            Scanner scanner = new Scanner(System.err, new String(bytes));
            List<Token> tokens = scanner.scanTokens();

            Parser parser = new Parser(System.err, tokens);
            List<Stmt> statements = parser.parse();
            AstSourceHtmlPrinter printer = new AstSourceHtmlPrinter();
            return Arrays.stream(printer.print(statements).toArray(new String[0]))
                .collect(Collectors.joining(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        StringBuilder preHtml = new StringBuilder();
        preHtml.append("<html>");
        preHtml.append("<head>");
        preHtml.append("<style>");
        preHtml.append(".program-file { border-bottom: solid 1px black; }");
        preHtml.append(".program-code { border: solid 1px grey; padding: 1em; background: #2B2B2B; color: #A9B7C6; font-family: monospace; }");
        preHtml.append(".program-code .keyword, .program-code .comma, .program-code .semicolon { color: #CC7832; }");
        preHtml.append(".expr-assign { }");
        preHtml.append(".expr-binary { }");
        preHtml.append(".expr-call { }");
        preHtml.append(".expr-grouping { }");
        preHtml.append(".expr-literal { }");
        preHtml.append(".expr-literal .string { color: #6A8759; }");
        preHtml.append(".expr-literal .number { color: #6897BB; }");
        preHtml.append(".expr-logical { }");
        preHtml.append(".expr-unary { }");
        preHtml.append(".expr-variable { }");
        preHtml.append(".stmt-block { }");
        preHtml.append(".stmt-block .statements { margin-left: 1em; }");
        preHtml.append(".stmt-expression { }");
        preHtml.append(".stmt-function { }");
        preHtml.append(".stmt-function .function-name { color: #FFC66D; }");
        preHtml.append(".stmt-function .statements { margin-left: 1em; }");
        preHtml.append(".stmt-if { }");
        preHtml.append(".stmt-if .then-branch { margin-left: 1em; }");
        preHtml.append(".stmt-if .else-branch { margin-left: 1em; }");
        preHtml.append(".stmt-print { }");
        preHtml.append(".stmt-var { }");
        preHtml.append(".stmt-return { }");
        preHtml.append(".stmt-while { }");
        preHtml.append("</style>");
        preHtml.append("</head>");
        preHtml.append("<body>");
        System.out.println(preHtml.toString());
        String[] programs = new String[]{
            "programs/scope.lox",
            "programs/fibonacci.lox",
            "src/test/resources/programs/closure/assign_to_closure.lox",
            "src/test/resources/programs/closure/nested_closure.lox",
            "src/test/resources/programs/for/closure_in_body.lox",
            "src/test/resources/programs/logical_operator/and.lox",
        };
        for (String program : programs) {
            StringBuilder builder = new StringBuilder();
            builder.append(wrap("h1", "program-file", program));
            builder.append(wrap("div", "program-code", programToString(program)));
            System.out.println(wrap("div", "program", builder.toString()));
        }
        System.out.println("</body></html>");
    }
}
