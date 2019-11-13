package EscribirXML;

import CocheXML.CocheModel;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;

public class EscribirXML {
    
     static ArrayList<CocheModel>arrayCoche;
     
     public EscribirXML(){
     
         arrayCoche = new ArrayList();
     
     }
 public static void main(String args[]) throws IOException{
    
    DocumentBuilderFactory factory =
    DocumentBuilderFactory.newInstance();
    
    arrayCoche = new ArrayList();
    
    CocheModel c1 = new CocheModel("seat","2323DDD");
    arrayCoche.add(c1);
    CocheModel c2 = new CocheModel("mercedez","2323AAA");
    arrayCoche.add(c2);    
    CocheModel c3 = new CocheModel("seat","2323BBB");
    arrayCoche.add(c3);
    
    
    try{
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "Empleados", null);
        document.setXmlVersion("1.0"); 

      for(int i = 0;i < arrayCoche.size();i++) {
          Element raiz = document.createElement("Coche");  
          document.getDocumentElement().appendChild(raiz);
          
          CrearElemento("modelo", arrayCoche.get(i).getMarca().trim(), raiz, document); 
          CrearElemento("matricula",arrayCoche.get(i).getMatricula(), raiz, document); 
          
      }   
        
      Source source = new DOMSource(document);
      Result result = new StreamResult(new java.io.File("Coches.xml"));        
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.transform(source, result);
      
     }catch(Exception e){ System.err.println("Error: "+e); }

 }//fin de main
 
 //Inserción de los datos del empleado
    static void  CrearElemento(String datoEmple, String valor,Element raiz, Document document){

        Element elem = document.createElement(datoEmple); 
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor		 	
    }

}//fin de la clase
