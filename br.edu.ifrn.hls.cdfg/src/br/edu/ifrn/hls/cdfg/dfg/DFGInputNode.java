package br.edu.ifrn.hls.cdfg.dfg;

public class DFGInputNode extends DFGNode {

	private String name;
	private String type;

	public DFGInputNode() {

	}

	public DFGInputNode(String name, String type) {
		this.setName(name);
		this.setType(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("INPUT: name = ").append(this.getName());
		sb.append(" : type = ").append(this.getType());
		return sb.toString();
	}

}
