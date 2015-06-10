package br.edu.ifrn.hls.cdfg.dfg;

public class DFGOutputNode extends DFGNode {

	public DFGOutputNode(String name, String type) {
		DFGNodePort port = new DFGNodePort();
		port.setName(name);
		port.setType(type);
		port.setNode(this);
		this.addInput(port);
	}

	public String getName() {
		return this.getInputsName().iterator().next();
	}

	public String getType() {
		return this.getInputByName(this.getName()).getType();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("OUTPUT: name = ").append(this.getName());
		sb.append(" : type = ").append(this.getType());
		return sb.toString();
	}

	public boolean check() {
		return false;
	}

}
