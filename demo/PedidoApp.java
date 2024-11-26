package com.example;

import java.util.Scanner;

public class PedidoApp {
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Gerar Pedido");
            System.out.println("3. Consultar Pedido");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    gerarPedido(scanner);
                    break;
                case 3:
                    consultarPedido();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o email do usuário:");
        String email = scanner.nextLine();

        System.out.println("Digite o telefone do usuário");
        String telefone = scanner.nextLine();

        System.out.println("Digite o endereço do usuário");
        String endereco = scanner.nextLine();

        Usuario usuario = new Usuario(nome);
        usuarioDAO.cadastrarUsuario(usuario);
    }

    private static void gerarPedido(Scanner scanner) {
        System.out.print("Digite o ID do usuário para o pedido: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); 
        Usuario usuario = usuarioDAO.consultarUsuario(userId);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Pedido pedido = new Pedido(usuario);

        String continuar;
        do {
            System.out.print("Digite o ID do produto: ");
            int productId = scanner.nextInt();
            scanner.nextLine(); 
            Produto produto = produtoDAO.consultarProduto(productId);

            if (produto == null) {
                System.out.println("Produto não encontrado.");
            } else {
                System.out.print("Digite a quantidade do produto: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine(); 

                for (int i = 0; i < quantidade; i++) {
                    pedido.adicionarProduto(produto);
                }
            }

            System.out.print("Deseja adicionar mais produtos? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("Pedido gerado com sucesso!");
        System.out.println(pedido);
    }

    private static void consultarPedido() {
        System.out.println("Funcionalidade de consulta de pedidos ainda não implementada.");
    }
}
