/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.spellchecker;

/**
 *
 * @author Administrator
 */
public class SpellCheckerTool
{
   String sentence;
   MySpellChecker spellChecker;
   int totalcount;
   
   public SpellCheckerTool()
   {
       sentence="";
        spellChecker= new MySpellChecker("inputs/dicwords.txt");
   }
   
   public String getBest(String sentence)
   {
       this.sentence=sentence;
       totalcount=sentence.split(" ").length;
        //spellChecker= new MySpellChecker("dicwords.txt");
       String modified="";
       modified=spellChecker.doCorrection(sentence);
       return(modified);
   }
   
   public String getCount()
   {
       return(spellChecker.getMisspellCount() + "#" + totalcount);
   }
   
   public static void main(String []args)
   {
        SpellCheckerTool sct=new SpellCheckerTool();
        String modified;
        modified=sct.getBest("frmo whne hapens brutlses");
        System.out.println(modified);
        System.out.println(sct.getCount());
   }
}
