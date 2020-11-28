
package rsa_examen;

/**
 *
 * @author Daniel Rodriguez Flores 5IV8
 */

//Importamos las librerias

import java.util.*;
import java.io.*;
import java.math.BigInteger;


public class RSA {
    //Variables
    int tamPrimo;
    private BigInteger p,q,n;
    private BigInteger phi;
    private BigInteger e, d;
    
    //Creamos el constructor de la clase
    public RSA(int tamPrimo) {
        this.tamPrimo = tamPrimo;
        generarPrimos();//Genera p y q
        generarClaves();//Genera e y d

    }
    
    public RSA(BigInteger p,BigInteger q,int tamPrimo) {
        this.tamPrimo=tamPrimo;
        this.p=p;
        this.q=q;
        generarClaves();
    }
    
    //Vamos a generar los numeros primos a partir de los que ingrese el usuario
    public void generarPrimos(){
        p = new BigInteger(tamPrimo, 100, new Random());
        do q = new BigInteger(tamPrimo, 100, new Random());
            while(q.compareTo(p)==0);
    }
    
    //Vamos a generar las claves
    public void generarClaves(){
        
        // n = p * q
        n = p.multiply(q);
        
        // phi = (p-1)*(q-1)
        phi = p.subtract(BigInteger.valueOf(1));//p-1
        
        phi = phi.multiply(q.subtract(BigInteger.valueOf(1)));
        
        // Ahora vamos a calcular el coprimo para e
        do e = new BigInteger(2 * tamPrimo, new Random());
            while((e.compareTo(phi) != -1) || (e.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));
        
        //Calcular:  d = e^1 mod phi
        d = e.modInverse(phi);
        
    }
    
    //Ahora vamos a cifrar usando la llave publica
    public BigInteger[] encriptar(String mensaje){
        
        //Variables
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        BigInteger[] bigdigitos = new BigInteger[digitos.length];

        //lo primero es recorrer a bigdigitos para integrarlos al temporal
        for(i=0; i<bigdigitos.length;i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }

        BigInteger[] cifrado = new BigInteger[bigdigitos.length];

        //Ahora que conocemos el tamaño debemos cifrarlo
        for(i=0; i<bigdigitos.length; i++){
            cifrado[i] = bigdigitos[i].modPow(e,n);
        }
        
        return(cifrado);
    }
    
    
    //Ahora vamos a descifrar usando la clave privada
    public String desencripta(BigInteger[] cifrado) {
        
        BigInteger[] descifrado = new BigInteger[cifrado.length];
        
        //Primero tenemos que recorrerlo
        for(int i=0; i<descifrado.length; i++){
            //Aplicando el descifrado
            descifrado[i] = cifrado[i].modPow(d, n);
        }
        
        //Vamos a necesitar un arreglo de caracteres para toda la info
        char[] charArray = new char[descifrado.length];

        for(int i=0; i<charArray.length; i++){
            charArray[i] = (char) (descifrado[i].intValue());
        }
        
        return(new String(charArray));
    }
    
    //Los metodos para enviar p, q, n, phi, e, d
    public BigInteger dameP(){
        return p;
    }

    public BigInteger dameQ(){
        return q;
    }

    public BigInteger damePhi(){
        return phi;
    }

    public BigInteger dameN(){
	return n;
    }

    public BigInteger dameE(){
	return e;
    }

    public BigInteger dameD(){
	return d;
    }
    
    
}
