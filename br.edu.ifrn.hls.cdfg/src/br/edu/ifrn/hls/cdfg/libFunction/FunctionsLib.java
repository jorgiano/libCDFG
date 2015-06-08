package br.edu.ifrn.hls.cdfg.libFunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class FunctionsLib {

	private Map<String, Function> _functions;

	static private FunctionsLib _functionsLib;

	private FunctionsLib() {
		_functions = new HashMap<String, Function>();
	}

	static public FunctionsLib getFunctionsLib() {
		if (_functionsLib == null)
			_functionsLib = new FunctionsLib();
		return _functionsLib;
	}

	public Function getFunction(String functionName) {
		return _functions.get(functionName);
	}

	public void addFunction(Function f) throws Exception {
		if (_functions.get(f.getName()) == null)
			_functions.put(f.getName(), f);
		else
			throw new Exception("Function " + f.getName()
					+ " already in library");
	}

	public void loadFromFile(String filename) throws FileNotFoundException {
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		Yaml yaml = new Yaml();
		Object data = yaml.load(fis);
		if (data instanceof HashMap) {
			@SuppressWarnings("unchecked")
			HashMap<String, HashMap<String, Object>> functions = ((HashMap<String, HashMap<String, Object>>) data);
			for (String functionName : functions.keySet()) {
				loadFunction(functionName, functions.get(functionName));
			}
		}
		try {
			fis.close();
		} catch (IOException e) {
		}
	}

	@SuppressWarnings("unchecked")
	private void loadFunction(String functionName,
			HashMap<String, Object> attributes) {
		List<String> inputs = (List<String>) attributes.get("inputs");
		List<String> outputs = (List<String>) attributes.get("outputs");
		String mnemonic = (String) attributes.get("mnemonic");
		Map<String, Object> tags = (Map<String, Object>) attributes.get("tags");
		try {
			Function f = new Function(functionName, inputs.size(),
					outputs.size(), mnemonic);
			for (int i = 0; i < inputs.size(); i++) {
				f.setInputName(i + 1, inputs.get(i));
			}
			for (int i = 0; i < outputs.size(); i++) {
				f.setOutputName(i + 1, outputs.get(i));
			}
			this.addFunction(f);
			for (String tagName : tags.keySet()) {
				f.addTag(tagName, tags.get(tagName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveToFile(String filename) {

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Function function : _functions.values()) {
			sb.append(function).append("\n");
		}
		return sb.toString();
	}

}
