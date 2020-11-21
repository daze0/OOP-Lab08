package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    private static final String SEP = System.getProperty("file.separator");
    private File currentFile;
    /**
     * CONSTRUCTOR.
     * 
     * By default, when a {@link Controller} object is 
     * created {@link this#currentFile} is output.txt
     */
    public Controller() {
        this.currentFile = new File(System.getProperty("user.home") + SEP + "output.txt");
    }
    /**
     * This method sets a given {@link File} as 
     * {@link this#currentFile}.
     * 
     * @param file
     *     {@link File} to be set as current
     */
    public final void setCurrentFile(final File file) {
        this.currentFile = file;
    }
    /**
     * 
     * @return {@link this#currentFile}
     */
    public final File getCurrentFile() {
        return this.currentFile;
    }
    /**
     * @return a {@link String} 
     *     that contains the path of {@link this#currentFile}
     *     example: using the default file would be
     *     (Unix) ==> "user/home/output.txt"
     */
    public final String getCurrentFilePath() {
        return this.currentFile.getPath();
    }
    /**
     * This method saves a given String, using serialization,
     * into {@link this#currentFile}.
     *
     * @param inputString
     *     this {@link String} is written in {@link this#currentFile}
     *     in order to save it
     * @throws {@link IOException}
     */
    public final void saveInCurrentFile(final String inputString) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(this.currentFile))) {
            oos.writeObject(inputString);
        }
    }
}
