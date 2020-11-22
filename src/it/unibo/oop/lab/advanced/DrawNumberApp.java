package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final String CONFIG_FILE = "config.yml";
    private int min;
    private int max;
    private int attempts;
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * 
     */
    public DrawNumberApp() {
        this.importConfig(CONFIG_FILE);
        this.model = new DrawNumberImpl(this.min, this.max, this.attempts);
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = this.model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

    private void importConfig(final String configFile) {
        final InputStream in = ClassLoader.getSystemResourceAsStream(configFile);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            this.min = this.lastToken(br);
            this.max = this.lastToken(br);
            this.attempts = this.lastToken(br);
        } catch (IOException e) {
            this.view.displayError(e.getMessage());
        } 
    }

    private int lastToken(final BufferedReader buffer) throws IOException {
        final StringTokenizer tokenizer = new StringTokenizer(buffer.readLine(), ": ");
        tokenizer.nextToken(); //skips the first one, we don't need it.
        return Integer.parseInt(tokenizer.nextToken());
    }
}
