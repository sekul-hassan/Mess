package com.messmanagementback.Service;

import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Model.Profile;
import com.messmanagementback.Repository.MessInfoRepository;
import com.messmanagementback.Repository.ProfileRepository;
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

    private static final String UPLOAD_DIRECTORY = "/Images/";
    private static final String BASE_URL = "http://localhost:8080";

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MessInfoRepository messInfoRepository;

    // This is completed
    public ResponseEntity<Map<String, Object>> updateProfile(MultipartFile profilePic, String messId) {
        Map<String, Object> response = new HashMap<>();

        if (profilePic == null) {
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

                if (!Objects.requireNonNull(profilePic).isEmpty()) {
                    String profilePicExtension = ".png";
                    String profilePicName = "profilePic_" + messId + profilePicExtension;
                    File profileFile = new File(uploadPath + profilePicName);
                    profilePic.transferTo(profileFile);
                    profilePicPath = UPLOAD_DIRECTORY + profilePicName;
                }


                if (profile != null) {
                    if (profilePicPath != null) {
                        profile.setProfilePic(profilePicPath);
                    }
                    profileRepository.save(profile);
                    response.put("status", HttpStatus.OK.value());
                    response.put("message", "Profile updated successfully");
                    response.put("data", profile);
                } else {
                    Profile newProfile = new Profile();
                    newProfile.setProfilePic(profilePicPath);
                    newProfile.setMessInfo(mess);
                    profileRepository.save(newProfile);
                    response.put("status", HttpStatus.OK.value());
                    response.put("message", "New profile added");
                    response.put("data", newProfile);
                }
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

    public ResponseEntity<Map<String,Object>> getAProfile(String messId){
        Map<String, Object> response = new HashMap<>();

        try{
            Optional<Profile> profile = profileRepository.findByMessInfo_MessId(messId);
            if (profile.isPresent()) {
                Profile profile1 = profile.get();
                profile1.setProfilePic(BASE_URL + profile1.getProfilePic());
                response.put("status", HttpStatus.OK.value());
                response.put("message", "Profile found");
                response.put("data", profile1);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "Profile not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
