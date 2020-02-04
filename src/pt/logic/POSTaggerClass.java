/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.logic;

import opennlp.tools.postag.*;
import opennlp.tools.cmdline.postag.*;
import opennlp.tools.tokenize.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class POSTaggerClass 
{
  StringBuilder contents;
  POSModel model = null;
POSTaggerClass posInstance = null;
String DIR_PATH = ".";
String POS_MODEL_FILE = "en-pos-maxent.bin";
ArrayList<String> speech;
  
  public POSTaggerClass()
  {
      contents=new StringBuilder();
  }
  public POSTaggerClass(StringBuilder contents)
  {
      this.contents=contents;
  }
  
  public ArrayList<String> process()
  {
      try {
    File inputFile = null;
	String fileLocation = "inputs//"+POS_MODEL_FILE;
	if (new File(fileLocation).exists()) {
		inputFile = new File(fileLocation);
	} else {
		System.out.println("File : " + fileLocation + " does not exists.");
	}
	if (inputFile != null) {
		model = new POSModelLoader().load(inputFile);
	}
} catch (Exception e) {
	e.printStackTrace();
}

      try {
          speech=new ArrayList<String>();
	if (model != null) {
		POSTaggerME tagger = new POSTaggerME(model);
		if (tagger != null) {
			// Call Sentence Detector
			//String[] sentences = getSentences(input);
			//String[] sentences=new String[]{"I am very happy today but my friend is sad","I was upset becoz of my trip cancel"};	
                    String []sentences=contents.toString().split("\n");
			for (String sentence : sentences) {
				String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(sentence);
				String[] tags = tagger.tag(whitespaceTokenizerLine);
				for (int i = 0; i < whitespaceTokenizerLine.length; i++) {
					String word = whitespaceTokenizerLine[i].trim();
					String tag = tags[i].trim();
					System.out.println(word + ":" + tag);
                                        speech.add(word + ":" + tag);
				}
			}
		}
		tagger = null;
                return(speech);
	}
} catch (Exception e) {
	e.printStackTrace();
        return(null);
}
return(speech);
  }
}
