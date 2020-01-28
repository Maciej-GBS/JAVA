import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BouncingBallsPanel extends JPanel {
    java.util.List<Ball> balls = new ArrayList<>();
    AnimationThread animationEngine = new AnimationThread();
    boolean stopFlag = true;
    int width, height;

    static class Ball {
        int x;
        int y;
        double vx;
        double vy;
        Color color;

        public void move(long t) {
            double time = t / 100.0;
            x += vx * time;
            y += vy * time;
        }

        public void bounce(int w, int h) {
            if (x < 0 || x > w)
                vx = -vx;
            if (y < 0 || y > h)
                vy = -vy;
        }

        public void impulse(Ball other) {
            if (this == other)
                return;
            double dx = x - other.x, dy = y - other.y;
            if (dx + dy > 20)
                return;
            double dst = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2)),
                    nx = dx / dst, ny = dy / dst,
                    dvx = vx - other.vx, dvy = vy - other.vy,
                    strength = dvx * nx + dvy * ny;
            double[] impulse = {nx * strength, ny * strength};
            vx -= impulse[0];
            other.vx += impulse[0];
            vy -= impulse[1];
            other.vy += impulse[1];
        }

        public void paint(Graphics2D g2d) {
            g2d.setColor(color);
            g2d.fillOval(x-5, y-5, 10, 10);
        }
    }

    class AnimationThread extends Thread{
        static final long timeStep = 50;
        @Override
        public void run(){
            for(;;) {
                if (stopFlag)
                    break;
                try {
                    for (Ball b : balls) {
                        b.move(timeStep);
                        b.bounce(width, height);
                        for (Ball other : balls)
                            b.impulse(other);
                    }
                    repaint();
                    sleep(timeStep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    BouncingBallsPanel() {
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        setBackground(new Color(255,255,255));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Ball b : balls)
            b.paint(g2d);
    }

    void onStart() {
        width = getWidth();
        height = getHeight();
        stopFlag = false;
        if (!animationEngine.isAlive()) {
            System.out.println("Start or resume animation thread");
            animationEngine = new AnimationThread();
            animationEngine.start();
        }
    }

    void onStop() {
        System.out.println("Suspend animation thread");
        stopFlag = true;
    }

    void onPlus() {
        System.out.println("Add a ball");
        var b = new Ball();
        b.x = 20;
        b.y = 20;
        b.color = new Color(8, 145,227);
        b.vx = 5;
        b.vy = 8;
        balls.add(b);
    }

    void onMinus() {
        System.out.println("Remove a ball");
        if (balls.size() > 0)
            balls.remove(0);
    }
}