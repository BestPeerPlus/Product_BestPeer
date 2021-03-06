/*
 * @(#) SPPassClientListener.java 1.0 2006-3-5
 * 
 * Copyright 2006, National University of Singapore.
 * All rights reserved.
 */

package sg.edu.nus.peer.event;

import sg.edu.nus.gui.AbstractMainFrame;
import sg.edu.nus.gui.server.Pane;
import sg.edu.nus.gui.server.ServerGUI;
import sg.edu.nus.peer.info.PeerInfo;
import sg.edu.nus.peer.info.PhysicalInfo;
import sg.edu.nus.peer.management.EventHandleException;
import sg.edu.nus.peer.management.PeerMaintainer;
import sg.edu.nus.protocol.Message;
import sg.edu.nus.protocol.MsgType;
import sg.edu.nus.protocol.body.SPPassClientBody;

/**
 * Implement a listener for processing SP_PASS_CLIENT message.
 * 
 * @author Vu Quang Hieu  
 * @version 1.0 2006-3-5
 */

public class SPPassClientListener extends ActionAdapter {

	public SPPassClientListener(AbstractMainFrame gui) {
		super(gui);
	}

	public void actionPerformed(PhysicalInfo dest, Message msg)
			throws EventHandleException {
		try {
			/* get the message body */
			SPPassClientBody body = (SPPassClientBody) msg.getBody();

			PeerInfo[] attachedPeers = body.getAttachedPeers();
			PeerMaintainer maintainer = PeerMaintainer.getInstance();

			/* update table component */
			Pane pane = ((ServerGUI) gui).getPane();
			int size = attachedPeers.length;
			for (int i = 0; i < size; i++) {
				PeerInfo peerInfo = attachedPeers[i];
				maintainer.put(peerInfo);
				pane.firePeerTableRowInserted(pane.getPeerTableRowCount(),
						peerInfo.toObjectArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EventHandleException("SP_PASS_CLIENT operation failure",
					e);
		}
	}

	public boolean isConsumed(Message msg) throws EventHandleException {
		if (msg.getHead().getMsgType() == MsgType.SP_PASS_CLIENT.getValue())
			return true;
		return false;
	}

}