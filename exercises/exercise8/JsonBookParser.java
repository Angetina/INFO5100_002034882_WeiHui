package exercises.exercise8;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class JsonBookParser {
    public static void main(String[] args) throws IOException {
        File file = new File("exercises/exercise8/books.json");//Java File path

        //Creating an ObjectMapper parser
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);

        ArrayNode bookList = (ArrayNode) rootNode.get("book");

        System.out.println("Raw JSON content");
        for (JsonNode book : bookList){
            System.out.println(book.toPrettyString());
        }
        //Create new book information
        ObjectNode newBook = mapper.createObjectNode();
        newBook.put("id","04");
        newBook.put("language", "Go");
        newBook.put("edition", "first");
        newBook.put("title", "Go in Action");
        newBook.put("publishedYear", 2015);
        newBook.put("numberOfPages", 300);
        ArrayNode authors = mapper.createArrayNode();
        authors.add("William Kennedy");
        newBook.set("authors",authors);

        //Add New Book
        bookList.add(newBook);

        System.out.println("\n Added JSON content");
        for(JsonNode book: bookList){
            System.out.println(book.toPrettyString());
        }
    }
}
