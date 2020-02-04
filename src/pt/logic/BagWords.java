/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.logic;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;
import pt.auth.DataOperations;

/**
 *
 * @author Administrator
 */
public class BagWords
{
    StringBuilder topics;
    Set<String> _first,_second;
   Set<String> intersection,union;
    int intersect,unionr;
            double simratio;
    public BagWords()
   {
       _first=null;
       _second=null;
   }
   
    public boolean isValidEssay(StringBuilder sb,String classname,String topic)
    {
         try
         {
             topics=new DataOperations().getBagwords(classname,topic);
             setKeywords(topics,sb); 
             unionr=getUnion();
                intersect=getIntersection();
                simratio=((double)intersect/unionr)*100;
                
                System.out.println("Simialirty ration " + simratio);
                if(simratio>=30)
                    return(true);
                else
                    return(false);
         }
         catch(Exception ex)
         {
             return(false);
         }
    }
      private String removeStopChars(String term){
        String[] stopChars = {".",",",":",";","-","?","!","<",">"};

	for(String sc:stopChars){
		if(term.contains(sc)){
			term = term.replace(sc,"");
		}
	}
	return term;
    }
      
      private Set<String> getIntersectWords()
   {
       return(intersection);
   }
   
   private Set<String> getUnionWords()
   {
       return(union);
   }
   private int getIntersection()
   {
       
       intersection=Sets.intersection(_first, _second);
       return(intersection.size());
   }
   private int getUnion()
   {
       union=Sets.union(_first, _second);
       return(union.size());
   }
    private void setKeywords(StringBuilder _firstlist,StringBuilder _secondlist)
   {
       _first=new HashSet<String>();
       _second=new HashSet<String>();
       
           String []keywords;
           keywords=_firstlist.toString().split(",");
           
           String temp="";
           for(String st : keywords)
           {
              temp=removeStopChars(st);
              if(!StopWordsProvider.getInstance().contains(temp))
                  _first.add(temp);
           }
               keywords=_secondlist.toString().split(" ");
           
           for(String st : keywords)
           {
               temp=removeStopChars(st);
               if(!StopWordsProvider.getInstance().contains(temp))
               _second.add(st);
           }
   }
   
}
