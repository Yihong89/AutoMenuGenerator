package auto.menu.generator;

import auto.menu.generator.data.Dish;
import auto.menu.generator.data.Material;
import auto.menu.generator.email.HtmlGenerator;
import auto.menu.generator.email.SendEmail;
import auto.menu.generator.io.TxtReader;
import auto.menu.generator.select.DishSelect;
import auto.menu.generator.select.Select;
import auto.menu.generator.translator.DishTranslator;
import auto.menu.generator.translator.MaterialTranslator;
import auto.menu.generator.translator.SimpleTranslator;
import auto.menu.generator.translator.Translator;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Start {

    public static void main(String arg[]) throws IOException {

        StaticDataDomain data = new StaticDataDomain();
        Translator<Material> materialTranslator = new MaterialTranslator();
        Translator<Dish> dishTranslator = new DishTranslator(data);
        Translator<String> simpleTranslator = new SimpleTranslator();
        HtmlGenerator htmlGenerator = new HtmlGenerator();

        TxtReader<Material> txtReader = new TxtReader("material.csv", materialTranslator);
        TxtReader<Dish> dishTxtReader = new TxtReader("dish.csv", dishTranslator);

        data.registerMaterial(txtReader.read());
        data.registerDish(dishTxtReader.read());

        TxtReader<String> ydishTxtReader = new TxtReader("yesterday-dish.txt", simpleTranslator);
        htmlGenerator.addHeadContent("Yesterday menu");
        Set<String> ydish = ydishTxtReader.read();
        htmlGenerator.addContent(ydish.toString());
        data.deregisterDish(ydish);


        TxtReader<String> tMaterialTxtReader = new TxtReader("today-material.txt", simpleTranslator);

        Set<String> todayMaterials = new HashSet(tMaterialTxtReader.read());

        Select select = new DishSelect(data, todayMaterials, htmlGenerator);

        Set<Dish> selectedDish = select.selectDishes(3, 3, 9 );
        htmlGenerator.addHeadContent("Random pick results");
        StringBuilder sb = new StringBuilder();
        for(Dish dish : selectedDish){
            htmlGenerator.addContent("<b>"+dish.getName()+"</b>");
            sb.append("<p>");
            sb.append(dish.getName());
            sb.append(": ");
            sb.append(dish.getMaterialNames());
            sb.append("</p>");
        }
        htmlGenerator.addHeadContent("Material List");
        htmlGenerator.addHtmlContent(sb.toString());

        SendEmail sendEmail = new SendEmail();
        Date today = new Date();
        DateFormat formatter = DateFormat.getDateTimeInstance();

        sendEmail.send("Suggest Menu of " + formatter.format(today), htmlGenerator.getContent());
    }


}
