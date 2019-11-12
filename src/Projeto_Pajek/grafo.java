package Projeto_Pajek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

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

    public int adjacentesNaoDir(int S, LinkedList<Integer> adj) {
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

    public LinkedList<Integer> BuscaComponenteRecursivo(int S, LinkedList<Integer> visitados) {
        visitados.add(S);    //adiciona atual ao visitados
        LinkedList<Integer> R = new LinkedList();
        int x = adjacentesNaoDir(S, R);   // pega os adjacentes do atual

        if (x == 0 && !R.contains(S)) {      //caso o vertice esteja flutuando
            R.add(S);
            return R;
        }

        for (int i = 0; i < x; i++) { // para cada adjacente
            LinkedList<Integer> B = new LinkedList();

            if (!visitados.contains(R.get(i))) {    // se visitados não contém este adjacente
                B = BuscaComponenteRecursivo(R.get(i), visitados);   // faz recursão
            }

            for (int vértice : B) {        // para cada nó do resultado, add em R
                if (!R.contains(vértice)) {
                    R.add(vértice);
                }
            }

        }
        return R;
    }

    public LinkedList<Integer> BuscaComponente2(int S){
        LinkedList<Integer> visitados = new LinkedList();
        visitados.add(S);
        LinkedList<Integer> R = new LinkedList();
        int x = adjacentesNaoDir(S, R);   // pega os adjacentes do atual

        while(!R.isEmpty()){
            LinkedList<Integer> A = new LinkedList();
            if (!visitados.contains(R.get(0))) {
                adjacentesNaoDir(R.get(0), A);
                visitados.add(R.get(0));

                for ( int i = 0; i<A.size();i++){
                    if (!visitados.contains(A.get(i))) {
                        R.add(A.get(i));
                    }
                }
            }
            R.remove(R.get(0));
        }

        return visitados;

    }

    public ArrayList<Integer> ContaComponentes() {
        Random Rand = new Random();
        int C = 0;
        ArrayList<Integer> R = new ArrayList();
        LinkedList<Integer> visitados = new LinkedList(); // array com todas os vértices
        for (int i = 0; i < size; i++) {
            visitados.add(i);
        }

        while (!visitados.isEmpty()) { //até que esteja vazio
            // busca um componente
            LinkedList<Integer> Componente = BuscaComponente2(visitados.get(0));
            C++;
            System.out.println("Componente "+ C+ ":" + Componente);
            R.add(Componente.get(Rand.nextInt(Componente.size())));
            visitados.removeAll(Componente); // remove o componente dos vértices sobrando
        }
        return R;
    }
    
    public boolean SerConexo(){
        return ContaComponentes().size() == 1;
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
    
    public boolean ciclico(){
        ArrayList<Integer> vetor = new <Integer>ArrayList();
        vetor.add(0);
        return eciclico(0,vetor);
}

    public boolean eciclico(int index,ArrayList<Integer> vetor){
        int adj[]=new int[vertices.length];
        int cont = adjacentes(index,adj);
        for (int i = 0; i < cont; i++) {
            if (adj[i] == index) {
                continue;
            }
            if (vetor.contains(adj[i])) {
                return true;
            }
            else{
                vetor.add(adj[i]);
                if (eciclico(adj[i],vetor)) {
                    return true;
                }
            }
        }
        return false;
    }




}



    
