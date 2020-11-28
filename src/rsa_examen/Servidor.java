
package rsa_examen;

//Importamos las librerias
import com.sun.istack.internal.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author Daniel Rodriguez Flores 5IV8
 */
public class Servidor extends Observable implements Runnable{

    private int puerto;
    
    public Servidor(int puerto){
       
        this.puerto = puerto;
       
    }
    
    
    @Override
    public void run() {
        
        //Variables
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        
        try{
        
            //Creamos el socket del servidor
            servidor = new ServerSocket(puerto);
            System.out.println("Server iniciado");
            
            //Implementamos un while para que simpre reciba peticiones
            while(true){
            
                //Aceptmos al cliente
                sc = servidor.accept();
                
                in = new DataInputStream(sc.getInputStream());
//                out = new DataOutputStream(sc.getOutputStream());
                
                //Leemos el mensaje que envia
                String mensaje_c = in.readUTF();
                System.out.println(mensaje_c);
                
                this.setChanged();
                this.notifyObservers(mensaje_c);
                this.clearChanged();
                
                //Cerramos el socket
                sc.close();
                
            }
            
        }catch(IOException ex){
            //Logger.getLogger((Servidor.class.getName()).log(Level.SEVERE,null, ex));
        }
        
    }
    
}
