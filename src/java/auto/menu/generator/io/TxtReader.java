package auto.menu.generator.io;

import auto.menu.generator.translator.Translator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class TxtReader<T> {


    private String name;
    private Translator<T> translator;

    public TxtReader(String name, Translator translator) {
        this.name = name;
        this.translator = translator;
    }


    public Set<T> read() throws IOException {
        Set<T> newSet = new HashSet<>();
        BufferedReader in = new BufferedReader(new FileReader(this.getClass().getClassLoader().getResource(name).getPath()));
        String line;
        while((line = in.readLine()) != null)
        {
            if(line.startsWith("#") || line.isEmpty()){
                continue;
            }
            T t = translator.parse(line);
            if(t!=null) {
                newSet.add(t);
            }
        }
        in.close();
        return newSet;
    }

}
