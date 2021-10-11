package jcodes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class FileData {
    //doc file
    public static Vector<Word> readFile(String url) {
        Vector<Word> words = new Vector<Word>();
        try {
            Path path = Path.of(url);
            List<String> list = null;
            list = Files.readAllLines(path);
            for (String string : list) {
                if (!string.equals("")) {
                    String[] data = string.split(" ", 2);
                    if (data.length > 1) {
                        words.add(new Word(data[0], data[1]));
                    } else {
                        words.add(new Word(data[0], ""));
                    }
                }
            }
        } catch (IOException e) {
        }
        return words;
    }

    //ghi them 1 tu vao cuoi file
    public static void writeWordToFile(String url) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu muon them: ");
        String word_target = scanner.nextLine();
        System.out.print("Nhap nghia cua tu " + word_target + ": ");
        String word_explain = scanner.nextLine();
        try {
            FileWriter fw = new FileWriter(url, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n");
            bw.write(word_target + " " + word_explain);
            bw.close();
        } catch (IOException ignored) {
        }
    }

    //ghi danh sach vao file (co sap xep)
    public static void writeListToFile(String url, Vector<Word> list) {
        CommonFunction.sortListWord(list);
        try {
            FileWriter fw = new FileWriter(url);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).getWord_target() + " " + list.get(i).getWord_explain());
                if (i == list.size() - 1) {
                    break;
                }
                bw.write("\n");
            }
            bw.close();
        } catch (IOException ignored) {
        }
    }
}
