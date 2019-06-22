package pe.edu.unmsm.quipucamayoc.util;

import java.io.File;
import java.io.IOException;

import javax.swing.SwingWorker;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

public class UploadTask extends SwingWorker<Void, Integer> {
	private static Logger logger = Logger.getLogger(UploadTask.class);
	private String uploadURL;
	private File uploadFile;
	private String numero;
	private String tipo;

	public UploadTask(String uploadURL, File uploadFile, String tipo, String numero) {
		this.uploadURL = uploadURL;
		this.uploadFile = uploadFile;
		this.numero = numero;
		this.tipo = tipo;
	}

	@Override
	protected Void doInBackground() {
		try {

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(uploadURL);
			FileBody archivo = new FileBody(uploadFile);
			StringBody elTipo = new StringBody(tipo, ContentType.TEXT_PLAIN);
			StringBody elNumero = new StringBody(numero, ContentType.TEXT_PLAIN);
			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("archivo", archivo).addPart("tipo", elTipo)
					.addPart("numero", elNumero).build();

			httppost.setEntity(reqEntity);
			httppost.getRequestLine();
			httpclient.execute(httppost);

		} catch (IOException ex) {
			logger.error("Error en lectura y escritura de archivos", ex);
			setProgress(0);
			cancel(true);
		}

		return null;
	}

	@Override
	protected void done() {
		// No se porque fui creado :'v
	}
}