package br.edu.ifrn.hls.cdfg.builder;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.dfg.DFGInputNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGNodePort;
import br.edu.ifrn.hls.cdfg.dfg.DFGOperationNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGOutputNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGVertex;
import br.edu.ifrn.hls.cdfg.function.Function;
import br.edu.ifrn.hls.cdfg.function.FunctionLib;

public class DFGBuilder {

	private final static Logger LOGGER = Logger.getLogger(DFGBuilder.class
			.getName());

	private DFGBuilder() {
	}

	public static DFG buildDFGfromYAMLMap(Map<String, Object> dfgData) {
		DFG dfg = new DFG();
		LOGGER.log(Level.INFO, "Building DFG from " + dfgData.toString());

		@SuppressWarnings("unchecked")
		Map<String, Object> nodes = (Map<String, Object>) dfgData.get("nodes");
		buildDFGNodes(dfg, nodes);
		@SuppressWarnings("unchecked")
		Map<String, Object> vertices = (Map<String, Object>) dfgData
				.get("vertices");
		buildDFGVertices(dfg, vertices);
		@SuppressWarnings("unchecked")
		Map<String, Object> tags = (Map<String, Object>) dfgData.get("tags");
		addTags(tags, dfg.getTags());
		return dfg;
	}

	private static void buildDFGNodes(DFG dfg, Map<String, Object> nodes) {
		@SuppressWarnings("unchecked")
		Map<String, Object> inputs = (Map<String, Object>) nodes.get("inputs");
		buildDFGInputs(dfg, inputs);
		@SuppressWarnings("unchecked")
		Map<String, Object> outputs = (Map<String, Object>) nodes
				.get("outputs");
		buildDFGOutputs(dfg, outputs);
		@SuppressWarnings("unchecked")
		Map<String, Object> operations = (Map<String, Object>) nodes
				.get("operations");
		buildDFGOperations(dfg, operations);

	}

	private static void buildDFGInputs(DFG dfg, Map<String, Object> inputs) {
		for (String inputName : inputs.keySet()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> inputData = (Map<String, Object>) inputs
					.get(inputName);
			String type = (String) inputData.get("type");
			DFGInputNode input = new DFGInputNode(inputName, type);
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) inputData
					.get("tags");
			addTags(tags, input.getTags());
			input.setDFG(dfg);
			dfg.addInputNode(input);
		}

	}

	private static void buildDFGOutputs(DFG dfg, Map<String, Object> outputs) {
		for (String outputName : outputs.keySet()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> outputData = (Map<String, Object>) outputs
					.get(outputName);
			String type = (String) outputData.get("type");
			DFGOutputNode output = new DFGOutputNode(outputName, type);
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) outputData
					.get("tags");
			addTags(tags, output.getTags());
			output.setDFG(dfg);
			dfg.addOutputNode(output);
		}
	}

	private static void buildDFGOperations(DFG dfg,
			Map<String, Object> operations) {
		for (String operationName : operations.keySet()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> nodeData = (Map<String, Object>) operations
					.get(operationName);
			String functionName = (String) nodeData.get("function");
			Function function = FunctionLib.getFunctionsLib().getFunction(
					functionName);
			DFGOperationNode node = new DFGOperationNode(function);
			node.setName(operationName);
			@SuppressWarnings("unchecked")
			Map<String, Object> inputs = (Map<String, Object>) nodeData
					.get("inputs");
			buildDFGNodeInputPorts(node, inputs);
			@SuppressWarnings("unchecked")
			Map<String, Object> outputs = (Map<String, Object>) nodeData
					.get("outputs");
			buildDFGNodeOutputPorts(node, outputs);
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) nodeData
					.get("tags");
			addTags(tags, node.getTags());
			node.setDFG(dfg);
			dfg.addOperationNode(node);

		}
	}

	private static void buildDFGNodeOutputPorts(DFGOperationNode node,
			Map<String, Object> outputs) {
		for (Object o : outputs.keySet()) {
			String outputName = (String) o;
			String type = (String) outputs.get(outputName);
			DFGNodePort port = new DFGNodePort();
			port.setName(outputName);
			port.setType(type);
			port.setNode(node);
			node.getOutputs().put(port.getName(), port);
		}
	}

	private static void buildDFGNodeInputPorts(DFGOperationNode node,
			Map<String, Object> inputs) {
		for (Object o : inputs.keySet()) {
			String inputName = (String) o;
			LOGGER.log(Level.FINE, "inputname");
			String type = (String) inputs.get(inputName);
			DFGNodePort port = new DFGNodePort();
			port.setName(inputName);
			port.setType(type);
			port.setNode(node);
			node.getInputs().put(port.getName(), port);
		}
	}

	private static void buildDFGVertices(DFG dfg, Map<String, Object> vertices) {
		for (String vertexName : vertices.keySet()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> vertexData = (Map<String, Object>) vertices
					.get(vertexName);

			@SuppressWarnings("unchecked")
			Map<String, String> source = (Map<String, String>) vertexData
					.get("source");
			String type = source.get("type");
			String name = source.get("name");
			DFGVertex vertex = new DFGVertex(vertexName);
			buildVertexSource(dfg, source, type, name, vertex);
			@SuppressWarnings("unchecked")
			List<Map<String, String>> targets = (List<Map<String, String>>) vertexData
					.get("targets");
			buildVertexTargets(dfg, vertex, targets);
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) vertexData
					.get("tags");
			addTags(tags, vertex.getTags());
			vertex.setDFG(dfg);
			dfg.addVertex(vertex);
		}
	}

	private static void buildVertexTargets(DFG dfg, DFGVertex vertex,
			List<Map<String, String>> targets) {
		for (Map<String, String> target : targets) {
			String targetType = target.get("type");
			String targetName = target.get("name");
			if (targetType.equals("input")) {
				try {
					throw new Exception("Vertex source cannot be an input node");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (targetType.equalsIgnoreCase("output")) {
				DFGNodePort outputPort = dfg.getOutputs().get(targetName)
						.getInputs().get(targetName);
				vertex.addTarget(outputPort);
				outputPort.setConnectedTo(vertex);

			} else if (targetType.equals("operation")) {
				DFGNodePort operationPort;
				String portName = target.get("port");
				DFGOperationNode op = dfg.getOperationByName(targetName);
				operationPort = op.getInputs().get(portName);
				vertex.addTarget(operationPort);
				operationPort.setConnectedTo(vertex);
			}
		}
	}

	private static void buildVertexSource(DFG dfg, Map<String, String> source,
			String type, String name, DFGVertex vertex) {
		if (type.equals("input")) {
			DFGNodePort inputPort = dfg.getInputs().get(name).getOutputs()
					.get(name);
			vertex.setSource(inputPort);
			inputPort.setConnectedTo(vertex);
		} else if (type.equalsIgnoreCase("output")) {
			try {
				throw new Exception("Vertex source cannot be an output node");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (type.equals("operation")) {
			DFGNodePort operationPort;
			String portName = source.get("port");
			operationPort = dfg.getOperations().get(name).getOutputs()
					.get(portName);
			vertex.setSource(operationPort);
			operationPort.setConnectedTo(vertex);
		}
	}

	private static void addTags(Map<String, Object> source,
			Map<String, String> target) {
		if (source != null)
			for (String tagName : source.keySet()) {
				target.put(tagName, (String) source.get(tagName).toString());
			}
	}
}