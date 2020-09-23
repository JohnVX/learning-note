package string;

/**
 * 字符串匹配方法: 简单匹配方法
 */
public class SimpleSearch {
    public static void main(String[] args) {
        match("aabababdcabababcababc", "abababc");
    }

    public static void match(String string, String pattern){
        int index, pos, round;
        index = pos = round = 0;
        while(index < pattern.length() && pos < string.length()){
            if(pattern.charAt(index) == string.charAt(pos)){
                index++;
                pos++;
            }else {
                round++;
                index = 0;
                pos = round;
            }
        }
        if(index >= pattern.length()){
            System.out.println("find pattern in string, index :" + (pos - pattern.length()));
        }else {
            System.out.println("dont't match");
        }
    }
}
