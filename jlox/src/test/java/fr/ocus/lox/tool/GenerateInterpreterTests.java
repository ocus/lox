package fr.ocus.lox.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.*;
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
    private static List<String> DISABLED_TESTS = new ArrayList<>();

    static {
        DISABLED_TESTS.add("benchmark/binary_trees");
        DISABLED_TESTS.add("benchmark/equality");
        DISABLED_TESTS.add("benchmark/fib");
        DISABLED_TESTS.add("benchmark/invocation");
        DISABLED_TESTS.add("benchmark/method_call");
        DISABLED_TESTS.add("benchmark/properties");
        DISABLED_TESTS.add("benchmark/string_equality");
        DISABLED_TESTS.add("expressions/evaluate");
        DISABLED_TESTS.add("expressions/parse");
        DISABLED_TESTS.add("limit/loop_too_large");
        DISABLED_TESTS.add("limit/stack_overflow");
        DISABLED_TESTS.add("limit/too_many_constants");
        DISABLED_TESTS.add("limit/too_many_locals");
        DISABLED_TESTS.add("limit/too_many_upvalues");
        DISABLED_TESTS.add("scanning/identifiers");
        DISABLED_TESTS.add("scanning/keywords");
        DISABLED_TESTS.add("scanning/numbers");
        DISABLED_TESTS.add("scanning/punctuators");
        DISABLED_TESTS.add("scanning/strings");
        DISABLED_TESTS.add("scanning/whitespace");

    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_interpreter_tests <output directory>");
            System.exit(1);
        }
        String outputDir = args[0];

        Path dir = Paths.get("src", "test", "resources", "programs");
        List<Path> files = getLoxFiles(dir);

        generateTests(outputDir, dir, files, false);
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

    private static void generateTests(String outputDir, Path baseDir, List<Path> files, boolean generateSuite) throws IOException {
        Map<String, ArrayList<Path>> groupedPaths = new HashMap<>();
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

        Set<String> groupNames = new TreeSet<>();
        for (String group : groupedPaths.keySet()) {
            List<Path> groupFiles = groupedPaths.get(group);
            Collections.sort(groupFiles);
            generateTest(outputDir, baseDir, groupFiles, group);
            groupNames.add(group);
        }

        if (generateSuite) {
            Path outputFile = Paths.get(outputDir, "InterpreterTestSuite.java");
            PrintWriter writer = new PrintWriter(outputFile.toAbsolutePath().toString(), "UTF-8");
            writer.println("package fr.ocus.lox.jlox;");
            writer.println("");
            writer.println("import org.junit.runner.RunWith;");
            writer.println("import org.junit.runners.Suite;");
            writer.println("");
            writer.println("@RunWith(Suite.class)");
            writer.println("@Suite.SuiteClasses({");
            for (String group : groupNames) {
                String camelCaseGroupName = (group != null && group.length() > 0 ? toCamelCase(group) : "");
                writer.println("        Interpreter" + camelCaseGroupName + "Test.class,");
            }
            writer.println("})");
            writer.println("public class InterpreterTestSuite {");
            writer.println("}");
            writer.close();
        }
    }

    private static void generateTest(String outputDir, Path baseDir, List<Path> files, String groupName) throws IOException {
        String camelCaseGroupName = (groupName != null && groupName.length() > 0 ? toCamelCase(groupName) : "");
        Path outputFile = Paths.get(outputDir, "Interpreter" + camelCaseGroupName + "Test.java");
        PrintWriter writer = new PrintWriter(outputFile.toAbsolutePath().toString(), "UTF-8");

        boolean hasIgnored = false;
        for (Path file : files) {
            Path relative = baseDir.relativize(file);
            Path fileName = relative.getFileName();
            String baseName = fileName.toString().replaceAll(".lox", "");
            hasIgnored |= DISABLED_TESTS.contains(groupName + "/" + baseName);
        }
        writer.println("package fr.ocus.lox.jlox;");
        writer.println("");
        if (hasIgnored) {
            writer.println("import org.junit.Ignore;");
        }
        writer.println("import org.junit.Test;");
        writer.println("import org.slf4j.Logger;");
        writer.println("import org.slf4j.LoggerFactory;");
        writer.println("");
        writer.println("import java.nio.file.Path;");
        writer.println("import java.nio.file.Paths;");
        writer.println("import java.util.Arrays;");
        writer.println("");
        writer.println("import static org.hamcrest.CoreMatchers.containsString;");
        writer.println("import static org.hamcrest.MatcherAssert.assertThat;");
        writer.println("import static org.junit.Assert.assertArrayEquals;");
        writer.println("import static org.junit.Assert.assertEquals;");
        writer.println("");
        writer.println("/**");
        writer.println(" * @author Matthieu Honel <ocus51@gmail.com>");
        writer.println(" * @since 2017-08-04");
        writer.println(" */");
        writer.println("public class Interpreter" + camelCaseGroupName + "Test {");
        writer.println("    private static final Logger LOGGER = LoggerFactory.getLogger(Interpreter" + camelCaseGroupName + "Test.class.getName());");

        for (Path file : files) {
            Path relative = baseDir.relativize(file);
            Path fileName = relative.getFileName();
            String baseName = fileName.toString().replaceAll(".lox", "");

            String camelName = toCamelCase(baseName);
            String testName = "test" + camelName;

            writer.println("");
            writer.println("    @Test");
            if (DISABLED_TESTS.contains(groupName + "/" + baseName)) {
                writer.println("    @Ignore");
            }
            writer.println("    public void " + testName + "() {");
            writer.println("        Path file = " + toPathsGetString(file) + ";");
            writer.println("        InterpreterTestHelper helper = new InterpreterTestHelper(file);");
            writer.println("        helper.run();");
            writer.println("        String[] out = helper.getOutput();");
            writer.println("        String[] err = helper.getError();");
            writer.println("        LOGGER.debug(\"{} :: OUT :: {}\", file, Arrays.toString(out));");
            writer.println("        LOGGER.debug(\"{} :: ERR :: {}\", file, Arrays.toString(err));");
            List<String> lines = Files.readAllLines(file);
            List<String> outputExpect = new ArrayList<>();
            List<String> errorExpect = new ArrayList<>();
            for (int l = 0; l < lines.size(); l++) {
                String line = lines.get(l);
                Matcher mOutputExpect = OUTPUT_EXPECT.matcher(line);
                if (mOutputExpect.find()) {
                    outputExpect.add(mOutputExpect.group(1));
                }
                Matcher mErrorExpect = ERROR_EXPECT.matcher(line);
                if (mErrorExpect.find()) {
                    errorExpect.add(mErrorExpect.group(1));
                }
                Matcher mErrorLineExpect = ERROR_LINE_EXPECT.matcher(line);
                if (mErrorLineExpect.find()) {
                    String language = mErrorLineExpect.group(2);
                    if (language == null || "java".equals(language)) {
                        errorExpect.add(mErrorLineExpect.group(4));
                    }
                }
                Matcher mRuntimeErrorExpect = RUNTIME_ERROR_EXPECT.matcher(line);
                if (mRuntimeErrorExpect.find()) {
                    errorExpect.add(mRuntimeErrorExpect.group(1));
                    errorExpect.add("[line " + (l + 1) + "]");
                }

//                Map<String, Pattern> patterns = new HashMap<String, Pattern>() {{
//                    put("ERROR_EXPECT", ERROR_EXPECT);
//                    put("ERROR_LINE_EXPECT", ERROR_LINE_EXPECT);
//                    put("RUNTIME_ERROR_EXPECT", RUNTIME_ERROR_EXPECT);
//                    put("SYNTAX_ERROR_RE", SYNTAX_ERROR_RE);
//                    put("STACK_TRACE_RE", STACK_TRACE_RE);
//                    put("NONTEST_RE", NONTEST_RE);
//                }};
//                for (String name : patterns.keySet()) {
//                    Pattern pattern = patterns.get(name);
//                    Matcher m = pattern.matcher(line);
//                    if (m.find()) {
//                        System.out.println(file + " @@@ " + line + " @@@  " + name + " @@@ " + m.group(0));
//                    }
//                }
            }
            if (outputExpect.size() == 0) {
                writer.println("        assertArrayEquals(new String[]{\"\"}, out);");
            } else {
                writer.println("        assertEquals(" + outputExpect.size() + ", out.length);");
                for (int i = 0; i < outputExpect.size(); i++) {
                    writer.println("        assertEquals(\"" + outputExpect.get(i).replaceAll("\"", "\\\\\"") + "\", out[" + i + "]);");
                }
            }
            if (errorExpect.size() == 0) {
                writer.println("        assertArrayEquals(new String[]{\"\"}, err);");
            } else {
                writer.println("        assertEquals(" + errorExpect.size() + ", err.length);");
                for (int i = 0; i < errorExpect.size(); i++) {
                    writer.println("        assertThat(err[" + i + "], containsString(\"" + errorExpect.get(i).replaceAll("\"", "\\\\\"") + "\"));");
                }
            }
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

    static String toPathsGetString(Path path) {
        String arguments = "";
        for (int i = 0; i < path.getNameCount(); i++) {
            arguments += "\"" + path.getName(i) + "\"";
            if (i < path.getNameCount() - 1) {
                arguments += ", ";
            }
        }
        return "Paths.get(" + arguments + ")";
    }
}
