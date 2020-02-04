/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.logic;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CorrectSentence 
{
    String sentence;
    public CorrectSentence()
    {
        sentence="";
    }
    public CorrectSentence(String sentence)
    {
        this.sentence=sentence;
    }
    public boolean isCorrectStatement(String grammerrules)
    {
        ArrayList<String> unique=new ArrayList<String>();
        for(String st : grammerrules.split(","))
            if(!unique.contains(st))
                unique.add(st);
        
        String rule="";
        for(int i=0;i<unique.size();i++)
            rule+=unique.get(i)+",";
        
        rule=rule.substring(0,rule.length()-1);
        if(GrammerRules.getInstance().contains(rule))
            return(true);
        else
            return(false);
    /*    for(int i=0;i<unique.size();i++)
        System.out.print( unique.get(i) +  " ");
        return true;*/
    }
}
