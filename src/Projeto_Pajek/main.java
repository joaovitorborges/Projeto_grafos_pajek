package Projeto_Pajek;

import java.io.File;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {

        
        /*
        G.seta_inform(0, "0");
        G.seta_inform(1, "1");
        G.seta_inform(2, "2");
        G.seta_inform(2, "3");
        G.seta_inform(4, "4");
        G.seta_inform(5, "5");
        G.seta_inform(6, "6");
        G.seta_inform(7, "7");
        G.seta_inform(8, "8");

        G.criar_adj(0, 1, 2);
        G.criar_adj(1, 0, 2);
        G.criar_adj(0, 2, 1);
         G.criar_adj(2, 0, 1);
        
        G.criar_adj(1, 3, 3);
        G.criar_adj(1, 4, 2);
       
        G.criar_adj(2, 3, 1);
        G.criar_adj(2, 5, 2);
        G.criar_adj(3, 1, 3);
        G.criar_adj(3, 2, 1);
        G.criar_adj(3, 4, 4);
        G.criar_adj(3, 5, 3);
        G.criar_adj(4, 1, 2);
        G.criar_adj(4, 3, 4);
        G.criar_adj(4, 6, 1);
        G.criar_adj(5, 2, 2);
        G.criar_adj(5, 3, 3);
        G.criar_adj(5, 6, 3);
        G.criar_adj(6, 4, 1);
        G.criar_adj(6, 5, 3);
        G.criar_adj(6, 7, 2);
        G.criar_adj(6, 8, 2);
        G.criar_adj(7, 6, 2);
        G.criar_adj(7, 8, 1);
        G.criar_adj(8, 6, 2);
        G.criar_adj(8,7, 1);
        
        
        G.prim2();
*/
        
        grafo G = null;
        
        pajek P = new pajek(G);
        
        File ler = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Projeto_Pajek\\arquivos\\pajek1.txt");
        File escrever = new File("arquivos/teste1.txt");
        
        P.Load(ler);
        P.Write(escrever);
        
        
        
    }
}
