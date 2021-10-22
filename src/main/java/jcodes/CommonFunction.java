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




}
