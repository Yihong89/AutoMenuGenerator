package auto.menu.generator.translator;

import auto.menu.generator.data.Material;

public class MaterialTranslator implements Translator<Material> {

    public Material parse(String lines) {
        String[] records = lines.split(", ");
        if(records.length == 2) {
            return new Material(records[0], Boolean.parseBoolean(records[1]));
        } else {
            return null;
        }
    }

    public String toTxt(Material material) {
        return null;
    }
}
