package Projeto_Pajek;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class main {

    public static void main(String[] args) throws IOException {
        /* ================== ler e salvar pajek ================     */

        grafo G = null;
        pajek P = new pajek(G);
        
        File ler = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Projeto_Pajek\\arquivos\\pajek2.txt");
        //File escrever = new File("arquivos/teste1.txt");
        
        P.Load(ler);

        P.G.Centralidade_Intermediacao();
        //P.Write(escrever,"direcionado");




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


         /* ================leitor da base ====================

        grafo G = null;
        // link para download da base : https://snap.stanford.edu/data/soc-sign-bitcoin-otc.html
        File ler = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Projeto_Pajek\\arquivos\\soc-sign-bitcoinotc.txt");

        Leitor L = new Leitor(G,6006);
        L.Load(ler);

        System.out.println("");

        L.G.porcentagem();
*/
    }
}
