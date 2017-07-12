package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/12.
 */
public class ReflectUtils {
    /**
     * 得到运行时对象的属性
     * @param obj
     * @param fieldName
     * @return
     * @throws Exception
     */
    public Object getProperty(Object obj, String fieldName) throws Exception {
        Class clazz = obj.getClass();
        Field field = clazz.getField(fieldName);
        Object property = field.get(obj);
        return property;
    }

    /**
     * 得到运行时类的静态属性
     * @param className
     * @param fieldName
     * @return
     * @throws Exception
     */
    public Object getStaticProperty(String className, String fieldName)
            throws Exception {
        Class clazz = Class.forName(className);
        Field field = clazz.getField(fieldName);
        Object property = field.get(clazz);
        return property;
    }

    /**
     * 执行运行时对象的方法
     * @param obj
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    public Object invokeMethod(Object obj, String methodName, Object[] args) throws Exception {
        Class clazz = obj.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = clazz.getMethod(methodName, argsClass);
        return method.invoke(obj, args);
    }


    /**
     * 执行某个类的静态方法
     * @param className
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    public Object invokeStaticMethod(String className, String methodName,
                                     Object[] args) throws Exception {
        Class clazz = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = clazz.getMethod(methodName, argsClass);
        return method.invoke(null, args);
    }

    /**
     * 新建实例
     * @param className
     * @param args
     * @return
     * @throws Exception
     */
    public Object newInstance(String className, Object[] args) throws Exception {
        Class newoneClass = Class.forName(className);

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Constructor cons = newoneClass.getConstructor(argsClass);

        return cons.newInstance(args);

    }

    /**
     * 判断是否为某个类的实例
     * @param obj
     * @param clazz
     * @return
     */
    public boolean isInstance(Object obj, Class clazz) {
        return clazz.isInstance(obj);
    }


    /**
     * 得到数组中的某个元素
     * @param array
     * @param index
     * @return
     */
    public Object getByArray(Object array, int index) {
        return Array.get(array,index);
    }
}
