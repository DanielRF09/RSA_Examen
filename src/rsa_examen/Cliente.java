
package rsa_examen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Daniel Rodriguez Flores 5IV8
 */
public class Cliente implements Runnable{

    private String host;
    private int puerto;
    private String mensaje_c;
    
    public Cliente(String host, int puerto, String mensaje_c){
        this.host = host;
        this.puerto = puerto;
        this.mensaje_c = mensaje_c;
    
    }
    
    
    @Override
    public void run() {
        
        //Ip del servidor
        
          
        DataOutputStream out;
        
        try{
           //Creamos el Socket
           Socket sc = new Socket(host, puerto);
           
           out = new DataOutputStream(sc.getOutputStream());
           
           //Enviamos in mensaje
           out.writeUTF(mensaje_c);
           
           sc.close();
           
        
        }catch(Exception e){}
    }
    
}
