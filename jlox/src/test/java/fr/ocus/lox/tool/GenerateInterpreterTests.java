package fr.ocus.lox.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class GenerateInterpreterTests {
    private static Pattern OUTPUT_EXPECT = Pattern.compile("// expect: ?(.*)");
    private static Pattern ERROR_EXPECT = Pattern.compile("// (Error.*)");
    private static Pattern ERROR_LINE_EXPECT = Pattern.compile("// \\[((java|c) )?line (\\d+)\\] (Error.*)");
    private static Pattern RUNTIME_ERROR_EXPECT = Pattern.compile("// expect runtime error: (.+)");
    private static Pattern SYNTAX_ERROR_RE = Pattern.compile("\\[.*line (\\d+)\\] (Error.+)");
    private static Pattern STACK_TRACE_RE = Pattern.compile("\\[line (\\d+)\\]");
    private static Pattern NONTEST_RE = Pattern.compile("// nontest");
    private static final PathMatcher loxMatcher = FileSystems.getDefault().getPathMatcher("glob:**.lox");
    private static List<String> DISABLED_DIRECTORIES = new ArrayList<>();

    static {
        DISABLED_DIRECTORIES.add("benchmark");
        DISABLED_DIRECTORIES.add("class");
//        DISABLED_DIRECTORIES.add("closure");
        DISABLED_DIRECTORIES.add("constructor");
        DISABLED_DIRECTORIES.add("expressions");
        DISABLED_DIRECTORIES.add("field");
        DISABLED_DIRECTORIES.add("inheritance");
        DISABLED_DIRECTORIES.add("limit");
        DISABLED_DIRECTORIES.add("method");
        DISABLED_DIRECTORIES.add("scanning");
        DISABLED_DIRECTORIES.add("super");
        DISABLED_DIRECTORIES.add("this");
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_interpreter_tests <output directory>");
            System.exit(1);
        }
        String outputDir = args[0];

        Path dir = Paths.get("src", "test", "resources", "programs");
        List<Path> files = getLoxFiles(dir);

        generateTests(outputDir, dir, files);
    }

    private static List<Path> getLoxFiles(Path dir) {
        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                if (loxMatcher.matches(file)) {
                    files.add(file);
                } else if (Files.isDirectory(file)) {
                    files.addAll(getLoxFiles(file));
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }
        return files;
    }

    private static void generateTests(String outputDir, Path baseDir, List<Path> files) throws IOException {
        HashMap<String, ArrayList<Path>> groupedPaths = new HashMap<String, ArrayList<Path>>();
        for (Path file : files) {
            Path relative = baseDir.relativize(file);
            Path fileName = relative.getFileName();
            String baseName = fileName.toString().replaceAll(".lox", "");
            Path type = relative.getParent();
            String group = type != null ? type.toString() : "";
            if (!groupedPaths.containsKey(group)) {
                groupedPaths.put(group, new ArrayList<>());
            }
            groupedPaths.get(group).add(file);
        }

        for (String group : groupedPaths.keySet()) {
            generateTest(outputDir, baseDir, groupedPaths.get(group), group);
        }
    }

    private static void generateTest(String outputDir, Path baseDir, List<Path> files, String groupName) throws IOException {
        String camelCaseGroupName = (groupName != null && groupName.length() > 0 ? toCamelCase(groupName) : "");
        Path outputFile = Paths.get(outputDir, "Interpreter" + camelCaseGroupName + "Test.java");
        PrintWriter writer = new PrintWriter(outputFile.toAbsolutePath().toString(), "UTF-8");

        writer.println("package fr.ocus.lox.jlox;");
        writer.println("");
        writer.println("import org.junit.Ignore;");
        writer.println("import org.junit.Test;");
        writer.println("");
        writer.println("import java.util.Arrays;");
        writer.println("");
        writer.println("import static org.junit.Assert.assertArrayEquals;");
        writer.println("import static org.junit.Assert.assertEquals;");
        writer.println("");
        writer.println("/**");
        writer.println(" * @author Matthieu Honel <ocus51@gmail.com>");
        writer.println(" * @since 2017-08-04");
        writer.println(" */");
        if (DISABLED_DIRECTORIES.contains(groupName)) {
            writer.println("@Ignore");
        }
        writer.println("public class Interpreter" + camelCaseGroupName + "Test {");

        for (Path file : files) {
            Path relative = baseDir.relativize(file);
            Path fileName = relative.getFileName();
            String baseName = fileName.toString().replaceAll(".lox", "");

            String camelName = toCamelCase(baseName);
            String testName = "test" + camelName;

            writer.println("");
            writer.println("    @Test");
            writer.println("    public void " + testName + "() {");
            writer.println("        InterpreterTestHelper helper = new InterpreterTestHelper(\"" + file + "\");");
            writer.println("        helper.run();");
            writer.println("        String[] out = helper.getOutput();");
            writer.println("        String[] err = helper.getError();");
            writer.println("        System.err.println(Arrays.toString(err));");
            List<String> lines = Files.readAllLines(file);
            int outputExpectIndex = 0;
            for (String line : lines) {
                Matcher m = OUTPUT_EXPECT.matcher(line);
                if (m.find()) {
                    writer.println("        assertEquals(\"" + m.group(1).replaceAll("\"", "\\\\\"") + "\", out[" + outputExpectIndex + "]);");
                    outputExpectIndex++;
                }
            }
            if (outputExpectIndex == 0) {
                writer.println("        assertArrayEquals(new String[]{\"\"}, out);");
            }
            writer.println("        assertArrayEquals(new String[]{\"\"}, err);");
            writer.println("    }");
        }
        writer.println("}");
        writer.close();
    }

    static String toCamelCase(String s) {
        String[] parts = s.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
            s.substring(1).toLowerCase();
    }
}
