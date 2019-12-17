package br.uerj.ime.iterativemethods.payload;

public class CalculateResponse {

	private String method;
	private double precision;
	private String fileName;
	private String fileDownloadUri;

	public CalculateResponse(String method, double precision, String fileName, String fileDownloadUri) {
		this.method = method;
		this.precision = precision;
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getMethod() {
		return method;
	}

	public double getPrecision() {
		return precision;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

}
