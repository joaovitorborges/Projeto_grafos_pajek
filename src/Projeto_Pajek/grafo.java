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

    public void imprimi_adj() {
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

    public ArrayList<ListaSE.No> prim2() {

        ArrayList<Integer> N = new ArrayList();
        ArrayList<ListaSE.No> A = new ArrayList();
        N.add(0);
        int ca = 0;
        while (N.size() < vertices.length) {
            ArrayList<ListaSE.No> Alinha = new ArrayList();
            for (int i = 0; i < N.size(); i++) {

                ArrayList<ListaSE.No> adj = new ArrayList();
                int cont = this.adjacentes2(N.get(i), adj); // adj ta com os vertices adjacentes a variavel aresta

                for (int j = 0; j < adj.size(); j++) {

                    if (!A.contains(adj.get(j))) {
                        Alinha.add(adj.get(j));
                    }

                }
            }

            this.removeRepeticao(Alinha);

            ListaSE.No a = null;
            int c = 99999;
            for (int i = 0; i < Alinha.size(); i++) {

                if (Alinha.get(i).peso < c) {
                    a = Alinha.get(i);
                    c = Alinha.get(i).peso;

                }

                if (!A.contains(a)) {
                    A.add(a);
                    if (ca == 0) {
                        System.out.println("---: " + a.inicio);
                        System.out.println("---: " + a.fim);
                        System.out.println("---: " + a.peso);
                        ca = 1;

                    }

                }
                if (!N.contains(a.fim)) {
                    N.add(a.fim);
                }

            }

        }
        this.removeRepeticao(A);

        System.out.println(A.size());
        for (int i = 0; i < A.size(); i++) {
            System.out.println("Menor: " + A.get(i).inicio + " Maior: " + A.get(i).fim + " Peso:" + A.get(i).peso);

        }
        System.out.println(N);

        return A;

    }

    //----------------------------------------
    public void prim() {

        ArrayList<Integer> N = new ArrayList();
        ArrayList<ListaSE.No> A = new ArrayList();
        ArrayList<ListaSE.No> Alinha = new ArrayList();
        ArrayList<ListaSE.No> Visitados = new ArrayList();
        //ArrayList<ListaSE.No> AlinhaFiltrado = new ArrayList();
        int aresta = 0;
        N.add(aresta);

        while (N.size() < this.size) {
            ArrayList<ListaSE.No> adj = new ArrayList();

            int cont = this.adjacentes2(aresta, adj);

            for (int i = 0; i < cont; i++) {

                if (!Alinha.contains(adj.get(i))) {
                    Alinha.add(adj.get(i));
                    //}
                }

            }

            this.removeRepeticao(Alinha);

            ListaSE.No menor = null;
            int c = 99999;
            for (int i = 1; i < Alinha.size(); i++) {
                if (Alinha.get(i).peso < c && !Visitados.contains(Alinha.get(i))) {
                    c = Alinha.get(i).peso;

                    menor = Alinha.get(i);
                    aresta = menor.fim;

                }
            }
            A.add(menor);
            Visitados.add(menor);

            N.add(aresta);

            //}
        }
        System.out.println(A.size());
        for (int i = 0; i < A.size(); i++) {
            System.out.println("Menor: " + A.get(i).inicio + " Maior: " + A.get(i).fim + " Peso:" + A.get(i).peso);

        }
        System.out.println(N);

    }

    public void removeRepeticao(ArrayList<ListaSE.No> Alinha) { // nao está mais sendo utilizado
        for (int i = 0; i < Alinha.size(); i++) {
            int a = Alinha.get(i).inicio;
            int b = Alinha.get(i).fim;

            for (int j = 0; j < Alinha.size(); j++) {
                if (Alinha.get(j).inicio == b && Alinha.get(j).fim == a) {
                    Alinha.remove(Alinha.get(j));
                }
            }

        }
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

    public int conectividade() {

        int controlainicio = 0;//ponteiro de inicio
        int controlafim = 1;//ponteiro de fim
        int quantidade_componentes = 1;// quantidade de componenetes
        ArrayList<Integer> falha = new ArrayList();
        ArrayList<Integer> visitados = new ArrayList();
        while (controlainicio <= vertices.length - 1) {

            if (controlafim > vertices.length - 1 && falha.size() > 0 && controlainicio <= vertices.length - 1) {

                if (visitados.contains(Integer.parseInt(vertices[controlainicio].nome))) {
                    controlainicio++;
                } else {

                    if (!NosComponenete.isEmpty()) {
                        quantidade_componentes++;
                        //System.out.println(NosComponenete);
                    }
                    for (int i = 0; i < this.size; i++) {

                        if (!visitados.contains(falha.get(i))) {
                            controlainicio = falha.get(i);
                            // System.out.println("controlainicio"+controlainicio);
                            controlafim = 0;
                            //System.out.println("controlafim"+controlafim);
                            falha.clear();
                            //System.out.println(NosComponenete); 
                            NosComponenete.clear();
                            break;
                        }

                    }
                }

            }
            if (controlainicio == vertices.length - 1) {
                //System.out.println(NosComponenete);
                NosComponenete.clear();
                System.out.println("quantidade de componentes: " + quantidade_componentes);

            }

            boolean aux = false;
            if (controlafim <= vertices.length - 1) {
                aux = this.Largura(Integer.parseInt(vertices[controlainicio].nome), Integer.parseInt(vertices[controlafim].nome));
            }
            if (aux == true) {
                if (!visitados.contains(Integer.parseInt(vertices[controlainicio].nome))) {
                    visitados.add(Integer.parseInt(vertices[controlainicio].nome));
                }
                if (!visitados.contains(Integer.parseInt(vertices[controlafim].nome))) {
                    visitados.add(Integer.parseInt(vertices[controlafim].nome));
                }
                controlafim++;

            }
            if (aux == false) {

                falha.add(controlafim);
                controlafim++;

            }

        }
        return quantidade_componentes;

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
