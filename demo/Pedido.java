package com.example;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Usuario usuario;
    private List<Produto> produtos;

    public Pedido(int id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
        this.produtos = new ArrayList<>();
    }

    public Pedido(Usuario usuario) {
        this.usuario = usuario;
        this.produtos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public String toString() {
        return "Pedido [ID=" + id + ", Usuario=" + usuario + ", Produtos=" + produtos + "]";
    }
}
