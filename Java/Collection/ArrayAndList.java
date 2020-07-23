import java.util.Arrays;
import java.util.List;

public class ArrayAndList {
    public static void main(String[] args) {
        Integer[] ages = new Integer[]{2,4,5};
        List<Integer> list = Arrays.asList(ages);
        Integer[] newArr = (Integer[]) list.toArray();
        System.out.println(Arrays.toString(newArr));
    }
}
