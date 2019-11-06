package Projeto_Pajek;

import java.io.File;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {

        
        /* ================== ler e salvar pajek ================*/
              
        grafo G = null;
        
        pajek P = new pajek(G);
        
        File ler = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Projeto_Pajek\\arquivos\\pajek1.txt");
        File escrever = new File("arquivos/teste1.txt");
        
        P.Load(ler);
        P.Write(escrever,"direcionado");
        
        
        
         /*================== gerador de grafo aleatório =============
        int vertices = 100;
        int arestas = 400;
        
        grafo G = new grafo(vertices); 
        
        Gerador gerador = new Gerador(G);
        gerador.criar(vertices, arestas);
        
        G.imprimi_adj();
             
*/
        //System.out.println(G.euleriano(G.conectividade()));
    }
}
