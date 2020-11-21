package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    /**
     * FIELDS.
     * 
     * {@link PROPORTION} indicates the proportion of the windows
     *     in relation to the monitor
     * {@link this#frame} is the frame of the GUI
     * {@link this#controller} is the controller(MVC)
     */
    private static final int PROPORTION = 2;
    private final JFrame frame = new JFrame();
    private final Controller controller = new Controller();
    /**
     * builds a new {@link SimpleGUIWithFileChooser}.
     */
    public SimpleGUIWithFileChooser() {
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * Sets window title and default close operation:
         * when the window is closed the program ends.
         */
        frame.setTitle("My first Java graphical interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Create the main JPanel with BorderLayout
         */
        final JPanel mainPanel = new JPanel(new BorderLayout());
        frame.setContentPane(mainPanel);
        /*
         * Adding the JTextArea and the "save" JButton
         */
        final JTextArea textArea = new JTextArea();
        final JButton saveButton = new JButton("Save");
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(saveButton, BorderLayout.SOUTH);
        /*
         * Create the NORTH JPanel with BorderLayout
         * along with the JTextField containing the current selected file
         * and the "Browse..." JButton
         */
        final JPanel northPanel = new JPanel(new BorderLayout());
        final JTextField textField = new JTextField();
        updateSelectedFile(textField);
        textField.setEditable(false);
        final JButton browseButton = new JButton("Browse...");
        northPanel.add(textField, BorderLayout.CENTER);
        northPanel.add(browseButton, BorderLayout.LINE_END);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        /*
         * Handlers
         */
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.saveInCurrentFile(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                final int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(fileChooser.getSelectedFile());
                    updateSelectedFile(textField);
                } else if (result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, "We're sorry, an error occured..", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        /*
         * Least but not last
         */
        frame.setVisible(true);
    }
    private void updateSelectedFile(final JTextField textFieldToUpdate) {
        textFieldToUpdate.setText(controller.getCurrentFilePath());
    }
    /**
     * starts the {@link SimpleGUIWithFileChooser}.
     * @param args
     *     ignored
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser();
    }
}
