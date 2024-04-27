package cn.itcast.dragon.controller;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cn.itcast.dragon.entities.Food;
import cn.itcast.dragon.entities.Gift;
import cn.itcast.dragon.entities.Ground;
import cn.itcast.dragon.entities.Dragon;
import cn.itcast.dragon.listener.GameListener;
import cn.itcast.dragon.listener.DragonListener;
import cn.itcast.dragon.util.Global;
import cn.itcast.dragon.view.GamePanel;

/**
 * 控制器<BR>
 * 控制Ground, Dragon, Food<BR>
 * 负责游戏的逻辑<BR>
 * 处理按键事件<BR>
 * <BR>
 * 实现了DragonListener接口, 可以处理Dragon 触发的事件<BR>
 * 方法 dragonEatFood() 处理蛇吃到食物后触发的 dragonEatFood事件 但什么也没做<BR>
 * <BR>
 * 
 */
public class Controller extends KeyAdapter implements DragonListener {

	/* 地形 */
	private Ground ground;

	/* 蛇 */
	private Dragon dragon;

	/* 食物 */
	private Food food;

	/* 显示 */
	private GamePanel gamePanel;

	/* 提示信息 */
	private JLabel gameInfoLabel;

	private boolean playing;

	private int map;
	
	private int foodScore=0;
	
	private int giftScore=0;

	/* 控制器监听器 */
	private Set<GameListener> listeners = new HashSet<GameListener>();

	/**
	 * 处理按键事件<BR>
	 * 接受按键, 根据按键不同, 发出不同的指令<BR>
	 * UP: 改变蛇的移动方向为向上<BR>
	 * DOWN: 改变蛇的移动方向为向下<BR>
	 * LEFT: 改变蛇的移动方向为向左 <BR>
	 * RIGHT: 改变蛇的移动方向为向右<BR>
	 * SPACE: 暂停/继续<BR>
	 * PAGE UP: 加快蛇的移动速度<BR>
	 * PAGE DOWN: 减慢蛇的移动速度<BR>
	 * Y: 重新开始游戏
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_Y && !playing)
			return;
		// TODO Auto-generated method stub
		/* 根据按键不同, 让蛇改变不同的方向 */
		switch (e.getKeyCode()) {

		/* 方向键 上 */
		case KeyEvent.VK_UP:
			if (dragon.isPause()) {
				dragon.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			dragon.changeDirection(Dragon.UP);
			break;
		/* 方向键 下 */
		case KeyEvent.VK_DOWN:
			if (dragon.isPause()) {
				dragon.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			dragon.changeDirection(Dragon.DOWN);
			break;
		/* 方向键 左 */
		case KeyEvent.VK_LEFT:
			if (dragon.isPause()) {
				dragon.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			dragon.changeDirection(Dragon.LEFT);
			break;
		/* 方向键 右 */
		case KeyEvent.VK_RIGHT:
			if (dragon.isPause()) {
				dragon.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			dragon.changeDirection(Dragon.RIGHT);
			break;
			/* 方向键 上 */
		case KeyEvent.VK_W:
			if (dragon.isPause()) {
				dragon.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			dragon.changeDirection(Dragon.UP);
			break;
		/* 方向键 下 */
		case KeyEvent.VK_S:
			if (dragon.isPause()) {
				dragon.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			dragon.changeDirection(Dragon.DOWN);
			break;
		/* 方向键 左 */
		case KeyEvent.VK_A:
			if (dragon.isPause()) {
				dragon.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			dragon.changeDirection(Dragon.LEFT);
			break;
		/* 方向键 右 */
		case KeyEvent.VK_D:
			if (dragon.isPause()) {
				dragon.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			dragon.changeDirection(Dragon.RIGHT);
			break;
		/* 回车或空格 (暂停) */
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			dragon.changePause();
			/* === */
			for (GameListener l : listeners)
				if (dragon.isPause())
					l.gamePause();
				else
					l.gameContinue();
			break;
		/* PAGE_UP 加速 */
		case KeyEvent.VK_PAGE_UP:
			dragon.speedUp();
			break;
		/* PAGE_DOWN 减速 */
		case KeyEvent.VK_PAGE_DOWN:
			dragon.speedDown();
			break;
		/* 字母键 Y (重新开始游戏) */
		case KeyEvent.VK_Y:
			if (!isPlaying())
				newGame();
			break;
		}

		/* 重新显示 */
		if (gamePanel != null)
			gamePanel.redisplay(ground,dragon, food);
		/* 更新提示 */
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}

	/**
	 * 处理dragon 触发的 dragonMoved 事件<BR>
	 */
	public void dragonMoved() {

		/* 判断是否吃到食物 */
		if (food != null && food.isDragonEatFood(dragon)) {
			/* 吃到食物后, 蛇增加身体, 再重新丢一个食物 */
			dragon.eatFood();
			food.setLocation(ground == null ? food.getNew() : ground
					.getFreePoint());

		}/* 如果吃到食物, 就肯定不会吃到石头 */
		else if (ground != null && ground.isDragonEatRock(dragon)) {
			/* 如果吃到的是石头, 或吃到自己的身体, 就让蛇死掉 */
			stopGame();
		}
		if (dragon.isEatBody())
			stopGame();
		if (gamePanel != null)
			gamePanel.redisplay(ground, dragon, food);
		/* 更新提示 */
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}

	/**
	 * 开始一个新游戏
	 */
	public void newGame() {
		foodScore = 0;
		giftScore = 0;
		if (ground != null) {
			switch (map) {
			case 1:
				ground.init();
				break;
			case 2:
				ground.clear();
				ground.generateRocks2();
				break;
			case 3:
				ground.diyStart();
				break;
			default:
				break;
			}
		}
		playing = true;

		dragon.reNew();
		for (GameListener l : listeners)
			l.gameStart();
	}

	/**
	 * 结束游戏
	 */
	public void stopGame() {
		if (playing) {
			playing = false;
			dragon.dead();
			for (GameListener l : listeners)
				l.gameOver();
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	int score = foodScore+giftScore;
	                JOptionPane.showMessageDialog(null, "得分："+score, "游戏结束！", JOptionPane.INFORMATION_MESSAGE);
	            }
	        });
			}
	}

	/**
	 * 暂停游戏
	 */
	public void pauseGame() {
		dragon.setPause(true);
		for (GameListener l : listeners)
			l.gamePause();
	}

	/**
	 * 继续游戏
	 */
	public void continueGame() {
		dragon.setPause(false);
		for (GameListener l : listeners)
			l.gameContinue();
	}

	/**
	 * 接受Dragon,Food,Ground 的构造器<BR>
	 * 
	 * @param dragon
	 * @param food
	 * @param ground
	 */
	public Controller(Dragon dragon, Food food, Ground ground, GamePanel gamePanel) {
		this.dragon = dragon;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
		/* 先丢一个食物 */
		if (ground != null && food != null)
			food.setLocation(ground.getFreePoint());
		/* 注册监听器 */
		this.dragon.addDragonListener(this);
	}

	/**
	 * 多接受一个显示提示信息的JLabel
	 * 
	 * @param dragon
	 * @param food
	 * @param ground
	 * @param gameInfoLabel
	 */
	public Controller(Dragon dragon, Food food, Ground ground,
			GamePanel gamePanel, JLabel gameInfoLabel) {

		this(dragon, food, ground, gamePanel);
		this.setGameInfoLabel(gameInfoLabel);

		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}

	/**
	 * 得到最新的提示信息
	 * 
	 * @return 蛇的最新信息
	 */
	public String getNewInfo() {
		if (!dragon.isLive())
			return " ";// " 提示: 按 Y 开始新游戏";
		else
			return new StringBuffer().append("提示: ").append("速度 ").append(
					dragon.getSpeed()+ " 毫秒/格").append("              得分：").append(""+foodScore)
					.append("+").append(giftScore).toString();
	}

	/**
	 * 添加监听器
	 * 
	 * @param l
	 */
	public synchronized void addGameListener(GameListener l) {
		if (l != null)
			this.listeners.add(l);
	}

	/**
	 * 移除监听器
	 * 
	 * @param l
	 */
	public synchronized void removeGameListener(GameListener l) {
		if (l != null)
			this.listeners.remove(l);
	}

	/**
	 * 得到蛇的引用
	 * 
	 * @return
	 */
	public Dragon getDragon() {
		return this.dragon;
	}

	/**
	 * 得到食物的引用
	 * 
	 * @return
	 */
	public Food getFood() {
		return this.food;
	}

	/**
	 * 得到地形的引用
	 * 
	 * @return
	 */
	public Ground getGround() {
		return this.ground;
	}

	/**
	 * 处理蛇吃到食物后触发的 dragonEatFood事件, 但什么也没做<BR>
	 * 可以覆盖这个方法增加功能, 例如, 增加记分功能
	 */
	public void dragonEatFood() {
		// TODO Auto-generated method stub
		foodScore++;
		System.out.println("吃到食物!");
		if (foodScore%5==0 && !Gift.getList().isEmpty()) {
			giftScore +=raffle().getScore();
		}
	}
	
	/**
	 * 随机抽取奖品
	 *
	 */
	public Gift raffle() {
		int all = 0;
		if(!Gift.getList().isEmpty()) {
		for(Gift gift:Gift.getList()) {
			all += gift.getWeight();
		}
		int x = new Random().nextInt(all)+1;
		for(Gift gift:Gift.getList()) {
			x = x - gift.getWeight();
			if(x <= 0) {
				return gift;
			}
		}
		}
		return null;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * 设置GamePanel
	 * 
	 * @param gamePanel
	 */
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JLabel getGameInfoLabel() {
		return gameInfoLabel;
	}

	public void setGameInfoLabel(JLabel gameInfoLabel) {
		this.gameInfoLabel = gameInfoLabel;
		this.gameInfoLabel.setSize(Global.WIDTH * Global.CELL_WIDTH, 20);
		this.gameInfoLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		gameInfoLabel.setText(this.getNewInfo());
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}

	public void setDragon(Dragon dragon) {
		this.dragon = dragon;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	public  int getScore() {
		return foodScore;
	}

	public  void setScore(int score) {
		this.foodScore = score;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean isPausingGame() {
		// TODO Auto-generated method stub
		return dragon.isPause();
	}

}
