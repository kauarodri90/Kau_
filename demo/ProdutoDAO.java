package com.example;
import java.sql.*;

public class ProdutoDAO {
    private static final String URL = "jdbc:sqlite:produtos.db";
   
    public ProdutoDAO() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS produto ("
                                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                                    + "nome TEXT NOT NULL,"
                                    + "preco REAL NOT NULL)"
                                    + "status INTEREGER DEFAULT 1)";
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCreateTable);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public void cadastrarProduto(Produto produto) {
        String sqlInsert = "INSERT INTO produto(nome, preco, status) VALUES(?, ?, 1)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public Produto consultarProduto(int id) {
        String sqlSelect = "SELECT * FROM produto WHERE id = ? AND status = 1"; 
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produto: " + e.getMessage());
        }
        return null;
    }
    
    public void alterarPrecoProduto(int id, double novoPreco){
        String sqlUpdate = "UPTADE produto SET preco = ? WHERE id = ? AND status = 1";

        try (Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)){
                pstmt.setDouble(1, novoPreco);
                pstmt.setInt(2, id);
                int rowsAffected = pstmt.executeUpdate();
                if(rowsAffected > 0){
                    System.out.println("Preço do produto alterado com sucesso");
                }else{
                    System.out.println("Produto não encontrado ou esta inativo");
                }
            }catch (SQLException e){
                System.out.println("Erro ao alterar preço do produto:" + e.getMessage());
            }
    }

    public void desativarProduto(int id) {
        String sqlUpdate = "UPDATE produto SET status = 0 WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto desativado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desativar produto: " + e.getMessage());
        }
    }
}
