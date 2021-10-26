package jcodes;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

    public static int BNSToIndex(Vector<Word> list,String string) {
        int left=0;
        int right=list.size()-1;
        while(right>=left) {
            int mid=left+(right-left)/2;
            if(list.get(mid).getWord_target().compareTo(string)>0) {
                right=mid-1;
            }else if(list.get(mid).getWord_target().compareTo(string)<0) {
                left=mid+1;
            } else if(list.get(mid).getWord_target().compareTo(string)==0) {
                return mid;
            }
        }
        //System.out.println(left+" "+(left+(right-left)/2)+" "+right);
        return left+(right-left)/2;
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

    public static int partition(Vector<Word>a,int low,int high) {
        Word pivot=a.get(high);
        int i=low-1;
        for(int j=low;j<high;j++)
        {
            //cout<<i<<" "<<j<<endl;
            if(a.get(j).getWord_target().compareTo(pivot.getWord_target())<=0) {
                i++;
                Word temp=a.get(i);
                a.set(i,a.get(j));
                a.set(j,temp);
            }
        }
        Word temp=a.get(i+1);
        a.set(i+1,a.get(high));
        a.set(high,temp);
        return i+1;
    }

    public static void quickSort(Vector<Word>a,int low,int high) {
        if(low<high) {
            int pivot=partition(a,low,high);
            quickSort(a,low,pivot-1);
            quickSort(a,pivot+1,high);
        }
    }

    public static String translateText(String text ,String langfrom,String langto) throws UnsupportedEncodingException, IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbyyOZ8m5BEhlL74JcbBs7VygJVeAO5NWMX_8edPVT0qnAPmvFA27DmOYLQrPLRPgtwJhA/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langto +
                "&source=" + langfrom;
        //System.out.println(urlStr);
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }


    public static void playAudio(String url) {
        Media media=new Media(new File(url).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static void playAudio() {
        Media media=new Media(new File("src/main/resources/com/example/dictinary/audio/clickButton.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static void textToSpeech(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
        }
        try {
            voice.setRate(190);
            voice.setPitch(150);
            voice.setVolume(3);
            voice.speak(text);
            voice.deallocate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
