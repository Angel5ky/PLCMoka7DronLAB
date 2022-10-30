package PaquetePrincipal;

import java.io.IOException;

/* @author Ángel López. 
Descripción:
Obtención de datos de PLC S71200  para realidad aumentada.*/
import Moka7.*;

public class Main {
    //Parámetros necesarios para la lectura del PLC

    private static byte[] Buffer = new byte[65536]; //64K buffer
    private static final S7Client Cliente = new S7Client();
    
    private static String DireccionIP = "192.168.1.253";    // IP asignada al PLC.
    private static int Rack = 0;                            // Rack en el que está ubicado el PLC.
    private static int Slot = 0;                            // Slot en el que se ubica el PLC.
    private static int DB = 1;                              // DB de la que se quieren extraer datos.
    


   
    public static boolean Connect()
    {
    	Cliente.SetConnectionType(S7.OP);                                   // Permisos de acceso al PLC.
    	int Result = Cliente.ConnectTo(DireccionIP, Rack, Slot);            // Conexión al PLC. Devolverá un 0 si la conexión al PLC es correcta.
    	if (Result==0)
    	{
            System.out.println("Conexión con PLC establecida");
    	}
        else{
            System.out.println("Fallo en la conexión con el PLC");

        }
    	return Result == 0;
    }
    
    
  
    public static void main(String[] args) throws IOException 
    {
        if (args.length == 3){
            System.out.println("Conexión mediante consola a:\nIP: = " + args[0] + "\nRack: " + args[1] + "\nSlot: " + args[2]);
            DireccionIP = args[0];
            Rack = Integer.valueOf(args[1]);
            Slot = Integer.valueOf(args[2]);
            Connect();
        }


    	if (args.length != 3) 
    	{
            if(Connect()){
                int Result = Cliente.ReadArea(S7.S7AreaDB, DB, 0, 528, Buffer);     // Clase para leer los datos del DB seleccionado
                System.out.println(S7.GetBitAt(Buffer, 268, 1));                         // La clase S7 tiene métodos de ayuda para traducir los datos del buffer
                Cliente.Disconnect();
            }
    	}
    }
}
