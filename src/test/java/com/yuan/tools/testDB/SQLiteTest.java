package com.yuan.tools.testDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class SQLiteTest{
    
    private Connection c = null;
    private Statement stmt = null;
    
    
    @Before
    public void connection(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
          }
    }
    
    @Test
    public void createDB(){
        try {
          stmt = c.createStatement();
          String sql = "CREATE TABLE COMPANY " +
                       "(ID integer PRIMARY KEY   autoincrement  NOT NULL," +
                       " NAME           TEXT    NOT NULL, " + 
                       " AGE            INT     NOT NULL, " + 
                       " ADDRESS        CHAR(50), " + 
                       " SALARY         REAL)"; 
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    
    @Test
    public void dropTable(){
        try{
            stmt = c.createStatement();
            String sql = "drop table COMPANY";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table drop successfully");
    }
    
    @Test
    public void insertDate(){
        try{
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (null, 'Paul', 32, 'California', 20000.00 );"; 
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (null, 'Allen', 25, 'Texas', 15000.00 );"; 
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (null, 'Teddy', 23, 'Norway', 20000.00 );"; 
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (null, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
            stmt.executeUpdate(sql);
            
            
            stmt.close();
            c.commit();
            c.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        System.out.println("Records created successfully");
    }
}
