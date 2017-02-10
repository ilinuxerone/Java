package sortUtils.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapSortUtil {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("c", "ccccc");
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("d", "ddddd");
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>(){
        	
        	public int compare(Entry<String, String> o1, Entry<String, String> o2){
        		//升序排序- key
        		return (o1.getKey()).toString().compareTo(o2.getKey());
        		//升序排序- value
        		//return o1.getValue().compareTo(o2.getValue());
        	}
        });
        
        for(Map.Entry<String, String> mapping : list)
        {
        	System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
        
        Iterator<Map.Entry<String, String>> entrys = list.iterator();
        while (entrys.hasNext())
        {
        	Map.Entry<String, String> temp = entrys.next();
        	System.out.println(temp.getKey()+":"+temp.getValue());
        }
    }
}
