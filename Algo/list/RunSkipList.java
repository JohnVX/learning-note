package list;

import static java.lang.System.out;

public class RunSkipList {
    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<>();
        skipList.add(30, "");
        skipList.add(29, "");
        skipList.add(28, "");
        skipList.add(27, "");
        skipList.add(26, "");
        skipList.add(25, "");
        skipList.add(24, "");
        skipList.add(23, "");
        skipList.add(22, "");
        skipList.add(21, "");
        skipList.add(20, "");
        skipList.add(19, "");
        skipList.add(1, "");
        skipList.add(2, "");
        skipList.add(3, "");
        skipList.add(4, "");
        skipList.add(5, "");
        skipList.add(6, "");
        skipList.add(7, "");
        skipList.add(8, "");
        skipList.add(9, "f");
        skipList.add(10, "");
        skipList.add(12, "");
        skipList.add(11, "");
        skipList.add(13, "");
        skipList.add(14, "");
        skipList.add(16, "");
        skipList.add(15, "");
        skipList.add(18, "");
        skipList.add(17, "");
        skipList.delete(60);
        out.println(skipList.get(9));
    }
}
