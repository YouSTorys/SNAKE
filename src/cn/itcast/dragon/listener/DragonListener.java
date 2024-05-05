package cn.itcast.dragon.listener;

/**
 * 
 * 蛇的监听器
 * 
 */
public interface DragonListener {
	/**
	 * 蛇移动事件
	 */
	void dragonMoved();

	/**
	 * 蛇吃到食物事件
	 */
	void dragonEatFood();
}
