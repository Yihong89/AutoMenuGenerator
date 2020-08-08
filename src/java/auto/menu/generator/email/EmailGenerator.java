package auto.menu.generator.email;


public class EmailGenerator {

    StringBuilder sb = new StringBuilder();

    public void addContent(String content){
        sb.append(content);
    }

    public String getContent(){
        return sb.toString();
    }

}
