import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOM {
    public static void main(String[] args) {
        double SumaDesc, TotaPagarDia, TotalPagar = 0;
        double count;
        String uds, desc, precioud;


        try {
            //Indicaremos la ruta del fichero xml
            //Src es el nombre raiz de nuestro proyecto, main es la primera carpeta, resources la siguiente, dentro de xml encontraremos el fichero
            //Esta es la ruta relativa.
            //Creamos los objetos que nos permitiran leer el fichero

            File fichero = new File("C:\\Users\\fjsequera\\Desktop\\ACCDAT\\Tarea2.2LecturadeXML\\compras.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Le pasamos el XML
            Document doc = db.parse(fichero);
            doc.getDocumentElement().normalize();


            NodeList nodeList = doc.getElementsByTagName("compra");

            //Creamos un primer bucle para recorrer los datos desde la etiqueta compra
            for (int i = 0; i< nodeList.getLength(); i++) {
                //Inicializamos las variables con las que queremos reiniciar su valor por cada compra
                count = 0;
                SumaDesc=0;
                TotaPagarDia = 0;
                Node nodo = nodeList.item(i);


                if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                    //Creamos el elemento y una nueva lista con las etiquetas hija de compra
                    Element eElement = (Element) nodo;
                    NodeList hijos1 = nodo.getChildNodes();
                    System.out.println(eElement.getElementsByTagName("fecha").item(0).getTextContent()+"\n");

                    //Creamos un segundo bucle para recorrer los datos de las etiquetas hija de fecha y tickt
                    for (int j = 0; j < hijos1.getLength(); j++) {
                        Node nodo1 = hijos1.item(j);

                        if (nodo1.getNodeType() == Node.ELEMENT_NODE) {
                            //Creamos una nueva lista que contiene los datos de las etiquetas hija de ticket y fecha
                            NodeList hijo2 = nodo1.getChildNodes();

                            //Añadimos un último bucle para recorrer los datos de las etiquetas producto
                            for (int k = 0; k < hijo2.getLength(); k++) {
                                Node nodo2 = hijo2.item(k);

                                if (nodo2.getNodeType() == Node.ELEMENT_NODE) {
                                    Element element2 = (Element) nodo2;
                                    //Introducimos en una variable String el valor ed la etiqueta precio_unidad
                                    precioud = element2.getElementsByTagName("precio_unidad").item(0).getTextContent();

                                    //Adición de unidades a la cantidad de productos comprados por dia
                                    if(element2.getElementsByTagName("unidades").item(0) != null) {//comprueba las unidades de cada producto. Si no existe la etiqueta unidades en el producto, las unidades por defecto serán 1.
                                        uds = element2.getElementsByTagName("unidades").item(0).getTextContent();//Introducimos el valor de la etiqueta unidades en una variable cadena.
                                        count = count + Double.parseDouble(uds);//La añadimos al contador de cantidad de productos las unidades compradas.
                                        TotaPagarDia = TotaPagarDia + Double.parseDouble(precioud)*Double.parseDouble(uds);//Añadimos al total de pagar por dia el precio * las unidades.
                                    }
                                    else{
                                        TotaPagarDia = TotaPagarDia + Double.parseDouble(precioud);//Si las unidades son 1, añadimos el precio por unidad sin más.
                                        //Mediante este contador contamos la cantidad de productos por dia.
                                        count++;
                                    }

                                    if(element2.getElementsByTagName("descuento").item(0) != null) {//Si existe la etiqueta descuento:
                                        desc = element2.getElementsByTagName("descuento").item(0).getTextContent();//Recogemos el valor de descuento en una variable cadena
                                        SumaDesc = SumaDesc + Double.parseDouble(desc); //Añadimos el valos de descuento al total de descuento por dia
                                    }
                                }
                            }
                        }
                    }

                    //Vamos sumando el total de pagar por dia
                    TotaPagarDia = TotaPagarDia-SumaDesc;
                    //Realizamos el cálculo del total a pagar de todos los dias
                    TotalPagar = TotalPagar + TotaPagarDia;

                    //Mostramos por pantalla los resultados
                    System.out.println("Cantidad de productos comprados en total: "+count);
                    System.out.println("Suma de todos los descuentos: "+SumaDesc);
                    System.out.println("El total a pagar será: " +TotaPagarDia + "\n");
                }
            }
            System.out.print("Resumen total de la compra: "+TotalPagar);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}