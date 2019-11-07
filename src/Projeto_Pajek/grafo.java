package Projeto_Pajek;

import java.util.ArrayList;
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

    public int adjacentesNaoDir(int S, ArrayList<Integer> adj) {
        int c = 0;
        ListaSE.No p = m.linhas[S].primeiro;
        for (int i = 0; i < m.size; i++) {  // adiciona todos os adjacentes de S

            if (p != null) {
                adj.add(p.fim);
                p = p.proximo;
                c++;
            }
        }

        //adiciona todos que se conectam a S
        for (int i = 0; i < size; i++) {  // pra cada nó,
            p = m.linhas[i].primeiro;     // pega sua linha de adjacências
            for (int j = 0; j < size; j++) {  //para cada adjacência
                if (p == null) {
                    break;
                } else if (p.fim == S) {  // se esse nó se liga a S
                    adj.add(i);
                    c++;
                    break; 
                } else {            // se não
                    p = p.proximo;
                }
            }
        }

        return c;
    }

    public ArrayList<Integer> BuscaComponente(int S, ArrayList<Integer> visitados) {
        visitados.add(S);    //adiciona atual ao visitados
        ArrayList<Integer> R = new ArrayList();
        int x = adjacentesNaoDir(S, R);   // pega os adjacentes do atual

        if (x == 0 && !R.contains(S)) {      //caso o vertice esteja flutuando
            R.add(S);
            return R;
        }

        for (int i = 0; i < x; i++) { // para cada adjacente
            ArrayList<Integer> B = new ArrayList();

            if (!visitados.contains(R.get(i))) {    // se visitados não contém este adjacente
                B = BuscaComponente(R.get(i), visitados);   // faz recursão
            }

            for (int vértice : B) {        // para cada nó do resultado, add em R
                if (!R.contains(vértice)) {
                    R.add(vértice);
                }
            }

        }
        return R;
    }

    public int ContaComponentes() {
        int C = 0;
        ArrayList<Integer> visitados = new ArrayList(); // array com todas os vértices
        for (int i = 0; i < size; i++) {
            visitados.add(i);
        }

        while (!visitados.isEmpty()) { //até que esteja vazio
            // busca um componente
            ArrayList<Integer> Componente = BuscaComponente(visitados.get(0), new ArrayList());
            C++;
            visitados.removeAll(Componente); // remove o componente dos vértices sobrando
        }
        return C;
    }
    
    public boolean SerConexo(){
        return ContaComponentes() == 1;
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
