package br.com.e2f.institutodepilar.util;

public class EmailTemplate {
	
	private static String nome;
	private static String assunto;
	private static String mensagem;
	private static String senha;
	
	
	public static String bemVindo() {
		final StringBuilder str = new StringBuilder();
		
		str.append("<html>");
		str.append("<head></head>");
		str.append("<body>");
		
		str.append("<h3>{assunto}</h3>");
		
		str.append("<p>{mensagem}</p>");
		
		str.append("</html>");
		
		return str.toString();
	}
	
	public static String esqueciSenha(String nome, String senha) {
		final StringBuilder str = new StringBuilder();
		
//		str.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
//        str.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
//        str.append("<head>");
//        str.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
//        str.append("<title>Email</title>");
/*        str.append("<style type=\"text/css\">");
        str.append("@media only screen and (max-device-width: 599px) {.header {height: 205px;}}");
        str.append("@media only screen and (max-device-width: 599px) {.titulo, .subtitulo {text-align: left;color: #ffffff;top: 5px;left: 15px;}}");
        str.append("@media only screen and (max-device-width: 599px) {.subtitulo {top: -15px !important;}}");
        str.append("@media only screen and (max-device-width: 599px) {.posicao-horario {position: relative;top: -30px;left: 0px;}}");
        str.append("@media only screen and (max-device-width: 599px) {.menssagem {text-align: left;left: 15px;}}");
        str.append("</style>");
*/
		//        str.append("</head>");
//        str.append("<body>");
        str.append("<div class=\"email-background\">");
        str.append("<div class=\"email-container\" style='max-width:600px;margin:0 auto;font-family:\"Roboto\", sans-serif;'>");
        str.append("<div class=\"header row\" style=\"background:#4f54ad;text-align:center;height:170px;\">");
        str.append("<div class=\"col col-30\">");
        str.append("<i class=\"logo\" style='background:url(\"http://www.ugas.com.br/email/logo.png\");background-repeat:no-repeat;background-size:contain;display:block;height:125px;position:relative;top:20px;left:20px;'></i>");
        str.append("</div><div>");
        str.append("<div class=\"titulo row\" style=\"text-align:center;color:#ffffff;position:relative;top:-90px;\">");
        str.append("<h4>Recuperação de senha:</h4>");
        str.append("</div>");
        str.append("<div class=\"subtitulo row\" style=\"text-align:center;color:#ffffff;position:relative;top:-90px;\">");
        str.append("<h3>"+nome+"</h3>");
        str.append("</div>");
        str.append("</div>");
        str.append("</div>");
        str.append("<div class=\"menssagem\" style=\"color:#a9abae;position:relative;text-align:center;\">");
        str.append("<br/>");
        str.append("Sr(a), usuário(a),<br/><br/>informamos que sua senha,<br/>cadastrada em nossos sistemas é:<br/> <h3>"+senha+"</h3>");
        str.append("</div>");
        str.append("<div class=\"borda texto-cinza\" style=\"border-top:solid 1px #a9abae;color:#a9abae;\">");
        str.append("<div>");
        str.append("<i class=\"suporte\" style='background:url(\"http://www.ugas.com.br/email/logo.png\");background-repeat:no-repeat;background-size:contain;display:block;height:125px;position:relative;top:20px;left:20px;background:url(\"http://www.ugas.com.br/email/suporte.png\") no-repeat;width:40px;height:40px;'></i>");
        str.append("<div class=\"texto-suporte\" style=\"position:relative;top:-20px;left:70px;\">");
        str.append("(61)3552-5383<br/>sac@ugas.com</div></div>");
        str.append("<div class=\"posicao-horario\" style=\"position:relative;top:-80px;left:300px;\">");
        str.append("<i class=\"horario\" style='background:url(\"http://www.ugas.com.br/email/logo.png\");background-repeat:no-repeat;background-size:contain;display:block;height:125px;position:relative;top:20px;left:20px;background:url(\"http://www.ugas.com.br/email/horario.png\") no-repeat;width:40px;height:40px;'></i>");
        str.append("<div class=\"texto-horario\" style=\"position:relative;top:-20px;left:70px;\">Seg/ Sex - 08:00 as 18:00<br/>Sábado - 08:00 as 12:00</div></div></div></div></div>");
  //      str.append("</body>");
  //      str.append("</html>");
		
		
		return str.toString();
	}
	
}
