package br.edu.ifrn.hls.cdfg.dfg;

import java.util.logging.Logger;

public class DFGOutputNode extends DFGNode {

	private final static Logger LOGGER = Logger.getLogger(DFGOutputNode.class
			.getName());

	public DFGOutputNode(String name, String type) {
		DFGNodePort port = new DFGNodePort();
		port.setName(name);
		port.setType(type);
		port.setNode(this);
		this.getInputs().put(port.getName(), port);
	}

	public String getName() {
		return this.getInputs().keySet().iterator().next();
	}

	public String getType() {
		return this.getInputs().get(this.getName()).getType();
	}

	public DFGNodePort getInputPort() {
		DFGNodePort port = null;
		if (this.getInputs().size() == 1)
			port = this.getInputs().values().iterator().next();
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
		ok = ok && this.getInputs().size() == 1
				&& this.getOutputs().size() == 0;
		return ok;
	}
}
