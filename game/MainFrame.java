package cn.itcast.dragon.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import cn.itcast.dragon.controller.Controller;
import cn.itcast.dragon.entities.Food;
import cn.itcast.dragon.entities.Gift;
import cn.itcast.dragon.entities.Ground;
import cn.itcast.dragon.entities.Dragon;
import cn.itcast.dragon.listener.GameListener;
import cn.itcast.dragon.util.Global;
import cn.itcast.dragon.view.GamePanel;

/**
 * 主界面, 实现了 GameListener 接口
 * 
 */
public class MainFrame extends JFrame implements GameListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JOptionPane previousOptionPane = null; //保存弹窗

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			MainFrame frame = new MainFrame(new Controller(new Dragon(),
					new Food(), new Ground(), new GamePanel(), new JLabel()));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final GameOptionPanel optionPanel;
	private final GamePanel gamePanel;

	private final Dragon dragon;
	private final Ground ground;
	private final Food food;
	private final JLabel infoLabel;
	private final Controller controller;

	/**
	 * Create the frame
	 */
	public MainFrame(Controller c) {
		super();
		this.controller = c;

		this.setTitle("龙之旅");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);

		int left = 10;
		optionPanel = new GameOptionPanel();

		/** =======* */
		gamePanel = c.getGamePanel();
		dragon = c.getDragon();
		ground = c.getGround();
		food = c.getFood();
		infoLabel = c.getGameInfoLabel() == null ? new JLabel() : c
				.getGameInfoLabel();
		c.setGameInfoLabel(infoLabel);

		optionPanel.getButton_griddingColor().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Color griddingColor = JColorChooser.showDialog(
								MainFrame.this, "请选择网格的颜色", Color.LIGHT_GRAY);
						if (griddingColor != null)
							ground.setGriddingColor(griddingColor);
					}
				});
		optionPanel.getButton_backgroundColor().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Color backgroundColor = JColorChooser
								.showDialog(MainFrame.this, "请选择背景的颜色",
										new Color(0xcfcfcf));
						if (backgroundColor != null)
							gamePanel.setBackgroundColor(backgroundColor);
					}
				});
		optionPanel.getButton_food().addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        
		    	ImageIcon icon =imgChooser();
		        
		        if (icon != null) {
		        	food.setImage(icon);
		        }
		    }
		});
		optionPanel.getButton_dragonSet().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showOptionDialog(null, optionPanel.getDragonPanel(), "请选择修改部位",
				                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				                null, null, null);
					}
				});
		optionPanel.getButton_gift().addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        refreshGiftPanel();
		    }
		});
		optionPanel.getButton_head().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ImageIcon icon =imgChooser();
				        
				        if (icon != null) {
				        	dragon.setHeadIcon(icon);
				        }
							
					}
				});
		optionPanel.getButton_body().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ImageIcon icon =imgChooser();
				        
				        if (icon != null) {
				        	dragon.setBodyIcon(icon);
				        }
					}
				});
		optionPanel.getButton_bodyL().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ImageIcon icon =imgChooser();
				        
				        if (icon != null) {
				        	dragon.setBodyLIcon(icon);
				        }
					}
				});
		optionPanel.getButton_tail().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ImageIcon icon =imgChooser();
				        
				        if (icon != null) {
				        	dragon.setTailIcon(icon);
				        }
					}
				});
		optionPanel.getButton_dragon1().addActionListener(
			new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        try {
		        	dragon.setHeadIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/long.png"))));
		        	dragon.setBodyIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyI.png"))));
		        	dragon.setBodyLIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyL.png"))));
		        	dragon.setTailIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyW.png"))));
		            JOptionPane.getRootFrame().dispose(); // 关闭弹窗
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		optionPanel.getButton_dragon2().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
				        try {
				        	dragon.setHeadIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/head2.png"))));
				        	dragon.setBodyIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyI2.png"))));
				        	dragon.setBodyLIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyL2.png"))));
				        	dragon.setTailIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyW2.png"))));
				            JOptionPane.getRootFrame().dispose(); // 关闭弹窗
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
				    }
				});
		optionPanel.getButton_map().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(optionPanel.getRadioButton_map1().isSelected()||
						optionPanel.getRadioButton_map2().isSelected()) {
							ground.clear();
						}
						optionPanel.getRadioButton_map3().setSelected(true);
						JPanel panel = new JPanel();
						for (int x = 0; x < Global.WIDTH; x++)
							for (int y = 0; y < Global.HEIGHT; y++) {
									JButton button = new JButton();
				                    button.setBounds(x*10, y*10, 10, 10);
				                    if(x== Global.WIDTH/2+1&&y==Global.HEIGHT/2) {
				                    	button.setBackground(Color.red);
										panel.add(button);
				                    	continue;
				                    }
								if (ground.getDiyRock(x,y)) {
				                    button.setBackground(Color.black);
								}else {
									button.setBackground(Color.white);
								}
								button.setOpaque(true);
								 final int finalX = x;
								 final int finalY = y;
								button.addActionListener(e -> {
									ground.addDiyRock(finalX, finalY,!ground.getDiyRock(finalX, finalY));
									  button.setBounds(finalX*10, finalY*10, 10, 10);
										if (ground.getDiyRock(finalX,finalY)) {
						                    button.setBackground(Color.black);
										}else {
											button.setBackground(Color.white);
										}
					            }); 
			                    panel.setLayout(null);
								panel.add(button);
							
							}
						panel.setSize(400, 210);
						panel.setPreferredSize(new Dimension(Global.WIDTH*10, Global.HEIGHT*10));
						JOptionPane.showOptionDialog(null, panel, "地图设置",
				                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				                null, null, null);
			            
			             
					}
				});

		this.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent arg0) {
				controller.pauseGame();
				if (optionPanel.getPauseButton().isEnabled())
					optionPanel.getPauseButton().setText("继续游戏");
			}
		});
		gamePanel.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				// controller.continueGame();
			}

			public void focusLost(FocusEvent arg0) {
				controller.pauseGame();
				if (optionPanel.getPauseButton().isEnabled())
					optionPanel.getPauseButton().setText("继续游戏");
			}
		});

		

		optionPanel.getNewGameButton().addActionListener(new ActionListener() {
			/**
			 * 开始游戏的按钮
			 */
			
			
			public void actionPerformed(ActionEvent e) {

				controller.setMap(optionPanel.getRadioButton_map1()
						.isSelected() ? 1 : (optionPanel.getRadioButton_map2()
						.isSelected() ? 2:3));
				// TODO Auto-generated method stub
				if (controller.isPlaying()) {
					return;
				}
				
				controller.newGame();
			}
		});
		optionPanel.getStopGameButton().addActionListener(new ActionListener() {
			/**
			 * ͣ停止游戏的按钮
			 */
			public void actionPerformed(ActionEvent e) {

				controller.stopGame();
			}
		});
		optionPanel.getPauseButton().setEnabled(false);
		optionPanel.getStopGameButton().setEnabled(false);

		optionPanel.getPauseButton().addActionListener(new ActionListener() {
			/**
			 *暂停/继续游戏的按钮
			 */
			public void actionPerformed(ActionEvent e) {
				if (controller.isPausingGame()) {
					controller.continueGame();

				} else {
					controller.pauseGame();
				}
				if (controller.isPausingGame())
					optionPanel.getPauseButton().setText("继续游戏");
				else
					optionPanel.getPauseButton().setText("暂停游戏");
			}
		});
		optionPanel.getCheckBox_drawGridding().addChangeListener(
				new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						optionPanel.getButton_griddingColor().setVisible(
								optionPanel.getCheckBox_drawGridding()
										.isSelected());
						ground.setDrawGridding(optionPanel
								.getCheckBox_drawGridding().isSelected());
					}
				});

		optionPanel.getButton_default().addActionListener(new ActionListener() {
			/**
			 * 恢复默认设置的按钮
			 */
			
			public void actionPerformed(ActionEvent e) {

				gamePanel.setBackgroundColor(GamePanel.DEFAULT_BACKGROUND_COLOR);
				optionPanel.getCheckBox_drawGridding().setSelected(false);
				ground.setGriddingColor(Ground.DEFAULT_GRIDDING_COLOR);
				dragon.setHeadIcon(null);
				dragon.setBodyLIcon(null);
				dragon.setBodyIcon(null);
				dragon.setTailIcon(null);
				optionPanel.getRadioButton_map1().setSelected(true);

			}
		});

		/** ******************* */

		infoLabel.setBounds(10, 0, infoLabel.getSize().width - 10, infoLabel
				.getSize().height);
		gamePanel.setBounds(0, infoLabel.getSize().height,
				gamePanel.getSize().width, gamePanel.getSize().height);

		/**
		 * subPanel
		 */
		JPanel subPanel = new JPanel();
		subPanel.setLayout(null);
		subPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		subPanel.setFocusable(false);

		subPanel.setSize(gamePanel.getSize().width + 1,
				infoLabel.getSize().height + gamePanel.getSize().height + 1);
		subPanel.setBounds(left, 5, subPanel.getSize().width, subPanel
				.getSize().height);

		subPanel.add(infoLabel);
		subPanel.add(gamePanel);

		optionPanel.setBounds(left, subPanel.getSize().height + 10, optionPanel
				.getSize().width, optionPanel.getSize().height);

		/**
		 * ˵��
		 */
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		infoPanel.setLayout(null);
		infoPanel.setBounds(left + optionPanel.getSize().width + 5, subPanel
				.getSize().height + 10, gamePanel.getSize().width
				- optionPanel.getSize().width - 5 + 1,
				optionPanel.getSize().height);

		final JLabel infoTitleLable = new JLabel();
		infoTitleLable.setFont(new Font("宋体", Font.PLAIN, 12));
		infoTitleLable.setText(Global.TITLE_LABEL_TEXT);
		infoTitleLable.setBounds(10, 5, infoPanel.getSize().width - 10, 20);

		final JTextArea infoTextArea = new JTextArea();
		infoTextArea.setFont(new Font("宋体", Font.PLAIN, 12));
		infoTextArea.setText(Global.INFO_LABEL_TEXT);
		infoTextArea.setFocusable(false);
		infoTextArea.setBackground(this.getBackground());
		infoTextArea.setBounds(10, 25, infoPanel.getSize().width - 20,
				infoPanel.getSize().height - 50);

		infoPanel.add(infoTitleLable);
		infoPanel.add(infoTextArea);

		optionPanel.getCheckBox_drawGridding().setFocusable(false);
		optionPanel.getRadioButton_map1().setFocusable(false);
		optionPanel.getRadioButton_map2().setFocusable(false);
		optionPanel.getRadioButton_map3().setFocusable(false);

		this
				.setSize(
						subPanel.getSize().width > optionPanel.getSize().width ? gamePanel
								.getSize().width
								+ 2 * left + 8
								: optionPanel.getSize().width + 2 * left + 8,
						subPanel.getSize().height + 20/* 边框 */
								+ optionPanel.getSize().height + 30);
		/* 让窗口居中 */
		this.setLocation(this.getToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, this.getToolkit().getScreenSize().height
				/ 2 - this.getHeight() / 2);

		/* 添加监听器 */
		gamePanel.addKeyListener(controller);
		this.addKeyListener(controller);
		controller.addGameListener(this);

		this.getContentPane().add(subPanel);
		this.getContentPane().add(optionPanel);
		this.getContentPane().add(infoPanel);
	}
	
	

	public void refreshGiftPanel() {
		if (previousOptionPane != null) {
            previousOptionPane.setValue(JOptionPane.CLOSED_OPTION);
        }
	    JPanel panel = new JPanel();
	    ArrayList<Gift> arrayList = Gift.getList();

	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // 将面板设置为垂直排列

	    JTextField nameField = new JTextField(5);
	    JTextField scoreField = new JTextField(5);
	    JTextField weightField = new JTextField(5);
	    if(previousOptionPane == null) {
	    nameField.setText("名称");
	    scoreField.setText("分值ֵ");
	    weightField.setText("权重(正整数)");
	    }
	    JButton okButton =new JButton();
	    okButton.setText("增加");
	    okButton.setFont(new Font("宋体", Font.PLAIN, 12));
	    Panel panel3 = new  Panel();
	    panel3.add(nameField);
	    panel3.add(scoreField);
	    panel3.add(weightField);
	    panel3.add(okButton);
	    panel.add(panel3);

	    JLabel label_2;
	    okButton.addActionListener(
	            new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    String name = nameField.getText();
	                    int score = 0;
	                    int weight = 0;
	                    try {
	                        score = Integer.parseInt(scoreField.getText());
	                        weight = Integer.parseInt(weightField.getText())>0?Integer.parseInt(weightField.getText()):-Integer.parseInt(weightField.getText());
	                        arrayList.add(new Gift(name, score, weight));
	                        refreshGiftPanel();
	                    } catch (NumberFormatException ex) {
	                        JOptionPane.showMessageDialog(null, "分值和权重必须为整数", "错误", JOptionPane.ERROR_MESSAGE);
	                    }

	                }

	            });
	    for (Gift gift : arrayList) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("名称：")
	                .append(gift.getName())
	                .append("   分值：")
	                .append(gift.getScore())
	                .append("   权重：")
	                .append(gift.getWeight())
	                .append("\n");

	        label_2 = new JLabel();
	        label_2.setFont(new Font("宋体", Font.PLAIN, 12));
	        label_2.setText(sb.toString());
	        label_2.setBounds(10, 10, 60, 15);

	        JButton delButton = new JButton();
	        delButton.setText("删除");
	        delButton.setFont(new Font("宋体", Font.PLAIN, 12));
	        delButton.setBounds(0,-15,120, 10);
	        JPanel panel2 = new JPanel();
	        panel2.setPreferredSize(new Dimension(100, 30));
	        panel2.add(label_2);
	        panel2.add(delButton);
	        panel.add(panel2);
	        delButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                arrayList.remove(gift);
	                panel.remove(panel2);
	                refreshGiftPanel();
	            }
	        });
	    }

	    JScrollPane scrollPane = new JScrollPane(panel); // 将面板放入滚动窗格中
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setPreferredSize(new Dimension(300, 200));

	    
	    JOptionPane optionPane = new JOptionPane(scrollPane, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        previousOptionPane = optionPane; // 保存弹窗实例

        JDialog dialog = optionPane.createDialog("请设置随机奖励");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
	}
	
	public ImageIcon imgChooser() {
		
			JPanel panel =new JPanel();
			
			 AtomicReference<ImageIcon> ima = new AtomicReference<>();
			 ArrayList<ImageIcon> imgArrayList = new ArrayList<ImageIcon>();
			 //加载jar包文件
			 try {
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/head2.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyI2.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyL2.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyW2.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/long.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/head.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyI.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyL.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/bodyW.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/apple.png"))));
				 imgArrayList.add(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/3322.png"))));
				 			            
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
			 
		File folder = new File("img"); 
		 // 创建代表文件夹的 File 对象
        
        
        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹中的所有文件
            File[] files = folder.listFiles();
            // 遍历文件列表并输出文件路径
            if (files != null) {
                for (File file : files) {
                	
                    ImageIcon icon = new ImageIcon( file.getAbsolutePath());
                    imgArrayList.add(icon);
                }
                }
         else {
            System.err.println("ָ指定的路径不是一个文件夹或文件夹不存在。");
        }
        }
            int i = 0;
            for(ImageIcon icon : imgArrayList) {
                    Image image = icon.getImage();
                    Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                    JButton button = new JButton();
                    button.setIcon(resizedIcon);
                    button.setPreferredSize(new Dimension(50, 50));
                    button.setBounds(i%5*50, i/5*50, 50, 50);
                    panel.setLayout(null);
                    panel.add(button);
                    button.addActionListener(new ActionListener() {
        	            public void actionPerformed(ActionEvent e) {
        	                ima.set(icon);;
                            JOptionPane.getRootFrame().dispose(); // 关闭弹窗
        	            }
        	            });
                    i++;
                }
        
            
        JScrollPane scrollPane = new JScrollPane(panel); // 将面板放入滚动窗格中
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setPreferredSize(new Dimension(300, 200));
		JOptionPane.showOptionDialog(null, scrollPane, "请选择图片",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, null, null);
		return ima.get();
		
			
		
    
	}

	public void gameOver() {
		// TODO Auto-generated method stub

		optionPanel.getPauseButton().setEnabled(false);

		optionPanel.getStopGameButton().setEnabled(false);
		optionPanel.getNewGameButton().setEnabled(true);
		optionPanel.getPauseButton().setText("暂停/继续");
	}

	public void gameStart() {

		// TODO Auto-generated method stub
		
		optionPanel.getPauseButton().setEnabled(true);
		optionPanel.getNewGameButton().setEnabled(false);
		optionPanel.getStopGameButton().setEnabled(true);
	}

	public void gameContinue() {
		// TODO Auto-generated method stub
		optionPanel.getPauseButton().setText("暂停游戏");
	}

	public void gamePause() {
		// TODO Auto-generated method stub
		optionPanel.getPauseButton().setText("继续游戏");
	}
}
