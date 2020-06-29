/**
 * 字符串匹配方法: KMP 方法
 */
public class KMP {
    public static void main(String[] args) {
        match("aabababdcabababcababc", "abababc");
    }
    public static void match(String string, String pattern){
        int[] T = new int[pattern.length()];
        initT(T, pattern);
        int index = 0, pos = 0;
        while(pos < string.length() && index < pattern.length()){
            if(pattern.charAt(index) == string.charAt(pos)){
                index++;
                pos++;
            }else if(T[index] == 0){
                index = 0;
            }else if(T[index] < 0){
                pos++;
            } else {
                index = T[index];
            }
        }
        if(index >= pattern.length()){
            System.out.println("find pattern in string, index :" + (pos - pattern.length()));
        }else {
            System.out.println("dont't match");
        }
    }
    private static void initT(int[] T, String pattern){
        T[0] = -1;
        T[1] = 0;
        int pos = 2, index = 0;
        while (pos < pattern.length()){
            if(pattern.charAt(pos - 1) == pattern.charAt(index)){
                index++;
                T[pos] = index;
                pos++;
            }else if(index > 0){
                index = T[index];
            }else {
                T[pos] = 0;
                pos++;
            }
        }
    }
}
