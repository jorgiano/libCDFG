package br.edu.ifrn.hls.cdfg.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;

import br.edu.ifrn.hls.cdfg.builder.CFGBuilder;
import br.edu.ifrn.hls.cdfg.cfg.CFG;
import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.function.FunctionLib;

public class TestCDFG {

	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) {

		DFG initDFG = null;
		CFG cdfg = null;
		LOGGER.setLevel(Level.FINER);
		LOGGER.log(Level.INFO, "Test load init DFG from YAML file");
		try {
			FunctionLib.getFunctionsLib().loadFromFile("functions.yml");
			cdfg = loadInitFromYAMLFile("mult_add.yml");
			// cdfg = loadInitFromYAMLFile("mult_add.yml");
			initDFG = cdfg.getInitDFG();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter fos = null;
		BufferedWriter bof = null;
		try {
			fos = new FileWriter(new File("saida.yml"));
			bof = new BufferedWriter(fos);
			bof.write(cdfg.toYAML());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bof.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static CFG loadInitFromYAMLFile(String filename) throws IOException {
		DFG init = null;
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		Yaml yaml = new Yaml();
		Object data = yaml.load(fis);
		CFG cdfg = CFGBuilder.buildDFGfromYAMLMap((Map<String, Object>) data);
		init = cdfg.getInitDFG();
		System.out.println("DFG " + init.getName());
		System.out.println("  Number of Inputs     = " + init.numberOfInputs());
		System.out
				.println("  Number of Outputs    = " + init.numberOfOutputs());
		System.out.println("  Number of Nodes      = "
				+ init.numberOfOperations());
		System.out.println("  Number of Vertices   = "
				+ init.numberOfVertices());
		System.out.println("  Test                 = " + init.check());

		fis.close();
		return cdfg;

	}
}
