package Projeto_Pajek;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class main {

    public static void main(String[] args) throws IOException {
        /* ================== ler e salvar pajek ================

        grafo G = null;
        pajek P = new pajek(G);
        
        File ler = new File("arquivos\\pajek2.txt");
        File ler_base = new File("arquivos/comentarios pajek.txt");
        //File escrever = new File("arquivos/teste1.txt");
        
        P.Load(ler);
        System.out.println("nome:"+P.G.vertices[0].nome);
        System.out.println("pontos:"+P.G.vertices[0].pontos);

        //P.G.Centralidade_Intermediacao();
        //P.Write(escrever,"direcionado");

*/


         /*================== gerador de grafo aleatório =============
        int vertices = 7000;
        int arestas = 25000;
        
        grafo G = new grafo(vertices); 
        
        Gerador gerador = new Gerador(G);
        gerador.criar(vertices, arestas, "conexo");
        
        //G.imprime_adj();

        */

         
         /*================== busca de componentes com largura ================

        //LinkedList<Integer> R = P.G.BuscaComponente2(9);
        //System.out.println(R);

        //System.out.println(G.ContaComponentes());

        //System.out.println(P.G.ciclico());
        //System.out.println(G.euleriano(G.conectividade()));

        */


         /* ================leitor da base ====================  */

        grafo G = null;
        File ler = new File("arquivos\\comentarios programming.csv");

        Leitor L = new Leitor(G,11506);
        L.Load(ler);

        pajek P = new pajek(L.G);

        File escrever = new File("arquivos/comentarios pajek.txt");
        P.Write(escrever,"direcionado");

    }
}
