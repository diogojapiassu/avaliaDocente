package br.ufg.inf.es.avaliadocente.util;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;

/**
 * 
 * Classe contendo métodos utilitários relacionados a manipulação de {@link File}s.
 * 
 * <p>
 * Classe adaptada do <a href="http://examples.javacodegeeks.com/core-java/class/find-a-file-in-classpath/">seguinte link</a>.
 * </p>
 *  
 * @author Ilias Tsagklis
 * @author Danilo Guimarães
 */
public class FileUtils {
	
	private static final String NEW_LINE_REGEX = "[\\r\\n]+";
	private static final String COMMA_REGEX = "\\;";
	private static final String PIPE_REGEX = "\\|";
	
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
	 * 
	 * @param path
	 * @param encoding
	 * @throws IOException
	 */
	public static void newFile(String path, String encoding) throws IOException {
		org.apache.commons.io.FileUtils.write(new File(path), null, encoding);
	}
	
	/**
	 * 
	 * @param path
	 * @param content
	 * @param encoding
	 * @throws IOException
	 */
	public static void newFile(String path, String content, String encoding) throws IOException {
		newFile(path, content, encoding);
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
	 * Lê e retorna o conteúdo de um {@link File} qualquer.
	 * 
	 * @param file arquivo que terá o conteúdo lido
	 * @param encoding o encoding a ser usado, null significa o default da plataforma
	 * @return conteúdo do arquivo.
	 * @throws IOException
	 * @author Danilo Guimarães
	 */
	public static String getFileContent(final File file, String encoding) throws IOException {
		return org.apache.commons.io.FileUtils.readFileToString(file, encoding);
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
	
	/**
	 * Busca por um arquivo que esteja no classpath e retorna seu conteúdo.
	 * 
	 * @param fileName nome do arquivo que está no classpath
	 * @param encoding o encoding a ser usado, null significa o default da plataforma
	 * @return conteúdo do arquivo.
	 * @throws IOException
	 * @author Danilo Guimarães
	 */
	public static String getContent(final String fileName, String encoding) throws IOException {
		return getFileContent(findFileOnClassPath(fileName), encoding);
	}
	
	/**
	 * Faz a separação de um conteúdo com base no token passado.
	 * 
	 * <p>
	 * É o mesmo que:
	 * 
	 * <pre>
	 * String[] splitted = content.split(token);
	 * return splitted;
	 * </pre>
	 * 
	 * @param content
	 * @param token
	 * @return
	 * @author Danilo Guimarães
	 */
	public static String[] splitContent(String content, String token) {
		return content.split(token);
	}
	
	/**
	 * Faz a separação de um conteúdo com base no caracter new line
	 * (<b>'\r\n'</b>)
	 * @param content conteúdo que sofrerá o split por <b>'\r\n'</b>
	 * @return array de {@link String} com o conteúdo separado.
	 * @author Danilo Guimarães
	 */
	public static String[] splitByNewLine(String content) {
		return splitContent(content, NEW_LINE_REGEX);
	}
	
	/**
	 * Faz a separação de um conteúdo com base no caracter ponto-vírgula
	 * (<b>';'</b>)
	 * 
	 * @param content conteúdo que sofrerá o split por <b>';'</b>
	 * @return array de {@link String} com o conteúdo separado.
	 * @author Danilo Guimarães
	 */
	public static String[] splitByComma(String content) {
		return splitContent(content, COMMA_REGEX);
	}
	
	/**
	 * Faz a separação de um conteúdo com base no caracter pipe (<b>'|'</b>)
	 * 
	 * @param content conteúdo que sofrerá o split por <b>'|'</b>
	 * @return array de {@link String} com o conteúdo separado.
	 * @author Danilo Guimarães
	 */
	public static String[] splitByPipe(String content) {
		return splitContent(content, PIPE_REGEX);
	}
	
	
}
