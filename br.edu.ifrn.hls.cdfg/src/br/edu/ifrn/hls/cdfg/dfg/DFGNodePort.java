package br.edu.ifrn.hls.cdfg.dfg;

import java.util.logging.Logger;

import br.edu.ifrn.hls.cdfg.cfg.CFG;

public class DFGNodePort {

	private final static Logger LOGGER = Logger.getLogger(DFGNodePort.class
			.getName());

	private String name;
	private String type;
	private DFGNode node;
	private DFGVertex connectedTo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DFGVertex getConnectedTo() {
		return connectedTo;
	}

	public void setConnectedTo(DFGVertex connectedTo) {
		this.connectedTo = connectedTo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DFGNode getNode() {
		return node;
	}

	public void setNode(DFGNode node) {
		this.node = node;
	}

	public boolean check() {
		return node != null && connectedTo != null && CFG.checkType(type);
	}

}
