import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class Main extends JFrame {
    public class App extends JPanel {
        Stage stage;

        /**
         * Constructor
         */
        public App() {
            setPreferredSize(new Dimension(900,720));
            stage = new Stage();
        }

        /**
         * Paint the {@code App}
         *
         * @param g where to paint
         */
        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }
    }

    public static void main(String[] args) {
        Main window = new Main();
        window.run();
    }

    /**
     * Constructor
     */
    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Repeatedly paint
     */
    public void run() {
        while (true) {
            this.repaint();
        }
    }
}
