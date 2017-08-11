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

    boolean define(String name, Object value) {
        if (values.containsKey(name)) {
            return false;
        }
        values.put(name, value);
        return true;
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
}
