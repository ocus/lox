package fr.ocus.lox.tool;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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

        // expressions
        defineAst(outputDir, "Expr", Arrays.asList(
            "Binary   : Expr left, Token operator, Expr right",
            "Grouping : Expr expression",
            "Literal  : Object value",
            "Unary    : Token operator, Expr right"
        ));

        // statements
        defineAst(outputDir, "Stmt", Arrays.asList(
            "Expression : Expr expression",
            "Print      : Expr expression"
        ));
    }

    private static void defineAst(String outputDir, String baseName, List<String> types)
        throws IOException {
        String path = outputDir + File.separator + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writer.println("package fr.ocus.lox.jlox;");
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
