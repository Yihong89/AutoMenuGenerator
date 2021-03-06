package auto.menu.generator.select;

import auto.menu.generator.data.Dish;
import auto.menu.generator.StaticDataDomain;
import auto.menu.generator.data.Material;
import auto.menu.generator.email.HtmlGenerator;

import java.util.*;

public class DishSelect implements Select {

    private StaticDataDomain data;
    private Set<Material> todayMaterials = new HashSet();
    private static final Random generator = new Random();
    private HtmlGenerator htmlGenerator;


    public DishSelect(StaticDataDomain data, Set<String> todayMaterials, HtmlGenerator htmlGenerator) {
        this.data = data;
        this.htmlGenerator = htmlGenerator;
        for(String name : todayMaterials) {
            Material material = data.getMaterialByName(name);
            if(material != null) {
                this.todayMaterials.add(material);
            }
        }
    }

    public Set<Dish> selectDishes(int numSoup, int numMeet, int numVeg) {
        List<Dish> soupDishes = new ArrayList();
        List<Dish> nonSoupMeetDish = new ArrayList();
        List<Dish> nonSoupVegDish = new ArrayList();

        Set<Dish> dishes = new HashSet();
        htmlGenerator.addHeadContent("Today Remaining materials");
        htmlGenerator.addContent(todayMaterials.toString());

        htmlGenerator.addHeadContent("Reccommand Dish based on today's material");
        for(Dish dish : data.getDishes()){
            int score = matchMaterialNum(dish);
            if(score > 0) {
                htmlGenerator.addContent("Score :"+score +"\t"+dish);
            }

            if(dish.isSoup()){
                soupDishes.add(dish);
            } else if(dish.containMeet()){
                nonSoupMeetDish.add(dish);
            } else {
                nonSoupVegDish.add(dish);
            }
        }


        for(int i = 0; i<numSoup; i++) {
            if(soupDishes.size()<=0){
                break;
            }
            Dish soup = soupDishes.get(generator.nextInt(soupDishes.size()));
            dishes.add(soup);
            soupDishes.remove(soup);
            if(soup.containMeet()){
                numMeet--;
            } else {
                numVeg--;
            }
        }
        for(int i=0; i<numMeet; i++){
            if(nonSoupMeetDish.size()<=0){
                break;
            }
            Dish dish = nonSoupMeetDish.get(generator.nextInt(nonSoupMeetDish.size()));
            dishes.add(dish);
            nonSoupMeetDish.remove(dish);
        }
        for(int i=0; i<numVeg; i++){
            if(nonSoupVegDish.size()<=0){
                break;
            }
            Dish dish = nonSoupVegDish.get(generator.nextInt(nonSoupVegDish.size()));
            dishes.add(dish);
            nonSoupVegDish.remove(dish);
        }

        return dishes;
    }

    private int matchMaterialNum(Dish dish){
        int i = 0;
        for(Material material : todayMaterials){
            if(dish.containMaterial(material)){
                i ++;
            }
        }
        return i;
    }
}
