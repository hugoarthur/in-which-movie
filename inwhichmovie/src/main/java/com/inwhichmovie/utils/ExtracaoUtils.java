package com.inwhichmovie.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.inwhichmovie.beans.Filme;
import com.inwhichmovie.beans.Musica;

public class ExtracaoUtils {
	public static Filme getFilme(String id) {
		Filme filme = null;
		String nomeUrl = "http://www.imdb.com/title/tt" + id;
		try {
			System.out.println("Acessando a url = "+nomeUrl);
			Parser parser = new Parser(nomeUrl);
			NodeList source = parser.extractAllNodesThatMatch((new CssSelectorNodeFilter("#overview-top  > div[class=\"infobar\"]")));
			String html = source.toHtml();
			if (!(html.contains("TV Series") || html.contains("Video Game") || html.contains("TV Episode") || html.contains("Adult") || html.contains("Documentary"))) {
				// Filme
				parser.reset();
				Node sourceDadosFilme = parser.extractAllNodesThatMatch((new CssSelectorNodeFilter("#overview-top > h1[class=\"header\"]"))).elementAt(0);
				NodeList nodeList = sourceDadosFilme.getChildren();
				filme = new Filme();
				filme.setIdImdb(id);
				filme.setNome(nodeList.elementAt(1).toPlainTextString());
				String tituloOriginal = null;
				try {
					tituloOriginal = nodeList.elementAt(6).toPlainTextString();
				} catch (NullPointerException e) {
				}
				if (tituloOriginal != null)
					filme.setNomeOriginal(StringUtils.substringBetween(tituloOriginal, "\""));
				try {
					filme.setAno(nodeList.elementAt(3).toPlainTextString().replaceAll("\\D", ""));
				} catch (NullPointerException e) {
				}
				filme.setTipo("Movie");
				System.out.println(filme);
				getMusicas(filme, parser, nomeUrl);
				return filme;
			}
			return null;
		} catch (ParserException e) {
			System.out.println("Problema na URL = "+nomeUrl+"\n"+e.getMessage());
		}
		return filme;
	}

	public static void getMusicas(Filme filme, Parser parser, String nomeUrl) throws ParserException {
		// Musica
		parser = new Parser(nomeUrl + "/soundtrack");
		List<Musica> musicas = new ArrayList<Musica>();
		try {
			NodeList nodeList = parser.extractAllNodesThatMatch((new CssSelectorNodeFilter("#soundtracks_content > div[class=\"list\"]")));

			String listHtml = nodeList.toHtml();
			if (!listHtml.contains("It looks like we don't have any Soundtracks for this title yet.")) {
				for (Node node : nodeList.elementAt(0).getChildren().toNodeArray()) {
					NodeList nodeFilhos = node.getChildren();
					if (nodeFilhos != null) {
						Musica musica = new Musica();
						musica.setNome(nodeFilhos.toNodeArray()[0].getText().replace("\"", ""));
						String html = nodeFilhos.toHtml();
						if (html.contains("Performed by"))
							musica.setNomeArtista(StringUtils.substringBetween(html, "Performed by ", "<br />").replaceAll("\\<.*?>", ""));
						else if (html.contains("Written by"))
							musica.setNomeArtista(StringUtils.substringBetween(html, "Written by ", "<br />").replaceAll("\\<.*?>", ""));
						System.out.println(musica);
						musicas.add(musica);
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		filme.setMusicas(musicas);
	}
}
