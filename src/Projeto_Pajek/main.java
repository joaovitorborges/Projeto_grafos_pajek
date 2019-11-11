package Projeto_Pajek;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class main {

    public static void main(String[] args) throws IOException {
        /* ================== ler e salvar pajek ================

        grafo G = null;
        pajek P = new pajek(G);
        
        File ler = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Projeto_Pajek\\arquivos\\pajek1.txt");
        File escrever = new File("arquivos/teste1.txt");
        
        P.Load(ler);
        P.Write(escrever,"direcionado");



         /*================== gerador de grafo aleatório ============= */
        int vertices = 10000;
        int arestas = 50000;
        
        grafo G = new grafo(vertices); 
        
        Gerador gerador = new Gerador(G);
        gerador.criar(vertices, arestas);
        
        G.imprime_adj();


         
         /*================== busca de componentes com largura ================*/

                
        //LinkedList R = P.G.BuscaComponente(9,new LinkedList());
        //System.out.println(R);
        //LinkedList<Integer> R2 = P.G.BuscaComponente2(9);
       // System.out.println(R2);


        System.out.println(G.ContaComponentes());
        //System.out.println(P.G.ciclico());
        //System.out.println(G.euleriano(G.conectividade()));
        //
    }
}
