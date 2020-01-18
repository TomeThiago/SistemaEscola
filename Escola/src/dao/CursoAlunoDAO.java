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
    
    public void create(CursoAluno matricula) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO geral.curso_aluno (codigo_aluno,codigo_curso) VALUES (?,?)");
            stmt.setInt(1, matricula.getCodigo_aluno());
            stmt.setInt(2, matricula.getCodigo_curso());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "O aluno foi matrículado com sucesso no curso informado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao matrícular o aluno no curso informado, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void update(CursoAluno matricula) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("UPDATE geral.curso_aluno SET codigo_aluno=?, codigo_curso=? WHERE codigo = ?");
            stmt.setInt(1, matricula.getCodigo_aluno());
            stmt.setInt(2, matricula.getCodigo_curso());
            stmt.setInt(3, matricula.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar a matrícula informada, tente novamente mais tarde!");
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void delete(CursoAluno matricula) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("DELETE FROM geral.curso_aluno WHERE codigo = ?");
            stmt.setInt(1, matricula.getCodigo());
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
            stmt = con.prepareStatement("SELECT geral.curso_aluno.codigo, geral.curso_aluno.codigo_aluno, "
                    + "geral.aluno.nome, geral.curso_aluno.codigo_curso, geral.curso.descricao FROM geral.curso_aluno "
                    + "INNER JOIN geral.aluno ON (geral.curso_aluno.codigo_aluno = geral.aluno.codigo) "
                    + "INNER JOIN geral.curso ON (geral.curso_aluno.codigo_curso = geral.curso.codigo)");
            rs = stmt.executeQuery();
            while(rs.next()){
                CursoAluno matricula = new CursoAluno();
                matricula.setCodigo(rs.getInt("codigo"));
                matricula.setCodigo_aluno(rs.getInt("codigo_aluno"));
                matricula.setNome_aluno(rs.getString("nome"));
                matricula.setCodigo_curso(rs.getInt("codigo_curso"));
                matricula.setDescricao_curso(rs.getString("descricao"));
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
