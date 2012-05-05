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
import java.util.HashSet;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.util.EntityUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Looper;
import br.usp.ime.uspmobtools.R;

public class AtualizaDadosAluno extends Thread {

	private String nusp;
	private String senha;
	private DadosAlunoActivity context;

	AtualizaDadosAluno(String nusp, String senha, DadosAlunoActivity context) {
		this.nusp = nusp;
		this.senha = senha;
		this.context = context;

	}

	@Override
	public void run() {
		/*
		 * abre http://janus.usp.br faz login acessa página "dados pessoais"
		 * recupera dados acessa página "ficha do aluno" recupera dados sai
		 */
		HttpClient client = null;
		try {
			conectaJanus(client);
			

			
		} catch (Exception e) {
			context.exibeMensagem("Erro", "deu erro.");
			e.printStackTrace();
		}

	}

	public void conectaJanus(HttpClient client) throws ClientProtocolException, IOException,
			URISyntaxException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, KeyManagementException,
			UnrecoverableKeyException {
		
		HttpPost post = new HttpPost(new URI(
				context.getString(R.string.janusmob_pagina_inicial)));
		// post.setEntity(new StringEntity(BODY));

		KeyStore trusted = KeyStore.getInstance("BKS");
		trusted.load(null, "".toCharArray());
		SSLSocketFactory sslf = new SSLSocketFactory(trusted);
		sslf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("https", sslf, 443));
		SingleClientConnManager cm = new SingleClientConnManager(
				post.getParams(), schemeRegistry);

		client = new DefaultHttpClient(cm, post.getParams());
		HttpResponse result = client.execute(post);
	}

	public void atualizaFichaAluno(JanusDatabaseHelper db) {
		HashMap<String, String> hash = new HashMap<String, String>();

		db.atualizaCampos(hash);
	}
}
