/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_persistencia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jhoan
 */
public class PersistenciaContabilidad {
    
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/contabilidad?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "empanada123";
    
    public Connection cadena;

    public PersistenciaContabilidad() {
         this.cadena = null;
    }
    
    
    public Connection conectar() throws SQLException {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return this.cadena;

    }

    public void desconectar() {
        try {
            this.cadena.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // ------ Credenciales
    public boolean findUser (String as_user, String as_pass){
        boolean mensaje = false;
        String sql="";
        int r=0;
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM credenciales WHERE usuario ='"+as_user+"' and pass = '"+as_pass+"'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                r+=1;
                res.getString(1);
                res.getString(2);
            }
            if(r==1){
                mensaje=true;
            }else{
            mensaje = false;
        }
            cn.close();
        }catch (SQLException e)
        {
            System.out.println(e);
        }
        return mensaje;
    }
    
    public static String insertarTipoCuenta(String tc_codigo, String tc_nombre)
    {     
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "Insert INTO tipocuenta VALUES (?,?)";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tc_codigo);
            pst.setString(2, tc_nombre);
            pst.execute();
            mensaje = "Insertado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo insertar";
        }
        return mensaje;
    }
    
    public static String listarTipoCuenta()
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM tipocuenta";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){       
                mensaje += res.getString(1)+";"+res.getString(2)+";/";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo listar";
        }
        return mensaje;
    }
    
    
    public static String mostrarTipoCuenta(String as_codigo)
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM tipocuenta WHERE tc_codigo="+as_codigo+"";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje = res.getString(1)+";"+res.getString(2)+";";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
      
     public static String actualizarTipoCuenta(String as_codigo, String as_nombre)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "UPDATE tipocuenta SET tc_nombre = ? WHERE tc_codigo = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_nombre);
            pst.setString(2, as_codigo);
            pst.execute();
            mensaje = "actualizado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo actualizar";
        }
        return mensaje;
    }
    
    public static String eliminarTipoCuenta (String as_codigo)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "DELETE FROM tipocuenta WHERE tc_codigo = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigo);
            pst.execute();
            mensaje = "eliminado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo eliminar";
        }
        return mensaje;
    } 
    
    public static String buscarTipoCuenta(String as_pBuscar)
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM tipocuenta WHERE tc_nombre='"+as_pBuscar+"'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje = res.getString(1)+";"+res.getString(2)+";";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
    
    
    
    // ---Cuenta
    public static String insertarCuenta(String as_codigo,String tc_codigo, String as_nombre)
    {     
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "Insert INTO cuenta VALUES (?,?,?)";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigo);
            pst.setString(2, tc_codigo);
            pst.setString(3, as_nombre);
            pst.execute();
            mensaje = "Insertado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo insertar";
        }
        return mensaje;
    }
    
    public static String listarCuenta()
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM cuenta";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje += res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+"/";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo listar";
        }
        return mensaje;
    }
    
    
    public static String mostrarCuenta(String as_codigo)
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM cuenta WHERE c_codigo='"+as_codigo+"'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje = res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
      
     public static String actualizarCuenta(String as_codigo,String tc_codigo, String as_nombre)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "UPDATE cuenta SET c_nombre = ?, tc_codigo=? WHERE c_codigo = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_nombre);
            pst.setString(2, tc_codigo);
            pst.setString(3, as_codigo);
            pst.execute();
            mensaje = "actualizado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo actualizar";
        }
        return mensaje;
    }
    
    public static String eliminarCuenta (String as_codigo)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "DELETE FROM cuenta WHERE c_codigo = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigo);
            pst.execute();
            mensaje = "eliminado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo eliminar";
        }
        return mensaje;
    } 
    
    public static String buscarCuenta(String as_pBuscar)
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM cuenta WHERE c_nombre='"+as_pBuscar+"'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje = res.getString(1)+";"+res.getString(2)+";";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
    
    // --Comprobante
    public static String insertarComprobante(Integer as_codigo, String as_fecha, String observaciones)
    {     
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "Insert INTO cabeceracomprobante VALUES (?,?,?)";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, as_codigo);
            pst.setString(2, as_fecha);
            pst.setString(3, observaciones);
            pst.execute();
            mensaje = "Insertado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo insetar";
        }
        return mensaje;
    }
    
     
    public static String mostrarComprobante(Integer as_codigo)
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM cabeceracomprobante WHERE cc_numero="+as_codigo+"";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje = res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
      
    public static String actualizarComprobante(Integer as_codigo, String as_fecha, String as_observaciones)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "UPDATE cabeceracomprobante SET cc_fecha = ?, cc_observaciones = ? WHERE cc_numero = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_fecha);
            pst.setString(2, as_observaciones);
            pst.setInt(3, as_codigo);
            pst.execute();
            mensaje = "actualizado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo actualizar";
        }
        return mensaje;
    }
    
    public static String eliminarComprobante (Integer as_codigo)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "DELETE FROM cabeceracomprobante WHERE cc_numero = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, as_codigo);
            pst.execute();
            mensaje = "eliminado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo eliminar";
        }
        return mensaje;
    } 
    
    public static String listarComprobante()
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM cabeceracomprobante";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){       
                mensaje += res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+"/";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo listar";
        }
        return mensaje;
    }
    
    public static String buscarComprobanteFechas(String fecha1, String fecha2)
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM cabeceracomprobante WHERE cc_fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje = res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4)+";"+res.getString(5)+";";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
    
    // --- Detalle Comprobante
    public static String insertarDetalleComprobante(String as_codigo, String as_codigoCuenta,Integer as_codigoCompro, Float debe, Float haber){
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "Insert INTO detallecomprobante VALUES (?,?,?,?,?)";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigo);
            pst.setString(2, as_codigoCuenta);
            pst.setInt(3, as_codigoCompro);
            pst.setFloat(4, debe);
            pst.setFloat(5, haber);
            System.out.println("SQL: " +pst.toString());
            pst.execute();
            mensaje = "Insertado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo insetar";
        }
        return mensaje;
    }
    
      
    public static String mostrarDetalleComprobante(String as_codigo)
    {
        String mensaje="";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM detallecomprobante WHERE dc_codigo='"+as_codigo+"'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje = res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4)+";"+res.getString(5)+";";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
      
     public static String actualizarDetalleComprobante(String as_codigo,Integer as_codigoComprobante, String as_codigoCuenta, Float as_debe, Float as_haber)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "UPDATE detallecomprobante SET c_codigo = ?, dc_debe = ?, dc_haber=? WHERE dc_codigo = ? AND cc_numero = ?";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigoCuenta);
            pst.setFloat(2, as_debe.floatValue());
            pst.setFloat(3, as_haber.floatValue());
            pst.setString(4, as_codigo);
            pst.setInt(5, as_codigoComprobante);
            pst.execute();
            mensaje = "actualizado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo actualizar";
        }
        return mensaje;
    }
    
    public static String eliminarDetalleComprobante (String as_codigo)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "DELETE FROM detallecomprobante WHERE dc_codigo = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigo);
            pst.execute();
            mensaje = "eliminado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo eliminar";
        }
        return mensaje;
    } 
    
    public static String listarDetalleComprobante(Integer as_idComprobante)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM detallecomprobante WHERE cc_numero = "+as_idComprobante+"";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){       
                mensaje += res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4)+";"+res.getString(5)+"/";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo listar";
        }
        return mensaje;
    }
    
    public static String listarDetalleComprobanteAll()
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT * FROM detallecomprobante";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){       
                mensaje += res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4)+";"+res.getString(5)+"/";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo listar";
        }
        return mensaje;
    }
    
    public String reporte1Man()
    {
        String mensaje = "";
        String sql = "";
        PersistenciaContabilidad cone = new PersistenciaContabilidad();
        try{
            sql = "SELECT c_codigo,dc_debe, dc_haber FROM detallecomprobante";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){       
                mensaje += res.getString(1)+";"+res.getString(2)+"/";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo listar";
        }
        return mensaje;
    }
}
