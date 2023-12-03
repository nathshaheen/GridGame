import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.time.Duration;
import java.time.Instant;

public class Main extends JFrame {
    public class App extends JPanel implements MouseListener {
        Stage stage;

        /**
         * Constructor
         */
        public App() {
            setPreferredSize(new Dimension(940,720));
            this.addMouseListener(this);
            stage = StageReader.readStage("data/stage1.rvb");
//            stage = StageReader.readStage("data/test.rvb");   // TESTING
        }

        /**
         * Paint the {@code App}
         *
         * @param graphics where to paint
         */
        @Override
        public void paint(Graphics graphics) {
            stage.paint(graphics, getMousePosition());
        }

        /**
         * Mouse click event listener
         *
         * @param event the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent event) {
            stage.mouseClicked(event.getX(), event.getY());
        }

        @Override
        public void mousePressed(MouseEvent event) {
        }

        @Override
        public void mouseReleased(MouseEvent event) {
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }
    }

    /*
     * START HERE
     */
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
            Instant startTime = Instant.now();
            this.repaint();
            Instant endTime = Instant.now();
            long sleepTime = Duration.between(startTime, endTime).toMillis();
            try {
                Thread.sleep(20L - sleepTime);
            } catch (InterruptedException exception) {
                System.out.println("Thread was interrupted");
            } catch (IllegalArgumentException exception) {
                System.out.println("Application can't keep up with the framerate");
            }
        }
    }
}
