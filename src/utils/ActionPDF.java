package utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ActionPDF {
    public void openFile(String filePath){
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filePath);
                p.waitFor();

            }
        }catch (Exception e){

        }
    }
    public void closeFile() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "taskkill/IM wps.exe");
        builder.redirectErrorStream(true);
        Process p = builder.start();
    }
}
