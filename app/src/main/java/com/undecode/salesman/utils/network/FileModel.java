package com.undecode.salesman.utils.network;

import android.webkit.MimeTypeMap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileModel
{
    private String name, path;
    private byte[] file;
    private String mime;

    public FileModel(String path)
    {
        this.name = path.substring(path.lastIndexOf("/")+1);
        File f = new File(path);
        int size = (int) f.length();
        this.file = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(f));
            buf.read(this.file, 0, this.file.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.mime = getMimeType(path);
    }

    public String getName()
    {
        return name;
    }

    public byte[] getFile()
    {
        return file;
    }

    public String getMime()
    {
        return mime;
    }

    public static String getMimeType(String url)
    {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }
}
