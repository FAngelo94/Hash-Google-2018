/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcodeexample;

import static hashcodeexample.HashCodeExample.maxCells;

/**
 *
 * @author Angelo
 */
public class Sons implements Runnable{
    
    //Initial Values, I never modify this values
    Father f;
    SaveSolution solution;
    int [][]pizzaUsed;
    
    int r1=0,r2=0,c1=0,c2=0;
    
    int fr1=0,fr2=0,fc1=0,fc2=0;
    
    
    public Sons(int[][]piUs, Father tf, SaveSolution s){
        pizzaUsed=piUs;
        f=tf;
        solution=new SaveSolution();
        solution.clone(s);
    }
    
    public Sons(int[][]piUs, Father tf, SaveSolution s,int startR,int startC){
        r1=startR;
        r2=startR;
        c1=startC;
        c2=startC;
        f=tf;
        pizzaUsed=new int[f.rows][f.columns];
        for(int i=0;i<f.rows;i++)
        {
            for(int j=0;j<f.columns;j++)
            {
                pizzaUsed[i][j]=piUs[i][j];
            }
        }
        solution=new SaveSolution();
        solution.clone(s);
        //Start really algorithm
    }
    private void resetVariable(){
        r1=fr1;r2=fr2;c1=fc1;c2=fc2;
    }
    
    public boolean findMinimalSolution(){
        //DEVO SISTEMARE
        newSolution();
        boolean solutionFound=false;
        int maxAreaFound=0;
        for(int i=r1;i<f.maxCells+r1 && i<f.rows;i++)
        {
            for(int j=c1;j<f.maxCells+c1 && j<f.columns;j++)
            {
                int area=(i+1-r1)*(j+1-c1);
                if(area<=f.maxCells)
                {//area ok
                    if(countOccurency("M",r1,i,c1,j)>=f.minIngre && countOccurency("T",r1,i,c1,j)>=f.minIngre)
                    {//ingredient ok
                        if(!checkOverlap(r1,i,c1,j))
                        {//not overlap
                            if(area>maxAreaFound)
                            {
                                newSolution(r1,i,c1,j);
                                maxAreaFound=area;
                                solutionFound=true;
                            }
                        }
                    }
                }
            }
        }
        
        return solutionFound;
    }
    public void newSolution(int r1,int r2,int c1,int c2){
        fr1=r1;
        fr2=r2;
        fc1=c1;
        fc2=c2;
    }
    public boolean checkOverlap(int r1,int r2,int c1,int c2)
    {
        for(int i=r1;i<=r2;i++)
        {
            for(int j=c1;j<=c2;j++)
            {
                if(pizzaUsed[i][j]==1)
                    return true;
            }    
        }
        return false;
    }
    public int countOccurency(String value,int r1,int r2,int c1,int c2)
    {
        int count=0;
        for(int i=r1;i<=r2;i++)
        {
            for(int j=c1;j<=c2;j++)
            {
                if(f.pizza[i][j].equals(value))
                    count++;
            }
        }
        return count;
    }
    
    private boolean simpleIncrement(){
        boolean check=true;
        if(r2<c2 && r2<f.rows-1)
        {
            r2++;
            check=false;
        }
        if(c2<r2 && c2<f.columns-1 && check)
        {
            c2++;
            check=false;
        }
        if(r2==c2 && r2<f.rows-1 && c2<f.columns-1 && check)
        {
            if(f.rows>f.columns)
                r2++;
            else
                c2++;
            check=false;
        }
        if(check)
        {
            if(r2<f.rows-1)
            {
                r2++;
                check=false;
            }
            if(c2<f.columns-1)
            {
                c2++;
                check=false;
            }
        }
        return check;
    }
    private boolean rowIncrement(){
        if(r2<f.rows-1)
        {
            r2++;
            return false;
        }
        return true;
    }
    private boolean columnIncrement(){
        if(c2<f.columns-1)
        {
            c2++;
            return false;
        }
        return true;
    }
    
    public void createNewSons(){
        //before I finish a row and then I restart from column 0
        if(fc2<f.columns-1)
        {
            f.createdSon(pizzaUsed, solution, fr1, fc2+1);
        }
        else
        {
            if(fr2<f.rows-1)
            {
                f.createdSon(pizzaUsed, solution, fr2+1, 0);
            }
            else
            {
                f.printBestSolution();
            }
        }
    }
    
    public boolean checkIngredients(){
        if(countOccurency("M")>=f.minIngre && countOccurency("T")>=f.minIngre)
            return true;
        return false;
    }
    public int countOccurency(String value)
    {
        int count=0;
        for(int i=r1;i<=r2;i++)
        {
            for(int j=c1;j<=c2;j++)
            {
                if(f.pizza[i][j].equals(value))
                    count++;
            }
        }
        return count;
    }
    public boolean checkArea(){
        int area=(r2-r1+1)*(c2-c1+1);
        if(area<=maxCells)
            return true;
        return false;
    }
    public boolean checkOverlap()
    {
        for(int i=r1;i<=r2;i++)
        {
            for(int j=c1;j<=c2;j++)
            {
                if(pizzaUsed[i][j]==1)
                    return true;
            }    
        }
        return false;
    }
    public void writePizza()
    {
       for(int i=fr1;i<=fr2;i++)
        {
            for(int j=fc1;j<=fc2;j++)
            {
                pizzaUsed[i][j]=1;
            }    
        }
       solution.addPiece(fr1+" "+fc1+" "+fr2+" "+fc2);
       return;
    }
    public void newSolution(){
        fr1=r1;
        fr2=r2;
        fc1=c1;
        fc2=c2;
    }
    
    @Override
    public void run() {
        if(findMinimalSolution())
        {
            writePizza();
            f.saveSolution(solution, pizzaUsed);
            
        }
        createNewSons();
    }

}
