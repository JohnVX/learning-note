package files;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * 一个可配置的产生批量即时时间戳日志内容文件的小工具:
 * 能指定生成的测试数据文件放在哪，生成多少个文件、文件内要几行日志，每行的时间是即时更新的
 *
 * 使用举例:
 * java -DworkingDir="/usr/local/src/" -DdebugMode=false -Dfiles=10000 -Dlines=30 files/GenerateBatchFiles
 *
 * author: wangsw
 */
public class GenerateBatchFiles {
    private static boolean debugMode = false;
    private static String workingDir = "C:\\Users\\wangsw\\Desktop";
    private static int lines = 30;
    private static int files = 1000;
    private static final String filePrefix = "access.log";
    private static final String dirPrefix = "generatedFiles";
    public static void main(String[] args) throws Exception{
        setArgumentIfNeeded();
        String dir = workingDir + File.separator + dirPrefix;
        if(new File(dir).mkdir()){
            if(debugMode) {
                System.out.println("创建目录 " + dir + " 成功");
            }
        }else {
            throw new Exception("无法创建目录 " + dir + ", 没有权限或者目录已经存在?");
        }
        for(int i = 0; i < files; i++){
            File file;
            String name = workingDir + File.separator + dirPrefix + File.separator + filePrefix + "." + (i+1);
            if((file = new File(name)).createNewFile()){
                if(debugMode) {
                    System.out.println("创建文件 " + name + " 成功");
                }
                FileWriter fileWriter = new FileWriter(file);
                String content = getFileCContent();
                fileWriter.write(content);
                fileWriter.close();
            }else {
                throw new Exception("创建文件 " + name + " 失败, 没有权限或者文件盘满了?");
            }
        }
    }
    //1595298619.559 [21/Jul/2020:10:30:19 +0800] 10.8.237.8 10.8.237.18 10.8.237.3:80 www.perf.com "/small.html" 200 0.001 127 299 "Apache-HttpClient/4.5.10 (Java/1.8.0_121)" - 48 "206" "320" "0.001" "-" "-" - -
    private static String getFileCContent(){
        StringBuilder stringBuilder = new StringBuilder();
        String format = "%s [%s] 10.8.237.8 10.8.237.18 10.8.237.3:80 www.perf.com \"/small.html\" 200 0.001 127 299 \"Apache-HttpClient/4.5.10 (Java/1.8.0_121)\" - 48 \"206\" \"320\" \"0.001\" \"-\" \"-\" - -";
        for(int i = 0; i < GenerateBatchFiles.lines; i++) {
            long time = System.currentTimeMillis();
            String timeString = String.valueOf(time);
            String arg1 = timeString.substring(0, timeString.length() - 3) + "." + timeString.substring(timeString.length() - 3);
            String arg2 = new SimpleDateFormat("dd/MMM/yyyy:H:m:s +0800", Locale.ENGLISH).format(new Date(time));
            String line = String.format(format, arg1, arg2);
            stringBuilder.append(line);
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
    private static void setArgumentIfNeeded(){
        Properties properties = System.getProperties();
        if(properties.get("debugMode") != null){
            debugMode = Boolean.valueOf((String)properties.get("debugMode"));
        }
        if(properties.get("workingDir") != null){
            workingDir = String.valueOf(properties.get("workingDir"));
        }
        if(properties.get("files") != null){
            files = Integer.valueOf((String)properties.get("files"));
        }
        if(properties.get("lines") != null){
            lines = Integer.valueOf((String)properties.get("lines"));
        }
    }
}
