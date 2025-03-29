package exercises.exercise8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlBookParser {
    public static void main(String[] args) throws Exception {
        File file = new File("exercises/exercise8/books.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        NodeList books = doc.getElementsByTagName("Book");

        System.out.println("Raw XML Book Data");
        for (int i = 0; i < books.getLength(); i++) {
            Node book = books.item(i);
            printBook(book);
        }

        //Create New Book
        Element newBook = doc.createElement("Book");

        createElementWithText(doc, newBook, "id", "04");
        createElementWithText(doc, newBook, "language", "Go");
        createElementWithText(doc, newBook, "edition", "first");
        createElementWithText(doc, newBook, "title", "Go in Action");
        createElementWithText(doc, newBook, "publishedYear", "2015");
        createElementWithText(doc, newBook, "numberOfPages", "300");

        Element authors = doc.createElement("authors");
        Element author = doc.createElement("author");
        author.setTextContent("William Kennedy");
        authors.appendChild(author);
        newBook.appendChild(authors);

        doc.getDocumentElement().appendChild(newBook);

        System.out.println("\n Added XML book data");
        NodeList updatedBooks = doc.getElementsByTagName("Book");
        for (int i = 0; i < updatedBooks.getLength(); i++) {
            Node book = updatedBooks.item(i);
            printBook(book);
        }
    }
    static void printBook(Node bookNode) {
        NodeList children = bookNode.getChildNodes();
        for (int j = 0; j < children.getLength(); j++) {
            Node child = children.item(j);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(child.getNodeName() + " : " + child.getTextContent());
            }
        }
    }

    static void createElementWithText(Document doc, Element parent, String name, String text){
    Element elem = doc.createElement(name);
    elem.setTextContent(text);
    parent.appendChild(elem);
    }
}