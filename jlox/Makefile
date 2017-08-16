# Recursive wildcard
rwildcard=$(foreach d,$(wildcard $1*),$(call rwildcard,$d/,$2) $(filter $(subst *,%,$2),$d))
noop=
space = $(noop) $(noop)

JAVA := java
JAVAC := javac
JAVAC_OPTIONS := -Werror -encoding UTF8

BUILD_DIR := build

ifeq ($(OS),Windows_NT)
    UNAME_S := Windows
else
    UNAME_S := $(shell uname -s)
endif

ifeq ($(UNAME_S), Windows)
    CLASSPATH_SEP = ;
endif
ifeq ($(UNAME_S), Linux)
    CLASSPATH_SEP = :
endif

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
TEST_SOURCES := $(call rwildcard, $(TEST_DIR)/$(subst .,/,$(TEST_PACKAGE)), *.java)
TEST_SUITE_SOURCES := $(call rwildcard, $(TEST_DIR)/$(subst .,/,$(TEST_PACKAGE)), *Suite.java)
TEST_DEPS := $(addprefix test_compile_$(TEST_BUILD_DIR)/,$(TEST_SOURCES:.java=.class))
TEST_LIBS := $(subst $(space),$(CLASSPATH_SEP),$(wildcard libs/*.jar))
TEST_SUITE_CLASSES := $(addprefix $(TEST_PACKAGE)., $(basename $(notdir $(TEST_SUITE_SOURCES))))
TEST_SUITES := $(addprefix test_junit_suite_,$(TEST_SUITE_CLASSES))
TEST_JUNIT_RUNNER := org.junit.runner.JUnitCore

default: build

.PHONY: clean
clean:
	@ rm -rf $(BUILD_DIR) && echo "Removed build directory: $(BUILD_DIR)"

build: $(MAIN_DEPS)
build_test: build $(TEST_DEPS)

test: clean build_test $(TEST_SUITES)

main_compile_$(MAIN_BUILD_DIR)/$(MAIN_DIR)%.class: $(MAIN_DIR)/%.java
	@ mkdir -p $(MAIN_BUILD_DIR)
	$(JAVAC) -cp $(MAIN_DIR) -d $(MAIN_BUILD_DIR) $(JAVAC_OPTIONS) -implicit:none $<

test_compile_$(TEST_BUILD_DIR)/$(TEST_DIR)%.class: $(TEST_DIR)/%.java
	@ mkdir -p $(TEST_BUILD_DIR)
	$(JAVAC) -cp "$(MAIN_DIR)$(CLASSPATH_SEP)$(TEST_DIR)$(CLASSPATH_SEP)$(TEST_LIBS)" -d $(TEST_BUILD_DIR) $(JAVAC_OPTIONS) -implicit:none $<

test_junit_suite_%:
	$(JAVA) -cp "$(MAIN_BUILD_DIR)$(CLASSPATH_SEP)$(TEST_BUILD_DIR)$(CLASSPATH_SEP)$(TEST_LIBS)" $(TEST_JUNIT_RUNNER) $(subst test_junit_suite_,,$@)
