package SocketsTCP;

import java.net.*;
import java.io.*;

public class Cliente {
  public static void main(String[] args)  throws IOException {
    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    System.out.println("\tInterfaz del Cliente\n");
    try {
      socketCliente = new Socket("localhost", 4444);
      // Obtenemos el canal de entrada
      
      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
      
      // Obtenemos el canal de salida
      salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
    } catch (IOException e) {
	System.err.println("No puede establer canales de E/S para la conexión");
        System.exit(-1);
    }
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String linea;
    
    try {
    	boolean sw = true;
      while (sw) {

        System.out.println("\tMenu");
        System.out.println("1. Opcion 1\n"+"2. Opcion 2\n"+"3. Opcion 3\n"+"Salir\n");
			
    	linea = stdIn.readLine();
        
        salida.println(linea);
        
        if (linea.equals("salir")) sw = false ;
        else {
        linea = entrada.readLine();
        System.out.println("\nRespuesta servidor: " + linea);
        }
        
      }
    } catch (IOException e) {
	System.out.println("IOException: " + e.getMessage());
    }
 
    // Libera recursos
    salida.close();
    entrada.close();
    stdIn.close();
    socketCliente.close();
  }
}