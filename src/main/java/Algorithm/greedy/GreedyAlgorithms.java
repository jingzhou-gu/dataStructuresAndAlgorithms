package main.java.Algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: gjz
 * @Date: 2024/1/9 16:42
 * @Description: TODO 贪心算法
 */
public class GreedyAlgorithms {
    public static void main(String[] args) {
        //创建广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //将各个电台加入到广播中
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        // allAreas存放所有需要覆盖的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建集合存放电台集合
        ArrayList<String> selects = new ArrayList<>();

        //用来存放广播与所有地区中重叠的地区集合
        HashSet<String> tempSet = new HashSet<>();

        int maxValue = 0;
        String maxKey = null; //覆盖数量最多的key
        while (allAreas.size() > 0){
            maxKey = null;
            maxValue = 0;
            for (String key : broadcasts.keySet()) { //遍历广播中的每个key
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key); //取出每个key对应的所有地区集合
                tempSet.addAll(areas); //将地区集合添加到临时集合
                tempSet.retainAll(allAreas); //将临时集合中的地区和所有地区求出交集放入临时集合

                if(tempSet.size()>0 && (maxKey == null || tempSet.size() > maxValue) ){
                    maxKey = key;
                    maxValue = tempSet.size();
                }
            }
            if(maxKey != null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

//        HashMap<String, Integer> map = new HashMap<>();
//        while(allAreas.size()>0){
//            int max = 0;
//            String k = null;
//            for (String key : broadcasts.keySet()) {
//                HashSet<String> areas = broadcasts.get(key);
//                for (String area : areas) {
//                    if(allAreas.contains(area)){
//                        Integer integer = map.get(key);
//                        if(integer == null){
//                            map.put(key,1);
//                        }else{
//                            map.put(key,integer+1);
//                        }
//                    }
//                }
//            }
//            for (String key : map.keySet()) {
//                Integer integer = map.get(key);
//                if(integer>max){
//                    k = key;
//                    max = integer;
//                }
//            }
//            selects.add(k);
//            HashSet<String> set = broadcasts.get(k);
//            for (String key : set) {
//                if(allAreas.contains(key)){
//                    allAreas.remove(key);
//                }
//            }
//            map.clear();
//        }

        System.out.println(selects);
    }
}
