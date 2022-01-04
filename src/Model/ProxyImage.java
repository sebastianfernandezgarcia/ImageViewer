package Model;

import Util.AccessCounter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProxyImage implements Image {
    
    private final Image realImage;

    public ProxyImage(File file) {
        this.realImage = new RealImage(file.getName(), this.getStream(file));
    }
    
    private InputStream getStream(File file) {
        try {
            return new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public String name() {
        return this.realImage.name();
    }

    @Override
    public InputStream stream() {
        Integer count = AccessCounter.getInstance().increment(this.name());
        System.out.printf("File '%s' accessed '%d' time \n", this.name(), count);
        return this.realImage.stream();
    }
    
}
