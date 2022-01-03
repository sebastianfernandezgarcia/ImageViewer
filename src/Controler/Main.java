package Controler;

import java.io.File;
import Model.Image;
import Persistence.FileImageLoader;

public class Main {
    public static void main(String[] args) {
        File file = new File("images");
        FileImageLoader imageLoader = new FileImageLoader(file);
        Image image = imageLoader.load();
        MainFrame mainFrame = new MainFrame(imageLoader);
        mainFrame.getImageDisplay().show(image);
    }
}
