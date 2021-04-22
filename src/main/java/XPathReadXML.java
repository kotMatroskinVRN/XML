import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.naming.NameNotFoundException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.TreeSet;

public class XPathReadXML {

    //Node root;
    Document document ;
    XPath xPath;

    XPathReadXML(String xmlFileName){

        getDocument(xmlFileName);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        xPath = xPathFactory.newXPath();

    }

    private void getDocument(String xmlFileName){


        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(xmlFileName);
            //root = document.getDocumentElement();
        }
        catch (ParserConfigurationException | IOException | SAXException ex)
        { ex.printStackTrace(System.out); }

    }



    public String getValue(String expression) throws NameNotFoundException {

        try {
            Node result = (Node) xPath.evaluate(expression , document , XPathConstants.NODE);

                return result.getTextContent() ;

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        throw new NameNotFoundException();

    }

    public String getFirstValue(String expression) throws NameNotFoundException {

        try {
            NodeList result = (NodeList) xPath.evaluate(expression , document , XPathConstants.NODESET);
            for(int i=0 ; i < result.getLength() ; i++){
                return result.item(i).getTextContent() ;
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        throw new NameNotFoundException();

    }

    public TreeSet<Node> getNodes(String expression) throws NameNotFoundException {
        TreeSet result  = new TreeSet();

        try {
            NodeList searchResult = (NodeList) xPath.evaluate(expression , document , XPathConstants.NODESET);
            for(int i=0 ; i < searchResult.getLength() ; i++){
                 result.add(searchResult.item(i)) ;
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        if(result.isEmpty()) throw new NameNotFoundException();

        return result;

    }

    public static void main(String[] args) throws NameNotFoundException {

        String result;

        XPathReadXML xml = new XPathReadXML("src/main/resources/CoreRefIO.xml");

        IOlibrary xs018_IO_CELLS_JI3V = new IOlibrary("xs018" , "IO_CELLS_JI3V" , xml) ;



        System.out.println(xs018_IO_CELLS_JI3V);


    }



}

