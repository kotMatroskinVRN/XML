import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.HashSet;

public class ReadCoreRefIO {

    Node root;
    Document document ;

    ReadCoreRefIO(String xmlFile){

        getRoot(xmlFile);
    }

    Node getRoot(String xmlFile){


        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            document = documentBuilder.parse(xmlFile);
            root = document.getDocumentElement();
        }
        catch (ParserConfigurationException | IOException ex) { ex.printStackTrace(System.out); } catch (SAXException ex) { ex.printStackTrace(System.out);}
        return root;
    }

    Node getLib(String tech , String lib){
        NodeList list = document.getElementsByTagName("name");

        HashSet<Node> nodes = new HashSet();

        for(int i=0 ; i < list.getLength() ; i++){
            Node name = list.item(i);
            Node parent = name.getParentNode();

            System.out.println(parent.getNodeName());
            Node techName = getChildByName(parent,"name");


            boolean factor =
                    //techName.getNodeName().equals("name") &&
                    parent.getNodeName().equals("lib") &&
                    techName.getTextContent().trim().equals(tech);

            //System.out.println(techName.getNodeName());
            if(factor)
                nodes.add(name);

        }

        System.out.println(nodes.size());
        for(Node node : nodes){
            System.out.println(node.getNodeName());
            System.out.println(node.getTextContent());
        }
        return null;
    }

    Node getChildByName(Node node , String name){
        Node result = node.getFirstChild() ;
        //System.out.println(result);
        while( !result.getNodeName().equals(name)){
            //System.out.println(result);
            result = result.getNextSibling();
        }

        return  result;
    }

    public static void main(String[] args) {

        ReadCoreRefIO xml = new ReadCoreRefIO("src/main/resources/CoreRefIO.xml");
        System.out.println(xml.root.getNodeName());
        System.out.println(xml.root.getNodeValue());

        NodeList list = xml.document.getElementsByTagName("name");

        System.out.println(list.getLength());

        for(int i=0 ; i < list.getLength() ; i++){
            Node node = list.item(i);
            Node parent = node.getParentNode();
            Node techName = parent.getFirstChild().getNextSibling();

            //System.out.println(techName.getNodeName());
            if(techName.getNodeName().equals("name") && techName.getParentNode().getNodeName().equals("lib"))
                System.out.println(node.getTextContent());

        }


        xml.getLib("xh018" , "IO_CELLS_JI3V");







    }
}
