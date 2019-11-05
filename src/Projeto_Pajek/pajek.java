package Projeto_Pajek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class pajek {
    grafo G;
    int size;

    public pajek(grafo G) {
        this.G = G;
    }
    

    public void Load(File f) throws IOException{
        FileReader reader = new FileReader(f.getAbsolutePath());
        BufferedReader br = new BufferedReader(reader);
        
        String line = br.readLine();
                    
        if(line.startsWith("*Vertices  ")){
            size = Integer.parseInt(line.substring(11));    //pega o tamanho do grafo
        }           
        
        G = new grafo(size);
        
        String[] a;
        int c = 0;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("*Edges") ) { //se comeca com edges, parar
                break;
            }
            if(line.isEmpty()){
                continue;         //se for linha em branco, ignora
            }
            line = line.replace("\"", "");System.out.println(line);
            a = line.split(" ",2);           //divide a linha entre o numero do nó e o nome dele
            G.seta_inform(c, a[1]);
            c++;
            /*
            System.out.println("nó n:"+a[0]);
            System.out.println("nome:"+a[1]);
            System.out.println("");
            */
                        
        }
        
        while ((line = br.readLine()) != null) {
            if(line.isEmpty()){
                continue;         //se for linha em branco, ignora
            }
            
            a = line.split(" ");
            G.criar_adj(Integer.parseInt(a[0])-1, Integer.parseInt(a[1])-1, Integer.parseInt(a[2]));
            /*
            System.out.println("inicio:"+a[0]);
            System.out.println("fim:"+a[1]);    
            System.out.println("peso:"+a[2]);
            System.out.println("");
            */
        }
            
                
        G.imprimi_adj();
    }
    
    public void Write(File f) throws IOException{
        FileWriter writer = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(writer);
        
        bw.write("*Vertices  "+G.size+"\n\n");
        
        for (int i = 0; i < G.size; i++) {
            bw.write(i+1+" \""+G.vertices[i].nome+"\"\n");
        }
        bw.write("\n\n*Edges\n\n");
        
        for (int i = 0; i < G.size; i++) {
            ArrayList<ListaSE.No> adj = new ArrayList();
            int c = G.adjacentes2(i, adj);
            for (int j = 0; j < c; j++) {
                bw.write(i+1+" "+ (adj.get(j).fim+1) + " " + adj.get(j).peso + "\n");
            }
        }
                
        bw.close();
        
        
        
    }
}
