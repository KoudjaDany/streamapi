package com.ddf.training.streamapi.problem1;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try {
            Document document = readXMLDocument("src/main/resources/students.xml");
            Node rootNode = document.getElementsByTagName("students").item(0);
            children(rootNode).forEach(Main::printNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Document readXMLDocument(String documentPath) throws ParserConfigurationException, SAXException, IOException {
        File xmlFile = new File(documentPath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();
        return document;
    }

    private static Stream<Node> children(Node parent) {
        NodeList nodeList = parent.getChildNodes();
        return IntStream
                .range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(node -> node.getNodeType()!= Node.TEXT_NODE);
    }

    private static void printNode(Node node) {
        printNodeAttributes(node.getAttributes());
    }

    private static void printNodeAttributes(NamedNodeMap namedNodeMap){
        IntStream.range(0, namedNodeMap.getLength())
                .mapToObj(namedNodeMap::item)
                .forEach(node -> System.out.printf("%s : %s\n",node.getNodeName(), node.getNodeValue()));
    }
}


