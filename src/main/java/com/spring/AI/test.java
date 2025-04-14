package com.spring.AI;

import java.util.*;

public class test {
    public static void main(String[] args) {

        int[] nums = new int[]{1,3,4,2,2};
        Set<Integer> seen = new HashSet<>();
        int a =Arrays.stream(nums).filter(value -> !seen.add(value)).findFirst().getAsInt();


        /**int sum=0;
        int[] height = Arrays.stream("4,2,0,3,2,5".split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(height));*/

        /**Map<String, List<String>> map = new HashMap<>();
        map.put("1", new ArrayList<>(List.of("1","1")));
        map.put("2", new ArrayList<>(List.of("2")));
        map.put("3", new ArrayList<>(List.of("3")));

        map.computeIfAbsent("4", k-> List.of("4"));
        map.computeIfPresent("2", (k, v) -> {
            v.add(k);
            return v;
        } );
        System.out.println(map);*/

        /** Leet Code Problem 49:
        String[] a = new String[4];
        a[0] = "eat";
        a[1] = "ate";
        a[2] = "tea";
        a[3] = "Cat";
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> out = new ArrayList<>();
        for(String str:a){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);

            if(map.containsKey(s)){
                List<String> list = map.get(s);
                list.add(str);
                map.put(s, list);
            }else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        map.forEach((k,v)->{out.add(v);});
        System.out.println(out);*/

        /**Leet Code Problem 20:
        String a="))";
        System.out.println(func(a));
         */

        /**Leet Code Problem 3:
        String str = "dvdf";
        HashSet<Character> hset = new HashSet<>();
        int a=0;int b=0; int max=0;

        while(b<str.length()){
            if(!hset.contains(str.charAt(b))){
                hset.add(str.charAt(b));
                b++;
                max = Math.max(hset.size(), max);
            }else {
                hset.remove(str.charAt(a));
                a++;
            }
        }

        System.out.println(max);*/
    }

    static boolean func(String s){
        if(s.length()%2!=0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int count=0;

        while (count<s.length()){
            char c = s.charAt(count);
            if(c=='(' || c=='{' || c=='['){
                stack.push(c);
                count++;
            }else{
                c =s.charAt(count);
                if(stack.isEmpty()){
                    return false;
                }
                if(c==')'  && stack.pop()!='('){
                    return false;
                }else if(c=='}' && stack.pop()!='{'){
                    return false;
                }else if(c==']' && stack.pop()!='['){
                    return false;
                }
                count++;
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
}
