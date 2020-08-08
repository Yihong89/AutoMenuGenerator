package auto.menu.generator.data;

public class Material {

    private String name;
    private boolean isMeet;

    public Material(String name, boolean isMeet) {
        this.name = name.toLowerCase();
        this.isMeet = isMeet;
    }

    public String getName() {
        return name;
    }

    public boolean isMeet() {
        return isMeet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeet(boolean meet) {
        isMeet = meet;
    }

    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                ", isMeet=" + isMeet +
                '}';
    }
}
