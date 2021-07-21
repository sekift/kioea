package com.kioea.www.mathutil;

/**
 * @author zhuting
 * @version 1.0
 *
 * 空白格子输入0
 *
 * TODO:
 * 1.支持文件读入输出
 * 2.美化GUI
 * 3.支持截屏输入
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class SolverGUI extends JFrame {
	private static final long serialVersionUID = -3595128867320035255L;
	private NumberPanel numberPanel = new NumberPanel();
	private ButtonPanel btPanel = new ButtonPanel();

	private SolverGUI() {
		setLayout(new BorderLayout());
		add(numberPanel, BorderLayout.CENTER);
		add(new JLabel("Sudoku Quick Solver", SwingConstants.CENTER), BorderLayout.NORTH);
		add(btPanel, BorderLayout.SOUTH);
		add(new JLabel(" "), BorderLayout.EAST);
		add(new JLabel(" "), BorderLayout.WEST);
	}

	public static void main(String[] args) {
		SolverGUI frame = new SolverGUI();
		frame.setTitle("sudoku");
		frame.setSize(400, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private class ButtonPanel extends JPanel {
		private static final long serialVersionUID = -2538819927984172155L;
		private JButton jbtComf = new JButton("Solve!");
		private JButton jbtCle = new JButton("Clear");

		public ButtonPanel() {
			setLayout(new GridLayout(1, 2, 20, 20));
			add(new JLabel(" "));
			add(jbtComf);
			add(jbtCle);
			add(new JLabel(" "));
			jbtComf.addActionListener(new ComfListener());
			jbtCle.addActionListener(new CleListener());
		}

		class ComfListener implements ActionListener {
			@Override
            public void actionPerformed(ActionEvent e) {
				numberPanel.quickSolve();
			}
		}

		class CleListener implements ActionListener {
			@Override
            public void actionPerformed(ActionEvent e) {
				numberPanel.clear();
			}
		}
	}

	private class NumberPanel extends JPanel {
		private static final long serialVersionUID = -1829297639727007938L;
		private JTextField[] textField = new JTextField[81];

		public NumberPanel() {
			setLayout(new GridLayout(10, 10, 5, 5));
			for (int i = 0; i < 10; ++i) {
				add(new JLabel(" " + i));
			}
			for (int i = 0; i < 81; ++i) {
				textField[i] = new JTextField("0", 1);
				textField[i].setHorizontalAlignment(JTextField.CENTER);
				if((i%9<3&&(i<27||i>53))||(i%9>5&&(i<27||i>54))||(i>27&&i<54&&i%9>2&&i%9<6)){
				Color bg=new Color(255,255,0);
				textField[i].setBackground(bg);
				}
			}
			int cur = 0;
			for (int i = 1; i < 10; ++i) {
				add(new JLabel(" " + i));
				for (int j = 1; j < 10; ++j) {
					add(textField[cur]);
					++cur;
				}
			}
		}

		private char[] getStr() {
			String strTmp;
			char[] str = new char[81];
			for (int i = 0; i < 81; ++i) {
				strTmp = textField[i].getText();
				if (strTmp.length() != 1) {
					return null;
				}
				if (Integer.parseInt(strTmp) < 1 || Integer.parseInt(strTmp) > 9) {
					JOptionPane.showMessageDialog(null,"有的数字不符合规范");
					return null;
				}
				str[i] = strTmp.charAt(0);
			}
			return str;
		}

		public void quickSolve() {
			boolean isSuccess = false;
			char[] ori = getStr();
			char[] ans;
			if (ori == null) {
				isSuccess = false;
			} else {
				SudokuSolver sudoSolver = new SudokuSolver(ori);
				ans = sudoSolver.getAns();
				if (ans == null) {
					isSuccess = false;
				} else {
					for (int i = 0; i < 81; ++i) {
						textField[i].setText("" + ans[i]);
					}
					isSuccess = true;
				}
			}
			if (!isSuccess) {
				JOptionPane.showMessageDialog(null, "No possible solution!");
			}
			return;
		}

		public void clear() {
			for (int i = 0; i < 81; ++i) {
				textField[i].setText("0");
			}
			return;
		}
	}
}
