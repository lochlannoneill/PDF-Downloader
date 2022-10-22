import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class Download implements Runnable {
    
    private String link;
    private File out;
    private String fileType;
    private static String percent;
    private ProgressBar downloadProgressbar;
    private Label downloadProgressDescription;

    public Download (String link, File out, ProgressBar downloadProgressbar, Label downloadProgressDescription) {
        this.link = link;
        this.out = out;
        this.downloadProgressbar = downloadProgressbar;
        this.downloadProgressDescription = downloadProgressDescription;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            double fileSize = (double)http.getContentLength();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(this.out);
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            double downloaded = 0.00;
            int read = 0;
            double percentDownloaded = 0.00;
            while((read = in.read(buffer, 0, 1024)) >= 0) {
                bout.write(buffer, 0, read);
                downloaded += read;

                percentDownloaded = (downloaded*100)/fileSize;
                String percent = String.format("%.4f", percentDownloaded);
                // TODO - downloadProgressDescription.setText(percent + "% of file downloaded");
                downloadProgressbar.setProgress(percentDownloaded);
                System.out.println("Downloaded " + percent + "% of file downloaded");
            }
            bout.close();
            in.close();
            System.out.println("Download completed");
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
