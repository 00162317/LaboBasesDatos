/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UCA
 * URL: Es el lugar donde esta alojado nuestra base de datos donde lleva el nombre del SERVIDOR en el URL
 * User: Usuario de la base de datos
 * Pass: Es la contraseña del usuario User
 */
public class Conexion {
    private String user,pass,driver,url;
    private Connection cnx;
    public static Conexion instance;
    public synchronized static Conexion conectar(){
        if(instance == null){
            return new Conexion();
        }
        return instance;
    }
    private Conexion(){
        cargarCredenciales();
        try{
            //Le enviamos el driver que usara para la conexion a la base de datos
            Class.forName(this.driver);
            cnx=(Connection) DriverManager.getConnection(this.url,this.user,this.pass);
            
        } catch (Exception ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    //Creedenciales de mi servidor de bases de datos
    private void cargarCredenciales() {
        user="root";
        pass="";
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost/filtros";
        
    }
    public Connection getCnx(){
        return cnx;
    }
    public void cerrarConexion(){
        instance = null;
    }
//Fin por aqui
}
