package auto.menu.generator.translator;

public interface Translator<T> {

    T parse(String lines);

    String toTxt(T t);

}
