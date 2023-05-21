package com.wikinado;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.Statement;  

public class TestConexion {
   public static void main(String[] args) { 
      Connection con = null; 
      Statement stmt = null; 
      int result = 0; 
      try { 
         Class.forName("org.hsqldb.jdbc.JDBCDriver"); 
         con = DriverManager.getConnection( "jdbc:hsqldb:hsql://localhost:9001/wik", "SA", ""); 
         stmt = con.createStatement(); 
         result = stmt.executeUpdate("CREATE TABLE articulo (id_articulo INT NOT NULL PRIMARY KEY)");
         con.commit(); 
      }catch (Exception e) { 
         e.printStackTrace(System.out); 
      } 
      System.out.println(result+" rows effected"); 
      System.out.println("Rows inserted successfully"); 
   } 
} 