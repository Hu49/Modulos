package pkg_persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author josep
 */
public class PersistenciaNomina {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/arquitectura?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASWORD = "Alfa123";

    public Connection cadena;

    public PersistenciaNomina() {
        this.cadena = null;
    }

    public Connection conectar() throws SQLException {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL, USER, PASWORD);

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
    public static boolean findUser (String as_user, String as_pass){
        boolean mensaje = false;
        String sql="";
        int r=0;
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM credenciales WHERE usuario ='"+as_user+"'";
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
    
    public static String crearUsuario(String as_user, String as_pass)
    {     
        String mensaje="";
        String sql = "";
        boolean existe = false;
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            existe = findUser(as_user, as_pass);
            if (existe == false) {
                sql = "Insert INTO credenciales VALUES (null,?,?)";
                Connection con = cone.conectar();
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setString(1, as_user);
                pst.setString(2, as_pass);
                pst.execute();
                mensaje = "Usuario creado con exito";
                pst.close();
            } else{
                mensaje = "Usuario ya existe";
            }
           
        }catch (SQLException e)
        {
            mensaje = "no se pudo crear el usuario";
        }
        return mensaje;
    }
    
    //-------------Empleado

    public static String insertarEmpleado(String as_cedula, String as_nombre, String as_fechaIngreso, String as_sueldo) {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try {
            sql = "Insert INTO empleado VALUES (null,?,?,?,?)";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_cedula);
            pst.setString(2, as_nombre);
            pst.setString(3, as_fechaIngreso);
            pst.setString(4, as_sueldo);
            pst.execute();
            mensaje = "Insertado con exito";
            pst.close();
        } catch (SQLException e) {
            mensaje = "no se pudo insertar";
        }
        return mensaje;
    }

    public static String listarEmpleado() {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try {
            sql = "SELECT * FROM empleado";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while (res.next()) {
                mensaje += res.getString(1) + ";" + res.getString(2) + ";" + res.getString(3) +";"+ res.getString(4) + ";"+ res.getString(5) +"/";
            }
            cn.close();
        } catch (SQLException e) {
            mensaje = "no se pudo listar";
        }
        return mensaje;
    }

    public static String mostrarEmpleado(String as_codigo) {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try {
            sql = "SELECT * FROM empleado WHERE codigoEmp='" + as_codigo + "'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while (res.next()) {
                mensaje += res.getString(1) + ";" + res.getString(2) + ";" + res.getString(3)+ ";" + res.getString(4) + ";"+ res.getString(5)+ ";";
            }
            cn.close();
        } catch (SQLException e) {
            mensaje = "no se encontro";
        }
        return mensaje;
    }

    public static String actualizarEmpleado(String as_codigo, String as_cedula, String as_nombre, String as_fechaIngreso, String as_sueldo) {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try {
            sql = "UPDATE empleado SET cedulaEmp = ?, nombreEmp = ?, fechaIngresoEmp = ?, sueldoEmp = ? WHERE codigoEmp = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_cedula);
            pst.setString(2, as_nombre);
            pst.setString(3, as_fechaIngreso);
            pst.setString(4, as_sueldo);
            pst.setString(5, as_codigo);
            pst.execute();
            mensaje = "actualizado con exito";
            pst.close();
        } catch (SQLException e) {
            mensaje = "no se pudo actualizar";
        }
        return mensaje;
    }

    public static String eliminarEmpleado(String as_codigo) {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try {
            sql = "DELETE FROM empleado WHERE codigoEmp = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigo);
            pst.execute();
            mensaje = "eliminado con exito";
            pst.close();
        } catch (SQLException e) {
            mensaje = "no se pudo eliminar";
        }
        return mensaje;
    }

    public static String buscarEmpleado(String as_pBuscar) {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try {
            sql = "SELECT * FROM empleado WHERE cedulaEmp='" + as_pBuscar + "'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while (res.next()) {
                mensaje += res.getString(2) + ";" + res.getString(3) + ";" + res.getString(4)+ ";" + res.getString(5) + ";";
            }
            cn.close();
        } catch (SQLException e) {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
    
    //Motivo
    public static String insertarMotivo(String as_nombre, String as_signo)
    {     
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "Insert INTO motivo VALUES (null,?,?,null)";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            //pst.setString(1, as_codigo);
            pst.setString(1, as_nombre);
            pst.setString(2, as_signo);
            pst.execute();
            mensaje = "Insertado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo insertar";
        }
        return mensaje;
    }
    
    public static String listarMotivo()
    {
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM motivo";
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
    
    
    public static String mostrarMotivo(String as_codigo)
    {
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM motivo WHERE codigoMov='"+as_codigo+"'";
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
      
     public static String actualizarMotivo(String as_codigo, String as_nombre)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "UPDATE motivo SET nombreMov = ? WHERE codigoMov = ? ";
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
    
    public static String eliminarMotivo (String as_codigo)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "DELETE FROM motivo WHERE codigoMov = ? ";
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
    
    public static String buscarMotivo(String as_pBuscar)
    {
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM motivo WHERE nombreMov='"+as_pBuscar+"'";
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
    
    //Nomina
    public static String insertarNominaCab(String as_fecha, String as_empleado)
    {     
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "Insert INTO nominacab VALUES (null,?,?)";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_fecha);
            pst.setString(2, as_empleado);
            pst.execute();
            mensaje = "Insertado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo insetar";
        }
        return mensaje;
    }
    
     
    public static String mostrarNominaCab(String as_codigo)
    {
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM nominacab WHERE codigoNmnCab='"+as_codigo+"'";
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
      
    public static String actualizarNominaCab(String as_codigo, String as_fecha, String as_empleado)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "UPDATE nominacab SET fechaNmnCab = ?, empNmnCab = ? WHERE codigoNmnCab = ? ";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_fecha);
            pst.setString(2, as_empleado);
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
    
    public static String eliminarNominaCab(String as_codigo)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "DELETE FROM nominacab WHERE codigoNmnCab = ? ";
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
    
    public static String listarNominaCab()
    {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM nominacab";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){       
                mensaje += res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+"/";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo listar";
        }
        return mensaje;
    }
    
    public static String buscarNomina(String as_pBuscar)
    {
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM nominadtl WHERE codigoNmnDet='"+as_pBuscar+"'";
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
    
    //Detalle Nomina
    public static String insertarNominaDetalle(String as_codigoCab, String as_codigoMotivo, String as_valor){
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "Insert INTO nominadtl VALUES (null,?,?,?)";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigoCab);
            pst.setString(2, as_codigoMotivo);
            pst.setString(3, as_valor);
            
            pst.execute();
            mensaje = "Insertado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje = "no se pudo insetar";
        }
        return mensaje;
    }
    
      
    public static String mostrarNominaDetalle(String as_codigo)
    {
        String mensaje="";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM nominadtl WHERE codigoNmnDet='"+as_codigo+"'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){
                mensaje = res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4)+";";
            }
            cn.close();
        }catch (SQLException e)
        {
            mensaje = "no se encontro";
        }
        return mensaje;
    }
      
     public static String actualizarNominaDetalle(String as_codigo, String as_codigoCab, String as_codigoMotivo, String as_valor)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "UPDATE nominadtl SET codCabNmnDet = ?, motivoNmnDet = ?, valorNmnDet = ? WHERE codigoNmnDet = ?";
            Connection con = cone.conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, as_codigoCab);
            pst.setString(2, as_codigoMotivo);
            pst.setString(3, as_valor);
            pst.setString(4, as_codigo);
            pst.execute();
            mensaje = "actualizado con exito";
            pst.close();
        }catch (SQLException e)
        {
            mensaje="no se pudo actualizar";
        }
        return mensaje;
    }
    
    public static String eliminarNominaDetalle(String as_codigo)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "DELETE FROM nominadtl WHERE codigoNmnDet = ? ";
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
    
    public static String listarNominaDetalle(String as_idNomina)
    {
        String mensaje = "";
        String sql = "";
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM nominadtl WHERE codCabNmnDet= '"+as_idNomina+"'";
            Connection con = cone.conectar();
            Statement cn = con.createStatement();
            ResultSet res = cn.executeQuery(sql);
            while(res.next()){       
                mensaje += res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4)+"/";
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
        PersistenciaNomina cone = new PersistenciaNomina();
        try{
            sql = "SELECT * FROM detalle_nomina";
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
}
