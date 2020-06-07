package SocketsTCP;
import java.io.*;
import java.net.*;

public class Servidor {  

	public static final int port = 4444;
	public static void main(String[] args) throws IOException {
		// Establece el puerto en el que escucha peticiones
		ServerSocket socketServidor = null;
		try {
			socketServidor = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("No hay respuesta desde el puerto: " + port);
			System.exit(-1);
		}

		Socket socketCliente = null;
		BufferedReader entrada = null;
		PrintWriter salida = null;

		System.out.println("Iniciando servidor... ");
		try {

			while(true) {
				socketCliente = socketServidor.accept();
				System.out.println("\nConnexión establecida con el Cliente: \n");
				// Establece canal de entrada
				entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				// Establece canal de salida
				salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
				boolean salir = false;
				while(!salir) {

						String str = entrada.readLine();
						switch (str){
						case "1":
							salida.println("Palel");
							break;
						case "2":
							salida.println("Piedra");
							break;
						case "3":
							salida.println("Tijera");
							break;
						case "salir":
							salir = true;
							salida.println("salir");
							break;     			
						}
						if(str.equals("1"))
							str = "opcion 1";
						if(str.equals("2"))
							str = "opcion 2";
						if(str.equals("3"))
							str = "opcion 3";
						System.out.println("Respuesta del Cliente: "+str);
				}        
			}

		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}  
		salida.close();
		entrada.close();
		socketCliente.close();
		socketServidor.close();
	}
}
