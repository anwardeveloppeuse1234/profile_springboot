package com.exemple.profils.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.profils.entities.profil;
import com.exemple.profils.repos.profilRepository;
import com.exemple.profils.service.profilService;

import dto.FileResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class profilController {
	
	
	@Autowired
	profilService profilService;
	
	@RequestMapping("/showCreate")
	public String showCreate()
	{
	return "createprofil";
	}
	@PostMapping("/createProfil")
	public profil uploadFile(@RequestBody profil  profil) throws IOException {
		return profilService.saveprofil(profil);
	}
	
	@PostMapping("/testSave")
	  public void saveForm(@RequestParam String  name,@RequestParam String  prenom, @RequestPart("image") MultipartFile image) throws IOException {
	    // Enregistrer le formulaire dans la base de données
//	    Form form = new Form();
//	    form.setNom(nom);
		profil p = new profil();
		p.setNumtel(new Long(200));
		p.setNomprofil(name);
		p.setPrenomprofil(prenom);
	   // p.setPhoto(image.getBytes());
	    profilService.saveprofil(p);

	    // Sauvegarder l'image sur le disque ou effectuer tout autre traitement nécessaire
	    // Exemple : image.transferTo(new File("/chemin/vers/le/repertoire/" + image.getOriginalFilename()));
	  }
	
	@RequestMapping(value="/saveprofil" ,method = RequestMethod.POST, consumes = { "multipart/form-data" })

	@CrossOrigin
	public ResponseEntity<String> saveprofil(@RequestParam("file") MultipartFile file ,@ModelAttribute("profil") profil profil
							) throws 	ParseException
	{
		  String uploadPath = "C:\\profil";
		  if (file != null) { 
	Path filepath = Paths.get(uploadPath, file.getOriginalFilename());
    try {
		Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
	} catch (Exception e) {
		// TODO Auto-generated catch block
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file.");

	}
  
    profil.setPhoto(file.getOriginalFilename());
		  }
	profil saveprofil = profilService.saveprofil(profil);
	return ResponseEntity.ok("createprofil");
	}
	
	

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Votre logique de traitement du fichier ici
        if (!file.isEmpty()) {
            try {
                // Obtenez les informations du fichier
                String fileName = file.getOriginalFilename();
                String fileType = file.getContentType();
                long fileSize = file.getSize();

                // Effectuez le traitement souhaité, par exemple, enregistrez le fichier sur le disque
              //  file.transferTo(new File("/chemin/vers/le/repertoire/destination/" + fileName));

                // Retournez une réponse réussie ou effectuez une redirection
                return "redirect:/success";
            } catch (Exception e) {
                // Gérez les erreurs liées à l'upload du fichier
                return "redirect:/error";
            }
        } else {
            // Gérez le cas où aucun fichier n'a été sélectionné
            return "redirect:/error";
        }
    }

	
	
	


	@RequestMapping("/updateprofils")
	public String updateprofil(@ModelAttribute("profil") profil profil,	ModelMap modelMap) throws ParseException
	{
	profilService.updateprofil(profil);
		return "listeprofil";
	}
	

	
	


@RequestMapping("/modifierprofil")
public String editerprofil(@RequestParam("id") Long id,ModelMap modelMap)
{
	profil p= profilService.getprofil(id);
	modelMap.addAttribute("profil", p);
	modelMap.addAttribute("mode", "edit");
	return "formprofil";

}
@RequestMapping("/updateprofil")
public String updateprofil(@ModelAttribute("profil") profil profil,
@RequestParam("date") String date,ModelMap modelMap) throws ParseException {
	
	 profilService.updateprofil(profil);
	 modelMap.addAttribute("profils");
	return "listeprofil";
	}




}
