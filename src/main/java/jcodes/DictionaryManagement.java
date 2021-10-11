package jcodes;

import jcodes.Word;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Callable;

public class DictionaryManagement {
    //ghi tu dien vao file
    public static void writeDictinaryToFile(String url, Dictionary dictionary) {
        FileData.writeListToFile(url, dictionary.getWordList());
    }

    //show tu dien
    public static void showDictionary(Dictionary dictionary) {
        CommonFunction.showListWord(dictionary.getWordList());
    }

    //sap xep tu dien
    public static void sortDictionary(Dictionary dictionary) {
        CommonFunction.sortListWord(dictionary.getWordList());
    }

    //tim kiem tu
    public static void searchWord(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu muon tim kiem: ");
        String search = scanner.nextLine();
        Word word = CommonFunction.search(dictionary.getWordList(), search);
        if (word != null) {
            System.out.println("Nghia cua tu " + word.getWord_target() + " la: " + word.getWord_explain());
        } else {
            System.out.println("Tu " + search + " khong co trong tu dien.");
        }
    }

    //them tu
    public static void addNewWord(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu muon them vao tu dien: ");
        String target = scanner.nextLine();
        Word word = CommonFunction.search(dictionary.getWordList(), target);
        if (word != null) {
            System.out.println("Tu " + word.getWord_target() + " da co trong tu dien.");
            System.out.println("Nghia cua tu " + word.getWord_target() + " la: " + word.getWord_explain());
        } else {
            System.out.print("Nhap nghia cua tu " + target + ": ");
            String explain = scanner.nextLine();
            dictionary.addWordList(new Word(target, explain));
            System.out.println("Tu " + target + " da duoc them vao tu dien.");
            sortDictionary(dictionary);
        }
    }

    //xoa tu
    public static void deleteWord(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu muon xoa khoi tu dien: ");
        String target = scanner.nextLine();
        Word word = CommonFunction.search(dictionary.getWordList(), target);
        int index = CommonFunction.searchToIndex(dictionary.getWordList(), target);
        if (index >= 0) {
            System.out.println(word.getWord_target() + ": " + word.getWord_explain());
            dictionary.deleteWordList(index);
            System.out.println("Tu " + target + " da duoc xoa khoi tu dien.");

        } else {
            System.out.println("Tu " + target + " khong co trong tu dien.");
        }
    }

    //cap nhat tu
    public static void updateWord(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu muon cap nhat: ");
        String target = scanner.nextLine();
        int index = CommonFunction.searchToIndex(dictionary.getWordList(), target);
        Word word = CommonFunction.search(dictionary.getWordList(), target);
        if (index >= 0) {
            System.out.println(word.getWord_target() + ": " + word.getWord_explain());
            System.out.print("Nhap nghia muon cap nhat: ");
            String explain = scanner.nextLine();
            dictionary.updateWordList(index, new Word(target, explain));
            System.out.println("Da hoan tat cap nhat");
            System.out.println(target + ": " + explain);
        } else {
            System.out.println("Tu " + target + " khong co trong tu dien.");
            System.out.println("Co muon them tu " + target + " vao tu dien khong?");
            System.out.println("Yes(1)/No(0)?");
            int ans;
            do {
                ans = scanner.nextInt();
                if (ans != 0 && ans != 1) {
                    System.out.println("Nhap lai!");
                }
            } while (ans != 0 && ans != 1);
            if (ans == 1) {
                System.out.print("Nhap nghia cua tu " + target + ": ");
                String explain = scanner.nextLine();
                dictionary.addWordList(new Word(target, explain));
                System.out.println("Tu " + target + " da duoc them vao tu dien.");
                sortDictionary(dictionary);
            } else {
                System.out.println("Exit!");
            }
        }
    }
}
