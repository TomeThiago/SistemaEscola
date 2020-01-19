package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexao {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String PORTA = "5432";
    private static final String BD = "escola";
    private static final String URL = "jdbc:postgresql://localhost:"+PORTA+"/"+BD;
    private static final String USER = "postgres";
    private static final String PASS = "bakuman10";
    
    public static void createDatabase(){
        //SELECT * FROM pg_database WHERE datname = 'maniasmulher'"
        
       /* try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","bakuman10");
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("CREATE DATABASE escola");
            stmt.executeUpdate();
   
        } catch (SQLException err){
            
        }*/
        
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","bakuman10");
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("CREATE DATABASE escola");
            try {
                stmt.executeUpdate();
            } catch (Exception e) {
                
            } finally {
                con.close();
                stmt.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
           throw new RuntimeException("Erro na conexão com o gerenciador: ", ex);
        }
        
        createSchemas();
        createTableCurso();
        createTableAluno();
        createTableCursoAluno();
    }
    
    public static void createSchemas(){
        Connection con = getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("CREATE SCHEMA IF NOT EXISTS geral;");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar as schemas!");
        } finally {
           closeConnection(con, stmt);
        }
    }
    
    public static void createTableCurso(){
        Connection con = getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS geral.curso(\n" +
                                        "  codigo SERIAL NOT NULL,\n" +
                                        "  descricao VARCHAR(50) NOT NULL,\n" +
                                        "  ementa TEXT,\n" +
                                        "  PRIMARY KEY(codigo)\n" +
                                        ");");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar a tabela de cursos!");
        } finally {
           closeConnection(con, stmt);
        }
    }
    
    public static void createTableAluno(){
        Connection con = getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS geral.aluno(\n" +
                                        "  codigo SERIAL NOT NULL,\n" +
                                        "  nome VARCHAR(50),\n" +
                                        "  PRIMARY KEY(codigo)\n" +
                                        ");");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar a tabela de alunos!");
        } finally {
           closeConnection(con, stmt);
        }
    }
    
    public static void createTableCursoAluno(){
        Connection con = getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS geral.curso_aluno(\n" +
                                        "  codigo SERIAL NOT NULL,\n" +
                                        "  codigo_aluno INTEGER NOT NULL,\n" +
                                        "  codigo_curso INTEGER NOT NULL,\n" +
                                        "  FOREIGN KEY (codigo_aluno) REFERENCES geral.aluno(codigo),\n" +
                                        "  FOREIGN KEY (codigo_curso) REFERENCES geral.curso(codigo),\n" +
                                        "  PRIMARY KEY (codigo)\n" +
                                        ");");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar a tabela de cursos!");
        } finally {
           closeConnection(con, stmt);
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão com o banco de dados: ", ex);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) { 
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet res) { 
        closeConnection(con, stmt);
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
