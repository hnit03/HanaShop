package nhinh.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.Part;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 */
public class Utils {

    public String formatDateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String dateFormat = df.format(date);
        return dateFormat;
    }

    public String formatStringToDate(String strDate) {
        java.sql.Date date = java.sql.Date.valueOf(strDate);
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String dateFormat = df.format(date);
        return dateFormat;
    }

    public String formatDateTimeToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String dateFormat = df.format(date);
        return dateFormat;
    }

    public String formatStringToDateTime(LocalDateTime strDate) {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String dateFormat = df.format(strDate);
        return dateFormat;
    }

    public void storeFile(String path, String fileName, Part part) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            File outputFilePath = new File(path + fileName);
            outputFilePath.createNewFile();
            is = part.getInputStream();
            os = new FileOutputStream(outputFilePath);
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    public String getRandomID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
