package org.dougllas.medsystem.service;

public class Email {
    private String servidor;
    private String usuario;
    private String senha;
    private String destino;
    private String remetente;
    private String assunto;
    private String mensagem;

    public Email() {
    }

    public static String geraMensagem(String var0, String var1) {
        StringBuffer var2 = new StringBuffer();
        var2.append("<!DOCTYPE HTML PUBLIC \'-//W3C//DTD HTML 4.01 Transitional//EN\' \'http://www.w3.org/TR/html4/loose.dtd\'>\t\t");
        var2.append(" \t \t<html>\t\t");
        var2.append(" \t \t<head>\t\t");
        var2.append(" \t \t<meta http-equiv=\'Content-Type\' content=\'text/html; charset=UTF-8\'>\t\t");
        var2.append(" \t \t<title>Cadastro</title>\t\t");
        var2.append(" \t \t</head>\t\t");
        var2.append(" \t \t<body background=\'http://sige.seduc.ce.gov.br/Images/fundo_02.jpg\' topmargin=\'0\'>\t\t");
        var2.append(" \t \t<table width=\'500\'  border=\'1\' align=\'center\' cellpadding=\'0\' cellspacing=\'0\' bordercolor=\'#E3E1BA\' bgcolor=\'#FFFFFF\'>\t\t");
        var2.append(" \t \t  <tr>\t\t");
        var2.append(" \t \t    <td><img src=\'http://sige.seduc.ce.gov.br/Images/top_email.jpg\' width=\'500\' height=\'70\'></td>\t\t");
        var2.append(" \t \t  </tr>\t\t");
        var2.append(" \t \t  <tr>\t\t");
        var2.append(" \t \t    <td><div align=\'justify\'>\t\t");
        var2.append(" \t \t      <table width=\'100%\'  border=\'0\' cellpadding=\'10\' cellspacing=\'0\'>\t\t");
        var2.append(" \t \t       <tr>\t\t");
        var2.append(" \t \t          <td><p><font size=\'1\' face=\'Verdana, Arial, Helvetica, sans-serif\'>Bem vindo a SEDUC/CE!<br>\t\t");
        var2.append(" \t \t                <br>\t\t");
        var2.append(" \t \t                Para concluir o processo de cadastramento voc&ecirc; deve acessar o sistema utilizando o usu&aacute;rio e a senha abaixo:<br>\t\t");
        var2.append(" \t \t          </font></p>\t\t");
        var2.append(" \t \t           <p><font color=\'#018C93\' size=\'2\' face=\'Verdana, Arial, Helvetica, sans-serif\'>\t\t");
        var2.append(" \t \t\t\t\t<strong><span class=\'style1\'>Usu&aacute;rio= " + var0 + " <br>\t\t");
        var2.append(" \t \t     \t\t Senha= " + var1 + "</span></strong></font></p>\t\t");
        var2.append(" \t \t            <p><font size=\'1\' face=\'Verdana, Arial, Helvetica, sans-serif\'><strong>Observa&ccedil;&atilde;o</strong>: <br>\t\t");
        var2.append(" \t \t              Em seu primeiro acesso, o sistema solicitar&aacute; automaticamente a troca da senha. Essa senha &eacute; de sua inteira responsabilidade, n&atilde;o devendo ser compartilhada com ningu&eacute;m. Lembre-se de que o sistema grava o nome do usu&aacute;rio que realizou qualquer opera&ccedil;&atilde;o. Caso sua senha seja compartilhada, tudo que for realizado utilizando sua senha ser&aacute; tamb&eacute;m de sua responsabilidade.<br>\t\t");
        var2.append(" \t \t             <br>\t\t");
        var2.append(" \t \t             Em caso de d&uacute;vidas, entre em contato com a SEDUC.</font></p></td>\t\t");
        var2.append(" \t \t       </tr>\t\t");
        var2.append(" \t \t     </table>\t\t");
        var2.append(" \t \t      </div></td>\t\t");
        var2.append(" \t \t  </tr>\t\t");
        var2.append(" \t \t</table>\t\t");
        var2.append(" \t \t</body>\t\t");
        var2.append(" \t \t</html>\t\t");
        return var2.toString();
    }

    public static String geraMensagemRedefinirSenha(String var0, String var1) {
        StringBuffer var2 = new StringBuffer();
        var2.append("<!DOCTYPE HTML PUBLIC \'-//W3C//DTD HTML 4.01 Transitional//EN\' \'http://www.w3.org/TR/html4/loose.dtd\'>\t\t");
        var2.append(" \t \t<html>\t\t");
        var2.append(" \t \t<head>\t\t");
        var2.append(" \t \t<meta http-equiv=\'Content-Type\' content=\'text/html; charset=UTF-8\'>\t\t");
        var2.append(" \t \t<title>Controle de Acesso</title>\t\t");
        var2.append(" \t \t</head>\t\t");
        var2.append(" \t \t<body background=\'http://sige.seduc.ce.gov.br/Images/fundo_02.jpg\' topmargin=\'0\'>\t\t");
        var2.append(" \t \t<table width=\'500\'  border=\'1\' align=\'center\' cellpadding=\'0\' cellspacing=\'0\' bordercolor=\'#E3E1BA\' bgcolor=\'#FFFFFF\'>\t\t");
        var2.append(" \t \t  <tr>\t\t");
        var2.append(" \t \t    <td><img src=\'http://sige.seduc.ce.gov.br/Images/top_email.jpg\' width=\'500\' height=\'70\'></td>\t\t");
        var2.append(" \t \t  </tr>\t\t");
        var2.append(" \t \t  <tr>\t\t");
        var2.append(" \t \t    <td><div align=\'justify\'>\t\t");
        var2.append(" \t \t      <table width=\'100%\'  border=\'0\' cellpadding=\'10\' cellspacing=\'0\'>\t\t");
        var2.append(" \t \t       <tr>\t\t");
        var2.append(" \t \t          <td><p><font size=\'1\' face=\'Verdana, Arial, Helvetica, sans-serif\'>Recupera&ccedil;&atilde;o de senha do Controle de Acesso<br>\t\t");
        var2.append(" \t \t                <br>\t\t");
        var2.append(" \t \t                A sua nova senha de acesso &eacute;: " + var1 + "<br/>");
        var2.append("\t\t\t\t\t\tUsu&aacute;rio: " + var0 + " </font></p>\t\t");
        var2.append(" \t \t            <p><font size=\'1\' face=\'Verdana, Arial, Helvetica, sans-serif\'><br>\t\t");
        var2.append("\t\t\t\t\t Ao entrar no sistema com essa senha ser&aacute; solicitada sua altera&ccedil;&atilde;o. Essa senha &eacute; de sua inteira responsabilidade, n&atilde;o devendo ser compartilhada com ningu&eacute;m. Lembre-se de que o sistema grava o nome do usu&aacute;rio que realizou qualquer opera&ccedil;&atilde;o. Caso sua senha seja compartilhada, tudo que for realizado utilizando sua senha ser&aacute; tamb&eacute;m de sua responsabilidade.<br>\t\t");
        var2.append(" \t \t             <br>\t\t");
        var2.append(" \t \t             Em caso de d&uacute;vidas, entre em contato com a SEDUC.</font></p></td>\t\t");
        var2.append(" \t \t       </tr>\t\t");
        var2.append(" \t \t     </table>\t\t");
        var2.append(" \t \t      </div></td>\t\t");
        var2.append(" \t \t  </tr>\t\t");
        var2.append(" \t \t</table>\t\t");
        var2.append(" \t \t</body>\t\t");
        var2.append(" \t \t</html>\t\t");
        return var2.toString();
    }

    public String getServidor() {
        return this.servidor;
    }

    public void setServidor(String var1) {
        this.servidor = var1;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String var1) {
        this.usuario = var1;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String var1) {
        this.senha = var1;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String var1) {
        this.destino = var1;
    }

    public String getRemetente() {
        return this.remetente;
    }

    public void setRemetente(String var1) {
        this.remetente = var1;
    }

    public String getAssunto() {
        return this.assunto;
    }

    public void setAssunto(String var1) {
        this.assunto = var1;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String var1) {
        this.mensagem = var1;
    }
}
