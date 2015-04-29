/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dojointercalacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
     * Executa o algoritmo Intercalação Ótima
     * @param nomeParticoes array com os nomes dos arquivos que contêm as partições de entrada
     * @param nomeArquivoSaida nome do arquivo de saída resultante da execução do algoritmo
     */
public class Intercalacao {

    public void executa(List<String> nomeParticoes, String nomeArquivoSaida) throws Exception {

            //TODO: Inserir aqui o código do algoritmo de Intercalação Ótima
        int totalPartitions = nomeParticoes.size();
        
        while(nomeParticoes.size() > 1){
            
            List<Cliente> result = new ArrayList<Cliente>();
            
            for(int i = 0; i < 3; i++){
                if(nomeParticoes.isEmpty()) break;
                
                DataInputStream in = new DataInputStream(new FileInputStream(nomeParticoes.get(0)));
                try {
                    while (true) {
                        Cliente c = Cliente.le(in);
                        if(!result.contains(c) && !c.equals(Cliente.Vazio())){
                            result.add(c);
                        }
                    }
                } catch (EOFException e) {}
                in.close();
                nomeParticoes.remove(0);
            }
                        
            totalPartitions++;
            String nextArchiveName = "P" + totalPartitions;
            
            Collections.sort(result, new Comparator<Cliente>() {
                public int compare(Cliente o1, Cliente o2) {
                    return Integer.compare(o1.codCliente, o2.codCliente);
                }
            });
            DataOutputStream out = new DataOutputStream(new FileOutputStream(nextArchiveName));
            for (Cliente entidade : result) {
                entidade.salva(out);
            }
            Cliente.Vazio().salva(out);
            out.close();
            
            nomeParticoes.add(nextArchiveName);
        }
        
        File f = new File(nomeParticoes.get(0));
        f.renameTo(new File(nomeArquivoSaida));
        
    }

}
