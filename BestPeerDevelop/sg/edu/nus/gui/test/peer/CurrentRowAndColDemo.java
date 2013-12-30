package sg.edu.nus.gui.test.peer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CurrentRowAndColDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3351744803257605602L;
	private JPanel panel = null;
	private JTextArea textArea = null;
	private JLabel theLabelShowRowAndColumn = null;
	private JScrollPane scrollPane = null;

	/**
	 * This is the default constructor
	 */
	public CurrentRowAndColDemo() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("��ù�����ı����е�����λ����ʾ");
		this.add(getJPanel(), BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * This method initializes panel    
	 *     
	 * @return javax.swing.JPanel    
	 */
	private JPanel getJPanel() {
		if (panel == null) {
			panel = new JPanel(new BorderLayout());
			panel.add(getScrollPane(), BorderLayout.CENTER);
			panel.add(getJLabel(), BorderLayout.SOUTH);
		}
		return panel;
	}

	/**
	 * This method initializes textArea    
	 *     
	 * @return javax.swing.JTextArea    
	 */
	private JTextArea getJTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.addMouseListener(new TextMouseListener());
			textArea.addKeyListener(new TextKeyListener());
		}
		return textArea;
	}

	/**
	 * This method initializes scrollPane    
	 *     
	 * @return javax.swing.JScrollPane    
	 */
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(320, 180));
			scrollPane.setViewportView(getJTextArea());
		}
		return scrollPane;
	}

	/**
	 * This method initializes theLabelShowRowAndColumn
	 *     
	 * @return javax.swing.JTextArea    
	 */
	private JLabel getJLabel() {
		if (theLabelShowRowAndColumn == null) {
			theLabelShowRowAndColumn = new JLabel();
			theLabelShowRowAndColumn.setText("��:1   ��:1");
			theLabelShowRowAndColumn.setBorder(BorderFactory.createEmptyBorder(
					0, 16, 0, 0));
		}
		return theLabelShowRowAndColumn;
	}

	// �ı���������¼���������
	class TextMouseListener extends MouseAdapter {
		// �������
		public void mousePressed(MouseEvent e) {
			// �û����
			if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
				getCurrenRowAndCol(); // ��õ�ǰ���к���λ��
			}
		}

		// �ɿ����
		public void mouseReleased(MouseEvent e) {
			// �û����
			if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
				getCurrenRowAndCol(); // ��õ�ǰ���к���λ��
			}
		}
	}

	// �ı����ļ����¼���������
	class TextKeyListener extends KeyAdapter {
		// ����ĳ��
		public void keyPressed(KeyEvent e) {
			getCurrenRowAndCol(); // ��õ�ǰ���к���λ��
		}

		// �ͷ�ĳ��
		public void keyReleased(KeyEvent e) {
			getCurrenRowAndCol(); // ��õ�ǰ���к���λ��
		}
	}

	// ��õ�ǰ���к���λ��,����ʾ��theLabelShowRowAndColumn��
	private void getCurrenRowAndCol() {
		int row = 0;
		int col = 0;
		int pos = textArea.getCaretPosition(); // ��ù�����0��0�е�λ��

		// ��!!!
		col = pos - textArea.getText().substring(0, pos).lastIndexOf("\n");
		// ��!!!
		try {
			row = textArea.getLineOfOffset(pos) + 1; // �������Ǵ�0�����,����+1
		} catch (Exception exception) {
		}
		theLabelShowRowAndColumn.setText("��:" + row + "   ��:" + col);
	}

	public static void main(String[] args) {
		new CurrentRowAndColDemo();
	}
}
