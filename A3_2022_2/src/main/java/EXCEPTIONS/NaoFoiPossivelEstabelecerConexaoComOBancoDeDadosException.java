package EXCEPTIONS;

public class NaoFoiPossivelEstabelecerConexaoComOBancoDeDadosException extends Exception {

    @Override
    public String getMessage() {
        return "Não foi possível estabelecer conexão com o banco de dados.\nTente novamente mais tarde!";
    }
}
