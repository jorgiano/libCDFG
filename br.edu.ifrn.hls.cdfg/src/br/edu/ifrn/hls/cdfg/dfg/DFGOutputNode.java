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

	public DFGNodePort getInputPort() {
		DFGNodePort port = null;
		if (this.inputs.size() == 1)
			port = this.inputs.values().iterator().next();
		return port;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("OUTPUT: name = ").append(this.getName());
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
		ok = ok && this.inputs.size() == 1 && this.outputs.size() == 0;
		return ok;
	}
}
