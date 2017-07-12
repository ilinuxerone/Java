package search;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Administrator on 2017/7/12.
 */
public class SearchUtils {
    static <T extends Comparable> int binarySearch(T[] array, T key) {
        Arrays.sort(array);
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) >> 1;
            if (key.compareTo(array[mid]) > 0) {
                low = mid + 1;
            } else if (key.compareTo(array[mid]) < 0) {
                high = mid -1;
            } else {
                return mid;
            }
        }


        return -1;
    }

    /**
     * @param i   数组
     * @param key 要查找的Key
     * @return
     */
/*    static int binarySearch(int[] i, int key) {
        Arrays.sort(i);
        int low = 0;
        int high = i.length - 1;
        //int mid = (low + high)/2;

        while (low <= high) {              // 计算条件，数组低下标和高下标一直在计算中变化
            int mid = (low + high) >> 1;   // 使用>>比除法计算效率高
            if (key > i[mid]) {
                low = mid + 1;
            } else if (key < i[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }*/

    public static void main(String[] args) {
        Integer[] intArray = {1, 4, 2, 1, 8, 0, 6, 3};
        System.out.println(binarySearch(intArray, 1));

    }
}
