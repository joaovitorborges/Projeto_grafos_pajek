package Projeto_Pajek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Leitor {
    grafo G;
    int size;

    public Leitor(grafo g, int size) {
        G = g;
        this.size = size;
    }

    public void Load(File f) throws IOException {
        FileReader reader = new FileReader(f.getAbsolutePath());
        BufferedReader br = new BufferedReader(reader);

        String line;
        int maior = -999999;
        int adjacencias = 0;
        G = new grafo(size);
        String[] a;

        while ((line = br.readLine()) != null) {
            if(line.isEmpty()){
                continue;         //se for linha em branco, ignora
            }

            a = line.split(",");
            for (int i = 0;i<a.length-1;i++){
                if (Integer.parseInt(a[i])>maior){
                    maior = Integer.parseInt(a[i]);
                }
            }
            G.criar_adj(Integer.parseInt(a[0])-1, Integer.parseInt(a[1])-1, Integer.parseInt(a[2]));
            adjacencias++;
        }

        G.imprime_adj();
        System.out.println("Maior nó:"+maior);
        System.out.println("adjacencias:"+adjacencias);
    }

}
