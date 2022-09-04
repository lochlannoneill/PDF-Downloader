import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download implements Runnable {
    
    String link;
    File out;
    // public double percentDownloaded;

    // public Download(String location, String link, File out)
    public Download (String link, File out) {
        this.link = link;
        this.out = out;
        // percentDownloaded = 0.00;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
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
                System.out.println("Downloaded " + percent + "% of a file.");
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
