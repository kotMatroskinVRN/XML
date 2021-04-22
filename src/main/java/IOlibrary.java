import javax.naming.NameNotFoundException;
import java.util.TreeSet;

public class IOlibrary {

    private String
        name ,
        tech ,
        pair ,
        type ,
        lefdir
                ;
    private TreeSet<String> lefFiles ;


    IOlibrary(String tech , String name){

       init(tech , name);

    }

    IOlibrary(String tech , String name , XPathReadXML xml) {
        String expression = null;

        init(tech , name);

        String mainExpression = String.format("xkit/tech[name='%s']/lib[name='%s']/" , tech , name );


        try {
            expression = mainExpression + "pair";
            pair = xml.getValue(expression );

            expression = mainExpression + "type";
            type = xml.getValue(expression );

            expression =  mainExpression + "LEFdir";
            lefdir = xml.getValue(expression );

        } catch (NameNotFoundException e) {
            e.printStackTrace();
            System.out.println(expression);
        }

    }

    void init(String tech , String name){

        this.tech = tech;
        this.name = name;

    }


    @Override
    public String toString(){
        String format = "%-20s: %s\n";
        StringBuilder result = new StringBuilder();
        result.append(String.format(format , "TECH"     , tech  ));
        result.append(String.format(format , "LIB NAME" , name  ));
        result.append(String.format(format , "PAIR"     , pair  ));
        result.append(String.format(format , "TYPE"     , type  ));
        result.append(String.format(format , "LEF dir"  , lefdir));

        return result.toString();
    }

    public String getName() { return name; }

    public String getTech() { return tech; }

    public String getPair() { return pair; }

    public String getType() { return type; }

    public String getLefdir() { return lefdir; }

    public TreeSet<String> getLefFiles() { return lefFiles; }
}
