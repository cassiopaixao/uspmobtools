package br.usp.ime.uspmobtools.janusmob;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.byarger.exchangeit.EasySSLSocketFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
import br.usp.ime.uspmobtools.R;

public class DadosAlunoActivity extends Activity {
	private HttpClient httpClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.janusmob_dados_aluno);

		try {
			configuraHttpClient();
			conectaJanus();

		} catch (Exception e) {
			exibeMensagem("Erro", e.getMessage());
			e.printStackTrace();
		}

	}

	void exibeMensagem(String titulo, String mensagem) {
		TextView campo = (TextView) findViewById(R.id.textView1);
		campo.setText(titulo + ": " + mensagem);
	}

	public void conectaJanus() throws ClientProtocolException, IOException,
			URISyntaxException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, KeyManagementException,
			UnrecoverableKeyException {
		HttpPost paginaInicial = new HttpPost(new URI(
				getString(R.string.janusmob_pagina_inicial)));

		HttpResponse result = httpClient.execute(paginaInicial);
		String conteudo = EntityUtils.toString(result.getEntity());

		System.err.print(conteudo);
		
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode node = cleaner.clean(conteudo);

		TagNode loginform = node.findElementByName(
				getString(R.string.janusmob_formulario), false);

		if (loginform == null) {
			exibeMensagem("erro", conteudo);
		} else {
			exibeMensagem("achou", loginform.getName());
		}
		
//		List<TagNode> headersLogin = new LinkedList<TagNode>();
//		headersLogin.add(loginform.findElementByName(
//				getString(R.string.janusmob_campo_login), true));
//		headersLogin.add(loginform.findElementByName(
//				getString(R.string.janusmob_campo_submit), true));
//		headersLogin.add(loginform.findElementByName(
//				getString(R.string.janusmob_campo_link_hidden), true));
//		headersLogin.add(loginform.findElementByName(
//				getString(R.string.janusmob_campo_jsf_tree_64), true));
//		headersLogin.add(loginform.findElementByName(
//				getString(R.string.janusmob_campo_jsf_state_64), true));
//		headersLogin.add(loginform.findElementByName(
//				getString(R.string.janusmob_campo_jsf_viewid), true));
//
//		HttpPost paginaLogin = new HttpPost(new URI(
//				getString(R.string.janusmob_pagina_login)));
//
//		paginaLogin.addHeader(getString(R.string.janusmob_campo_usuario), "778900");
//		paginaLogin.addHeader(getString(R.string.janusmob_campo_senha), "senah");
//		
//		for(TagNode campo : headersLogin) {
//			paginaLogin.addHeader(campo.getAttributeByName("name"),
//					campo.getAttributeByName("value"));
//		}
//		
//		result = httpClient.execute(paginaLogin);
//		conteudo = EntityUtils.toString(result.getEntity());
//		
//		node = cleaner.clean(conteudo);
//		
//		exibeMensagem("Carregou", node.findElementByName("logoutForm_SUBMIT", true).getAttributeByName("value"));
	}

	private void configuraHttpClient() {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", new EasySSLSocketFactory(),
				443));

		HttpParams params = new BasicHttpParams();
		params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 30);
		params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE,
				new ConnPerRouteBean(30));
		params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

		ClientConnectionManager cm = new SingleClientConnManager(params,
				schemeRegistry);
		httpClient = new DefaultHttpClient(cm, params);
		
	}

	public void atualizaFichaAluno(JanusDatabaseHelper db) {
		HashMap<String, String> hash = new HashMap<String, String>();

		db.atualizaCampos(hash);
	}
}
