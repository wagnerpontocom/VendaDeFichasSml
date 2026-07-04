/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.aaaFerramentas;

import br.com.qualix.aaaException.AlertaException;
import br.com.qualix.aaaException.InfoException;
import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Wagner
 */
public class Pandora {
    public static void except(String msgErro) throws Exception {
        throw new Exception(msgErro);
    }

    public static String LD_STR(LocalDate data) throws Exception {
        try {
            if (data != null) {
                String dt = java.sql.Date.valueOf(data).toString();
                String dia = dt.substring(8, 10);
                String mes = dt.substring(5, 7);
                String ano = dt.substring(0, 4);
                dt = dia + "/" + mes + "/" + ano;
                return dt;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

    public static Date LD_DT(LocalDate data) throws Exception {
        try {
            if (data != null) {
                Date dt;
                dt = Date.valueOf(data);
                return dt;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

    public static Date STR_DT(String data) throws Exception {
        try {
            if (data != null) {
                try {
                    data = validaData(data);
                    LocalDate dt;
                    dt = STR_LD(data);
                    return Date.valueOf(dt);
                } catch (Exception ex) {
                    msgErro(ex.getMessage());
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

    public static LocalDate STR_LD(String data) throws Exception {
        try {
            if (data != null) {
                LocalDate ld = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return ld;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Tipo de Data incompatível para conversão. ('dd/mm/aaaa').");
        }
    }

    public static LocalDate DT_LD(Date data) throws Exception {
        try {
            if (data != null) {
                LocalDate d = data.toLocalDate();
                return d;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

//    Ver formato de data a ser enviado.
    public static String DT_STR(Date data) throws Exception {
        try {
            if (data != null) {
                return data.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

//    Ver formato de data a ser enviado.
    public static String TMP_STR(Timestamp tmp) throws Exception {
        try {
            if (tmp != null) {
                return tmp.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

    public static LocalDateTime TMP_LDT(Timestamp tmp) throws Exception {
        try {
            if (tmp != null) {
                return tmp.toLocalDateTime();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

//    Verificar o retorno
    public static LocalDateTime STR_LDT(String data) throws Exception {
        try {
            if (data != null) {
                return LocalDateTime.parse(data);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

//    Verificar o retorno
    public static Timestamp STR_TMP(String data) throws Exception {
        try {
            if (data != null) {
                return Timestamp.valueOf(data);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

    public static Timestamp LDT_TMP(LocalDateTime ldt) throws Exception {
        try {
            if (ldt != null) {
                return Timestamp.valueOf(ldt);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }

//    Ver retorno
    public static String LDT_STR(LocalDateTime ldt) throws Exception {
        try {
            if (ldt != null) {
                LocalTime lt = ldt.toLocalTime();
                String hora = "";
                String minuto = "";
                String segundo = "";
                if (lt.getHour() < 10){
                    hora = "0";
                }
                hora = hora + lt.getHour();
                
                if (lt.getMinute()< 10){
                    minuto = "0";
                }
                minuto = minuto + lt.getMinute();
                
                if (lt.getSecond()< 10){
                    segundo = "0";
                }
                segundo = segundo + lt.getSecond();
                
                return Pandora.LD_STR(ldt.toLocalDate()) + " " + hora + ":" + minuto + ":" + segundo;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Data inválida para conversão.");
        }
    }
    
    

    //GetTime
    public static LocalDate getHojeLD() {
        return LocalDate.now();
    }

    public static String getHojeSTR() throws Exception {
        return LD_STR(LocalDate.now());
    }

    public static Date getHojeDT() {
        return Date.valueOf(LocalDate.now());
    }

    public static LocalDate getAmanhaLD() {
        return LocalDate.now().plusDays(1);
    }

    public static String getAmanhaSTR() throws Exception {
        return LD_STR(LocalDate.now().plusDays(1));
    }

    public static Date getAmanhaDT() {
        return Date.valueOf(LocalDate.now().plusDays(1));
    }

    public static LocalDate getOntemLD() {
        return LocalDate.now().minusDays(1);
    }

    public static String getOntemSTR() throws Exception {
        return LD_STR(LocalDate.now().minusDays(1));
    }

    public static Date getOntemDT() {
        return Date.valueOf(LocalDate.now().minusDays(1));
    }

    public static LocalDateTime getAgoraLDT() {
        return LocalDateTime.now();
    }

    public static String getAgoraSTR() throws Exception {
        return LDT_STR(LocalDateTime.now());
    }

    public static Timestamp getAgoraTMP() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public static String BD_STR(BigDecimal bd) throws Exception {
        try {
            if (bd != null) {
                return bd.toPlainString();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Conversão inválida ");
        }
    }

    public static Double BD_DBL(BigDecimal bd) throws Exception {
        try {
            if (bd != null) {
                return bd.doubleValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Conversão inválida ");
        }
    }

    public static BigDecimal INT_BD(int it) throws Exception {
        try {
            if (it != 0) {
                return BigDecimal.valueOf(it);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Conversão inválida");
        }
    }

    public static String INT_STR(int it) throws Exception {
        try {
            if (it != 0) {
                return Integer.toString(it);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Conversão inválida ");
        }
    }

    public static BigDecimal STR_BD(String str) throws Exception {
        try {
            if (str != null) {
                return BigDecimal.valueOf(Double.parseDouble(str));
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Conversão inválida ");
        }
    }

    public static int STR_INT(String str) throws Exception {
        try {
            if (str != null) {
                int a = Integer.parseInt(str);
                return a;
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new Exception("Conversão inválida ");
        }
    }

    public static Double STR_DBL(String str) throws Exception {
        try {
            if (str != null) {
                return Double.valueOf(str);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Conversão inválida ");
        }
    }

//    public
    public static String validaDataNasc(String data) throws Exception {
        LocalDate ldate;
        LocalDate hoje;
//        LocalDate variavel;
        hoje = LocalDate.now();

        data = validaData(data);

        if (!data.equals("")) {

            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(3, 5));
            int ano = Integer.parseInt(data.substring(6, 10));

            ldate = LocalDate.of(ano, mes, dia);

            if (ldate.isAfter(hoje)) {
                throw new Exception("Esta data não pode ser superior à data de hoje.");

            } else if (ldate.isAfter(hoje.minusYears(10))) {

                int option = JOptionPane.showConfirmDialog(null, "A data digitada '" + data + "' está correta?");
                if (option != 0) {
                    throw new Exception("Você deve digitar uma data válida.");
                }
            }
        }
        return data;
    }

    public static String validaDataMenorHoje(String data) throws Exception {
        LocalDate ldate;
        LocalDate hoje;
        hoje = LocalDate.now();
        data = validaData(data);
        if (!data.equals("")) {

            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(3, 5));
            int ano = Integer.parseInt(data.substring(6, 10));

            ldate = LocalDate.of(ano, mes, dia);
            if (ldate.isAfter(hoje)) {
                throw new Exception("Esta data não pode ser superior à data de hoje.");
            }
        }
        return data;
    }

    public static String validaData(String data) throws Exception {
        //Maior que ano base 1900++, menor que ano base 2000++
        //98 = 1998
        //14 = 2014
        if (!data.equals("")) {

            int anoBase = 40;

            String message1 = "Formato de Data incorreta.";
            String message2 = "A data é inválida.";

            LocalDate ldate;
            LocalDate hoje;
            hoje = LocalDate.now();
            String formatado;

            int qtBarra = 0;
            for (int i = 0; i < data.length(); i++) {
                if (data.charAt(i) == '/') {
                    qtBarra++;
                }
            }

            int dia = 0;
            int mes = 0;
            int ano = 0;
            try {
                switch (qtBarra) {
                    case 0:
                        if (data.length() == 1) {
                            data = "0" + data;
                            dia = Integer.parseInt(data);
                            mes = hoje.getMonthValue();
                            ano = hoje.getYear();
                        } else if (data.length() == 2) {
                            dia = Integer.parseInt(data);
                            mes = hoje.getMonthValue();
                            ano = hoje.getYear();
                        } else if (data.length() == 4) {
                            dia = Integer.parseInt(data.substring(0, 2));
                            mes = Integer.parseInt(data.substring(2, 4));
                            ano = hoje.getYear();
                        } else if (data.length() == 6) {
                            dia = Integer.parseInt(data.substring(0, 2));
                            mes = Integer.parseInt(data.substring(2, 4));
                            ano = Integer.parseInt(data.substring(4, 6));
                            if (ano > anoBase) {
                                ano = ano + 1900;
                            } else {
                                ano = ano + 2000;
                            }
                        } else if (data.length() == 8) {
                            dia = Integer.parseInt(data.substring(0, 2));
                            mes = Integer.parseInt(data.substring(2, 4));
                            ano = Integer.parseInt(data.substring(4, 8));
                        } else {
                            throw new Exception(message1);
                        }
                        break;
                    case 1:
                        if (data.length() == 5) {
                            dia = Integer.parseInt(data.substring(0, 2));
                            mes = Integer.parseInt(data.substring(3, 5));
                            ano = hoje.getYear();
                        } else {
                            throw new Exception(message1);
                        }

                        break;
                    case 2:
                        if (data.length() == 8) {
                            dia = Integer.parseInt(data.substring(0, 2));
                            mes = Integer.parseInt(data.substring(3, 5));
                            ano = Integer.parseInt(data.substring(6, 8));
                            if (ano > anoBase) {
                                ano = ano + 1900;
                            } else {
                                ano = ano + 2000;
                            }

                        } else if (data.length() == 10) {
                            dia = Integer.parseInt(data.substring(0, 2));
                            mes = Integer.parseInt(data.substring(3, 5));
                            ano = Integer.parseInt(data.substring(6, 10));
                        } else {
                            throw new Exception(message1);
                        }

                        break;
                    default:
                        throw new Exception(message1);
                }

            } catch (NumberFormatException ne) {
                throw new Exception(message2);
            }
            //Formatação de data para padrão Brasileiro médio dd/mm/aaaa
            String sdia = Integer.toString(dia);
            String smes = Integer.toString(mes);
            String sano = Integer.toString(ano);
            if (sdia.length() < 2) {
                sdia = "0" + sdia;
            }
            if (smes.length() < 2) {
                smes = "0" + smes;
            }
            if (sano.length() < 4) {
                for (int i = 0; i < (4 - sano.length()); i++) {
                    sano = "0" + sano;
                }
            }
            formatado = sdia + "/" + smes + "/" + sano;
            try {
                ldate = LocalDate.of(ano, mes, dia);
                return formatado;
            } catch (Exception e) {
                throw new Exception(message2);
            }
        } else {
            return "";
        }

    }

    public static String toDatePT(String data) throws Exception {
        if ((data.charAt(4) != '-' || data.charAt(7) != '-') && (data.charAt(4) != '/' || data.charAt(7) != '/')) {
            throw new Exception("Formato de data incorreto. (aaaa-mm-dd)");
        }
        String dia = data.substring(8, 10);
        String mes = data.substring(5, 7);
        String ano = data.substring(0, 4);
        String date = dia + "/" + mes + "/" + ano;

        return validaData(date);
    }

    public static String getHoje() {
        LocalDate hj = LocalDate.now();
        String data = java.sql.Date.valueOf(hj).toString();
        String dia = data.substring(8, 10);
        String mes = data.substring(5, 7);
        String ano = data.substring(0, 4);
        data = dia + "/" + mes + "/" + ano;
        return data;
    }

//    public static LocalDate dateStrToLocalDate(String data) throws Exception {
//        //    passar data assim: "31/01/2014"
//        
//    }
    //ALERTAS DE MENSAGENS SECAS
    public static void msgErro(String titulo, String texto) {
        JOptionPane.showMessageDialog(null, texto, titulo, 0);
    }

    public static void msgErro(String texto) {
        msgErro("Erro no sistema:", texto);
    }

    public static void msgInfo(String titulo, String texto) {
        JOptionPane.showMessageDialog(null, texto, titulo, 1);
    }

    public static void msgInfo(String texto) {
        msgInfo("Informação", texto);
    }

    public static void msgAlerta(String titulo, String texto) {
        JOptionPane.showMessageDialog(null, texto, titulo, 2);
    }

    public static void msgAlerta(String texto) {
        msgAlerta("Alerta", texto);
    }

    public static void msgQuestao(String titulo, String texto) {
        JOptionPane.showMessageDialog(null, texto, titulo, 3);
    }

    public static void msgQuestao(String texto) {
        msgQuestao("Importante", texto);
    }
    
    public static void msgException(Exception e) {
        if (e instanceof AlertaException){
            Pandora.msgAlerta(e.getMessage());
        } else if (e instanceof InfoException){
            Pandora.msgInfo(e.getMessage());
        } else if (e instanceof NullPointerException){
            Pandora.msgErro(e.getMessage() + "\nNullPointerException");
        } else if (e instanceof Exception){
            Pandora.msgErro(e.getMessage());
        }
    }
    
    public static void ex(Exception e) {
        msgException(e);
    }
    

    public static void nextField(JPanel painel) {
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    }

    public static void nextField(JFrame painel) {
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    }

    
    public static Date convDate(LocalDate localDate){
        if (localDate == null){
            return null;
        } else {
            try {
                return Pandora.LD_DT(localDate);
            } catch (Exception ex) {
                return null;
            }
        }
    }
    
    public static Timestamp convTimesTamp(LocalDateTime localDate){
        if (localDate == null){
            return null;
        } else {
            try {
                return Pandora.LDT_TMP(localDate);
            } catch (Exception ex) {
                return null;
            }
        }
    }
    
    public static LocalDate convLocalDate(Date date){
        if (date == null){
            return null;
        } else {
            try {
                return Pandora.DT_LD(date);
            } catch (Exception ex) {
                return null;
            }
        }
    }
    
    public static LocalDateTime convLocalDateTime(Timestamp date){
        if (date == null){
            return null;
        } else {
            try {
                return Pandora.TMP_LDT(date);
            } catch (Exception ex) {
                return null;
            }
        }
    }
    
    
    public static String imprimeCPF(String CPF) throws Exception{
        if (CPF.equals("")){
            return CPF;
        }
        CPF = CPF.replace("-", "");
        CPF = CPF.replace(".", "");
        CPF = CPF.replace(" ", "");
        
        if (CPF.length() != 11){
            throw new Exception("CPF inválido");
        }
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
    
    public static String imprimeCNPJ(String cnpj) throws Exception{
        if (cnpj.equals("")){
            return cnpj;
        }
        cnpj = cnpj.replace("/", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace(" ", "");
        
        if (cnpj.length() != 14){
            throw new Exception("CNPJ inválido");
            
        }
        return(cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "." + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14)); 
    }
    
    public static String imprimeTelefone(String telefone) throws Exception{
        if (telefone.equals("")){
            return telefone;
        }
        telefone = telefone.replace("(", "");
        telefone = telefone.replace(")", "");
        telefone = telefone.replace("/", "");
        telefone = telefone.replace("-", "");
        telefone = telefone.replace(".", "");
        telefone = telefone.replace(" ", "");
        
        if (telefone.length() != 10 && telefone.length() != 11){
            throw new Exception("Telefone inválido");
        }
        
        if (telefone.length() == 10){
            return("(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10)); 
        } else {
            return("(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 3) + " " +  telefone.substring(3, 7) + "-" + telefone.substring(7, 11)); 
        }
    }
    
    
    public static String getVersaoPC(){
        String result = "";
        try {
            //File file = File.createTempFile("tmp",".vbs");
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
 
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" + "Set colDrives = objFSO.Drives\n"
                            + "Set objDrive = colDrives.item(\"C\")\n" + "Wscript.Echo objDrive.SerialNumber";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
        }
        if (result.trim().length() < 1  || result == null) {
            result = "NO_DISK_ID";
        }
        return result.trim();
    }
    
    public static void main(String[] args) {
        Pandora.msgAlerta(getVersaoPC());
    }
    
}
