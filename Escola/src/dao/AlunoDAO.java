package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Aluno;

public class AlunoDAO {

    public void create(Aluno aluno) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("INSERT INTO geral.aluno (nome) VALUES (?)");
            stmt.setString(1, aluno.getNome());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "O aluno foi salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o aluno informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void update(Aluno aluno) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
       
        try {
                stmt = con.prepareStatement("UPDATE geral.aluno set nome = ? WHERE codigo = ?");
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar o aluno informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void delete(Aluno aluno) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("DELETE FROM geral.aluno WHERE codigo = ?");
            stmt.setInt(1, aluno.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o aluno informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Aluno> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM geral.aluno");
            rs = stmt.executeQuery();
            while(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setCodigo(rs.getInt("codigo"));
                aluno.setNome(rs.getString("nome"));
                alunos.add(aluno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar os alunos, tente novamente mais tarde!");
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        };
        
        return alunos;

    }
}
