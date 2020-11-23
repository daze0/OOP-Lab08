package it.unibo.oop.lab.advanced;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public final class DrawNumberViewFile implements DrawNumberView {

    private static final String FILE_NAME = "output.txt";
    private File output;

    public void setObserver(final DrawNumberViewObserver observer) {
        /*
         * Output only
         */
    }

    public void start() {
        this.output = new File(FILE_NAME);
    }

    public void numberIncorrect() {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(this.output)))) {
            bw.write("Incorrect Number.. try again\n");
        } catch (FileNotFoundException e) {
            this.displayError(e.getMessage());
        } catch (IOException e2) {
            this.displayError(e2.getMessage());
        }
    }

    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            displayMessage(res.getDescription());
            return;
        case YOU_WON:
            displayMessage(res.getDescription() + ": new game starts..");
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
    }

    public void limitsReached() {
        this.displayMessage("limits reached: You lost");
    }

    public void displayError(final String message) {
        this.displayMessage(message);
    }

    private void displayMessage(final String message) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(this.output)))) {
            bw.write(message);
        } catch (FileNotFoundException e) {
            System.err.println("FileViewError: " + e.getMessage());
        } catch (IOException e2) {
            System.err.println("FileViewError: " + e2.getMessage());
        }
    }

}
