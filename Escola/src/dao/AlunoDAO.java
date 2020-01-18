package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
