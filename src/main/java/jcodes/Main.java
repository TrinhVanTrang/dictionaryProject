package jcodes;
import jfxcode.ConnectDatabase;
import jfxcode.Vocabulary;
import jcodes.FileData;

import java.sql.Connection;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
//        Dictionary dictionary=new Dictionary("WordList");
//        DictionaryManagement.showDictionary(dictionary);
//        //DictionaryManagement.searchWord(dictionary);
//        DictionaryManagement.addNewWord(dictionary);
//        DictionaryManagement.showDictionary(dictionary);
//        Vector<Word> words=DictionaryManagement.seachWordList(dictionary);
//        if(words!=null) {
//            CommonFunction.showListWord(words);
//        }
//       // DictionaryManagement.showDictionary(dictionary);
//        DictionaryManagement.writeDictinaryToFile("WordList",dictionary);

//        Scanner scanner=new Scanner(System.in);
//       // int id=scanner.nextInt();
//        String search=scanner.nextLine();
//        //Vocabulary vocabulary=new Vocabulary(id);
//        Vocabulary vcb= ConnectDatabase.querySelectOne(search,ConnectDatabase.LINKDB,"av");
//        System.out.println(vcb.getId()+" "+vcb.getWord()+" "+vcb.getDescription()+"\n"+vcb.getHtml());
//       // ConnectDatabase.queryInsert(vcb,ConnectDatabase.LINKDB,"myword");
//         ConnectDatabase.queryDelete(vcb,ConnectDatabase.LINKDB,"myword");
//        Vector<Vocabulary> vocabularies=ConnectDatabase.querySelectMyWord("",ConnectDatabase.LINKDB);
//        for(Vocabulary vocabulary:vocabularies) {
//            System.out.println(vocabulary.getId()+" "+vocabulary.getWord()+" "+vocabulary.getDescription()+"\n"+vocabulary.getHtml());
//            System.out.println("============\n");
//        }
        Vocabulary vocabulary=ConnectDatabase.querySelectOne("hello",ConnectDatabase.LINKDB,"av");
        System.out.println(ConnectDatabase.IsConcern(vocabulary,ConnectDatabase.LINKDB,"history"));
        if(ConnectDatabase.IsConcern(vocabulary,ConnectDatabase.LINKDB,"history")) {
            ConnectDatabase.queryDelete(vocabulary,ConnectDatabase.LINKDB,"history");
        } else {
            ConnectDatabase.queryInsert(vocabulary,ConnectDatabase.LINKDB,"history");
        }
        System.out.println(ConnectDatabase.IsConcern(vocabulary,ConnectDatabase.LINKDB,"history"));
    }
}
