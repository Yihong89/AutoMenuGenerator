package auto.menu.generator.select;

import auto.menu.generator.data.Dish;

import java.util.Set;

public interface Select {

    Set<Dish> selectDishes(int numSoup, int numMeet, int numVeg);
}
