package jcodes;

import jcodes.FileData;
import jcodes.Word;

import java.util.Vector;

public class Dictionary {
    private Vector<Word> wordList;

    public Dictionary() {
        wordList = new Vector<Word>();
    }

    public Dictionary(String url) {
        wordList = FileData.readFile(url);
    }

    public void setWordList(Vector<Word> dictionary) {
        this.wordList = dictionary;
    }

    public Vector<Word> getWordList() {
        return wordList;
    }

    public void addWordList(Word word) {
        wordList.add(word);
    }

    public void addWordList(int index, Word word) {
        wordList.add(index,word);
    }

    public void deleteWordList(int index) {
        wordList.remove(index);
    }

    public void updateWordList(int index,Word word) {
        wordList.set(index ,word);
    }
}
