package jcodes;

import java.util.Vector;

public class CommonFunction {
    //tim kiem tu -> tra ve tu duoc tim thay
    public static Word search(Vector<Word> list, String string) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWord_target().equals(string)) {
                return list.get(i);
            }
        }
        return null;
    }

    //tim kiem tu -> tra ve vi tri tim thay trong danh sach
    public static int searchToIndex(Vector<Word> list, String string) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWord_target().equals(string)) {
                return i;
            }
        }
        return -1;
    }

    //show danh sach tu
    public static void showListWord(Vector<Word> list) {
        for (Word word : list) {
            System.out.println(word.getWord_target() + ": " + word.getWord_explain());
        }
    }

    //sap xep danh sach tu
    public static void sortListWord(Vector<Word> list) {
        //bubble sort
        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getWord_target().compareTo(list.get(j + 1).getWord_target()) > 0) {
                    flag = false;
                    Word temp = new Word(list.get(j).getWord_target(), list.get(j).getWord_explain());
                    list.get(j).setWord(list.get(j + 1).getWord_target(), list.get(j + 1).getWord_explain());
                    list.get(j + 1).setWord(temp.getWord_target(), temp.getWord_explain());
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
