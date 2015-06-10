package br.edu.ifrn.hls.cdfg.dfg;

public class DFGNodePort {

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

}
