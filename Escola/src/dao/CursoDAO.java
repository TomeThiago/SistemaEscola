package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            
            JOptionPane.showMessageDialog(null, "O curso foi salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o curso informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
