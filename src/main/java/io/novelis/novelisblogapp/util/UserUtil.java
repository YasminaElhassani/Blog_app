package io.novelis.novelisblogapp.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static Long getIdConnectedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long idCurrentUser = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // L'objet d'authentification contient généralement les détails de l'utilisateur
            // Vous devrez adapter ceci selon votre configuration d'authentification
            idCurrentUser =Long.parseLong(authentication.getPrincipal().toString());
        }

        // Assurez-vous que l'ID de l'utilisateur est valide avant de l'utiliser
        if (idCurrentUser == null) {
            // Gérer le cas où l'ID de l'utilisateur ne peut pas être récupéré
            // Par exemple, vous pouvez renvoyer une erreur ou prendre une autre action appropriée
            throw new RuntimeException("utilisateur non connecté");
        }
        return idCurrentUser;
    }

}
