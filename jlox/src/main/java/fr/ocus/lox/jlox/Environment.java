package fr.ocus.lox.jlox;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 */
public class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    Environment() {
        this(null);
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    Map<String, Object> getValues() {
        return values;
    }

    Map<String, Object> getAllValues() {
        Map<String, Object> allValues = new HashMap<>();
        Environment environment = this;
        do {
            allValues.putAll(environment.values);
            environment = environment.enclosing;
        } while (environment != null);

        return allValues;
    }

    void define(String name, Object value) {
        values.put(name, value);
    }

    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        if (enclosing != null) return enclosing.get(name);

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    void assignAt(int distance, Token name, Object value) {
        Environment environment = this;
        for (int i = 0; i < distance; i++) {
            environment = environment.enclosing;
        }

        environment.values.put(name.lexeme, value);
    }

    Object getAt(int distance, String name) {
        Environment environment = this;
        for (int i = 0; i < distance; i++) {
            environment = environment.enclosing;
        }

        return environment.values.get(name);
    }

    @Override
    public String toString() {
        String result = values.toString();
        if (enclosing != null) {
            result += " -> " + enclosing.toString();
        }

        return result;
    }
}
