package sort;

/**
 * Created by Administrator on 2017/7/10.
 */
public class SortUtils {
    public static <T extends  Comparable> T[] bubbleSort(T oriArray[], int size)
    {
        T[] newArray = oriArray;
        for(int i = 0; i < size; i++){
            for (int j = 1; j < size - i; j++){
                if (oriArray[j - 1].compareTo(oriArray[j]) > 0){
                    T tmp = newArray[j-1];
                    newArray[j-1]=newArray[j];
                    newArray[j]=tmp;
                }
            }
        }
        return newArray;
    }

    public static void main(String [] args)
    {
        Integer[] intArray = {1,2,1,22,11,23,12,32,24,55};
        bubbleSort(intArray, intArray.length);
        for (Integer element : intArray){
            System.out.print(element+";");
        }
        System.out.println();
    }
}
