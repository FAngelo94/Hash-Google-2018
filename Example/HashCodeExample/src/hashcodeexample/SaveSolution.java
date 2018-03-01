/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcodeexample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public class SaveSolution {
    int pieces;
    SolutionNode solution;
    public SaveSolution(){
        pieces=0;
        solution=null;
    }
    public void addPiece(String piece){//Add new piece coordinates on the head
        pieces++;
        if(solution==null)
        {
            solution=new SolutionNode();
            solution.row=piece;
            solution.next=null;
        }
        else
        {
            SolutionNode tmp=new SolutionNode();
            tmp.row=piece;
            tmp.next=solution;
            solution=tmp;
        }
    }
    public void printSolution(){
        try {
            System.out.println(pieces);
            SolutionNode tmp=solution;
            String text=pieces+"\n";
            while(tmp!=null)
            {
                System.out.println(tmp.row);
                text=text+tmp.row+"\n";
                tmp=tmp.next;
            }
            //print in a file too
            Files.write(Paths.get("c:/Users/Angelo/Desktop/Hash Code/Example/big.out"), text.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(SaveSolution.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class SolutionNode{
        String row;
        SolutionNode next;
    }
    
    public void clone(SaveSolution old)
    {
        if(old!=null)
        {        
            pieces=old.pieces;
            solution=old.solution;
        }
    }
}
