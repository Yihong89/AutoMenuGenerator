package auto.menu.generator.translator;

import auto.menu.generator.data.Dish;
import auto.menu.generator.data.LoadedData;
import auto.menu.generator.data.Material;

public class DishTranslator implements Translator<Dish> {

    private LoadedData data;

    public DishTranslator(LoadedData data) {
        this.data = data;
    }


    @Override
    public Dish parse(String lines) {
        String[] records = lines.split(": ");
        if(records.length == 2) {
            Dish dish = new Dish(records[0]);
            String[] materialNames = records[1].split(", ");
            for(String name : materialNames){
                if(!data.containsMaterial(name)){
//                    System.out.println(name + " is not registered, registered as non-meet material");
                    data.registerMaterial(new Material(name, false));
                }
                dish.addMaterial(data.getMaterialByName(name));
            }
            return dish;
        } else {
            return null;
        }
    }

    @Override
    public String toTxt(Dish dish) {
        return null;
    }
}
