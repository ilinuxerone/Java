package arrayUtil;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/13.
 */
public class ArrayUtils {
    public static <T> T[] expandCapacity(T[] data, int newLength) {
        // 判断是否为负值
        newLength = newLength < 0 ? 0 : newLength;

        // 生成新数组,拷贝原值并制定长度
        //Arrays.copyOf为浅拷贝,在使用时需要注意
        return Arrays.copyOf(data, newLength);
    }


    public static int getMaxByArray(int[] data) {
        // 最简单自行实现的查找方式
        int max = data[0];
        for (int i = 1, size = data.length; i < size; i++) {
            max = max > data[i] ? max : data[i];
        }
        return max;
    }

    public static int getMaxBySortArray(int[] data) {
        // 先排序后获取最后位
        Arrays.sort(data);
        return data[data.length - 1];
    }
}
