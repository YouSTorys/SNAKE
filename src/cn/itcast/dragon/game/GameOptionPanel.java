package cn.itcast.dragon.game;

import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 游戏的设置项面板
 * 
 * @version 1.0, 01/01/08
 * 
 * @author xiao-mingyuan
 * 
 */
public class GameOptionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonGroup buttonGroup = new ButtonGroup();

	private JFrame frame;

	private final JButton newGameButton = new JButton();

	private final JButton stopGameButton = new JButton();

	private final JButton pauseButton = new JButton();

	private final JCheckBox checkBox_drawGridding = new JCheckBox();

	private final JRadioButton radioButton_map1;
	private final JRadioButton radioButton_map2;
	private final JRadioButton radioButton_map3;
	
	private final JButton button_griddingColor;
	private final JButton button_backgroundColor;
	private final JButton button_food;
	private final JButton button_dragonSet;
	private final JButton button_gift;
	private final JButton button_dragon1;
	private final JButton button_dragon2;
	private final JButton button_head;
	private final JButton button_body;
	private final JButton button_bodyL;
	private final JButton button_tail;
	private final JButton button_map;
	
	private final JButton button_default;
	private final JPanel dragonPanel;

	/**
	 * Create the panel
	 */
	public GameOptionPanel() {
		super();
		setSize(450, 185);
		setLayout(null);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setFocusable(false);

		final JSeparator separator = new JSeparator();
		separator.setBounds(140, 55, 156, 50);
		add(separator);

		button_griddingColor = new JButton();
		button_griddingColor.setBounds(85, 10, 60, 23);
		separator.add(button_griddingColor);

		button_griddingColor.setFont(new Font("宋体", Font.PLAIN, 12));
		button_griddingColor.setFocusable(false);
		button_griddingColor.setText("颜色");

		button_griddingColor.setVisible(false);

		checkBox_drawGridding.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				button_griddingColor.setVisible(checkBox_drawGridding
						.isSelected());
			}
		});
		checkBox_drawGridding.setBounds(5, 10, 78, 23);
		separator.add(checkBox_drawGridding);

		checkBox_drawGridding.setText("显示网格");
		checkBox_drawGridding.setFont(new Font("宋体", Font.PLAIN, 12));

		final JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(10, 55, 119, 33);
		add(separator_6);

		button_backgroundColor = new JButton();
		button_backgroundColor.setBounds(5, 10, 110, 23);
		separator_6.add(button_backgroundColor);

		button_backgroundColor.setFont(new Font("宋体", Font.PLAIN, 12));
		button_backgroundColor.setFocusable(false);
		button_backgroundColor.setText("设置背景颜色");

		final JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 135, 286, 39);
		add(separator_4);

		button_food = new JButton();
		button_food.setBounds(5, 10, 60, 23);
		separator_4.add(button_food);

		button_food.setFont(new Font("宋体", Font.PLAIN, 12));
		button_food.setFocusable(false);
		button_food.setText("食物");

		button_map = new JButton();
		button_map.setBounds(220, 10, 60, 23);
		separator_4.add(button_map);

		button_map.setFont(new Font("宋体", Font.PLAIN, 12));
		button_map.setFocusable(false);
		button_map.setText("自设");

		radioButton_map3 = new JRadioButton();
		radioButton_map3.setFont(new Font("宋体", Font.PLAIN, 12));
		radioButton_map3.setSelected(true);
		buttonGroup.add(radioButton_map3);
		radioButton_map3.setText("");
		radioButton_map3.setBounds(198, 10, 20, 23);
		separator_4.add(radioButton_map3);

		radioButton_map1 = new JRadioButton();
		radioButton_map1.setFont(new Font("宋体", Font.PLAIN, 12));
		radioButton_map1.setSelected(true);
		buttonGroup.add(radioButton_map1);
		radioButton_map1.setText("地图1");
		radioButton_map1.setBounds(75, 10, 63, 23);
		separator_4.add(radioButton_map1);

		radioButton_map2 = new JRadioButton();
		radioButton_map2.setFont(new Font("宋体", Font.PLAIN, 12));
		buttonGroup.add(radioButton_map2);
		radioButton_map2.setText("地图2");
		radioButton_map2.setBounds(135, 10, 63, 23);
		separator_4.add(radioButton_map2);

		final JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 95, 286, 39);
		add(separator_1);

		button_dragonSet = new JButton();
		button_dragonSet.setBounds(5, 10, 120, 23);
		separator_1.add(button_dragonSet);

		button_dragonSet.setFont(new Font("宋体", Font.PLAIN, 12));
		button_dragonSet.setFocusable(false);
		button_dragonSet.setText("小龙设置");

		button_gift = new JButton();
		button_gift.setBounds(135, 10, 120, 23);
		separator_1.add(button_gift);

		button_gift.setFont(new Font("宋体", Font.PLAIN, 12));
		button_gift.setFocusable(false);
		button_gift.setText("随机奖励");
		
		dragonPanel = new JPanel();
		
		
		
		button_dragon1 = new JButton();
		button_dragon1.setBounds(5, 60, 60, 23);
		dragonPanel.add(button_dragon1);

		button_dragon1.setFont(new Font("宋体", Font.PLAIN, 12));
		button_dragon1.setFocusable(false);
		button_dragon1.setText("1号皮");
		
		button_dragon2 = new JButton();
		button_dragon2.setBounds(145, 60, 60, 23);
		dragonPanel.add(button_dragon2);

		button_dragon2.setFont(new Font("宋体", Font.PLAIN, 12));
		button_dragon2.setFocusable(false);
		button_dragon2.setText("2号皮");
		
		button_head = new JButton();
		button_head.setBounds(5, 10, 60, 23);
		dragonPanel.add(button_head);

		button_head.setFont(new Font("宋体", Font.PLAIN, 12));
		button_head.setFocusable(false);
		button_head.setText("龙头");
		
		button_body = new JButton();
		button_body.setBounds(75, 10, 60, 23);
		dragonPanel.add(button_body);

		button_body.setFont(new Font("宋体", Font.PLAIN, 12));
		button_body.setFocusable(false);
		button_body.setText("直身");

		button_bodyL = new JButton();
		button_bodyL.setBounds(145, 10, 60, 23);
		dragonPanel.add(button_bodyL);

		button_bodyL.setFont(new Font("宋体", Font.PLAIN, 12));
		button_bodyL.setFocusable(false);
		button_bodyL.setText("L身");

		button_tail = new JButton();
		button_tail.setBounds(215, 10, 60, 23);
		dragonPanel.add(button_tail);

		button_tail.setFont(new Font("宋体", Font.PLAIN, 12));
		button_tail.setFocusable(false);
		button_tail.setText("龙尾");
		

		final JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(302, 10, 140, 165);
		add(separator_2);
		
		final JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 70, 125, 95);
		separator_2.add(separator_5);

		stopGameButton.setText("停止游戏");

		stopGameButton.setBounds(11, 10, 101, 23);
		separator_5.add(stopGameButton);
		stopGameButton.setFont(new Font("宋体", Font.PLAIN, 12));
		stopGameButton.setFocusable(false);

		pauseButton.setBounds(10, 40, 101, 23);
		separator_5.add(pauseButton);
		pauseButton.setText("暂停/继续");
		pauseButton.setFont(new Font("宋体", Font.PLAIN, 12));
		pauseButton.setFocusable(false);

		newGameButton.setFont(new Font("宋体", Font.PLAIN, 12));
		newGameButton.setBounds(11, 70, 101, 23);
		separator_5.add(newGameButton);
		newGameButton.setFocusable(false);
		newGameButton.setText("开始新游戏");

		final JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 20, 286, 34);
		add(separator_3);
		
		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("宋体", Font.PLAIN, 12));
		label_2.setText("选项：");
		label_2.setBounds(10, 10, 60, 15);
		separator_3.add(label_2);

		button_default = new JButton();
		button_default.setText("恢复默认设置");
		button_default.setFont(new Font("宋体", Font.PLAIN, 12));
		button_default.setBounds(139, 6, 137, 23);
		button_default.setFocusable(false);
		separator_3.add(button_default);
		
	}
	
	

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JCheckBox getCheckBox_drawGridding() {
		return checkBox_drawGridding;
	}

	public JButton getNewGameButton() {
		return newGameButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public JButton getStopGameButton() {
		return stopGameButton;
	}

	public JButton getButton_griddingColor() {
		return button_griddingColor;
	}

	public JButton getButton_backgroundColor() {
		return button_backgroundColor;
	}

	public JButton getButton_food() {
		return button_food;
	}
	
	public JButton getButton_gift() {
		return button_gift;
	}
	
	public JButton getButton_dragon1() {
		return button_dragon1;
	}
	
	public JButton getButton_dragon2() {
		return button_dragon2;
	}
	
	public JButton getButton_dragonSet() {
		return button_dragonSet;
	}
	
	public JPanel getDragonPanel() {
		return dragonPanel;
	}

	public JButton getButton_head() {
		return button_head;
	}

	public JButton getButton_body() {
		return button_body;
	}

	public JButton getButton_bodyL() {
		return button_bodyL;
	}

	public JButton getButton_tail() {
		return button_tail;
	}
	
	public JButton getButton_map() {
		return button_map;
	}
	
	public JRadioButton getRadioButton_map3() {
		return radioButton_map3;
	}

	public JRadioButton getRadioButton_map2() {
		return radioButton_map2;
	}

	public JRadioButton getRadioButton_map1() {
		return radioButton_map1;
	}

	public JButton getButton_default() {
		return button_default;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

}
