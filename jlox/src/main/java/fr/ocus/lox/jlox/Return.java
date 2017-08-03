package fr.ocus.lox.jlox;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 03/08/2017
 */
class Return extends RuntimeException {
    final Object value;

    Return(Object value) {
        super(null, null, false, false);
        this.value = value;
    }
}
