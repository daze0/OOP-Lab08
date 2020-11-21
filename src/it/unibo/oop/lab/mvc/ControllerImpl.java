package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private String current;
    private String next;
    private final List<String> history;
    /**
     * CONSTRUCTOR.
     */
    public ControllerImpl() {
        this.current = "";
        this.next = "";
        this.history = new ArrayList<>();
    }
    /**
     * {@inheritDoc}
     */
    public void setNext(final String next) {
        this.current = this.next;
        this.next = next;
        this.history.add(next);
    }
    /**
     * {@inheritDoc}
     */
    public String getNext() {
        return this.next;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getHistory() {
        return this.history;
    }

    /**
     * {@inheritDoc}
     */
    public String getCurrent() {
        if (this.current.isEmpty()) {
            throw new IllegalStateException();
        }
        return this.current;
    }

}
