/**
 * Created by trixi on 2017.02.02..
 */

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;


public abstract class Store implements StoreCapable
{
    private static String filename = "productsXmlFile.xml";

    protected abstract void storeProduct(Product product);

    public void storeCDProduct(String name, int price, int tracks)
    {
        Product cd = createProduct("CD", name, price, tracks);
        store(cd);
    }

    public void storeBookProduct(String name, int price, int size)
    {
        Product book = createProduct("Book", name, price, size);
        store(book);
    }

    protected Product createProduct(String type, String name, int price, int size)
    {
        if(type.equals("CD"))
        {
            return new CDProduct(name, price, size);
        }
        else
        {
            return new BookProduct(name, price, size);
        }

    }

    private void saveToXml(Product product)
    {
        File xmlFile = new File(filename);
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc;
            Element rootElement;

            if(xmlFile.exists())
            {
                doc = dBuilder.parse(xmlFile);
                rootElement = doc.getDocumentElement();
            }

            else
            {
                doc = dBuilder.newDocument();
                rootElement = doc.createElement("Products");
                doc.appendChild(rootElement);
            }

            //  element
            Element name = doc.createElement("Product");
            rootElement.appendChild(name);

            // setting attribute to element
            Attr attr = doc.createAttribute("Name");
            attr.setValue(product.name);
            name.setAttributeNode(attr);
            Attr attr2 = doc.createAttribute("Price");
            attr2.setValue(product.price.toString());
            name.setAttributeNode(attr2);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<Product> loadProducts()
    {
        ArrayList<Product> loadProductsList = new ArrayList<>();


        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Product");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String instanceName = eElement.getAttribute("Name");
                    int instancePrice = Integer.parseInt(eElement.getAttribute("Price"));

                    loadProductsList.add(createProduct("CD", instanceName, instancePrice, 0));
                }
            }
            return loadProductsList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return loadProductsList;
    }

    public void store(Product product)
    {
        saveToXml(product);
        storeProduct(product);
    }
}