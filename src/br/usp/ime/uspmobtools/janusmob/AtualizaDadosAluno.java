package br.usp.ime.uspmobtools.janusmob;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
		HttpClient clienteWeb = new DefaultHttpClient();
		HttpGet get = new HttpGet(
				context.getString(R.string.janusmob_pagina_inicial));
		try {
			HttpResponse resposta = clienteWeb.execute(get);

			String conteudo = EntityUtils.toString(resposta.getEntity());

			context.exibeMensagem("Correto", "deu certo xD");

		} catch (ClientProtocolException e) {
			context.exibeMensagem("Erro", "deu erro de ClientProtocol.");
			e.printStackTrace();
		} catch (IOException e) {
			context.exibeMensagem("Erro", "deu erro de IO.");
			e.printStackTrace();
		}

	}

}
