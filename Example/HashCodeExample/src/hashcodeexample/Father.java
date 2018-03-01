/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcodeexample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Angelo
 */
public class Father {
    
    //Initial Values, I never modify this values
    int rows;
    int columns;
    int minIngre;
    int maxCells;
    
    String [][]pizza;
    
    int M;
    int T;
    
    private ExecutorService executor;
    
    private int bestPoint=0;
    
    SaveSolution bestSolution;
    
    public Father(int r,int c,int min,int max, String [][]p,int m,int t){
        rows=r;
        columns=c;
        minIngre=min;
        maxCells=max;
        pizza=p;
        M=m;
        T=t;
        int[][]pizzaUsed=new int[rows][columns];
        createdSon(pizzaUsed);
    }
    
    void createdSon(int[][]pizzaUsed)
    {
        SaveSolution sol=new SaveSolution();
        
        Sons s=new Sons(pizzaUsed, this,sol);
        Thread t=new Thread(s);
        t.start();
    }
    
    synchronized void createdSon(int[][]pizzaUsed,SaveSolution sol,int startR,int startC)
    {   
        Sons s=new Sons(pizzaUsed, this,sol,startR,startC);
        Thread t=new Thread(s);
        t.start();
    }
    
    void printBestSolution(){
        bestSolution.printSolution();
        System.out.println("Punti="+bestPoint);
    }
    
    synchronized void  saveSolution(SaveSolution s,int[][]usedPizza)
    {
        int cont=0;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                if(usedPizza[i][j]==1)
                    cont++;
            }    
        } 
        if(cont>bestPoint)
        {
            bestPoint=cont;
            System.out.println("--NEW BEST POINT--");
            System.out.println("Punti="+cont);
            bestSolution=new SaveSolution();
            bestSolution.clone(s);
        }
    }
}
