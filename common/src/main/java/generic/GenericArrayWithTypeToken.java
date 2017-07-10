package generic;

import java.lang.reflect.Array;

/**
 * Created by Administrator on 2017/7/10.
 */
public class GenericArrayWithTypeToken<T> {
    T[] array;

    public GenericArrayWithTypeToken(Class<T> type, int sz) {
        array = (T[]) Array.newInstance(type, sz);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gawtt = new GenericArrayWithTypeToken<>(Integer.class, 10);
        Integer[] ia = gawtt.rep(); //能成功返回了！
    }
}
