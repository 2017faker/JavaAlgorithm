import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
public class AllSort {

    static String[] whichSort=new String[10];


    public static void call(LinkedList array,LinkedList list,int which){
        //找到原数组，准备选择排序
        array=(LinkedList)list.clone();
        System.out.println("原始数组：");
        System.out.println(array);

        long start=System.currentTimeMillis();
        //selectSort(array);
        switch (which){
            case 0:
                bubbleSort(array);
                break;
            case 1:
                selectSort(array);
                break;
            case 2:
                insertSort(array);
                break;
            case 3:
                quickSort(array,0,array.size()-1);
                break;
            case 4:
                array=mergeSort(array);
                break;
            case 5:
                shellSort(array,7);


        }

        long end=System.currentTimeMillis();

        System.out.println(whichSort[which]+"时间"+(end-start));
        System.out.println(array+"\n");
    }

    public  static void swap(LinkedList array,int i,int j){
        int k=(int)array.get(i);
        array.set(i,array.get(j));
        array.set(j,k);
    }
    public static void bubbleSort(LinkedList array){//冒泡排序,稳定

        for(int i=0;i<array.size()-1;i++){
            for(int j=0;j<array.size()-1;j++){
                if((int)array.get(j+1)<(int)array.get(j)){
                    swap(array,j+1,j);
                }
            }
        }

    }

    public static void selectSort(LinkedList array){//选择排序，不稳定
            for(int i=0;i<array.size()-1;i++){
                int k=i;
                for(int j=i+1;j<array.size();j++){
                    if((int)array.get(j)<(int)array.get(k)){
                        k=j;
                    }
                }
                if(k!=i) {
                    swap(array, i, k);
                }
            }
    }
    public static void insertSort(LinkedList array){
        for(int i=0;i<array.size();i++){
            int temp=(int)array.get(i);
            for(int j=i-1;j>=0;j--){
                if(temp<(int)array.get(j) && j>0){
                    array.set(j+1,array.get(j));
                }
                else if(temp>(int)array.get(j)){
                    array.set(j+1,temp);
                    break;
                }
                else{//j=0并且temp<(int)array.get(j)的情况
                    array.set(j+1,array.get(j));
                    array.set(0,temp);
                    break;
                }
            }
        }
    }
    public static void quickSort(LinkedList array,int left,int right){
        if(left==right){
            return;
        }
        int basis=(int)array.get(left);
        int l=left;
        int r=right;
        while(right>left){
            if((int)array.get(right)<basis){//右边往左边找
                swap(array,right,left);
                while (right>left){//左边往右边找
                    if((int)array.get(left)>basis){
                        swap(array,left,right);
                        break;//找到后重新从右边往左边找
                    }
                    left++;
                }
            }
            right--;
        }
        if(left-1>=l){
            quickSort(array,l,left-1);
        }
        if(left+1<=r){
            quickSort(array,left+1,r);
        }

    }

    public static LinkedList mergeSort(LinkedList array){

        int len=array.size();
        if(len<=2){
            if (len==2 && (int)array.get(0)>(int)array.get(1)){
                swap(array,0,1);
            }
            return array;
        }

        LinkedList left=mergeSort( new LinkedList(array.subList(0,len/2)));

        LinkedList right=mergeSort( new LinkedList(array.subList(len/2,len)));

        LinkedList merge=new LinkedList();
        while (merge.size()!=len){
            if(left.isEmpty()&&!right.isEmpty()){
                merge.add(right.getFirst());
                right.removeFirst();

            }
            else if (!left.isEmpty() && right.isEmpty()){
                merge.add(left.getFirst());
                left.removeFirst();

            }
            else {//都不为空
                if ((int)left.getFirst()>=(int)right.getFirst()){
                    merge.add(right.getFirst());
                    right.removeFirst();
                }
                else{
                    merge.add(left.getFirst());
                    left.removeFirst();
                }
            }

        }
        return merge;
    }

    public static void shellSort(LinkedList array,int incresment){

        if(incresment>0){
            for(int i=0;i<array.size()-incresment;i+=incresment){
                int temp=(int)array.get(i+incresment);
                if((int)array.get(i)>temp){
                    array.set(i+incresment,array.get(i));
                    int index=i;
                    for(int j=i-incresment;j>=0;j-=incresment){
                        array.set(j+incresment,array.get(j));
                        if((int)array.get(j)<=temp){
                            index=j+incresment;
                            break;
                        }
                        else if(j==0){
                            index=0;
                        }
                    }
                    array.set(index,temp);
                }

            }
            shellSort(array,incresment-2);
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        whichSort[0]="冒泡排序";
        whichSort[1]="选择排序";
        whichSort[2]="插入排序";
        whichSort[3]="快速排序";
        whichSort[4]="归并排序";
        whichSort[5]="希尔排序";
        whichSort[6]="计数排序";
        whichSort[7]="基数排序";
        whichSort[8]="堆排序";
        whichSort[9]="桶排序";

        LinkedList array=new LinkedList();
        Random rd=new Random(System.currentTimeMillis());
        for(int i=0;i<3000;i++){
            array.add(rd.nextInt());
        }
        LinkedList list=(LinkedList)array.clone();



        System.out.println("0是冒泡，1是选择，2是插入，3是快速，4是二路归并排序，5是希尔排序，6是计数排序，7是基数排序，8是堆排序，9是桶排序");

        while (input.hasNextInt()){
            int which=input.nextInt();
            call(array,list,which);
        }




    }


}