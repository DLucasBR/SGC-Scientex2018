package br.edu.univasf.util;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Classe para recuperar informações do WS dos correios
 * Origem do codigo: https://github.com/uliss3s/ceputil
 */
public class ClienteWsCorreios {

    private static final Set<String> CAMPOS = new HashSet<String>(Arrays.asList(
            "cep",
            "uf",
            "bairro",
            "cidade",
            "end",
            "complemento",
            "complemento2"
    ));

    public static Map<String, String> getMapPorCep(String cep) throws RuntimeException {

        Document document = getCepResponse(cep);

        Map<String, String> mapa = buscaNodes(document.getChildNodes(), new HashMap<String, String>());

        for (Iterator<Map.Entry<String,String>> it = mapa.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String,String> entry = it.next();
            if (!CAMPOS.contains(entry.getKey())) {
                it.remove();
            }
        }

        return mapa;
    }

    private static Document getCepResponse(String cep) {
        final String wellformedrequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                " xmlns:cli=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<cli:consultaCEP>" +
                "<cep>"+cep+"</cep>" +
                "</cli:consultaCEP>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        DefaultHttpClient httpclient;
        Document document = null;
        try {
            if (!validaCep(cep)) {
                throw new RuntimeException("Formato de CEP inválido");
            }

            httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente");
            httpPost.setHeader(new BasicHeader("Content-Type", "text/xml;charset=UTF-8"));
            httpPost.setHeader(new BasicHeader("SOAPAction", "http://cliente.bean.master.sigep.bsb.correios.com.br/AtendeCliente/consultaCEP"));
            StringEntity s = new StringEntity(wellformedrequest, "UTF-8");
            httpPost.setEntity(s);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream inputStream = entity.getContent();

                byte[] buffer = new byte[(int) entity.getContentLength()];
                inputStream.read(buffer);

                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);

                SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null, bais);

                document = soapMessage.getSOAPBody().extractContentAsDocument();

                inputStream.close();
            }

            httpclient.getConnectionManager().shutdown();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return document;
    }

    private static Map<String, String> buscaNodes(NodeList nodeList, Map<String, String> mapa) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            mapa.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());

            if (nodeList.item(i).getChildNodes() != null) {
                buscaNodes(nodeList.item(i).getChildNodes(), mapa);
            }
        }

        return mapa;
    }
    
    private static boolean validaCep(String cep) {
        if (!cep.matches("^\\d{5}[-]\\d{3}$")) {
            return false;
        }
        return true;
    }
}