package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Curso;

public class CursoDAO {
    
    public void create(Curso curso) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("INSERT INTO geral.curso (descricao,ementa) VALUES (?,?)");
            stmt.setString(1, curso.getDescricao());
            stmt.setString(2, curso.getEmenta());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o curso informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void update(Curso curso) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("UPDATE geral.curso SET descricao=?, ementa=? WHERE codigo = ?");
            stmt.setString(1, curso.getDescricao());
            stmt.setString(2, curso.getEmenta());
            stmt.setInt(3, curso.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar o aluno informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void delete(Curso curso) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("DELETE FROM geral.curso WHERE codigo = ?");
            stmt.setInt(1, curso.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o curso informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Curso> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Curso> cursos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM geral.curso");
            rs = stmt.executeQuery();
            while(rs.next()){
                Curso curso = new Curso();
                curso.setCodigo(rs.getInt("codigo"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setEmenta(rs.getString("ementa"));
                cursos.add(curso);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar os cursos, tente novamente mais tarde!");
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        };
        
        return cursos;
    }
}
