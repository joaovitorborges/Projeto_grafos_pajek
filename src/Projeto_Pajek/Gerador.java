package Projeto_Pajek;

import java.util.ArrayList;
import java.util.Random;

public class Gerador {

    grafo G;

    public Gerador(grafo G) {
        this.G = G;
    }

    public void criar(int vertices, int arestas) {

        Random gerador = new Random();

        int adj[] = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            G.seta_inform(i, Integer.toString(i));
            System.out.println(i);
        }

        int cont = 0;
        System.out.println("---");
        System.out.println("---");
        System.out.println("---");
        while (cont < arestas) {

            int vertice1 = gerador.nextInt(vertices); // pega um vertice com o valor aleatorio      
            int vertice2 = gerador.nextInt(vertices); // pega o outro vertice aleatorio para fazer uma conexao
            int peso = gerador.nextInt(vertices);

            if (vertice1 != vertice2) { // os vertices nao podem ser iguais
                int quant_adj = G.adjacentes(vertice1, adj);
                if (quant_adj == 0) { // se for 0, quer dizer que ele tem nenhum adjacente
                    G.criar_adj(vertice1, vertice2, peso);
                    cont++;
                } else {

                    boolean existe_ligacao = false; // variavel que ficará true se dentro de adjacentes existir uma licagão de vertice 1 com vertice 2
                    for (int i = 0; i < quant_adj; i++) {
                        if (adj[i] == vertice2) {//se algum adjacente do vertice1 for igual ao vetice2, existe ligacao
                            existe_ligacao = true;
                        }

                    }
                    if (existe_ligacao == true) { //se existir a ligacao, pula e gera novos vertices aleatorios
                        continue;
                    } else {
                        G.criar_adj(vertice1, vertice2, peso);
                        cont++;

                    }
                }

            }

        }

    }

}
