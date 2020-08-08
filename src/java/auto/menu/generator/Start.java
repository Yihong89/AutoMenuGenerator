package auto.menu.generator;

import auto.menu.generator.data.Dish;
import auto.menu.generator.data.LoadedData;
import auto.menu.generator.data.Material;
import auto.menu.generator.io.TxtReader;
import auto.menu.generator.select.DishSelect;
import auto.menu.generator.select.Select;
import auto.menu.generator.translator.DishTranslator;
import auto.menu.generator.translator.MaterialTranslator;
import auto.menu.generator.translator.SimpleTranslator;
import auto.menu.generator.translator.Translator;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Start {

    public static void main(String arg[]) throws IOException {

        LoadedData data = new LoadedData();
        Translator<Material> materialTranslator = new MaterialTranslator();
        Translator<Dish> dishTranslator = new DishTranslator(data);
        Translator<String> simpleTranslator = new SimpleTranslator();

        TxtReader<Material> txtReader = new TxtReader<>("material.csv", materialTranslator);
        TxtReader<Dish> dishTxtReader = new TxtReader<>("dish.csv", dishTranslator);

        data.registerMaterial(txtReader.read());
        data.registerDish(dishTxtReader.read());

        TxtReader<String> ydishTxtReader = new TxtReader<>("yesterday-dish.txt", simpleTranslator);
        data.deregisterDish(ydishTxtReader.read());


        TxtReader<String> tMaterialTxtReader = new TxtReader<>("today-material.txt", simpleTranslator);

        Set<String> todayMaterials = new HashSet<>(tMaterialTxtReader.read());

        Select select = new DishSelect(data, todayMaterials);

        Set<Dish> selectedDish = select.selectDishes(2, 2, 6 );
        System.out.println("\n >>>> Random pick results :");
        StringBuilder sb = new StringBuilder();
        for(Dish dish : selectedDish){
            System.out.println(dish.getName());
            sb.append(dish.getName());
            sb.append(": ");
            sb.append(dish.getMaterials());
            sb.append("\n");
        }
        System.out.println("\n >>>>>Material List");
        System.out.println(sb.toString());
    }


}
