package br.edu.ifrn.hls.cdfg.dfg;

public class DFGInputNode extends DFGNode {

	public DFGInputNode(String name, String type) {
		super();
		DFGNodePort port = new DFGNodePort();
		port.setName(name);
		port.setType(type);
		port.setNode(this);
		this.addOutput(port);
	}

	public String getName() {
		return this.getOutputsName().iterator().next();
	}

	public String getType() {
		return this.getOutputByName(this.getName()).getType();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("INPUT: name = ").append(this.getName());
		sb.append(" : type = ").append(this.getType());
		return sb.toString();
	}

	public boolean check() {
		return false;
	}

}
