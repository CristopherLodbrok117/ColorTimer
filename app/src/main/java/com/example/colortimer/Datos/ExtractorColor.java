import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*AUN FALTAN PRUEBAS CON LA NUEVA MODIFICACIÖN DE LA CAMARA*/
public class ExtractorColor { 
    
		
		/*Este método extrae el color a partir del nombre del archivo recibido*/
    public static void extraerColor(String imagenName) {
    	try {
            // Cargamos la imagen desde un archivo
            File imagenFile = new File(imagenName);
            BufferedImage imagen = ImageIO.read(imagenFile);
            int color = extraerColorDominante(imagen);
            
            System.out.println("Color de la imagen: " + color);
            System.out.println("Hexa: "+MyColor.convertirAHexadecimal(color));
            
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

		/*Este método extrae el color a partir del archivo recibido*/ 
    public static void extraerColor(File imagenFile) {
    	try {
            // Cargamos la imagen desde un archivo
            BufferedImage imagen = ImageIO.read(imagenFile);
            
            int color = extraerColorDominante(imagen);
            
            System.out.println("\nColor de la imagen: " + color);
            System.out.println("Hexa: "+MyColor.convertirAHexadecimal(color));
            
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }
    
		/* Recibe una imagen, obtiene los colores rgb de una matriz de n*n (puntos de la imagen) y a partir de su promedio individual
				genera un nuevo color que representa el color promedio de toda la imagen
		*/
    public static int extraerColorDominante(BufferedImage imagen) {
    	final int MUESTRAS_WIDTH = 5; // Modificar para obtener mas/menos muestras
    	final int MUESTRAS_HEIGHT = 5; // Modificar para obtener mas/menos muestras
    	
    	int width = imagen.getWidth();
    	int height = imagen.getHeight();
    	
    	int distanciaX = width /MUESTRAS_WIDTH; // Distancia entre cada muestra x
    	int distanciaY = height/MUESTRAS_HEIGHT; // Distancia entre cada muestra y
    			
    	int x = distanciaX/2; // Punto en el eje x
    	int y = distanciaY/2; // Punto en el eje y
    	
    	Color matrizColores[][] = new Color[MUESTRAS_HEIGHT][MUESTRAS_WIDTH]; // Color de la libreria java.awt.Color;
    	
    	// Llenamos matriz con colores
    	for(int i = 0; i < MUESTRAS_HEIGHT; i++) {
    		for(int j = 0; j < MUESTRAS_WIDTH; j++){
    			matrizColores[i][j] = new Color(imagen.getRGB(x, y));
    			x += distanciaX;
    		}
    		
    		y += distanciaY; // Avanzo en el eje Y
    		x = distanciaX/2; // Reinicio x para recorrer dicho eje con el nuevo valor de y
    	}
    	
			//inicializamos sumas
    	int sumaRojo = 0;
    	int sumaVerde = 0;
    	int sumaAzul = 0;
    	
    	//Sumamos colores (rojo, verde y azul individualmente)
    	for(int i = 0; i < MUESTRAS_HEIGHT; i++) {
    		for(int j = 0; j < MUESTRAS_WIDTH; j++){
    			sumaRojo += matrizColores[i][j].getRed();
    			sumaVerde += matrizColores[i][j].getGreen();
    			sumaAzul += matrizColores[i][j].getBlue();
    		}
    	}
    	
			//Calculamos el promedio
    	int totalMuestras = MUESTRAS_HEIGHT*MUESTRAS_WIDTH;
    	int rojoAvg = sumaRojo/totalMuestras;
    	int verdeAvg = sumaVerde/totalMuestras;
    	int azulAvg = sumaAzul/totalMuestras;
    	
			//Obtenemos el color en valor decimal a partir de los promedios rgb obtenidos
    	int colorDominante = (rojoAvg << 16) + (verdeAvg << 8) + azulAvg;
    	
    	return colorDominante;
    }
    

}








/**En este ejemplo, primero cargamos la imagen desde un archivo utilizando 
 * la clase ImageIO de Java. A continuación, extraemos el color del píxel 
 * en la posición (x,y) utilizando el método getRGB() de la clase 
 * BufferedImage. Este método devuelve un entero que representa el color 
 * en formato RGB. Finalmente, creamos un objeto de la clase Color a partir 
 * de este entero y utilizamos los métodos getRed(), getGreen() y getBlue() 
 * para obtener los componentes RGB del color.
 * 
 * 
 * Para obtener el promedio de n colores hexadecimales, primero debemos 
 * convertir cada color hexadecimal a su valor decimal correspondiente 
 * para poder realizar el cálculo. Luego, podemos calcular el promedio 
 * de cada componente de color (rojo, verde, azul) por separado y 
 * combinar los resultados en un nuevo valor hexadecimal.
 * 
 * En este ejemplo, tenemos un arreglo de String llamado hexColors que 
 * contiene los 4 colores hexadecimales. Utilizamos un bucle for para 
 * iterar a través de cada color en el arreglo y convertir cada 
 * componente de color (rojo, verde, azul) a su valor decimal 
 * correspondiente utilizando Integer.parseInt().
 * 
 Luego, sumamos cada componente de color a la variable correspondiente 
(redSum, greenSum, blueSum). Después de eso, calculamos el promedio de 
cada componente de color por separado dividiendo la suma entre la 
longitud del arreglo hexColors. El resultado de cada promedio se almacena 
en las variables redAvg, greenAvg y blueAvg.

Finalmente, combinamos los valores promedio de cada componente de color 
en un nuevo valor hexadecimal utilizando String.format() y lo imprimimos 
en la consola utilizando System.out.println().*/





