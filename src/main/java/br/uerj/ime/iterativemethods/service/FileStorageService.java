package br.uerj.ime.iterativemethods.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.uerj.ime.iterativemethods.exception.FileStorageException;
import br.uerj.ime.iterativemethods.exception.MyFileNotFoundException;
import br.uerj.ime.iterativemethods.property.FileStorageProperties;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Não foi possível criar o diretório para envio do arquivo", e);

		}
	}

	public String storeFile(MultipartFile file) {
		String fileName = normalizeFilename(file.getOriginalFilename());

		try {
			if (containsInvalidCharacters(fileName)) {
				throw new FileStorageException("Arquivo contém caracteres inválidos: " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Não foi possível carregar o " + fileName + ". Por favor tente novamente!", ex);
		}
	}

	private boolean containsInvalidCharacters(String fileName) {		
		return fileName.contains(".."); 
	}
	
	private String normalizeFilename(String originalFilename) {
		return StringUtils.cleanPath(originalFilename);
	}
	
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("Arquivo não encontrado " + fileName);
			}
		} catch (MalformedURLException e) {
			throw new MyFileNotFoundException("Arquivo não encontrado " + fileName, e);
		}
	}

}
