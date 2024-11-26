package com.example;
import java.sql.*;

public class UsuarioDAO {
    private static final String URL = "jdbc:sqlite:usuarios.db";
    
    public UsuarioDAO() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS usuario ("
                                    + "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,"
                                    + "nome TEXT NOT NULL,"
                                    + "email TEXT NOT NULL,"
                                    + "dtinsercao TEXT NOT NULL,"
                                    + "endereco TEXT NOT NULL,"
                                    + "telefone TEXT NOT NULL,";
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCreateTable);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public void cadastrarUsuario(Usuario usuario){
        String sqlInsert = "INSERT INTO usuario(nome, email, endereco, telefone) VALUES(?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert)){
                pstmt.setString(1, usuario.getNome());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getTelefone());
                pstmt.setString(4, usuario.getEndereco());
                pstmt.executeUpdate(); 
                System.out.println("Usuario cadastrado com sucesso");
            }catch (SQLException e){
                System.out.println("Erro ao cadastrar usuario:" + e.getMessage());
            }
    }

    public void consultarUsuario(int id){
        String sqlSelect = "SELECT * FROM usuario WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect)){
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                    );
                }
        }catch (SQLException e){
            System.out.println("Erro ao consultar usuario: " + e.getMessage());
        }
        return null;
    }
}