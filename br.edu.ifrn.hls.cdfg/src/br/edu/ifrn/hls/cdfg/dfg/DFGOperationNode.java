package br.edu.ifrn.hls.cdfg.dfg;

import java.util.logging.Logger;

import br.edu.ifrn.hls.cdfg.function.Function;

public class DFGOperationNode extends DFGNode {

	private final static Logger LOGGER = Logger
			.getLogger(DFGOperationNode.class.getName());

	private Function function;

	private String name;

	public DFGOperationNode(Function function) {
		this.function = function;

	}

	public Function getFunction() {
		return this.function;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean check() {
		boolean ok = true;
		ok = ok && (this.function != null);
		ok = ok
				&& (this.getInputs().size() == this.function
						.getNumberOfInputs());
		ok = ok
				&& (this.getOutputs().size() == this.function
						.getNumberOfOutputs());
		ok = ok && (this.name != null);
		ok = ok && super.check();
		if (ok) {
			for (String inputName : this.getInputs().keySet()) {
				ok = ok && (this.function.getInputIndexByName(inputName) >= 0);
			}
		}
		if (ok) {
			for (String outputName : this.getOutputs().keySet()) {
				ok = ok
						&& (this.function.getOutputIndexByName(outputName) >= 0);
			}
		}
		return ok;
	}

	public String toYAML() {
		StringBuilder sb = new StringBuilder("        ");
		sb.append(this.getName()).append(":\n");
		sb.append("          ").append("function: ");
		sb.append(this.getFunction().getName()).append("\n");
		sb.append("          ").append("inputs: {");
		String sep = " ";
		for (DFGNodePort inputPort : this.getInputs().values()) {
			sb.append(sep);
			sb.append(inputPort.getName()).append(": ");
			sb.append(inputPort.getType()).append(" ");
			sep = ", ";
		}
		sb.append("}\n");
		sb.append("          ").append("outputs: {");
		sep = " ";
		for (DFGNodePort outputPort : this.getOutputs().values()) {
			sb.append(sep);
			sb.append(outputPort.getName()).append(": ");
			sb.append(outputPort.getType()).append(" ");
			sep = ", ";
		}
		sb.append("}\n");
		sb.append("          dependsOn: []\n");
		sb.append("          tags: {}\n");
		return sb.toString();
	}
}
