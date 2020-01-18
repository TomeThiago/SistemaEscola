package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Curso;
import model.Aluno;
import model.CursoAluno;

public class CursoAlunoDAO {
    
    public void create(Curso curso, Aluno aluno) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO geral.curso_aluno (codigo_aluno,codigo_curso) VALUES (?,?)");
            stmt.setInt(1, aluno.getCodigo());
            stmt.setInt(2, curso.getCodigo());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "O aluno foi registrado com sucesso no curso informado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao registrar o aluno no curso informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void update(Curso curso, Aluno aluno) {
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
    
    public void delete(Curso curso, Aluno aluno) {
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

    public List<CursoAluno> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<CursoAluno> matriculas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM geral.curso_aluno");
            rs = stmt.executeQuery();
            while(rs.next()){
                CursoAluno matricula = new CursoAluno();
                //matricula.setCodigo(rs.getInt("codigo"));
                matriculas.add(matricula);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar as matriculas, tente novamente mais tarde!");
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        };
        
        return matriculas;
    }
    
}
