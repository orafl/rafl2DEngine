package com.rafl.engine.gfx;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Display implements Bitmap{

	private JFrame frame;
	private Canvas canvas;
	private final Dimension defaultSize;
	private List<Drawable> drawRequests;
	private boolean fullscreen;
	private BufferedImage screen;
	private int[] raster;

	public Display(String title, int width, int height) {
		this.frame = new JFrame(title);
		this.canvas = new Canvas();
		this.defaultSize = new Dimension(width, height);
		this.drawRequests = new ArrayList<>();
		setSize(width, height);
		createRaster();
	}

	public Display(int width, int height) {
		this("", width, height);
	}

	public Display(String title, int width) {
		this(title, width, rationalHeight(width));
	}

	public Display(int width) {
		this("", width);
	}

	private static final int rationalHeight(int width) {

		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension screenSize = t.getScreenSize();
		double ow = screenSize.getWidth(),
				oh = screenSize.getHeight();

		return (int) ((oh/ow)*width);
	}

	private void createRaster() {
		screen = new BufferedImage(getWidth(), getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		raster = ((DataBufferInt)screen.getRaster().getDataBuffer())
				.getData();
	}

	public void createView() {
		if(frame.isShowing()) return;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	public void setSize(int width, int height) {
		canvas.setSize(width, height);
		frame.pack();
	}

	public void setSize(Dimension d) {
		setSize(d.width, d.height);
	}

	public void makeFullscreen() {
		frame.dispose();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.add(canvas);
		setSize(frame.getSize());
		fullscreen = true;
		createRaster();
	}

	public void makeWindowed() {
		frame.dispose();
		frame.setUndecorated(false);
		frame.setExtendedState(JFrame.NORMAL);
		frame.setSize(defaultSize.width, defaultSize.height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void show() {

		if(!canvas.isShowing()) return;

		BufferStrategy bs = canvas.getBufferStrategy();

		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);

		for (Drawable d : drawRequests) d.draw(g);

		bs.show();
		g.dispose();

	}

	@Override
	public int getWidth() {
		return canvas.getWidth();
	}

	@Override
	public int getHeight() {
		return canvas.getHeight();
	}
	
	@Override
	public int getPixel(int x, int y) {
		return raster[x + y * getWidth()];
	}
	
	@Override
	public void setPixel(int x, int y, int pixel) {
		raster[x + y * getWidth()] = pixel;
	}
	
	@Override
	public int[] getRaster() {
		return raster;
	}
	
	public void setPixel(int i,int pixel) {
		raster[i] = pixel;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void addKeyListener(KeyListener l) {
		canvas.addKeyListener(l);
	}

	public void addMouseListener(MouseListener l) {
		canvas.addMouseListener(l);
	}

	public void addMouseMotionListener(MouseMotionListener l) {
		canvas.addMouseMotionListener(l);
	}

	public void addMouseWheelListener(MouseWheelListener l) {
		canvas.addMouseWheelListener(l);
	}

	public void requestFocus() {
		canvas.requestFocus();
	}

	public void draw(Drawable d) {
		this.drawRequests.add(d);
	}

	public void clear() {
		drawRequests.clear();
	}

}
