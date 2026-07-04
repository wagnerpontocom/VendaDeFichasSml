package br.com.qualix.proserv.servico;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.proserv.modelo.Produto;
import java.io.File;
import javax.swing.ImageIcon;

public class SImagemProduto {

    private static final String CAMINHO_BASE = Configuracoes.CAMINHO_DADOS;
    private static final String PASTA_IMAGEM_PRODUTOS = "imagem_produtos";
    private static final String[] EXTENSOES_SUPORTADAS = {".png", ".jpg", ".jpeg"};

    public static ImageIcon retrieveImagemProduto(Produto produto) throws Exception {
        criarDiretorioImagemProdutos();

        for (String extensao : EXTENSOES_SUPORTADAS) {
            File imagem = new File(getDiretorioImagemProdutos(), produto.getId() + extensao);
            if (imagem.exists() && imagem.isFile()) {
                return new ImageIcon(imagem.getAbsolutePath());
            }
        }

        throw new Exception("Imagem do produto nao encontrada");
    }

    private static File getDiretorioImagemProdutos() {
        return new File(CAMINHO_BASE, PASTA_IMAGEM_PRODUTOS);
    }

    private static void criarDiretorioImagemProdutos() {
        File diretorio = getDiretorioImagemProdutos();
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }
}
