package auto.menu.generator.data;

import java.util.*;

public class LoadedData {
    Map<String, Material> materials = new HashMap();
    Map<String, Dish> dishes = new HashMap();

    public void registerMaterial(Material material) {
        materials.put(material.getName(), material);
    }

    public void registerDish(Dish dish){
        dishes.put(dish.getName(), dish);
    }

    public void deregisterDish(String dish){
        dishes.remove(dish);
    }

    public void deregisterDish(Set<String> dishSet){
        for(String dish : dishSet){
            deregisterDish(dish);
        }
    }

    public void deregisterMaterial(String material){
        dishes.remove(material);
    }

    public void deregisterMaterials(Set<String> materialSet){
        for(String material : materialSet){
            deregisterMaterial(material);
        }
    }

    public void registerMaterial(Set<Material> materialSet) {
        for(Material material : materialSet) {
            registerMaterial(material);
        }
    }

    public void registerDish(Set<Dish> dishSet){
        for(Dish dish : dishSet){
            registerDish(dish);
        }
    }

    public Collection<Material> getMaterials() {
        return materials.values();
    }

    public Collection<Dish> getDishes() {
        return dishes.values();
    }

    public boolean containsMaterial(String name){
        return materials.containsKey(name.toLowerCase());
    }

    public Material getMaterialByName(String name) {
        return materials.get(name.toLowerCase());
    }
}
