import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
         * @param g where to paint
         */
        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }

        /**
         * Mouse click event listener
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            stage.mouseClicked(e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
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
            long howLong = Duration.between(startTime, endTime).toMillis();
            try {
                Thread.sleep(20L - howLong);
            } catch (InterruptedException e) {
                System.out.println("Thread was interupted");
            } catch (IllegalArgumentException e) {
                System.out.println("Application can't keep up with the framerate");
            }
        }
    }
}
