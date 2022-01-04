package Persistence;

import Model.Image;
import Model.RealImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class FileImageLoader implements ImageLoader {
    
    private final File[] files;
    private int current;
    
    
    public FileImageLoader(File folder) {
        this.current = 0;
        this.files = folder.listFiles(imageTypes());
    }
    
    /**
    private FileFilter imageTypes() {
        return new FileFilter() {
    
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".jpg");
        }
        };
    }
    **/
    
        private FileFilter imageTypes() {
            return (File pathname) -> pathname.getName().endsWith(".jpg");
        }
    
        @Override
        public Image load() {
            return new RealImage(
                    files[current].getName(),
                    this.getStream(files[current])    
        );
    }
        
    private InputStream getStream(File file) {
        try {
            return new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public Image next() {
        if (this.current ==  this.files.length -1){
            this.current = 0;
        } else {
        this.current++;
        }
        return this.load();
        
    }

    @Override
    public Image prev() {
        if (this.current == 0){
            this.current = this.files.length - 1;
        } else {
            this.current--;
        }
        
        return this.load();
    }
}

