/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.logic;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GrammerRules 
{
    //add some rules here to check whether the sentence is correct.
    //big process of NLP and it is ongoing research work 

  private String[] rules = {"NN,IN,DT,VBD,WP$,VBZ,RB,JJ,WRB,CC","NN,JJ,RB,VB","NN,DT,NN,VBD,WP$,VBZ,RB","NN,DT,NN,VBD,WP$,VBZ,RB,JJ","NN,JJ,WRB,VBZ","NN,IN,DT,CC","VBZ,JJ,DT,NN","NN,VBZ,DT,IN","PRP,VBP,RB,JJ,NN,CC","PRP,VBD,JJ,IN,PRP$,VB","NN,VB,VBP,VBD"};
    private List<String> words = null;
    private GrammerRules(){
        
    }
    public static GrammerRules getInstance(){
        if(provider==null){
            provider = new GrammerRules();
        }
        return provider;
    }
    private static GrammerRules provider = null;
    
    public String[] getRules(){
        return rules;
    }
    
    public java.util.List<String> getRulesList(){
        return Arrays.asList(rules);
    }
    public boolean contains(String word)
    {
        if(words==null){
            words = Arrays.asList(rules);
        }
        return words.contains(word.toLowerCase());
    }
}