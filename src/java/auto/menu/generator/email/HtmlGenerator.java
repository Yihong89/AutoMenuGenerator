package auto.menu.generator.email;


public class HtmlGenerator {

    StringBuilder sb = new StringBuilder();

    public void addHtmlContent(String htmlContent){
        sb.append(htmlContent);
    }

    public void addContent(String content){
        sb.append("<p>");
        sb.append(content);
        sb.append("</p>\n");
    }

    public void addHeadContent(String header) {
        sb.append("<h1>");
        sb.append(header);
        sb.append("</h1>\n");
    }

    public String getContent(){
        return sb.toString();
    }

}
