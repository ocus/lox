package fr.ocus.lox.tool;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 */
public class GenerateAst {
    private static String INDENT = "    ";

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output directory>");
            System.exit(1);
        }
        String outputDir = args[0];

        System.out.println("Generating AST");

        // expressions
        defineAst(outputDir, "Expr", Collections.singletonList("java.util.List"), Arrays.asList(
            "Assign   : Token name, Expr value",
            "Binary   : Expr left, Token operator, Expr right",
            "Call     : Expr callee, Token paren, List<Expr> arguments",
            "Get      : Expr object, Token name",
            "Grouping : Expr expression",
            "Literal  : Object value",
            "Logical  : Expr left, Token operator, Expr right",
            "Set      : Expr object, Token name, Expr value",
            "This     : Token keyword",
            "Super    : Token keyword, Token method",
            "Unary    : Token operator, Expr right",
            "Variable : Token name"
        ));

        // statements
        defineAst(outputDir, "Stmt", Collections.singletonList("java.util.List"), Arrays.asList(
            "Block      : List<Stmt> statements",
            "Break      : Token keyword",
            "Class      : Token name, Expr superclass, List<Stmt.Function> methods",
            "Expression : Expr expression",
            "Function   : Token name, List<Token> parameters, List<Stmt> body",
            "If         : Expr condition, Stmt thenBranch, Stmt elseBranch",
            "Print      : Expr expression",
            "Var        : Token name, Expr initializer",
            "Return     : Token keyword, Expr value",
            "While      : Expr condition, Stmt body"
        ));
    }

    private static void defineAst(String outputDir, String baseName, List<String> imports, List<String> types) throws IOException {
        String path = outputDir + File.separator + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writer.println("package fr.ocus.lox.jlox;");
        if (imports != null && !imports.isEmpty()) {
            writer.println("");
            for (String imp : imports) {
                writer.println("import " + imp + ";");
            }
        }
        writer.println("");
        writer.println("/**");
        writer.println(" * @author Matthieu Honel <ocus51@gmail.com>");
        writer.println(" * @since 2017-08-03");
        writer.println(" */");
        writer.println("abstract class " + baseName + " {");

        defineVisitor(writer, baseName, types);

        // The AST classes.
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        // The base accept() method.
        writer.println("");
        writer.println(INDENT + "abstract <R> R accept(Visitor<R> visitor);");

        writer.println("}");
        writer.close();

        System.out.println(path + " written.");
    }

    private static void defineType(
        PrintWriter writer, String baseName,
        String className, String fieldList) {
        writer.println("");
        writer.println(INDENT + "static class " + className + " extends " + baseName + " {");

        // Constructor.
        writer.println(INDENT + INDENT + className + "(" + fieldList + ") {");

        // Store parameters in fields.
        String[] fields = fieldList.split(", ");
        for (String field : fields) {
            String name = field.split(" ")[1];
            writer.println(INDENT + INDENT + INDENT + "this." + name + " = " + name + ";");
        }

        writer.println(INDENT + INDENT + "}");

        // Visitor pattern.
        writer.println();
        writer.println(INDENT + INDENT + "<R> R accept(Visitor<R> visitor) {");
        writer.println(INDENT + INDENT + INDENT + "return visitor.visit" + className + baseName + "(this);");
        writer.println(INDENT + INDENT + "}");

        // Fields.
        writer.println();
        for (String field : fields) {
            writer.println(INDENT + INDENT + "final " + field + ";");
        }

        writer.println(INDENT + "}");
    }

    private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {
        writer.println(INDENT + "interface Visitor<R> {");

        for (String type : types) {
            String typeName = type.split(":")[0].trim();
            writer.println("");
            writer.println(INDENT + INDENT + "R visit" + typeName + baseName + "(" + typeName + " " + baseName.toLowerCase() + ");");
        }

        writer.println(INDENT + "}");
    }
}
