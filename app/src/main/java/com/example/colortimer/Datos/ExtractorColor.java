package com.example.colortimer.Datos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


/*AUN FALTAN PRUEBAS CON LA NUEVA MODIFICACIÖN DE LA CAMARA*/
public class ExtractorColor {

	private Context context;
	public ExtractorColor(Context context){
		this.context = context;
	}

	/*Este método extrae el color a partir del bitmap recibido*/

	public MyColor extraerColor(Bitmap imgBitmap) {
		MyColor color = null;
		try {
			color = extraerColorDominante(imgBitmap);

			String texto = "Color de la imagen: " + color.getValor();
			Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();

		} catch (Exception e) {

			Toast.makeText(context, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
			color = new MyColor("FFFFFF");
		}
		return color;
	}

	public Bitmap obtenerBitmap(File fileImage){
		String filePath = fileImage.getPath();
		Bitmap imagen = BitmapFactory.decodeFile(filePath);

		return imagen;
	}

	public MyColor extraerColorDominante(Bitmap imagen) {

		final int MUESTRAS_WIDTH = 5; // Modificar para obtener mas/menos muestras
		final int MUESTRAS_HEIGHT = 5; // Modificar para obtener mas/menos muestras

		int width = imagen.getWidth();
		int height = imagen.getHeight();

		int distanciaX = width /MUESTRAS_WIDTH; // Distancia entre cada muestra x
		int distanciaY = height/MUESTRAS_HEIGHT; // Distancia entre cada muestra y

		int x = distanciaX/2; // Punto en el eje x
		int y = distanciaY/2; // Punto en el eje y

		//int matrizPixeles[][] = new int[MUESTRAS_HEIGHT][MUESTRAS_WIDTH]; // Guardaremos los pixeles aqui

		int sumaRojo = 0;
		int sumaVerde = 0;
		int sumaAzul = 0;
		int pixel;
		// Tomamos muestras y obtenemos el rgb indiviula de cada pixel
		for(int i = 0; i < MUESTRAS_HEIGHT; i++) {
			for(int j = 0; j < MUESTRAS_WIDTH; j++){
				pixel = imagen.getPixel(x, y);
				sumaRojo += Color.red(pixel);
				sumaVerde += Color.green(pixel);
				sumaAzul += Color.blue(pixel);

				x += distanciaX;
			}

			y += distanciaY; // Avanzo en el eje Y
			x = distanciaX/2; // Reinicio x para recorrer dicho eje con el nuevo valor de y
		}


		//Calculamos el promedio
		int totalMuestras = MUESTRAS_HEIGHT*MUESTRAS_WIDTH;
		int rojoAvg = sumaRojo/totalMuestras;
		int verdeAvg = sumaVerde/totalMuestras;
		int azulAvg = sumaAzul/totalMuestras;

		//Obtenemos el color en valor decimal a partir de los promedios rgb obtenidos
		int colorDominante = (rojoAvg << 16) + (verdeAvg << 8) + azulAvg;
		int colorDominante2 = Color.rgb(rojoAvg, verdeAvg, azulAvg);


		MyColor color = new MyColor(colorDominante, colorDominante, colorDominante2);

		return color;
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





