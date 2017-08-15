# Recursive wildcard
rwildcard=$(foreach d,$(wildcard $1*),$(call rwildcard,$d/,$2) $(filter $(subst *,%,$2),$d))
noop=
space = $(noop) $(noop)

JAVA := java
JAVAC := javac
JAVAC_OPTIONS := -Werror -encoding UTF8

BUILD_DIR := build

# MAIN_SOURCES := $(wildcard $(MAIN_DIR)/fr/ocus/lox/jlox/*.java)
MAIN_DIR := src/main/java
MAIN_BUILD_DIR := $(BUILD_DIR)/jlox/main
MAIN_PACKAGE := fr.ocus.lox.jlox
MAIN_SOURCES := $(call rwildcard, $(MAIN_DIR)/$(subst .,/,$(MAIN_PACKAGE)), *.java)
MAIN_DEPS := $(addprefix main_compile_$(MAIN_BUILD_DIR)/,$(MAIN_SOURCES:.java=.class))
MAIN_JAR_FILE := $(BUILD_DIR)/jlox.jar

TEST_DIR := src/test/java
TEST_BUILD_DIR := $(BUILD_DIR)/jlox/test
TEST_PACKAGE := fr.ocus.lox.jlox
TEST_SOURCES := $(call rwildcard, $(TEST_DIR)/$(subst .,/,$(TEST_PACKAGE)), *Test.java)
TEST_DEPS := $(addprefix test_compile_$(TEST_BUILD_DIR)/,$(TEST_SOURCES:.java=.class))
TEST_LIBS := $(subst $(space),;,$(wildcard libs/*.jar))
TEST_CLASSES := $(addprefix $(TEST_PACKAGE)., $(basename $(notdir $(TEST_SOURCES))))
TEST_JUNIT := $(addprefix test_junit_,$(TEST_CLASSES))

default: build

.PHONY: clean
clean:
	@ rm -rf $(BUILD_DIR) && echo "Removed build directory: $(BUILD_DIR)"

build: $(MAIN_DEPS)
build_test: build $(TEST_DEPS)

test: build_test $(TEST_JUNIT)

main_compile_$(MAIN_BUILD_DIR)/$(MAIN_DIR)%.class: $(MAIN_DIR)/%.java
	@ mkdir -p $(MAIN_BUILD_DIR)
	$(JAVAC) -cp $(MAIN_DIR) -d $(MAIN_BUILD_DIR) $(JAVAC_OPTIONS) -implicit:none $<

test_compile_$(TEST_BUILD_DIR)/$(TEST_DIR)%.class: $(TEST_DIR)/%.java
	@ mkdir -p $(TEST_BUILD_DIR)
	$(JAVAC) -cp "$(MAIN_DIR);$(TEST_DIR);$(TEST_LIBS)" -d $(TEST_BUILD_DIR) $(JAVAC_OPTIONS) -implicit:none $<

test_junit_%:
	-$(JAVA) -cp "$(MAIN_BUILD_DIR);$(TEST_BUILD_DIR);$(TEST_LIBS)" org.junit.runner.JUnitCore $(subst test_junit_,,$@)
