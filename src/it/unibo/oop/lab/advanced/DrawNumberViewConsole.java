package it.unibo.oop.lab.advanced;

public final class DrawNumberViewConsole implements DrawNumberView {

    public void setObserver(final DrawNumberViewObserver observer) {
        /*
         * Output only
         */
    }

    public void start() {
        System.out.println("Start game");
    }

    public void numberIncorrect() {
        System.out.println("Incorrect Number.. try again");
    }

    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            System.out.println(res.getDescription());
            return;
        case YOU_WON:
            System.out.println(res.getDescription() + ": new game starts..");
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
    }

    @Override
    public void limitsReached() {
        System.out.println("limits reached: You lost");
    }

    @Override
    public void displayError(final String message) {
        System.out.println(message);
    }

}
