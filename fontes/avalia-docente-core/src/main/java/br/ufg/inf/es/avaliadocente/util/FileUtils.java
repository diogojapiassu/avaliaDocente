package br.ufg.inf.es.avaliadocente.util;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * A class containing useful utility methods relating to files.
 * 
 * <p>
 * Classe adaptada do <a href="http://examples.javacodegeeks.com/core-java/class/find-a-file-in-classpath/">seguinte link</a>.
 * </p>
 *  
 * @author Ilias Tsagklis
 * @author Danilo Guimarães
 */
public class FileUtils {
	
	//Classe estática
	private FileUtils() { }

	/**
	 * Returns a reference to a file with the specified name that is located
	 * somewhere on the classpath.
	 * 
	 * @param fileName nome do arquivo.
	 * @return {@link File}
	 * @author Ilias Tsagklis
	 */
	public static File findFileOnClassPath(final String fileName) {

		final String classpath = System.getProperty("java.class.path");
		final String pathSeparator = System.getProperty("path.separator");
		final StringTokenizer tokenizer = new StringTokenizer(classpath, pathSeparator);

		while (tokenizer.hasMoreTokens()) {
			final String pathElement = tokenizer.nextToken();
			final File directoryOrJar = new File(pathElement);
			final File absoluteDirectoryOrJar = directoryOrJar.getAbsoluteFile();

			if (absoluteDirectoryOrJar.isFile()) {
				final File target = new File(
						absoluteDirectoryOrJar.getParent(), fileName);

				if (target.exists()) {
					return target;
				}
			} else {
				final File target = new File(directoryOrJar, fileName);
				if (target.exists()) {
					return target;
				}
			}
		}
		return null;
	}
	
	/**
	 * Lê e retorna o conteúdo de um {@link File} qualquer.
	 * 
	 * @param file arquivo que terá o conteúdo lido
	 * @return conteúdo do arquivo.
	 * @throws IOException
	 * @author Danilo Guimarães
	 */
	public static String getFileContent(final File file) throws IOException {
		return org.apache.commons.io.FileUtils.readFileToString(file);
	}
	
	/**
	 * Busca por um arquivo que esteja no classpath e retorna seu conteúdo.
	 * 
	 * @param fileName nome do arquivo que está no classpath
	 * @return conteúdo do arquivo.
	 * @throws IOException
	 * @author Danilo Guimarães
	 */
	public static String getContent(final String fileName) throws IOException {
		return getFileContent(findFileOnClassPath(fileName));
	}
}
