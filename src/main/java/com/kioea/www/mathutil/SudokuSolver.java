package com.kioea.www.mathutil;

/**
 * @author zhuting
 * @version 1.0
 * 
 *          功能: 本类用于给出任意数独的一个解 用法: 使用SudokuSolver(char[] toSolve)构造函数.
 *          toSolve是一个待解的数独字符数组,81个字符. 如果数独中的某格是空的,对应字符用'0'替代,
 *          否则使用对应的数字字符'1'~'9' ;
 * 
 *          调用char[] getAns()函数, 会返回已经补充完整的数独字符数组;
 * 
 *          注意: 不要通过修改str的内容,重复使用一个SudukuSolver进行求解.
 *          因为这里的init()函数没有写完整,这样做可能导致未知错误.
 * 
 *          算法: dlx算法 + 启发式 效率: 本类由c++代码修改而来,原c++代码可以在几百毫秒的时间内解出数独
 */
class SudokuSolver {
	// 以下为宏
	private final int maxl = 100;
	private final int maxc = 325;// 最大列数,包含头结点
	private final int maxr = 730;// 最大行数,包含待匹配行
	private final int maxn = 240000;// 最大总结点数
	private final int head = 0;// 头结点编号为0

	private int getCel(int i, int j, int k) {
		return i * 9 + j;
	}

	private int getRow(int i, int j, int k) {
		return 80 + i * 9 + k;
	}

	private int getCol(int i, int j, int k) {
		return 161 + j * 9 + k;
	}

	private int getBlo(int i, int j, int k) {
		return 242 + (3 * (i / 3) + j / 3) * 9 + k;
	}

	private int getI(int l) {
		return (l - 1) / 81;
	}

	private int getJ(int l) {
		return (l - 1) % 81 / 9;
	}

	private int getK(int l) {
		return (l - 1) % 81 % 9 + 1;
	}

	// 宏end
	private final int m = 729;
	private final int n = 324;
	private boolean mymap[][] = new boolean[maxr][maxc];
	private int num[] = new int[maxc];
	// 每个节点需要的信息
	private int up[] = new int[maxn];
	private int down[] = new int[maxn];
	private int left[] = new int[maxn];
	private int right[] = new int[maxn];
	private int column[] = new int[maxn];
	private int row[] = new int[maxn];
	private int ans[] = new int[maxn];
	public char[] str = new char[maxl];

	private void init() {
		for (int i = 1; i <= n; ++i)
			num[i] = 9;
		return;
	}

	private void genZeroOne() {
		int curLine = 0;
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				for (int k = 1; k <= 9; ++k) {
					mymap[curLine][getCel(i, j, k)] = true;
					mymap[curLine][getRow(i, j, k)] = true;
					mymap[curLine][getCol(i, j, k)] = true;
					mymap[curLine][getBlo(i, j, k)] = true;
					++curLine;
				}
			}
		}
		return;
	}

	private void genNode() {
		int id = 1;
		boolean tmp = false;
		int lineUp[] = new int[maxc];
		for (int j = 0; j < n; ++j) {
			lineUp[j] = id;
			column[id] = j + 1;
			if (j != n - 1) {
				right[id] = id + 1;
			}
			left[id] = id - 1;
			++id;
		}
		right[head] = 1;
		left[head] = id - 1;
		right[id - 1] = head;
		for (int i = 0; i < m; ++i) {
			int firstId = 0;
			int leftId = 0;
			for (int j = 0; j < n; ++j) {
				tmp = mymap[i][j];
				if (tmp) {
					row[id] = i + 1;
					column[id] = j + 1;
					if (firstId == 0) {
						firstId = id;
						leftId = id;
					} else {
						left[id] = leftId;
						right[leftId] = id;
						leftId = id;
					}
					int upId = lineUp[j];
					up[id] = upId;
					down[upId] = id;
					lineUp[j] = id;
					++id;
				}
			}
			if (firstId != 0) {
				left[firstId] = leftId;
				right[leftId] = firstId;
			}
		}
		for (int j = 0; j < n; ++j) {
			down[lineUp[j]] = j + 1;
			up[j + 1] = lineUp[j];
		}
		return;
	}

	// dlx算法部分
	private void remove(int c) {
		left[right[c]] = left[c];
		right[left[c]] = right[c];
		for (int i = down[c]; i != c; i = down[i]) {
			for (int j = right[i]; j != i; j = right[j]) {
				up[down[j]] = up[j];
				down[up[j]] = down[j];
				--num[column[j]];
			}
		}
		return;
	}

	private void resume(int c) {
		left[right[c]] = c;
		right[left[c]] = c;
		for (int i = up[c]; i != c; i = up[i]) {
			for (int j = right[i]; j != i; j = right[j]) {
				up[down[j]] = j;
				down[up[j]] = j;
				++num[column[j]];
			}
		}
		return;
	}

	private boolean dance(int k) {
		int c = right[head];
		if (c == head)// 成功
		{
			recover(k);
			return true;
		}
		// 启发式
		int minN = num[c];
		int minCur = c;
		while (true) {
			c = right[c];
			if (c == head)
				break;
			if (num[c] < minN) {
				minN = num[c];
				minCur = c;
			}
		}
		c = minCur;
		// end 启发式
		remove(c);
		for (int i = down[c]; i != c; i = down[i]) {
			ans[k] = row[i];
			for (int j = right[i]; j != i; j = right[j])
				remove(column[j]);
			if (dance(k + 1))
				return true;
			for (int j = left[i]; j != i; j = left[j])
				resume(column[j]);
		}
		resume(c);
		return false;
	}

	// end dlx算法
	// 根据ans还原数独
	private void recover(int t) {
		for (int i = 0; i < t; ++i) {
			int tmp = ans[i];
			int cur = getI(tmp) * 9 + getJ(tmp);
			str[cur] = (char) (getK(tmp) + '0');
		}
		return;
	}

	// 解析字符串
	private void parse(char[] a) {
		int k = 0;
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				char chTmp = a[i * 9 + j];
				if (chTmp >= '1' && chTmp <= '9') {
					k = chTmp - '0';
					remove(getCel(i, j, k) + 1);// 预处理
					remove(getRow(i, j, k) + 1);
					remove(getCol(i, j, k) + 1);
					remove(getBlo(i, j, k) + 1);
				}
			}
		}
		return;
	}

	private void printHelp() {
		System.out.println("***********************************************");
		System.out.println("Usage: give a char[81] to record a sudoku");
		System.out.println(" if a cell is empty, please enter 0");
		System.out.println("***********************************************");
	}

	public SudokuSolver(char[] a) {
		if (a.length < 81)
			printHelp();
		for (int i = 0; i < 81; ++i)
			str[i] = a[i];
	}

	public char[] getAns() {
		init();
		genZeroOne();
		genNode();
		parse(str);
		boolean isPossible = dance(0);
		if (isPossible) {
			/*
			 * System.out.println("SUCCESS:"); for (int i = 0; i < 81; ++i) {
			 * System.out.print(str[i]); }
			 */
			return str;
		} else {
			/*
			 * System.out.println("FAILED: no solutions");
			 */
			return null;
		}
	}
}
