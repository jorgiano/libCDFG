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

	public DFGNodePort getOutputPort() {
		DFGNodePort port = null;
		if (this.outputs.size() == 1)
			port = this.outputs.values().iterator().next();
		return port;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("INPUT: name = ").append(this.getName());
		sb.append(" : type = ").append(this.getType());
		return sb.toString();
	}

	public String toYAML() {
		StringBuilder sb = new StringBuilder("        ");
		sb.append(this.getName()).append(":\n          ");
		sb.append("type: ").append(this.getType());
		/* TODO: add tags to YAML */
		sb.append("\n          tags: {}\n");
		return sb.toString();
	}

	public boolean check() {
		boolean ok = super.check();
		ok = ok && this.inputs.size() == 0 && this.outputs.size() == 1;
		return ok;
	}

}
