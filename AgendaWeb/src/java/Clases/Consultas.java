
package Clases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Consultas extends DBconnection{
    
    
    public void Guardar(Contacto contacto) throws SQLException, ClassNotFoundException{
        Conectar();
        String sql="insert into agenda(nombre, apellido ,telefono , direccion,correo,status, fecha)"+"values(?, ?, ?, ?, ?, ?, ?)";
        estado= con.prepareStatement(sql);
        estado.setString(1, contacto.getNombre());
        estado.setString(2, contacto.getApellido());
        estado.setString(3, contacto.getTelefono());
        estado.setString(4, contacto.getDireccion());
         estado.setString(5, contacto.getCorreo());
         estado.setInt(6, 0);
        estado.setString(7,fecha );
        estado.execute();
        System.out.println("Guarde");
        Desconectar();
    }
    
    public void Editar(Contacto contacto) throws SQLException, ClassNotFoundException{
        Conectar();
        String sql = "update agenda set nombre = ?, apellido = ?,telefono= ?, direccion =?,correo=?,status=?, fecha=? "+"where id = ?";
        estado= con.prepareStatement(sql);
        estado.setString(1, contacto.getNombre());
        estado.setString(2, contacto.getApellido());
        estado.setString(3, contacto.getTelefono());
        estado.setString(4, contacto.getDireccion());
         estado.setString(5, contacto.getCorreo());
         estado.setInt(6, 0);
        estado.setString(7,fecha );
        estado.setInt(8, contacto.getId());
        estado.execute();
        System.out.println("Edite");
        Desconectar();
    }
    
    
      public void eliminar(int id) throws SQLException, ClassNotFoundException{
        Conectar();
        String sql = "update agenda set status=1 "+"where id = '"+id+"'";
        estado= con.prepareStatement(sql);
        estado.execute();
        System.out.println("elimine");
        Desconectar();
    }
    
    public List<Contacto> Buscar() throws SQLException, ClassNotFoundException{
        Conectar();
        ArrayList<Contacto> contacto= new ArrayList<>(); 
        String sql="select * from agenda  where status = 0 order by nombre";
        estado= con.prepareStatement(sql);
        rs= estado.executeQuery();
        while(rs.next()){
        Contacto c= new Contacto(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),rs.getString("telefono"), rs.getString("direccion"),rs.getString("correo"),rs.getInt("status"),rs.getString("fecha"));
       contacto.add(c);
        }
       Desconectar();
       
       return contacto;
        
    }
    
    
   
    
}