package br.edu.ifrn.hls.cdfg.dfg;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DFGInputNode extends DFGNode {
	private final static Logger LOGGER = Logger.getLogger(DFGInputNode.class
			.getName());

	public DFGInputNode(String name, String type) {
		super();
		LOGGER.log(Level.FINE, "Creating input node {0}", name);
		DFGNodePort port = new DFGNodePort();
		port.setName(name);
		port.setType(type);
		port.setNode(this);
		this.getOutputs().put(port.getName(), port);
	}

	public String getName() {
		return getOutputPort().getName();
	}

	public String getType() {
		return getOutputPort().getType();
	}

	public DFGNodePort getOutputPort() {
		DFGNodePort port = null;
		if (this.getOutputs().size() == 1)
			port = this.getOutputs().values().iterator().next();
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
		ok = ok && this.getInputs().size() == 0
				&& this.getOutputs().size() == 1;
		return ok;
	}

}
