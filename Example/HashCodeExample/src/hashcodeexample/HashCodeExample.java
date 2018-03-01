/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcodeexample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Angelo
 */
public class HashCodeExample {
    
    private static String FILENAME="big.in";
    
    static int rows;
    static int columns;
    static int minIngre;
    static int maxCells;
    
    static String [][]pizza;
    
    static int M;
    static int T;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String []c="bella".split("");
        readFile();
        M=countOccurency("M");
        T=countOccurency("T");
        System.out.println("M="+M+" - T="+T);
        System.out.println("START ALGORITHM");
        System.out.println("------------------------------------");
        startAlgorithm();
        
    }
    
    public static void readFile()
    {
        BufferedReader br = null;
        FileReader fr = null;

        try {

                //br = new BufferedReader(new FileReader(FILENAME));
                fr = new FileReader(FILENAME);
                br = new BufferedReader(fr);

                String sCurrentLine;
                sCurrentLine = br.readLine();
                System.out.println(sCurrentLine);
                String[]initialData=sCurrentLine.split(" ");
                //set initial data
                rows=Integer.parseInt(initialData[0]);
                columns=Integer.parseInt(initialData[1]);
                minIngre=Integer.parseInt(initialData[2]);
                maxCells=Integer.parseInt(initialData[3]);
                //SET PIZZA
                pizza=new String[rows][columns];
                int countRows=0;
                while ((sCurrentLine = br.readLine()) != null) {
                    pizza[countRows]=sCurrentLine.split("");
                    countRows++;
                }
                System.out.println("File read");

        } catch (IOException e) {

                e.printStackTrace();

        } finally {

                try {

                        if (br != null)
                                br.close();

                        if (fr != null)
                                fr.close();

                } catch (IOException ex) {

                        ex.printStackTrace();

                }

        }
    }
    
    public static int countOccurency(String value)
    {
        int count=0;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                if(pizza[i][j].equals(value))
                    count++;
            }
        }
        return count;
    }
    
    public static void startAlgorithm(){
        Father f=new Father(rows,columns,minIngre,maxCells,pizza,M,T);
    }    
    
    public void manageThread(){
        //istanzio l'oggetto della classe che estende
        //l'interfaccia Runnable
        EsempioThread r = new EsempioThread();

        //Creo il Thread passando al costruttore della classe
        //Thread del Java l'oggetto della classe
        //che implementa l'interfaccia Runnable

        Thread nuovoThread = new Thread(r);
        nuovoThread.start();
    }
    
    public class EsempioThread implements Runnable {
        public void run() {
            int n;
            for(n=0;n<6;n++) {
              System.out.println("L'indice n è uguale a :" + n);
            }
        }
    }
}
