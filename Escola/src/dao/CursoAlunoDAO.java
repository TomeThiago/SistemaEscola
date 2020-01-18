package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Curso;
import model.Aluno;

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
    
}
