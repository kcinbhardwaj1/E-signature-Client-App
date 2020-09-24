package com.rgp.de.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.docusign.esign.model.CarbonCopy;
import com.docusign.esign.model.Document;
import com.docusign.esign.model.EnvelopesInformation;
import com.docusign.esign.model.RecipientViewRequest;
import com.docusign.esign.model.Recipients;
import com.docusign.esign.model.SignHere;
import com.docusign.esign.model.Signer;
import com.docusign.esign.model.Tabs;
import com.rgp.de.beans.DocumentDTO;
import com.rgp.de.beans.DocumentResult;
import com.rgp.de.beans.EmbedSummary;
import com.rgp.de.beans.EmbeddedDTO;
import com.rgp.de.beans.Envelope;
import com.rgp.de.beans.EnvelopeDTO;
import com.rgp.de.beans.EnvelopeDTOWithoutTemp;
import com.rgp.de.beans.EnvelopeDef;
import com.rgp.de.beans.EnvelopeQuery;
import com.rgp.de.beans.EnvelopeQueryStrings;
import com.rgp.de.beans.EnvelopeResponse;
import com.rgp.de.beans.Template;
import com.rgp.de.enums.DocumentType;
import com.rgp.de.util.EnvelopeComparator;
import com.sun.jersey.core.util.Base64;

@Controller
public class ClientController {

	private Logger logger = LoggerFactory.getLogger(ClientController.class);

	private static final String ZIP_EXTENSION = "zip";
	private static final String HTTP_CONTENT_DISPOSITION_VALUE = "inline;filename=";
	private List<Template> templateList;
	
	private static List<Envelope> envelopeList=new ArrayList<Envelope>();
	private Map<String, List<Envelope>> envelopeRepo = new HashMap<String, List<Envelope>>();

	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "pages/index";
	}

	@RequestMapping(value = "/templateDetails", method = RequestMethod.GET)
	public ModelAndView getTemplateDetails(@RequestParam String templateId) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/main/signingViaEmail2");

		for (Template template : templateList) {
			if (template.getTemplateId().equals(templateId)) {
				modelAndView.addObject("done", template);
				logger.info("Template: " + template);
				break;
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/templateDetailsOfEmbed", method = RequestMethod.GET)
	public ModelAndView getTemplateDetailsForEmbed(@RequestParam String templateId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/main/embedSigning2");

		for (Template template : templateList) {
			if (template.getTemplateId().equals(templateId)) {
				modelAndView.addObject("done", template);
				logger.info("Template: " + template);
				break;
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/signingViaEmail", method = RequestMethod.GET)
	public ModelAndView signing() throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		try {
			final String baseUrl = env.getProperty("template.Url");
			URI uri = new URI(baseUrl);

			ResponseEntity<Template[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, Template[].class);

			templateList = Arrays.asList(response.getBody());

			modelAndView.setViewName("pages/main/signingViaEmail");
			
			modelAndView.addObject("done", response.getBody());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/embedSigning", method = RequestMethod.GET)
	public ModelAndView embedSigning(Model model) throws URISyntaxException {
		ModelAndView modelAndView = new ModelAndView();
		try {
			final String baseUrl = env.getProperty("template.Url");
			URI uri = new URI(baseUrl);

			ResponseEntity<Template[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, Template[].class);

			templateList = Arrays.asList(response.getBody());

			modelAndView.addObject("done", response.getBody());
			modelAndView.setViewName("pages/main/embedSigning");

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/viewUrl", method = RequestMethod.GET)
	public ModelAndView embedViwUrl(@RequestParam String url) throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("url", url);
		modelAndView.setViewName("pages/main/embed2");
		return modelAndView;

	}

	@RequestMapping(value = "/done", method = RequestMethod.GET)
	public String success(Model model) {
		return "pages/done";

	}

	@RequestMapping(value = "/remoteSigning", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView createRemoteSigningEnvelope(EnvelopeDTO envelopeDTO, ModelMap model,
			HttpServletResponse serveletResponse) throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		EnvelopeResponse envelopeResponse = new EnvelopeResponse();
		try {

			final String baseUrl = env.getProperty("envelope.Url") + "/template";
			URI uri = new URI(baseUrl);

			EnvelopeDef envelopeDef = new EnvelopeDef();
			envelopeDef.setEmailSubject(envelopeDTO.getEmailSubject());
			envelopeDef.setStatus("sent");
			Recipients recipients = new Recipients();
			List<Signer> signerList = new ArrayList<Signer>();

			if (null != envelopeDTO.getSignerName() && !envelopeDTO.getSignerName().isEmpty()) {
				for (Integer i = 0, j = 1; i < envelopeDTO.getSignerName().size(); i++, j++) {
					Signer signer = new Signer();
					signer.setName(envelopeDTO.getSignerName().get(i));
					signer.setEmail(envelopeDTO.getSignerEmail().get(i));
					signer.setRoutingOrder(j.toString());
					signerList.add(signer);
				}
			}

			List<CarbonCopy> ccList = new ArrayList<CarbonCopy>();

			if (null != envelopeDTO.getCcName() && !envelopeDTO.getCcName().isEmpty()) {
				for (Integer i = 0, j = envelopeDTO.getSignerName().size() + 1; i < envelopeDTO.getCcName()
						.size(); i++, j++) {
					CarbonCopy carbonCopy = new CarbonCopy();
					carbonCopy.setName(envelopeDTO.getCcName().get(i));
					carbonCopy.setEmail(envelopeDTO.getCcEmail().get(i));
					carbonCopy.setRoutingOrder(j.toString());
					ccList.add(carbonCopy);
				}
			}

			recipients.setSigners(signerList);
			recipients.setCarbonCopies(ccList);
			envelopeDef.setRecipients(recipients);
			envelopeDef.setEmailSubject(envelopeDTO.getEmailSubject());
			envelopeDef.setTemplateId(envelopeDTO.getTemplateId());

			HttpEntity<EnvelopeDef> envelopeDefinition = new HttpEntity<EnvelopeDef>(envelopeDef);
			ResponseEntity<EnvelopeResponse> response = restTemplate.exchange(uri, HttpMethod.POST, envelopeDefinition,
					EnvelopeResponse.class);
			envelopeResponse = (EnvelopeResponse) response.getBody();
			envelopeResponse.setTemplate(true);
			// JSONObject jsonObject = new JSONObject(envelopeResponse);
			// String json = jsonObject.toString(4);
			modelAndView.setViewName("pages/example_done");
			modelAndView.addObject("done", envelopeResponse);

			logger.info("Remote signing response: " + envelopeResponse.toString());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/embeddedSigning", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView createEmbeddedSigningEnvelope(EnvelopeDTO envelopeDTO, ModelMap model,
			HttpServletResponse serveletResponse) throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		try {

			final String baseUrl = env.getProperty("envelope.Url") + "/embed/template";

			URI uri = new URI(baseUrl);
			EnvelopeDef envelopeDef = new EnvelopeDef();
			envelopeDef.setEmailSubject(envelopeDTO.getEmailSubject());
			envelopeDef.setStatus("sent");

			Recipients recipients = new Recipients();
			List<Signer> signerList = new ArrayList<Signer>();
			for (Integer i = 0; i < envelopeDTO.getSignerName().size(); i++) {
				Signer signer = new Signer();
				signer.setName(envelopeDTO.getSignerName().get(i));
				signer.setEmail(envelopeDTO.getSignerEmail().get(i));
				// signer.setRoutingOrder("0");
				signerList.add(signer);
			}
			recipients.setSigners(signerList);
			envelopeDef.setRecipients(recipients);
			envelopeDef.setEmailSubject(envelopeDTO.getEmailSubject());
			envelopeDef.setTemplateId(envelopeDTO.getTemplateId());

			RecipientViewRequest recipientViewRequest = new RecipientViewRequest();
			recipientViewRequest.setReturnUrl(env.getProperty("server.returnUrl"));
			recipientViewRequest.setAuthenticationMethod("None");
			envelopeDef.setRecipientViewRequest(recipientViewRequest);
			HttpEntity<EnvelopeDef> envelopeDefinition = new HttpEntity<EnvelopeDef>(envelopeDef);

			List<EmbeddedDTO> embeddedList = new ArrayList<EmbeddedDTO>();

			ResponseEntity<EmbedSummary> response = restTemplate.exchange(uri, HttpMethod.POST, envelopeDefinition,
					EmbedSummary.class);
			//embeddedList = Arrays.asList(response.getBody());
			//embeddedList.get(0).setTemplate(true);
			response.getBody().setIsTemplate(true);
			modelAndView.setViewName("pages/main/embed");
			modelAndView.addObject("done", response.getBody());
			//modelAndView.addObject("envelopeId", embeddedList.get(0).getEnvelopeId());			
			logger.info("Embedded signing response: " + embeddedList);

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/listEnvelopes", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listEnvelopes() throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		EnvelopesInformation envelopesInformation = new EnvelopesInformation();
		try {
			Integer days = 7;
			final String baseUrl = env.getProperty("envelope.Url") + "/" + days;
			URI uri = new URI(baseUrl);
			ResponseEntity<EnvelopesInformation> response = restTemplate.exchange(uri, HttpMethod.GET, null,
					EnvelopesInformation.class);
			envelopesInformation = response.getBody();
			modelAndView.setViewName("pages/main/envelopeList");
			modelAndView.addObject("done", envelopesInformation);

			// logger.info("Remote signing response: " + envelopesInformation.toString());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
		}

		return modelAndView;

	}

	@RequestMapping(value = "/filterEnvelopes", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView filterEnvelopes(EnvelopeQuery envelopeQuery, ModelMap model) throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		EnvelopeQueryStrings envelopeQueryStrings = new EnvelopeQueryStrings();
		EnvelopesInformation envelopesInformation = new EnvelopesInformation();
		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate formattedFromDate = null;
			LocalDate formattedToDate = null;

			if (null != envelopeQuery.getEnvCreatedFrom() && !envelopeQuery.getEnvCreatedFrom().isEmpty()) {
				formattedFromDate = LocalDate.parse(envelopeQuery.getEnvCreatedFrom(), formatter);
				envelopeQueryStrings.setFromDate(formattedFromDate.toString());
			}

			if (null != envelopeQuery.getEnvCreatedTo() && !envelopeQuery.getEnvCreatedTo().isEmpty()) {
				formattedToDate = LocalDate.parse(envelopeQuery.getEnvCreatedTo(), formatter);
				envelopeQueryStrings.setToDate(formattedToDate.toString());
			}

			if (null != envelopeQuery.getUserEmail() && !envelopeQuery.getUserEmail().equalsIgnoreCase("select")) {
				envelopeQueryStrings.setEmail(envelopeQuery.getUserEmail());
			}

			if (null != envelopeQuery.getEnvStatus() && !envelopeQuery.getEnvStatus().equalsIgnoreCase("select")) {
				envelopeQueryStrings.setStatus(envelopeQuery.getEnvStatus());
			}

			if (null != envelopeQuery.getEnvOrder() && !envelopeQuery.getEnvOrder().equalsIgnoreCase("select")) {
				envelopeQueryStrings.setOrder(envelopeQuery.getEnvOrder());
			}

			if (null != envelopeQuery.getEnvOrderBy() && !envelopeQuery.getEnvOrderBy().equalsIgnoreCase("select")) {
				envelopeQueryStrings.setOrderBy(envelopeQuery.getEnvOrderBy());
			}

			if (null != envelopeQuery.getEnvCount() && !envelopeQuery.getEnvCount().equalsIgnoreCase("select")) {
				envelopeQueryStrings.setCount(envelopeQuery.getEnvCount());
			}

			final String baseUrl = env.getProperty("envelope.Url") + "/query";
			URI uri = new URI(baseUrl);
			HttpEntity<EnvelopeQueryStrings> httpEntity = new HttpEntity<EnvelopeQueryStrings>(envelopeQueryStrings);
			ResponseEntity<EnvelopesInformation> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,
					EnvelopesInformation.class);
			envelopesInformation = response.getBody();

			modelAndView.setViewName("pages/main/envelopeList");
			modelAndView.addObject("done", envelopesInformation);

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/main/envelopeList");
			modelAndView.addObject("done", envelopesInformation);
		}

		return modelAndView;

	}

	@RequestMapping(value = "/downloadAllDocs/{envelopeId}", method = RequestMethod.GET)
	@ResponseBody
	public Object downloadAllDocuments(@PathVariable String envelopeId, HttpServletResponse response)
			throws URISyntaxException {

		List<DocumentDTO> documentDTOList = new ArrayList<DocumentDTO>();
		try {

			final String baseUrl = env.getProperty("envelope.Url") + "/" + envelopeId + "/document";
			URI uri = new URI(baseUrl);
			ResponseEntity<DocumentDTO[]> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null,
					DocumentDTO[].class);
			if (responseEntity.getBody() != null || responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				documentDTOList = Arrays.asList(responseEntity.getBody());
			}

			String zipFileName = "Docs".concat(".zip");
			ServletOutputStream sos = response.getOutputStream();
			ZipOutputStream zos = new ZipOutputStream(sos);

			for (DocumentDTO documentDTO : documentDTOList) {

				byte[] byteArray = Base64.decode(documentDTO.getDocumentBase64().getBytes());

				String docName = documentDTO.getDocumnetName();
				String docType = documentDTO.getDocumnetType();
				String pdfExtention = DocumentType.PDF.getDefaultFileExtention();

				if (StringUtils.equalsAny(docType, "content", "summary", pdfExtention)) {
					docName = addExtension(docName, pdfExtention);
				}

				if (ZIP_EXTENSION.equals(docType)) {
					docName = addExtension(docName, ZIP_EXTENSION);
				}
				zos.putNextEntry(new ZipEntry(docName));
				zos.write(byteArray, 0, byteArray.length);
				zos.closeEntry();
			}

			response.setContentType("application/zip");
			// response.setContentLength(zos.length);
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zipFileName);
			response.flushBuffer();
			zos.finish();
			zos.close();

		} catch (Exception ex) {
			ModelAndView modelAndView = new ModelAndView();
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
			return modelAndView;
		}

		return null;

	}

	@RequestMapping(value = "/downloadDoc/{envelopeId}/{docId}", method = RequestMethod.GET)
	@ResponseBody
	public Object downloadDocument(@PathVariable String envelopeId, @PathVariable String docId,
			HttpServletResponse response) throws URISyntaxException {

		DocumentDTO documentDTO = new DocumentDTO();
		try {

			final String baseUrl = env.getProperty("envelope.Url") + "/" + envelopeId + "/document/" + docId;
			URI uri = new URI(baseUrl);
			ResponseEntity<DocumentDTO> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null,
					DocumentDTO.class);
			if (responseEntity.getBody() != null || responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				documentDTO = responseEntity.getBody();
			}

			byte[] byteArray = Base64.decode(documentDTO.getDocumentBase64().getBytes());

			String docName = documentDTO.getDocumnetName();
			String docType = documentDTO.getDocumnetType();
			String pdfExtention = DocumentType.PDF.getDefaultFileExtention();
			if (StringUtils.equalsAny(docType, "content", "summary", pdfExtention)) {
				docName = addExtension(docName, pdfExtention);
			}
			if (ZIP_EXTENSION.equals(docType)) {
				docName = addExtension(docName, ZIP_EXTENSION);
			}

			response.setContentType(URLConnection.guessContentTypeFromName(docName));
			response.setContentLength(byteArray.length);
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, HTTP_CONTENT_DISPOSITION_VALUE + docName);
			response.getOutputStream().write(byteArray);
			response.flushBuffer();

		} catch (Exception ex) {
			ModelAndView modelAndView = new ModelAndView();
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
			return modelAndView;
		}
		return null;
	}

	@RequestMapping(value = "/deleteDoc/{envelopeId}/{docId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView deleteDocument(@PathVariable String envelopeId, @PathVariable String docId,
			HttpServletResponse response) throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		DocumentResult envelopeDocumentsResult = new DocumentResult();
		try {
			final String baseUrl = env.getProperty("envelope.Url") + "/" + envelopeId + "/document/" + docId;
			URI uri = new URI(baseUrl);
			ResponseEntity<DocumentResult> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, null,
					DocumentResult.class);
			if (responseEntity.getBody() != null || responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				envelopeDocumentsResult = responseEntity.getBody();

				JSONObject jsonObject = new JSONObject(envelopeDocumentsResult);
				String json = jsonObject.toString(4);
				modelAndView.setViewName("pages/delete_done");
				modelAndView.addObject("done", json);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
		}
		return modelAndView;
	}

	private static String addExtension(String fileName, String extension) {
		if (FilenameUtils.isExtension(fileName, extension)) {
			return fileName;
		}
		return String.join(".", fileName, extension);
	}

	// without template code start

	@RequestMapping(value = "/signingViaEmailWithoutTemp", method = RequestMethod.GET)
	public String signing(Model model) {
		return "pages/main/signingViaEmailWithoutTemp";

	}

	@RequestMapping(value = "/embedSigningWithoutTemp", method = RequestMethod.GET)
	public String embedSigningWithoutTemp(Model model) {
		return "pages/main/embedSigningWithoutTemp";

	}

	@RequestMapping(value = "/remoteSigningWithoutTemp", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	@ResponseBody
	public ModelAndView createRemoteSigningEnvelopeWithoutTemp(EnvelopeDTOWithoutTemp envelopeDTO, ModelMap model,
			HttpServletResponse serveletResponse) throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		EnvelopeResponse envelopeResponse = new EnvelopeResponse();
		try {

			final String baseUrl = env.getProperty("envelope.Url");
			URI uri = new URI(baseUrl);

			EnvelopeDef envelopeDef = new EnvelopeDef();
			envelopeDef.setEmailSubject(envelopeDTO.getEmailSubject());
			envelopeDef.setStatus("sent");
			Recipients recipients = new Recipients();
			List<Signer> signerList = new ArrayList<Signer>();
			Signer signer1 = new Signer();
			signer1.setEmail(envelopeDTO.getSignerEmail());
			signer1.setName(envelopeDTO.getSignerName());
			// signer1.setRecipientId("1");
			signer1.setRoutingOrder("1");

			Signer signer2 = new Signer();
			signer2.setEmail(envelopeDTO.getSignerEmail2());
			signer2.setName(envelopeDTO.getSignerName2());
			// signer2.setRecipientId("2");
			signer2.setRoutingOrder("2");

			SignHere signHere1 = new SignHere();
			signHere1.setAnchorString("/sn1/");
			signHere1.setAnchorUnits("pixels");
			signHere1.setAnchorXOffset("20");
			signHere1.anchorYOffset("10");
			signHere1.setDocumentId("1");
			signHere1.setPageNumber("1");

			SignHere signHere2 = new SignHere();
			signHere2.setAnchorString("/sn2/");
			signHere2.setAnchorUnits("pixels");
			signHere2.setAnchorXOffset("20");
			signHere2.anchorYOffset("10");
			signHere2.setDocumentId("1");
			signHere2.setPageNumber("4");

			// Tabs are set per recipient / signer
			Tabs tabs1 = new Tabs();
			tabs1.setSignHereTabs(Arrays.asList(signHere1));
			signer1.setTabs(tabs1);
			signerList.add(signer1);

			Tabs tabs2 = new Tabs();
			tabs2.setSignHereTabs(Arrays.asList(signHere2));
			signer2.setTabs(tabs2);
			signerList.add(signer2);

			List<CarbonCopy> ccList = new ArrayList<CarbonCopy>();
			CarbonCopy carbonCopy = new CarbonCopy();
			carbonCopy.setEmail(envelopeDTO.getCcEmail());
			carbonCopy.setName(envelopeDTO.getCcName());
			// carbonCopy.setRecipientId("3");
			carbonCopy.setRoutingOrder("3");
			ccList.add(carbonCopy);

			recipients.setSigners(signerList);
			recipients.setCarbonCopies(ccList);

			List<Document> documentList = new ArrayList<Document>();
			Document document = new Document();
			document.setName(envelopeDTO.getFile().getOriginalFilename().substring(0,
					envelopeDTO.getFile().getOriginalFilename().lastIndexOf(".")));

			document.setFileExtension(envelopeDTO.getFile().getOriginalFilename().substring(
					envelopeDTO.getFile().getOriginalFilename().lastIndexOf(".") + 1,
					envelopeDTO.getFile().getOriginalFilename().length()));
			document.setDocumentId("1");
			document.setDocumentBase64(new String(Base64.encode(envelopeDTO.getFile().getBytes())));
			documentList.add(document);

			envelopeDef.setRecipients(recipients);
			envelopeDef.setEmailSubject(envelopeDTO.getEmailSubject());
			envelopeDef.setDocuments(documentList);
			HttpEntity<EnvelopeDef> envelopeDefinition = new HttpEntity<EnvelopeDef>(envelopeDef);
			ResponseEntity<EnvelopeResponse> response = restTemplate.exchange(uri, HttpMethod.POST, envelopeDefinition,
					EnvelopeResponse.class);

			envelopeResponse = (EnvelopeResponse) response.getBody();
			envelopeResponse.setTemplate(false);

			modelAndView.setViewName("pages/example_done");
			modelAndView.addObject("done", envelopeResponse);

			logger.info("Remote signing envelopeDTO details: " + envelopeDTO.toString());
			logger.info("Remote signing response: " + envelopeResponse.toString());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/embeddedSigningWithoutTemp", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	@ResponseBody
	public ModelAndView createEmbeddedSigningEnvelopeWithoutTemp(EnvelopeDTOWithoutTemp envelopeDTO, ModelMap model,
			HttpServletResponse serveletResponse) throws URISyntaxException {

		ModelAndView modelAndView = new ModelAndView();
		// RedirectView redirect = null;
		try {

			final String baseUrl = env.getProperty("envelope.Url") + "/embed";
			URI uri = new URI(baseUrl);
			EnvelopeDef envelopeDef = new EnvelopeDef();
			envelopeDef.setEmailSubject(envelopeDTO.getEmailSubject());
			envelopeDef.setStatus("sent");

			Recipients recipients = new Recipients();
			List<Signer> signerList = new ArrayList<Signer>();

			Signer signer1 = new Signer();
			signer1.setEmail(envelopeDTO.getSignerEmail());
			signer1.setName(envelopeDTO.getSignerName());
			signer1.setRoutingOrder("0");
			
			SignHere signHere1 = new SignHere(); 
			signHere1.setAnchorString("/sn1/");
			signHere1.setAnchorUnits("pixels");
			signHere1.setAnchorXOffset("20");
			signHere1.anchorYOffset("10"); 
			signHere1.setDocumentId("1");
			signHere1.setPageNumber("1");
			  
			// Tabs are set per recipient / signer 
			Tabs tabs1 = new Tabs();
			tabs1.setSignHereTabs(Arrays.asList(signHere1)); signer1.setTabs(tabs1);
			 
			signerList.add(signer1);
			recipients.setSigners(signerList);

			List<Document> documentList = new ArrayList<Document>();
			Document document = new Document();
			document.setName(envelopeDTO.getFile().getOriginalFilename().substring(0,
					envelopeDTO.getFile().getOriginalFilename().lastIndexOf(".")));

			document.setFileExtension(envelopeDTO.getFile().getOriginalFilename().substring(
					envelopeDTO.getFile().getOriginalFilename().lastIndexOf(".") + 1,
					envelopeDTO.getFile().getOriginalFilename().length()));
			document.setDocumentId("1");
			document.setDocumentBase64(new String(Base64.encode(envelopeDTO.getFile().getBytes())));
			documentList.add(document);

			envelopeDef.setRecipients(recipients);
			envelopeDef.setEmailSubject(envelopeDTO.getEmailSubject());
			envelopeDef.setDocuments(documentList);

			RecipientViewRequest recipientViewRequest = new RecipientViewRequest();
			recipientViewRequest.setReturnUrl(env.getProperty("server.returnUrl"));
			recipientViewRequest.setAuthenticationMethod("None");
			envelopeDef.setRecipientViewRequest(recipientViewRequest);
			HttpEntity<EnvelopeDef> envelopeDefinition = new HttpEntity<EnvelopeDef>(envelopeDef);

			//List<EmbeddedDTO> embeddedList = new ArrayList<EmbeddedDTO>();

			ResponseEntity<EmbedSummary> response = restTemplate.exchange(uri, HttpMethod.POST, envelopeDefinition,
					EmbedSummary.class);

			logger.info("Embedded signing envelopeDTO details: " + envelopeDTO.toString());
			//embeddedList = response.getBody().getEmbedList();
			//embeddedList.get(0).setTemplate(false);
			response.getBody().setIsTemplate(false);
			modelAndView.setViewName("pages/main/embed");
			modelAndView.addObject("done", response.getBody());
			//modelAndView.addObject("envelopeId", embeddedList.get(0).getEnvelopeId());
			
			logger.info("Embedded signing response: " + response.getBody().getEmbedList());

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/error");
			modelAndView.addObject("done", ex.getMessage());
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/getLatestStatus/{envelopeId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getEnvelopeStatus(@PathVariable String envelopeId) throws URISyntaxException {
		
		ModelAndView modelAndView= new ModelAndView();
		
		try {
			
			logger.info("In get status Envelope Id: "+envelopeId);
			
			Envelope envelope=new Envelope();
			final String baseUrl = env.getProperty("envelope.Url") + "/status/" + envelopeId;
			URI uri = new URI(baseUrl);
			ResponseEntity<Envelope> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null,
					Envelope.class);
			envelope = responseEntity.getBody();
			
			logger.info("Newely fetched Envelope Status:"+envelope);
			
			List<Envelope> envStatus =new ArrayList<Envelope>();
			
			if(envelopeRepo.containsKey(envelopeId)){	
				envStatus = envelopeRepo.get(envelopeId);	
				logger.info("Envelope List based on id: "+ envStatus);	
				envStatus.add(envelope);
				envelopeRepo.put(envelopeId, envStatus);
				
			}else{
				
				envStatus.add(envelope);
				envelopeRepo.put(envelope.getEnvelopeId(), envStatus);
			}
			
			List<Envelope> distinctList = getDistinctEnvelope(envStatus);
			
	
			EnvelopeComparator envelopeComparator = new EnvelopeComparator();

			List<Envelope> sortedList = distinctList.stream().
					sorted(envelopeComparator).collect(Collectors.toList());
			
			modelAndView.setViewName("pages/main/webhook");
			modelAndView.addObject("envelopeList", sortedList);
			
			//boolean flag=removeEnvelopeCompleted(envelope);
			//modelAndView.addObject("flag", flag);
			logger.info("Envelope latest status: " + sortedList);


		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			modelAndView.setViewName("pages/main/webhook");
			modelAndView.addObject("envelopeList", null);
			return modelAndView;
		}
		return modelAndView;
	}

	
	private boolean removeEnvelopeCompleted(Envelope envelope){
		
		boolean envStatus = false;
		if (envelope.getStatus().equalsIgnoreCase("Completed")) {
			for (com.rgp.de.beans.Signer signer : envelope.getRecipients().getSigners()) {
				if (signer.getStatus().equalsIgnoreCase("Completed")) {
					envStatus = true;
				} else {
					envStatus = false;
					break;
				}
			}

			if (envStatus) {
				for (com.rgp.de.beans.CarbonCopy cc : envelope.getRecipients().getCarbonCopy()) {
					if (cc.getStatus().equalsIgnoreCase("Completed")) {
						envStatus = true;
					} else {
						envStatus = false;
						break;
					}
				}
			}
		}
		
		if(envStatus) {
			//envelopeRepo.remove(envelope.getEnvelopeId());
			return envStatus;
		}
		return envStatus;
	}
	
	private List<Envelope> getDistinctEnvelope(List<Envelope>envelopes) {
		
		TreeSet<Envelope> distinctSet = envelopes.stream()
	    .collect(Collectors.toCollection(
	      () -> new TreeSet<Envelope>((e1, e2) -> e1.getStatusChangedDateTime().compareTo(e2.getStatusChangedDateTime()))));
		
		List<Envelope> list = new ArrayList<>(distinctSet);
		return list;

	}
}
