package br.edu.ifrn.hls.cdfg.cdfg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.edu.ifrn.hls.cdfg.dfg.DFG;

public class CDFG {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Map<String, DFG> nodes;

	public Map<String, DFG> getNodes() {
		return nodes;
	}

	private Map<String, CDFGVertex> vertices;
	private DFG init;
	private Set<DFG> end;
	private Map<String, String> tags;

	public CDFG() {
		nodes = new HashMap<String, DFG>();
		end = new HashSet<DFG>();
		tags = new HashMap<String, String>();
		vertices = new HashMap<String, CDFGVertex>();
	}

	public static boolean checkType(String typeName) {
		if (typeName.equals("i16") || typeName.equals("i32"))
			return true;
		return false;
	}

	public void setInitDFG(DFG init) {
		this.init = init;

	}

	public DFG getInitDFG() {
		return this.init;
	}

	public void addNode(DFG node) {
		this.nodes.put(node.getName(), node);

	}

	public Map<String, String> getTags() {
		return this.tags;
	}

	public String toYAML() {
		StringBuilder sb = new StringBuilder("---\n");
		sb.append("name:\n");
		sb.append("  ").append(this.getName()).append("\n");
		sb.append("nodes:\n");
		if (this.nodes != null)
			for (DFG node : this.nodes.values()) {
				sb.append(node.toYAML());
			}
		sb.append("vertices:\n");
		if (this.vertices != null)
			for (CDFGVertex vertex : this.vertices.values()) {
				sb.append(vertex.toYAML());
			}
		sb.append("init:\n");
		sb.append("  name: ").append(this.init.getName()).append("\n");
		sb.append("end: []\n");
		sb.append("tags: {}\n");
		return sb.toString();

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: ").append(this.name).append("\n");
		sb.append(nodes);
		return sb.toString();
	}

}
