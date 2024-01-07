package interprete;

import static interprete.Interprete.error;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and",    TipoToken.AND);
        palabrasReservadas.put("else",   TipoToken.ELSE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("for",    TipoToken.FOR);
        palabrasReservadas.put("fun",    TipoToken.FUN);
        palabrasReservadas.put("if",     TipoToken.IF);
        palabrasReservadas.put("null",   TipoToken.NULL);
        palabrasReservadas.put("or",     TipoToken.OR);
        palabrasReservadas.put("print",  TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true",   TipoToken.TRUE);
        palabrasReservadas.put("var",    TipoToken.VAR);
        palabrasReservadas.put("while",  TipoToken.WHILE);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();
    
    public Scanner(String source){
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        int estado = 0;
        String lexema = "";
        char c;
        int linea_actual=1;
        for(int i=0; i<source.length(); i++){
            
            c = source.charAt(i);
            
            switch (estado){
                case 0:
                    if(c == '<'){
                        estado = 1;
                        lexema += c;
                    }
                    else if(c == '>'){
                        estado = 2;
                        lexema += c;
                    }
                    else if(c == '!'){
                        estado = 3;
                        lexema += c;
                    }
                    else if(c == '='){
                        estado = 4;
                        lexema += c;
                    }
                    else if(c == '+'){
                        estado = 5;
                        lexema += c;
                    }
                    else if(c == '-'){
                        estado = 6;
                        lexema += c;
                    }
                    else if(c == '*'){
                        estado = 7;
                        lexema += c;
                    }
                    else if(c == '/'){
                        estado = 8;
                        lexema += c;
                    }
                    else if(c == '{'){
                        estado = 9;
                        lexema += c;
                    }
                    else if(c == '}'){
                        estado = 10;
                        lexema += c;
                    }
                    else if(c == '('){
                        estado = 11;
                        lexema += c;
                    }
                    else if(c == ')'){
                        estado = 12;
                        lexema += c;
                    }
                    else if(c == ','){
                        estado = 13;
                        lexema += c;
                    }
                    else if(c == '.'){
                        estado = 14;
                        lexema += c;
                    }
                    else if(c == ';'){
                        estado = 15;
                        lexema += c;
                    }
                    else if(c == '"'){
                        estado = 16;
                        lexema += c;
                    }
                    else if(Character.isLetter(c)){
                        estado = 17;
                        lexema += c;
                    }
                    else if(Character.isDigit(c)){
                        estado = 18;
                        lexema += c;
                    }
                    else if(c==' '||c=='\t'||c=='\r'){
                        estado=0;
                    }
                    else if(c=='\n'){
                        linea_actual++;
                        estado=0;
                    }
                    else{
                        error(linea_actual,"Caracter invalido "+c);
                        System.exit(0);
                    }
                    break;
                case 1:
                    if(c == '='){
                        estado = 101;
                        lexema += c;
                        Token t = new Token(TipoToken.LESS_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                    }
                    else{
                        estado = 102;
                        Token t = new Token(TipoToken.LESS, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                    
                case 2:
                    if(c == '='){
                        estado= 201;
                        lexema += c;
                        Token t = new Token(TipoToken.GREATER_EQUAL,lexema);
                        tokens.add(t);
                        
                        estado = 0;
                        lexema = "";
                    }
                    else{
                        estado = 202;
                        Token t = new Token(TipoToken.GREATER,lexema);
                        tokens.add(t);
                        
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                    
                case 3:
                    if(c == '='){
                        estado = 301;
                        lexema += c;
                        Token t = new Token(TipoToken.BANG_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                    }
                    else{
                        estado = 302;
                        Token t = new Token(TipoToken.BANG, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                    
                case 4:
                    if(c == '='){
                        estado = 401;
                        lexema += c;
                        Token t = new Token(TipoToken.EQUAL_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                    }
                    else{
                        estado = 402;
                        Token t = new Token(TipoToken.EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 5:
                    if(i==i){
                        estado = 501;
                        Token t = new Token(TipoToken.PLUS, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 6:
                    if(i==i){
                        estado = 601;
                        Token t = new Token(TipoToken.MINUS, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 7:
                    if(i==i){
                        estado = 701;
                        Token t = new Token(TipoToken.STAR, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 8:
                    if(c == '/'){
                        estado = 801;
                        lexema += c;
                    }
                    else if(c =='*'){
                        estado = 802;
                        lexema += c;
                    }
                    else{
                        estado = 803;
                        Token t = new Token(TipoToken.SLASH, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 801:
                    if(c=='\n'){
                        linea_actual++;
                        estado=0;
                        lexema="";
                    }
                    else{
                        estado=801;
                        lexema+=c;
                    }
                    break;
                case 802:
                    if(c=='*'){
                        estado=80201;
                        lexema+=c;
                    }
                    else{
                        estado=802;
                        lexema+=c;
                    }
                    break;
                case 80201:
                    if(c=='/'){
                        estado=0;
                        lexema="";
                    }
                    else{
                        estado=802;
                        lexema+=c;
                    }
                    break;
                case 9:
                    if(i==i){
                        estado = 901;
                        Token t = new Token(TipoToken.LEFT_BRACE, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                    
                case 10:
                    if(i==i){
                        estado = 1001;
                        Token t = new Token(TipoToken.RIGHT_BRACE, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 11:
                    if(i==i){
                        estado = 1101;
                        Token t = new Token(TipoToken.LEFT_PAREN, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 12:
                    if(i==i){
                        estado = 1201;
                        Token t = new Token(TipoToken.RIGHT_PAREN, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 13:
                    if(i==i){
                        estado = 1301;
                        Token t = new Token(TipoToken.COMMA, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 14:
                    if(i==i){
                        estado = 1401;
                        Token t = new Token(TipoToken.DOT, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 15:
                    if(i==i){
                        estado = 1501;
                        Token t = new Token(TipoToken.SEMICOLON, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                }
                    break;
                case 16:
                    if(c == '"'){
                        estado = 1601;
                        lexema += c;
                        Token t = new Token(TipoToken.STRING, lexema.substring(1, lexema.length()-1));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                    }
                    else if(i == source.length() - 1){
                        error(linea_actual, "Comillas sin cerrar");
                        System.exit(0);
                    }
                    else if(c=='\n'){
                        error(linea_actual,"No se esperaba el salto de linea.");
                        System.exit(0);
                    }
                    else{
                        estado = 16;
                        lexema += c;
                    }
                    break;
                case 17: //letra
                    if(Character.isLetter(c)||Character.isDigit(c)){
                        estado = 17;
                        lexema += c;
                    }
                    else{
                        estado = 1701;
                        TipoToken tt = palabrasReservadas.get(lexema);

                        if(tt == null){
                            Token t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);
                        }
                        else{
                            Token t = new Token(tt, lexema);
                            tokens.add(t);
                        }
                        
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 18://15 no a salido de ser un numero entero
                    if(Character.isDigit(c)){
                        estado = 18;//15
                        lexema += c;
                    }
                    else if(c == '.'){//camino para decimal
                        estado = 1801;//16
                        lexema += c;
                    }
                    else if(c == 'E'){ //camino para exponencial
                        estado = 1802; //18
                        lexema += c;
                    }
                    else{
                        estado = 1803;//22 se da el numero entero
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                    
                case 1801:
                    if(Character.isDigit(c)){
                        estado = 180101;//17
                        lexema += c;
                    }
                    else{
                        lexema=lexema.substring(0,lexema.length()-1);
                        estado = 180300;
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i=i-2;
                    }
                    break;
                    
                case 180101:
                    if(Character.isDigit(c)){
                        estado = 180101;
                        lexema += c;
                    }
                    else if(c == 'E'){
                        estado = 1802;
                        lexema += c;
                    }
                    else{
                        estado = 18010101;
                        Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 1802:
                    if(Character.isDigit(c)){
                        estado = 180201;
                        lexema += c;
                    }
                    else if(c == '+' || c == '-'){
                        estado = 180202;
                        lexema += c;
                    }
                    break;
                case 180202:
                    if(Character.isDigit(c)){
                        estado = 180201;
                        lexema += c;
                    }
                    break;
                case 180201:
                    if(Character.isDigit(c)){
                        estado = 180201;
                        lexema += c;
                    }
                    else{
                        estado = 18020101;
                        Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                    
                    
            }

        }


        return tokens;
    }
}
