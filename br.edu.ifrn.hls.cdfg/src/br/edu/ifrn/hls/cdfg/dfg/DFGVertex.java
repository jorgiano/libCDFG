package br.edu.ifrn.hls.cdfg.dfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DFGVertex {

	private final static Logger LOGGER = Logger.getLogger(DFGVertex.class
			.getName());

	private String name;
	private DFGNodePort source;
	private List<DFGNodePort> targets;
	private Map<String, String> tags;
	private DFG dfg;

	public DFGVertex(String name) {
		setName(name);
		init();
	}

	private void init() {
		targets = new ArrayList<DFGNodePort>();
		tags = new HashMap<String, String>();

	}

	public DFGNodePort getSource() {
		return this.source;
	}

	public void setSource(DFGNodePort source) {
		this.source = source;
	}

	public int numberOfTargets() {
		return targets.size();
	}

	public List<DFGNode> getTargetsNodes() {
		List<DFGNode> nodes = new ArrayList<DFGNode>();
		for (DFGNodePort port : this.targets) {
			nodes.add(port.getNode());
		}
		return nodes;
	}

	public DFGNodePort getTarget(int index) {
		return targets.get(index);
	}

	public void addTarget(DFGNodePort target) {
		this.targets.add(target);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean check() {
		boolean ok = this.source != null;
		ok = ok && this.source.getConnectedTo() == this;
		for (DFGNodePort port : this.targets) {
			ok = ok && (port.getConnectedTo() == this);
		}
		return ok;
	}

	public void setDFG(DFG dfg) {
		this.dfg = dfg;
	}

	public DFG getDFG() {
		return this.dfg;
	}

	public Map<String, String> getTags() {
		return this.tags;
	}

	public String toYAML() {
		StringBuilder sb = new StringBuilder("      ");
		sb.append(this.getName()).append(":\n");
		sb.append("        source: { type: ");
		if (this.getSource().getNode() instanceof DFGInputNode) {
			sb.append("input, name: ");
			sb.append(this.getSource().getName());
			sb.append("}\n");
		} else if (this.getSource().getNode() instanceof DFGOperationNode) {
			DFGOperationNode node = (DFGOperationNode) this.getSource()
					.getNode();
			sb.append("operation, name: ");
			sb.append(node.getName());
			sb.append(", port: ").append(this.getSource().getName());
			sb.append("}\n");
		} else if (this.getSource().getNode() instanceof DFGOutputNode) {
			System.out.println("Error: outputport set as vertex source!");
		}
		sb.append("        targets: [");
		String sep = " ";
		for (DFGNodePort node : this.targets) {
			sb.append(sep).append("{type: ");
			if (node.getNode() instanceof DFGInputNode) {
				System.out.println("Error: outputport set as vertex source!");
			} else if (node.getNode() instanceof DFGOperationNode) {
				DFGOperationNode opNode = (DFGOperationNode) node.getNode();
				sb.append("operation, name: ");
				sb.append(opNode.getName());
				sb.append(", port: ").append(node.getName());
				sb.append("}");
			} else if (node.getNode() instanceof DFGOutputNode) {
				sb.append("output, name: ");
				sb.append(node.getName());
				sb.append("}");
			}
			sep = ", ";
		}
		sb.append("]\n");
		sb.append("        tags: {");
		sep = " ";
		for (String tagName : this.getTags().keySet()) {
			String value = this.getTags().get(tagName);
			sb.append(sep);
			sb.append(tagName);
			sb.append(": ");
			sb.append(value);
			sep = ",";
		}
		sb.append("}\n");
		return sb.toString();
	}
}
