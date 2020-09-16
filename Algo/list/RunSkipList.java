package list;

import static java.lang.System.out;

public class RunSkipList {
    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<>();
        skipList.add(30, "30");
        skipList.add(40, "40");
        skipList.add(50, "50");
        skipList.add(60, "60");
        skipList.add(70, "70");
        skipList.add(90, "90");
        skipList.delete(60);
        out.println(skipList.get(50));
    }
}
