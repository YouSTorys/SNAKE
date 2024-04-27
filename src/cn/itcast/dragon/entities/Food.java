package cn.itcast.dragon.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import cn.itcast.dragon.util.Global;

/**
 * 
 * ʳ��, ��x , y ���� �� ͼƬ������<BR>
 * ������setImage() �ı�ʳ���ͼƬ<BR>
 * Ҳ����ͨ������ drawFood(Graphics, int, int, int, int) ���� �ı�ʳ�����ʾ��ʽ<BR>
 * 
 */
public class Food extends Point {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* ʳ���ͼƬ */
	private ImageIcon image ;

   
    

	private Random random = new Random();

	/**
	 * Ĭ�ϵĹ�����, ����(0,0)������
	 */
	public Food() {
		
		super();
	}

	public Point getNew() {
		Point p = new Point();
		p.x = random.nextInt(Global.WIDTH);
		p.y = random.nextInt(Global.HEIGHT);
		return p;
	}

	/**
	 * ��ʼ�������ָ��������ͬ�Ĺ�����
	 * 
	 * @param x
	 * @param y
	 */
	public Food(Point p) {
		super(p);
	}

	/**
	 * ���Ƿ�Ե���ʳ��
	 * 
	 * @param p
	 * @return
	 */
	public boolean isDragonEatFood(Dragon dragon) {
		return this.equals(dragon.getHead());
	}

	/**
	 * ���Լ�, ������ drawFood(Graphics, int, int, int, int) ����
	 * 
	 * @param g
	 */

	public void drawMe(Graphics g) {
		
		
		drawFood(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT,
				Global.CELL_WIDTH, Global.CELL_HEIGHT);
	}

	/**
	 * ��ʳ��, ���Ը�����������ı�ʳ�����ʾ
	 * 
	 * @param g
	 * @param x
	 *            �������� x
	 * @param y
	 *            �������� y
	 * @param width
	 *            ���(��λ:����)
	 * @param height
	 *            �߶�(��λ:����)
	 */
	public void drawFood(Graphics g, int x, int y, int width, int height) {
		//g.fill3DRect(x, y, width, height, true);
		
		if (image == null) {
		    try (InputStream inputStream = getClass().getResourceAsStream("/img/apple.png")) {
		        if (inputStream != null) {
		            BufferedImage ima = ImageIO.read(inputStream);
		            image = new ImageIcon(ima);
		        }
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		}
		g.drawImage(image.getImage(), x, y, width, height, null);
	}

	/**
	 * �õ�ʳ���ͼƬ
	 * 
	 * @return
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * ����ʳ���ͼƬ
	 * 
	 * @param color
	 */
	public void setImage(ImageIcon image) {
		this.image = image;
	}

}
