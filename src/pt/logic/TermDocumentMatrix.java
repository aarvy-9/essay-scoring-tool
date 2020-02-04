/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.logic;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Administrator
 */
public class TermDocumentMatrix 
{
    TreeSet<String> terms;
    ArrayList<String> docname;
    Object [][]matrix;
    public TermDocumentMatrix()
    {
         terms=new TreeSet<String>();
         docname=new ArrayList<String>();
    }
    public boolean addTerms(String doc,StringBuilder sb)
    {
        try
        {
           docname.add(doc);
            for(String st:sb.toString().split(" "))
            { 
                if(st.trim().length()!=0)
                {
                if(!StopWordsProvider.getInstance().contains(st))
                {
                 if(!terms.contains(st))
                     terms.add(st);
                }
            }
            }
            return(true);
        }
        catch(Exception ex)
        {
            return(false);
        }
    }
    public ArrayList getStats(StringBuilder contents,int totalsentences)
    {
        ArrayList stats=new ArrayList();
        String information="";
        int wordcount=0;
        information=totalsentences + ",";
        double avg=0.0;
        double avglength=0.0;
       try
       {
           for(String st :contents.toString().split(" "))
               if(st.trim().length()!=0)
               {
                    wordcount++;
                    avg+=st.length();
               }
         if(wordcount>0)  
             avglength=(double)avg/wordcount;
         information+=wordcount+"," + avglength;
         stats.add(information);
        return(stats);
       }
       catch(Exception ex)
       {
           return(null);
       }
    }
    public double getWeight(Object [][]matrix,int topk)
    {
        double weight=0.0;
        double initalweight=1.0;
             for(int j=1;j<matrix[0].length;j++)
             {
               //  System.out.print(matrix[0][j] + "       ");
                 if(Integer.parseInt(matrix[0][j].toString())>=topk)
                 weight+=Integer.parseInt(matrix[0][j].toString())*initalweight;
                 initalweight++;
             }
             if(weight==0.0)
                  weight=1.0;
             return(Math.sqrt(weight));
        
    }
    public StringBuilder createMatrix(StringBuilder contents)
    {
        
        StringBuilder matrixc=new StringBuilder();
        try
        {
         matrix=new Object[docname.size()][terms.size()+1];
         int cnt=0;
         for(int i=0;i<matrix.length;i++)
         {
             matrix[i][0]=docname.get(i).toString();
             for(int j=0;j<terms.toArray().length;j++)
             {
                   for(String st:contents.toString().split(" "))
                   {
                       if(st.equals(terms.toArray()[j].toString()))
                               cnt++;
                   }      
                   matrix[i][j+1]=cnt;
                   cnt=0;
             }
         }
         System.out.print("DocName      ");
         matrixc.append("DocName       ");
         for(int i=0;i<terms.size();i++)
         {
             System.out.print(terms.toArray()[i].toString() + "   ");
              matrixc.append(terms.toArray()[i].toString() + "   ");
         }
         matrixc.append("\r\n");
         
         for(int i=0;i<matrix.length;i++)
         {
             matrixc.append("\r\n" + matrix[i][0] + "       ");
             System.out.print("\r\n" + matrix[i][0] + "        ");
             for(int j=1;j<matrix[i].length;j++)
             {
                 System.out.print(matrix[i][j] + "       ");
                 matrixc.append(matrix[i][j] + "       ");
             }
             System.out.println();
             matrixc.append("\r\n");
         }
         return(matrixc);
        }
       catch(Exception ex)
       {
           return(new StringBuilder("Unable to Create Bag of Words "));
       }
    }

    public Object[][] getMatrix()
    {
        return(matrix);
    }

public static void main(String []args)
{
    TermDocumentMatrix tdm=new TermDocumentMatrix();
    //tdm.addTerms("first",new StringBuilder("india is great among other countries india is best among"));
    //tdm.createMatrix(new StringBuilder("india is great among other countries india is best among"));
    
    tdm.addTerms("first",new StringBuilder("where in created  currency   demonetarization   exchange   flow   high where   income   issue   middleman   money   problem   slow   todays   world"));
    tdm.createMatrix(new StringBuilder("where in created  currency   demonetarization   exchange   flow   high where   income   issue   middleman   money   problem   slow   todays   world"));
    System.out.println("Weight of essay is " + tdm.getWeight(tdm.getMatrix(),2));
}
}