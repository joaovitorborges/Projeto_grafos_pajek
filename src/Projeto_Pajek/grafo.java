package Projeto_Pajek;

import Projeto_Pajek.ListaSE.No;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class vertice {

    public String nome;
}

//-------------------------------------------
public class grafo {

    ArrayList<Integer> NosComponenete;
    int nVertices;
    MEsparsa m;
    vertice vertices[];
    int size;
    boolean[][] warshall;
    private static final boolean MEMBRO = true;
    private static final boolean NAOMEMBRO = false;
    private static final int INFINITO = 999999999;
    int caminho[];

    public grafo(int size) {
        this.size = size;
        m = new MEsparsa(size);
        vertices = new vertice[size];
        warshall = new boolean[size][size];
        caminho = new int[size];

        for (int i = 0; i < size; i++) {
            vertices[i] = new vertice();
            caminho[i] = -1;
            for (int j = 0; j < size; j++) {
                warshall[i][j] = false;
            }
        }
    }

    public void criar_adj(int i, int j, int tam) {
        m.insere(i, j, tam);
    }

    public void remove_adj(int i, int j) {
        ListaSE.No p = this.m.linhas[i].primeiro;        // pega primeiro

        if (p.fim == j) {                 // se for o primeiro q nois quer tirar
            ListaSE.No p2 = p.proximo;
            p = null;
            m.linhas[i].primeiro = p2;
        } else {                             // se nao roda ate o proximo ser oq a gente quer tirar
            while (p.proximo.fim != j) {
                p = p.proximo;
            }
            if (p.proximo != null) {
                ListaSE.No p2 = p.proximo.proximo;  // tira
                p.proximo = p2;
            }
        }
    }

    public void imprime_adj() {
        for (int i = 0; i < m.linhas.length; i++) {
            System.out.print(i + "      ");
            m.linhas[i].imprimir();
            System.out.println("");
        }
    }

    public void seta_inform(int i, String n) {
        this.vertices[i].nome = n;
    }

    public int adjacentes(int a, int[] adj) {
        int c = 0;
        ListaSE.No p = m.linhas[a].primeiro;
        for (int i = 0; i < m.size; i++) {
            if (p != null) {
                adj[c] = p.fim;
                p = p.proximo;
                c++;
            } else {
                adj[c] = -1;
            }
        }
        return c;
    }

    public int adjacentes2(int a, ArrayList<ListaSE.No> adj) {
        int c = 0;
        ListaSE.No p = m.linhas[a].primeiro;
        for (int i = 0; i < m.size; i++) {
            if (p != null) {
                adj.add(p);
                p = p.proximo;
                c++;
            }
        }
        return c;
    }

    public Boolean Largura(int s, int d) {

        boolean visited[] = new boolean[vertices.length];   // todos os visitados começam como falso
        LinkedList<Integer> queue = new LinkedList<>(); // para armazenar os já visitados

        visited[s] = true;     // marca o nó atual como visitado e adiciona ele na queue
        queue.add(s);

        int adj[] = new int[vertices.length];      // usado para armazenar os adjacentes

        while (!queue.isEmpty()) { // repeté até que todos os nós sejam visitados ou se encontre o nó procurado
            s = queue.poll();  //pega o vertice s da queue

            int n;
            this.adjacentes(s, adj);  //pegas os adjacentes do vertice já visitado

            for (int j = 0; j < adj.length; j++) {
                if (adj[j] == -1) {  // ignora -1
                    break;
                }

                n = adj[j];

                if (n == d) {  // se este nó for o procurado retorna true 
                    visited[d] = true;
                    //System.out.print(" [ ");
                    for (int i = 0; i < visited.length; i++) {
                        if (visited[i]) {
                            //System.out.print(vertices[i].nome+ " ");
                            if (!NosComponenete.contains(Integer.parseInt(vertices[i].nome))) {
                                NosComponenete.add(Integer.parseInt(vertices[i].nome));
                            }
                        }
                    }
                    //System.out.println("]");
                    return true;
                }

                if (!visited[n]) {  // caso contrario, marca o nó como visitado e o adiciona na queue
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        return false;
    }

    public boolean euleriano(int quantidade_componentes) {
        if (quantidade_componentes != 1) {
            return false;
        } else {
            ArrayList<ListaSE.No> adj = new ArrayList();
            for (int i = 0; i < vertices.length; i++) {
                int cont = adjacentes2(i, adj);
                if (cont % 2 == 1) {
                    return false;
                }

            }
        }
        return true;
    }

}
