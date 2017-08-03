package fr.ocus.lox.jlox;

import java.util.List;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 03/08/2017
 */
interface LoxCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
