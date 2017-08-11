package fr.ocus.lox.jlox;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthieu Honel <matthieu.honel@vectaury.io>
 * @since 2017-08-11
 */
class LoxInstance {
    private final LoxClass cls;
    final Map<String, Object> fields = new HashMap<>();

    LoxInstance(LoxClass cls) {
        this.cls = cls;
    }

    Object getProperty(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        LoxFunction method = cls.findMethod(this, name.lexeme);
        if (method != null) return method;

        throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
    }

    @Override
    public String toString() {
        return cls.toString() + " instance";
    }
}
