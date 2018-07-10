package com.rafl.engine.execution;

public abstract class AbstractGame implements Runnable{

    private boolean running;
    private int currentFps;
    private final double FRAME_TIME;
    private Thread thread;

    public AbstractGame(int fps) {
        FRAME_TIME = 1_000_000_000/fps;
        this.thread = new Thread(this);
    }

    public void start() {
        running = true;
        if(!thread.isAlive()) thread.start();
    }

    public void stop() throws InterruptedException {
        running = false;
        if(thread.isAlive()) thread.join();
    }

    @Override
    public void run() {

        double timer = System.currentTimeMillis();
        double delta = 0;
        double before, now;

        before = System.nanoTime();

        while(running) {

            now = System.nanoTime();
            delta += (now - before)/FRAME_TIME;
            before = now;

            while(delta >= 1) {
                onUpdate(delta);
                currentFps++;
                delta--;
            }
            onRender();

            if(System.currentTimeMillis() - timer >= 1000)
            {
                everySec();
                timer += 1000;
                currentFps = 0;
            }

        }

    }

    protected abstract void onUpdate(double delta);
    protected abstract void onRender();
    protected abstract void everySec();

    public int getCurrentFps() {
        return currentFps;
    }

}
