package auto.menu.generator.translator;

public class SimpleTranslator implements Translator<String> {

    public String parse(String lines) {
        return lines;
    }

    public String toTxt(String s) {
        return null;
    }
}
