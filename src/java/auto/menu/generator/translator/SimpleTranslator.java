package auto.menu.generator.translator;

public class SimpleTranslator implements Translator<String> {

    @Override
    public String parse(String lines) {
        return lines;
    }

    @Override
    public String toTxt(String s) {
        return null;
    }
}
