package br.edu.ifrn.hls.cdfg.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;

import br.edu.ifrn.hls.cdfg.builder.CFGBuilder;
import br.edu.ifrn.hls.cdfg.cfg.CFG;
import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.function.FunctionLib;
import br.edu.ifrn.hls.cdfg.visualization.DFGDOTFormatGenerator;

public class TestGenerateDot {

	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) {
		DFG initDFG = null;
		CFG cdfg = null;
		Runtime terminal = Runtime.getRuntime();
		try {
			FunctionLib.getFunctionsLib().loadFromFile("functions.yml");
			cdfg = loadInitFromYAMLFile("second_degree.yml");
			initDFG = cdfg.getInitDFG();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter fos = null;
		BufferedWriter bof = null;
		try {
			fos = new FileWriter(new File("graph.dot"));
			bof = new BufferedWriter(fos);
			DFGDOTFormatGenerator gen = new DFGDOTFormatGenerator();
			bof.write(gen.generate(initDFG));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bof.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try{
  		terminal.exec("dot -Tpdf graph.dot -o saida.pdf");
  	} catch (IOException e) {
  		e.printStackTrace();
		}
	}

	private static CFG loadInitFromYAMLFile(String filename) throws IOException {
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		Yaml yaml = new Yaml();
		Object data = yaml.load(fis);
		@SuppressWarnings("unchecked")
		Map<String, Object> mapData = (Map<String, Object>) data;
		CFG cdfg = CFGBuilder
				.buildDFGfromYAMLMap((Map<String, Object>) mapData);
		fis.close();
		return cdfg;

	}
}
