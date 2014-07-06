package br.ufg.inf.es.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.core.AvaliacaoHandler;
import br.ufg.inf.es.avaliadocente.core.concurrency.AbstractAsynchronousAvaliacaoHandler;
import br.ufg.inf.es.avaliadocente.core.concurrency.AvaliacaoListSplitter;
import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;
import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.NotasGrupoAtividade;
import br.ufg.inf.es.avaliadocente.model.bean.QuadroSumario;
import br.ufg.inf.es.avaliadocente.model.bean.json.AvaliacaoJsonBinder;
import br.ufg.inf.es.avaliadocente.repository.NotasGrupoAtividadeRepository;
import br.ufg.inf.es.avaliadocente.repository.QuadroSumarioRepository;
import br.ufg.inf.es.avaliadocente.util.FileUtils;

/**
 * 
 * @author Jhonatan Santos
 *
 */
@ManagedBean
@SessionScoped
public class QuadroSumarioMB {

	private List<QuadroSumario> listaQuadroSumario;
	private Docente docente;
	private List<NotasGrupoAtividade> notasGrupoAtividades;
	private NotasGrupoAtividadeRepository notasGrupoAtividadeRepository;
	private AvaliacaoHandler avaliacaoHandler;
	
	@Autowired
	private static AbstractAsynchronousAvaliacaoHandler asyncAvaliacaoHandler ;
	
	@Autowired
	public QuadroSumarioRepository quadroSumarioRepository;
	
	 private UploadedFile file;
	 private String destino = "C:\\temp\\";
	
	
	public QuadroSumarioMB() {
		avaliacaoHandler = CustomApplicationContext.getInstance().getContext().getBean(AvaliacaoHandler.class);
		quadroSumarioRepository = CustomApplicationContext.getInstance().getContext().getBean(QuadroSumarioRepository.class);
		asyncAvaliacaoHandler  = (AbstractAsynchronousAvaliacaoHandler) CustomApplicationContext.getInstance().getContext().getBean("asyncAvaliacaoHandler");
		notasGrupoAtividadeRepository = CustomApplicationContext.getInstance().getContext().getBean(NotasGrupoAtividadeRepository.class);
	}
	
	
	
	public List<QuadroSumario> getListaQuadroSumario() {
		if (quadroSumarioRepository != null) {
			listaQuadroSumario = quadroSumarioRepository.findAllOrdenadoPorId();
			
	/*		for (QuadroSumario quadroSumarioTemp  : listaQuadroSumario) {
				notasGrupoAtividades.addAll(pegaGrupoAtividade(quadroSumarioTemp.getId()));
			}
		}else{
			listaQuadroSumario = new ArrayList<>(); 
		}*/
		}
		return listaQuadroSumario;
	}

	public Docente getDocente() {
		docente = listaQuadroSumario.get(0).getDocente();
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	
    public List<NotasGrupoAtividade> getNotasGrupoAtividades() {
    	if (notasGrupoAtividadeRepository != null) {
			notasGrupoAtividades = notasGrupoAtividadeRepository.findAll();
		}else{
			notasGrupoAtividades = new ArrayList<>(); 
		}
    	
		return notasGrupoAtividades;
	}



	public void setNotasGrupoAtividades(
			List<NotasGrupoAtividade> notasGrupoAtividades) {
		this.notasGrupoAtividades = notasGrupoAtividades;
	}



	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload(){
    	String extValidate;
    	
    	if(getFile() != null){
    		String ext =getFile().getFileName();
    		
    		if(ext != null){
    			extValidate = ext.substring(ext.indexOf(".")+1);
    		}else{
    			extValidate = null;
    		}
    		
    		if(extValidate !=  null){
    			try {
    				tranfereArquivo(getFile().getFileName(), getFile().getInputstream());
    				String arquivo  = FileUtils.getFileContent(new File(destino+getFile().getFileName()));
    				List<Avaliacao> lista = AvaliacaoJsonBinder.unbindAvaliacoes(arquivo); 
    				/*processarApenasUmaAvaliacao(lista);*/
    				processarVariasAvaliacoes (lista);
				} catch (IOException e) {
					Logger.getLogger("Não funcionou" +e.getMessage());
				}
    			FacesMessage message = new FacesMessage("Arquivo ", file.getFileName() + " carregado com sucesso!!.");
                FacesContext.getCurrentInstance().addMessage(null, message);
    		}
    	}
   
    }
    
    public void tranfereArquivo(String nomeArquivo,InputStream entrada){
    	try{
    		OutputStream saida = new FileOutputStream(new File(destino+nomeArquivo));
    		int reader = 0;
    		byte[] bytes = new byte [(int)getFile().getSize()];
    		
    		while((reader = entrada.read(bytes))!= -1){
    			saida.write(bytes, 0, reader);
    		}
    		 entrada.close();
    		 saida.flush();
    		 saida.close();
    	}catch(IOException e){
    		System.out.println(e.getMessage());
    	}
    }
    private void processarApenasUmaAvaliacao(List<Avaliacao>avaliacoes) {
    	avaliacaoHandler.setAvaliacao(avaliacoes.get(0));
		
		new Thread(avaliacaoHandler).start();
	}
    
    private List<NotasGrupoAtividade> pegaGrupoAtividade(Long idQuadroSumario){
    	List<NotasGrupoAtividade> lista = notasGrupoAtividadeRepository.findByQuadroSumario(idQuadroSumario);
    	return lista;
    }
    
    private void processarVariasAvaliacoes(List<Avaliacao>avaliacoes) {
    	AvaliacaoListSplitter avaliacaoListSplitter =  new AvaliacaoListSplitter();
    	asyncAvaliacaoHandler.setListaDeAvaliacoes(avaliacoes);
		
		for (List<Avaliacao> listaCom1000 : avaliacaoListSplitter.split()) {
			//Obtem um novo asyncAvaliacaoHandler
			asyncAvaliacaoHandler = getNewAsyncAvaliacaoHandler();
			
			//Injea mais uma propriedade no objeto ja instanciado...
			asyncAvaliacaoHandler.setListaDeAvaliacoes(listaCom1000);
			
			//Constroi uma Thread com o seu objeto ja instanciado...
			new Thread(asyncAvaliacaoHandler).start();
		}
		
	}
    
	private static AbstractAsynchronousAvaliacaoHandler getNewAsyncAvaliacaoHandler() {
		//Estou pedindo o Spring pra construir esse objeto com base no id do bean...
		return asyncAvaliacaoHandler = (AbstractAsynchronousAvaliacaoHandler) CustomApplicationContext
				.getInstance().getContext()
				.getBean("asyncAvaliacaoHandler");
	}
    
   }