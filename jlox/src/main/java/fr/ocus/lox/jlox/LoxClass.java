package fr.ocus.lox.jlox;

import java.util.List;
import java.util.Map;

/**
 * @author Matthieu Honel <matthieu.honel@vectaury.io>
 * @since 2017-08-11
 */
public class LoxClass implements LoxCallable {
    private final String name;
    private final LoxClass superclass;
    private final Map<String, LoxFunction> methods;

    LoxClass(String name, LoxClass superclass, Map<String, LoxFunction> methods) {
        this.name = name;
        this.superclass = superclass;
        this.methods = methods;
    }

    @Override
    public int arity() {
        LoxFunction initializer = methods.get("init");
        if (initializer == null) {
            return 0;
        }
        return initializer.arity();
    }

    LoxFunction findMethod(LoxInstance instance, String name) {
        LoxClass cls = this;
        while (cls != null) {
            if (cls.methods.containsKey(name)) {
                return cls.methods.get(name).bind(instance);
            }

            cls = cls.superclass;
        }
        return null;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        LoxFunction initializer = methods.get("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    @Override
    public String toString() {
        return name;
    }
}
