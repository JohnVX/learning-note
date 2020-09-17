package list;

import static java.lang.System.out;

public class RunSkipList {
    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<>();
        skipList.add(1, "1");
        skipList.add(10, "10");
        skipList.add(5, "5");
        skipList.add(30, "30");
        skipList.add(70, "70");
        skipList.add(40, "40");
        skipList.add(50, "50");
        skipList.add(60, "60");
        skipList.add(90, "90");
        skipList.add(100, "100");
        skipList.add(200, "200");
        skipList.add(150, "150");
        skipList.delete(60);
        out.println(skipList.get(50));
    }
}
