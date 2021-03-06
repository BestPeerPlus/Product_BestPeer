/*
 * @(#) DBTreeCellRenderer.java 1.0 2007-1-3
 * 
 * Copyright 2006, Natioal University of Singapore.
 * All rights reserved.
 */

package sg.edu.nus.gui.dbview;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import sg.edu.nus.gui.AbstractMainFrame;

/**
 * Implement customize <code>SchemaMappingDBTreeCellRenderer</code> that is to render each cell
 * in the <code>DBTree</code>.
 * 
 * @author Han Xixian
 * @version 1.0 2008-07-12
 * @see javax.swing.tree.DefaultTreeCellRenderer
 */

public class SchemaMappingDBTreeCellRenderer extends DefaultTreeCellRenderer {
	// private members
	private static final long serialVersionUID = -4977292375250536195L;

	// three kinds of nodes in DBTree
	private String[] images = { "TableNode", "ColumnNode", "DBNode" };

	/**
	 * Rewrite the <code>getTreeCellRendererComponent</code> function in the DefaultTreeCellRenderer class
	 * to set the custemized image for different kinds of tree node in the <code>DBTree</code>.
	 * 
	 * @return the Component that the renderer uses to draw the value 
	 */
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded,
				leaf, row, hasFocus);

		DBTreeNode node = (DBTreeNode) value;

		String img = null;

		String text = node.toString();

		if (node.getNodeType().equals("TABLE")) {
			img = images[0];

			DBTreeNode tNode = node;
			if (tNode.getShareStatus() == DBTreeNode.SHARESTATUS_SHARE)
				this.setForeground(Color.RED);
			else if (tNode.getShareStatus() == DBTreeNode.SHARESTATUS_TEMPSHARE)
				this.setForeground(Color.PINK);
			else if (tNode.getShareStatus() == DBTreeNode.SHARESTATUS_TEMPUNSHARE)
				this.setForeground(Color.BLUE);
		} else if (node.getNodeType().equals("COLUMN")) {
			img = images[1];
			DBTreeNode cNode = node;

			if (cNode.getShareStatus() == DBTreeNode.SHARESTATUS_SHARE) {
				if (text.indexOf("->") != -1) {
					String[] arrText = text.split("->");

					this.setText("<html><font color=#FF0000>" + arrText[0]
							+ "</font>" + "->" + " <font color=#800080>"
							+ arrText[1] + "</font></html>");
				} else
					this.setForeground(Color.RED);

			} else if (cNode.getShareStatus() == DBTreeNode.SHARESTATUS_TEMPSHARE) {
				if (text.indexOf("->") != -1) {
					String[] arrText = text.split("->");

					this.setText("<html><font color=#FF00FF>" + arrText[0]
							+ "</font>" + "->" + " <font color=#808000>"
							+ arrText[1] + "</font></html>");
				} else
					this.setForeground(Color.PINK);
			} else if (cNode.getShareStatus() == DBTreeNode.SHARESTATUS_TEMPUNSHARE)
				this.setForeground(Color.BLUE);
		} else
			img = images[2];

		// look for the image
		String imageLoc = AbstractMainFrame.SRC_PATH + img + ".png";

		// set the image icon to render the node
		this.setIcon(new ImageIcon(imageLoc));

		return this;
	}
}
