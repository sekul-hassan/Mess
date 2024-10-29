package com.messmanagementback.Service;

import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Model.Profile;
import com.messmanagementback.Repository.MessInfoRepository;
import com.messmanagementback.Repository.ProfileRepository;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService {

    // Relative directory for file uploads
    private static final String UPLOAD_DIRECTORY = "/Images/";

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MessInfoRepository messInfoRepository;

    public ResponseEntity<Map<String, Object>> updateProfile(MultipartFile profilePic, MultipartFile coverPic, String messId) {
        Map<String, Object> response = new HashMap<>();

        if (profilePic == null && coverPic == null) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "No files found in the request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Optional<MessInfo> messInfoOptional = messInfoRepository.findById(messId);
            if (messInfoOptional.isPresent()) {
                MessInfo mess = messInfoOptional.get();
                Profile profile = mess.getProfile();

                String uploadPath = System.getProperty("user.dir") + "/" + UPLOAD_DIRECTORY;
                File directory = new File(uploadPath);
                if (!directory.exists()) {
                    boolean dirCreated = directory.mkdirs();
                    if (!dirCreated) {
                        throw new RuntimeException("Failed to create upload directory");
                    }
                }

                String profilePicPath = null;
                String coverPicPath = null;

                if (!Objects.requireNonNull(profilePic).isEmpty()) {
                    String profilePicName = "profilePic_" + messId + "_" + profilePic.getOriginalFilename();
                    File profileFile = new File(uploadPath + profilePicName);
                    profilePic.transferTo(profileFile);
                    profilePicPath = UPLOAD_DIRECTORY + profilePicName;
                }

                if (!coverPic.isEmpty()) {
                    String coverPicName = "coverPic_" + messId + "_" + coverPic.getOriginalFilename();
                    File coverFile = new File(uploadPath + coverPicName);
                    coverPic.transferTo(coverFile);
                    coverPicPath = UPLOAD_DIRECTORY + coverPicName;
                }

                if (profile != null) {
                    if (profilePicPath != null) {
                        profile.setProfilePic(profilePicPath);
                    }
                    if (coverPicPath != null) {
                        profile.setCoverPic(coverPicPath);
                    }
                    profileRepository.save(profile);
                } else {
                    Profile newProfile = new Profile();
                    newProfile.setProfilePic(profilePicPath);
                    newProfile.setCoverPic(coverPicPath);
                    newProfile.setMessInfo(mess);
                    profileRepository.save(newProfile);
                    response.put("message", "New profile added");
                }

                response.put("status", HttpStatus.OK.value());
                response.put("data", Map.of(
                        "profilePicPath", Objects.requireNonNull(profilePicPath),
                        "coverPicPath", Objects.requireNonNull(coverPicPath)
                ));
                response.put("message", "Profile pictures updated successfully.");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "Mess not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
