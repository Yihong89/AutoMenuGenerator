package auto.menu.generator.data;

import java.util.HashSet;
import java.util.Set;

public class Dish {

    private String name;
    private Set<Material> materials;

    public Dish(String name, Set<Material> materials) {
        this.name = name.toLowerCase();
        this.materials = materials;
    }

    public Dish(String name) {
        this.name = name.toLowerCase();
        this.materials = new HashSet();
    }

    public boolean containMeet() {
        for(Material material : materials) {
            if(material.isMeet()) {
                return true;
            }
        }
        return false;
    }

    public boolean isSoup(){
        return name.toLowerCase().contains("soup");
    }

    public boolean containMaterial(Material material){
        return materials.contains(material);
    }

    public String getName() {
        return name;
    }

    public Set<String> getMaterialNames() {
        Set<String> materialNames = new HashSet<String>();
        for(Material material : materials){
            materialNames.add(material.getName());
        }
        return materialNames;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMaterial(Material material) {
        this.materials.add(material);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", materials=" + materials +
                '}';
    }
}
