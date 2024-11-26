package com.example;

import java.util.Scanner;

public class App {
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            exibirMenuPrincipal();
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> menuProdutos(scanner);
                case 2 -> menuUsuarios(scanner);
                case 3 -> menuPedidos(scanner);
                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("--- Menu Principal ---");
        System.out.println("1. Menu de Produtos");
        System.out.println("2. Menu de Usuários");
        System.out.println("3. Menu de Pedidos");
        System.out.println("4. Sair");
    }

    private static void menuProdutos(Scanner scanner) {
        boolean executando = true;
        while (executando) {
            System.out.println("--- Menu de Produtos ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Consultar Produto");
            System.out.println("3. Alterar Preço");
            System.out.println("4. Inativar Produto");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> cadastrarProduto(scanner);
                case 2 -> consultarProduto(scanner);
                case 3 -> alterarPreco(scanner);
                case 4 -> inativarProduto(scanner);
                case 5 -> {
                    System.out.println("Voltando ao Menu Principal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void menuUsuarios(Scanner scanner) {
        boolean executando = true;
        while (executando) {
            System.out.println("--- Menu de Usuários ---");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Consultar Usuário");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> cadastrarUsuario(scanner);
                case 2 -> consultarUsuario(scanner);
                case 3 -> {
                    System.out.println("Voltando ao Menu Principal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void menuPedidos(Scanner scanner) {
        boolean executando = true;
        while (executando) {
            System.out.println("--- Menu de Pedidos ---");
            System.out.println("1. Gerar Pedido");
            System.out.println("2. Consultar Pedido");
            System.out.println("3. Inativar Pedido");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> gerarPedido(scanner);
                case 2 -> consultarPedido(scanner);
                case 3 -> inativarPedido(scanner);
                case 4 -> {
                    System.out.println("Voltando ao Menu Principal...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void cadastrarProduto(Scanner scanner) {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtoDAO.cadastrarProduto(produto);
    }

    private static void consultarProduto(Scanner scanner) {
        System.out.print("Digite o ID do produto que deseja consultar: ");
        int id = scanner.nextInt();

        Produto produto = produtoDAO.consultarProduto(id);
        if (produto != null) {
            System.out.println("Produto encontrado: " + produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void alterarPreco(Scanner scanner) {
        System.out.print("Digite o ID do produto que deseja alterar o preço: ");
        int id = scanner.nextInt();

        System.out.print("Digite o novo preço do produto: ");
        double novoPreco = scanner.nextDouble();

        produtoDAO.alterarPreco(id, novoPreco);
    }

    private static void inativarProduto(Scanner scanner) {
        System.out.print("Digite o ID do produto que deseja inativar: ");
        int id = scanner.nextInt();

        produtoDAO.inativarProduto(id);
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        System.out.print("Digite o telefone do usuário");
        String telefone = scanner.nextLine();

        System.out.print("Digite o endereço do usuário");
        String endereco = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email, telefone, endereco, "N/A", "N/A", "N/A", "N/A"); 
        usuarioDAO.cadastrarUsuario(usuario);
    }

    private static void consultarUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuário que deseja consultar: ");
        int id = scanner.nextInt();

        Usuario usuario = usuarioDAO.consultarUsuario(id);
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void gerarPedido(Scanner scanner) {
        System.out.print("Digite o ID do usuário para o pedido: ");
        int usuarioId = scanner.nextInt();
        System.out.println("Pedido gerado com sucesso!");
    }

    private static void consultarPedido(Scanner scanner) {
        System.out.println("Consultar pedido em desenvolvimento...");
    }

    private static void inativarPedido(Scanner scanner) {
        System.out.println("Inativar pedido em desenvolvimento...");
    }
}
