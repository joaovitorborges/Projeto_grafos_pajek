package Projeto_Pajek;

import Projeto_Pajek.ListaSE.No;
import java.util.ArrayList;
import java.util.Arrays;

class vertice {

    public String nome;
}

//-------------------------------------------
public class grafo {

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
                    if (ca==0) {
                        System.out.println("---: "+a.inicio);
                        System.out.println("---: "+a.fim);
                        System.out.println("---: "+a.peso);
                        ca=1;
                       
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
                
                           
                
                if(!Alinha.contains(adj.get(i))){
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
            
            for (int j = 0; j < Alinha.size(); j++){
                if(Alinha.get(j).inicio == b && Alinha.get(j).fim == a){
                    Alinha.remove(Alinha.get(j));
                }
            }       

        }
    }
    
    
}

